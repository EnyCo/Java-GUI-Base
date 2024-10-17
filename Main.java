import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
//import java.awt.geom.Point2D;
//import java.awt.image.*;
import java.util.*;
//import javax.imageio.*;
import javax.swing.*;

public class Main extends JFrame
{
    /*
     * is the object that is read from and is printed to
     */
    private static Canvas canvas;
    public static Canvas getCanvas() {
        return canvas;
    }

    /*
     * keyboard reader
     */
    private static InputKeyboard keyboard = new InputKeyboard();
    public static InputKeyboard getKeyboard() {
        return keyboard;
    }
    
    /*
     * mouse reader
     */
    private static InputMouse mouse = new InputMouse();
    public static InputMouse getMouse() {
        return mouse;
    }
    
    /*
     * indicator for when to end run
     */
    private static boolean gameOver = false;
    public static void setGameOver(boolean gO) {
        gameOver = gO;
    }
    public static boolean getGameOver() {
        return gameOver;
    }

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
    /*
     * screen height in pixels
     */
    private final static int HEIGHT = screenSize.height;
    public static int getHeightScreen() {
        return HEIGHT;
    }
    
    /*
     * screen width in pixels
     */
    private final static int WIDTH = screenSize.width;
    public static int getWidthScreen() {
        return WIDTH;
    }
    
    /*
     * value that refers to the pixel distance between:
     *      the left edge of screen 
     *      the right edge of the visual canvas
     * so that the visual canvas is centered
     */
	private final static int SHIFT = WIDTH/2 - HEIGHT/2;
    public static int getShift() {
        return SHIFT;
    }

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
    

    //should be list of buttons for each screen (may be in a screen object)
    static final ArrayList<Button> buttons = new ArrayList<Button>(Arrays.asList(
        new Button(10, 10, 100, 30, new CommandSave()), //xyz function
        new Button(200, 10, 100, 30, new CommandSelect())));// xyz function


    public static void main(String[] args) throws UnsupportedFlavorException,InterruptedException, IOException
    {
        Main app = new Main();
        app.setTitle( "Main" );
        app.setVisible( true );
        app.run();
        System.exit( 0 ); 
    }
    
    public void run() throws UnsupportedFlavorException, InterruptedException {
    	Thread outputVisual = new Thread(new ThreadOutputVisual());
    	outputVisual.start();

        Thread outputAudio = new Thread(new ThreadOutputAudio());
    	outputAudio.start();

        Thread inputProcess = new Thread(new ThreadInputProcess());
    	inputProcess.start();


        //all major screen button and variable data here.


    	while (!gameOver) {// this keep everything running
			Thread.sleep(10);
    	}
    }
}