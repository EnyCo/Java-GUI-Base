import java.awt.*;

public abstract class GUIcomponent {
    private boolean visible = false;
    private int x, y;
    private int width, height;

    public GUIcomponent(boolean visible, int x, int y, int width, int height) {
        this.visible = visible;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void drawGUIcomponent(Graphics2D g2d);

    public boolean getVisible() {
        return visible;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
