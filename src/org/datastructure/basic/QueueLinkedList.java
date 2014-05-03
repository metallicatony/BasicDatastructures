package org.datastructure.basic;

import java.util.Iterator;


/**
 * A generic implementation of Queue using linked list
 * @author skanniah
 *
 */
public class QueueLinkedList<T extends Object> implements Iterable<T> {

	private Element head;
	private Element tail;
	private class Element {
		private T data;
		private Element next;
	}
	
	/**
	 * Returns true or false based on whether the queue is empty or not
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (null == head);
	}
	
	/**
	 * Enqueues or adds the element to the end of queue
	 * If the queue is empty before enqueue, the head and tail of the queue will point to the new element enqueued
	 * @param data
	 * @return true if enqueue was successful else false
	 */
	public boolean enqueue(T data) {
		if (data != null) {
			Element prevTail = tail;
			tail = new Element();
			tail.data = data;
			tail.next = null;
			if (isEmpty()) {
				head = tail;
			} else {
				prevTail.next = tail;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Dequeues element from the start of the queue and returns the element's data. Returns null if there are no elements in queue.
	 * If the queue is empty after a successful dequeue, head and tail will be reset to null
	 * @return
	 */
	public T dequeue() {
		if (!isEmpty()) {
			T data = head.data;
			head = head.next;
			if (isEmpty()) {
				tail = head;
			}
			return data;
		}
		return null;
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
		QueueLinkedList<String> queue = new QueueLinkedList<String>();
		queue.enqueue("1");
		queue.enqueue("2");
		queue.enqueue("3");
		queue.enqueue("4");
		System.out.println("### After Enqueue ###");
		for (String element: queue) {
			System.out.println(element);
		}
		
		
		queue.dequeue();
		queue.dequeue();
		System.out.println("### After Dequeue ###");
		for (String element: queue) {
			System.out.println(element);
		}
	}
	
}
