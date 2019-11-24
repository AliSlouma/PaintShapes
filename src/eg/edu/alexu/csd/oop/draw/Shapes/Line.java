package eg.edu.alexu.csd.oop.draw.Shapes;
import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;



public class Line extends DShape {

	
	public Line() {
		
		
	}

	public void draw(Graphics canvas) {

        canvas.setColor(this.getFillColor());

      
			double lastPointx = this.getProperties().get("pointx");
			double lastPointy = this.getProperties().get("pointy");

			if(this.getPosition().x != 0) {
				canvas.drawLine((int)lastPointx,(int)lastPointy,this.getPosition().x ,this.getPosition().y);
			}

			

		
	}

    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape clonedShape = new Line();
        this.copyShapes(clonedShape);
        return clonedShape;
    }

    @Override
	public boolean contain(Point p) {

		double lastPointx = this.getProperties().get("pointx");
		double lastPointy = this.getProperties().get("pointy");
		
		double a = this.distance(p, this.getPosition());
		double b = this.distance(p, new Point((int)lastPointx,(int)lastPointy));
		double c =this.distance(this.getPosition(), new Point((int)lastPointx,(int)lastPointy));

		
		return Math.abs((a+b) - c) <= .05;
		
		
	}





}
