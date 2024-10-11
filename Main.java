/**
 * VERSION 2.0
 * HAS MULTITHREADING
 */
import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
//import java.awt.geom.Point2D;
//import java.awt.image.*;
//import java.util.*;
//import javax.imageio.*;
import javax.swing.*;

public class Main extends JFrame
{
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static Canvas canvas;

    final static int HEIGHT = screenSize.height-70;
    final static int WIDTH = screenSize.width;
	final static int RIGHT_SHIFT = WIDTH/2 - HEIGHT/2;

    static KeyboardInput keyboard = new KeyboardInput();
    static MouseInput mouse = new MouseInput(); 
    
    public Main() 
    {
        setIgnoreRepaint( true );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        canvas = new Canvas();
        canvas.setIgnoreRepaint( true );
        canvas.setSize( WIDTH, HEIGHT );
        add( canvas );
        pack();

        // for keyboard using
        addKeyListener( keyboard );
        canvas.addKeyListener( keyboard );
        
        // for mouse using
        addMouseListener( mouse );
        addMouseMotionListener( mouse );
        canvas.addMouseListener( mouse );
        canvas.addMouseMotionListener( mouse );
    }
    
    public static void main(String[] args) throws UnsupportedFlavorException,InterruptedException, IOException
    {
        Main app = new Main();
        app.setTitle( "Main" );
        app.setVisible( true );
        app.run();
        System.exit( 0 ); 
    }
    
    public void run() throws UnsupportedFlavorException, InterruptedException {
    	Thread vOutput = new Thread(new VisualOutput());
    	vOutput.start();

        Thread pInput = new Thread(new ProcessInput());
    	pInput.start();

    	while (true) {// this keep everything running
			//Thread.sleep(10000000);
    	}
    }
}