import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ThreadInputProcess implements Runnable {
    @Override
    public void run() {
        while (true) {                
            Point p = Main.getMouse().getPosition(); 
            int x = (int)(p.getX());
            int y = (int)p.getY();


            if (Main.getMouse().isButtonClicked(1)) {
                for (int i = 0; i < Main.getActiveScreen().getButtons().size(); i++) {
                    if (Main.getActiveScreen().getButtons().get(i).getVisible()) {
                        if (Main.getActiveScreen().getButtons().get(i).isHover(x, y)){
                            Main.setClang(true);
                            
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
            
            if (Main.getKeyboard().isKeyPressedOnce( KeyEvent.VK_ENTER )) {
                if (Main.getActiveTextBox() != null) {
                    Main.getActiveTextBox().onEnter();
                }
            }
            if (Main.getKeyboard().isKeyPressedOnce( KeyEvent.VK_ESCAPE )) {
                if (Main.getActiveTextBox() != null) {
                    Main.setActiveTextBox(null);
                } 


                if (Main.getActiveScreen().getName().equals("Exit Screen")) {
                    Main.popVisibleScreens();
                } else {
                    Main.pushVisibleScreens(Main.getActiveScreen().getSubScreens().get(0));
                }
                for (int i = 0; i < Main.getVisibleScreens().size(); i++) {
                    System.out.print(Main.getVisibleScreens().get(i).getName() + " ");
                }
                System.out.println();
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