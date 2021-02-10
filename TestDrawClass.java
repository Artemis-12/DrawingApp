package Drawing;
import javax.swing.JFrame;

public class TestDrawClass {
    public static void main(String[] args){
        JFrame drawingFrame = new DrawFrame();
        drawingFrame.setTitle("Shape Painter");
        drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingFrame.setVisible(true);
        drawingFrame.pack();
    }
}
