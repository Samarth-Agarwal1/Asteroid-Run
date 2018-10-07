import java.awt.*;
import java.awt.geom.*;
class Asteroid {
  //Instance Variables
  private int x; //x-value of asteroid
  private int y; //y-value of asteroid
  private int diameter; //diameter of asteroid
  private int speed; //speed of asteroid
  private Color color; //color of asteroid
  private Graphics2D g2D; //initializes Graphics2D object
  
  //Constructor
  Asteroid (int x, int y, int diameter, Color color, int speed){
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
  
  //method that moves asteroid
  public void move(){
    
    if (y > 570) { //if asteroid is below JFrame off screen
      
      //randomly respawn it somewhere off screen above JFrame
      x = (int)(Math.random() * (440) + 0);
      y = (int)(Math.random() * (700) - 1000);
      
    }
    
    y += speed; //moves asteroid
  }
  
  //method that draws asteroid
  public void draw(Graphics g){
    
    g2D = (Graphics2D)g;
    g2D.setStroke(new BasicStroke(5));
    g2D.setPaint(color);
    g2D.fill(new Ellipse2D.Double(x, y, diameter, diameter));
    
  }
  
  //method that checks for collision between spaceship and asteroid
  public boolean intersects (SpaceShip ship) {
    
    return ((x < ship.getInvisX() + ship.getInvisDiameter()) && (y < ship.getInvisY() + ship.getInvisDiameter()) && 
            (x + diameter > ship.getInvisX()) && (y + diameter > ship.getInvisY()));
    
  }
}