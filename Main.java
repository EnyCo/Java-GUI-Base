import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.*;
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


     /*
     * should be list of buttons for each screen (may be in a screen object)
     */
    static final ArrayList<Screen> SCREENS = new ArrayList<Screen>(Arrays.asList(new Screen("Start Screen", new ArrayList<GUIComponent>(Arrays.asList(
                                                                                    new ButtonExitYes(false, WIDTH/4 - 50, HEIGHT/2 - 10, 100, 20, "Yes"),
                                                                                    new ButtonExitNo(false, WIDTH*3/4 - 50, HEIGHT/2 - 10, 100, 20, "No!"),
                                                                                    new ButtonTestStart(true, WIDTH/2 - 50, HEIGHT/2 - 10, 100, 20, "Start!")))),
                                                                                 new Screen("End Screen", new ArrayList<GUIComponent>(Arrays.asList(
                                                                                    new ButtonExitYes(false, WIDTH/4 - 50, HEIGHT/2 - 10, 100, 20, "Yes"),
                                                                                    new ButtonExitNo(false, WIDTH*3/4 - 50, HEIGHT/2 - 10, 100, 20, "No!"),
                                                                                    new ButtonTestEnd(true, WIDTH/2 - 50, HEIGHT/2 - 10, 100, 20, "End!"))))));// set all future screens to false
                                                                   
    
    private static Screen activeScreen = SCREENS.get(0);// makes code more efficient

    public static void setActiveScreen(Screen aS){
        activeScreen.getButtons().get(0).setActive(false); //ensure reset if screen changes
        activeScreen.getButtons().get(1).setActive(false); //ensure reset if screen changes
        activeScreen = aS;
    }
    public static Screen getActiveScreen(){
        return activeScreen;
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
    



    //static String t = "";

    public static void main(String[] args) throws UnsupportedFlavorException,InterruptedException, IOException
    {
        System.out.println(activeScreen.getName() + " is the active screen now!");
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