
package hw3;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.example.SamplePiece;
import hw3.impl.Block;

public abstract class AbstractPiece implements IPolyomino
{
	/**
	 * Instance variable for Postion type object
	 */
	private Position pos;
	/**
	 * Instance variable for block object array
	 */
	private Block[] blocks;
	// TODO: add any constructors, instance variables, or methods that you need.
	public  AbstractPiece(Position p){
		initializePosition(p);
	}
	public Position getPosition(){
		return pos;
	}

	/**
	 * Sets the position of the upper left corner of the bounding square
	 * for this polyomino.
	 * @param position
	 *   new position for this polyomino
	 */
	public  void initializePosition(Position givenPosition){
		pos=givenPosition;
	}

	/**
	 * Returns an array of Block objects containing the absolute positions
	 * of the blocks in this polyomino
	 * 
	 * @return 
	 *   the blocks in this polyomino with their absolute positions
	 */
	public  Block[] getBlocks(){
		Block[] block1 = new Block[blocks.length];
		for(int i = 0; i < block1.length; i++){
			Position pos1 = new Position(blocks[i].getPosition());
			pos1.setCol(pos1.getCol()+pos.getCol());
			pos1.setRow(pos1.getRow()+pos.getRow());
			block1[i] = new Block(blocks[i].getColorHint(), pos1);
		}
		return block1;
	}

	/**
	 * Sets the blocks to be occupied by this polyomino.  The positions of the given
	 * blocks are relative to the upper-left corner of the bounding square. 
	 * @param
	 *    the blocks that are part of this polyomino with positions relative to
	 *    upper left corner of bounding square
	 */
	public  void initializeBlocks(Block[] givenBlocks){
		blocks = new Block[givenBlocks.length];
		for(int i=0; i<givenBlocks.length; i++)
			blocks[i] = new Block(givenBlocks[i]);
	}



	public  void shiftDown() throws IllegalStateException{
		pos.setRow(pos.getRow() + 1);
	}

	/**
	 * Shifts the position of this polyomino left (decreasing the x-coordinate) by
	 * one cell. No bounds checking is done.
	 */
	public void shiftLeft(){
		pos.setCol(pos.getCol()-1);
	}

	/**
	 * Shifts the position of this polyomino right (increasing the x-coordinate)
	 * by one cell. No bounds checking is done.
	 */
	public void shiftRight(){
		pos.setCol(pos.getCol()+1);
	}


	/**
	 * Returns a deep copy of this object.
	 * 
	 * @return 
	 *   a deep copy of this object.
	 * @throws CloneNotSupportedException 
	 */
	public IPolyomino clone() 
	{
		AbstractPiece cloned = null;
		try
		{
			cloned = (AbstractPiece) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			// can't happen
		}

		// TODO: 
		// Since clone() only gives us a "shallow" copy that shares  
		// references with the original, we have to make copies for all 
		// instance variables that are references (such as the Position 
		// and Block[] attributes)
		cloned.pos = new Position(pos);
		cloned.blocks = new Block[blocks.length];
		for(int i = 0; i <blocks.length; i++)
			cloned.blocks[i] = new Block(blocks[i]);
		return cloned;
	}

	/**
	 * Transforms this polyomino without altering its position according to the
	 * rules of the game to be implemented. Typical operations are rotation and
	 * reflection within the bounding square. No bounds checking is done.
	 */
	public  void transform(){}
	
	/**
	 * Cycles the blocks forward within the occupied cells of this polyomino,
	 * based on the top-to-bottom ordering of the cells in the original position.
	 * The last block should wrap around to the first cell. (This method has no
	 * apparent effect when all blocks are the same color.)
	 */
	public void cycle() {
		
	}
}