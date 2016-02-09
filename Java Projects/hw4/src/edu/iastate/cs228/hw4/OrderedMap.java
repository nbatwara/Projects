package edu.iastate.cs228.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * interface for an Ordered Map
 * 
 * @author Neh Batwara
 *
 * @param <O>
 *            the type with which the map is ordered
 * @param <M>
 *            the map values that are ordered by type O. So map(o) = m.
 */
public class OrderedMap<O extends Comparable<? super O>, M> implements
		OrderedMapInterface<O, M> {

	private Map<O, M> mapValues = new HashMap<O, M>();
	private BSTNode<O> root = null;
	private int size = 0;

	/**
	 * removes all items from the OrderedMap
	 */
	@Override
	public void clear() {
		mapValues.clear();
		root = null;
		size = 0;
	}

	/**
	 * 
	 * @param orderingKey
	 * @return the map value associated with this orderingKey, or null if none
	 *         exists
	 */
	@Override
	public M get(Object orderingKey) {
		return mapValues.get(orderingKey);
	}

	/**
	 * associates the given orderingKey with the given mapValue and adds the
	 * orderingKey to the BST
	 * 
	 * @param orderingKey
	 * @param mapValue
	 */
	@Override
	public void put(O orderingKey, M mapValue) {
		add(orderingKey);
		mapValues.put(orderingKey, mapValue);
		size++;
	}

	/**
	 * 
	 * @return the total number of O,M pairs in this OrderedMap
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * remove any mapping associated with this orderingKey and removes the
	 * orderingKey from the BST
	 * 
	 * @param orderingKey
	 * @return true if the remove was successful, false otherwise
	 */
	@Override
	public boolean remove(Object orderingKey) {
		if (!(orderingKey instanceof Comparable)) {
			return false;
		} else {
			mapValues.remove(orderingKey);

			boolean removed = (removeFromBST((Comparable) orderingKey) != null);
			if (removed) {
				size--;
			} else {
				// do nothing
			}
			System.out.println("Root is : " + root.data);
			return removed;
		}
	}

	/**
	 * 
	 * @return an iterator that iterates through the ordering keys in-order
	 */
	@Override
	public Iterator<O> keyIterator() {
		return new OrderedMapKeyIterator();
	}

	/**
	 * 
	 * @param orderingKey
	 * @return true if the orderingKey is in the OrderedMap. False otherwise.
	 */
	@Override
	public boolean containsOrderingKey(Object orderingKey) {
		if (orderingKey instanceof Comparable) {
			return findNode((Comparable) orderingKey) != null;
		} else {
			return false;
		}
	}

	/**
	 * determines whether this OrderedMap contains an ordering key that maps to
	 * the input mapValue
	 * 
	 * @param mapValue
	 * @return true if there is some orderingKey that maps to mapValue, false
	 *         otherwise
	 */
	@Override
	public boolean containsMapValue(Object mapValue) {
		return mapValues.containsValue(mapValue);
	}

	/**
	 * returns as an ArrayList all orderingKeys, in ascending order
	 * 
	 * @return an array of orderingKeys
	 */
	@Override
	public ArrayList<O> keysInAscendingOrder() {
		return inOrderTraversal();
	}

	/**
	 * 
	 * @return an ArrayList of all map values, in ascending order (as ordered by
	 *         ordering keys)
	 */
	@Override
	public ArrayList<M> valuesInAscendingOrder() {
		ArrayList<M> retList = new ArrayList<M>();
		ArrayList<O> keysInAscOrder = keysInAscendingOrder();
		for (O key : keysInAscOrder) {
			retList.add(mapValues.get(key));
		}
		return retList;
	}

	/**
	 * ceiling function for ordering keys returns least orderingKey > the input,
	 * or null if there is no such ordering key
	 * 
	 * @param orderingKey
	 * @return the least object of type O that is greater than the input
	 */
	@Override
	public O ceiling(O orderingKey) {
		BSTNode<O> node = findNode(orderingKey);
		if (node == null) {
			// ordering key does not exist
			return null;
		} else {
			BSTNode<O> successorNode = successor(node);
			if (successorNode == null) {
				return null;
			} else {
				return successorNode.data;
			}
		}
	}

	/**
	 * 
	 * @param fromKey
	 * @param toKey
	 * @return an orderedMap that takes all orderingKeys and map values >=
	 *         fromkey and <= toKey (inclusive) or returns null if either
	 *         fromKey or toKey does not exist
	 */
	@Override
	public OrderedMapInterface<O, M> subMap(O fromKey, O toKey) {
		if (containsOrderingKey(fromKey) && containsOrderingKey(toKey)) {
			int compVal = fromKey.compareTo(toKey);
			if (compVal > 0) {
				return null;
			} else {
				// do nothing
			}

			ArrayList<O> keysInAscOrder = keysInAscendingOrder();
			OrderedMapInterface<O, M> retMap = new OrderedMap<O, M>();
			for (O key : keysInAscOrder) {
				retMap.put(key, mapValues.get(key));
			}
			return retMap;
		} else {
			// do nothing here.
		}
		return null;
	}

	/**
	 * Adds the ordering key to the binary tree
	 * 
	 * @param orderingKey
	 */
	private void add(O orderingKey) {
		BSTNode<O> newNode = new BSTNode<O>(orderingKey);
		if (root == null) {
			root = newNode;
		} else {
			BSTNode<O> iterNode = root;
			while (true) {
				int compareVal = orderingKey.compareTo(iterNode.data);
				if (compareVal <= 0) {
					if (iterNode.leftChild == null) {
						iterNode.leftChild = newNode;
						break;
					} else {
						iterNode = iterNode.leftChild;
					}
				} else {
					if (iterNode.rightChild == null) {
						iterNode.rightChild = newNode;
						break;
					} else {
						iterNode = iterNode.rightChild;
					}
				}
			}
			// when code reaches here, we have iterNode as parent of our newNode
			newNode.parent = iterNode;
		}

	}

	/**
	 * Returns successor of the given node in the binary search tree
	 * 
	 * @param node
	 *            whose successor is to be found
	 * @return successor of the given node, if exists, null otherwise.
	 */
	private BSTNode<O> successor(BSTNode<O> node) {
		// Case 1 : Node is a leaf node. There is no successor
		if (node.leftChild == null && node.rightChild == null) {
			return null;
		} else {
			// Case 2 : Node has Single Child
			if (node.leftChild == null && node.rightChild != null) {
				return node.rightChild;
			} else if (node.leftChild != null && node.rightChild == null) {
				return node.leftChild;
			} else {
				// Case 3 : Node has both the children.
				BSTNode<O> iterNode = node.rightChild;
				while (iterNode.leftChild != null) {
					iterNode = iterNode.leftChild;
				}
				return iterNode;
			}
		}

	}

	private O removeFromBST(BSTNode<O> node) {
		if (node == null) {
			return null;
		} else {
			// do nothing here. go ahead.
		}
		BSTNode<O> successorNode = successor(node);
		BSTNode<O> parentNode = node.parent;
		boolean removingRoot = (parentNode == null);
		if (successorNode == null) {
			// No successor node. Remove this node
			if (!removingRoot) {
				if (parentNode.leftChild == node) {
					parentNode.leftChild = null;
				} else {
					parentNode.rightChild = null;
				}
			} else {
				root = null;
			}
			return node.data;
		} else {
			successorNode.leftChild = node.leftChild;
			successorNode.rightChild = node.rightChild;
			BSTNode<O> successorParentNode = successorNode.parent;
			if (successorParentNode != null) {
				if (successorParentNode.leftChild == successorNode) {
					successorParentNode.leftChild = null;
				} else {
					successorParentNode.rightChild = null;
				}
			} else {
				// do nothing here. we are fine
			}
			successorNode.parent = node.parent;
			if (removingRoot) {
				root = successorNode;
			} else {
				if (parentNode.leftChild == node) {
					parentNode.leftChild = successorNode;
				} else {
					parentNode.rightChild = successorNode;
				}
			}
			return node.data;
		}
	}

	private O removeFromBST(Comparable data) {
		BSTNode<O> node = findNode(data);
		if (node == null) {
			return null;
		} else {
			// do nothing here. go ahead.
		}
		BSTNode<O> successorNode = successor(node);
		BSTNode<O> parentNode = node.parent;
		boolean removingRoot = (parentNode == null);
		if (successorNode == null) {
			// No successor node. Remove this node
			if (!removingRoot) {
				if (parentNode.leftChild == node) {
					parentNode.leftChild = null;
				} else {
					parentNode.rightChild = null;
				}
			} else {
				root = null;
			}
			return node.data;
		} else {
			if (node.leftChild != successorNode) {
				successorNode.leftChild = node.leftChild;
			}else{
				
			}
			
			if (node.rightChild != successorNode) {
				successorNode.rightChild = node.rightChild;
			}else{
				
			}
			
			BSTNode<O> successorParentNode = successorNode.parent;
			if (successorParentNode != null) {
				if (successorParentNode.leftChild == successorNode) {
					successorParentNode.leftChild = null;
				} else {
					successorParentNode.rightChild = null;
				}
			} else {
				// do nothing here. we are fine
			}
			successorNode.parent = node.parent;
			if (removingRoot) {
				root = successorNode;
			} else {
				if (parentNode.leftChild == node) {
					parentNode.leftChild = successorNode;
				} else {
					parentNode.rightChild = successorNode;
				}
			}
			return node.data;
		}
	}

	private BSTNode<O> findNode(Comparable data) {
		if (root == null) {
			return null;
		} else {
			// do nothing here. go ahead
		}

		BSTNode<O> iterNode = root;
		while (!iterNode.data.equals(data)) {
			int compVal = data.compareTo(iterNode.data);
			if (compVal <= 0) {
				if (iterNode.leftChild == null) {
					return null;
				} else {
					iterNode = iterNode.leftChild;
				}
			} else {
				if (iterNode.rightChild == null) {
					return null;
				} else {
					iterNode = iterNode.rightChild;
				}
			}
		}
		return iterNode;

	}

	public void inOrderTraversal(Visitor v) {
		Stack<BSTNode<O>> stack = new Stack<BSTNode<O>>();
		BSTNode<O> iterNode = root;
		pushLeftNodesToStack(stack, iterNode);
		while (!stack.isEmpty()) {
			BSTNode<O> node = stack.pop();
			v.visit(node);
			pushLeftNodesToStack(stack, node.rightChild);
		}
	}

	private void pushLeftNodesToStack(Stack stack, BSTNode<O> node) {
		while (node != null) {
			stack.push(node);
			node = node.leftChild;
		}
	}

	private ArrayList<O> inOrderTraversal() {
		ArrayList<O> retList = new ArrayList<O>();
		Stack<BSTNode<O>> stack = new Stack<BSTNode<O>>();
		BSTNode<O> iterNode = root;
		pushLeftNodesToStack(stack, iterNode);
		while (!stack.isEmpty()) {
			BSTNode<O> node = stack.pop();
			retList.add(node.data);
			pushLeftNodesToStack(stack, node.rightChild);
		}
		return retList;
	}

	private class OrderedMapKeyIterator implements Iterator<O> {

		private int currIndex = 0;
		private O[] keysInOrder;

		public OrderedMapKeyIterator() {
			ArrayList<O> keysList = keysInAscendingOrder();
			keysInOrder = (O[]) new Comparable[keysList.size()];
			int iter = 0;
			for (O key : keysList) {
				keysInOrder[iter++] = key;
			}
		}

		@Override
		public boolean hasNext() {
			return currIndex < keysInOrder.length;
		}

		@Override
		public O next() {
			if (!hasNext()) {
				throw new NoSuchElementException(
						"There is no available element");
			} else {
				return keysInOrder[currIndex++];
			}
		}

	}

}
