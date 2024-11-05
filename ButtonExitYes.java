import java.awt.image.BufferedImage;

public class ButtonExitYes extends Button {
    public ButtonExitYes (boolean active, int x, int y, int width, int height, String name, BufferedImage img) {
        super(active, x, y, width, height, name, img);
    }

    public void onClick() {
        Main.setGameOver(true);
    }
}
