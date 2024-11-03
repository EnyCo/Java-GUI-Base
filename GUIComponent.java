public abstract class GUIcomponent {
    private boolean active = false;
    private int x, y;
    private int width, height;

    public GUIcomponent(boolean active, int x, int y, int width, int height) {
        this.active = active;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //public abstract void drawGUIcomponent();

    public boolean getActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public int getX(){
        return x;
    }
    /*public void setX(int x){
        this.x = x;
    }*/

    public int getY(){
        return y;
    }
    /*public void setY(int y){
        this.y = y;
    }*/

    public int getWidth(){
        return width;
    }
    /*public void setWidth(int width){
        this.width = width;
    }*/

    public int getHeight(){
        return height;
    }
    /*public void setHeight(int height){
        this.height = height;
    }*/
}
