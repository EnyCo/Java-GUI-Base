abstract public class TextBox extends GUIcomponent {
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
