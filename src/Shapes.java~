import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;

public class Shapes extends JComponentWithEvents{
  
  private int sides = 0;
  private int correctLength = 60;
  private Color shapeColor;
  private Color[] colorOptions = {Color.red, Color.blue, Color.green, Color.yellow};
  public String fireImage = "Resources/fire.png";
  private char[][] spaces = new char[10][10];
  private int rows = 10;
  private int cols = 10;
  
  public void Shapes(){
    for(int i=0; i<800; i+=80)
      for(int j=0; j<800; j+=80)
         spaces[i][j] = 'e';
  }
  
  public void randomNumber(){
    Random random = new Random();
    sides = random.nextInt(4)+3;
  }
  
  public void randomColor(){
    Random color = new Random();
    shapeColor = colorOptions[color.nextInt(4)];
  }
  
  public void placeFire(){
    Random firePosition = new Random();
    int fires = firePosition.nextInt(5)+5;
    for(int i=0; i<fires; i++){
      int xFire = firePosition.nextInt(9)+1;
      int yFire = firePosition.nextInt(10);
      spaces[xFire][yFire] = 'f';
    }
  }
  
  public void placeBases(){
    Random basePosition = new Random();
    int ySpot = basePosition.nextInt(10);
    spaces[0][ySpot] = 'b';
    ySpot = basePosition.nextInt(10);
    spaces[9][ySpot] = 'b';
  }
  
  public void start(){
    randomNumber();
    randomColor();
    placeFire();
    placeBases();
    System.out.println(Arrays.deepToString(spaces));
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
    page.setColor(Color.black);
    page.setFont(new Font("Arial", Font.BOLD, 15));
    page.drawString("Sides: " + sides, 10, 20);
    for(int i=0; i<rows; i++)
      for(int j=0; j<cols; j++){
        if(spaces[i][j] == 'b') paintBase(page, i, j);
        if(spaces[i][j] == 'f') paintFire(page, fireImage, i, j);
      }
    //paintTriangle(page, 40, 40, 60, shapeColor);
    //paintSquare(page, 80, 80, 60, shapeColor);
    //paintPenta(page, 200, 200, 50, shapeColor);
    //paintHexa(page, 300, 300, 40, shapeColor);
    paintGrid(page);
  }
  
  public void paintGrid(Graphics2D page){
    page.setColor(Color.black);
    for(int i=0; i<800; i+=80)
      for(int j=0; j<800; j+=80)
        page.drawRect(i, j, i+80, j+80);
  }
  
  public void paintBase(Graphics2D page, int x, int y){
    page.setColor(Color.gray);
    page.fillRect((x*80)+10, (y*80)+10, 60, 60); 
  }
  
  public void paintFire(Graphics2D page, String fireImage, int x, int y){
    drawImage(page, fireImage, (x*80)+15, (y*80)+10, .1, 0);
  }  
  
  public void paintTriangle(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+(sideLength/2), x1+sideLength};
    int[] y = {y1, (int)(y1+(sideLength*Math.sin(Math.PI/3))), y1};
    int n = 3;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.fillOval(x1-5, y1-5, 10, 10);
  }
  
  public void paintSquare(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+sideLength, x1+sideLength, x1};
    int[] y = {y1, y1, y1+sideLength, y1+sideLength};
    int n = 4;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.fillOval(x1-5, y1-5, 10, 10);
  }
  
  public void paintPenta(Graphics2D page, int x1, int y1, int sideLength, Color color){
    int xpoint1 = x1;
    int xpoint2 = (int)(x1+sideLength-(Math.cos((3*Math.PI)/5)*sideLength));
    int xpoint3 = x1+(sideLength/2);
    int xpoint4 = (int)(x1+(Math.cos((3*Math.PI)/5)*sideLength));
    int xpoint5 = x1+sideLength;
    
    int ypoint1 = y1;
    int ypoint2 = (int)(y1+(Math.sin((2*Math.PI)/3)*sideLength));
    int ypoint3 = (int)(y1+(Math.sin((2*Math.PI)/3)*sideLength)+(Math.sin(Math.PI/5)*sideLength));
    int ypoint4 = (int)(y1+(Math.sin((2*Math.PI)/3)*sideLength));
    int ypoint5 = y1;
    
    page.setColor(color);
    int[] x = {xpoint1, xpoint5, xpoint2, xpoint3, xpoint4};
    int[] y = {ypoint1, ypoint5, ypoint2, ypoint3, ypoint4};
    int n = 5;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.fillOval(x1-5, y1-5, 10, 10);
  }
  
  public void paintHexa(Graphics2D page, int x1, int y1, int sideLength, Color color){
    int xpoint1 = x1;
    int xpoint2 = x1+sideLength;
    int xpoint3 = (int)(x1-(Math.cos(Math.PI/3)*sideLength));
    int xpoint4 = x1;
    int xpoint5 = x1+sideLength;
    int xpoint6 = (int)(x1+sideLength+(Math.cos(Math.PI/3)*sideLength)); 
      
    int ypoint1 = y1;
    int ypoint2 = y1;
    int ypoint3 = (int)(y1+(Math.sin(Math.PI/3)*sideLength));
    int ypoint4 = (int)(y1+2*(Math.sin(Math.PI/3)*sideLength));
    int ypoint5 = (int)(y1+2*(Math.sin(Math.PI/3)*sideLength));
    int ypoint6 = (int)(y1+(Math.sin(Math.PI/3)*sideLength));
    
    page.setColor(color);
    int[] x = {xpoint1, xpoint2, xpoint6, xpoint5, xpoint4, xpoint3};
    int[] y = {ypoint1, ypoint2, ypoint6, ypoint5, ypoint4, ypoint3};
    int n = 6;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.fillOval(x1-5, y1-5, 10, 10);
  }
  
  public static void main(String[] args){launch(800,800);}
}