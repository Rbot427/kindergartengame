// EventsDemo.java
// By David Kosbie

// A simple demo showing some of the functionality of JComponentWithEvents:
// Requires JComponentWithEvents.class

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class EventsDemo extends JComponentWithEvents {
  public String steve = "Resources/Steve the Strawberry.png";
  public String tyrone = "Resources/Tyrone the Turtle.png";
  public String lion = "Resources/Lion the Lemon.png";
  public String music1 = "Resources/brandenburg6.mid";
  public Icon steveo;
  public double rad = 0;

  public void start() {
    play(music1);
    setTimerDelay(1);
    steveo = new Icon(getImageFromFile(tyrone));
  }

  public void timerFired() {
    rad += 0.001;
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
    drawImage(page, steveo.img, steveo.posX, steveo.posY + 100, 0.5, 0);
  }

  // Standard main method:
  public static void main(String[] args) { launch(600, 400); }
}
