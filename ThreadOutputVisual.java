import java.awt.*;
import java.awt.image.*;

public class ThreadOutputVisual implements Runnable {
    private final static int grid_size = Main.getHeightScreen();
    
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
        Color background = Color.DARK_GRAY;
        
        while(true) {
            try {
                // Clear the back buffer          
                g2d = bi.createGraphics();
                g2d.setColor( background );
                g2d.fillRect( 0, 0, Main.getWidthScreen(), Main.getHeightScreen() );
                drawField(g2d);
                

                Point p = Main.getMouse().getPosition(); 
                int x = (int)(p.getX());
                int y = (int)p.getY();

                g2d.setColor( Color.WHITE );
                g2d.drawString(x + ", " + y, x, y);
                for (int i = 0; i < Main.getActiveScreen().getButtons().size(); i++) {
                    if (Main.getActiveScreen().getButtons().get(i).getActive()){
                        if (Main.getActiveScreen().getButtons().get(i).getImg() == null) {
                            g2d.fillRect(Main.getActiveScreen().getButtons().get(i).getX(), Main.getActiveScreen().getButtons().get(i).getY(), 
                            Main.getActiveScreen().getButtons().get(i).getWidth(), Main.getActiveScreen().getButtons().get(i).getHeight());
                            
                            g2d.setColor( Color.BLACK );
                            if (!Main.getActiveScreen().getButtons().get(i).getText().equals("")) { // potentially fails due to getActiveScreen() changing in the fraction of a second between checks.
                                Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                                    Main.getActiveScreen().getButtons().get(i).getText()).getPixelBounds(null, 
                                        Main.getActiveScreen().getButtons().get(i).getX(), 
                                        Main.getActiveScreen().getButtons().get(i).getY());
                                g2d.drawString(Main.getActiveScreen().getButtons().get(i).getText(), 
                                    Main.getActiveScreen().getButtons().get(i).getX() + Main.getActiveScreen().getButtons().get(i).getWidth()/2 - (int)(text.getWidth()/2),
                                    Main.getActiveScreen().getButtons().get(i).getY() + Main.getActiveScreen().getButtons().get(i).getHeight()/2 + (int)(text.getHeight()/2)); 
                                
                            }

                            //g2d.drawString(Main.t, 100, 100);
                            g2d.setColor( Color.WHITE );
                        } else {
                            if (!Main.getActiveScreen().getButtons().get(i).isHover(x, y)) {
                                g2d.drawImage(Main.getActiveScreen().getButtons().get(i).getImg(), 
                                    Main.getActiveScreen().getButtons().get(i).getX(),
                                    Main.getActiveScreen().getButtons().get(i).getY(),
                                    null);
                            } else {
                                g2d.drawImage(Main.getActiveScreen().getButtons().get(i).getImg(), 
                                    Main.getActiveScreen().getButtons().get(i).getX(),
                                    Main.getActiveScreen().getButtons().get(i).getY(),
                                    null);
                            }
                            
                        }
                    }
                }
                

                
                graphics = buffer.getDrawGraphics();
                graphics.drawImage( bi, 0, 0, null );
                if( !buffer.contentsLost() )
                    buffer.show();
                
                try {
					Thread.sleep(13);
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
	    
    /*
     * draws the actual canvas beyond menus
     */
	private static void drawField(Graphics g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(Main.getShift(), 0, grid_size, grid_size);
    }
} 
