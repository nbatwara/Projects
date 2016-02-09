package hw3;

import hw3.api.GameStatus;
import hw3.api.IPolyominoGenerator;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;
import hw3.impl.Block;
import hw3.impl.GridCell;

import java.util.ArrayList;
import java.util.Random;

public class BlockGame extends AbstractBlockGame {
	/**
	 * variable for calculating score
	 */
	private int score;
	/**
	 * variable for IPolyomino object generator
	 */
	private IPolyominoGenerator generator;
	/**
	 * variable for creating a 2d array for obtaining a grid
	 */
	private GridCell[][] grid;
	public BlockGame(IPolyominoGenerator gen){
		super(gen);
		grid = super.getGrid();
		generator = gen;

	}
	public BlockGame(IPolyominoGenerator gen, Random rand){
		super(gen);

		grid = super.getGrid();

		for ( int row = getHeight()-8; row < getHeight(); row ++){

			for( int col = 0; col < getWidth(); col ++){
				if ( row % 2 == 0 && col % 2 != 0){
					grid[row][col] = new GridCell(AbstractBlockGame.COLORS[rand.nextInt(AbstractBlockGame.COLORS.length)]); 
				}
				if ( row % 2 != 0 && col % 2 == 0){
					grid[row][col] = new GridCell(AbstractBlockGame.COLORS[rand.nextInt(AbstractBlockGame.COLORS.length)]); 
				}
			}
		}
	}


	public java.util.ArrayList<Position> determineCellsToCollapse(){
		ArrayList<Position> cells = new ArrayList<Position>();
		//  GridCell[][] grid = getGrid(); 
		int count =0;
		boolean u, d, r, l;
		for (int row = 0; row < getHeight(); row += 1)
		{
			if (isRowComplete(row))
			{
				for (int col = 0; col < getWidth(); col += 1)
				{
					u = false; d = false; r = false; l = false; count = 0;
					if (grid[row][col] != null && grid[row+1][col] != null && (row+1) < getHeight() && grid[row][col].matches(grid[row +1][col]) ){
						//  cells.add(new Position(row, col));
						count += 1;
						u = true;
						
					}
					if(grid[row][col] != null && grid[row][col+1] != null && (col+1) < getWidth() && grid[row][col].matches(grid[row][col +1])){
						count += 1;
						d = true;
						
					}
					if (grid[row][col] != null && grid[row - 1][col] != null &&(row-1) >=0 && grid[row][col].matches(grid[row -1][col])){
						count += 1;
						r = true;
					
					}
					if (grid[row][col] != null && grid[row-1][col-1] != null && (col-1) >=0 && grid[row][col].matches(grid[row][col-1])){
						count +=1;	
						l = true;
						
					}
					if (count > 1){
						cells.add(new Position(row, col));
						if(u == true)
							cells.add(new Position(row+1,col));
						if(d == true)
							cells.add(new Position(row,col+1));
						if(r == true)
							cells.add(new Position(row-1,col));
						if(l == true)
							cells.add(new Position(row,col-1));
						score += 1;
					}
				}
			}
		}
		return cells;
	}

	public int determineScore(){
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


