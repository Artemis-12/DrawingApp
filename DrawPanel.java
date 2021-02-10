package Drawing;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.util.LinkedList;
import javax.swing.JComponent;

public class DrawPanel extends JPanel{
    public final int LINE = 0;
    public final int RECTANGLE = 1;
    public final int OVAL = 2;

    private LinkedList<MyShape> shapes = new LinkedList<MyShape>();
    private int shapeType;
    private MyShape currentShape;
    private Paint currentColor;
    private boolean filledShape;
    private JLabel statusLabel;
    private Stroke currentStroke;

    public DrawPanel(JLabel statusLabel){
        this.statusLabel = statusLabel;
        shapeType = LINE;
        currentShape = null;
        currentColor = Color.BLACK;
        currentStroke  = new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        setBackground(Color.WHITE);
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseHandler());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for(MyShape shape : shapes)
            shape.draw(g2d);

        if(currentShape != null)
            currentShape.draw(g2d);
    }

    public void setShapeType(int shapeType){
        if(shapeType < 0 || shapeType > 2)
            throw new IllegalArgumentException("Shape Type must be a number between 1 and 4.");
        this.shapeType = shapeType;
    }

    public void setColor(Paint c){
        currentColor = c;
    }

    public void setStroke(Stroke h){
        this.currentStroke = h;
    }

    public void setFilledShape(boolean filled){
        filledShape = filled;
    }

    public void clearLastShape(){
        if(!shapes.isEmpty()) {
            shapes.removeLast();
            repaint();
        }
    }

    public void clearAll(){
        if(!shapes.isEmpty()) {
            shapes.clear();
            repaint();
        }
    }

    class MouseHandler extends MouseAdapter implements MouseMotionListener{
        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();

            if(shapeType == LINE)
                currentShape = new MyLine(new Point2D.Double(x,y),new Point2D.Double(x,y),
                        currentColor,currentStroke);
            else if(shapeType == RECTANGLE)
                currentShape = new MyRectangle(new Point2D.Double(x,y),new Point2D.Double(x,y),
                        currentColor,filledShape,currentStroke);
            else if(shapeType == OVAL)
                currentShape = new MyOval(new Point2D.Double(x,y),new Point2D.Double(x,y),currentColor,
                        filledShape,currentStroke);
        }

        public void mouseReleased(MouseEvent e){
            int x = e.getX();
            int y = e.getY();

            currentShape.setSecondPoint(x,y);
            shapes.add(currentShape);
            currentShape = null;
            repaint();
        }

        public void mouseDragged(MouseEvent e){
            int x = e.getX();
            int y = e.getY();

            currentShape.setSecondPoint(x,y);

            if(statusLabel != null)
                statusLabel.setText("(" + x + ","+ y + ")");
            repaint();
        }

        public void mouseMoved(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            if(statusLabel != null)
                statusLabel.setText("(" + x + "," + y + ")");
        }
    }
}
