import java.awt.*;
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;

public class ThreadOutputVisual implements Runnable {    
    @Override
	public void run() {
		Main.getCanvas().createBufferStrategy( 2 );
        BufferStrategy buffer = Main.getCanvas().getBufferStrategy();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage bi = gc.createCompatibleImage( Main.getWidthScreen(), Main.getHeightScreen() );
        
        Graphics graphics = null;
        Graphics2D g2d = null;
        Color background = Color.WHITE;
        
        while(true) {
            try {
                // Clear the back buffer          
                g2d = bi.createGraphics();
                g2d.setColor( background );
                g2d.fillRect( 0, 0, Main.getWidthScreen(), Main.getHeightScreen() );                

                //Point p = Main.getMouse().getPosition(); 
                //int x = (int)(p.getX());
                //int y = (int)p.getY();

                for (Map.Entry<String, Screen> entry : Main.getScreens().entrySet()) {
                    entry.getValue().drawGUIcomponent(g2d);
                }
                
                graphics = buffer.getDrawGraphics();
                graphics.drawImage( bi, 0, 0, null );
                if( !buffer.contentsLost() )
                    buffer.show();
                
                try {
					Thread.sleep(Main.getThreadSleep());
				} catch (InterruptedException e) {
					System.out.println("OUTPUT CLOCK NOT WORKING");
					e.printStackTrace();
				}
            } finally {
                // Release resources
                if( graphics != null ) 
                    graphics.dispose();
                if( g2d != null ) 
                    g2d.dispose();
            }
        }
	}
}
