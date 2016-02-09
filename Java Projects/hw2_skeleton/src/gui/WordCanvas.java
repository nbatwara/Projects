package gui;

import hw2.WordPair;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Custom instance of JPanel for drawing letters in a word as
 * a sequence of rectangles that can be moved with a mouse.  Whenever
 * a letter is moved in the UI, this component will call an instance of
 * WordPair to perform the indicated move and obtain the
 * resulting scrambled word.
 * 
 * @author smkautz
 *
 */
public class WordCanvas extends JPanel
{
  /**
   * Serial version number, not used.
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Width of box for each letter.
   */
  private static final int LETTER_WIDTH = 30;
  
  /**
   * Height of box for each letter.
   */
  private static final int LETTER_HEIGHT = 40;
  
  /**
   * Font size for letters.
   */
  private static final int FONT_SIZE = 32;
  
  /**
   * Center to center separation between letters.
   */
  private static final int SEPARATION = 50; 
  
  /**
   * The WordPair instance used to obtain the scrambled word to display.
   */
  WordPair wordPair;

  /**
   * Array of movable rectangles for the letters.
   */
  LetterRectangle[] letters;
  
  /**
   * Index of letter currently clicked on.
   */
  private int pressed = -1;
  
  /**
   * X-coordinate of leftmost letter position.
   */
  private int leftMargin;
  
  /**
   * Flag indicating whether the current word is a correct solution.
   */
  private boolean solved;
  
  /**
   * Flag indicating whether the first round of the game has started.
   */
  private boolean started;
  
  /**
   * Constructs a new panel instance.
   */
  public WordCanvas()
  {
    solved = false;
    started = false;
    
    this.addMouseListener(new MyMouseListener());
    this.addMouseMotionListener(new MyMouseMotionListener());
    
    // before first round is started we have to set up some default value
    String initialWord = "CLICK START";
    this.setPreferredSize(new Dimension(SEPARATION * 10 + LETTER_WIDTH * 3, LETTER_HEIGHT * 3));
    letters = new LetterRectangle[initialWord.length()];
    for (int i = 0; i < letters.length; ++i)
    {
      letters[i] = new LetterRectangle();
    }
    initializeLetters(initialWord);
  }
  
  /**
   * Called by main panel to initialize a new scrambled word.
   */
  public void startRound(WordPair pair)
  {
    this.wordPair = pair;
    solved = false;
    started = true;
    String word = pair.getScrambledWord();
    letters = new LetterRectangle[word.length()];
    for (int i = 0; i < letters.length; ++i)
    {
      letters[i] = new LetterRectangle();
    }
    initializeLetters(word);
  }
  
  /**
   * Called by main panel to indicate that the round is over and
   * that the real word should now be displayed.
   * @param solved
   *   true if the puzzle was solved, false otherwise
   */
  public void over(boolean solved)
  {
    this.solved = solved;
    initializeLetters(wordPair.getRealWord());
  }
  
  /**
   * Called by main panel to redisplay the word after getting a hint.
   */
  public void reinitialize()
  {
    initializeLetters(wordPair.getScrambledWord());
    repaint();
  }
  
  /**
   * Initialize and reposition the existing letter rectangles with the given
   * string.  This method displays a console warning and exits if the
   * string length does not match the current number of rectangles.
   * @param word
   *   current scrambled word to be displayed
   */
  private void initializeLetters(String word)
  {
    if (word.length() != letters.length)
    {
      System.out.println(word);
      System.out.println("Error: expected string of length " + letters.length);
      return;
    }
    
    // Center the word in the window, if possible; othwerise keep
    // a minimum left margin of one letter width
    int totalWidth = (letters.length - 1) * SEPARATION  + LETTER_WIDTH;
    int windowWidth = Math.max(getWidth(), totalWidth + 2 * LETTER_WIDTH);
    leftMargin = (windowWidth - totalWidth) / 2;
    leftMargin = Math.max(leftMargin, LETTER_WIDTH); 

    int hintCount = wordPair != null ? wordPair.getNumLetterHints() : 0;
    
    // set character and x, y position for each rectangle
    for (int i = 0; i < letters.length; ++i)
    {
      letters[i].ch = word.charAt(i);
      //letters[i].isHint = i < hintCount;
      int x = leftMargin + i * SEPARATION;
      int y = LETTER_HEIGHT;
      letters[i].setLocation(x, y);
    }
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    
    Graphics2D g2 = (Graphics2D) g;
    Color savedColor = g2.getColor();
    g2.setBackground(Color.LIGHT_GRAY);
    g2.clearRect(0, 0, getWidth(), getHeight());

    // paint the rectangles...
    for (int i = 0; i < letters.length; ++i)
    {
      //boolean drawHint = false;
      LetterRectangle r = letters[i];

      if (solved)
      {
        g2.setColor(Color.ORANGE);
      }
      else
      {
        g2.setColor(Color.WHITE);
      }

      
      int fixedCount = wordPair != null ? wordPair.getNumLetterHints() : 0;
      if (i < fixedCount)
      {
        g2.setColor(Color.CYAN);
      }
      else if (pressed == i)
      {        
        g2.setColor(Color.YELLOW);
      }

      g2.fillRect(r.x + 1, r.y + 1, LETTER_WIDTH - 2, LETTER_HEIGHT - 2);
      g2.setColor(Color.BLACK);
      g2.draw(r);
      
      Font f = new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE);
      g2.setFont(f);
      FontMetrics fm = g2.getFontMetrics(f);
      String text = "" + r.ch;
      int h = fm.getHeight();
      int w = fm.stringWidth(text);
      int x = r.x + LETTER_WIDTH / 2 - (w / 2);
      int y = r.y + LETTER_HEIGHT / 2 + (h / 2) - 2;
      g2.drawString(text, x, y);

      g2.setColor(savedColor);
    }
  }
  
  
  /**
   * Mouse callback updates 'pressed' state if the mouse button is 
   * pressed while within the corresponding rectangle
   */
  private class MyMouseListener implements MouseListener
  {
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
      int mouseX = event.getX();
      int mouseY = event.getY();
      
      // see if we're within one of the rectangles, but only if that rectangle
      // is not fixed
      for (int i = 0; i < letters.length; ++i)
      {
        if (letters[i].contains(mouseX, mouseY) && i >= wordPair.getNumLetterHints())
        {
          pressed = i;
          break;
        }
      }
      
      // if so, record the offsets to upper left corner
      // so we can correctly track motion
      if (pressed >= 0)
      {
        letters[pressed].xOffset = mouseX - letters[pressed].x;
        letters[pressed].yOffset = mouseY - letters[pressed].y;
        repaint();
      }
    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
      if (!started || pressed < 0) return;
        
      // 'target' is closest space, 0 through letters.length, where
      // 0 means left of leftmost char and letters.length is
      // right of rightmost char
      int target;
      int x = letters[pressed].x;
      int totalWidth = (letters.length - 1) * SEPARATION  + LETTER_WIDTH;

      if (x < leftMargin)
      {
        target = 0;
      }
      else if (x > leftMargin + totalWidth)
      {
        target = letters.length;
      }
      else 
      {
        target = 1 + (x - leftMargin) / SEPARATION;   
      }

      // if the space is next to the index, do nothing,
      // otherwise move left or right as indicated
      if (target < pressed)
      {
        wordPair.moveLeft(pressed, pressed - target);
      }
      else if (target > pressed + 1)
      {
        wordPair.moveRight(pressed, target - pressed - 1);
      }     

      // reposition the rectangles and fill in the characters
      // according to what the game now tells us.
      initializeLetters(wordPair.getScrambledWord());
      pressed = -1;
       
      repaint();
    }

  }
  
  /**
   * Mouse motion callback updates x, y position of selected
   * rectangle when mouse is moved with button held down.
   */
  private class MyMouseMotionListener implements MouseMotionListener
  {
    @Override
    public void mouseDragged(MouseEvent event)
    {
      if (!started || pressed < 0) return; 

      int x = event.getX() - letters[pressed].xOffset;
      int y = event.getY() - letters[pressed].yOffset;
      letters[pressed].setLocation(x, y);
      repaint();    
    }

    @Override
    public void mouseMoved(MouseEvent arg0)
    {
    }    
  }
  
  /**
   * Subtype of Rectangle adds a fields to store a character and
   * to record a current x and y offset to track mouse motion.
   */
  private class LetterRectangle extends Rectangle
  {
    /**
     * Serial version number, not used.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Character to be drawn within this rectangle.
     */
    private char ch;
    
    /**
     * Offset from left side to mouse x position.
     */
    private int xOffset;
    
    /**
     * Offset from top to mouse y position.
     */
    private int yOffset;
        
    /**
     * Default constructor sets width and height; position
     * is set in initializeLetters.
     */
    public LetterRectangle()
    {
      super(0, 0, LETTER_WIDTH, LETTER_HEIGHT);
    }   
  }

}
