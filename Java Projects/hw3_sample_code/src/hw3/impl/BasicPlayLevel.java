
package hw3.impl;

import hw3.api.IPlayLevel;

/**
 * Minimal implementation of IPlayLevel.  Both the falling speed and
 * the fast drop speed are increased as the score goes up.
 */
public class BasicPlayLevel implements IPlayLevel
{
  
  /**
   * Number of points to go to next "level".
   */
  private int[] scores = {50, 100, 150, 200};
  
  /**
   * Falling speed for each level.
   */
  private int[] speeds = {1000, 800, 600, 400};
  
  /**
   * Fast-drop speed for each level.
   */
  private int[] fastDropSpeeds = {80, 60, 40, 20};
  

  @Override
  public int speed(int score)
  {
    int i = 0;
    while (i < scores.length - 1 && score >= scores[i])
    {
      ++i;
    }
    return speeds[i];
  }

  
  @Override
  public int fastDropSpeed(int score)
  {
    int i = 0;
    while (i < scores.length - 1 && score >= scores[i])
    {
      ++i;
    }
    return fastDropSpeeds[i];
  }

}
