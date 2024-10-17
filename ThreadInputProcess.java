import java.awt.*;
import java.awt.event.*;
//import java.awt.image.*;
//import java.io.*;
//import java.awt.datatransfer.*;
//import java.awt.geom.Point2D;
//import java.awt.image.*;
//import java.util.*;
//import javax.imageio.*;
//import javax.swing.*;

public class ThreadInputProcess implements Runnable {
    @Override
    public void run() {
        while (true) {
            Main.getKeyboard().updateKeyStates();// Poll the keyboard
            Main.getMouse().updateButtonStates();// poll the mouse
                
            Point p = Main.getMouse().getPosition(); 
            //int x = (int)(p.getX());
            //int y = (int)p.getY();
            
            if (Main.getKeyboard().keyDownOnce( KeyEvent.VK_ESCAPE )) {
                Main.setGameOver(true);
            }
        }
    }
}