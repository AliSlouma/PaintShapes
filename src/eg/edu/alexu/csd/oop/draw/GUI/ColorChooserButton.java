package eg.edu.alexu.csd.oop.draw.GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

public class ColorChooserButton extends JButton {

    private Color current;
    private Color shape;
   // private List<ColorChangedListener> listeners = new ArrayList<>();
    private ToolBar toolBar;
    public ColorChooserButton(Color c, ToolBar tool) {
        this.toolBar = tool;
        this.shape = c;
        setSelectedColor(c);
        addActionListener(arg0 -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", current);
            setSelectedColor(newColor);
            if (c.equals(Color.black))
                tool.setColor(getSelectedColor());
            else if(c.equals(Color.WHITE))
                tool.setBorderColor(getSelectedColor());
        });
    }

    public Color getSelectedColor() {
        return current;
    }

    public void setSelectedColor(Color newColor) {
        this.current = newColor;
        setIcon(createIcon(current, 16, 16));
        repaint();
    }

/*    public void setSelectedColor(Color newColor, boolean notify) {

        if (newColor == null) return;

        current = newColor;
        setIcon(createIcon(current, 16, 16));
        repaint();

        if (notify) {
            // Notify everybody that may be interested.
            for (ColorChangedListener l : listeners) {
                l.colorChanged(newColor);
            }
        }
    }

    public interface ColorChangedListener {
         void colorChanged(Color newColor);
    }


    public void addColorChangedListener(ColorChangedListener toAdd) {
        listeners.add(toAdd);
    }
*/
    public static  ImageIcon createIcon(Color main, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(main);
        graphics.fillRect(0, 0, width, height);
        graphics.setXORMode(Color.DARK_GRAY);
        graphics.drawRect(0, 0, width-1, height-1);
        image.flush();
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }
}