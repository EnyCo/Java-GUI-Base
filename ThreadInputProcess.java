import java.awt.*;
import java.awt.event.*;

public class ThreadInputProcess implements Runnable {
    @Override
    public void run() {
        while (true) {                
            Point p = Main.getMouse().getPosition(); 
            int x = (int)(p.getX());
            int y = (int)p.getY();


            if (Main.getMouse().isButtonClicked(1)) {
                for (int i = 0; i < Main.getActiveScreen().getButtons().size(); i++) {
                    if (Main.getActiveScreen().getButtons().get(i).getActive()) {
                        if (Main.getActiveScreen().getButtons().get(i).isHover(x, y)){
                            Main.getActiveScreen().getButtons().get(i).onClick();
                        }
                    }
                }
                
                if (Main.getActiveTextBox() != null && !Main.getActiveTextBox().isHover(x, y)) {
                    Main.setActiveTextBox(null);
                }
                for (int i = 0; i < Main.getActiveScreen().getTextboxes().size(); i++) {
                    if (Main.getActiveScreen().getTextboxes().get(i).getActive()) {
                        if (Main.getActiveScreen().getTextboxes().get(i).isHover(x, y)){
                            Main.getActiveScreen().getTextboxes().get(i).onClick();
                        }
                    }
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