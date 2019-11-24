package eg.edu.alexu.csd.oop.draw.Shapes;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;

public class Circle extends DShape {
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(this.getFillColor());
        canvas.fillOval((int)(this.getPosition().getX() - this.getProperties().get(this.ShapeProperties[0])),
                (int)(this.getPosition().getY() - this.getProperties().get(this.ShapeProperties[0])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[0])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[0])));
        canvas.setColor(this.getColor());
        canvas.drawOval((int)(this.getPosition().getX() -  this.getProperties().get(this.ShapeProperties[0])),
                (int)(this.getPosition().getY() - this.getProperties().get(this.ShapeProperties[0])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[0])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[0])));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape clonedShape = new Circle();
        this.copyShapes(clonedShape);
        return clonedShape;
    }

    @Override
    public boolean contain(Point p) {
        if (this.distance(p , this.getPosition()) <= this.getProperties().get(ShapeProperties[0]))
        {
            return true;
        }
        else
            return false;
    }
}
