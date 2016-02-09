package edu.iastate.cs228.hw3;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Neh Batwara
 *
 * @param <T>
 */
public class CS228DoublyLinkedList<T> implements CS228List<T> {
/**
 * Private Node class. 
 * 
 *
 */
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
		}
	}
	/**
	 * Variable for possible size of linked list
	 */
	private int size;
	/**
	 * Dummy node head
	 */
	private Node head;
	/**
	 * Dummy node tail
	 */
	private Node tail;
	
	public CS228DoublyLinkedList() {
		head = new Node(null);
		tail = new Node(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param toAdd
	 *            Element to add to the end of the list
	 */
	@Override
	public void add(T toAdd) {
		Node newNode = new Node(toAdd);
		Node prevNode = tail.prev;
		prevNode.next = newNode;
		newNode.prev = prevNode;
		newNode.next = tail;
		tail.prev = newNode;
		size++;
	}
	/**
	 * Inserts the specified element at the specified position in this list,
	 * shifts elements to the right if necessary
	 * 
	 * @param index
	 *            Add the element at this logical index of the list
	 * @param toAdd
	 *            Element to add to the list
	 * @return returns true if add is successful, false if not
	 * @throws IllegalArgumentException
	 *             throws IllegalArgumentException if index>size
	 */
	@Override
	public void add(int index, T toAdd) throws IllegalArgumentException {
		Node newNode = new Node(toAdd);
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			Node currentNode = head.next;
			Node previousNode = null;
			int counter = 0;
			while (counter != index) {
				currentNode = currentNode.next;
				counter++;
			}
			previousNode = currentNode.prev;
			previousNode.next = newNode;
			newNode.prev = previousNode;
			newNode.next = currentNode;
			currentNode.prev = newNode;
		}
		size++;
	}
	/**
	 * Clears list of all elements
	 */
	@Override
	public void clear() {
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	/**
	 * Tells us if the list is empty
	 * 
	 * @return true if there are no elements in the list
	 */
	@Override
	public boolean isEmpty() {
		if (head.next == tail && tail.prev == head) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Returns element at given index
	 * 
	 * @param index
	 *            index of list
	 * @return element at index
	 * @throws IllegalArgumentException
	 *             throw exception if index<0 or index>size
	 */
	@Override
	public T get(int index) throws IllegalArgumentException {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			Node currentNode = head.next;
			int counter = 0;
			while (counter != index) {
				currentNode = currentNode.next;
				counter++;
			}
			return currentNode.data;
		}
	}
	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 * 
	 * @param e
	 *            Object to compare to
	 * @return
	 */
	@Override
	public int indexOf(Object e) {
		Node currentNode = head.next;
		int count = 0;
		while (currentNode != tail) {
			if (currentNode.data.equals(e)) {
				return count;
			}
			currentNode = currentNode.next;
			count++;
		}
		return -1;
	}
	/**
	 * Create a forward-only iterator
	 * 
	 * @return
	 */
	@Override
	public Iterator<T> iterator() {
		return new CS228LinkedListIterator();
	}
	/**
	 * Create a list-iterator at index 0
	 * 
	 * @return Returns a list iterator over the elements in this list (in proper
	 *         sequence).
	 */
	@Override
	public ListIterator<T> listIterator() {
		return new CS228LinkedListIterator();
	}
	/**
	 * Create a list-iterator at given index
	 * 
	 * @param index
	 *            index to create iterator
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Override
	public ListIterator<T> listIterator(int index)
			throws IllegalArgumentException {
		if(index < 0 || index >= size){
			throw new IllegalArgumentException("Index out of bounds");
		}
		return new CS228LinkedListIterator(index);
	}
	/**
	 * Remove element at a given index
	 * 
	 * @param index
	 *            index of element to remove
	 * @return return the element that was removed
	 * @throws IllegalArgumentException
	 *             throw exception if index<0 or index>size
	 */
	@Override
	public T remove(int index) throws IllegalArgumentException {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			Node current = head.next;
			int count = 0;
			while (count < index) {
				current = current.next;
				++count;
			}
			Node prevNode = current.prev;
			Node nextNode = current.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			size--;
			return  current.data;
		}
	}
	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present
	 * 
	 * @param object
	 *            object to remove
	 * @return true if element was successfully removed
	 */
	@Override
	public boolean remove(T object) {
		int index = indexOf(object);
		try{
			remove(index);
			return true;
		}catch(Exception exc){
			return false;
		}
	}
	/**
	 * Get the size (number of elements) in the list
	 * 
	 * @return return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element
	 * 
	 * @param index
	 *            index of element to replace
	 * @param element
	 *            element to place in list
	 * @return return element that was replaced
	 * @throws IllegalArgumentException
	 *             throw exception if index<0 or index>size
	 */
	@Override
	public T set(int index, T element) throws IllegalArgumentException {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			Node current = head.next;
			int count = 0;
			while (count < index) {
				current = current.next;
				++count;
			}
			T retVal = current.data;
			current.data = element;
			return retVal;
		}
	}

	private class CS228LinkedListIterator implements ListIterator<T> {
		
		private Node currNode = null;
		private int index = -1;
		private Node lastEleNode = null;
		
		public CS228LinkedListIterator() {
			currNode = head;
		}
		
		public CS228LinkedListIterator(int index){
			if(index < 0 || index >= size){
				throw new IllegalArgumentException("Index out of bounds");
			}
			currNode = head.next;
			this.index = 0;
			while(this.index != index){
				currNode = currNode.next;
				this.index++;
			}
		}
		
		@Override
		public void add(T e) {
			CS228DoublyLinkedList.this.add(++index, e);
		}

		@Override
		public boolean hasNext() {
			return currNode.next != tail;
		}

		@Override
		public boolean hasPrevious() {
			return currNode.prev != head;
		}

		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException("No next element available");
			}
			currNode = currNode.next;
			this.index++;
			lastEleNode = currNode;
			return lastEleNode.data;
		}

		@Override
		public int nextIndex() {
			return this.index + 1;
		}

		@Override
		public T previous() {
			lastEleNode = currNode;
			currNode = currNode.prev;
			this.index--;
			return lastEleNode.data;
		}

		@Override
		public int previousIndex() {
			return this.index - 1;
		}

		@Override
		public void remove() {
			if(lastEleNode == null){
				throw new IllegalStateException("Iterator in invalid state. Call next() or previous()");
			}else{
				//do nothing
			}
			CS228DoublyLinkedList.this.remove(lastEleNode.data);
			lastEleNode = null;
		}

		@Override
		public void set(T e) {
			if(lastEleNode == null){
				throw new IllegalStateException("Iterator in invalid state. Call next() or previous()");
			}else{
				lastEleNode.data = e;
				lastEleNode = null;
			}
		}
		

	}
}