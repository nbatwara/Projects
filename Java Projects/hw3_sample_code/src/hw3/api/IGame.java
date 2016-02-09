package hw3.api;

import java.awt.Point;

/**
 * Interface for a Tetris-like game. Implementations of instances of this
 * interface should maintain all aspects of the game state.  The state
 * consists of a grid of IPolyomino objects representing occupied
 * and unoccupied positions, plus a current IPolyomino whose position is
 * normally updated by the step() method and can be altered by the
 * rotate(), shiftLeft(), and shiftRight() methods.
 * An additional part of the game state is the status that is returned by
 * each invocation of the step() method.  The status values are described
 * in detail in the GameStatus documentation.
 */
public interface IGame {
	/**
	 * Transition the game through one discrete step.  A step may consist of
	 * <ul>
	 *   <li>shifting the current polyomino down by one cell
	 *   <li>changing the status to COLLAPSING when when the current polyomino cannot
	 *       be dropped further and completes a collapsible group (e.g.,
	 *       in the standard Tetris game, a collabsible group would be a
	 *       completed horizontal row)
	 *   <li>changing the status to STOPPED when the current polyomino cannot
	 *       be dropped further, but does not complete a collapsible group
	 *   <li>changing the status to NEW_POLYOMINO when a new current polyomino
	 *       is started at the top of the grid
	 *   <li>deleting all cells to be collapsed
	 *   <li>changing the status to GAME_OVER when a new polyomino collides with
	 *       occupied cells in the top row
	 * </ul>
	 * @return the game status after the step
	 */
	GameStatus step();

	/**
	 * Performs a transform() operation on the current polyomino if it is
	 * possible to do so without letting it extend beyond the sides or
	 * bottom of the grid and without colliding with occupied cells in the grid.
	 *
	 * @return true if the current polyomino was moved, false otherwise
	 */
	boolean transform();

	/**
	 * Performs a cycle() operation on the current polyomino.
	 */
	void cycle();

	/**
	 * Shifts the current polyomino one cell to the left (decreasing x-coordinate),
	 * if it is possible to do so without letting it extend beyond the sides or
	 * bottom of the grid and without colliding with occupied cells in the grid.
	 *
	 * @return true if the current polyomino was moved, false otherwise
	 */
	boolean shiftLeft();

	/**
	 * Shifts the current polyomino one cell to the right (increasing x-coordinate),
	 * if it is possible to do so without letting it extend beyond the sides or
	 * bottom of the grid and without colliding with occupied cells in the grid.
	 *
	 * @return true if the current polyomino was moved, false otherwise
	 */
	boolean shiftRight();

	/**
	 * Returns the block associated with the given cell, or null if
	 * the cell is unoccupied.
	 * @param row the y-coordinate of the cell
	 * @param col the x-coordinate of the cell
	 * @return the block associated with the given cell.
	 * @throws ArrayIndexOutOfBoundsException if the (x,y) coordinates
	 *         are invalid for the grid
	 */
	IGameIcon getGridIcon(int row, int col);

	/**
	 * Returns the current polyomino.
	 * @return the current polyomino.
	 * @throws IllegalStateException if the game status is COLLAPSING or GAME_OVER
	 */
	IPolyomino getCurrent();

	/**
	 * Returns the width of the grid.
	 * @return the width of the grid.
	 */
	int getWidth();

	/**
	 * Returns the height of the grid.
	 * @return the height of the grid.
	 */
	int getHeight();

	/**
	 * Returns the locations of cells to be collapsed as an array
	 * of java.awt.Point. The purpose of this method is
	 * to allow GUIs to apply special rendering or animation to the
	 * collapsing cells.  Note that the returned array is allowed to
	 * contain duplicates.  The first coordinate (x) of each point is the
	 * column, and the second coordinate (y) is the row.
	 *
	 * @return array of Point objects representing the cells to be
	 * collapsed
	 * @throws IllegalStateException if the game status is not COLLAPSING
	 */
	Point[] getCellsToCollapse();


	/**
	 * Returns the current score for this game, for example, the total cells
	 * collapsed.
	 * @return the current score for this game
	 */
	int getScore();

	/**
	 * Determines whether the game is over, which occurs when a new
	 * polyomino in its initial position collides with occupied cells
	 * of the grid.
	 * @return true if the game is over, false otherwise
	 */
	boolean gameOver();
}
