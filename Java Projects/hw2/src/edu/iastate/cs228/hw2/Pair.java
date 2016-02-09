package edu.iastate.cs228.hw2;

/**
 * Pair is a generic class whose two elements can take two dierent types. To
 * achieve this, we will use two generic type parameters E, T. So the Pair
 * class should be in the form of Pair<E, T>. Both E and T must extend the
 * Comparable interface. Also, the Pair class itself must implement the
 * Comparable interface as well.
 * 
 * @author Neh Batwara
 *
 * @param <E> Generic E type 
 * @param <T> Generic T type
 */
public class Pair<E, T> implements Comparable<Pair<E, T>> {
	private E firstElement;
	private T secondElement;

	public Pair(E e, T t) {
		if (e == null || t == null) {
			throw new IllegalArgumentException("e or t is null");
		} else {
			if (e instanceof Comparable && t instanceof Comparable) {
				firstElement = e;
				secondElement = t;
			} else {
				throw new IllegalArgumentException(
						" e or t not of type Comparable");
			}
		}
	}
	/**
	 * Getter method for getting first element of the pair. 
	 * @return : first element of the pair.
	 */
	public E getFirstElement() {
		return firstElement;
	}
	/**
	 * Getter method for getting second element of the pair. 
	 * @return : second element of the pair.
	 */
	public T getSecondElement() {
		return secondElement;
	}
	/**
	 * compareTo method must be implemented since the pair class implements the Comparable interface. 
	 */
	@Override
	public int compareTo(Pair objPair) {
		// TODO Auto-generated method stub
		Comparable C1 = (Comparable) firstElement;
		Comparable C2 = (Comparable) secondElement;
		Comparable objPairFirst = (Comparable) objPair.getFirstElement();
		Comparable objPairSecond = (Comparable) objPair.getSecondElement();
		if (C1.compareTo(objPairFirst) > 0) {
			return 1;
		} else if (C1.compareTo(objPairFirst) < 0) {
			return -1;
		} else {
			if (C2.compareTo(objPairSecond) > 0) {
				return 1;
			} else if (C2.compareTo(objPairSecond) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	/**
	 * toString method to return String in specific format. 
	 */
	@Override
	public String toString() {
		return firstElement + " , " + secondElement;
	}

}
