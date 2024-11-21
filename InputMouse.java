import java.awt.Point;
import java.awt.event.*;
public class InputMouse implements MouseListener, MouseMotionListener, MouseWheelListener{
    private static final int BUTTON_COUNT = 3;// can be more
    private Point mousePos = new Point( 0, 0 );
    private Point currentPos = new Point( 0, 0 );
  
    private boolean[] state = new boolean[ BUTTON_COUNT ]; // which mouse buttons are currently pressed
    private MouseState[] buttons = new MouseState[ BUTTON_COUNT ];
        
    private static int notches;
    private enum MouseState{
        RELEASED, PRESSED, ONCE
    }
        
    public InputMouse(){
        for(int i = 0; i < BUTTON_COUNT; i++){// set all buttons to not pressed
          buttons[i] = MouseState.RELEASED;
        }
    }
            
    public synchronized void updateButtonStates(){//update buttons' states
        mousePos = new Point(currentPos);
        for(int i = 0; i < BUTTON_COUNT; i++){
            if(state[i]){
                if(buttons[i] == MouseState.RELEASED)
                    buttons[i] = MouseState.ONCE;
                else
                    buttons[i] = MouseState.PRESSED;
            } else{
                buttons[i] = MouseState.RELEASED;
            }
        }
    }
    
    public Point getPosition(){
        return mousePos;
    }
    
    public boolean buttonDownOnce( int button ){
        return buttons[ button-1 ] == MouseState.ONCE;
    }
    
    public boolean buttonDown( int button ){
        return buttons[ button-1 ] == MouseState.ONCE || buttons[ button-1 ] == MouseState.PRESSED;
    }
      
    public synchronized void mousePressed( MouseEvent e ){
        state[ e.getButton()-1 ] = true;
    }
    
    public synchronized void mouseReleased( MouseEvent e ){
        state[ e.getButton()-1 ] = false;
    }
    
    public synchronized void mouseEntered( MouseEvent e ){
        mouseMoved( e );
    }
      
    public synchronized void mouseExited( MouseEvent e ){
        mouseMoved( e );
    }
      
    public synchronized void mouseDragged( MouseEvent e ){
        mouseMoved( e );
    }
    
    public synchronized void mouseMoved( MouseEvent e ){
        currentPos = e.getPoint();
    }
      
    public void mouseClicked( MouseEvent e ){
        //System.out.println("clicked");
    }
      
    public void mouseWheelMoved(MouseWheelEvent e){
        notches = e.getWheelRotation();
    }
    
    public int getNotches(){
        return notches;
    }
    
    public void setNotches(int newNotches){
        notches = newNotches;
    }
}