public class ButtonExitNo extends Button {
    public ButtonExitNo (boolean visible, int x, int y, int width, int height, String name) {
        super(visible, x, y, width, height, name);
    }

    public void onClick() { //WILL CRASH IF SCREEN DOES NOT HAVE ONE SCREEN
        Main.popVisibleScreens();
    }
}