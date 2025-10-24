import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.net.URL;
// The Duck class represents a picture of a duck that can be drawn on the screen.


public class Duck {
    // Instance variables (data that belongs to each Duck object)
    private Image img;               // Stores the picture of the duck
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private int x;                
    private int y;        
    
    //variables for speed
    private int vx;
    private int vy;
    //shot
 
    //debugging variable
    public boolean debugging = true;
    // Constructor: runs when you make a new Duck object
    public Duck() {
        img = getImage("/imgs/PIskel candy.gif"); // Load the image file
        
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = 1.0;
        scaleY = 1.0;
        x = 0;
        y = 0;
        vx = 5;
        vy = 5;

        init(x, y); // Set up the starting location and size
    }
    
    //2nd constructor to initialize location and scale!
    public Duck(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public Duck(int x, int y, int scaleX, int scaleY, int vx, int vy) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	this.vx 	= vx; 
    	this.vy 	= vy;
    	init(x,y);
    }
    
    public void setVelocityVariables(int vx, int vy) {
    	this.vx = vx;
    	this.vy = vy;
    }

    
    
    // Changes the picture to a new image file
    public void changePicture(String imageFileName) {
        img = getImage("/imgs/"+imageFileName);
        init(x, y); // keep same location when changing image
    }
    
    //update any variables for the object such as x, y, vx, vy......
    public void update() { 
   	x = x + vx;
    if (vx > 50) {
    	
    }
    if (vx < -50) {
    	
    }
    if (vy> 50) {
    	
    }
    if (vy < -50) {
    	
    }
    
    
    
   	if (y < 400) {
   	y = y + vy;
   	
   	}
  
    	if(x>=450) {
    		vx = vx * -1;
    		if (vx>0) {
    			vx = vx +1;
    			System.out.println(vx);
    		}
    		else {
    			vx = vx - 1;
    			System.out.println(vx);
    		}
    	}
    	if(x <= -130) {
    		vx = vx * -1;
    		if (vx>0) {
    			vx = vx +1;
    			System.out.println(vx);
    			
    		}
    		else {
    			vx = vx - 1;
    			System.out.println(vx);
    		}
    	}
    	
    	if(y <=-130) {
    	vy = vy * -1;
    		if (vy>0) {
    			vy = vy +1;
    			System.out.println(vy);
			
    		}
    		else {
    			vy = vy -1;
    			System.out.println(vy);
    		}
    	}
    	
    	if (vx == 0 && vy > 10) {
    		if(y >= 200) {
    			vy = - (int)(Math.random()*8+3);
    		vx = (int)(Math.random()*8+3);
    		if(Math.random()<0.5) {
    			vx = vx * +1;
    			}
    		
    		}
    	}
  
    
  
    	
    	
    if(y >= 200 && vx!=0) {
   	vy = vy * -1;
   		if (vy>0) {
   			vy = vy +1;
   			System.out.println(vy);
		
   		}
   		else {
   			vy = vy - 1;
   			System.out.println(vy);
	}
    }
    }    
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (vx<0) {
        	tx.scale(-1, 1);
        	tx.translate(-img.getWidth(null), 0);
        }
        g2.drawImage(img, tx, null);
        update();
        init(x, y);
        //create a green hitbox
        if(debugging) {
        	g.setColor(Color.magenta);
        	g.drawRect((int) x + 150, (int) y + 140, 30, 30); 
        }
    }
    
    // Setup method: places the duck at (a, b) and scales it
    private void init(double a,double b){
        tx.setToTranslation(a, b);        // Move the image to position (a, b)
        tx.scale(scaleX, scaleY);         // Resize the image using the scale variables
    }

    // Loads an image from the given file path
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Duck.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // NEW: Method to set scale
    public void setScale(double sx, double sy) {
        scaleX = sx;
        scaleY = sy;
        init(x, y);  // Keep current location
    }
 

    // NEW: Method to set location
    public void setLocation(int newX, int newY) {
        x = newX;
        y = newY;
        init(x, y);  // Keep current scale
    }
    public void setVelocity(int newX, int newY) {
        vx = newX;
        vy = newY;
        init(x, y);  // Keep current scale
    }
    //Collision and collision logic
    public boolean checkCollision(int mX, int mY) {
    	
    	Rectangle mouse = new Rectangle(mX - 140, mY - 140, 15, 15);
    	Rectangle thisObject = new Rectangle((int)x, (int)y, 30, 30);
    
    	if (mouse.intersects(thisObject)) {

    		changePicture("PIskel candy (1).gif");
    		return true;
    		
    	}
    	else {
    		return false;
    	}
   
    	
    
    
    
    
    
    }
    public double getX() {
    	return this.x;
    }
    public double getY() {
    	return this.y;
    }
    public double getVX() {
    	return this.vx;
    }
    public double getVY() {
    	return this.vy;
    }
    

 
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
