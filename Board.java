/**
* This is the most important part including an array of squares
* which represent the board and alot of the info and methods used for showing
* and changing the board
*/
import java.util.*;
import java.awt.*;
import javax.swing.*;
import org.joda.time.*;
public class Board extends JPanel {
   private int height;
   private int width;
   private int mines;
   private int unmarked;
   private Square[][] board;
   boolean notLost = true;
   private boolean won = false;
   JButton button = new JButton();
   JFrame frame = new JFrame();
   int secStart;
/**
* sets the board to prepare for the game to start
*/
   public Board(int height, int width, int mines) {
      secStart = DateTime.now().millisOfDay().get();
      this.height = height;
      this.width = width;
      this.mines = mines;
      unmarked = mines;
      board = new Square[width][height];
      for (int i = 0; i < width; i++) {
         for (int l = 0; l < height; l++) {
            board[i][l] = new Square(false, false, false, 0);
         }
      }
   }
/**
* Replaces random spaces with mines and sets values to the rest of the spaces
*/
   public void setBoard() {
      int tempMines = mines;
      while (tempMines > 0) {
         int x = (int) (Math.random() * width);
         int y = (int) (Math.random() * height);
         if (board[x][y].getValue() != 9) {
            board[x][y] = new Square(false, false, true, 9);
            tempMines--;
         }
      }
      for (int w = 0; w < width; w++) {
         for (int l = 0; l < height; l++) {
            if (!board[w][l].getIsMine()) {
               int adj = 0;
               if (w != 0 && l != 0) {
                  if (board[w - 1][l - 1].getIsMine()) {
                     adj++;
                  }
               }
               if (w != 0) {
                  if (board[w - 1][l].getIsMine()) {
                     adj++;
                  }
               }
               if (w != 0 && l != height - 1) {
                  if (board[w - 1][l + 1].getIsMine()) {
                     adj++;
                  }
               }
               if (l != 0) {
                  if (board[w][l - 1].getIsMine()) {
                     adj++;
                  }
               }
               if (l != height - 1) {
                  if (board[w][l + 1].getIsMine()) {
                     adj++;
                  }
               }
               if (w != width - 1 && l != 0) {
                  if (board[w + 1][l - 1].getIsMine()) {
                     adj++;
                  }
               }
               if (w != width - 1) {
                  if (board[w + 1][l].getIsMine()) {
                     adj++;
                  }
               }
               if (w != width - 1 && l != height - 1) {
                  if (board[w + 1][l + 1].getIsMine()) {
                     adj++;
                  }
               }
               board[w][l] = new Square(false, false, false, adj);
            }
         }
      }
   }
/**
* returns the board
*/
   public Square[][] getBoard() {
      return board;
   }
/**
* changes the variable that tells if one has won
*/
   public void setWon() {
      won = true;
   }
/**
* tells whether one has won
*/
   public boolean getWon() {
      return won;
   }
/**
* returns how tall the board is
*/
   public int getHeight() {
      return height;
   }
/**
* returns how long the board is
*/
   public int getWidth() {
      return width;
   }
/**
* returns how many mines are on the board
*/
   public int getMines() {
      return mines;
   }
/**
* returns the time that the game started
*/
   public int getStart() {
      return secStart;
   }
/**
* sets the square at that location as marked or unmarked
*/
   public void mark(int x, int y) {
      board[x][y].mark();
   }
/**
* reveals the square and if there are no adjasent mines, all the adjasent spaces
*/
   public void reveal(int x, int y) {
      board[x][y].reveal();
      if (board[x][y].getValue() == 0) {
         if (x > 0 && y > 0 && !board[x - 1][y - 1].getRevealed() && !board[x - 1][y - 1].isMarked()) {
            this.reveal(x - 1, y - 1);
         }
         if (y > 0 && !board[x][y - 1].getRevealed() && !board[x][y - 1].isMarked()) {
            this.reveal(x, y - 1);
         }
         if (x < width - 1 && y > 0 && !board[x + 1][y - 1].getRevealed() && !board[x + 1][y - 1].isMarked()) {
            this.reveal(x + 1, y - 1);
         }
         if (x > 0 && !board[x - 1][y].getRevealed() && !board[x - 1][y].isMarked()) {
            this.reveal(x - 1, y);
         }
         if (x < width - 1 && !board[x + 1][y].getRevealed() && !board[x + 1][y].isMarked()) {
            this.reveal(x + 1, y);
         }
         if (x > 0 && y < height - 1 && !board[x - 1][y + 1].getRevealed() && !board[x - 1][y + 1].isMarked()) {
            this.reveal(x - 1, y + 1);
         }
         if (y < height - 1 && !board[x][y + 1].getRevealed() && !board[x][y + 1].isMarked()) {
            this.reveal(x, y + 1);
         }
         if (x < width - 1 && y < height - 1 && !board[x + 1][y + 1].getRevealed() && !board[x + 1][y + 1].isMarked()) {
            this.reveal(x + 1, y + 1);
         }
      }
      if (board[x][y].getIsMine()) {
         notLost = false;
      } else {
         notLost = true;
      }
   }
/**
* Sets up and shows the frame
*/
   public void show(Board gameBoard) {
      frame.setForeground(Color.LIGHT_GRAY);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(new Dimension(21 * width + 9, 21 * height + 88));
      frame.setTitle("Mine Sweeper");
      Graphics g = frame.getGraphics();
      RectPanel blah = new RectPanel(width, height, gameBoard);
      blah.addMouseListener(new ButtonClicker(gameBoard));
      frame.add(blah);
      frame.setVisible(true);
   }
/**
* updates the frame
*/
   public void addToFrame(RectPanel blah) {
      frame.add(blah);
      frame.setVisible(true);
   }
/**
* says if the player hasn't lost
*/
   public boolean getLost() {
      return notLost;
   }
/**
* sets if the player hasn't not lost and shows the board
*/
   public void setLost() {
      notLost = false;
      for (int w = 0; w < width; w++) {
         for (int l = 0; l < height; l++) {
            this.reveal(w, l);
         }
      }
   }
}