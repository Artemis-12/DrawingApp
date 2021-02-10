package Drawing;


public class StrokeInfo {
    private int width;
    private float dash;

    public StrokeInfo(int width,float dash){
        this.width = width;
        this.dash = dash;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setDash(float dash){
        this.dash = dash;
    }

    public int getWidth(){
        return width;
    }

    public float getDash(){
        return dash;
    }
}
