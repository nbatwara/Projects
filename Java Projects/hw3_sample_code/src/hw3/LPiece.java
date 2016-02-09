/**
 * 
 */
package hw3;

import java.awt.Color;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.Block;

/**
 * @author Neh Batwara
 *
 */
public class LPiece extends AbstractPiece implements IPolyomino{
	/**
	 * Instance variable for block object array
	 */
	private Block[] block;
	/**
	 * Instance variable for Postion type object
	 */
	private Position pos;
	public LPiece(Position pos, Color[] colors){
		super(pos);
		block = new Block[4];
		block[0] = new Block(colors[0], new Position(0,0));
		block[1] =  new Block(colors[1], new Position(0,1));
		block[2] =  new Block(colors[2], new Position(1,1));
		block[3] =  new Block(colors[3], new Position(2,1));
		super.initializeBlocks(block);
	}

	/**
	 * Transforms this polyomino without altering its position according to the
	 * rules of the game to be implemented. Typical operations are rotation and
	 * reflection within the bounding square. No bounds checking is done.
	 */
	@Override
	public  void transform(){
		super.initializeBlocks(block);
		if(block[0].getPosition().getCol()< block[1].getPosition().getCol())
			block[0].getPosition().setCol(block[0].getPosition().getCol()+2);
		else
			block[0].getPosition().setCol(block[0].getPosition().getCol()-2);
		//super.initializeBlocks(block);
	}

	/**
	 * Cycles the blocks forward within the occupied cells of this polyomino,
	 * based on the top-to-bottom ordering of the cells in the original position.
	 * The last block should wrap around to the first cell. (This method has no
	 * apparent effect when all blocks are the same color.)
	 */
	@Override
	public void cycle() {
		
		Color temp;
		temp = block[0].getColorHint();
		block[0].setIcon(block[1].getColorHint());
		block[1].setIcon(block[2].getColorHint());
		block[2].setIcon(block[3].getColorHint());
		block[3].setIcon(temp);
		super.initializeBlocks(block);

	}

}