import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class Main extends JFrame
{
    /*
     * is the object that is read from and is printed to
     */
    private static Canvas canvas = new Canvas();
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
                                                                                    new ButtonExitYes(false, WIDTH*2/5 - 50, HEIGHT/2 - 10, 100, 20, null,  "Yes"),
                                                                                    new ButtonExitNo(false, WIDTH*3/5 - 50, HEIGHT/2 - 10, 100, 20, null, "No"),
                                                                                    new ButtonTestStart(true, WIDTH/2 - 50, HEIGHT/2 - 10, 100, 20, null, "Start")))),
                                                                                 new Screen("End Screen", new ArrayList<GUIComponent>(Arrays.asList(
                                                                                    new ButtonExitYes(false, WIDTH*2/5 - 50, HEIGHT/2 - 10, 100, 20, null, "Yes"),
                                                                                    new ButtonExitNo(false, WIDTH*3/5 - 50, HEIGHT/2 - 10, 100, 20, null, "No"),
                                                                                    new ButtonTestEnd(true, WIDTH/2 - 50, HEIGHT/2 - 10, 100, 20, null, "End"))))));// set all future screens to false
                                                                   
    
    private static Screen activeScreen = SCREENS.get(0);// makes code more efficient

    public static void setActiveScreen(Screen aS){
        activeScreen.getButtons().get(0).setActive(false); //ensure reset if screen changes
        activeScreen.getButtons().get(1).setActive(false); //ensure reset if screen changes
        activeScreen = aS;
    }
    public static Screen getActiveScreen(){
        return activeScreen;
    }


    private void setCanvas() {
        setIgnoreRepaint( true );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        canvas.setIgnoreRepaint( true );
        canvas.setSize( WIDTH, HEIGHT );
        add( canvas );
        pack();
    }

    private void setKeyListener() {
        // for keyboard using
        addKeyListener( keyboard );
        canvas.addKeyListener( keyboard );
    }

    private void setMouseListener() {
        // for mouse using
        addMouseListener( mouse );
        addMouseMotionListener( mouse );
        canvas.addMouseListener( mouse );
        canvas.addMouseMotionListener( mouse );
    }

    private void setImages() {
        try {
            for (int i = 0; i < SCREENS.size(); i++) {
                for (int j = 0; j < SCREENS.get(i).getButtons().size(); j++) {
                    if (new File("img\\" + SCREENS.get(i).getButtons().get(j).getText() + ".png").exists()) {
                        BufferedImage img = ImageIO.read(new File("img\\" + SCREENS.get(i).getButtons().get(j).getText() + ".png"));
                        
                        Image newResizedImage = img.getScaledInstance(SCREENS.get(i).getButtons().get(j).getWidth(), 
                                                                      SCREENS.get(i).getButtons().get(j).getHeight(), 
                                                                      Image.SCALE_SMOOTH);
                        BufferedImage newImg = new BufferedImage(newResizedImage.getWidth(null), newResizedImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                
                        Graphics2D graphics2D = newImg.createGraphics();
                        graphics2D.drawImage(newResizedImage, 0, 0, null);
                        graphics2D.dispose();

                        SCREENS.get(i).getButtons().get(j).setImg(newImg);

                    }
                }
            }
        } catch (IOException e) {
			e.printStackTrace();
		} 
    }

    public Main() 
    {
        setCanvas();
        setKeyListener();
        setMouseListener();
        setImages();
    }
    



    //static String t = "";

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
        //all major screen button and variable data here. ?

    	while (!gameOver) {// this keep everything running
			Thread.sleep(10);
    	}
    }
}