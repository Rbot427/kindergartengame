import java.awt.*;

public class Icon {
  private Image img;
  private int posX, posY;
  private int size;
  
  public Icon(Image image, int x, int y) {
    img = image;
    posX = x; posY =  y;
  }
  
  public Icon(Image image) {
    Icon(image, 0, 0);
  }
  
  
}