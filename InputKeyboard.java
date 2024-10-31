import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class InputKeyboard implements KeyListener{ 
    private Map<Integer, Boolean> keyStates = new HashMap<>();
    private static char val = ' ';
        
    public synchronized void updateKeyStates(KeyEvent e, boolean isPressed){// updates key's states
        int keyCode = e.getKeyCode();
        keyStates.put(keyCode, isPressed);
    }
        
    public synchronized void keyPressed(KeyEvent e){
        updateKeyStates(e, true);
    }

    public synchronized void keyReleased(KeyEvent e){
        updateKeyStates(e, false);
    }

    public void keyTyped(KeyEvent e){
        val = e.getKeyChar();
        if (Main.getActiveTextBox() != null) {
            if (!Character.isISOControl(InputKeyboard.getVal())){
                Main.getActiveTextBox().append(val);
            } else if (isKeyPressed(KeyEvent.VK_BACK_SPACE)) {
                if (Main.getActiveTextBox().getText().length() > 0) {
                    Main.getActiveTextBox().remend();
                }  
            }
        }
    }
    
    public boolean isKeyPressed(int keyCode) {
        return keyStates.getOrDefault(keyCode, false);
    }

    public boolean isKeyPressedOnce(int keyCode) {
        if (keyStates.getOrDefault(keyCode, false)) {
            keyStates.put(keyCode, false); // Reset state after checking
            return true;
        }
        return false;
    }


    public static char getVal(){
        return val;
    }
}