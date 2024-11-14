public class ButtonAnswer extends Button {
    private boolean visible = false;
    private int value = 0;
    public ButtonAnswer (boolean visible, int x, int y, int width, int height, String name, int value) {
        super(visible, x, y, width, height, name);
        this.value = value;
        this.visible = visible;
    }

    public int getValue(){
        return value;
    }
    
    /*public void drawGUIComponent(Graphics2D g2d) {
        System.out.println("What the hell");
    }*/

    public void onClick() { //WILL CRASH IF SCREEN DOES NOT HAVE THESE TWO BUTTONS
        try {
            //System.out.print(Main.getActiveScreen().getButtons().get(2).getVisible() + " ");

            /*Main.getActiveScreen().getButtons().get(2).setVisible(//assumes 2 is next
                        !Main.getActiveScreen().getButtons().get(2).getVisible()
                    );//works but objectively wrong debug
            */

            Main.getActiveScreen().getButtons().get(2).setVisible(true);
            Main.getActiveScreen().getImgs().get(3).setVisible(true);

            //Main.score++;
        } catch (Exception e) {
            System.out.println("missing default buttons");
        }
    }
}
