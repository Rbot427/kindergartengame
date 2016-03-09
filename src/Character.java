import java.awt.*;

public class Character extends Icon {
  public int velocityX, velocityY;
  public int accelX, accelY; // Acceleration 
  
  public Character(Image image, int posX, int posY) {
    super(image, posX, posY);
  }
  
  public void defaults() {
    this.velocityX = 0; this.velocityY = 0;
    this.accelX = 0; this.accelY = 0;
  }
  
  public Character(Image image) {
    super(image);
  }
  
  public void setVelocity(int x, int y) {
    this.velocityX = x;
    this.velocityY = y;
  }
                          
  public void setAcceleration(int x, int y) {
    this.accelX = x;
    this.accelY = y;
  }
  
  public void move() {
    setPos(this.posX + this.velocityX, this.posY + this.velocityY);
    setVelocity(this.velocityX + this.accelX, this.velocityY + this.accelY);
  }
  
  public void updateVelocity() {
     setVelocity(this.velocityX + this.accelX, this.velocityY + this.accelY);
  }
}