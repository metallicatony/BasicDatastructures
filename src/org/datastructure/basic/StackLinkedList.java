package org.datastructure.basic;

import java.util.Iterator;

/**
 * A generic implementation of stack using linked list
 * @author skanniah
 *
 */
public class StackLinkedList<T extends Object> implements Iterable<T> {
	
	private Element head;
	private class Element {
		private T data;
		private Element next;
	}
	
	/**
	 * Returns true or false based on whether the stack is empty or not
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (null == head);
	}
	
	/**
	 * Push data to stack
	 * @param data
	 * @return true if push was successful else false
	 */
	public boolean push(T data) {
		if (null != data) {
			Element prevHead = head;
			head = new Element();
			head.data = data;
			head.next = prevHead;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Pops data from stack
	 * @return data if pop was successful else null
	 */
	public T pop() {
		if (!isEmpty()) {
			T data = head.data;
			head = head.next;
			return data;
		} else {
			return null;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private Element current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				final T data = current.data;
				current = current.next;
				return data;
			}

			@Override
			public void remove() {
				
			}
			
		};
	}
	
	public static void main(String[] args) {
		StackLinkedList<String> sll = new StackLinkedList<String>();
		sll.push("0");
		sll.push("1");
		sll.push("2");
		sll.push("3");
		System.out.println("### Stack Elements - After Push ###");
		for (String s: sll) {
			System.out.println(s);
		}
		sll.pop();
		sll.pop();
		sll.pop();
		sll.pop();
		System.out.println("### Stack Elements - After Pop ###");
		for (String s: sll) {
			System.out.println(s);
		}
		sll.push("0");
		System.out.println("### Stack Elements - After Push again ###");
		for (String string: sll) {
			System.out.println(string);
		}
		sll.pop();
	}
}
