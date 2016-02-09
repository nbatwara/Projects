package hw3.example;

import hw3.api.IPolyominoGenerator;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;
import hw3.impl.GridCell;

import java.util.ArrayList;

public class SampleGame extends AbstractBlockGame
{
  private int score;
  
  public SampleGame(IPolyominoGenerator givenGenerator)
  {
    super(givenGenerator);
    score = 0;
  }

  @Override
  public ArrayList<Position> determineCellsToCollapse()
  {
    ArrayList<Position> cells = new ArrayList<Position>();
    
    for (int row = 0; row < getHeight(); row += 1)
    {
      if (isRowComplete(row))
      {
        for (int col = 0; col < getWidth(); col += 1)
        {
          cells.add(new Position(row, col));
        }
        score += 1;
      }
    }
    return cells;
  }

  @Override
  public int determineScore()
  {
    return score;
  }
  
  private boolean isRowComplete(int row)
  {
    GridCell[][] grid = getGrid();
    for (int col = 0; col < grid[0].length; col += 1)
    {
      if (grid[row][col] == null)
      {
        return false;
      }
    }
    return true;
  }
}
