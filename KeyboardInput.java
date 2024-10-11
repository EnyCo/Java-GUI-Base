import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{ 
    private static final int NUM_KEYS = 522;// 256 if downsizing is needed
        
    private enum KeyState{
        RELEASED, PRESSED, ONCE
    }
    
    private boolean[] currentKeys = new boolean[NUM_KEYS];// which keys are currently pressed
    private KeyState[] keys = new KeyState[NUM_KEYS];
    
    private static char val = ' ';
    
    public KeyboardInput(){ 
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
            
    }
    
    public static char getKey(){
        return val;
    }
}