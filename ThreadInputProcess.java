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
                for (int i = 0; i < Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getButtons().size(); i++) {
                    if (Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getButtons().get(i).getActive()) {
                        if (Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getButtons().get(i).isHover(x, y)){
                            Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getButtons().get(i).onClick();
                        }
                    }
                }
                
                if (Main.getActiveTextBox() != null && !Main.getActiveTextBox().isHover(x, y)) {
                    Main.setActiveTextBox(null);
                }
                for (int i = 0; i < Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getTextboxes().size(); i++) {
                    if (Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getTextboxes().get(i).getActive()) {
                        if (Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getTextboxes().get(i).isHover(x, y)){
                            Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getTextboxes().get(i).onClick();
                        }
                    }
                }
            }            
            
            if (Main.getKeyboard().isKeyPressedOnce( KeyEvent.VK_ESCAPE )) {if (Main.getActiveScreens().size() == 1) {//if one page opens exit dialogue
                    Main.getActiveScreens().get(Main.getActiveScreens().size() - 1).getSubScreens().get(0).setActive(true);
                } else {
                    Main.getActiveScreens().remove(Main.getActiveScreens().size() - 1);
                }
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