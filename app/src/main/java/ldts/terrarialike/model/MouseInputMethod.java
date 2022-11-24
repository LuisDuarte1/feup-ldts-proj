package ldts.terrarialike.model;

public class MouseInputMethod {

    private int Posx;

    private int Posy;

    private boolean right_click;

    private boolean left_click;

    public MouseInputMethod(int posx, int posy) {
        this.Posx = posx;
        this.Posy = posy;
    }

    public MouseInputMethod(boolean right_click, boolean left_click) {
        this.right_click = right_click;
        this.left_click = left_click;
    }

    //setters and getters
    public int getPosx() {
        return Posx;
    }
    public void setPosx(int posx) {
        this.Posx = posx;
    }
    public int getPosy() {
        return Posy;
    }
    public void setPosy(int posy) {
        this.Posy = posy;
    }

    public boolean getRight_click() {

        return right_click;
    }

    public boolean getLeft_click() {
        return left_click;
    }


    //



}
