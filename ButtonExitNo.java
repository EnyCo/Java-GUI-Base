public class ButtonExitNo extends GUIComponent {
    public ButtonExitNo (boolean active, int x, int y, int width, int height, String text) {
        super(active, x, y, width, height, text);
    }

    public ButtonExitNo (boolean active, int x, int y, int width, int height) {
        super(active, x, y, width, height);
    }

    public void onClick() { //WILL CRASH IF SCREEN DOES NOT HAVE THESE TWO BUTTONS
        Main.getActiveScreen().getButtons().get(0).setActive(false);// sets no button to off
        Main.getActiveScreen().getButtons().get(1).setActive(false);// sets yes button to off
    }
}
