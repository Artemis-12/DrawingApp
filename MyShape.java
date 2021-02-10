package Drawing;

import java.awt.*;

public interface MyShape {
    void draw(Graphics2D g2d);
    void setFirstPoint(double x,double y);
    void setSecondPoint(double x,double y);
}
