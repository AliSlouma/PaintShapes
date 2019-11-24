package eg.edu.alexu.csd.oop.draw.GUI;

import eg.edu.alexu.csd.oop.draw.controller.drawingEngine;

import javax.swing.*;


public class Menu extends JMenuBar
{

    FileChooser file = new FileChooser();
    public JMenu x;

    // Menu items
    public JMenuItem save, load ;

    public Menu(drawingEngine engine , JFrame canvas) {



        x = new JMenu("Menu");

        save = new JMenuItem("Save");
        load = new JMenuItem("Load");

        save.addActionListener(e -> engine.save(file.ChoosePlace()));

        load.addActionListener(e -> {

            engine.load(file.getPath());
            canvas.repaint();
        });


        x.add(save);
        x.add(load);

        this.add(x);


    }

}
