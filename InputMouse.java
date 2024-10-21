import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class InputMouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    private Map<Integer, Boolean> buttonStates = new HashMap<>();
    private Point pos = new Point( 0, 0 );
    
    public synchronized void updateButtonStates(MouseEvent e, boolean isPressed){// updates key's states
        int buttonCode = e.getButton();
        buttonStates.put(buttonCode, isPressed);
        //System.out.println(buttonCode);
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        updateButtonStates(e, true);
        //System.out.println("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updateButtonStates(e, false);
        //System.out.println("Mouse released at (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse entered component.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse exited component.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Mouse dragged to (" + e.getX() + ", " + e.getY() + ")");
        pos = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse moved to (" + e.getX() + ", " + e.getY() + ")");
        pos = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //System.out.println("Mouse wheel moved: " + e.getWheelRotation());
    }

    public boolean isButtonClicked(int buttonCode) {
        if (buttonStates.getOrDefault(buttonCode, false)) {
            buttonStates.put(buttonCode, false); // Reset state after checking
            return true;
        }
        return false;
    }

    public Point getPosition() {
        return pos;
    }
}
