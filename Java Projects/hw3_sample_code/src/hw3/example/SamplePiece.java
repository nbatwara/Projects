package hw3.example;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;
import hw3.impl.Block;

public class SamplePiece implements IPolyomino
{
  private Position pos;
  private Block[] blocks;
  
  public SamplePiece(Position givenPosition)
  {
    pos = givenPosition;
    blocks = new Block[2];
    
    // we'll store positions in the blocks that are relative to the
    // upper left corner
    blocks[0] = new Block(AbstractBlockGame.COLORS[0], new Position(0, 0));
    blocks[1] = new Block(AbstractBlockGame.COLORS[0], new Position(1, 0));
  }
  
  @Override
  public Position getPosition()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void initializePosition(Position givenPosition)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Block[] getBlocks()
  {
    Block[] toReturn = new Block[2];
    
    // we need to return absolute positions in the grid, so add to 
    // row and column of upper left corner
    Position p = new Position(blocks[0].getPosition());
    p.setRow(p.getRow() + pos.getRow());
    p.setCol(p.getCol() + pos.getCol());    
    toReturn[0] = new Block(blocks[0].getColorHint(), p);
    
    p = new Position(blocks[1].getPosition());
    p.setRow(p.getRow() + pos.getRow());
    p.setCol(p.getCol() + pos.getCol());    
    toReturn[1] = new Block(blocks[1].getColorHint(), p);
    
    return toReturn;
  }

  @Override
  public void initializeBlocks(Block[] givenBlocks)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void shiftDown()
  {
    pos.setRow(pos.getRow() + 1);
  }

  @Override
  public void shiftLeft()
  {
    // TODO Auto-generated method stub
  }

  @Override
  public void shiftRight()
  {
    // TODO Auto-generated method stub
  }

  @Override
  public void transform()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void cycle()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public IPolyomino clone()
  {
    // The built-in cloning mechanism of the Java runtime will
    // create an object of the correct runtime type.
    SamplePiece cloned = null;
    try
    {
      cloned = (SamplePiece) super.clone();
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
    cloned.blocks = new Block[2];
    cloned.blocks[0] = new Block(blocks[0]);
    cloned.blocks[1] = new Block(blocks[1]);
    
    return cloned;
  }
  
}
