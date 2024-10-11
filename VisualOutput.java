import java.awt.*;
import java.awt.image.*;

public class VisualOutput implements Runnable {
    private final static int grid_size = Main.HEIGHT;
    
	public void run() {
		Main.canvas.createBufferStrategy( 2 );
        BufferStrategy buffer = Main.canvas.getBufferStrategy();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage bi = gc.createCompatibleImage( Main.WIDTH, Main.HEIGHT );
        
        Graphics graphics = null;
        Graphics2D g2d = null;
        Color background = Color.DARK_GRAY;
        
        while(true) {
            try {
                
                // Clear the back buffer          
                g2d = bi.createGraphics();
                g2d.setColor( background );
                g2d.fillRect( 0, 0, Main.WIDTH, Main.HEIGHT );
                
                drawField(g2d);
                
                graphics = buffer.getDrawGraphics();
                graphics.drawImage( bi, 0, 0, null );
                if( !buffer.contentsLost() )
                    buffer.show();
                
                try {
					Thread.sleep(50);
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
	
	private static void drawField(Graphics g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(Main.RIGHT_SHIFT, 0, grid_size, grid_size);
        

	}
}
