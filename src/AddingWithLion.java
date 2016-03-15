import java.util.*; 
import java.awt.*; 
import javax.swing.*; 

public class AddingWithLion extends JComponentWithEvents{
  public ArrayList<String> images;
  public String steve = "Resources/Steve the Strawberry.png";
  public String tyrone = "Resources/Tyrone the Turtle.png";
  public String lionPic = "Resources/Lion the Lemon.png";
  //public String music1 = "Resources/brandenburg6.mid";
  public double rad = 0;
  Character lion; 
  Character steveo; 
  boolean up = false;
  public int maxHeight = (int) (getHeight());
  
  public void lemon(){
    lion = new Character(getImageFromFile(lionPic)); 
    lion.posX = 1000;
    lion.posY = 100; 
    lion.scale = 0.50; 
  }
  
  public void strawberry(){
    steveo = new Character(getImageFromFile(steve)); 
    steveo.posY = getHeight()/2; 
  }
  
//  public void lemonUpOrDown(){
//    
//    if (lion.posY==getHeight()/2) up = true;
//    if (lion.posY>0 && lion.posY<getHeight()/2) up = false; 
//    System.out.println(up); 
//  }
  
  public void lionBounce(){
    if (!up){
      lion.setVelocity(-10, 50); 
      lion.setAcceleration(0, 10); 
      if (lion.posY>=maxHeight) up = true; 
    }
    System.out.println(up); 
    if (up){
      lion.setVelocity(-10, -50);
      lion.setAcceleration(0, -10); 
      if (lion.posY<=0) up = false;
    }
  }
  
  public void start() {
    lemon(); 
    strawberry();  
    lionBounce(); 
    //play(music1);
    setTimerDelay(1000);
  }

  public void timerFired() {
    lion.move();
  }

//  public void mousePressed(int x, int y) {
// 
// }
//
//  public void mouseDragged(int x, int y) {
//   
//  }
//
//  public void mouseReleased(int x, int y) {
//    
//  }
//
//  public void mouseMoved(int x, int y) {
//   
//  }
//
//  private void mouseEnteredOrExited(String msg, int x, int y) {
//    
//  }
//
//  public void mouseEntered(int x, int y) {
//    
//  }
//
//  public void mouseExited(int x, int y) {
//    
//  }

  public void keyPressed(char key) {
    if (key==LEFT && steveo.posX>5) steveo.posX-=5;
    if (key==RIGHT) steveo.posX+=5; 
    if (key==DOWN) steveo.posY+=5;
    if (key==UP && steveo.posY>5) steveo.posY-=5; 
  }
  
  public void drawCharacter(Graphics2D page, Character c){
    drawImage(page, c.img, c.posX, c.posY, c.scale, c.rotation); 
  }
    

  public void paint(Graphics2D page) {
    page.setColor(Color.cyan); 
    page.fillRect(0, 0, getWidth(), getHeight()/2); 
    page.setColor(Color.green);
    page.fillRect(0, getHeight()/2, getWidth(), getHeight()); 
    drawCharacter(page, steveo); 
    drawCharacter(page, lion); 
    }
  

  public static void main(String[] args){ launch(1500, 1125); }
  
}
  
  
  
  
  