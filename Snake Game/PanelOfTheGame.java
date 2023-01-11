import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class PanelOfTheGame extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25; //this is to determine how big we want the items in the game to be
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75; //the higher the delay the slower the game is
	final int x[] = new int [GAME_UNITS]; //x coordinates
	final int y[] = new int [GAME_UNITS]; //y coordinates
	int bodyParts = 6;
	int applesEaten = 0;
	int appleX;
	int appleY;
	char direction = 'R';//R for right, L for left, D for down, U for up
	boolean running = false;
	Timer  timer;
	Random random;
	
	
	
	
    PanelOfTheGame(){
		
    	random = new Random();
    	this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //preferred size for game panel
    	this.setBackground(Color.black); //background color
    	this.setFocusable(true); //sets focusability
    	this.addKeyListener(new MyKeyAdapter());
    	GameStarts();
    	
	}

    public void GameStarts()
    {
    	newApple(); //calls newApple(); method to create an apple on the screen
    	running = true; 
    	timer = new Timer(DELAY, this);
    	timer.start();
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	draw(g);
    	
    }
    
    public void draw (Graphics g)
    {
    	if (running)
    	{
    		
    	/* This was to check to make a grid so we can see the boxes and estimate where each item goes and 
    	 * how much space each item takes from the box
    	for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++)
    	{
    		g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
    		g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
    	}
    	*/
    		
    	g.setColor(Color.blue);
    	g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //UNIT_SIZE indicates how large apple is
    	
    	for (int i = 0; i < bodyParts; i++) //for each of the body parts of the snake
    	{
    		if (i == 0)
    		{
    			g.setColor(Color.RED);
    			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
    			
    			
    		}
    		
    		else
    		{
    			g.setColor(Color.GREEN);
    			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
    		}
    	}
    	
    	g.setColor(Color.YELLOW);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
    	FontMetrics fm = getFontMetrics(g.getFont()); //to set text in the middle of the screen
    	g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - fm.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
    	
    	}
    	
    	else
    	{
    	GG(g);	
    	
    	
    	}
    	
    	
    }
    
    public void newApple() //generates the coordinates of a new apple for us
    {
    	appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //X coordinate of our apple, so apple appears some place along x-axis
    	appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    	//we multiply at the end by unit size again so that the apple is placed evenly within one of the item spots
    	
    	
    }

    public void move () //moves the snake
    {
    	for (int i = bodyParts; i>0; i--)
    	{
    		x[i] = x [i-1];
    		y[i] = y [i-1];
    	}
    	
    	switch(direction)
    	{
    	case 'U': 
    		y [0] = y[0] - UNIT_SIZE;
    		break;
    	case 'D': 
    		y [0] = y[0] + UNIT_SIZE;
    		break;
    	case 'L': 
    		x [0] = x[0] - UNIT_SIZE;
    		break;
    	case 'R': 
    		x [0] = x[0] + UNIT_SIZE;
    		break;
    	}
    }

    public void checkForApple ()
    
    {
      if ((x[0] == appleX) && (y [0] == appleY)) //x[0] is the x-axis head position of the snake and y [0] is the y-axis position of the head of the snake
      {
    	  bodyParts++;
    	  applesEaten++;
    	  newApple();
    	  
      }
     
    }
    
    public void checkForCollisions()
    {
    	//this checks if head collides with body
    for (int i = bodyParts; i>0; i--)
    {
    	if ((x[0] == x[i]) && (y [0] == y [i])) //if this is the case the head collided with the body
    	{
    		running = false;
    		
    	}
    	
    	//checks if head touches left border
    	
    	if ((x [0] < 0))
    	{
    		running = false;
    	}
    	
    	//if head touches right border
    	
    	if ((x [0] > SCREEN_WIDTH))
    	{
    		running = false;
    	}
    	
    	//if head touches top border
    	
    	if ((y [0] < 0))
    	{
    		running = false;
    	}
    	
    	//check if head touches bottom border
    	
    	if ((y [0] > SCREEN_HEIGHT))
    	{
    		running = false;
    	}
    	
    	
    	if (!running)
    	{
    		timer.stop();
    	}
    }
    	
    }

    public void GG(Graphics g)
    {
    	//Displaying the game over text
    	g.setColor(Color.red);
    	g.setFont(new Font("Times New Roman", Font.ITALIC, 80));
    	FontMetrics fm = getFontMetrics(g.getFont()); //to set text in the middle of the screen
    	g.drawString("Game Over", (SCREEN_WIDTH - fm.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    	
    	//display score
    	g.setColor(Color.red);
    	g.setFont(new Font("Times New Roman", Font.ITALIC, 30));
    	FontMetrics idk = getFontMetrics(g.getFont()); //to set text in the middle of the screen
    	g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - idk.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
    	
    }
	@Override
	public void actionPerformed(ActionEvent e) {

      if (running) //if the game is running
      {
    	  move();
    	  checkForApple();
    	  checkForCollisions();
    	  
      }
      
      repaint();
		
		
		
	}

	public class MyKeyAdapter extends KeyAdapter {
		
		@Override 
		public void keyPressed (KeyEvent k)
		{
			switch(k.getKeyCode())
			{
			case KeyEvent.VK_LEFT:
				if (direction != 'R') //if the snake is not going to the right because if it is going right at the moment it cannot go left wouldnt make sense
				{
					direction = 'L'; //if it is not going to the right change direction to left
				}
			    break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') //if the snake is not going to the left because if it is going left at the moment it cannot go right wouldnt make sense
				{
					direction = 'R'; //if it is not going to the left change direction to right
				}
			    break;
			case KeyEvent.VK_UP:
				if (direction != 'D') //if the snake is not going to down because if it is going down at the moment it cannot go up wouldnt make sense
				{
					direction = 'U'; //if it is not going down change direction up
				}
			    break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') //if the snake is not going up because if it is going up at the moment it cannot go down wouldnt make sense
				{
					direction = 'D'; //if it is not going up change direction downwards
				}
			    break;
			    
		   
			
		    
			}
		}
		
	}
	
	
}
