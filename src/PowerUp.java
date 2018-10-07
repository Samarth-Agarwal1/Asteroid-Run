import java.awt.*;
import java.awt.geom.*;
class PowerUp {
  //Instance Variables
  private int x; //x-value of power-up
  private int y; //y-value of power-up
  private int diameter; //diameter of power-up
  private int speed; //speed of power-up
  private Color color; //color of power-up
  private static int points; //number of points that player has
  
  //Constructor
  PowerUp (int x, int y, int diameter, Color color, int speed){
    this.x = x;
    this.y = y;
    this.diameter = diameter;
    this.color = color;
    this.speed = speed;
  }
  
  //Setters
  public void setX ( int newX){
    x = newX;
  }
  public void setY ( int newY){
    y = newY;
  }
  public void setDiameter ( int newDiameter){
    diameter = newDiameter;
  }
  public void setColor ( Color newColor){
    color = newColor;
  }
  public void setSpeed ( int newSpeed){
    speed = newSpeed;
  }
  public void addPoints ( int addPoints){
    points += addPoints;
  }
  
  //Getters
  public int getX ( ){
    return x;
  }
  public int getY ( ){
    return y;
  }
  public int getDiameter ( ){
    return diameter;
  }
  public Color getColor ( ){
    return color;
  }
  public int getSpeed ( ){
    return speed;
  }
  public int getPoints ( ){
    return points;
  }
  
  //method that moves power-up
  public void move(){
    
    if (y > 570) { //if power-up is below JFrame off screen
      
      //randomly respawn power-up above JFrame off screen
      x = (int)(Math.random() * (460) + 0);
      y = (int)(Math.random() * (700) - 1000);
      
    }
    
    y += speed; //moves power-up
  }
  
  //method that draws power-up
  public void draw(Graphics g){
    
    Graphics2D g2D = (Graphics2D)g;
    g2D.setStroke(new BasicStroke(5));
    g2D.setPaint(color);
    g2D.fill(new Ellipse2D.Double(x, y, diameter, diameter));
    
  }
  
  //method that checks if collision has occurred between power-up and ship
  public boolean intersects (SpaceShip ship) {
    
    return ((x < ship.getInvisX() + ship.getInvisDiameter()) && (y < ship.getInvisY() + ship.getInvisDiameter()) && 
            (x + diameter > ship.getInvisX()) && (y + diameter > ship.getInvisY()));
    
  }
}