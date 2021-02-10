package Drawing;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.Flow;

public class DrawFrame extends JFrame{
    private DrawPanel drawPanel;
    private JLabel positionLabel;

   /* private final String[] colorNames = {"Black","Blue","Cyan","Dark Gray","Gray","Green","Light Gray",
    "Magenta","Orange","Pink","Red","White","Yellow"};

    private final Color[] color = {
            Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,
            Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.white,Color.YELLOW
    };*/


    public DrawFrame(){
        positionLabel = new JLabel("Coordinates");
        drawPanel = new DrawPanel(positionLabel);

        JPanel upperPanel = createUpperPanel();
        JPanel lowerPanel = createLowerPanel();
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(upperPanel);
        panel.add(lowerPanel);
        add(panel, BorderLayout.NORTH);
        add(drawPanel,BorderLayout.CENTER);
        add(positionLabel,BorderLayout.SOUTH);

        setPreferredSize(new Dimension(700,600));
    }

    public JPanel createUpperPanel(){
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        drawPanel.clearLastShape();
                    }
                }
        );

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.clearAll();
            }
        });

        JLabel shapeLabel = new JLabel("Shape: ");
        JComboBox<String> shapeBox = new JComboBox<String>();
        shapeBox.addItem("Line");
        shapeBox.addItem("Rectangle");
        shapeBox.addItem("Oval");
        shapeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                drawPanel.setShapeType(shapeBox.getSelectedIndex());
            }
        });

        JCheckBox checkBox = new JCheckBox("Filled");
        checkBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(checkBox.isSelected()){
                    drawPanel.setFilledShape(true);
                }
                else drawPanel.setFilledShape(false);
            }
        });

        JPanel upperPanel = new JPanel();
        upperPanel.add(undoButton);
        upperPanel.add(clearButton);
        upperPanel.add(shapeLabel);
        upperPanel.add(shapeBox);
        upperPanel.add(checkBox);
        return upperPanel;
    }

    public JPanel createLowerPanel(){
        final ColorHolder cHolder = new ColorHolder(Color.BLACK,Color.RED);

        JCheckBox gradientBox = new JCheckBox("Use Gradient");
        gradientBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(gradientBox.isSelected()){
                    drawPanel.setColor(new GradientPaint(0,0,cHolder.getColor1(),
                            50,50,cHolder.getColor2()));
                }
                else drawPanel.setColor(cHolder.getColor1());
            }
        });

        JButton color1Btn = new JButton("1st Color...");
        JButton color2Btn = new JButton("2nd Color...");


        class ButtonHandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(DrawFrame.this,"Choose Color 1",
                        cHolder.getColor1());
                if(c != null) {
                    if(e.getSource() == color1Btn)
                    cHolder.setColor1(c);
                    else cHolder.setColor2(c);
                }
                if(gradientBox.isSelected()){
                    drawPanel.setColor(new GradientPaint(0,0,cHolder.getColor1(),50,50,
                            cHolder.getColor2(),true));
                }
                else drawPanel.setColor(cHolder.getColor1());
            }
        }

        color1Btn.addActionListener(new ButtonHandler());
        color2Btn.addActionListener(new ButtonHandler());

        float dash = (float) 10.0;
        StrokeInfo stroke = new StrokeInfo(10,dash);

        JCheckBox dashedBox = new JCheckBox("Dashed Line");
        dashedBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(dashedBox.isSelected()){
                    try{
                        int width = stroke.getWidth();
                        float dashes[] = {stroke.getDash()};
                        drawPanel.setStroke(new BasicStroke(width,BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND,10,dashes,0));
                    }
                    catch(NumberFormatException exception){
                        System.out.println("Enter proper number!");
                    }
                }
            }
        });

        JLabel lineWidth = new JLabel("Line Width:");
        JTextField widthField = new JTextField(2);
        widthField.setText("10");

        JLabel dashLength = new JLabel("Dash Length:");
        JTextField dashField = new JTextField(2);
        dashField.setText("10");

        class FieldHandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stroke.setWidth(Integer.parseInt(widthField.getText()));
                    if (!dashedBox.isSelected()) {
                        drawPanel.setStroke(new BasicStroke(stroke.getWidth(), BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND));
                    } else {
                        stroke.setDash(Float.parseFloat(widthField.getText()));
                        float dashes[] = {stroke.getDash()};
                        drawPanel.setStroke(new BasicStroke(stroke.getWidth(), BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND, 10, dashes, 0));
                    }
                }
                catch (NumberFormatException exception){
                    System.out.println("Line Width needs to be a number!");
                }
            }
        }
        widthField.addActionListener(new FieldHandler());
        dashField.addActionListener(new FieldHandler());

        JPanel lowerPanel = new JPanel();
        lowerPanel.add(gradientBox);
        lowerPanel.add(color1Btn);
        lowerPanel.add(color2Btn);
        lowerPanel.add(lineWidth);
        lowerPanel.add(widthField);
        lowerPanel.add(dashLength);
        lowerPanel.add(dashField);
        lowerPanel.add(dashedBox);
        return lowerPanel;
    }
}




