import java.awt.*;

public abstract class GUIComponent {
    protected boolean visible;
    protected int x, y;
    protected int width, height;
    protected String name;

    public GUIComponent(boolean visible, int x, int y, int width, int height, String name) {
        this.visible = visible;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void drawGUIComponent(Graphics2D g2d){
        if (visible) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(x-width/2 + 5, y-height/2 + 5, width, height);

            g2d.setColor( Color.WHITE );
            g2d.fillRect(x-width/2, y-height/2, width, height);
    
    
            g2d.setColor( Color.BLACK );
            Rectangle text = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                name).getPixelBounds(null, x, y);
            g2d.drawString(name, 
                x - (int)(text.getWidth()/2),
                y + (int)(text.getHeight()/2)); 
            g2d.drawRect(x-width/2, y-height/2, width, height);
        }
    }

    public boolean getVisible() {
        return visible;
    }
    public synchronized void setVisible(boolean vis){
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
