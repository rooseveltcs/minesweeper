import java.awt.Color;
import java.awt.*;
import javax.swing.*;
public class RectPanel extends JPanel {
   int c, r;
   Board gameBoard;
   boolean notLost = true;
   public RectPanel(int c, int r, Board gameBoard) {
      this.gameBoard = gameBoard;
      this.c = c;
      this.r = r;
   }
   
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (int cR = 0; cR < r; cR++) {
         for (int cC = 0; cC < c; cC++) {
            g.setColor(Color.GRAY);
            g.fillRect(cC * 21 + 1, cR * 21 + 1, 20, 20);
         }
      }
      for (int cR = 0; cR < r; cR++) {
         for (int cC = 0; cC < c; cC++) {
            g.setColor(Color.LIGHT_GRAY);
            if (gameBoard.getBoard()[cC][cR].getRevealed()) {
               g.fillRect(cC * 21 + 1, cR * 21 + 1, 20, 20);
            }
//             if (gameBoard.getBoard()[cC][cR].getIsMine()) {
//                System.out.println(cC + "," + cR);
//             }
            if (gameBoard.getBoard()[cC][cR].getRevealed()) {
               if (gameBoard.getBoard()[cC][cR].getValue() != 0) {
                  g.setFont(new Font("Courier", Font.BOLD, 20));
                  if (gameBoard.getBoard()[cC][cR].getValue() == 1) {
                     g.setColor(Color.BLUE);
                  }
                  if (gameBoard.getBoard()[cC][cR].getValue() == 2) {
                     g.setColor(new Color(12, 159, 32));
                  }
                  if (gameBoard.getBoard()[cC][cR].getValue() == 3) {
                     g.setColor(Color.YELLOW);
                  }
                  if (gameBoard.getBoard()[cC][cR].getValue() == 4) {
                     g.setColor(new Color(95, 7, 108));
                  }
                  if (gameBoard.getBoard()[cC][cR].getValue() == 5) {
                     g.setColor(Color.RED);
                  }
                  if (gameBoard.getBoard()[cC][cR].getValue() == 6) {
                     g.setColor(new Color(7, 131, 98));
                  }
                  if (gameBoard.getBoard()[cC][cR].getValue() == 7) {
                     g.setColor(Color.WHITE);
                  }
                  if (gameBoard.getBoard()[cC][cR].getValue() == 8) {
                     g.setColor(Color.DARK_GRAY);
                  }
                  if (gameBoard.getBoard()[cC][cR].isMarked()) {
                     g.setColor(Color.DARK_GRAY);
                     g.fillOval(cC * 21, cR * 21, 21, 21);
                     g.setColor(Color.RED);
                     g.drawLine(cC * 21 + 1, cR * 21 + 1, cC * 21 + 20, cR * 21 + 20);
                     g.drawLine(cC * 21 + 1, cR * 21 + 20, cC * 21 + 20, cR * 21 + 1);

                     g.drawLine(cC * 21 + 1, cR * 21 + 2, cC * 21 + 19, cR * 21 + 20);
                     g.drawLine(cC * 21 + 1, cR * 21 + 19, cC * 21 + 19, cR * 21 + 1);

                     g.drawLine(cC * 21 + 2, cR * 21 + 1, cC * 21 + 20, cR * 21 + 19);
                     g.drawLine(cC * 21 + 2, cR * 21 + 20, cC * 21 + 20, cR * 21 + 2);

                     g.drawLine(cC * 21 + 1, cR * 21 + 3, cC * 21 + 18, cR * 21 + 20);
                     g.drawLine(cC * 21 + 1, cR * 21 + 18, cC * 21 + 18, cR * 21 + 1);

                     g.drawLine(cC * 21 + 3, cR * 21 + 1, cC * 21 + 20, cR * 21 + 18);
                     g.drawLine(cC * 21 + 3, cR * 21 + 20, cC * 21 + 20, cR * 21 + 3);
                  } else {                  
                     g.drawString("" + gameBoard.getBoard()[cC][cR].getValue(), cC * 21 + 5, cR * 21 + 17);
                  }
               }
               if (gameBoard.getBoard()[cC][cR].getIsMine()) {
                  g.setColor(Color.DARK_GRAY);
                  g.fillOval(cC * 21, cR * 21, 21, 21);
                  if (gameBoard.getBoard()[cC][cR].isMarked()) {
                     g.setColor(Color.RED);
                     g.drawOval(cC * 21, cR * 21, 21, 21);
                     g.drawOval(cC * 21 + 3, cR * 21 + 3, 15, 15);
                     g.drawOval(cC * 21 + 6, cR * 21 + 6, 9, 9);
                     g.drawOval(cC * 21 + 9, cR * 21 + 9, 3, 3);
                  }
               }
            }
            if (gameBoard.getBoard()[cC][cR].isMarked() && notLost) {
               g.setColor(Color.RED);
               g.fillRect(cC * 21 + 1, cR * 21 + 1, 20, 20);
               g.setColor(Color.BLACK);
               g.drawLine(cC * 21 + 8, cR * 21 + 6, cC * 21 + 18, cR * 21 + 9);
               g.drawLine(cC * 21 + 8, cR * 21 + 12, cC * 21 + 18, cR * 21 + 9);
               g.drawLine(cC * 21 + 8, cR * 21 + 6, cC * 21 + 8, cR * 21 + 19);
            }
         }
      }
      g.setFont(new Font("SansSerif", Font.BOLD, 10));
      g.setColor(Color.BLACK);
      g.drawString("Left click to reveal a tile", 1, r * 21 + 15);
      g.drawString("Right click to mark a mine", 1, r * 21 + 30);   
   }

   public void setLost() {
      notLost = false;
   }
//    public void changeBoard(Board gameBoard) {
//       this.gameBoard = gameBoard;
//    }
}
