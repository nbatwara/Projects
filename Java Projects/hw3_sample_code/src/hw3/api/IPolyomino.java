
package hw3.api;

import hw3.impl.Block;


/**
 * Interface for polyominoes (game pieces made of multiple square blocks) used
 * by Tetris-like games. Each polyomino has a position and a set of Block
 * objects. The position is an (x,y) pair representing the upper left corner of
 * the bounding square. The position is represented by an instance of
 * java.awt.Point. The initial position is presumed to be given by a
 * constructor; thereafter it can be modified by the shiftX() and shiftY()
 * methods. Note that classes implementing this interface have no knowledge of
 * the grid on which the game is being played and
 * <em>no bounds checking is done in
 * implementations of this interface<em>. Therefore, the position can have
 * negative coordinates.
 */
public interface IPolyomino extends Cloneable
{
  /**
   * Returns the position of the upper left corner of the bounding
   * square for this polyomino.
   * @return 
   *   position of this polyomino
   */
  Position getPosition();
  
  /**
   * Sets the position of the upper left corner of the bounding square
   * for this polyomino.
   * @param position
   *   new position for this polyomino
   */
  void initializePosition(Position givenPosition);
  
  /**
   * Returns an array of Block objects containing the absolute positions
   * of the blocks in this polyomino
   * 
   * @return 
   *   the blocks in this polyomino with their absolute positions
   */
  Block[] getBlocks();

  /**
   * Sets the blocks to be occupied by this polyomino.  The positions of the given
   * blocks are relative to the upper-left corner of the bounding square. 
   * @param
   *    the blocks that are part of this polyomino with positions relative to
   *    upper left corner of bounding square
   */
  void initializeBlocks(Block[] givenBlocks);
  
  /**
   * Shifts the position of this polyomino down (increasing the y-coordinate) by
   * one cell. No bounds checking is done.
   *
   * @throws IllegalStateException
   *           if this object is in the frozen state
   */
  void shiftDown();

  /**
   * Shifts the position of this polyomino left (decreasing the x-coordinate) by
   * one cell. No bounds checking is done.
   */
  void shiftLeft();

  /**
   * Shifts the position of this polyomino right (increasing the x-coordinate)
   * by one cell. No bounds checking is done.
   */
  void shiftRight();

  /**
   * Transforms this polyomino without altering its position according to the
   * rules of the game to be implemented. Typical operations are rotation and
   * reflection within the bounding square. No bounds checking is done.
   */
  void transform();

  /**
   * Cycles the blocks forward within the occupied cells of this polyomino,
   * based on the top-to-bottom ordering of the cells in the original position.
   * The last block should wrap around to the first cell. (This method has no
   * apparent effect when all blocks are the same color.)
   */
  void cycle();

  /**
   * Returns a deep copy of this object.
   * 
   * @return 
   *   a deep copy of this object.
   */
  IPolyomino clone();
}
