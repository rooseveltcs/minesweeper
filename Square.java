import java.util.*;
public class Square {
   private boolean marked;
   private boolean isMine;
   private int value;
   private boolean revealed;
   
   public Square(boolean marked, boolean revealed, boolean isMine, int value){
      this.marked = marked;
      this.isMine = isMine;
      this.value = value;
      this.revealed = revealed;
   }
   
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