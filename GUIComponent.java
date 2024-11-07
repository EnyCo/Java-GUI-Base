import java.awt.*;

public abstract class GUIComponent {
    private boolean visible = false;
    private int x, y;
    private int width, height;
    private String name;

    public GUIComponent(boolean visible, int x, int y, int width, int height, String name) {
        this.visible = visible;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public abstract void drawGUIComponent(Graphics2D g2d);

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

    public String getName() {
        return name;
    }
}
