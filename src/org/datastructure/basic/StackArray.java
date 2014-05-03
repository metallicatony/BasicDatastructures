package org.datastructure.basic;

import java.util.Iterator;

/**
 * A generic implementation of stack using a self resizing array
 * @author skanniah
 *
 */
public class StackArray<T extends Object> implements Iterable<T> {
	private T[] list;
	// actual number of elements in the array
	private int n = 0;

	/**
	 * Creates a self resizing stack using array of size 1
	 */
	public StackArray() {
		super();
		list = (T[]) new Object[1];
	}
	
	/**
	 * Creates a self resizing stack using array of requested capacity
	 * @param capacity
	 */
	public StackArray(int capacity) {
		super();
		list = (T[]) new Object[capacity];
	}

	/**
	 * Returns true if the stack is empty
	 * @return
	 */
	public boolean isEmpty() {
		return (n == 0);
	}
	
	
	/**
	 * Pushes object to stack. Resizes if needed.
	 * Note that resize operation happens before push.
	 * @param data
	 * @return
	 */
	public boolean push(T data) {
		if (null != data) {
			if (n == list.length) {
				resize(list.length * 2);
			}
			list[n++] = data;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Pops out from stack. Resizes if needed.
	 * Note that resize operation happens after pop.
	 * @return
	 */
	public T pop() {
		if (!isEmpty()) {
			T data = list[--n];
			list[n] = null;
			if (n <= list.length/4 && n > 0) {
				// resize the stack if the utilization is lesser than or equal to quarter of the capacity
				// not needed to resize if number of elements in stack was 1 and that element was popped (the new n will be 0 then)
				resize(list.length/2);
			}
			return data;
		} else {
			return null;
		}
	}
	
	/**
	 * Resizes the existing stack by creating a new stack
	 * and copying all the elements from existing stack.
	 * 
	 * In case of a growing stack, the source of data is small
	 * In case of a shrinking stack, the new target is small
	 * And so the copying operation has been restricted to number of elements
	 * 
	 * @param capacity
	 */
	private void resize(int capacity) {
		T[] newlist = (T[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			newlist[i] = list[i];
		}
		list = newlist;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			// as array data structure stores in the order of push but stack is a LIFO data structure
			// the iterator runs reverse
			private int i = (n-1);
			
			@Override
			public boolean hasNext() {
				return (i >= 0);
			}
			
			@Override
			public T next() {
				return list[i--];
			}

			@Override
			public void remove() {
				
			}
		};
	}
	
	public static void main(String[] args) {
		StackArray<String> mystack = new StackArray<String>();
		mystack.push("0");
		mystack.push("1");
		mystack.push("2");
		mystack.push("3");
		System.out.println("### Stack Elements - After Push ###");
		for (String string: mystack) {
			System.out.println(string);
		}
		mystack.pop();
		mystack.pop();
		mystack.pop();
		mystack.pop();
		System.out.println("### Stack Elements - After Pop ###");
		for (String string: mystack) {
			System.out.println(string);
		}
		
		mystack.push("0");
		System.out.println("### Stack Elements - After Push again ###");
		for (String string: mystack) {
			System.out.println(string);
		}
		mystack.pop();
	}

}
