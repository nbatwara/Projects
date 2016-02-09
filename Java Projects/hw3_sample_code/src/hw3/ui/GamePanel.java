
package hw3.ui;

import hw3.api.GameStatus;
import hw3.api.IGame;
import hw3.api.IGameIcon;
import hw3.api.IPlayLevel;
import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.Block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * User interface for the main grid of a Tetris-like game.
 */
public class GamePanel extends JPanel
{
  /**
   * The javax.swing.Timer instance used to animate the UI.
   */
  private Timer timer;

  /**
   * The IGame instance for which this is the UI.
   */
  private IGame game;

  /**
   * State variable indicating when the timer frequency is increased for
   * dropping a polyomino to the bottom of the grid.
   */
  private boolean fastDrop;

  /**
   * State variable counts down to zero while animating the cells to be
   * collapsed.
   */
  private int animationState = 0;

  /**
   * The difficulty level of the game, which determines the speed of the
   * animation.
   */
  private IPlayLevel level;

  /**
   * When the IGame object is in the COLLAPSING state, this field stores the the
   * cells to be collapsed in the next step. This value can be used to apply
   * special rendering or animation to the collapsing cells. An invariant is
   * maintained that this variable is non-null if and only if the IGame state is
   * COLLAPSING.
   */
  private Point[] blocksToCollapse = null;

  /**
   * Constructs a GamePanel with the given game and play level.
   * 
   * @param pGame
   *          the IGame instance for which this is the UI
   * @param pLevel
   *          the IPlayLevel that will determine the animation speed for this UI
   */
  public GamePanel(IGame pGame, IPlayLevel pLevel)
  {
    game = pGame;
    level = pLevel;
    addKeyListener(new MyKeyListener());
    timer = new Timer(level.speed(0), new TimerCallback());
    timer.start();
  }

  // The paintComponent is invoked by the Swing framework whenever
  // the panel needs to be rendered on the screen. In this application,
  // repainting is normally triggered by the calls to the repaint()
  // method in the timer callback and the keyboard event handler (see below).

  @Override
  public void paintComponent(Graphics g)
  {
    // clear background
    g.clearRect(0, 0, getWidth(), getHeight());

    // paint occupied cells of the grid
    for (int row = 0; row < game.getHeight(); ++row)
    {
      for (int col = 0; col < game.getWidth(); ++col)
      {
        IGameIcon t = game.getGridIcon(row, col);
        if (t != null)
        {
          paintOneCell(g, row, col, t.getColorHint());
        }
      }
    }

    if (blocksToCollapse != null)
    {
      // if cells are collapsing, flash them
      if (animationState % 2 != 0)
      {
        g.setColor(Color.DARK_GRAY);

        for (int i = 0; i < blocksToCollapse.length; ++i)
        {
          Point p = blocksToCollapse[i];
          g.fillRect(p.x * GameMain.SIZE, p.y * GameMain.SIZE, GameMain.SIZE, GameMain.SIZE);
        }
      }
    }
    else if (!game.gameOver())
    {
      // otherwise, paint the current polyomino
      IPolyomino t = game.getCurrent();
      for (Block c : t.getBlocks())
      {
        Position p = c.getPosition();
        paintOneCell(g, p.getRow(), p.getCol(), c.getColorHint());
      }
    }
  }

  /**
   * Renders a single cell of the grid.
   *
   * @param g
   *          the Swing graphics context
   * @param row
   *          y-coordinate of the cell to render
   * @param col
   *          x-coordinate of the cell to render
   * @param t
   *          the IPolyomino associated with the cell, normally used to
   *          determine the color with which to render the cell
   */
  private void paintOneCell(Graphics g, int row, int col, Color c) //GridCell t)
  {
    // scale everything up by the SIZE
    int x = GameMain.SIZE * col;
    int y = GameMain.SIZE * row;
    //g.setColor(t.getColorHint());
    g.setColor(c);
    g.fillRect(x, y, GameMain.SIZE, GameMain.SIZE);
    g.setColor(Color.GRAY);
    ;
    g.drawRect(x, y, GameMain.SIZE - 1, GameMain.SIZE - 1);
  }

  /**
   * Method invoked each time the timer fires.
   */
  private void doOneStep()
  {
    // transition the game through one step, and interpret the status
    GameStatus state = game.step();
    if (state == GameStatus.GAME_OVER)
    {
      timer.stop();
    }
    else
    {
      if (state == GameStatus.NEW_POLYOMINO)
      {
        // if we were in the collapsing state, we're done
        blocksToCollapse = null;

        // TODO only do this if we were animating
        int speed = level.speed(game.getScore());
        timer.setDelay(speed);
        timer.setInitialDelay(speed);
        timer.restart();
      }
      if (state == GameStatus.COLLAPSING || state == GameStatus.STOPPED)
      {
        // current polygon has reached the bottom, so if we were doing
        // a fast drop, reset the timer to normal
        if (fastDrop)
        {
          normalSpeed();
          fastDrop = false;
        }
        if (state == GameStatus.COLLAPSING)
        {
          // indicates that next call to step() will collapse cells,
          // so we want to animate them before invoking step() again.
          // Use the fast drop rate for animation
          blocksToCollapse = game.getCellsToCollapse();
          animationState = 5;
          fastSpeed();
        }
      }
      // else state is FALLING, nothing to do but repaint
    }
  }

  private void normalSpeed()
  {
    int speed = level.speed(game.getScore());
    timer.setDelay(speed);
    timer.setInitialDelay(speed);
    timer.restart();
  }

  private void fastSpeed()
  {
    int speed = level.fastDropSpeed(game.getScore());
    timer.setDelay(speed);
    timer.setInitialDelay(0);
    timer.restart();
  }

  /**
   * Listener for timer events. The actionPerformed method is invoked each time
   * the timer fires.
   */
  private class TimerCallback implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      if (animationState == 0)
      {
        doOneStep();
        repaint();
      }
      else
      {
        repaint();
        animationState--;
      }
    }
  }

  /**
   * Listener for keyboard events.
   */
  private class MyKeyListener implements KeyListener
  {
    @Override
    public void keyPressed(KeyEvent e)
    {
      int code = e.getKeyCode();
      if (code == KeyEvent.VK_RIGHT)
      {
        if (game.shiftRight())
        {
          repaint();
        }
      }
      else if (code == KeyEvent.VK_LEFT)
      {
        if (game.shiftLeft())
        {
          repaint();
        }
      }
      else if (code == KeyEvent.VK_UP)
      {
        if (game.transform())
        {
          repaint();
        }
      }
      else if (code == KeyEvent.VK_SPACE)
      {
        game.cycle();
        repaint();
      }
      else if (code == KeyEvent.VK_DOWN)
      {
        if (!fastDrop)
        {
          // reset timer for the fast drop frame rate
          fastSpeed();
          fastDrop = true;
        }
      }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
      int code = e.getKeyCode();
      if (code == KeyEvent.VK_DOWN)
      {
        // turn off fast drop mode when downarrow is released,
        // but not if we're animating
        if (fastDrop && animationState == 0)
        {
          normalSpeed();
        }
        if (fastDrop)
        {
          fastDrop = false;
        }
      }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
      // not used
    }

  }
}
