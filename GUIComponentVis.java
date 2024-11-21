import java.awt.*;
import java.awt.image.*;

public abstract class GUIComponentVis extends GUIComponent {
    protected int x, y;
    protected BufferedImage img = null;
    public GUIComponentVis(boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
        this.x = x - width/2;
        this.y = y - height/2;
    }

    public BufferedImage getImg(){
        return img;
    }
    public void setImg(BufferedImage img){
        this.img = img;
    }

    public void drawGUIComponent(Graphics2D g2d) {
        if (visible){
            if (img == null) {
                super.drawGUIComponent(g2d);
            } else {
                /*ColorModel cm = img.getColorModel();
                boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
                WritableRaster raster = img.copyData(null);
                BufferedImage cpy =  new BufferedImage(cm, raster, isAlphaPremultiplied, null);
                
                for (int y = 0; y < cpy.getHeight(); y++) {
                    for (int x = 0; x < cpy.getWidth(); x++) {
                        int color = cpy.getRGB(x, y);
                        int alpha = (color >> 24) & 0xff;

                        if (alpha == 255) {
                            cpy.setRGB(x, y, Color.BLACK.getRGB());
                        }

                        if (alpha > 0) { // If the pixel is not fully transparent
                            cpy.setRGB(x, y, 0xFF000000); // Set the pixel to black
                        }
                    }
                }
                g2d.drawImage(cpy, x+5, y+5, null);*/
                
                g2d.drawImage(img, x, y, null);
            }          
        }
    }

    public boolean getVisible() {
        return visible;
    }
    public void setVisible(boolean vis){
        visible = vis;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public String getName() {
        return name;
    }
}
