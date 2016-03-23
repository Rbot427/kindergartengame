import java.util.*; 
import java.awt.*; 
import javax.swing.*; 
import java.util.Random; 

public class AddingWithLion extends JComponentWithEvents{
  //public ArrayList<String> images;
  public String steve = "Resources/Steve the Strawberry.png";
  public String tyrone = "Resources/Tyrone the Turtle.png";
  public String lionPic = "Resources/Lion the Lemon.png";
  //public String music1 = "Resources/brandenburg6.mid";
  public double rad = 0;
  Character lion; 
  Character lion2; 
  Character steveo; 
  Character lemonPoop; 
  int lemonPoopX = lion.posX;
  int lemonPoopY = lion.posY; 
  int lemonPoopRadius = 10; 
  int lemonPoopSpeed = 5; 
  boolean up = false;
  boolean up2 = true; 
  public int maxHeight = (int) (getHeight()-200);
  public int number1, number2, number3, number4, number5, number6, number7; 
  
  public void lemon(){
    lion = new Character(getImageFromFile(lionPic)); 
    lion2 = new Character(getImageFromFile(lionPic)); 
    lion.posX = 1000;
    lion.posY = 100; 
    lion.scale = 0.50; 
    lion2.posX = 100; 
    lion2.posY = 900; 
    lion2.scale = 0.50; 
  }
  
  public void strawberry(){
    steveo = new Character(getImageFromFile(steve)); 
    steveo.posY = getHeight()/2-110; 
  }
  
//  public void lemonUpOrDown(){
//    
//    if (lion.posY==getHeight()/2) up = true;
//    if (lion.posY>0 && lion.posY<getHeight()/2) up = false; 
//    System.out.println(up); 
//  }
  
  public void lionBounce(){
    if (!up2){
      lion.setVelocity(-10, 50); 
      lion.setAcceleration(0, 10); 
      if (lion.posY>=maxHeight) up2 = true; 
    }
    System.out.println(up); 
    if (up2){
      lion.setVelocity(-10, -50);
      lion.setAcceleration(0, -10); 
      if (lion.posY<=0) up2 = false;
    }
    if (lion.posX<=0)
      reset(); 
    if (lion2.posX>=getWidth())
      reset(); 
  }
  
  public void lion2Bounce(){
    if (!up){
      lion2.setVelocity(10, 50); 
      lion2.setAcceleration(0, 10); 
      if (lion2.posY>=maxHeight) up = true; 
    }
    //System.out.println(up); 
    if (up){
      lion2.setVelocity(10, -50);
      lion2.setAcceleration(0, -10); 
      if (lion2.posY<=0) up = false;
    }
    if (lion2.posX>=getWidth())
      reset(); 
    if (lion2.posX<=0)
      reset(); 
  }
  
  public void setNumbers(){
    Random random = new Random(); 
    number1 = random.nextInt(6); 
    number2 = random.nextInt(6);
    number3 = random.nextInt(6);
    number4 = random.nextInt(6); 
    number5 = random.nextInt(6); 
    number6 = random.nextInt(6);
    number7 = random.nextInt(6);
  }
  
  public void lemonPoop(){
    if (up){
      lemonPoopY+=lemonPoopSpeed;
    }
//      lemonPoop.posX = lion.posX;
//      lemonPoop.posY = lion.posY; 
//      lemonPoop.setVelocity(0, 75); 
//      lemonPoop.setAcceleration(0, 10); 
    }
      
    
//  public void steveoFall(){
//    while(steveo.posY<=getHeight()/2){
//      steveo.posY+=75;
//    }
//  }
//  public void steveoJump(){
//    steveo.setVelocity(0, -75); 
//    steveo.setAcceleration(0, -10); 
//    steveoFall();
//  }
 
  
  public void start() {
    reset(); 
    setNumbers(); 
  }
  
  public void reset(){
    lemon(); 
    strawberry();  
    lionBounce(); 
    lion2Bounce(); 
    //play(music1);
    setTimerDelay(750);
  }

  public void timerFired() {
    lion.move();
    lion2.move(); 
    lionBounce(); 
    lion2Bounce(); 
    lemonPoop(); 
    //setNumbers(); 
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
//    if (key==SPACE) steveoJump();
    if (key==DOWN) steveo.posY+=5;
    //if (key==UP && steveo.posY>5) steveo.posY-=5; 
    if (key==UP) steveo.posY-=5;
  }
  
  public void drawCharacter(Graphics2D page, Character c){
    drawImage(page, c.img, c.posX, c.posY, c.scale, c.rotation); 
  }
    

  public void paint(Graphics2D page){
    page.setColor(Color.cyan); 
    page.fillRect(0, 0, getWidth(), getHeight()/2); 
    page.setColor(Color.green);
    page.fillRect(0, getHeight()/2, getWidth(), getHeight()); 
    page.setColor(Color.yellow);
    page.fillRect(1400, getHeight()/2-100, 100, 100); 
    drawCharacter(page, steveo); 
    drawCharacter(page, lion); 
    drawCharacter(page, lion2); 
    page.setColor(Color.red);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number1),80,120);
    page.setColor(Color.blue);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number2),300, 905);
    page.setColor(Color.black);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number3),520,120);
    page.setColor(Color.magenta);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number4),740,905);
    page.setColor(Color.white);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number5),960,120);
    page.setColor(Color.orange);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number6),1080,905);
    page.setColor(Color.green);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number7),1300,120);
    if (lemonPoopY!= lion.posY){
      page.setColor(Color.darkGray); 
      page.fillOval(lemonPoopX, lemonPoopY, lemonPoopRadius, lemonPoopRadius); 
    }
      
    }
  

  public static void main(String[] args){ launch(1500, 1125); }
  
}
  
  
  
  
  