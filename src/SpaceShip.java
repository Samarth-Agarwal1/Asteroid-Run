import java.awt.*;
import java.awt.geom.Ellipse2D;
class SpaceShip {
  //Instance Variables
  private int invisX; //x-value of invisible mesh around ship
  private int invisY; //y-value of invisible mesh around ship
  private int x1; //x-value of one cross of ship
  private int y1; //y-value of one cross of ship
  private int x2; //x-value of second cross of ship
  private int y2; //y-value of second cross of ship
  private int length1; //length of one cross of ship
  private int width1; //width of one cross of ship
  private int length2; //length of second cross of ship
  private int width2; //width of second cross of ship
  private int invisDiameter; //diameter of invisible mesh around ship
  private int speedX; //speed of ship
  private Color cross; //color of ship
  private Color invis; //color of invisible mesh
  private Graphics2D g2D; //initializes Graphics2D object
  
  //Constructor
  SpaceShip (int x1, int y1, int length1, int width1, int x2, int y2, int length2, int width2, Color cross, 
             int invisX, int invisY, int invisDiameter, Color invis, int speedX){
    this.x1 = x1;
    this.y1 = y1;
    this.length1 = length1;
    this.width1 = width1;
    this.x2 = x2;
    this.y2 = y2;
    this.length2 = length2;
    this.width2 = width2;
    this.cross = cross;
    this.invisX = invisX;
    this.invisY = invisY;
    this.invisDiameter = invisDiameter;
    this.invis = invis;
    this.speedX = speedX;
  }
  
  //Setters
  public void setX1 ( int newX1){
    x1 = newX1;
  }
  public void setY1 ( int newY1){
    y1 = newY1;
  }
  public void setLength1 ( int newLength1){
    length1 = newLength1;
  }
  public void setWidth1 ( int newWidth1){
    width1 = newWidth1;
  }
  public void setX2 ( int newX2){
    x2 = newX2;
  }
  public void setY2 ( int newY2){
    y2 = newY2;
  }
  public void setLength2 ( int newLength2){
    length2 = newLength2;
  }
  public void setWidth2 ( int newWidth2){
    width2 = newWidth2;
  }
  public void setCross ( Color newCross){
    cross = newCross;
  }
  public void setInvisX ( int newInvisX){
    invisX = newInvisX;
  }
  public void setInvisY ( int newInvisY){
    invisY = newInvisY;
  }
  public void setInvisDiameter ( int newInvisDiameter){
    invisDiameter = newInvisDiameter;
  }
  public void setInvis ( Color newInvis){
    invis = newInvis;
  }
  public void setSpeedX ( int newSpeedX){
    speedX = newSpeedX;
  }
  
  //Getters
  public int getX1 ( ){
    return x1;
  }
  public int getY1 ( ){
    return y1;
  }
  public int getLength1 ( ){
    return length1;
  }
  public int getWidth1 ( ){
    return width1;
  }
  public int getX2 ( ){
    return x2;
  }
  public int getY2 ( ){
    return y2;
  }
  public int getLength2 ( ){
    return length2;
  }
  public int getWidth2 ( ){
    return width2;
  }
  public Color getCross ( ){
    return cross;
  }
  public int getInvisX ( ){
    return invisX;
  }
  public int getInvisY ( ){
    return invisY;
  }
  public int getInvisDiameter ( ){
    return invisDiameter;
  }
  public Color getInvis ( ){
    return invis;
  }
  public int getSpeedX ( ){
    return speedX;
  }
  
  //method that moves ship left
  public void moveLeft( ){
    invisX -= speedX;
    x1 -= speedX;
    x2 -= speedX;
  }
  
  //method that moves ship right
  public void moveRight( ){
    invisX += speedX;
    x1 += speedX;
    x2 += speedX;
  }
  
  //method that draws ship
  public void draw(Graphics g){
    
    g2D = (Graphics2D)g;
    
    g2D.setStroke(new BasicStroke(5));
    g2D.setPaint(cross);
    
    g2D.fill(new Ellipse2D.Double(x1, y1, length1, width1));
    g2D.fill(new Ellipse2D.Double(x2, y2, length2, width2));
    
    g2D.setPaint(invis);
    
    g2D.fill(new Ellipse2D.Double(invisX, invisY, invisDiameter, invisDiameter));
  }
  
  //method that checks to see if ship is outside boundary or not
  public boolean checkBoundary( ) {
    
    return ((invisX < 0) || (invisX + invisDiameter > 500));
    
  }
}