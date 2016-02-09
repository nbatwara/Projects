package hw3;

import hw3.api.Position;
import hw3.example.SampleGenerator;
import hw3.impl.Block;
import hw3.impl.GridCell;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class hw3Test {
	 public static void main(String[] args)
	 {
	 Color[] colors = new Color[2];
	 colors[0] = Color.RED;
	 colors[1] = Color.BLUE;
	 DiagonalPiece d = new DiagonalPiece(new Position(2, 5), colors);
	 Block[] blocks = d.getBlocks();
	 System.out.println(Arrays.toString(blocks));
	 System.out.println("Expected [red at (2, 5), blue at (3, 6)]");
	 d.shiftDown();
	 System.out.println(d.getPosition());
	 System.out.println("Expected (3, 5)");
	 blocks = d.getBlocks();
	 System.out.println(Arrays.toString(blocks));
	 System.out.println("Expected [red at (3, 5), blue at (4, 6)]");
	 d.transform();
	 System.out.println(d.getPosition());
	 System.out.println("Expected (3, 5)");
	 blocks = d.getBlocks();
	 System.out.println(Arrays.toString(blocks));
	 System.out.println("Expected [red at (3, 6), blue at (4, 5)]");
	 BlockGame game = new BlockGame(new SampleGenerator());
	 GridCell[][] grid = game.getGrid(); // reference to the actual game array
	 grid[2][3] = new GridCell(Color.RED);
	 grid[3][3] = new GridCell(Color.BLUE);
	 grid[2][4] = new GridCell(Color.RED);
	 grid[3][4] = new GridCell(Color.RED);
	 ArrayList<Position> cells = game.determineCellsToCollapse();
	 System.out.println(cells);
	 System.out.println("Expected [(2, 4), (3, 4), (2, 3)] in some order");
	 }

}
