import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class TrickGame extends JComponentWithEvents {
  public boolean gameOver = false;
  public static int wWidth = 800, wHeight = 600;
  public int rowHeight = 100;
  public int cSpeed = 10;
  Character tyrone;
  Character ball;
  int score = 1;
  String tyroneImage = "Resources/Tyrone the Turtle Skateboard.png";
  String ballImage = "Resources/Ball.png";
  String backgroundImage = "Resources/Background.png";
  Image background;
  boolean ballDidHit = false, spaceHeld = false, arrowHeld = false;
  boolean tyroneAirborne = false;
  boolean correctResponse = false;
  Color[] rainbow = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink};
  int rainbowIndex = 0;
  String response = "";
  
  //TODO:  Organize methods
  
  public void start() {
    init();
    setTimerDelay(25);
  }
  
  public void init() {
    frame.setTitle("Trick Master Tyrone (LITE)");
    background = getImageFromFile(backgroundImage);
    tyrone = new Character(getImageFromFile(tyroneImage));
    //tyrone.setVelocity(cSpeed,0);
    tyrone.setScale(0.5);
    tyrone.setPos(wWidth/2 - tyrone.width/2, wHeight - tyrone.height);
    ball = new Character(getImageFromFile(ballImage), 200, 200);
    ball.setVelocity(5,5);
    ball.setAcceleration(ball.accelX, 1);
    gameOver = false;
    correctResponse = false;
    response = "";
    score = 1;
    announceScore();
  }

  public void timerFired() {
    if(tyrone.posY+tyrone.height > wHeight) {
      tyroneAirborne = false;
      landCharacter(tyrone);
    }
    tyrone.move();
    //if(tyrone.posX + tyrone.width > wWidth || tyrone.posY < 0)
      //nextRow(tyrone);
    checkBallPos(ball);
    handleCollision(tyrone, ball);
    ball.move();
    rainbowIndex = (rainbowIndex + 1)%rainbow.length;
    //tyrone.rotate(0.2);
  }
  
  public void nextRow(Character c) {
    c.setPos(c.posX, c.posY + rowHeight);
    c.setVelocity(c.velocityX*(-1), c.velocityY);
  }
  
  public void handleCollision(Character c1, Character c2) {
    if(checkCollision(c1, c2)) {
      if(!ballDidHit) {
         cBounce(c2, c1.velocityY);
         addScore();
         ballDidHit = true;
      }
    }
    else 
      ballDidHit = false;
  }
  
  public void keyReleased(char key) {
    if(key == ' ') spaceHeld = false;
    else if(key == LEFT) arrowHeld = false;
  }
  
  public void announceScore() {
    play("Resources/" + score + ".wav");
  }
  
  public void addScore() {
    score++;
    announceScore();
  }
  
  public boolean checkCollision(Character c1, Character c2) {
    int x1 = c1.posX + c1.width/2, x2 = c2.posX + c2.width/2;
    int y1 = c1.posY + c1.height/2, y2 = c2.posY + c2.height/2;
      return Math.abs(x1 - x2) <= c1.width/2 + c2.width/2 &&
             Math.abs(y1 - y2) <= c1.height/2 + c2.height/2;
  }
  
  public void checkBallPos(Character ball) {
    if(ball.posX + ball.width >= wWidth) {
      ball.setPos(wWidth-ball.width-1, ball.posY);
      ball.setVelocity(ball.velocityX*(-1), ball.velocityY);
    }
    else if(ball.posX <= 0) {
      ball.setPos(1, ball.posY);
      ball.setVelocity(ball.velocityX*(-1), ball.velocityY);
    }
    if(ball.posY + ball.height > wHeight) {
      gameOver = true;
      /*ball.setPos(ball.posX, wHeight-ball.height-1);
      ball.setVelocity(ball.velocityX, (int)(ball.velocityY * (-1) *(0.9)));*/
    }
  }
  
  public void keyPressed(char key) {
    if(!gameOver) {
    if(key == ' ' && tyroneAirborne == false) {cJump(tyrone); tyroneAirborne = true;}
    else if(key == LEFT) tyrone.setPos(tyrone.posX - 5, tyrone.posY);
    else if(key == RIGHT) tyrone.setPos(tyrone.posX + 5, tyrone.posY);
    }
    else
    {
      if(key == ENTER) submitResponse();
      if(java.lang.Character.isDigit(key)) response += key;
      if(key == SPACE && correctResponse == true) init();
    }
  }
  
  public void submitResponse() {
    int number = Integer.parseInt(response);
    if(number == score-1) 
      correctResponse = true;
    else {
      correctResponse = false;
      response = "";
    }
  }
  
  public void landCharacter(Character c) {
    cLand(c);
    c.setPos(c.posX, wHeight-c.height);
  }
  
  public void cBounce(Character c, int v) {
    c.setVelocity(c.velocityX, (int)((c.velocityY*(-1) + v)));
  }
  
  public void cLand(Character c) {
    c.setVelocity(c.velocityX, 0);
    c.setAcceleration(c.accelX, 0);
  }
  
  public void cJump(Character c) {
    c.setVelocity(c.velocityX, -20);
    c.setAcceleration(c.accelX, 1);
  }

  public void drawCharacter(Graphics2D page, Character c) {
    drawImage(page, c.img, c.posX, c.posY, c.scale, c.rotation);
  }
  
  public void paint(Graphics2D page) {
    if(!gameOver) {
    page.setColor(Color.blue); page.fillRect(0, 0, wWidth, wHeight);
    drawImage(page, background, 0, 0, 1, 0);
    drawCharacter(page, tyrone);
    drawCharacter(page, ball);
    page.setFont(new Font("Ariel", Font.BOLD, 20)); page.drawString("Hits: " + score, wWidth/2 - getStringWidth(page, page.getFont(), "Hits: " + score), 20);
    page.setColor(rainbow[rainbowIndex]); page.fillRect(ball.posX + (ball.width-((wHeight-ball.posY)/5))/2, wHeight-3, ((wHeight-ball.posY)/5), 3);
    }
    else
    {
      //I understand it is generally frowned upon to use literals, but time is scarce
      page.setFont(new Font("Ariel", Font.BOLD, 20));
      page.drawString("Impressive!  You got " + score + " hits!", 0, 20);
      page.drawString("What number comes before " + score + "?", 0, 40);
      page.drawString("Type your response: " + response, 0, 60);
      if(correctResponse) {
        page.drawString("Correct!  Press space to play again", 0, 80);
      }
      else {
        page.drawString("Nope!  Try again", 0, 80);
      }
    }
  }
  
  public static void main(String[] args) { launch(wWidth, wHeight); }
}