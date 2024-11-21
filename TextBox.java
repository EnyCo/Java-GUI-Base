import java.awt.*;

public class TextBox extends GUIComponent {
    private int x, y;
    private String text = "";
    private boolean seeBar = true;
    public TextBox (boolean visible, int x, int y, int width, int height, String name, String text) {
        super(visible, x, y, width, height, name);
        this.x = x - width/2;
        this.y = y - height/2;
        this.text = text;
    }
    
    public void drawGUIComponent(Graphics2D g2d) {
        if (visible){
            super.drawGUIComponent(g2d);
            
            g2d.setFont(new Font(g2d.getFont().getName(), Font.PLAIN, height - 4));
            g2d.setColor( Color.WHITE );
            g2d.fillRect(x, y, width, height);
            g2d.setColor( Color.BLACK );
            g2d.drawRect(x, y, width, height);

            String output = text;
            if (text.length() == 0) { // if nothing inside display textbox name
                output = name;
            }

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


    public void append(char text){
        this.text += text;
    }

    public void remend(){
        if (text.length() > 0){
            text = text.substring(0, text.length() - 1);
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
        //System.out.println(text);
        return text;
    }

}
