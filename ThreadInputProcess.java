import java.awt.*;
import java.awt.event.*;

public class ThreadInputProcess implements Runnable {
    @Override
    public void run() {
        while (true) {                
            Point p = Main.getMouse().getPosition(); 
            int x = (int)(p.getX());
            int y = (int)p.getY();

            

            for (int i = 0; i < Main.activeScreen.getButtons().size(); i++) {
                if (Main.activeScreen.getButtons().get(i).isHover(x, y) && Main.getMouse().isButtonClicked(1)){
                    Main.activeScreen.getButtons().get(i).onClick();
                }
            }                

            
            if (Main.getKeyboard().isKeyPressed( KeyEvent.VK_ESCAPE )) {
                Main.setGameOver(true);
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