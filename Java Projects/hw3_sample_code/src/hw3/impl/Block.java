
package hw3.impl;

import hw3.api.IGameIcon;
import hw3.api.Position;

import java.awt.Color;


/**
 * A Block represents a color and a location in the grid.
 */
public class Block implements IGameIcon
{
  /**
   * The Color represented by this block.
   */
  private Color color;

  /**
   * The location of this Block.
   */
  private Position position;

  /**
   * Constructs a Block from the given color and position.
   * 
   * @param color
   * @param position
   */
  public Block(Color color, Position position)
  {
    this.color = color;
    this.position = new Position(position);
  }

  /**
   * Copy constructor creates a copy of the given Block.
   * 
   * @param existing
   *          the given Block
   */
  public Block(Block existing)
  {
    this.color = existing.color;
    this.position = new Position(existing.position);
  }

  /**
   * Returns the location of this block.
   * 
   * @return the location of this block
   */
  public Position getPosition()
  {
    return position;
  }

  /**
   * Returns the color associated with this block.
   * 
   * @return the color associated with this block
   */
  @Override
  public Color getColorHint()
  {
    return color;
  }

  /**
   * Sets the color associated with this block.
   * 
   * @param givenColor
   *    the color for this block
   */
  public void setIcon(Color givenColor)
  {
    color = givenColor;
  }
  
  /**
   * Sets the position for this block.
   * 
   * @param givenPosition
   *   the position for this block
   */
  public void setPosition(Position givenPosition)
  {
    position = givenPosition;
  }
  
  @Override
  public String toString()
  {
    // default color representation
    String colorName = color.toString();  
    
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
    return "[" + colorName + ", " + position + "]";
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != Block.class)
    {
      return false;
    }
    Block other = (Block) obj;
    return this.color.equals(other.color) && this.position.equals(other.position);

  }
}
