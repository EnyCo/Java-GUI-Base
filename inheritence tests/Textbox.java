public class Textbox extends Entity {
    boolean active;
    String text;
    public Textbox (int x, int y, int w, int h, boolean a, String t) {
        super (x, y, w, h);
        active = a;
        text = t;
    }

    public void setActive(boolean a) {
        active = a;
    }
    public boolean getActive() {
        return active;
    } 
    
    public void setText(String t) {
        text = t;
    }
    public String getText() {
        return text;
    }  
}
