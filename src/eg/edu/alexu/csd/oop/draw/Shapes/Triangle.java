package eg.edu.alexu.csd.oop.draw.Shapes;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

public class Triangle extends DShape {

	int []arrx = new int[3];
	int []arry = new int[3];


	public Triangle () {
		super();
		this.properties.put("release", (double)0);
		this.properties.put("click", (double)0);
		this.properties.put("move", (double)0);
		this.properties.remove("Width");
		this.properties.remove("Length");

	}

	public void draw(Graphics canvas) {
		canvas.setColor(this.getFillColor());


		double click = this.getProperties().get("click");

		if((int) click == 0) {

			double release = this.getProperties().get("release");


			if((int) release == 0) {

				double lastPointx = this.getProperties().get("secondpointx");
				double lastPointy = this.getProperties().get("secondpointy");


				this.properties.put("thirdpointx", (double)this.getPosition().x);
				this.properties.put("thirdpointy", (double)this.getPosition().y);
				if(this.getPosition().x != 0)
					canvas.drawLine((int)lastPointx,(int)lastPointy,this.getPosition().x ,this.getPosition().y);
			}
			else {

				double lastPointx = this.getProperties().get("secondpointx");
				double lastPointy = this.getProperties().get("secondpointy");
				arrx[0]=(int) lastPointx;
				arry[0] = (int) lastPointy;

				double innerPointx = this.getProperties().get("thirdpointx");
				double innerPointy = this.getProperties().get("thirdpointy");

				arrx[1] =(int) innerPointx;
				arry[1] = (int)innerPointy;

				arrx[2] = this.getPosition().x;
				arry[2] = this.getPosition().y;
				if(this.getPosition().x != 0)
					canvas.fillPolygon(arrx, arry, 3);
				canvas.setColor(this.getColor());
				if(this.getPosition().x != 0)
					canvas.drawPolygon(arrx, arry, 3);
			}
		}
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clonedShape = new Triangle();
		this.copyShapes(clonedShape);
		return clonedShape;
	}

	@Override
	public boolean contain(Point p) {
		double lastPointx = this.getProperties().get("secondpointx");
		double lastPointy = this.getProperties().get("secondpointy");
		double innerPointx = this.getProperties().get("thirdpointx");
		double innerPointy = this.getProperties().get("thirdpointy");

		Point v1 = this.getPosition();
		Point v2 = new Point((int)innerPointx, (int)innerPointy);
		Point v3 = new Point((int) lastPointx , (int) lastPointy);

		double d1, d2, d3;
		boolean has_neg, has_pos;

		d1 = sign(p, v1, v2);
		d2 = sign(p, v2, v3);
		d3 = sign(p, v3, v1);

		has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
		has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

		return !(has_neg && has_pos);

	}

	public double sign (Point p1, Point p2, Point p3)
	{
		return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}


}
