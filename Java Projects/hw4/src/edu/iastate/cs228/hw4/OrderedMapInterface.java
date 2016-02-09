package edu.iastate.cs228.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/**
 * interface for an Ordered Map
 * @author sterling
 *
 * @param <O> the type with which the map is ordered
 * @param <M> the map values that are ordered by type O.  So map(o) = m.
 */

public interface OrderedMapInterface<O extends Comparable<? super O>, M> {
	
	/**
	 * removes all items from the OrderedMap
	 */
	public void clear();
	
	/**
	 * 
	 * @param orderingKey
	 * @return the map value associated with this orderingKey, or null if none exists
	 */
	public M get(Object orderingKey);
	
	/**
	 * associates the given orderingKey with the given mapValue and adds the orderingKey to the BST
	 * @param orderingKey
	 * @param mapValue
	 */
	public void put(O orderingKey, M mapValue);
	
	/**
	 * 
	 * @return the total number of O,M pairs in this OrderedMap
	 */
	public int size();
	
	/**
	 * remove any mapping associated with this orderingKey and removes the orderingKey from the BST
	 * @param orderingKey
	 * @return true if the remove was successful, false otherwise
	 */
	public boolean remove(Object orderingKey);
	
	/**
	 * 
	 * @return an iterator that iterates through the ordering keys in-order
	 */
	public Iterator<O> keyIterator();
	
	/**
	 * 
	 * @param orderingKey
	 * @return true if the orderingKey is in the OrderedMap. False otherwise.
	 */
	public boolean containsOrderingKey(Object orderingKey);
	
	/**
	 * determines whether this OrderedMap contains an ordering key that maps
	 * to the input mapValue
	 * @param mapValue
	 * @return true if there is some orderingKey that maps to mapValue, false otherwise
	 */
	public boolean containsMapValue(Object mapValue);
	
	/**
	 * returns as an ArrayList all orderingKeys, in ascending order
	 * @return an array of orderingKeys
	 */
	public ArrayList<O> keysInAscendingOrder();
	
	/**
	 * 
	 * @return an ArrayList of all map values, in ascending order (as ordered by ordering keys)
	 */
	public ArrayList<M> valuesInAscendingOrder();
	
	
	/**
	 * ceiling function for ordering keys
	 * returns least orderingKey > the input,
	 * or null if there is no such ordering key
	 * @param orderingKey
	 * @return the least object of type O that is greater than the input
	 */
	public O ceiling(O orderingKey);
	
	
	/**
	 * 
	 * @param fromKey
	 * @param toKey
	 * @return an orderedMap that takes all orderingKeys and map values
	 * >= fromkey and <= toKey (inclusive)
	 * or returns null if either fromKey or toKey does not exist
	 */
	public OrderedMapInterface<O,M> subMap(O fromKey, O toKey);
}