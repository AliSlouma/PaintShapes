package eg.edu.alexu.csd.oop.draw.controller;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.GUI.DialogBox;
import eg.edu.alexu.csd.oop.draw.Shapes.Line;
import eg.edu.alexu.csd.oop.draw.Shapes.Triangle;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ShapeHandler {
    private drawingEngine engine;
	private DialogBox dialog;

	public ShapeHandler(drawingEngine engine)
    {
        this.engine = engine;
    }
	
    public void move(Shape shape, Point driftedPos)
    {
        if (shape.getClass() != Triangle.class && shape.getClass() != Line.class)
        {
            shape.setPosition(new Point((int)(shape.getPosition().getX() + driftedPos.getX()),
                    (int)(shape.getPosition().getY() + driftedPos.getY())));

        }
        else if(shape.getClass() == Line.class){
        	
        	 shape.setPosition(new Point((int)(shape.getPosition().getX() + driftedPos.getX()),
                     (int)(shape.getPosition().getY() + driftedPos.getY())));
        	 
 			Map<String, Double> properties = shape.getProperties();

			properties.put("pointx" , shape.getProperties().get("pointx") + driftedPos.getX());
			properties.put("pointy" , shape.getProperties().get("pointy") + driftedPos.getY());
        	 
        	shape.setProperties(properties);
        }
        
        else if(shape.getClass() == Triangle.class) {
        
        	Map<String, Double> properties = shape.getProperties();
        	
        	properties.put("move", (double)1);
                	
        	properties.put("secondpointx" , shape.getProperties().get("secondpointx") + driftedPos.getX());
			properties.put("secondpointy" , shape.getProperties().get("secondpointy") + driftedPos.getY());
			properties.put("thirdpointx" , shape.getProperties().get("thirdpointx") + driftedPos.getX());
			properties.put("thirdpointy" , shape.getProperties().get("thirdpointy") + driftedPos.getY());
			
			shape.setProperties(properties);
			shape.setPosition(new Point((int)(shape.getPosition().getX() + driftedPos.getX()),
                    (int)(shape.getPosition().getY() + driftedPos.getY())));
        }
        engine.updateUndo();

    }

    public void Paint(Shape shape, Color fillcolor, Color borderColor)
    {
        Shape shape1 = shape;
        try {
            shape1 = (Shape) shape.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        shape1.setColor(borderColor);
        shape1.setFillColor(fillcolor);
        engine.updateShape(shape, shape1);
    }

    public void select(Shape shape)
    {
        dialog = new DialogBox(shape, engine);
    }
}
