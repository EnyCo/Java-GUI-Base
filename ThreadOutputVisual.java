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
        Color background = Color.LIGHT_GRAY;
        
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
                g2d.fillRect(Main.getActiveScreen().getX(),
                    Main.getActiveScreen().getY(),
                    Main.getActiveScreen().getWidth(),
                    Main.getActiveScreen().getHeight());
                
                /*for (int i = 0; i < Main.getActiveScreen().getComponents().size(); i++) {
                    for (int j = 0; j < Main.getActiveScreen().getComponents().get(i).size(); j++) {
                        if (Main.getActiveScreen().getComponents().get(i).get(j).getActive()){
                            if (Main.getActiveScreen().getComponents().get(i).get(j).getImg() == null) {
                                g2d.fillRect(Main.getActiveScreen().getComponents().get(i).get(j).getX(), Main.getActiveScreen().getComponents().get(i).get(j).getY(), 
                                Main.getActiveScreen().getComponents().get(i).get(j).getWidth(), Main.getActiveScreen().getComponents().get(i).get(j).getHeight());
                                
                                g2d.setColor( Color.BLACK );
                                if (!Main.getActiveScreen().getComponents().get(i).get(j).getName().equals("")) { // potentially fails due to getActiveScreen() changing in the fraction of a second between checks.
                                    Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                                        Main.getActiveScreen().getComponents().get(i).get(j).getName()).getPixelBounds(null, 
                                            Main.getActiveScreen().getComponents().get(i).get(j).getX(), 
                                            Main.getActiveScreen().getComponents().get(i).get(j).getY());
                                    g2d.drawString(Main.getActiveScreen().getComponents().get(i).get(j).getName(), 
                                        Main.getActiveScreen().getComponents().get(i).get(j).getX() + Main.getActiveScreen().getComponents().get(i).get(j).getWidth()/2 - (int)(text.getWidth()/2),
                                        Main.getActiveScreen().getComponents().get(i).get(j).getY() + Main.getActiveScreen().getComponents().get(i).get(j).getHeight()/2 + (int)(text.getHeight()/2)); 
                                    g2d.drawRect(Main.getActiveScreen().getComponents().get(i).get(j).getX(), Main.getActiveScreen().getComponents().get(i).get(j).getY(), 
                                        Main.getActiveScreen().getComponents().get(i).get(j).getWidth(), Main.getActiveScreen().getComponents().get(i).get(j).getHeight());
                                        
                                }

                                //g2d.drawString(Main.t, 100, 100);
                                g2d.setColor( Color.WHITE );
                            } else {
                                g2d.drawImage(Main.getActiveScreen().getComponents().get(i).get(j).getImg(), 
                                    Main.getActiveScreen().getComponents().get(i).get(j).getX(),
                                    Main.getActiveScreen().getComponents().get(i).get(j).getY(),
                                    null);
                            }
                        }
                    }
                }*/
                drawImages(g2d);
                drawButtons(g2d);
                drawTextBoxes(g2d);
                

                
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

    private void drawTextBoxes(Graphics2D g2d) {
        for (int i = 0;i < Main.getActiveScreen().getTextboxes().size(); i++) {
            if (Main.getActiveScreen().getTextboxes().get(i).getActive()){
                g2d.setColor( Color.BLACK );
                g2d.setFont(new Font(g2d.getFont().getName(), Font.PLAIN, Main.getActiveScreen().getTextboxes().get(i).getHeight() - 4)); 

                String output = Main.getActiveScreen().getTextboxes().get(i).getText();
                Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                    output).getPixelBounds(null, 
                        Main.getActiveScreen().getTextboxes().get(i).getX(), 
                        Main.getActiveScreen().getTextboxes().get(i).getY());
                
                while (text.getWidth() > Main.getActiveScreen().getTextboxes().get(i).getWidth()) {
                    output = output.substring(1);//no catch if textbox is 0
                    text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                    output).getPixelBounds(null, 
                        Main.getActiveScreen().getTextboxes().get(i).getX(), 
                        Main.getActiveScreen().getTextboxes().get(i).getY());
                }
                
                g2d.drawString(output, 
                    Main.getActiveScreen().getTextboxes().get(i).getX() + Main.getActiveScreen().getTextboxes().get(i).getWidth()/2 - (int)(text.getWidth()/2),
                    Main.getActiveScreen().getTextboxes().get(i).getY() + Main.getActiveScreen().getTextboxes().get(i).getHeight()/2 + (int)(text.getHeight()/2)); 
                
                g2d.drawRect(Main.getActiveScreen().getTextboxes().get(i).getX(), Main.getActiveScreen().getTextboxes().get(i).getY(), 
                    Main.getActiveScreen().getTextboxes().get(i).getWidth(), Main.getActiveScreen().getTextboxes().get(i).getHeight());
                
                
                if (Main.getActiveTextBox() != null) {
                    // drawing the text bar
                    if (Main.getActiveTextBox().getSeeBar()) {
                        g2d.drawRect((int)(Main.getActiveScreen().getTextboxes().get(i).getX() + Main.getActiveScreen().getTextboxes().get(i).getWidth()/2 - (int)(text.getWidth()/2) + text.getWidth() + 1),
                            (int)(Main.getActiveScreen().getTextboxes().get(i).getY() + 3),// 3 may be possible to caluclate? (1 for line + 1 for space + 1 for top of bar?) 
                            0, 
                            g2d.getFont().getSize() - 2);
                    }
                }
            }
        }
    }

    private void drawButtons(Graphics2D g2d) {
        for (int i = 0;i < Main.getActiveScreen().getButtons().size(); i++) {
            if (Main.getActiveScreen().getButtons().get(i).getActive()){
                if (Main.getActiveScreen().getButtons().get(i).getImg() != null) {
                    g2d.drawImage(Main.getActiveScreen().getButtons().get(i).getImg(), 
                    Main.getActiveScreen().getButtons().get(i).getX(),
                    Main.getActiveScreen().getButtons().get(i).getY(),
                    null);
                } else {
                    g2d.setColor( Color.BLACK );
                    Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                        Main.getActiveScreen().getButtons().get(i).getName()).getPixelBounds(null, 
                            Main.getActiveScreen().getButtons().get(i).getX(), 
                            Main.getActiveScreen().getButtons().get(i).getY());
                    g2d.drawString(Main.getActiveScreen().getButtons().get(i).getName(), 
                        Main.getActiveScreen().getButtons().get(i).getX() + Main.getActiveScreen().getButtons().get(i).getWidth()/2 - (int)(text.getWidth()/2),
                        Main.getActiveScreen().getButtons().get(i).getY() + Main.getActiveScreen().getButtons().get(i).getHeight()/2 + (int)(text.getHeight()/2)); 
                    g2d.drawRect(Main.getActiveScreen().getButtons().get(i).getX(), Main.getActiveScreen().getButtons().get(i).getY(), 
                        Main.getActiveScreen().getButtons().get(i).getWidth(), Main.getActiveScreen().getButtons().get(i).getHeight());
                }
            }
        }
    }

    private void drawImages(Graphics2D g2d) { // needs to be fixed IMAGE GUI COMP NEEDS TO BE ADDED
        /*for (int i = 0;i < Main.getActiveScreen().getComponents().get(0).size(); i++) {
            g2d.drawImage(Main.getActiveScreen().getComponents().get(0).get(i).getImg(), 
            Main.getActiveScreen().getComponents().get(0).get(i).getX(),
            Main.getActiveScreen().getComponents().get(0).get(i).getY(),
            null);
        }*/
    }
} 
