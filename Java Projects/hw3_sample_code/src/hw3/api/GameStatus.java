package hw3.api;
/**
 * Status values for a Tetris-like game.  The meaning of each values is
 * as follows:
 * <dl>
 *   <dt>NEW_POLYOMINO
 *   <dd>There is a new current polyomino at the top of the grid
 *   <dt>FALLING
 *   <dd>The current polyomino was successfully shifted down one cell.
 *   <dt>STOPPED
 *   <dd>The current polyomino could not be shifted down, but did
 *       not complete a collapsible group (it may be possible to move
 *       the polynomial horizontally)
 *   <dt>COLLAPSING
 *   <dd>There is at least one collapsible group, and the next
 *       invocation of step() will collapse them
 *   <dt>GAME_OVER
 *   <dd>A new polyomino cannot be placed at the top of the grid without
 *       colliding with occupied cells
 * </dl>
 */
public enum GameStatus {
	NEW_POLYOMINO, FALLING, STOPPED, COLLAPSING, GAME_OVER
}
