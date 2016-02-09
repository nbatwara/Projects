
package hw3.impl;

import hw3.api.GameStatus;
import hw3.api.IGame;
import hw3.api.IPolyomino;
import hw3.api.IPolyominoGenerator;
import hw3.api.Position;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A partial implementation of the IGame interface for Tetris-like falling block
 * games. Subclasses must implement the determineCellsToCollapse() and
 * determineScore() methods.
 */
public abstract class AbstractBlockGame implements IGame
{
  /**
   * Possible list of colors for the Blocks.
   */
  public static final Color[] COLORS = {
    Color.CYAN, 
    Color.BLUE, 
    Color.ORANGE, 
    Color.YELLOW, 
    Color.GREEN, 
    Color.MAGENTA,
    Color.RED};

  /**
   * Possible list of color abbreviations for the Blocks.
   */
  public static final String[] COLOR_NAMES = {
    "C",
    "B",
    "O",
    "Y",
    "G",
    "M",
    "R"};

  /**
   * Width of the game grid.
   */
  public static final int WIDTH = 12;

  /**
   * Height of the game grid.
   */
  public static final int HEIGHT = 24;

  /**
   * The polyomino that is subject to motion during the step() method or via
   * invocations of the shiftX() or transform() methods.
   */
  private IPolyomino current;

  /**
   * A WIDTH x HEIGHT grid of cells.  A cell may be occupied by a GridCell.
   * Unoccupied cells are null.  The blocks in the falling polyomino are not
   * actually added to the grid until it reaches the bottom.
   */
  private GridCell[][] grid;

  /**
   * Status of the game after each invocation of step(), as described in the
   * GameStatus documentation.
   */
  private GameStatus gameStatus;

  /**
   * Generator for new polyominoes.
   */
  private IPolyominoGenerator generator;

  /**
   * State variable indicating which blocks to be deleted when the status is
   * COLLAPSING. The implementation maintains the invariant that
   * cellsToCollapse.size() is nonzero if and only if gameStatus is COLLAPSING.
   */
  private ArrayList<Position> cellsToCollapse;

  /**
   * Constructs a new AbstractBlockGame with an empty grid.
   */
  protected AbstractBlockGame(IPolyominoGenerator generator)
  {
    grid = new GridCell[getHeight()][getWidth()];
    this.generator = generator;
    current = generator.getNext();
    gameStatus = GameStatus.NEW_POLYOMINO;
  }

  
  /**
   * Returns a reference to the grid for this game.  Logically, this method should
   * be <code>protected</code>, but it is declared <code>public</code> to
   * facilitate testing of the <code>determineCellsToCollapse()</code> method.
   * @return
   *   reference to the grid for this game
   */
  public GridCell[][] getGrid()
  {
    return grid;
  }
  
  /**
   * Returns a list of locations for all cells that form part of a collapsible
   * group. This list may contain duplicates.
   * 
   * @return list of locations for cells to be collapsed
   */
  public abstract ArrayList<Position> determineCellsToCollapse();

  /**
   * Returns the current score.
   * 
   * @return the current score
   */
  public abstract int determineScore();

  @Override
  public int getHeight()
  {
    return HEIGHT;
  }

  @Override
  public int getWidth()
  {
    return WIDTH;
  }

  @Override
  public GridCell getGridIcon(int row, int col)
  {
    return grid[row][col];
  }

  @Override
  public IPolyomino getCurrent()
  {
    if (gameStatus == GameStatus.COLLAPSING || gameStatus == GameStatus.GAME_OVER)
    {
      throw new IllegalStateException();
    }
    return current;
  }

  @Override
  public Point[] getCellsToCollapse()
  {
    if (cellsToCollapse.size() == 0)
    {
      throw new IllegalStateException();
    }
    Point[] points = new Point[cellsToCollapse.size()];
    for (int i = 0; i < cellsToCollapse.size(); ++i)
    {
      Position p = cellsToCollapse.get(i);
      points[i] = new Point(p.getCol(), p.getRow());  // column is "x", row is "y"
    }
    return points;
  }

  @Override
  public boolean transform()
  {
    boolean can = canTransform();
    if (can)
    {
      current.transform();
    }
    return can;
  }

  private boolean canTransform()
  {
    IPolyomino p = current.clone();
    p.transform();
    return !collides(p);
  }

  @Override
  public boolean shiftLeft()
  {
    boolean can = canShiftLeft();
    if (can)
    {
      current.shiftLeft();
    }
    return can;
  }

  private boolean canShiftLeft()
  {
    IPolyomino p = current.clone();
    p.shiftLeft();
    return !collides(p);
  }

  @Override
  public boolean shiftRight()
  {
    boolean can = canShiftRight();
    if (can)
    {
      current.shiftRight();
    }
    return can;
  }

  private boolean canShiftRight()
  {
    IPolyomino p = current.clone();
    p.shiftRight();
    return !collides(p);
  }

  @Override
  public int getScore()
  {
    return determineScore();
  }

  @Override
  public void cycle()
  {
    current.cycle();
  }

  @Override
  public boolean gameOver()
  {
    return gameStatus == GameStatus.GAME_OVER;
  }

  @Override
  public GameStatus step()
  {
    switch (gameStatus)
    {
      case GAME_OVER:
        // do nothing
        break;
      case NEW_POLYOMINO:
      case FALLING:
        if (gameStatus == GameStatus.NEW_POLYOMINO)
        {
          gameStatus = GameStatus.FALLING;
        }
        if (canShiftDown())
        {
          current.shiftDown();
        }
        else
        {
          // Add blocks of the current polyomino to the grid, maybe
          // temporarily, in order to check whether it has completed
          // a collapsible group
          for (Block c : current.getBlocks())
          {
            Position p = c.getPosition();
            if (p.getRow() >= 0 && p.getRow() < HEIGHT && p.getCol() >= 0 && p.getCol() < WIDTH)
            {
              grid[p.getRow()][p.getCol()] = new GridCell(c.getColorHint());
            }
          }
          cellsToCollapse = determineCellsToCollapse();
          if (cellsToCollapse.size() != 0)
          {
            // current polyomino completes a collapsible group,
            // so prepare to collapse
            gameStatus = GameStatus.COLLAPSING;
          }
          else
          {
            // current polyomino is stopped, but has not completed a
            // collapsible group, so it might be moved sideways;
            // take its blocks back out of the grid
            for (Block c : current.getBlocks())
            {
              Position p = c.getPosition();
              if (p.getRow() >= 0 && p.getRow() < HEIGHT && p.getCol() >= 0 && p.getCol() < WIDTH)
              {
                grid[p.getRow()][p.getCol()] = null;
              }
            }
            gameStatus = GameStatus.STOPPED;
          }
        }
        break;
      case STOPPED:
        // If the polyomino was previously stopped, it still may be possible
        // to shift it downwards since it could have been moved to the side
        // during the last step
        if (canShiftDown())
        {
          current.shiftDown();
          gameStatus = GameStatus.FALLING;
        }
        else
        {
          // we only get in the stopped state when the polyomino doesn't
          // complete
          // a collapsible group; start a new polyomino at the top
          for (Block c : current.getBlocks())
          {
            Position p = c.getPosition();
            if (p.getRow() >= 0 && p.getRow() < HEIGHT && p.getCol() >= 0 && p.getCol() < WIDTH)
            {
              grid[p.getRow()][p.getCol()] = new GridCell(c.getColorHint());
            }
          }
          current = generator.getNext();
          if (collides(current))
          {
            gameStatus = GameStatus.GAME_OVER;
          }
          else
          {
            gameStatus = GameStatus.NEW_POLYOMINO;
          }
        }
        break;
      case COLLAPSING:
        collapseCells(cellsToCollapse);
        cellsToCollapse = determineCellsToCollapse();
        if (cellsToCollapse.size() == 0)
        {
          // done collapsing, try to start a new polyomino
          current = generator.getNext();
          if (collides(current))
          {
            gameStatus = GameStatus.GAME_OVER;
          }
          else
          {
            gameStatus = GameStatus.NEW_POLYOMINO;
          }
        }
        break;
    }
    return gameStatus;
  }

  /**
   * Determines whether the current polyomino can be shifted down. Does not
   * modify the game state.
   * 
   * @return true if the current polyomino can be shifted down, false otherwise
   */
  private boolean canShiftDown()
  {
    IPolyomino t = (IPolyomino)current.clone();
    t.shiftDown();
    return !collides(t);
  }

  /**
   * Determines whether the given polyomino overlaps with the occupied cells of
   * the grid, or extends beyond the sides or bottom of the grid. (A polyomino
   * in its initial position MAY extend above the grid.)
   *
   * @param t
   *          a polyomino
   * @return true if the cells of the given polyomino extend beyond the sides or
   *         bottom of the grid or overlap with any occupied cells of the grid
   */
  private boolean collides(IPolyomino t)
  {
    for (Block c : t.getBlocks())
    {
      Position p = c.getPosition();
      if (p.getCol() < 0 || p.getCol() > WIDTH - 1 || p.getRow() > HEIGHT - 1)
      {
        return true;
      }

      // row, column
      if (p.getRow() >= 0 && grid[p.getRow()][p.getCol()] != null)
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Delete the blocks at the indicated positions and shift blocks above them
   * down. Only blocks lying within a column above a deleted block are shifted
   * down.
   * 
   * @param cellsToCollapse
   *          list of locations of cells to collapse. The list may contain
   *          duplicates.
   */
  private void collapseCells(List<Position> cellsToCollapse)
  {
    for (Position p : cellsToCollapse)
    {
      grid[p.getRow()][p.getCol()].setMarked(true);
    }
    for (int col = 0; col < WIDTH; ++col)
    {
      int start = HEIGHT - 1;
      boolean done = false;
      while (!done)
      {
        // go up the column and find the first marked block
        while (start > 0 && (grid[start][col] == null || !grid[start][col].isMarked()))
        {
          --start;
        }
        if (grid[start][col] != null && grid[start][col].isMarked())
        {
          // go past all the marked cells, setting them null
          int j = start;
          while (j >= 0 && grid[j][col] != null && grid[j][col].isMarked())
          {
            // System.out.println("nulling " + j + ", " + col);
            grid[j][col] = null;
            --j;
          }
          if (j >= 0)
          {
            // found something non-marked, so
            // shift down everything at j and above
            int shift = start - j;
            for (int k = j; k >= 0; --k)
            {
              grid[k + shift][col] = grid[k][col];
            }
          }
          else
          {
            done = true;
          }

        }
        else
        {
          // no marked cells
          done = true;
        }

      }
    }
  }

}
