
package edu.iastate.cs228.hw4;

/**
 * Visitor for a Binary Search Tree.
 */
public interface Visitor<E extends Comparable<? super E>> {

  /**
   * Visit the specified node.
   * The implementation determines what, if anything,
   * will be done with the node.
   */
  public void visit( BSTNode<E> node );
}
