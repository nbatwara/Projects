package hw3.api;

/**
 * Class encapsulating a position in a 2d grid as a row and column.
 */
public class Position
{
  /**
   * Row for this position.
   */
  private int col;
  
  /**
   * Column for this position.
   */
  private int row;
  
  /**
   * Constructs a Position with the given row and column.
   * @param givenRow
   * @param givenColumn
   */
  public Position(int givenRow, int givenColumn)
  {
    col = givenColumn;
    row = givenRow;
  }
  
  /**
   * Constructs a copy of the given Position.
   * @param existing
   */
  public Position(Position existing)
  {
    col = existing.col;
    row = existing.row;
  }
  
  /**
   * Returns the row.
   * @return
   */
  public int getRow()
  {
    return row;
  }
  
  /**
   * Returns the column.
   * @return
   */
  public int getCol()
  {
    return col;
  }
  
  /**
   * Sets the row.
   * @param givenRow
   */
  public void setRow(int givenRow)
  {
    row = givenRow;
  }
  
  /**
   * Sets the column.
   * @param givenCol
   */
  public void setCol(int givenCol)
  {
    col = givenCol;
  }
  
  @Override
  public String toString()
  {
    return "(" + row + ", " + col + ")";
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != Position.class)
    {
      return false;
    }
    Position other = (Position) obj;
    return this.col == other.col && this.row == other.row;
  }
}
