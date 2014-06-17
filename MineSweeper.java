 /**
 * The main class that starts the game and gets s*** done 
 */
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MineSweeper extends JPanel{
   static int width = 0;
   static int height = 0;
   int tempVar;
   static Annoyance num;
   public static void main(String[] args) throws InterruptedException {
      newGame();
   }
 /**
 * Starts the game and calls createWidth, createHeight and createMines 
 * This method always creates the board to the specefication
 */
   public static void newGame() throws InterruptedException {
      Scanner console = new Scanner(System.in);
      System.out.println("Welcome to minesweeper!");
      System.out.print("First, ");
      width = createWidth(console);
      System.out.print("Next, ");
      height = createHeight(console);
      System.out.print("Finally, ");
      int mines = createMines(console);
      Board gameBoard = new Board(height, width, mines);
      gameBoard.setBoard();
      showBoard(gameBoard);
   }
 /**
 * Asks for and returns the width and continually calls itself until a good size is created
 */
   public static int createWidth(Scanner console) {
      System.out.println("enter the width of the board you want! (between 10 and 50 squares)");
      int width = console.nextInt();
      if (width < 10 || width > 50) {
         System.out.println("Sorry, you can't have a board with that width, width must be between 10 and 50 squares.");
         System.out.print("Now, ");
         width = createWidth(console);
      }
      return width;
   }
 /**
 * Asks for and returns the height and continually calls itself until a good size is created
 */
    public static int createHeight(Scanner console) throws InterruptedException {
      System.out.println("enter the height of the board you want! (between 5 and 40 squares)");
      int height = console.nextInt();
      if (height < 5 || height > 40) {
         System.out.println("Sorry, you can't have a board with that height\nheight must be between 5 and 40 squares.");
         System.out.print("Now, ");
         height = createHeight(console);
      }
      return height;
   }
 /**
 * Asks for and returns the number of mines and continually calls itself until a good number is created
 */
    public static int createMines(Scanner console) {
      System.out.println("enter the number of mines you want on the board!");
      System.out.println("I recommend " + (height * width / 5));
      int mines = console.nextInt();
      if (mines == 0) {
         System.out.println("Sorry, you can't have a board with 0 mines.");
         System.out.print("Now, ");
         mines = createMines(console);
      } else if (mines >= height * width) {
         System.out.println("Sorry, you can't have a board with more mines than spaces.");
         System.out.print("Now, ");
         mines = createMines(console);
      } else if (mines < 0) {
         System.out.println("Sorry, you can't have a board with a negative number of mines.");
         System.out.print("Now, ");
         mines = createMines(console);
      }
      return mines;
   }
 /**
 * shows the board, and would eventually help for playing multiple games
 */
    public static boolean play(Board gameBoard, Scanner console) {
      showBoard(gameBoard);
      return(gameBoard.getLost());
   }
 /**
 * shows the board and starts the game
 */   
   public static void showBoard(Board gameBoard) {
      gameBoard.show(gameBoard);
   }
}