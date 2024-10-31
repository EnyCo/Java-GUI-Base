import java.awt.image.BufferedImage;

public class ButtonExitYes extends Button {
    public ButtonExitYes (boolean active, int x, int y, int width, int height, BufferedImage img, String text) {
        super(active, x, y, width, height, img, text);
    }

    public void onClick() {
        Main.setGameOver(true);
    }
}
