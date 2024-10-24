import java.awt.image.BufferedImage;

public class ButtonTestStart extends GUIComponent {
    public ButtonTestStart (boolean active, int x, int y, int width, int height, BufferedImage img, String text) {
        super(active, x, y, width, height, img, text);
    }

    public void onClick() {
        Main.setActiveScreen(Main.SCREENS.get(1));
    }
}
