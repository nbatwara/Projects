package hw3;

import java.awt.Color;
import java.util.Random;

import hw3.api.IPolyomino;
import hw3.api.IPolyominoGenerator;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;

public class BasicGenerator implements IPolyominoGenerator {
	/**
	 * variable for a random object from random class
	 */
	private Random rand;
	/**
	 * variable for color array 
	 */
	private Color[] color;

	public BasicGenerator(Random rand1){
		rand = rand1;
		color = new Color[AbstractBlockGame.COLORS.length];

	}
	/**
	 * Returns a new IPolyomino instance according to generator's
	 * strategy.
	 * @return a new IPolyomino
	 */
	public IPolyomino getNext(){
	//	Random prob = new Random();	
		int piece = rand.nextInt(99);
		if( piece < 60){
			for(int i = 0; i < 3; i ++){
				color[i] = (AbstractBlockGame.COLORS[rand.nextInt(AbstractBlockGame.COLORS.length)]);		}
			return new IPiece(new Position(-2,5), color);
		}
		else if (piece >= 60 && piece < 80){
			for (int i =0; i <2 ; i ++){
				color[i] = (AbstractBlockGame.COLORS[rand.nextInt(AbstractBlockGame.COLORS.length)]);
			}
			return new DiagonalPiece(new Position(-1,5), color);
		}
		else {
			for (int i =0; i <4 ; i ++){
				color[i] = (AbstractBlockGame.COLORS[rand.nextInt(AbstractBlockGame.COLORS.length)]);
			}
			return new LPiece(new Position(-2,5), color);

		}
	}
}
