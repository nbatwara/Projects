/**
 * 
 */
package hw3;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.Block;
import java.awt.Color;

/**
 * @author Neh Batwara
 *
 */
public class IPiece extends AbstractPiece implements IPolyomino {
	/**
	 * Instance variable for block object array
	 */
	private Block[] block;
	/**
	 * Instance variable for Postion type object
	 */
	private Position pos;

	public IPiece(Position pos, Color[] colors){
		super(pos);
		block = new Block[3];
		block[0] = new Block(colors[0], new Position(0,0));
		block[1] =  new Block(colors[1], new Position(1,0));
		block[2] =  new Block(colors[2], new Position(2,0));
		super.initializeBlocks(block);
	}

	/**
	 * Transforms this polyomino without altering its position according to the
	 * rules of the game to be implemented. Typical operations are rotation and
	 * reflection within the bounding square. No bounds checking is done.
	 */
	@Override
	public  void transform(){
		// do nothing
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
		//		if (block[0].getColorHint() != block[1].getColorHint()){
		temp = block[0].getColorHint();
		block[0].setIcon(block[1].getColorHint());
		block[1].setIcon(block[2].getColorHint());
		block[2].setIcon(temp);
		super.initializeBlocks(block);
		//		}
	}

}
