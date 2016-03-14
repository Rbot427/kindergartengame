import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class TrickGame extends JComponentWithEvents {
  public boolean gameOver = false;
  public static int wWidth = 800, wHeight = 600;
  Character tyrone;
  String tyroneImage = "Resources/Tyrone the Turtle Skateboard.png";
  
  public void start() {
    init();
    setTimerDelay(50);
  }
  
  public void init() {
    frame.setTitle("Trick Master Tyrone");
    tyrone = new Character(getImageFromFile(tyroneImage));
    tyrone.setVelocity(0,0);
    tyrone.scale = 0.5;
  }

  public void timerFired() {
    tyrone.move();
    //tyrone.rotate(0.2);
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
    if(key == ' ') cJump(tyrone);
    else if(key == 'l') cLand(tyrone);
    else if(key == LEFT) tyrone.setPos(tyrone.posX - 1, tyrone.posY);
    else if(key == RIGHT) tyrone.setPos(tyrone.posX + 1, tyrone.posY);
  }
  
  public void cLand(Character c) {
    c.setVelocity(c.velocityX, 0);
    c.setAcceleration(c.accelX, 0);
  }
  
  public void cJump(Character c) {
    c.setVelocity(c.velocityX, -20);
    c.setAcceleration(c.accelX, 1);
  }

  public void drawCharacter(Graphics2D page, Character c) {
    drawImage(page, c.img, c.posX, c.posY, c.scale, c.rotation);
  }
  
  public void paint(Graphics2D page) {
    drawCharacter(page, tyrone);
  }

  // Main Standard Method
  public static void main(String[] args) { launch(wWidth, wHeight); }
}