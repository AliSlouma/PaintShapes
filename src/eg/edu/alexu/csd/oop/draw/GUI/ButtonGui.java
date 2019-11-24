package eg.edu.alexu.csd.oop.draw.GUI;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.controller.drawingEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ButtonGui extends JPanel {
    private final int MAXSIZE = 7;
    private String[] picsPaths = new String[MAXSIZE];
    private static ButtonGui buttonGui = null;
    private final int MAXWIDTH = 60;
    private final int MAXHEIGHT = 60;
    private Shape ChosenShape;
    private drawingEngine engine;
    private JFrame jFrame;

    private ButtonGui(drawingEngine drawingEngine, JFrame jFrame)
    {
        this.jFrame = jFrame;
        ChosenShape = null;
        this.engine = drawingEngine;
        picsPaths[0] = "Shapes\\circle.png";
        picsPaths[1] = "Shapes\\ellipse.png";
        picsPaths[2] = "Shapes\\line.png";
        picsPaths[3] = "Shapes\\rectangle.png";
        picsPaths[4] = "Shapes\\square.png";
        picsPaths[5] = "Shapes\\triangle.png";
        picsPaths[6] = "Shapes\\roundedrectangle.png";

        this.setBounds(10, 10, 436, 81);
        this.setLayout(null);
        //this.setBackground(Color.BLUE);
        this.Initializing();
    }

    public static synchronized ButtonGui makeInstance(drawingEngine drawingEngine, JFrame jframe)
    {
    	if(buttonGui == null) {
    		buttonGui = new ButtonGui(drawingEngine, jframe);
    	}
    	
    	
    		return buttonGui;
    	
    
    }

    private void Initializing()
    {
    	this.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < MAXSIZE; i++)
        {

            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(picsPaths[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image dimg = Objects.requireNonNull(img).getScaledInstance(MAXWIDTH, MAXHEIGHT, Image.SCALE_SMOOTH);

            ImageIcon imgg = new ImageIcon(dimg);
            JLabel button = new JLabel(imgg);

            button.setBounds(10 + (i * 80), 21, MAXWIDTH, MAXHEIGHT);

            this.add(button, JLabel.CENTER);
            int finalI = i;
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        deterCurrentShape(finalI);
                        jFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }

    private void deterCurrentShape(int x) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*switch (x){
            case 0:
                this.ChosenShape = new Circle(); break;
            case 1:
                this.ChosenShape = new Ellipse(); break;
            case 2:
                this.ChosenShape = new Line(); break;
            case 3:
                this.ChosenShape = new Rectangle(); break;
            case 5:
                this.ChosenShape = new Triangle(); break;
            case 6:
                if(engine.getSupportedShapes().size() > 6)
                    this.ChosenShape = engine.getSupportedShapes().get(x).getConstructor().newInstance();
                break;
            default:
                break;
        }*/
        if (engine.getSupportedShapes().size() > x)
        {
            this.ChosenShape = engine.getSupportedShapes().get(x).getConstructor().newInstance();
        }
        else
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose the Shape");
           // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                engine.installPluginShape(chooser.getSelectedFile().toString());
                this.ChosenShape = engine.getSupportedShapes().get(x).getConstructor().newInstance();
            }
        }
    }

    public Shape getChosenShape() {
        return ChosenShape;
    }


}
