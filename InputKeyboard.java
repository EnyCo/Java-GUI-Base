import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKeyboard implements KeyListener{ 
    private static final int NUM_KEYS = 522;// 256 if downsizing is needed
        
    private enum KeyState{
        RELEASED, PRESSED, ONCE
    }
    
    private boolean[] currentKeys = new boolean[NUM_KEYS];// which keys are currently pressed
    private KeyState[] keys = new KeyState[NUM_KEYS];
    
    private static char val = ' ';
    
    public InputKeyboard(){ 
        for(int i = 0; i < NUM_KEYS; i++){// sets all keys to not pressed
            keys[i] = KeyState.RELEASED;
        }
    }
        
    public synchronized void updateKeyStates(){// updates key's states
        for(int i = 0; i < NUM_KEYS; i++){
            if(currentKeys[i]){
                if(keys[i] == KeyState.RELEASED)
                    keys[i] = KeyState.ONCE;
                else
                    keys[i] = KeyState.PRESSED;
            } else{
                keys[i] = KeyState.RELEASED;
            }
        }
    }
        
    public boolean keyDown(int keyID){
        return keys[keyID] == KeyState.ONCE || keys[keyID] == KeyState.PRESSED;
    }
        
    public boolean keyDownOnce(int keyID){
        return keys[keyID] == KeyState.ONCE;
    }
        
    public synchronized void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        
        if( keyCode >= 0 && keyCode < NUM_KEYS ){
            currentKeys[keyCode] = true;
        }
    }

    public synchronized void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if( keyCode >= 0 && keyCode < NUM_KEYS ){
           currentKeys[keyCode] = false;
        }
    }

    public void keyTyped(KeyEvent e){
        val = e.getKeyChar();
        if (Main.getActiveTextBox() != null) {
            if (!Character.isISOControl(val)){
                Main.getActiveTextBox().append(val);
            } else if (InputKeyboard.getKey() == KeyEvent.VK_BACK_SPACE) {
                Main.getActiveTextBox().remend(); 
            }
        }   
    }
    
    public static char getKey(){
        return val;
    }
}