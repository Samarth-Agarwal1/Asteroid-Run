import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;

public class AsteroidRun extends JFrame implements ActionListener{
  
  public AsteroidRunCanvas canvas; //creates canvas to incorporate AsteroidRunCanvas class into this one
  public Timer collisionTimer; //initializes timer to check for collisions
  public boolean checkA; //checks for asteroid collisions
  public boolean checkP; //checks for power-up collisions
  public boolean checkBounds; //checks to see if spaceship is out of bounds
  public int countCheck; //acts as flag to know when game over message dialogue has been shown to player
  public JFrame title; //creates title page window
  public JButton start; //button for starting game
  public JButton instructions; //button for instructions
  public JLabel background; //holds background picture
  public JLabel gameName; //name of game
  public JPanel main; //panel for GUI components in title JFrame
  
  public static void main(String[] args) {
    
    javax.swing.SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        
        new AsteroidRun(); //creates the game window
        
      }
    });
  }
  
  public AsteroidRun() {
    
    title = new JFrame(); //creates JFrame for title screen
    
    start = new JButton("START"); //creates start button
    instructions = new JButton("INSTRUCTIONS"); //creates instructions button
    
    start.setPreferredSize(new Dimension(500, 30)); //sets size of start button
    instructions.setPreferredSize(new Dimension(500, 30)); //sets size of instructions button
    
    start.addActionListener(this); //adds action listener to start button
    instructions.addActionListener(this); //adds action listener to instructions button
    
    start.setActionCommand("start"); //adds action command to start button
    instructions.setActionCommand("instructions"); //adds action command to instructions button
    
    main = new JPanel(); //creates panel for title screen components
    
    background = new JLabel(new ImageIcon("Background.jpg")); //creates background label
    
    //sets game name and font
    gameName = new JLabel("Asteroid Run");
    gameName.setFont(new Font("Asteroid Run", Font.PLAIN, 24));
    
    //adds components to title screen panel
    main.add(gameName);
    main.add(start);
    main.add(instructions);
    main.add(background);
    
    title.add(main); //adds title screen panel to title screen JFrame
    
    title.setSize(500, 550); //sets size of title screen JFrame
    title.setResizable(false); //makes sure that title screen JFrame can't be resized
    title.setVisible(true); //makes title screen JFrame visible
    title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //title screen JFrame closes when close button is pressed
    title.setLocationRelativeTo(null); //makes title screen JFrame show up in middle of screen
    
    canvas = new AsteroidRunCanvas();
    getContentPane().add(canvas);
    
    setTitle("Asteroid Run        Points: 0"); //sets title of JFrame
    setSize(500, 550); //sets size of JFrame
    setResizable(false); //makes sure that JFrame cannot be resized
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame closes when close button is pressed in top right corner
    setLocationRelativeTo(null); //makes JFrame show up on middle of screen
    
    collisionTimer = new Timer (1, new ActionListener(){
      
      public void actionPerformed (ActionEvent evt){
        
        for (int c = 0; c < 2; c++) {
          for (int i = 0; i < 5; i++) {
            
            checkA = canvas.asteroid[c][i].intersects(canvas.ship); //checks to see if collision with asteroid has occurred
            
            if (checkA && countCheck == 0) { //if collision with asteroid has occurred and game over message hasn't been shown
              
              JOptionPane.showMessageDialog(null, "You got hit by an asteroid!\nGame Over!\nPoints: "
                                              +canvas.powerUp[0][0].getPoints(), "GAME OVER", JOptionPane.INFORMATION_MESSAGE); //game over message
              setVisible(false); //closes JFrame
              countCheck = 1; //acts as flag so that program knows not to display this message again
              
            }
          }
        }
        
        for (int c = 0; c < 2; c++) {
          for (int i = 0; i < 3; i++) {
            
            checkP = canvas.powerUp[c][i].intersects(canvas.ship); //checks to see if collision with power-up has occurred
            
            if (checkP) { //if collision with power-up has occurred
              
              canvas.powerUp[c][i].addPoints(10); //add 10 points
              
              setTitle("Asteroid Run        Points: "+canvas.powerUp[0][0].getPoints()); //update points shown on JFrame
              
              //makes power respawn on top of JFrame
              canvas.x = (int)(Math.random() * (440) + 0);
              canvas.y = (int)(Math.random() * (300) - 1000);
              
              canvas.powerUp[c][i].setX(canvas.x);
              canvas.powerUp[c][i].setY(canvas.y);
              
            }
          }
        }
        
        checkBounds = canvas.ship.checkBoundary(); //checks to see if ship is out of bounds
        
        while (checkBounds && canvas.ship.getInvisX() < 0) { //while ship is out of bounds to the left side of the JFrame
          
          //repositions ship so that it can't leave JFrame
          canvas.ship.setInvisX(0);
          canvas.ship.setX1(11);
          canvas.ship.setX2(0);
          
        }
        
        while (checkBounds && canvas.ship.getInvisX() + canvas.ship.getInvisDiameter() > 500) { //while ship is out of bounds on right side of JFrame
          
          //repositions ship so that it can't leave JFrame
          canvas.ship.setInvisX(500 - canvas.ship.getInvisDiameter());
          canvas.ship.setX1(500 - 19);
          canvas.ship.setX2(500 - canvas.ship.getLength2());
          
        }
      }
    });
    collisionTimer.start();
    
    addKeyListener (new KeyAdapter() {
      
      public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT){ //if left arrow key is pressed
          
          //ship moves left
          canvas.ship.moveLeft();
          
        }
        
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){ //if right arrow key is pressed
          
          //ship moves right
          canvas.ship.moveRight();
          
        }
      }
    });
  }
  
  public void actionPerformed (ActionEvent e) {
    
    if (e.getActionCommand().equals("start")) {
      
      title.setVisible(false); //makes title screen not visible
      setVisible(true); //makes game screen visible
      
    }
    
    else if (e.getActionCommand().equals("instructions")) {
      
      //shows instructions
      JOptionPane.showMessageDialog(null, "Guide your spaceship through space using the left and right arrow keys.\nAvoid the asteroids (gray spheres) and collect as many power-ups (yellow spheres) as possible.\nEach power-up collected gives you 10 points.\nYou only have 1 life, so be careful!", "INSTRUCTIONS", JOptionPane.INFORMATION_MESSAGE);
      
    }
  }
}

class AsteroidRunCanvas extends JComponent {
  
  public SpaceShip ship; //creates ship that user controls
  public Asteroid asteroid[][] = new Asteroid[2][5]; //creates asteroids
  public PowerUp powerUp[][] = new PowerUp[2][3]; //creates power-ups
  public Color gray; //stores gray color of asteroid
  public Color white; //stores white color of ship
  public Color yellow; //stores yellow color of power-up
  public Color invis; //stores invisible color of invisible mesh around ship
  public int x; //stores x-coordinate of asteroids and power-ups
  public int y; //stores y-coordinate of asteroids and power-ups
  public int speed; //stores value of speed of asteroids and power-ups
  public int diameter; //stores diameter of asteroids
  public boolean close; //checks to see if JFrame should be closed or not
  public Timer myTimer; //initializes a timer to repaint the frame
  public Graphics2D g2; //creates Graphics2D object to convert from Graphics to Graphics2D
  public Graphics2D g3; //creates Graphics2D object to convert from Graphics to Graphics2D
  public ImageIcon img; //stores background image
  
  public AsteroidRunCanvas() {
    
    myTimer = new Timer (1, new ActionListener(){
      public void actionPerformed (ActionEvent evt){
        repaint(); //calls paint method
      }
    });
    myTimer.start();
    
    white = new Color(255, 255, 255); //white color for ship
    invis = new Color(56, 255, 34, 0); //makes invisble circle around ship to act as collision detector
    
    ship = new SpaceShip(265, 400, 8, 30, 254, 410, 30, 8, white, 254, 400, 30, invis, 10); //calls ship constructor
    
    speed = 1; //speed of asteroid
    
    for (int c = 0; c < 2; c++) {
      for (int i = 0; i < 5; i++) {
        
        gray = new Color(181, 181, 161); //color gray for asteroid
        diameter = (int)(Math.random() * (30) + 30); //random diameter for asteroid
        
        //randomly makes asteroid spawn somewhere above JFrame view
        x = (int)(Math.random() * (440) + 0);
        y = (int)(Math.random() * (300) - 1000);
        
        asteroid[c][i] = new Asteroid(x, y, diameter, gray, speed); //calls asteroid constructor
        
      }
      speed += 1; //increases speed by 1
    }
    
    speed = 1; //speed of power-up
    
    for (int c = 0; c < 2; c++) {
      for (int i = 0; i < 3; i++) {
        
        yellow = new Color(255, 255, 0); //color yellow for power-up
        
        //randomly makes power-up spawn somewhere above JFrame view
        x = (int)(Math.random() * (470) + 0);
        y = (int)(Math.random() * (500) - 1000);
        
        powerUp[c][i] = new PowerUp(x, y, 15, yellow, speed); //calls power-up constructor
        
      }
      speed += 1; //increases speed by 1
    }
  }
  
  public void paint(Graphics g) {
    
    g2 = (Graphics2D)g; //makes Graphics2D object
    
    drawBackground(g2); //calls method that draws background
    ship.draw(g); //calls method that draws ship
    
    for (int c = 0; c < 2; c++) {
      for (int i = 0; i < 5; i++){
        
        asteroid[c][i].move(); //calls method that moves asteroid
        asteroid[c][i].draw(g2); //calls method that draws asteroid
        
      }
    }
    
    for (int c = 0; c < 2; c++) {
      for (int i = 0; i < 3; i++){
        
        powerUp[c][i].move(); //calls method that moves power-up
        powerUp[c][i].draw(g2); //calls method that draws power-up
        
      }
    }
  }
  
  public void drawBackground (Graphics g2) {
    
    g3 = (Graphics2D)g2; //creates Graphics2D object
    
    img = new ImageIcon("Background.jpg"); //creates image object to set as background
    g3.drawImage(img.getImage(), 0, 0, this); //sets image as background
    
  }
}