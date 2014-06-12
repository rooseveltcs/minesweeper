import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ButtonClicker extends MouseInputAdapter {// implements ActionListener {
   Board gameBoard;
   int r;
   int c;
   boolean lost;
   boolean won;
   public ButtonClicker(Board gameBoard) {
      this.gameBoard = gameBoard;
   }
   
   public void mouseClicked(MouseEvent event) {
      c = event.getX() / 21;
      r = event.getY() / 21;
      if (event.getButton() == 1) {
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
      }
      if (won) {
         JOptionPane.showMessageDialog(null, "YOU WON!");
      }
//       System.out.print("good");
//       System.out.print(gameBoard.getBoard()[1][1].isMarked());
//       System.out.print(gameBoard.getBoard()[1][1].getRevealed());

      //right now, it changes the values, but it doesn't update the image
      //everything looks right if the original 
   }
}