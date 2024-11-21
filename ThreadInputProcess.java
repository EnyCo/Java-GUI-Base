import java.awt.*;
import java.awt.event.*;

public class ThreadInputProcess implements Runnable {
    @Override
    public void run() {
        while (true) {   
            Main.getKeyboard().updateKeyStates();
            Main.getMouse().updateButtonStates();

            Point p = Main.getMouse().getPosition(); 
            int x = (int)(p.getX());
            int y = (int)p.getY();


            if (Main.getMouse().buttonDownOnce(1)) {
                if (!Main.getActiveScreen().isHover(x, y)) { 
                    Main.popVisibleScreens(); 
                }


                for (int i = 0; i < Main.getActiveScreen().getButtons().size(); i++) {
                    if (Main.getActiveScreen().getButtons().get(i).getVisible()) {
                        if (Main.getActiveScreen().getButtons().get(i).isHover(x, y)) {
                            Main.getActiveScreen().getButtons().get(i).onClick();
                        }
                    }
                }
                
                if (Main.getActiveTextBox() != null && !Main.getActiveTextBox().isHover(x, y)) {
                    Main.setActiveTextBox(null);
                }
                for (int i = 0; i < Main.getActiveScreen().getTextboxes().size(); i++) {
                    if (Main.getActiveScreen().getTextboxes().get(i).getVisible()) {
                        if (Main.getActiveScreen().getTextboxes().get(i).isHover(x, y)){
                            Main.getActiveScreen().getTextboxes().get(i).onClick();
                        }
                    }
                }
            }            
            
            if (Main.getKeyboard().keyDownOnce( KeyEvent.VK_ENTER )) {
                if (Main.getActiveTextBox() != null) {
                    String user = Main.getActiveTextBox().onEnter();

                    
                    Main.setUser(user);
                    //System.out.println(Main.user);
                    
                    Main.getActiveScreen().getButtons().get(0).setVisible(true);
                    Main.setActiveTextBox(null);
                }
            }
            if (Main.getKeyboard().keyDownOnce( KeyEvent.VK_ESCAPE )) {
                if (Main.getActiveTextBox() != null) {
                    Main.setActiveTextBox(null);
                } 


                if (Main.getActiveScreen().getName().equals("Exit Screen")) {
                    Main.popVisibleScreens();
                } else {
                    Main.pushVisibleScreens(Main.getActiveScreen().getSubScreens().get(0));
                }
            }


            try {
                Thread.sleep(Main.getThreadSleep());
            } catch (InterruptedException e) {
                System.out.println("PROCESS CLOCK NOT WORKING");
                e.printStackTrace();
            }
        }
    }
}