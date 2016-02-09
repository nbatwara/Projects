package hw3.api;

import java.awt.Color;

/**
 * Interface representing a graphical element that can represent
 * a block.  Implementations must provide a color, but the user
 * interface may choose its own rendering of the block.
 */
public interface IGameIcon
{
  /**
   * Return the color for this game element.
   * @return
   *    color for this game element
   */
  Color getColorHint();
}
