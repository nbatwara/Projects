package edu.iastate.cs228.hw3;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Doubly Linked-list interface for CS228 Summer 2015
 * 
 * @author Andrew Nguyen
 *
 * @param <E>
 */
public interface CS228List<E> {

	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param toAdd
	 *            Element to add to the end of the list
	 */
	public void add(E toAdd);

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
	public void add(int index, E toAdd) throws IllegalArgumentException;

	/**
	 * Clears list of all elements
	 */
	public void clear();

	/**
	 * Compares the specified object with this list for equality.
	 * 
	 * @param o
	 *            Object to compare to
	 * @return
	 */
	public boolean equals(Object o);

	/**
	 * Tells us if the list is empty
	 * 
	 * @return true if there are no elements in the list
	 */
	public boolean isEmpty();

	/**
	 * Returns element at given index
	 * 
	 * @param index
	 *            index of list
	 * @return element at index
	 * @throws IllegalArgumentException
	 *             throw exception if index<0 or index>size
	 */
	public E get(int index) throws IllegalArgumentException;

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 * 
	 * @param e
	 *            Object to compare to
	 * @return
	 */
	public int indexOf(Object e);

	/**
	 * Create a forward-only iterator
	 * 
	 * @return
	 */
	public Iterator<E> iterator();

	/**
	 * Create a list-iterator at index 0
	 * 
	 * @return Returns a list iterator over the elements in this list (in proper
	 *         sequence).
	 */
	public ListIterator<E> listIterator();

	/**
	 * Create a list-iterator at given index
	 * 
	 * @param index
	 *            index to create iterator
	 * @return
	 * @throws IllegalArgumentException
	 */
	public ListIterator<E> listIterator(int index)
			throws IllegalArgumentException;

	/**
	 * Remove element at a given index
	 * 
	 * @param index
	 *            index of element to remove
	 * @return return the element that was removed
	 * @throws IllegalArgumentException
	 *             throw exception if index<0 or index>size
	 */
	public E remove(int index) throws IllegalArgumentException;

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present
	 * 
	 * @param object
	 *            object to remove
	 * @return true if element was successfully removed
	 */
	public boolean remove(E object);

	/**
	 * Get the size (number of elements) in the list
	 * 
	 * @return return the size of the list
	 */
	public int size();

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
	public E set(int index, E element) throws IllegalArgumentException;
}