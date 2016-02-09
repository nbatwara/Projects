
package hw3.ui;

// Add these imports after you implement these two classes
//import hw3.BasicGenerator;
//import hw3.BlockGame;

import hw3.api.IGame;
import hw3.impl.BasicPlayLevel;
import hw3.example.SampleGame;
import hw3.example.SampleGenerator;
import hw3.BlockGame;
import hw3.BasicGenerator;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main class for a GUI for a Tetris game sets up a GamePanel instance in a
 * frame.
 */
public class GameMain
{
  /**
   * Cell size in pixels.
   */
  public static final int SIZE = 25;

  /**
   * Helper method for instantiating the components. This method should be
   * executed in the context of the Swing event thread only.
   */
  private static void create()
  {
    // EDIT BELOW to create BlockGame instead of SampleGame
    
    // Use BasicGenerator and initialize the game with the checkerboard pattern
    Random rand = new Random();
    IGame game = new BlockGame(new BasicGenerator(rand), rand);

    // Use the SampleGenerator, start with a blank grid
    //IGame game = new BlockGame(new SampleGenerator());
    
    // Try out the sample game only
    //IGame game = new SampleGame(new SampleGenerator());
    
    BasicPlayLevel level = new BasicPlayLevel();
    GamePanel panel = new GamePanel(game, level);
    JFrame frame = new JFrame();
    frame.getContentPane().add(panel);

    // give it a nonzero size
    panel.setPreferredSize(new Dimension(game.getWidth() * GameMain.SIZE, game.getHeight() * GameMain.SIZE));
    frame.pack();

    // we want to shut down the application if the
    // "close" button is pressed on the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // be sure key events get to the panel
    panel.requestFocus();

    // rock and roll...
    frame.setVisible(true);
  }

  /**
   * Entry point. Main thread passed control immediately to the Swing event
   * thread.
   * 
   * @param args
   *          not used
   */
  public static void main(String[] args)
  {
    Runnable r = new Runnable()
    {
      public void run()
      {
        create();
      }
    };
    SwingUtilities.invokeLater(r);
  }
}
