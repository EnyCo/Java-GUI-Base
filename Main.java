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
     * should be list of buttons for each screen (may be in a screen object)
     */

    private static HashMap<String, Screen> Screens = new HashMap<String, Screen>();
    private static ArrayList<Screen> activeScreens = new ArrayList<Screen>();
    
    public static ArrayList<Screen> getActiveScreens(){
        return activeScreens;
    }

    private static TextBox activeTextBox;
    public static void setActiveTextBox(TextBox tb){
        activeTextBox = tb;
    }
    public static TextBox getActiveTextBox(){
        return activeTextBox;
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
        Screen exitDialogue = new Screen(false, WIDTH/3, HEIGHT/3, WIDTH/3, HEIGHT/3, Color.LIGHT_GRAY, "Exit Screen!",
        new ArrayList<Img>(Arrays.asList()),
        new ArrayList<Button>(Arrays.asList(// button list
            new ButtonExitYes(true, WIDTH*4/10-50, HEIGHT/2-25, 100, 50, null, "yes"),
            new ButtonExitNo(true, WIDTH*6/10-50, HEIGHT/2-25, 100, 50, null, "no")
            )),
        new ArrayList<TextBox>(Arrays.asList()),// textbox list
        new ArrayList<Screen>(Arrays.asList())// subscreen list
        );


        Screens.put("Start Screen", new Screen(true, 0, 0, WIDTH, HEIGHT, Color.GRAY, "Start Screen",
                new ArrayList<Img>(Arrays.asList(
                    new Img(true, 0, 0, WIDTH, 100, null, "banner")
                )),
                new ArrayList<Button>(Arrays.asList(
                    )),
                new ArrayList<TextBox>(Arrays.asList(
                    new TextBoxTest(true, WIDTH/2-50, HEIGHT/2-10, 100, 20, ""))),
                new ArrayList<Screen>(Arrays.asList(
                    exitDialogue))
                )
        );
        getActiveScreens().add(Screens.get("Start Screen"));
    }

    private void setImages() {
        try {
            for (Map.Entry<String,Screen> mapElement : Screens.entrySet()) {
                for (int i = 0; i < mapElement.getValue().getImgs().size(); i++) {                        
                    if (new File("img\\" + mapElement.getValue().getImgs().get(i).getName() + ".png").exists()) {
                        BufferedImage img = ImageIO.read(new File("img\\" + mapElement.getValue().getImgs().get(i).getName() + ".png"));
                                        
                        Image newResizedImage = img.getScaledInstance(mapElement.getValue().getImgs().get(i).getWidth(), 
                        mapElement.getValue().getImgs().get(i).getHeight(), 
                                                                                    Image.SCALE_SMOOTH);
                        BufferedImage newImg = new BufferedImage(newResizedImage.getWidth(null), newResizedImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        
                        Graphics2D graphics2D = newImg.createGraphics();
                        graphics2D.drawImage(newResizedImage, 0, 0, null);
                        graphics2D.dispose();

                        mapElement.getValue().getImgs().get(i).setImg(newImg);
                    }
                }
                for (int i = 0; i < mapElement.getValue().getButtons().size(); i++) {                        
                    if (new File("img\\" + mapElement.getValue().getButtons().get(i).getName() + ".png").exists()) {
                        BufferedImage img = ImageIO.read(new File("img\\" + mapElement.getValue().getButtons().get(i).getName() + ".png"));
                                        
                        Image newResizedImage = img.getScaledInstance(mapElement.getValue().getButtons().get(i).getWidth(), 
                        mapElement.getValue().getButtons().get(i).getHeight(), 
                                                                                    Image.SCALE_SMOOTH);
                        BufferedImage newImg = new BufferedImage(newResizedImage.getWidth(null), newResizedImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        
                        Graphics2D graphics2D = newImg.createGraphics();
                        graphics2D.drawImage(newResizedImage, 0, 0, null);
                        graphics2D.dispose();

                        mapElement.getValue().getButtons().get(i).setImg(newImg);
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

        setScreens();
        setImages();
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
        Thread clock = new Thread(new Clock());
        clock.start();

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