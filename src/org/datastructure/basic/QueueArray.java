package org.datastructure.basic;

import java.util.Iterator;

/**
 * A generic implementation of queue using a self resizing array
 * @author skanniah
 *
 */
public class QueueArray<T extends Object> implements Iterable<T> {
	private T[] list;
	// head index points to the first element in the queue
	private int headIndex = 0;
	// tail index points to the next available position for a new element
	private int tailIndex = 0;
	
	/**
	 * Creates a self resizing queue using array of size 1
	 * 
	 */
	public QueueArray() {
		super();
		list = (T[]) new Object[1];
	}
	
	/**
	 * Creates a self resizing queue using array of requested capacity
	 * @param capacity
	 */
	public QueueArray(int capacity) {
		super();
		list = (T[]) new Object[capacity];
	}
	
	/**
	 * Returns true if queue is empty
	 * @return
	 */
	public boolean isEmpty() {
		return (0 == tailIndex);
	}

	/**
	 * Enqueues to queue, resizes if needed
	 * @param data
	 * @return
	 */
	public boolean enqueue(T data) {
		if (data != null) {
			if (!isEmpty()) {
				// queue is full
				if ((tailIndex - headIndex) == list.length) {
					resize(2*list.length);
				}
			}
			// if the queue is empty or if the queue is not full
			list[tailIndex++] = data;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Dequeues from queue, resizes if needed
	 * @return
	 */
	public T dequeue() {
		if (!isEmpty()) {
			T data = list[headIndex];
			list[headIndex++] = null;
			if ( ((tailIndex - headIndex) <= list.length/4) && list.length > 1) {
				// resize the queue to half it's capacity, if the utilization is lesser than or equal to quarter of the capacity
				// do not resize if number of elements in queue was 1 and that element was dequeued (the new number of elements will be 0 then)
				resize(list.length/2);
			}
			return data;
		} else {
			return null;
		}
	}
	
	/**
	 * Resizes the queue to the provided capacity
	 * @param capacity
	 */
	private void resize(int capacity) {
		T[] newlist = (T[]) new Object[capacity];
		int i = headIndex;
		int j = 0;
		for (; i < tailIndex; i++, j++) {
			newlist[j] = list[i];
		}
		headIndex = 0;
		tailIndex = j;
		list = newlist;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private int i = headIndex;
			
			@Override
			public boolean hasNext() {
				return (i < (tailIndex - headIndex));
			}

			@Override
			public T next() {
				return list[i++];
			}

			@Override
			public void remove() {
			}
		};
	}
	
	public static void main(String[] args) {
		QueueArray<String> queue = new QueueArray<String>();
		queue.enqueue("0");
		queue.enqueue("1");
		queue.enqueue("2");
		queue.enqueue("3");
		System.out.println("### Queue Elements - After Enqueue ###");
		for (String string: queue) {
			System.out.println(string);
		}
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		System.out.println("### Queue Elements - After Dequeue ###");
		for (String string: queue) {
			System.out.println(string);
		}
		
		queue.enqueue("1");
		System.out.println("### Queue Elements - After Enqueue again ###");
		for (String string: queue) {
			System.out.println(string);
		}
		queue.dequeue();
	}

}
