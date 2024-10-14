public class Button extends Entity{
    private String file;

    public Button (int x, int y, int w, int h, String f) {
        super (x, y, w, h);
        file = f;
    }
    public Button (int x, int y, int w, int h) {
        super (x, y, w, h);
        file = "";
    }

    public void setFile(String f) {
        file = f;
    }
    public String getFile() {
        return file;
    }    
}
