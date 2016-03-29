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
  private Color[] colorOptions = {Color.red, Color.blue, Color.green, Color.cyan, Color.magenta, Color.black};
  public String fireImage = "Resources/fire.png";
  public String strawberry = "Resources/Steve the Strawberry.png";
  private char[][] spaces = new char[10][10];
  private char[][] steve = new char[10][10];
  private int rows = 10;
  private int cols = 10;
  private int steveX;
  private int steveY;
  private int lastPathX;
  private int lastPathY;
  private char shapeSelection = ' ';
  private int ySpot;
  private boolean select = true;
  private boolean place = false;
  
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
    triShapeColor = colorOptions[color.nextInt(6)];
    squShapeColor = colorOptions[color.nextInt(6)];
    pentShapeColor = colorOptions[color.nextInt(6)];
    hexShapeColor = colorOptions[color.nextInt(6)];
  }
  
  public void placeFire(){
    Random firePosition = new Random();
    int fires = firePosition.nextInt(5)+10;
    for(int i=0; i<fires; i++){
      int xFire = firePosition.nextInt(9)+1;
      int yFire = firePosition.nextInt(10);
      spaces[xFire][yFire] = 'f';
    }
  }
  
  public void placeBases(){
    Random basePosition = new Random();
    ySpot = basePosition.nextInt(10);
    spaces[0][ySpot] = 'b';
    steveX = 0;
    steveY = ySpot;
    steve[steveX][steveY] = 's';
    ySpot = basePosition.nextInt(10);
    spaces[9][ySpot] = 'b';
  }
  
  public void start(){
    randomNumber();
    randomColor();
    placeFire();
    placeBases();
  }
  
  public void reset(){
    for(int i=0; i<10; i++)
      for(int j=0; j<10; j++){
        steve[i][j] = ' ';
        spaces[i][j] = ' ';
      }
    start();
  }
  
  public void timerFired(){
  }
  
  public void keyPressed(char key){
    if(key == 'r') reset();
  }
  
  public void mousePressed(int x, int y){
    if(select){
      shapeSelection(x, y);
    }
    else if(place && shapeSelection != 0){
      decideToPlace(x, y);
    }
  }
  
  public void shapeSelection(int x, int y){
      if((x>250 && x<330) && (y>790 && y<870)) shapeSelection = 't';
      else if((x>330 && x<410) && (y>790 && y<870)) shapeSelection = 's';
      else if((x>418 && x<500) && (y>790 && y<870)) shapeSelection = 'p';
      else if((x>500 && x<580) && (y>790 && y<870)) shapeSelection = 'h';
      else shapeSelection = 0;
      if(shapeSelection != 0){
        select = false;
        place = true;
      }
  }
  
  public void decideToPlace(int x, int y){
      lastPathX = (int)x/80;
      lastPathY = (int)y/80;
      if(lastPathX<10 && lastPathY<10){
        if((lastPathX == steveX+1 && lastPathY == steveY) || (lastPathX == steveX && (lastPathY == steveY+1 || lastPathY == steveY-1))){
          if(shapeSelection == 't' && sides == 3 || shapeSelection == 's' && sides == 4 || shapeSelection == 'p' && sides == 5 || shapeSelection == 'h' && sides == 6){
            spaces[lastPathX][lastPathY] = shapeSelection;
            steve[steveX][steveY] = ' ';
            steveX = lastPathX;
            steveY = lastPathY;
            steve[steveX][steveY] = 's';
            randomNumber();
          }
          else spaces[lastPathX][lastPathY] = 'f';
        }
        else spaces[lastPathX][lastPathY] = 'f';
      }
      select = true;
      place = false;
      if((steveX==9 && (steveY+1 == ySpot || steveY-1 == ySpot)) || (steveY == ySpot && steveX+1 == 9)){
        steve[steveX][steveY] = ' ';
        steveX = 9;
        steveY = ySpot;
        steve[steveX][steveY] = 's';
      }
  }
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Paint methods
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
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
        if(spaces[i][j] == 't') shape.paintTriangle(page, (i*80)+10, ((j+1)*80)-10, 60, triShapeColor);
        if(spaces[i][j] == 's') shape.paintSquare(page, (i*80)+10, ((j+1)*80)-10, 60, squShapeColor);
        if(spaces[i][j] == 'p') shape.paintPenta(page, (i*80)+18, ((j+1)*80)-6, 45, pentShapeColor);
        if(spaces[i][j] == 'h') shape.paintHexa(page, (i*80)+22, ((j+1)*80)-8, 35, hexShapeColor);
        if(steve[i][j] == 's') paintSteve(page, strawberry, i, j);
      }
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