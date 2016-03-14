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
    page.setFont(new Font("Arial", Font.BOLD, 20));
    page.drawString("Sides: " + sides, 10, 20);
    paintTriangle(page, 40, 40, 60, Color.gray);
    paintSquare(page, 80, 80, 60, Color.gray);
    paintPenta(page, 200, 200, 60, Color.gray);
  }
  
  public void paintTriangle(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+(sideLength/2), x1+sideLength};
    int[] y = {y1, y1+sideLength, y1};
    int n = 3;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
  }
  
  public void paintSquare(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+sideLength, x1+sideLength, x1};
    int[] y = {y1, y1, y1+sideLength, y1+sideLength};
    int n = 4;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
  }
  
  public void paintPenta(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+sideLength, (int)(x1+sideLength+Math.cos(108)*sideLength), x1+sideLength/2, (int)(x1-Math.cos(108)*sideLength)};
    int[] y = {y1, y1, (int)(y1+Math.sin(108)*sideLength), (int)(y1+Math.sin(108)*sideLength+Math.sin(36)*sideLength), (int)(y1+Math.sin(108)*sideLength)};
    int n = 5;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
  }
  
  public void paintHexa(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+sideLength, x1+sideLength, x1};
    int[] y = {y1, y1, y1+sideLength, y1+sideLength};
    int n = 6;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
  }
  
  public static void main(String[] args){launch(800,600);}
}