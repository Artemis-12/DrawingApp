package Drawing;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class MyLine implements MyShape{
    private Point2D.Double first;
    private Point2D.Double second;
    private Paint color;
    private Stroke stroke;

    public MyLine(Point2D.Double first,Point2D.Double second,Paint c,Stroke stroke){
        this.first = first;
        this.second = second;
        color = c;
        this.stroke = stroke;

    }


    public void setFirstPoint(double x,double y){
        first = new Point2D.Double(x,y);
    }

    public void setSecondPoint(double x,double y){
        second = new Point2D.Double(x,y);
    }

    public void draw(Graphics2D g2d){
        g2d.setPaint(color);
        g2d.setStroke(stroke);
        Line2D.Double line = new Line2D.Double(first,second);
        g2d.draw(line);
    }
}
