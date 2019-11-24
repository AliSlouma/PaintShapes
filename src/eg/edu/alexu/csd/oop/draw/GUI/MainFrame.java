package eg.edu.alexu.csd.oop.draw.GUI;

import eg.edu.alexu.csd.oop.draw.controller.drawingEngine;

import java.awt.Color;

import javax.swing.*;


public class MainFrame extends JFrame {

    private MainFrame()
    {
        // latest version ya 3lolo
        drawingEngine engine = new drawingEngine();
        ButtonGui buttonGui = ButtonGui.makeInstance(engine, this);
        ToolBar toolBar = ToolBar.makeInstance(engine , this);
        DrawCanvas canvas = DrawCanvas.makeInstance(engine, buttonGui, toolBar, this);

        Menu menuu = new Menu(engine, this);

        menuu.setBounds(0,0,1200,30);

        JPanel canvaspanel = new JPanel();

        canvaspanel.setBounds(0, 130, 1200, 700 );
        canvaspanel.add(canvas);


        buttonGui.setBounds(0, 30, 700, 100);

        toolBar.setBounds(700, 30, 500, 100);

        getContentPane().add(buttonGui);
        getContentPane().add(canvaspanel);
        getContentPane().add(toolBar);
        getContentPane().add(menuu);


        getContentPane().setLayout(null);
        setBounds(0, 0, 1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Paint");
        setVisible(true);

    }

    public static void main(String[]args)
    {

        MainFrame d = new MainFrame();

    }
}
