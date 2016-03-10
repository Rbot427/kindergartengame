import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;

public class Shapes extends JComponentWithEvents{
  
  private int sides = 0;
  private int correctLength = 60;
  
  public void Shapes(){
    //getImageFromFile(lava);
  }
  
  public void randomNumber(){
    Random random = new Random();
    sides = random.nextInt(5)+2;
  }
  
  public void start(){
  }
  
  public void reset(){
  }
  
  public void timerFired(){
  }
  
  public void keyPressed(){
  }
  
  public void mousePressed(int x, int y){
  }
  
  public void paint(Graphics2D page){
    page.setColor(Color.orange);
    page.fillRect(0,0,getWidth(),getHeight());
    page.setColor(Color.gray);
    page.fillRect(getWidth()-60, getHeight()/2-30, 60, 60);
    page.fillRect(0, getHeight()/2-30, 60, 60);
    page.setColor(Color.black);
    page.drawString("Sides: " + sides, 0, 0);
  }
  
  public static void main(String[] args){launch(800,600);}
}