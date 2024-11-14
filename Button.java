import java.awt.image.*;

abstract public class Button extends GUIComponentVis {
    protected int x, y;
    protected BufferedImage img = null;
    
    public Button (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
        this.x = x - width/2;
        this.y = y - height/2;
    }

    public boolean isHover(int x2, int y2) {
        return x2 >= x && y2 >= y 
            && x2 <= x + width && y2 <= y + height;
    }

    public void onClick() {
        System.out.println("default abstract button click");
    }
}
