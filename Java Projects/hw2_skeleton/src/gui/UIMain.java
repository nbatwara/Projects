package gui;

import hw2.ScoreCalculator;
import hw2.Words;

import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.PermutationGenerator;

/**
 * Main class for starting an instance of <code>WordGameUI</code>.
 */
public class UIMain
{
  /**
   * Entry point.
   */
  public static void main(String[] args)
  {
    // Edit here to use a different word file
    final String filename = "words2.txt";
    
    // Edit here to change the seed for the WordScrambler's
    // random number generator.  For unpredictable behavior,
    // use the system time as a seed:
    final int seed = (int) System.currentTimeMillis();
    //final int seed = 42;
     
    // Boilerplate code for safely starting up a Swing application
    // on the event-handling thread.
    Runnable r = new Runnable()
    {
      public void run()
      {
        createAndShow(filename, seed);
      }
    };
    SwingUtilities.invokeLater(r);
  }

  /**
   * Helper method for creating and starting the UI.
   * @param filename
   *   filename for word list
   * @param seed
   *   seed for random number generator in the WordScrambler
   */
  private static void createAndShow(String filename, int seed)
  {
    // the main window for this application
    JFrame frame = new JFrame("Com S 227 Word Scramble");

    try
    {
      // this may throw FileNotFoundException
      Words wordList = new Words(filename);   
      
      // scoring based on 
      //    5 seconds per letter
      //    5 second penalty for a letter hint
      //    1/10 second penalty for rescrambling
      //    1-second penalty for submitting an incorrect guess
      ScoreCalculator calc = new ScoreCalculator(5000, 5000, 100, 1000);
      Random rand = new Random(seed);
      PermutationGenerator pg = new PermutationGenerator(rand);
      
      // construct the main game panel and add it to the frame
      JPanel mainPanel = new WordGameUI(calc, wordList, rand, pg);
      frame.getContentPane().add(mainPanel);

      // size the frame based on the preferred size of the mainPanel
      frame.pack();

      // make sure it closes when you click the close button on the window
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // rock and roll...
      frame.setVisible(true);
    }
    catch (FileNotFoundException e)
    {
      JOptionPane.showMessageDialog(frame, "Unable to open word file: " + e.getMessage());
      frame.dispose();
    }

  }
  
}
