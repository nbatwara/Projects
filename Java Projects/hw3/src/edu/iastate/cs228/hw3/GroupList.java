package edu.iastate.cs228.hw3;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Neh Batwara
 *
 * @param <E>
 */
public class GroupList<E> implements CS228List<E> {
	/**
	 * number of elements possible in a doubly linked list
	 */
	private int limit;
	/**
	 * size if the number of filled elements in the list
	 */
	private int size;
	/**
	 * Array of doubly linked list. 
	 */
	private CS228DoublyLinkedList<E>[] listArray;
	/**
	 * maximum elements possible in the array
	 */
	private int maxSize;

	public GroupList(int limit) {
		this.limit = limit;
		listArray = (CS228DoublyLinkedList<E>[]) new CS228DoublyLinkedList[3];
		listArray[0] = new CS228DoublyLinkedList<E>();
		listArray[1] = new CS228DoublyLinkedList<E>();
		listArray[2] = new CS228DoublyLinkedList<E>();
		size = 0;
		maxSize = listArray.length * limit;
	}

	public GroupList() {
		new GroupList<E>(3);
	}
	/**
	 * Private function for upgrading size of the array by 50% if the array is full.
	 */

	private void sizeUpgrade() {
		int sizeIncrease = (int) (Math.ceil((1.0 / 2) * listArray.length));

		CS228DoublyLinkedList<E>[] newListArray = (CS228DoublyLinkedList<E>[]) new CS228DoublyLinkedList[listArray.length
				+ sizeIncrease];
		for (int i = 0; i < listArray.length; i++) {
			newListArray[i] = listArray[i];
		}
		for (int i = listArray.length; i < listArray.length + sizeIncrease; i++) {
			newListArray[i] = new CS228DoublyLinkedList<E>();
		}
		listArray = newListArray;
		maxSize = listArray.length * limit;
	}
	/**
	 * Private funtion for reducing size of array by 25% if more than half the array is empty.
	 */
	private void sizeReduce() {
		int sizeDecrease = (int) (Math.ceil((1.0 / 4) * listArray.length));
		CS228DoublyLinkedList<E>[] newListArray = (CS228DoublyLinkedList<E>[]) new CS228DoublyLinkedList[listArray.length
				- sizeDecrease];
		for (int i = 0; i < newListArray.length; i++) {
			newListArray[i] = listArray[i];
		}

		listArray = newListArray;
		maxSize = listArray.length * limit;

	}

	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param toAdd
	 *            Element to add to the end of the list
	 */
	@Override
	public void add(E toAdd) {
		if (size == maxSize) {
			sizeUpgrade();
		} else {
			// do nothing
		}
		int arrayIndex = size / limit;
		listArray[arrayIndex].add(toAdd);
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
	public void add(int index, E toAdd) throws IllegalArgumentException {
		if (index < 0 || index > maxSize) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			if (size == maxSize) {
				sizeUpgrade();
			} else {
				// do nothing
			}
			E lastVal = get(size - 1);
			add(lastVal);
			for (int i = size - 1; i > index; i--) {
				int arrayIndex = i / limit;
				int listIndex = i % limit;
				CS228DoublyLinkedList<E> list = listArray[arrayIndex];
				list.set(listIndex, get(i - 1));
			}
			int arrayIndex = index / limit;
			int listIndex = index % limit;
			listArray[arrayIndex].set(listIndex, toAdd);
		}
	}

	/**
	 * Clears list of all elements
	 */
	@Override
	public void clear() {
		size = 0;
		for (int i = 0; i < listArray.length; i++) {
			listArray[i].clear();
		}
	}

	/**
	 * Tells us if the list is empty
	 * 
	 * @return true if there are no elements in the list
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
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
	public E get(int index) throws IllegalArgumentException {
		if (index < 0 || index >= maxSize) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			int arrayIndex = index / limit;
			int listIndex = index % limit;
			CS228DoublyLinkedList<E> list = listArray[arrayIndex];
			return (E) list.get(listIndex);
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
		for (int i = 0; i < size; i++) {
			E element = get(i);
			if (element.equals(e)) {
				return i;
			} else {

			}
		}
		throw new NoSuchElementException("No such element");
	}

	/**
	 * Create a forward-only iterator
	 * 
	 * @return
	 */
	@Override
	public Iterator<E> iterator() {
		return new GroupListIterator();
	}

	/**
	 * Create a list-iterator at index 0
	 * 
	 * @return Returns a list iterator over the elements in this list (in proper
	 *         sequence).
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new GroupListIterator();
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
	public ListIterator<E> listIterator(int index)
			throws IllegalArgumentException {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		return new GroupListIterator(index);

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
	public E remove(int index) throws IllegalArgumentException {
		if (index < 0 || index >= maxSize) {
			throw new IllegalArgumentException("Index out of bounds");
		} else {
			if (size == (maxSize / 2) && listArray.length > 3) {
				sizeReduce();
			} else {
				// do nothing
			}
			E retVal = get(index);
			int arrayIndex = 0;
			int listIndex = 0;
			for (int i = index; i < size - 1; i++) {
				arrayIndex = i / limit;
				listIndex = i % limit;
				E val = get(i + 1);
				set(i, val);
			}
			arrayIndex = (size - 1) / limit;
			listIndex = (size - 1) % limit;
			listArray[arrayIndex].remove(listIndex);
			size--;
			return retVal;
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
	public boolean remove(E object) {
		int index = indexOf(object);
		try {
			remove(index);
			return true;
		} catch (Exception exc) {
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
	public E set(int index, E element) throws IllegalArgumentException {
		int arrayIndex = index / limit;
		int listIndex = index % limit;
		E retVal = get(index);
		listArray[arrayIndex].set(listIndex, element);
		return retVal;
	}

	private class GroupListIterator implements ListIterator<E> {
		/**
		 * 
		 */
		private int currIndex = -1;
		/**
		 * 
		 */
		private E lastEleReturned = null;

		public GroupListIterator() {
			// do nothing here
		}

		public GroupListIterator(int index) {
			currIndex = index;
		}

		@Override
		public void add(E element) {
			GroupList.this.add(++currIndex, element);
		}

		@Override
		public boolean hasNext() {
			return currIndex < size - 1;
		}

		@Override
		public boolean hasPrevious() {
			return currIndex > 0;
		}

		@Override
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException("No next element available");
			}
			lastEleReturned = GroupList.this.get(++currIndex);
			return lastEleReturned;
		}

		@Override
		public int nextIndex() {
			return currIndex + 1;
		}

		@Override
		public E previous() {
			lastEleReturned = GroupList.this.get(currIndex);
			--currIndex;
			return lastEleReturned;
		}

		@Override
		public int previousIndex() {
			return currIndex - 1;
		}

		@Override
		public void remove() {
			if (lastEleReturned == null) {
				throw new IllegalStateException(
						"Iterator in invalid state. Call next() or previous()");
			}
			GroupList.this.remove(lastEleReturned);
			lastEleReturned = null;
		}

		@Override
		public void set(E element) {
			if (lastEleReturned == null) {
				throw new IllegalStateException(
						"Iterator in invalid state. Call next() or previous()");
			}
			GroupList.this.set(indexOf(lastEleReturned), element);
		}
	}
}
