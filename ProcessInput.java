import java.awt.*;
//import java.awt.image.*;
//import java.io.*;
//import java.awt.datatransfer.*;
//import java.awt.geom.Point2D;
//import java.awt.image.*;
//import java.util.*;
//import javax.imageio.*;
//import javax.swing.*;


public class ProcessInput implements Runnable {
    public void run()
    {
        while (true) {
            Main.keyboard.updateKeyStates();// Poll the keyboard
            Main.mouse.updateButtonStates();// poll the mouse
                
            Point p = Main.mouse.getPosition();
            int x = (int)(p.getX() - Main.RIGHT_SHIFT);
            int y = (int)p.getY();

            if (Main.mouse.buttonDownOnce(1)) {
                System.out.println(x + " : " + y);
            }
        }
    }
}