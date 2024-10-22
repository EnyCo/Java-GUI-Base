import java.awt.*;
import java.awt.event.*;

public class ThreadInputProcess implements Runnable {
    @Override
    public void run() {
        while (true) {                
            Point p = Main.getMouse().getPosition(); 
            int x = (int)(p.getX());
            int y = (int)p.getY();

            for (int i = 0; i < Main.getActiveScreen().getButtons().size(); i++) {
                if (Main.getActiveScreen().getButtons().get(i).isHover(x, y) && Main.getMouse().isButtonClicked(1)){
                    Main.getActiveScreen().getButtons().get(i).onClick();
                }
            }                
            
            if (Main.getKeyboard().isKeyPressedOnce( KeyEvent.VK_ESCAPE )) {
                Main.getActiveScreen().getButtons().get(0).setActive(!Main.getActiveScreen().getButtons().get(0).getActive());// sets no button to on
                Main.getActiveScreen().getButtons().get(1).setActive(!Main.getActiveScreen().getButtons().get(1).getActive());// sets yes button to on
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("PROCESS CLOCK NOT WORKING");
                e.printStackTrace();
            }
        }
    }
}