package Drawing;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class MyOval implements MyShape{
    private Point2D.Double first;
    private Point2D.Double second;
    private Paint color;
    private boolean filled;
    private Stroke stroke;

    public MyOval(Point2D.Double first,Point2D.Double second,Paint c,boolean filled,Stroke stroke){
        this.first = first;
        this.second = second;
        color = c;
        this.filled = filled;
        this.stroke = stroke;
    }

    public void checkPoints(){
        checkPoints();
        if(first.getY() > second.getY() && first.getX() > second.getX()){
            Point2D.Double temp = first;
            first = second;
            second = temp;
        }
        else if(first.getX() > second.getX() && first.getY() < second.getY()){
            double temp = first.getX();
            first.x = second.x;
            second.x = temp;
        }
        else if(first.getX() < second.getX() && first.getY() > second.getY()){
            double temp = first.getY();
            first.y = second.getY();
            second.y = temp;
        }
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }
    public void setFirstPoint(double x,double y){
        first = new Point2D.Double(x,y);
    }

    public void setSecondPoint(double x,double y){
        second = new Point2D.Double(x,y);
    }

    public void draw(Graphics2D g2d){
        Ellipse2D.Double ellipse = new Ellipse2D.Double(first.getX(),first.getY(),
                second.getX() - first.getX(),second.getY()-first.getY());
        g2d.setPaint(color);
        g2d.setStroke(stroke);

        if(filled)
            g2d.fill(ellipse);
        else  g2d.draw(ellipse);
    }
}
