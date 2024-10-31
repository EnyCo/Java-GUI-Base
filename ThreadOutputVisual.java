import java.awt.*;
import java.awt.image.*;

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

                g2d.setColor( Color.LIGHT_GRAY );
                for (int i = 0; i < Main.getActiveScreens().size(); i++) {
                    drawScreen(g2d, Main.getActiveScreens().get(i));
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

    private void drawScreen(Graphics2D g2d, Screen activeScreen) {
        g2d.setColor(activeScreen.getBackground());
        g2d.fillRect(activeScreen.getX(),
            activeScreen.getY(),
            activeScreen.getWidth(),
            activeScreen.getHeight());
            
        drawImages(g2d, activeScreen);
        drawButtons(g2d, activeScreen);
        drawTextBoxes(g2d, activeScreen);
        for (int i = 0; i < activeScreen.getSubScreens().size(); i++) {
            if (activeScreen.getSubScreens().get(i).getActive()){
                drawScreen(g2d, activeScreen.getSubScreens().get(i));
            }
        }  
    }

    private void drawTextBoxes(Graphics2D g2d, Screen screen) {
        for (int i = 0;i < screen.getTextboxes().size(); i++) {
            if (screen.getTextboxes().get(i).getActive()){
                g2d.setColor( Color.BLACK );
                g2d.setFont(new Font(g2d.getFont().getName(), Font.PLAIN, screen.getTextboxes().get(i).getHeight() - 4)); 

                String output = screen.getTextboxes().get(i).getText();
                Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                    output).getPixelBounds(null, 
                        screen.getTextboxes().get(i).getX(), 
                        screen.getTextboxes().get(i).getY());
                
                while (text.getWidth() + 2 + 2 >= screen.getTextboxes().get(i).getWidth()) {// -2 for each side?
                    output = output.substring(1);//no catch if textbox is 0
                    text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                    output).getPixelBounds(null, 
                        screen.getTextboxes().get(i).getX(), 
                        screen.getTextboxes().get(i).getY());
                }
                
                g2d.drawString(output, 
                    screen.getTextboxes().get(i).getX() + screen.getTextboxes().get(i).getWidth()/2 - (int)(text.getWidth()/2),
                    screen.getTextboxes().get(i).getY() + screen.getTextboxes().get(i).getHeight()/2 + (int)(text.getHeight()/2)); 
                
                g2d.drawRect(screen.getTextboxes().get(i).getX(), screen.getTextboxes().get(i).getY(), 
                    screen.getTextboxes().get(i).getWidth(), screen.getTextboxes().get(i).getHeight());
                
                
                if (Main.getActiveTextBox() != null) {
                    // drawing the text bar
                    if (Main.getActiveTextBox().getSeeBar()) {
                        g2d.drawRect((int)(screen.getTextboxes().get(i).getX() + screen.getTextboxes().get(i).getWidth()/2 - (int)(text.getWidth()/2) + text.getWidth() + 1),
                            (int)(screen.getTextboxes().get(i).getY() + 3),// 3 may be possible to caluclate? (1 for line + 1 for space + 1 for top of bar?) 
                            0, 
                            g2d.getFont().getSize() - 2);
                    }
                }
            }
        }
    }

    private void drawButtons(Graphics2D g2d, Screen screen) {
        for (int i = 0;i < screen.getButtons().size(); i++) {
            if (screen.getButtons().get(i).getActive()){
                if (screen.getButtons().get(i).getImg() != null) {
                    g2d.drawImage(screen.getButtons().get(i).getImg(), 
                    screen.getButtons().get(i).getX(),
                    screen.getButtons().get(i).getY(),
                    null);
                } else {
                    g2d.setColor( Color.BLACK );
                    Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                        screen.getButtons().get(i).getName()).getPixelBounds(null, 
                            screen.getButtons().get(i).getX(), 
                            screen.getButtons().get(i).getY());
                    g2d.drawString(screen.getButtons().get(i).getName(), 
                        screen.getButtons().get(i).getX() + screen.getButtons().get(i).getWidth()/2 - (int)(text.getWidth()/2),
                        screen.getButtons().get(i).getY() + screen.getButtons().get(i).getHeight()/2 + (int)(text.getHeight()/2)); 
                    g2d.drawRect(screen.getButtons().get(i).getX(), screen.getButtons().get(i).getY(), 
                        screen.getButtons().get(i).getWidth(), screen.getButtons().get(i).getHeight());
                }
            }
        }
    }

    private void drawImages(Graphics2D g2d, Screen screen) { 
        for (int i = 0;i < screen.getImgs().size(); i++) {
            g2d.drawImage(screen.getImgs().get(i).getImg(), 
            screen.getImgs().get(i).getX(),
            screen.getImgs().get(i).getY(),
            null);
        }
    }
} 
