import java.util.*; 
import java.awt.*; 
import javax.swing.*; 
import java.util.Random; 

public class AddingWithLion extends JComponentWithEvents{
  public String steve = "Resources/Steve the Strawberry.png";
  public String tyrone = "Resources/Tyrone the Turtle.png";
  public String lionPic = "Resources/Lion the Lemon.png";
  public String music = "Resources/StarWars.mid"; 
  Character lion; 
  Character lion2; 
  Character steveo; 
  public int[] numbers = new int[10]; 
  int answer;
  public boolean correct = false; 
  boolean up = false;
  boolean up2 = true; 
  public int maxHeight = (int) (getHeight()-200);
  public int number1, number2; 
  
  public void lemon(){
    lion = new Character(getImageFromFile(lionPic)); 
    lion2 = new Character(getImageFromFile(lionPic)); 
    lion.posX = 600;
    lion.posY = 100; 
    lion.scale = 0.50; 
    lion2.posX = 300; 
    lion2.posY = 700; 
    lion2.scale = 0.50; 
  }
  
  public void strawberry(){
    steveo = new Character(getImageFromFile(steve)); 
    steveo.posY = getHeight()/2-110; 
  }

  
  public void lionBounce(){
    if (!up2){
      lion.setVelocity(0, 50); 
      lion.setAcceleration(0, 10); 
      if (lion.posY>=maxHeight) up2 = true; 
    }
    if (up2){
      lion.setVelocity(0, -50);
      lion.setAcceleration(0, -10); 
      if (lion.posY<=100) up2 = false;
    }
    if (lion.posX<=0)
      reset(); 
    if (lion2.posX>=getWidth())
      reset(); 
  }
  
  public void lion2Bounce(){
    if (!up){
      lion2.setVelocity(0, 50); 
      lion2.setAcceleration(0, 10); 
      if (lion2.posY>=maxHeight) up = true; 
    }
    if (up){
      lion2.setVelocity(0, -50);
      lion2.setAcceleration(0, -10); 
      if (lion2.posY<=100) up = false;
    }
    if (lion2.posX>=getWidth())
      reset(); 
    if (lion2.posX<=0)
      reset(); 
  }
  
  public void setNumbers(){
    Random random = new Random(); 
    number1 = random.nextInt(6); 
    number2 = random.nextInt(5);
    for (int i=0; i<numbers.length; i++){
      numbers[i] = i; 
    }
    answer = (number1+number2);    
  }
 
  
  public void start() {
    reset(); 
    play(music); 
  }
  
  public void reset(){
    lemon(); 
    strawberry();  
    lionBounce(); 
    lion2Bounce();
    setNumbers(); 
    setTimerDelay(750);
  }

  public void timerFired() {
    lion.move();
    lion2.move(); 
    lionBounce(); 
    lion2Bounce(); 
  }

  public void keyPressed(char key) {
    if (key==48 && answer==0){
      correct = true; 
      moveSteve();
    }
    if (key==49 && answer==1){ 
      correct = true; 
      moveSteve();
    }
    if (key==50 && answer==2){
       correct = true; 
      moveSteve();
    }
    if (key==51 && answer==3){
       correct = true; 
      moveSteve();
    }
    if (key==52 && answer==4){
       correct = true; 
      moveSteve();
    }
    if (key==53 && answer==5){
       correct = true; 
      moveSteve();
    }
    if (key==54 && answer==6){
       correct = true; 
      moveSteve();
    }
    if (key==55 && answer==7) { 
      correct = true; 
      moveSteve();
    }
    if (key==56 && answer==8){ 
       correct = true; 
      moveSteve();
    }
    if (key==57 && answer==9){
       correct = true; 
      moveSteve();
    } 
    if (key=='r'){
      correct= false;
      reset(); 
    }
    else beep(); 
  }
  
  public void moveSteve(){
    if (answer==0){
      steveo.posX= 960;
      steveo.posY= 400-75; 
    }
    if (answer==1){
      steveo.posX=960;
      steveo.posY=260-75;
    }
    if (answer==2){
      steveo.posX=960;
      steveo.posY=120-75;
    }
    if (answer==3){
      steveo.posX=960;
      steveo.posY=680-75;
    }
    if (answer==4){
      steveo.posX=960;
      steveo.posY=540-75;
    }
    if (answer==5){
      steveo.posX=1110;
      steveo.posY=400-75;
    }
     if (answer==6){
      steveo.posX=1110;
      steveo.posY=260-75;
    }
      if (answer==7){
      steveo.posX=1110;
      steveo.posY=120-75;
    }
       if (answer==8){
      steveo.posX=1110;
      steveo.posY=680-75;
    }
        if (answer==9){
      steveo.posX=1110;
      steveo.posY=540-75;
    }
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
    page.drawString(Integer.toString(number1)+ "=?",lion.posX,lion.posY);
    page.setColor(Color.black);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(number2)+"+",lion2.posX,lion2.posY);
    page.setColor(Color.yellow);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[0]),960, 400);
    page.setColor(Color.magenta);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[1]),960,260);
    page.setColor(Color.white);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[2]),960,120);
    page.setColor(Color.orange);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[3]),960,680);
    page.setColor(Color.blue);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[4]),960,540);
    page.setColor(Color.gray);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[5]),1110, 400);
    page.setColor(Color.yellow);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[6]),1110,260);
    page.setColor(Color.magenta);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[7]),1110,120);
    page.setColor(Color.white);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[8]),1110,680);
    page.setColor(Color.orange);
    page.setFont(new Font("SansSerif",Font.BOLD,104));
    page.drawString(Integer.toString(numbers[9]),1110,540);
    page.setColor(Color.blue); 
    page.setFont(new Font("SansSerif",Font.BOLD,17));
    page.drawString("Steve the Strawberry needs help picking the right number. Use the creepy lemon's clues and press a key to be a good friend and help Steve out!",10,150);
    if (correct){
      page.setColor(Color.blue); 
      page.setFont(new Font("SansSerif",Font.BOLD,30));
      page.drawString("Woah! You did it! Steve could use a little more help...Press r to help him again!",10,600);
    }
   }
  
  public static void main(String[] args){ launch(1250, 750); }
  
}
  
  
  
  