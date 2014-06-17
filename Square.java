/**
* This is the most basic element of the program
* Squares are each indevidual space and say whether a space is a mine,
* what its value is, if it is marked and if it has been revealed
*/
import java.util.*;
public class Square {
   private boolean marked;
   private boolean isMine;
   private int value;
   private boolean revealed;
/**
* creates the square with all its variables already specified
*/
   public Square(boolean marked, boolean revealed, boolean isMine, int value){
      this.marked = marked;
      this.isMine = isMine;
      this.value = value;
      this.revealed = revealed;
   }
/**
* All the following methods are self explanitory
*/
   public boolean isMarked() {
      return marked;
   }
   
   public int getValue() {
      return value;
   }
   
   public boolean getRevealed() {
      return revealed;
   }
   
   public boolean getIsMine() {
      return isMine;
   }
   
   public void reveal() {
      revealed = true;
   }
   
   public void mark() {
      if (marked) {
         marked = false;
      } else {
         marked = true;
      }
   }
}