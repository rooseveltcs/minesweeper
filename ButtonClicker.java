/**
* This class is a listener that marks or reveals when one clicks on a space
*/
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import org.joda.time.*;

public class ButtonClicker extends MouseInputAdapter {
   Board gameBoard;
   int r;
   int c;
   boolean lost;
   boolean won;
   boolean won1 = false;
/**
* creates the object
*/
   public ButtonClicker(Board gameBoard) {
      this.gameBoard = gameBoard;
   }
/**
* marks or reveals based on which button is clicked and if one has one, sends the "you win" message
*/
   public void mouseClicked(MouseEvent event) {
      c = event.getX() / 21;
      r = event.getY() / 21;
      if (event.getButton() == 1 && !won1) {
         if (r <= gameBoard.getHeight()) {
            if (gameBoard.getBoard()[c][r].isMarked() == false) {
               gameBoard.getBoard()[c][r].reveal();
               if (gameBoard.getBoard()[c][r].getValue() == 0) {
                  if (c > 0 && r > 0 && !gameBoard.getBoard()[c - 1][r - 1].isMarked()) {
                     gameBoard.reveal(c - 1, r - 1);
                  }
                  if (r > 0 && !gameBoard.getBoard()[c][r - 1].isMarked()) {
                     gameBoard.reveal(c, r - 1);
                  }
                  if (c < gameBoard.getWidth() - 1 && r > 0  && !gameBoard.getBoard()[c + 1][r - 1].isMarked()) {
                     gameBoard.reveal(c + 1, r - 1);
                  }
                  if (c > 0 && !gameBoard.getBoard()[c - 1][r].isMarked()) {
                     gameBoard.reveal(c - 1, r);
                  }
                  if (c < gameBoard.getWidth() - 1 && !gameBoard.getBoard()[c + 1][r].isMarked()) {
                     gameBoard.reveal(c + 1, r);
                  }
                  if (c > 0 && r < gameBoard.getHeight() - 1 && !gameBoard.getBoard()[c - 1][r + 1].isMarked()) {
                     gameBoard.reveal(c - 1, r + 1);
                  }
                  if (r < gameBoard.getHeight() - 1 && !gameBoard.getBoard()[c][r + 1].isMarked()) {
                     gameBoard.reveal(c, r + 1);
                  }
                  if (c < gameBoard.getWidth() - 1 && r < gameBoard.getHeight() - 1 && !gameBoard.getBoard()[c + 1][r + 1].isMarked()) {
                     gameBoard.reveal(c + 1, r + 1);
                  }
               }
               if (gameBoard.getBoard()[c][r].getIsMine()) {
                  lost = true;
                  gameBoard.setLost();
               }
            }
         } else if (!gameBoard.getBoard()[c][r].getRevealed()) {
            if (r <= gameBoard.getHeight()) {
               gameBoard.getBoard()[c][r].mark();
            }
         }
      }
      else if (!gameBoard.getBoard()[c][r].getRevealed() && !won1) {
         gameBoard.getBoard()[c][r].mark();
      }
      RectPanel blah = new RectPanel(gameBoard.getWidth(), gameBoard.getHeight(), gameBoard);
      blah.addMouseListener(new ButtonClicker(gameBoard));
      if (lost) {
         blah.setLost();
      }
      gameBoard.addToFrame(blah);
      won = true;
      for (int cR = 0; cR < gameBoard.getWidth(); cR++) {
         for (int cC = 0; cC < gameBoard.getHeight(); cC++) {
            if (!gameBoard.getBoard()[cR][cC].getRevealed() && !gameBoard.getBoard()[cR][cC].getIsMine()) {
               won = false;
            }
            if (gameBoard.getBoard()[cR][cC].getRevealed() && gameBoard.getBoard()[cR][cC].getIsMine()) {
               won = false;
            }            
         }
      }
      if (won && !won1) {
         won1 = true;
         int sec =((DateTime.now().millisOfDay().get() - gameBoard.getStart()) / 1000);
         gameBoard.setWon();
         if (sec > 1) {
            JOptionPane.showMessageDialog(null, "YOU WON!\nIt took you " + (sec + " seconds!"));
         } else {
            JOptionPane.showMessageDialog(null, "YOU WON!\nIt took you " + (sec + " second!"));
         }
//       System.out.print("good");
//       System.out.print(gameBoard.getBoard()[1][1].isMarked());
//       System.out.print(gameBoard.getBoard()[1][1].getRevealed());
      }
   }
}