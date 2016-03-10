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
  
  public void lemon(){
    lion = new Character(getImageFromFile(lionPic)); 
  }
  public void start() {
    //play(music1);
    setTimerDelay(1);
    images = new ArrayList<>();
    //images.add(steve);
    //images.add(tyrone);
    images.add(lion);
  }
  
  
  

  public void timerFired() {
    //rad += 0.001;
  }

  public void mousePressed(int x, int y) {
    //images.add(tyrone);
  }

  public void mouseDragged(int x, int y) {
   
  }

  public void mouseReleased(int x, int y) {
    
  }

  public void mouseMoved(int x, int y) {
   
  }

  private void mouseEnteredOrExited(String msg, int x, int y) {
    
  }

  public void mouseEntered(int x, int y) {
    
  }

  public void mouseExited(int x, int y) {
    
  }

  public void keyPressed(char key) {
    
  }

  public void paint(Graphics2D page) {
    for(int i = 0; i < images.size(); i++) {
      drawImage(page, images.get(i), (int)(Math.random()*getWidth()), (int)(Math.random()*getHeight()), 1, rad);
    }
  }

  
  
  
  
  
  public static void main(String[] args){ launch(800, 600); }
  
}
  
  
  
  
  