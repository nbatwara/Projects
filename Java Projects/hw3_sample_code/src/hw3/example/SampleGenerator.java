package hw3.example;

import hw3.api.IPolyomino;
import hw3.api.IPolyominoGenerator;
import hw3.api.Position;

public class SampleGenerator implements IPolyominoGenerator
{

  @Override
  public IPolyomino getNext()
  {
    // TODO Auto-generated method stub
    return new SamplePiece(new Position(-1, 5));
  }

}
