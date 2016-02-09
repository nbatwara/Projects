
package util;

import hw2.ScoreCalculator;
import hw2.WordPair;
import hw2.WordScrambler;
import hw2.Words;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Text-based user interface for the scrambled word game.
 */
public class TextUI
{
  /**
   * Interval in milliseconds for reporting the score.
   */
  private static final int TIMER_INTERVAL = 1000;

  /**
   * Format string for printing scores.
   */
  private static final String SCORE_FORMAT_STRING = "Time: %-10d Score: %-10d Total Score: %-10d";

  /**
   * Scanner to read from the console.
   */
  private Scanner scanner = new Scanner(System.in);

  /**
   * Source of randomness.
   */
  private Random rand;

  /**
   * Permutation generator.
   */
  private PermutationGenerator pg;

  /**
   * Score calculator.
   */
  private ScoreCalculator calc;

  /**
   * Word list.
   */
  private Words wordList;

  /**
   * Total score.
   */
  private int totalScore;

  /**
   * Entry point.
   */
  public static void main(String[] args) throws FileNotFoundException
  {
    new TextUI("words2.txt").go();
  }

  /**
   * Constructs an instance of the UI
   * @param filename
   *   file containing list of words
   * @throws FileNotFoundException
   *   if the file cannot be opened
   */
  public TextUI(String filename) throws FileNotFoundException
  {
    // use a seed to make the results reproducible
    // rand = new Random(137);
    rand = new Random();
    pg = new PermutationGenerator(rand);

    // this may throw FileNotFoundException
    wordList = new Words(filename);

    // scoring based on:
    // 10 seconds per letter
    // 5 second penalty for a letter hint
    // 1/10 second penalty for rescrambling
    // 1 second penalty for submitting an incorrect guess
    calc = new ScoreCalculator(10000, 5000, 100, 1000);
  }

  /**
   * Starts the UI.
   * 
   * @throws FileNotFoundException
   */
  public void go() throws FileNotFoundException
  {
    long startTime = System.currentTimeMillis();
    WordPair game = initializeWord();
    calc.start(game.getRealWord().length());
    boolean restart = false;

    // main loop gets a selection from user and dispatches it
    boolean over = false;
    while (!over)
    {
      printStatus(game, calc, startTime);
      menu();
      System.out.print("Your choice: ");
      String response = scanner.nextLine();
      response = response.trim().toLowerCase();

      if (response.startsWith("r"))
      {
        // move right
        doMove(game, false);
      }
      else if (response.startsWith("l"))
      {
        // move left
        doMove(game, true);
      }
      else if (response.startsWith("a"))
      {
        // letter hint
        game.doLetterHint();
        calc.applyHintPenalty();
      }
      else if (response.startsWith("b"))
      {
        // rescramble
        game.rescramble(pg);
        calc.applyRescramblePenalty();
      }
      else if (response.startsWith("c"))
      {
        // check whether scrambled word is correct
        restart = doCheckSolution(game, false, startTime);
      }
      else if (response.startsWith("d"))
      {
        // give up
        System.out.println("The word was " + game.getRealWord());
        restart = true;
      }
      else if (response.startsWith("e"))
      {
        // check a word entered by player
        restart = doCheckSolution(game, true, startTime);
      }
      else if (response.startsWith("q"))
      {
        // quit
        System.out.println("Bye!");
        over = true;
      }
      else
      {
        System.out.println("Please enter a, b, c, d, e, l, r or q");
      }

      if (restart)
      {
        // get a new word and reset the score calculator
        game = initializeWord();
        calc.start(game.getRealWord().length());
        startTime = System.currentTimeMillis();
        restart = false;
      }

    }
  }

  /**
   * Displays the score and the current scrambled word.
   * @param wordPair
   *   WordPair for the current round
   * @param calc
   *   score calculator for this game
   * @param startTime
   *   starting time for the current round
   */
  private void printStatus(WordPair wordPair, ScoreCalculator calc, long startTime)
  {
    System.out.println();
    int elapsed = getApproximateTime(startTime);
    String scores = String.format(SCORE_FORMAT_STRING, elapsed, calc.getPossibleScore(elapsed), totalScore);
    System.out.println(scores);
    System.out.println();
    System.out.println(wordPair.getScrambledWord());
    System.out.println();
  }

  /**
   * Displays a menu of choices for player.
   */
  private void menu()
  {
    System.out.println(" a) Letter hint");
    System.out.println(" b) Rescramble");
    System.out.println(" c) Submit");
    System.out.println(" d) Give up");
    System.out.println(" e) Enter word");
    System.out.println(" l) Move left");
    System.out.println(" r) Move right");
    System.out.println(" q) Quit");
  }

  /**
   * Performs a move operation after getting the index and distance from 
   * the player.
   * @param wordPair
   *   WordPair for the current round
   * @param left
   *   true if the player is moving a character to the left, false if
   *   moving to the right
   */
  private void doMove(WordPair wordPair, boolean left)
  {
    System.out.print("Enter index followed by how far: ");
    String line = scanner.nextLine();
    Scanner temp = new Scanner(line);
    int index = -1;
    int howFar = -1;
    {
      if (temp.hasNextInt())
      {
        index = temp.nextInt();
      }
      if (temp.hasNextInt())
      {
        howFar = temp.nextInt();
      }
    }
    if (index >= 0 && howFar >= 0)
    {
      if (left)
      {
        wordPair.moveLeft(index, howFar);
      }
      else
      {
        wordPair.moveRight(index, howFar);
      }
    }
    else
    {
      System.out.println("Please enter two numbers for the index and how far");
    }
  }

  /**
   * Checks the solution using either the current scrambled word or a word
   * entered in by the player.
   * @param game
   *   word pair for the current round
   * @param doEnter
   *   true if the player will enter the solution at the console, false to
   *   use the current secret word as solution
   * @param startTime
   *   starting time for this round
   * @return
   *   true if the puzzle is solved, false otherwise
   */
  private boolean doCheckSolution(WordPair game, boolean doEnter, long startTime)
  {
    boolean won;

    if (doEnter)
    {
      System.out.println("Enter your guess: ");
      String guess = scanner.nextLine();
      won = game.checkSolution(guess);
    }
    else
    {
      won = game.checkSolution();
    }

    if (won)
    {
      System.out.println("That's it!");
      int score = calc.getPossibleScore(getApproximateTime(startTime));
      System.out.println("Your score " + score);
      totalScore += score;
    }
    else
    {
      System.out.println("Nope, that's not it.");
      calc.applyIncorrectGuessPenalty();
    }
    return won;
  }

  /**
   * Returns a new WordPair.
   * @return
   *   WordPair for a new round of the game
   * @throws FileNotFoundException
   *   if attempting to get a new word requires opening the word file and fails
   */
  private WordPair initializeWord() throws FileNotFoundException
  {
    String word = wordList.getWord(rand);
    String scrambledWord = WordScrambler.scramble(word, pg);
    return new WordPair(word, scrambledWord);
  }

  /**
   * Gets the number of milliseconds the player has used in this
   * round of the game, rounded to the nearest multiple of TIMER_INTERVAL.
   * @param startTime
   *   starting time for the current round of the game
   * @return
   *   elapsed time for the current round so far, rounded to the nearest multiple
   *   of TIMER_INTERVAL
   */
  private int getApproximateTime(long startTime)
  {
    int elapsed = (int) (System.currentTimeMillis() - startTime);
    
    // round to nearest multiple of interval
    int numIntervals = elapsed / TIMER_INTERVAL;
    int left = elapsed % TIMER_INTERVAL;
    if (left >= TIMER_INTERVAL / 2)
    {
      numIntervals += 1;
    }
    return numIntervals * TIMER_INTERVAL;
  }
}
