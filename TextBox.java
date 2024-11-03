import java.awt.*;

public class TextBox extends GUIcomponent {
    private boolean active;
    private int x, y;
    private int width, height;
    private String text = "";
    private boolean seeBar = true;
    public TextBox (boolean active, int x, int y, int width, int height, String text) {
        super(active, x, y, width, height);
        this.active = active;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
    
    public void drawGUIcomponent(Graphics2D g2d) {
        if (active){
            g2d.setColor( Color.BLACK );
            g2d.setFont(new Font(g2d.getFont().getName(), Font.PLAIN, height - 4)); 

            String output = text;
            Rectangle box = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                output).getPixelBounds(null, x, y);
                
            while (box.getWidth() + 2 + 2 >= width) {// -2 for each side?
                output = output.substring(1);//no catch if textbox is 0
                box = g2d.getFont().createGlyphVector(g2d.getFontRenderContext(), 
                output).getPixelBounds(null, x, y);
            }
                
            g2d.drawString(output, 
                x + width/2 - (int)(box.getWidth()/2),
                y + height/2 + (int)(box.getHeight()/2)); 
                
            g2d.drawRect(x, y, width, height);
                
                
            if (Main.getActiveTextBox() != null) {
                // drawing the text bar
                if (Main.getActiveTextBox().getSeeBar()) {
                    g2d.drawRect((int)(x + width/2 - (int)(box.getWidth()/2) + box.getWidth() + 1),
                        (int)(y + 3),// 3 may be possible to caluclate? (1 for line + 1 for space + 1 for top of bar?) 
                        0, 
                        g2d.getFont().getSize() - 2);
                }
            }
        }
    }

    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

    public void append(char text){
        this.text += text;
    }

    public void remend(){
        if (text.length() - 1 >= 0){
            setText(text.substring(0, text.length() - 1));
        }
    }

    public boolean getSeeBar(){
        return seeBar;
    }
    public void setSeeBar(boolean seeBar){
        this.seeBar = seeBar;
    }

    public boolean isHover(int x2, int y2) {
        return x2 >= x && y2 >= y 
            && x2 <= x + width && y2 <= y + height;
    }

    public void onClick() {
        Main.setActiveTextBox(this);
    }

    public String onEnter() {
        Main.setActiveTextBox(null);
        return getText();
    }
}
