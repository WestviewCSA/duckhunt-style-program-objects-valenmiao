import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//frame size
	private int screenWidth = 640, screenHeight = 640;
	private String title = "feed the baby";
	
	
	/**
	 * Declare and instantiate (create) your objects here
	 */
	int score = 0;

	int x = 1;
	int y = 400;
	int frame = 0;
	int fr = 0;
	double scaleX = 1.0;
	double scaleY = 1.0;
	private Background myBackground = new Background();
	private Duck duckObject = new Duck(1, 1, 1, 1);
	private Kid kidObject = new Kid(x, y, 1, 1);
	private MyCursor cursor = new MyCursor();
	private Duck duckObject2 = new Duck(152, 1, 1, 1);
	
	public void paint(Graphics pen) {
	if (frame == 0) {
		duckObject.setVelocity((int)(Math.random()* 5) + score, (int)(Math.random()* 5) + score);
		
	}
		//this line of code is to force redraw the entire frame
		super.paintComponent(pen);
		//Background should be drawn before the objects as it's layer is last
		myBackground.paint(pen);
		//call paint for the object
		//for objects, you call methods on them using the dot operator
		//methods use always involve parenthesis
		duckObject.paint(pen);
		if (score > 5) {
			duckObject2.paint(pen);
			
		}
		kidObject.paint(pen);
		cursor.paint(pen);
		
		frame = frame + 1;
		if (fr == frame) {
			cursor.changePicture("download.png");
		}
		if ((duckObject.getVX() ==0)&& kidObject.checkCollision((int)duckObject.getX(), (int)duckObject.getY())) {
			cursor.ammoIncrement();
			score = score+1;
			System.out.println(score);
			duckObject.changePicture("PIskel candy.gif");
			duckObject.setLocation((int)(Math.random()* 100) , (int)(Math.random() * 100));
			System.out.println(duckObject.getX() + ", " + duckObject.getY());
			duckObject.setVelocity((int)(Math.random()* 5) + score, (int)(Math.random()* 5) + score);
		
		}
		
		if ((duckObject2.getVX() ==0)&& score > 5 && kidObject.checkCollision((int)duckObject2.getX(), (int)duckObject2.getY())) {
			cursor.ammoIncrement();
			score = score+1;
			System.out.println(score);
			duckObject2.changePicture("PIskel candy.gif");
			duckObject2.setLocation((int)(Math.random()* 100) , (int)(Math.random() * 100));
			System.out.println(duckObject2.getX() + ", " + duckObject2.getY());
			duckObject2.setVelocity((int)(Math.random()* 5) + score, (int)(Math.random()* 5) + score);
		
		}
		
		if (cursor.ammoLeft() == -1) {
			System.out.println("You have ran out of Ammo.");
			System.out.println("Your score was " + score);
			System.exit(0);
		}
		////////////////////////////////////around 120 velocityit breaks
		///
		
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
	    // Runs when the mouse is clicked (pressed and released quickly).
	    // Example: You could use this to open a menu or select an object.
	    
	    
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
	    // Runs when the mouse enters the area of a component (like a button).
	    // Example: You could highlight the button when the mouse hovers over it.
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
	    // Runs when the mouse leaves the area of a component.
	    // Example: You could remove the highlight when the mouse moves away.
	}


	@Override
	public void mousePressed(MouseEvent mouse) {
	    // Runs when a mouse button is pressed down.
	    // Example: You could start dragging an object here.
		int Mx = mouse.getX() - 50;
		int My = mouse.getY() - 50;
		System.out.println(mouse.getX()+":"+mouse.getY());
		//if (duckObject.checkCollision(Mx, My)) {
			//duckObject.shot();
		//}
		System.out.println(duckObject.checkCollision(Mx, My));
		if (duckObject.checkCollision(Mx, My) && cursor.ammoLeft() >=0) {
			duckObject.setVelocity(0, 9);
	
		}
		System.out.println(duckObject2.checkCollision(Mx, My));
		if (duckObject2.checkCollision(Mx, My) && cursor.ammoLeft() >=0 && score>5) {
			duckObject2.setVelocity(0, 9);
	
		}
		if (cursor.ammoLeft() >=0) {
		cursor.changePicture("download (1).png");
	    fr = frame + 10;
	    cursor.ammoDecrement();
	    System.out.println("You have " + cursor.ammoLeft() + " ammo left");
		}
		System.out.println("score:" + score);

	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
	    // Runs when a mouse button is released.
	    // Example: You could stop dragging the object or drop it in place.
	}



	/*
	 * This method runs automatically when a key is pressed down
	 */
	public void keyPressed(KeyEvent key) {
		
		//System.out.println("from keyPressed method:"+key.getKeyCode());
		
		if (65 == key.getKeyCode()) {
			x = x -5;
			kidObject.setLocation(x, y);
			//System.out.println("a");
		}
		if (68 == key.getKeyCode()) {
			x = x+5;
			kidObject.setLocation(x, y);
			//ddadadSystem.out.println("d");
		}
		//w87
		//a65
		//s83
		//d68
	}

	/*
	 * This method runs when a keyboard key is released from a pressed state
	 * aka when you stopped pressing it
	 */
	public void keyReleased(KeyEvent key) {
		
	}

	/**
	 * Runs when a keyboard key is pressed then released
	 */
	public void keyTyped(KeyEvent key) {
		
		
	}
	
	
	/**
	 * The Timer animation calls this method below which calls for a repaint of the JFrame.
	 * Allows for our animation since any changes to states/variables will be reflected
	 * on the screen if those variables are being used for any drawing on the screen.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	/*
	 * Main method to create a Frame (the GUI that you see)
	 */
	public static void main(String[] arg) {
		new Frame();
	}
	
	
	
	public Frame() {
		JFrame f = new JFrame(title);
		f.setSize(new Dimension(screenWidth, screenHeight));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		
		//Cursor icon Code
		//Toolkit toolkit = Toolkit.getDefaultToolkit();
		//Image image = toolkit.getImage("cursor.png");
		//Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(),this.getY()));
		//this.setCursor (a);
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
