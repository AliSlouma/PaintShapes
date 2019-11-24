package eg.edu.alexu.csd.oop.draw.GUI;

import javax.swing.*;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.controller.drawingEngine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ToolBar extends JPanel {
    private Color color;
    private Color borderColor;
    private static ToolBar toolBar;
    private JFrame canvas;
    private  final int MAXWIDTH = 45;
    private final int MAXHEIGHT = 45;
    private drawingEngine engine ;

    private ToolBar(DrawingEngine engine , JFrame Jcanvas)
    {
     //   Color[] colors = new Color[]{Color.orange, Color.WHITE, Color.CYAN, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.BLACK, Color.PINK, Color.YELLOW};
    	this.canvas = Jcanvas;
    	this.engine = (drawingEngine) engine;
        toolBar = null;
        borderColor = Color.WHITE;
        color = Color.black;
        this.setBounds(10, 115, 732, 55);
        this.setLayout(null);
        initialize();
    }


    public static synchronized ToolBar makeInstance(drawingEngine engine , JFrame Jcanvas)
    {
       if(toolBar == null) {
    	   toolBar = new ToolBar(engine , Jcanvas);
       }
    	   return toolBar;
       
    }

    private void initialize()
    {
        JButton sizebut = new JButton("Resize");
        JButton movebut = new JButton("Move");
        JButton undobut = new JButton("Undo");
        JButton redobut = new JButton("Redo");
        JButton delete = new JButton("Delete");
        JButton select = new JButton("Select");

        ColorChooserButton colorChooser = new ColorChooserButton(Color.BLACK, this);


        ColorChooserButton colorborderChooser = new ColorChooserButton(Color.WHITE, this);

        colorborderChooser.addActionListener(e -> canvas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
        colorChooser.addActionListener(e -> canvas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));

        this.setBackground(Color.darkGray);
        colorChooser.setSize(MAXWIDTH, MAXHEIGHT);
        colorborderChooser.setSize(MAXWIDTH, MAXHEIGHT);

        colorChooser.setBounds(290, 30, 80, 50);
        colorborderChooser.setBounds(380, 30, 80, 50);

        sizebut.setBounds(105, 55, 85, 40);
        movebut.setBounds(105, 10, 85, 40);
        sizebut.setBackground(Color.LIGHT_GRAY);
        movebut.setBackground(Color.LIGHT_GRAY);

        undobut.setBounds(10, 10, 85, 40);
        redobut.setBounds(10, 55, 85, 40);
        undobut.setBackground(Color.LIGHT_GRAY);
        redobut.setBackground(Color.LIGHT_GRAY);

        delete.setBounds(200,10,85,40);
        delete.setBackground(Color.LIGHT_GRAY);

        colorChooser.setBackground(Color.LIGHT_GRAY);

        select.setBackground(Color.LIGHT_GRAY);
        select.setBounds(200, 55, 85, 40);

        this.add(select);
        this.add(colorborderChooser);
        this.add(sizebut);
        this.add(movebut);
        this.add(undobut);
        this.add(redobut);
        this.add(colorChooser);
        this.add(delete);

        redobut.addActionListener(e -> {
            engine.redo();
            canvas.repaint();

        });
        undobut.addActionListener(e -> {
            engine.undo();
            canvas.repaint();
        });
        select.addActionListener(e -> canvas.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR)));
        delete.addActionListener(e -> canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)));
		movebut.addActionListener(e -> canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)));
		sizebut.addActionListener(e -> canvas.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR)));


    }

    public Color getColor () {
    	return color;
    }
    public Color getBorderColor()
    {
        return borderColor;
    }

    void setColor(Color color) {
        this.color = color;
    }
    void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}
