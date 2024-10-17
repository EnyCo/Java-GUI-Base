public class Button {
    private int x, y;
    private int width, height;
    private Command command;

    public Button (int x, int y, int width, int height, Command command) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.command = command;
    }

    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getWidth(){
        return width;
    }
    public void setWidth(int width){
        this.width = width;
    }

    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public boolean isHover(int x2, int y2) {
        return x2 >= x && y2 >= y 
            && x2 <= x + width && y2 <= y + height;
    }

    public void onClick() {
        command.execute();
    }
    public void onClick(int param) {
        command.execute(param);
    }
}
