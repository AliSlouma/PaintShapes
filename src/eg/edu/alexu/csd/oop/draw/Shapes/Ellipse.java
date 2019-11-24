package eg.edu.alexu.csd.oop.draw.Shapes;
import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends DShape {
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(this.getFillColor());
        canvas.fillOval((int)(this.getPosition().getX() - this.getProperties().get(this.ShapeProperties[0])),
                (int)(this.getPosition().getY() - this.getProperties().get(this.ShapeProperties[1])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[0])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[1])));
        canvas.setColor(this.getColor());
        canvas.drawOval((int)(this.getPosition().getX() -  this.getProperties().get(this.ShapeProperties[0])),
                (int)(this.getPosition().getY() - this.getProperties().get(this.ShapeProperties[1])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[0])),
                (int)(2.0 * this.getProperties().get(this.ShapeProperties[1])));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape clonedShape = new Ellipse();
        this.copyShapes(clonedShape);
        return clonedShape;
    }

    @Override
    public boolean contain(Point p) {

        double a = this.getProperties().get(this.ShapeProperties[0]);
        double b = this.getProperties().get(this.ShapeProperties[1]);

        double ans = (Math.pow(p.x - this.getPosition().getX(),2))/(a*a) +  (Math.pow(p.y - this.getPosition().getY(),2))/(b*b);

        return ans <= 1;

    }

}
