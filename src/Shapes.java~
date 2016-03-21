import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;

public class Shapes extends JComponentWithEvents{
  
  private int sides = 0;
  private int correctLength = 60;
  private Color triShapeColor;
  private Color squShapeColor;
  private Color pentShapeColor;
  private Color hexShapeColor;
  private Color[] colorOptions = {Color.red, Color.blue, Color.green, Color.yellow};
  public String fireImage = "Resources/fire.png";
  public String strawberry = "Resources/Steve the Strawberry.png";
  private char[][] spaces = new char[10][10];
  private int rows = 10;
  private int cols = 10;
  private int steveX;
  private int steveY;
  private int lastPathX;
  private int lastPathY;
  
  PaintShapes shape = new PaintShapes();
  
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
    triShapeColor = colorOptions[color.nextInt(4)];
    squShapeColor = colorOptions[color.nextInt(4)];
    pentShapeColor = colorOptions[color.nextInt(4)];
    hexShapeColor = colorOptions[color.nextInt(4)];
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
    steveX = 0;
    steveY = ySpot;
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
    page.setColor(Color.white);
    page.fillRect(0, 800, 800, 80);
    for(int i=0; i<rows; i++)
      for(int j=0; j<cols; j++){
        if(spaces[i][j] == 'b') paintBase(page, i, j);
        if(spaces[i][j] == 'f') paintFire(page, fireImage, i, j);
      }
    paintSteve(page, strawberry, steveX, steveY);
    shape.paintTriangle(page, 250, 870, 60, triShapeColor);
    shape.paintSquare(page, 330, 870, 60, squShapeColor);
    shape.paintPenta(page, 418, 870, 45, pentShapeColor);
    shape.paintHexa(page, 502, 870, 35, hexShapeColor);
    paintGrid(page);
  }
  
  public void paintGrid(Graphics2D page){
    page.setColor(Color.black);
    for(int i=0; i<800; i+=80)
      for(int j=0; j<880; j+=80)
        page.drawRect(i, j, i+80, j+80);
  }
  
  public void paintBase(Graphics2D page, int x, int y){
    page.setColor(Color.gray);
    page.fillRect((x*80)+10, (y*80)+10, 60, 60); 
  }
  
  public void paintFire(Graphics2D page, String fireImage, int x, int y){
    drawImage(page, fireImage, (x*80)+15, (y*80)+10, .1, 0);
  }  
  
  public void paintSteve(Graphics2D page, String strawberry, int x, int y){
   drawImage(page, strawberry, (x*80)+10, (y*80)+10, .5, 0); 
  }

  public static void main(String[] args){launch(800,880);}
}