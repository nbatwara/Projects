package gui;

import hw2.ScoreCalculator;
import hw2.WordPair;
import hw2.WordScrambler;
import hw2.Words;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import util.PermutationGenerator;

/**
 * User interface for ScrambledWordGame.  The UI uses a WordPair
 * instance to obtain the letters to draw, to obtain hints, and to check
 * the solutions.  A ScoreCalculator is used to keep a running score that
 * is updated on the UI as it decreases.  A WordScrambler is used to
 * obtain words and randomize them to start each round of the game.
 */
public class WordGameUI extends JPanel
{
  /**
   * Interval in milliseconds for refreshing the score.
   */
  private static final int TIMER_INTERVAL = 1000;

  /**
   * Format string for printing scores at top of window.
   */
  private static final String SCORE_FORMAT_STRING = "Time: %-10d Score: %-10d Total Score: %-10d";
  
  /**
   * Serial version number, not used.
   */
  private static final long serialVersionUID = 1L;
  
  // Swing components
  private JButton submitButton;
  private JButton hintButton;
  private JButton rescrambleButton;
  private JButton startButton;
  private JLabel scoreLabel;
  private Timer timer;
  
  /**
   * Panel where letters are drawn and can be moved with the mouse.
   */
  private WordCanvas wordPanel;
  
  /**
   * WordPair instance for current round.
   */
  private WordPair wordPair;

  /**
   * Score calculator for this UI.
   */
  private ScoreCalculator scorer;
  
  /**
   * Word generator for this UI.
   */
  private Words wordList;
  
  /**
   * Total score obtained since UI was started.
   */
  private int totalScore;
  
  /**
   * Flag indicating whether there is a round in progress.
   */
  private boolean roundOver;
  
  /**
   * Records start time of the round for scoring purposes.
   */
  private long startTime;
  
  /**
   * Random number generator used for selecting words with the Words object.
   */
  private Random rand;
  
  /**
   * Permutation generator used for scrambling words with the WordScrambler.
   */
  private PermutationGenerator pg;
  
  /**
   * Constructs a new instance of the UI main panel.
   * @param scorer
   *   score calculator
   * @param wordList
   *   Words object to use for generating words
   * @param rand
   *   random number generator for selecting words
   * @param pg
   *   permutation generator for scrambling words
   */
  public WordGameUI(ScoreCalculator scorer, Words wordList, Random rand, PermutationGenerator pg)
  {
    this.scorer = scorer;
    this.wordList = wordList;
    this.rand = rand;
    this.pg = pg;
    roundOver = true;
    
    // create swing controls
    // (Start button will be relabeled "Give up" when a round
    // is in progress.)
    submitButton = new JButton("Submit");
    hintButton = new JButton("Hint");
    rescrambleButton = new JButton("Rescramble");
    startButton = new JButton("Start");
    String labelText = String.format(SCORE_FORMAT_STRING, 0, 0, totalScore);
    scoreLabel = new JLabel(labelText);
 
    // create a timer that will fire every TIMER_INTERVAL ms
    // for updating the score
    timer = new Timer(TIMER_INTERVAL, new TimerCallback());

    // running score will be centered in top panel and use larger font
    scoreLabel.setFont(new Font("Serif", Font.PLAIN, 24));
    scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // panel for buttons and total score
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(startButton);
    buttonPanel.add(hintButton);
    buttonPanel.add(rescrambleButton);
    buttonPanel.add(submitButton);

    // add callbacks for buttons
    ActionListener submitHandler = new SubmitButtonHandler();
    ActionListener hintHandler = new HintButtonHandler();    
    ActionListener rescrambleHandler = new RescrambleButtonHandler();
    ActionListener startHandler = new StartButtonHandler();
    
    submitButton.addActionListener(submitHandler);
    hintButton.addActionListener(hintHandler);
    rescrambleButton.addActionListener(rescrambleHandler);
    startButton.addActionListener(startHandler);

    // initially only the start button is enabled
    hintButton.setEnabled(false);
    rescrambleButton.setEnabled(false);
    submitButton.setEnabled(false);
    
    // custom panel for drawing the letters
    wordPanel = new WordCanvas();
    
    // score on top, buttons in middle, letters on bottom
    this.setLayout(new BorderLayout());
    this.add(buttonPanel, BorderLayout.CENTER);
    this.add(scoreLabel, BorderLayout.NORTH);
    this.add(wordPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Helper method called when current round ends, either by
   * successfully solving the word or by giving up.
   * @param solved
   *   true if the puzzle was solved, false otherwise
   */
  private void endRound(boolean solved)
  {
    roundOver = true;
    timer.stop();
    startButton.setText("Start");
    hintButton.setEnabled(false);
    rescrambleButton.setEnabled(false);
    submitButton.setEnabled(false);
    wordPanel.over(solved);

    int elapsedTime = (int) (System.currentTimeMillis() - startTime);
    int currentScore = 0;
    if (solved)
    {
      currentScore = scorer.getPossibleScore(elapsedTime);
    }

    totalScore += currentScore;
    String labelText = String.format(SCORE_FORMAT_STRING, elapsedTime, currentScore, totalScore);
    scoreLabel.setText(labelText);
    repaint();
  }
  
  /**
   * Helper method called to start a new round.
   */
  private void startRound()
  {
    try
    {
      // Depending on how the WordScrambler is implemented,
      // this may throw FileNotFoundException...
      String word = wordList.getWord(rand); 

      String hidden = WordScrambler.scramble(word, pg);
      wordPair = new WordPair(word, hidden);

      if (wordPair.isSolutionPossible())
      {
        roundOver = false;
        startButton.setText("Give up");
        hintButton.setEnabled(true);
        rescrambleButton.setEnabled(true);
        submitButton.setEnabled(true);
        scorer.start(word.length());
        int initialScore = scorer.getPossibleScore(0);
        String labelText = String.format(SCORE_FORMAT_STRING, 0, initialScore, totalScore);
        scoreLabel.setText(labelText);
        wordPanel.startRound(wordPair);
        startTime = System.currentTimeMillis();
        timer.start(); 
      }
      else
      {
        String msg = "Error: isSolutionPossible returns false for '" + word + "' and '" + hidden + "'";
        JOptionPane.showMessageDialog(this.getParent(), msg);        
      }
    }
    catch (FileNotFoundException e)  // possible FileNotFoundException
    {
      String msg = "Unable to open word file: " + e.getMessage();
      JOptionPane.showMessageDialog(this.getParent(), msg);        
    }
    catch (Exception e)  // possible FileNotFoundException
    {
      String msg = "Exception: " + e;
      JOptionPane.showMessageDialog(this.getParent(), msg);
      System.exit(1);
    }

  }
  
  /**
   * Callback for the start (or give up) button.
   */
  private class StartButtonHandler implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      // This button is labeled "Start" if a round is not in progress,
      // and "Give up" if a round is in progress.
      if (roundOver)
      {
        startRound();
      }
      else
      {
        endRound(false);
      }
      repaint();
    }  
  }
  
  /**
   * Callback for submit button.
   */
  private class SubmitButtonHandler implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      // if the solution is correct, end the round and add the score,
      // otherwise impose penalty for incorrect guess
      if (wordPair.checkSolution())
      {
        endRound(true);
      }
      else
      {
        scorer.applyIncorrectGuessPenalty();
      }
    }    
  }
  
  /**
   * Callback for hint button.
   */
  private class HintButtonHandler implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      wordPair.doLetterHint();
      scorer.applyHintPenalty();
      wordPanel.reinitialize();
    }   
  }

  /**
   * Callback for rescramble button.
   */
  private class RescrambleButtonHandler implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      wordPair.rescramble(pg);
      if (!wordPair.isSolutionPossible())
      {
        String word = wordPair.getRealWord();
        String hidden = wordPair.getScrambledWord();
        String msg = "Error: isSolutionPossible returns false for '" + word + "' and '" + hidden + "'";
        JOptionPane.showMessageDialog(WordGameUI.this.getParent(), msg); 
        
        System.exit(1);
      }

      scorer.applyRescramblePenalty();
      wordPanel.reinitialize();
    }   
  }

  /**
   * Timer callback updates the score each time timer fires.
   */
  private class TimerCallback implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      int elapsed = (int) (System.currentTimeMillis() - startTime);
      
      // round to nearest multiple of interval
      int numIntervals = elapsed / TIMER_INTERVAL;
      int left = elapsed % TIMER_INTERVAL;
      if (left >= TIMER_INTERVAL / 2)
      {
        numIntervals += 1;
      }
      elapsed = numIntervals * TIMER_INTERVAL;
      
      int currentScore = scorer.getPossibleScore(elapsed);
      String labelText = String.format(SCORE_FORMAT_STRING, elapsed, currentScore, totalScore);
      
      scoreLabel.setText(labelText);
      repaint();
    }
    
  }
}
