import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MineSweeper extends JPanel{
   static int width = 0;
   static int height = 0;
   public static void main(String[] args) {
      newGame();
   }
   
   public static void newGame() {
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
      boolean lost = false;
//       while (gameBoard.getLost()) {
// //          lost = play(gameBoard, console);
         showBoard(gameBoard);
// //          lost = gameBoard.getLost();      
//       }
   }
   
   public static int createWidth(Scanner console) {
      System.out.println("enter the width of the board you want!");
      int width = console.nextInt();
      if (width < 10 || width > 50) {
         System.out.println("Sorry, you can't have a board with that width, width must be between 10 and 50 squares.");
         System.out.print("Now, ");
         width = createWidth(console);
      }
      return width;
   }
   
   public static int createHeight(Scanner console) {
      System.out.println("enter the height of the board you want!");
      int height = console.nextInt();
      if (height < 5 || height > 40) {
         System.out.println("Sorry, you can't have a board with that height, height must be between 5 and 40 squares.");
         System.out.print("Now, ");
         height = createHeight(console);
      }
      return height;
   }

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
   
   public static boolean play(Board gameBoard, Scanner console) {
      showBoard(gameBoard);
      return(gameBoard.getLost());
   }
   
   public static void showBoard(Board gameBoard) {
      gameBoard.show(gameBoard);
   }
}