import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.*;
import javax.swing.*;

public class Main extends JFrame
{
    private static int threadSleep = 10;
    public static int getThreadSleep() {
        return threadSleep;
    }

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
     * should be list of buttons for each screen (may be in a screen object)
     */

    private static HashMap<String, Screen> Screens = new HashMap<String, Screen>();
    public static HashMap<String, Screen> getScreens(){
        return Screens;
    }

    private static Stack<Screen> visibleScreens = new Stack<Screen>();
    public static Stack<Screen> getVisibleScreens(){
        return visibleScreens;
    }
    public static void pushVisibleScreens(Screen screen){
        visibleScreens.push(screen);
        screen.setVisible(true);
    }
    public static void popVisibleScreens(){
        Screen rem = visibleScreens.pop();
        rem.setVisible(false);
    }

    public static Screen getActiveScreen(){
        return visibleScreens.peek();
    }

    private static TextBox activeTextBox;
    public static void setActiveTextBox(TextBox tb){
        activeTextBox = tb;
    }
    public static TextBox getActiveTextBox(){
        return activeTextBox;
    }

    
    private static boolean playClang = false; //store all audios in structure so not to flood this area.
    public static void setClang(boolean p) {
        playClang = p;
    }
    public static boolean getClang() {
        return playClang;
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

    private void setScreens() {
        Screen exitDialogue = new Screen(false, WIDTH/3, HEIGHT/3, ComponentSize.Large.getWidth(), ComponentSize.Large.getHeight(), "Exit Screen", Color.LIGHT_GRAY,
        new ArrayList<Img>(Arrays.asList()),
        new ArrayList<Button>(Arrays.asList(// button list
            new ButtonExitYes(true, WIDTH*4/10, HEIGHT/2, ComponentSize.Small.getWidth(), ComponentSize.Small.getHeight(), "yes", null),
            new ButtonExitNo(true, WIDTH*6/10, HEIGHT/2, ComponentSize.Small.getWidth(), ComponentSize.Small.getHeight(), "no", null)
            )),
        new ArrayList<TextBox>(Arrays.asList()),// textbox list
        new ArrayList<Screen>(Arrays.asList())// subscreen list
        );


        Screens.put("Start Screen", new Screen(true, 0, 0, ComponentSize.ScreenDefault.getWidth(), ComponentSize.ScreenDefault.getHeight(), "Start Screen", Color.GRAY, 
                new ArrayList<Img>(Arrays.asList(
                    new Img(true, WIDTH/2, ComponentSize.BannerDefault.getHeight()/2, ComponentSize.BannerDefault.getWidth(), ComponentSize.BannerDefault.getHeight(), "banner", null)
                )),
                new ArrayList<Button>(Arrays.asList(
                    )),
                new ArrayList<TextBox>(Arrays.asList(
                    new TextBox(true, WIDTH/2, HEIGHT/2, ComponentSize.TextBoxDefault.getWidth(), ComponentSize.TextBoxDefault.getHeight(), "Your Name", ""))),
                new ArrayList<Screen>(Arrays.asList(
                    exitDialogue))
                )
        );
        visibleScreens.push(Screens.get("Start Screen"));
    }

    public Main() 
    {
        setCanvas();
        setKeyListener();
        setMouseListener();

        setScreens();
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
        Thread clock = new Thread(new ClockThread());
        clock.start();

        Thread inputProcess = new Thread(new ThreadInputProcess());
    	inputProcess.start();

    	Thread outputVisual = new Thread(new ThreadOutputVisual());
    	outputVisual.start();

        Thread outputAudio = new Thread(new ThreadOutputAudio());
    	outputAudio.start();

    	while (!gameOver) {// this keep everything running
			Thread.sleep(10);
    	}
    }

    enum ComponentSize { // 2,3,4,6,12?? 
        ScreenDefault(WIDTH, HEIGHT), 
        TextBoxDefault(WIDTH/10, HEIGHT/40), 
        BannerDefault(WIDTH, HEIGHT/10), 

        Small(WIDTH/12, HEIGHT/12), 
        Medium(WIDTH/4, HEIGHT/4), 
        Large(WIDTH/3, HEIGHT/3),
        XtraLarge(WIDTH/2, HEIGHT/2);
      
        private int width;
        private int height;

        private ComponentSize(final int width, final int height) {
            this.width = width;
            this.height = height;
        }
      
        public int getWidth() {
          return width;
        }
        public int getHeight() {
            return height;
        }
    }
}