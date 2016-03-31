import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PaintShapes extends JComponentWithEvents{
  
  public void paintTriangle(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+(sideLength/2), x1+sideLength};
    int[] y = {y1, (int)(y1-(sideLength*Math.sin(Math.PI/3))), y1};
    int n = 3;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.drawLine(x1, y1, x1+(sideLength/2), (int)(y1-(sideLength*Math.sin(Math.PI/3))));
    page.drawLine(x1+(sideLength/2), (int)(y1-(sideLength*Math.sin(Math.PI/3))), x1+sideLength, y1);
    page.drawLine(x1, y1, x1+sideLength, y1);
  }
  
  public void paintSquare(Graphics2D page, int x1, int y1, int sideLength, Color color){
    page.setColor(color);
    int[] x = {x1, x1+sideLength, x1+sideLength, x1};
    int[] y = {y1, y1, y1-sideLength, y1-sideLength};
    int n = 4;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.drawLine(x1, y1, x1+sideLength, y1);
    page.drawLine(x1+sideLength, y1, x1+sideLength, y1-sideLength);
    page.drawLine(x1+sideLength, y1-sideLength, x1, y1-sideLength);
    page.drawLine(x1, y1-sideLength, x1, y1);
  }
  
  public void paintPenta(Graphics2D page, int x1, int y1, int sideLength, Color color){
    int xpoint1 = x1;
    int xpoint2 = (int)(x1+sideLength-(Math.cos((3*Math.PI)/5)*sideLength));
    int xpoint3 = x1+(sideLength/2);
    int xpoint4 = (int)(x1+(Math.cos((3*Math.PI)/5)*sideLength));
    int xpoint5 = x1+sideLength;
    
    int ypoint1 = y1;
    int ypoint2 = (int)(y1-(Math.sin((2*Math.PI)/3)*sideLength));
    int ypoint3 = (int)(y1-(Math.sin((2*Math.PI)/3)*sideLength)-(Math.sin(Math.PI/5)*sideLength));
    int ypoint4 = (int)(y1-(Math.sin((2*Math.PI)/3)*sideLength));
    int ypoint5 = y1;
    
    page.setColor(color);
    int[] x = {xpoint1, xpoint5, xpoint2, xpoint3, xpoint4};
    int[] y = {ypoint1, ypoint5, ypoint2, ypoint3, ypoint4};
    int n = 5;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.drawLine(xpoint1, ypoint1, xpoint5, ypoint5);
    page.drawLine(xpoint5, ypoint5, xpoint2, ypoint2);
    page.drawLine(xpoint2, ypoint2, xpoint3, ypoint3);
    page.drawLine(xpoint3, ypoint3, xpoint4, ypoint4);
    page.drawLine(xpoint4, ypoint4, xpoint1, ypoint1);
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
    int ypoint3 = (int)(y1-(Math.sin(Math.PI/3)*sideLength));
    int ypoint4 = (int)(y1-2*(Math.sin(Math.PI/3)*sideLength));
    int ypoint5 = (int)(y1-2*(Math.sin(Math.PI/3)*sideLength));
    int ypoint6 = (int)(y1-(Math.sin(Math.PI/3)*sideLength));
    
    page.setColor(color);
    int[] x = {xpoint1, xpoint2, xpoint6, xpoint5, xpoint4, xpoint3};
    int[] y = {ypoint1, ypoint2, ypoint6, ypoint5, ypoint4, ypoint3};
    int n = 6;
    Polygon p = new Polygon(x, y, n);
    page.fillPolygon(p);
    page.setColor(Color.black);
    page.drawLine(xpoint1, ypoint1, xpoint2, ypoint2);
    page.drawLine(xpoint2, ypoint2, xpoint6, ypoint6);
    page.drawLine(xpoint6, ypoint6, xpoint5, ypoint5);
    page.drawLine(xpoint5, ypoint5, xpoint4, ypoint4);
    page.drawLine(xpoint4, ypoint4, xpoint3, ypoint3);
    page.drawLine(xpoint3, ypoint3, xpoint1, ypoint1);
  }
}
