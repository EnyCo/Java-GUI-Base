import java.awt.image.BufferedImage;

public class ButtonExitNo extends Button {
    public ButtonExitNo (boolean active, int x, int y, int width, int height, BufferedImage img, String text) {
        super(active, x, y, width, height, img, text);
    }

    public void onClick() { //WILL CRASH IF SCREEN DOES NOT HAVE THESE TWO BUTTONS
        try {
            Main.getVisibleScreens().pop();

            Main.getActiveScreen().getSubScreens().get(0).setVisible(false);
        } catch (Exception e) {
            System.out.println("missing default buttons");
        }
    }
}
