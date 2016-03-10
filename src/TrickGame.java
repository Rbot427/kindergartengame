import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class TrickGame extends JComponentWithEvents {
  public boolean gameOver = false;
  public static int wWidth = 800, wHeight = 600;
  Character tyrone;
  String tyroneImage = "Resources/Tyrone the Turtle.png";
  
  public void start() {
    init();
    setTimerDelay(100);
  }
  
  public void init() {
    frame.setTitle("Trick Master Tyrone");
    tyrone = new Character(getImageFromFile(tyroneImage));
    tyrone.setVelocity(5,1);
  }

  public void timerFired() {
    tyrone.move();
    tyrone.rotate(0.2);
  }

  public void mousePressed(int x, int y) {
    
  }

  public void mouseDragged(int x, int y) {
   
  }

  public void mouseReleased(int x, int y) {
    
  }

  public void mouseMoved(int x, int y) {
    
  }

  private void mouseEnteredOrExited(String msg, int x, int y) {
    
  }

  public void mouseEntered(int x, int y) {
    
  }

  public void mouseExited(int x, int y) {
    
  }

  public void keyPressed(char key) {
    
  }

  public void paint(Graphics2D page) {
    drawImage(page, tyrone.img, tyrone.posX, tyrone.posY, 1, tyrone.rotation);
  }

  // Main Standard Method
  public static void main(String[] args) { launch(wWidth, wHeight); }
}