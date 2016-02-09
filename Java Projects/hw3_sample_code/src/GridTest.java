
// Uncomment this line after you implement the BlockGame class
//import hw3.BlockGame;

import hw3.api.Position;
import hw3.example.SampleGenerator;
import hw3.impl.AbstractBlockGame;
import hw3.impl.GridCell;

import java.awt.Color;
import java.util.ArrayList;

public class GridTest
{
  public static void main(String[] args)
  {
    AbstractBlockGame game = null;
    
    // Uncomment this line after you implement the BlockGame class
    //game = new BlockGame(new SampleGenerator());
    
    GridCell[][] grid = game.getGrid();
    grid[2][3] = new GridCell(Color.RED);
    grid[3][3] = new GridCell(Color.BLUE);  
    grid[2][4] = new GridCell(Color.RED);
    grid[3][4] = new GridCell(Color.RED);
    ArrayList<Position> cells = game.determineCellsToCollapse();
    System.out.println(cells);
    System.out.println();
    
    printGrid(grid);
  }
  
  /**
   * Displays a 2d array of GridCell in a readable form.
   * @param grid
   */
  public static void printGrid(GridCell[][] grid)
  {
    for (int row = 0; row < grid.length; row += 1)
    {
      for (int col = 0; col < grid[0].length; col += 1)
      {
        String s = findShortColorName(grid[row][col]);
        System.out.print(s + " ");
      }
      System.out.println();
    }
  }
  
  /**
   * Returns a one-letter abbreviation for the colors listed in
   * AbstractBlockGame.
   * @param c
   * @return
   */
  public static String findShortColorName(GridCell c)
  {
    if (c == null) return "-";
    Color color = c.getColorHint();
    String colorName = "?";     
    
    // look for a color name that is used in abstract block game
    Color[] knownColors = AbstractBlockGame.COLORS;
    String[] knownColorNames = AbstractBlockGame.COLOR_NAMES;
    
    for (int i = 0; i < knownColors.length; i += 1)
    {
      if (knownColors[i].equals(color))
      {
        colorName = knownColorNames[i];
        break;
      }
    }
    return colorName;
  }
}
