import java.awt.*;

public class ButtonNext extends Button {
    //protected boolean visible;
    public ButtonNext (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
        //this.visible = visible;
    }
    
    public void drawGUIComponent(Graphics2D g2d) {
        if (visible){
            super.drawGUIComponent(g2d);
            if (img != null) {
                g2d.drawImage(img, x, y, null);
            }            
        }
    }

    public void onClick() { //WILL CRASH IF SCREEN DOES NOT EXIST
        try {
            String name = Main.popVisibleScreens().getName();
            int x = Integer.parseInt(name.substring(name.length() - 1)) + 1;
            //Main.popVisibleScreens();
            Main.pushVisibleScreens(Main.getScreens().get("Question " + x));
        } catch (Exception e) {
            System.out.println("NEXT DOESN'T EXIST");
        }
    }
}
