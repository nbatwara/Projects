
package hw3.impl;

import hw3.api.IGameIcon;

import java.awt.Color;

/**
 * Represents a colored cell in a Tetris-style game.
 */
public class GridCell implements IGameIcon
{
  /**
   * The color associated with this cell.
   */
  private final Color color;

  /**
   * The marked status of this cell.
   */
  private boolean marked;

  /**
   * Constructs a GridCell with the given color in the unmarked state.
   * 
   * @param c
   *    color to be associated with this cell
   */
  public GridCell(Color c)
  {
    this.color = c;
  }

  public GridCell(GridCell existing)
  {
    this.color = existing.color;
    this.marked = existing.marked;
  }

  @Override
  public Color getColorHint()
  {
    return color;
  }

  /**
   * Returns true if this cell has been marked.
   * @return
   *   true if this cell is marked, false otherwise
   */
  public boolean isMarked()
  {
    return marked;
  }

  /**
   * Returns true if this cell has the same color as the given cell.
   * @param cell
   * @return
   */
  public boolean matches(GridCell cell)
  {
    return (cell != null && cell.getColorHint() == this.color);
  }

  /**
   * Sets the marked status of this cell.
   * @param marked
   */
  public void setMarked(boolean marked)
  {
    this.marked = marked;
  }

}
