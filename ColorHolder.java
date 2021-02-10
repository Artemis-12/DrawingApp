package Drawing;
import java.awt.Color;

public class ColorHolder {
    private Color color1;
    private Color color2;

    public ColorHolder(Color color1,Color color2){
        this.color1 = color1;
        this.color2 = color2;
    }

    public void setColor1(Color color1){
        this.color1 = color1;
    }

    public void setColor2(Color color2){
        this.color2 = color2;
    }

    public Color getColor1(){
        return color1;
    }

    public Color getColor2(){
        return color2;
    }
}
