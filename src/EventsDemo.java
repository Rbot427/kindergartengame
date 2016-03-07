// EventsDemo.java
// By David Kosbie

// A simple demo showing some of the functionality of JComponentWithEvents:
// Requires JComponentWithEvents.class

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class EventsDemo extends JComponentWithEvents {
  public ArrayList<String> images;
  public String steve = "Steve the Strawberry.png";
  public String tyrone = "Tyrone the Turtle.png";
  public String lion = "Lion the Lemon.png";
  public String music1 = "brandenburg6.mid";
  public double rad = 0;

  public void start() {
    play(music1);
    setTimerDelay(1);
    images = new ArrayList<>();
    images.add(steve);
    images.add(tyrone);
    images.add(lion);
  }

  public void timerFired() {
    rad += 0.001;
  }

  public void mousePressed(int x, int y) {
    images.add(tyrone);
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
    for(int i = 0; i < images.size(); i++) {
      drawImage(page, images.get(i), (int)(Math.random()*getWidth()), (int)(Math.random()*getHeight()), 1, rad);
    }
  }

  // Standard main method:
  public static void main(String[] args) { launch(500, 400); }
}
