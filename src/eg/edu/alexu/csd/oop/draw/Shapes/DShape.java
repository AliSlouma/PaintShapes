package eg.edu.alexu.csd.oop.draw.Shapes;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class DShape implements Shape {
    private Point center;
 
    
    public Map<String, Double> properties;
    protected String[] ShapeProperties = {"Width", "Length"};
    private Color borderColor;
    private Color fillColor;
    

    
    public DShape()
    {
        center = new Point();
        properties = new HashMap<>();
        borderColor = Color.blue;
        fillColor = Color.blue;
        properties.put("Width" , (double) 5);
        properties.put("Length" , (double) 5);
    }
    @Override
    public void setPosition(Point position) {
        this.center.setLocation(position.getX(), position.getY());
    }

    @Override
    public Point getPosition() {
        return this.center;
    }



    @Override
    public void setProperties(Map<String, Double> properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }

    @Override
    public void setColor(Color color) {
        this.borderColor = color;
    }

    @Override
    public Color getColor() {
        return this.borderColor;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    @Override
    public abstract void draw(Graphics canvas);

    @Override
    public abstract Object clone() throws CloneNotSupportedException;

    public abstract boolean contain(Point p);

    protected void copyShapes(Shape clonedShape)
    {
        clonedShape.setPosition(new Point((int)this.getPosition().getX(),(int)this.getPosition().getY()));
        clonedShape.setFillColor(new Color(this.getFillColor().getRGB()));
        clonedShape.setColor(new Color(this.getFillColor().getRGB()));

        Map<String , Double> copy = new HashMap<>();
        for (Map.Entry<String , Double> entry : this.getProperties().entrySet())
        {
            copy.put(entry.getKey(),Double.valueOf(entry.getValue()));
        }

        clonedShape.setProperties(copy);
    }


    public double distance(Point x, Point y)
    {
        return Math.sqrt((Math.pow(x.getX() - y.getX(),2) + Math.pow(x.getY() - y.getY(), 2)));
    }
  
}
