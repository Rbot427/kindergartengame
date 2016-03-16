import java.awt.*;

public class Icon {
  private Image img;
  private int posX, posY;
  private int height, width;
  private double rotation, scale;
  
  public Icon(Image image, int x, int y) {
    img = image;
    posX = x; posY =  y;
    height = img.getHeight(null);
    width = img.getWidth(null);
    scale = 1; rotation = 0;
  }
  
  public Icon(Image image) {
    this(image, 0, 0);
  }
 
  public void setPos(int x, int y) {
    posX = x; posY = y;
  }
  
  private void updateSize() {
    height = img.getHeight(null);
    width = img.getWidth(null);
  }
  
  public void setImage(Image image) {
    img = image;
    updateSize();
  }
  
  public void setScale(double newScale) {
    scale = newScale;
    height = (int)(height*scale);
    width = (int)(width*scale);
  }
  
  public void setRotation(double radians) {
    //TODO:  add test cases here
    rotation = radians;
  }
  
  public void scale(double newScale) {
    scale = newScale;
  }
  
  //Rotates the image pi/2 radians clockwise
  public void rotate() {
    setRotation(rotation - Math.PI/2);
  }
  
  //Rotates the image x radians clockwise
  public void rotate(double radians) { 
    setRotation(rotation - radians);
  }
}