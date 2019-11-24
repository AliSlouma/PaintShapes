package eg.edu.alexu.csd.oop.draw.GUI;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.Shapes.*;
import eg.edu.alexu.csd.oop.draw.Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DialogBox {
	
	private Shape shape ;
	private String width = "";
	private String length = "";
	private DrawingEngine engine;
	private Point center = new Point();
	private Map<String, Double> properties ;
	public DialogBox(Shape shape, DrawingEngine engine1 ){
		this.engine = engine1;
		this.shape = shape;
		try {
			initialize();
		} catch (CloneNotSupportedException e) {
		}
	}
	
	
	
	private void initialize() throws CloneNotSupportedException {
		
		JPanel panel = new JPanel();
		
		panel.setBounds(0, 0, 600, 600);
		
		panel.setLayout(null);
		
		int i=1;
		JLabel label;
		JLabel label2;
		JLabel label3;
		JLabel label4;
		JLabel label5;
		JLabel label6;
		JTextField field = null;
		JTextField field2 =null;
		JTextField field3 = null;
		JTextField field4 = null;
		JTextField field5= null;
		JTextField field6 =null;

		
		System.out.println(shape.getClass().toString());
		
		  if(this.shape.getClass() == Triangle.class) {
			  label = new JLabel("pointX");
			  label.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label.setBounds(10, 10 , 100, 42);
			  panel.add(label);
			  
			  field = new JTextField();
			  field.setBounds(150, 18 , 96, 29);
			  
			  Double x1 =this.shape.getPosition().getX();
			  field.setText(x1.toString());
			  panel.add(field);
			  
			  label2 = new JLabel("pointY");
			  label2.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label2.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label2);
			  
			  field2 = new JTextField();
			  field2.setBounds(150, 18+(i*42) , 96, 29);
			
			  Double y1 =this.shape.getPosition().getY();
			  field2.setText(y1.toString());
			  panel.add(field2);
			  
			  
			  i++;
			  
			  label3 = new JLabel("Second pointX");
			  label3.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label3.setBounds(10, 10+(i*42), 100, 42);
			  panel.add(label3);
			  
			 
			  field3 = new JTextField();
			  field3.setBounds(150, 18+(i*42) , 96, 29);
			 
			  field3.setText(this.shape.getProperties().get("secondpointx").toString());
			  panel.add(field3);
			  
			  i++;
			  label4 = new JLabel("Second pointY");
			  label4.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label4.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label4);
			  
			  field4 = new JTextField();
			  field4.setBounds(150, 18+(i*42) , 96, 29);
			  field4.setText(this.shape.getProperties().get("secondpointy").toString());
			  panel.add(field4);
			  
			  i++;
			  label5 = new JLabel("Third pointX");
			  label5.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label5.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label5);
			  
			  field5 = new JTextField();
			  field5.setBounds(150, 18+(i*42) , 96, 29);
			  
			  field5.setText(this.shape.getProperties().get("thirdpointx").toString());
			
			  panel.add(field5);
			  i++;
			  
			   label6 = new JLabel("Third pointY");
			  label6.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label6.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label6);
			  
			  field6 = new JTextField();
			  field6.setBounds(150, 18+(i*42) , 96, 29);
			  field6.setText(this.shape.getProperties().get("thirdpointy").toString());
			  panel.add(field6);
			  
			  
		  }
		  else if (this.shape.getClass().toString().equals("class eg.edu.alexu.csd.oop.draw.RoundRectangle"))
		  {
			  label = new JLabel("Width");
			  label.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label.setBounds(10, 10 , 100, 42);
			  panel.add(label);

			  field = new JTextField();
			  field.setBounds(150, 18 , 96, 29);

			  field.setText(String.valueOf(this.shape.getProperties().get("Width")));
			  panel.add(field);

			  label2 = new JLabel("Length");
			  label2.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label2.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label2);

			  field2 = new JTextField();
			  field2.setBounds(150, 18+(i*42) , 96, 29);

			  field2.setText(String.valueOf(this.shape.getProperties().get("Length")));
			  panel.add(field2);


			  i++;

			  label3 = new JLabel("ArcWidth");
			  label3.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label3.setBounds(10, 10+(i*42), 100, 42);
			  panel.add(label3);


			  field3 = new JTextField();
			  field3.setBounds(150, 18+(i*42) , 96, 29);

			  field3.setText(this.shape.getProperties().get("ArcWidth").toString());
			  panel.add(field3);

			  i++;
			  label4 = new JLabel("ArcLength");
			  label4.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label4.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label4);

			  field4 = new JTextField();
			  field4.setBounds(150, 18+(i*42) , 96, 29);
			  field4.setText(this.shape.getProperties().get("ArcLength").toString());
			  panel.add(field4);

		  }
		  else if (this.shape.getClass() == Line.class) {
			  label = new JLabel("pointX");
			  label.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label.setBounds(10, 10 , 100, 42);
			  panel.add(label);
			  
			  field = new JTextField();
			  field.setBounds(150, 18 , 96, 29);
			  
			  Double x1 =this.shape.getPosition().getX();
			  field.setText(x1.toString());
			  panel.add(field);
			  
			  label2 = new JLabel("pointY");
			  label2.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label2.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label2);
			  
			  field2 = new JTextField();
			  field2.setBounds(150, 18+(i*42) , 96, 29);
			
			  Double y1 =this.shape.getPosition().getY();
			  field2.setText(y1.toString());
			  panel.add(field2);
			  
			  
			  i++;
			  
			  label3 = new JLabel("secondpointX");
			  label3.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label3.setBounds(10, 10+(i*42), 100, 42);
			  panel.add(label3);
			  
			 
			  field3 = new JTextField();
			  field3.setBounds(150, 18+(i*42) , 96, 29);
			 
			  field3.setText(this.shape.getProperties().get("pointx").toString());
			  panel.add(field3);
			  
			  i++;
			  label4 = new JLabel("secondpointY");
			  label4.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label4.setBounds(10, 10+(i*42) , 100, 42);
			  panel.add(label4);
			  
			  field4 = new JTextField();
			  field4.setBounds(150, 18+(i*42) , 96, 29);
			  field4.setText(this.shape.getProperties().get("pointy").toString());
			  panel.add(field4);
			  
		  }
		  
		  else if(this.shape.getClass() == Circle.class) {
			  label = new JLabel("Radius");
			  label.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label.setBounds(10, 10 , 100, 42);
			  panel.add(label);
			  
			  field = new JTextField();
			  field.setBounds(150, 18 , 96, 29);
			  field.setText(this.shape.getProperties().get("Width").toString());
			  panel.add(field);
			  
		  } else {
			  
			  label = new JLabel("Width");
			  label.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label.setBounds(10, 10 , 100, 42);
			  panel.add(label);
			  
			  field = new JTextField();
			  field.setBounds(150, 18 , 96, 29);
			  field.setText(this.shape.getProperties().get("Width").toString());
			  panel.add(field);
			  
			  
			  label2 = new JLabel("Length");
			  label2.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label2.setBounds(10, 10+42 , 100, 42);
			  panel.add(label2);
			  
			  field2 = new JTextField();
			  field2.setBounds(150, 18+42 , 96, 29);
			
			  field2.setText(this.shape.getProperties().get("Length").toString());
			  panel.add(field2);
			  
		  }
		  
		  
		  
		  
		  
	/*	  for (Map.Entry<String,Double> entry : this.shape.getProperties().entrySet())  
		  {
			  
			  if(!entry.getKey().equals("click") && !entry.getKey().equals("release") && !entry.getKey().equals("move")) {
				  
			  
			  JLabel label3 = new JLabel(entry.getKey());
			  label3.setFont(new Font("Tahoma", Font.BOLD, 13));
			  label3.setBounds(10, 10 +(i*42), 80, 42);
			  panel.add(label3);
			  
			  JTextField field3 = new JTextField();
			  field3.setBounds(120, 18 + (i*42), 96, 29);
			  field3.setText(entry.getValue().toString());
			  
			  panel.add(field3);
			  
	           
	            i++;
		  }
		  }
		  */
		
	     
	      UIManager.put("OptionPane.minimumSize",new Dimension(600,600)); 
	      int result = JOptionPane.showConfirmDialog(null, panel, 
	               "Edit", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
			  Shape newShape = (Shape) shape.clone();
	    	if(newShape.getClass() == Triangle.class) {
	    		Map<String, Double> properties = newShape.getProperties();

	    		center.x = (int) Double.parseDouble(field.getText());
	    		center.y = (int) Double.parseDouble(field2.getText());
	    		
	    		
				properties.put("secondpointx" , Double.parseDouble(field3.getText()));
				properties.put("secondpointy" , Double.parseDouble(field4.getText()));
				
				properties.put("thirdpointx" , Double.parseDouble(field5.getText()));
				properties.put("thirdpointy" , Double.parseDouble(field6.getText()));
				
				newShape.setProperties(properties);
				newShape.setPosition(center);
	    	}
	    	
	    	if(newShape.getClass() == Rectangle.class) {
	    		Map<String, Double> properties = newShape.getProperties();
	    		properties.put("Width" , Double.parseDouble(field.getText()));
				properties.put("Length" , Double.parseDouble(field2.getText()));
				newShape.setProperties(properties);
	    		
	    	} else if(newShape.getClass() == Ellipse.class) {
	    		Map<String, Double> properties = newShape.getProperties();
	    		properties.put("Width" , Double.parseDouble(field.getText()));
	    	}
	    	else if (newShape.getClass().toString().equals("class eg.edu.alexu.csd.oop.draw.RoundRectangle"))
			{
				Map<String, Double> properties = newShape.getProperties();

				properties.put("Width" , Double.parseDouble(field.getText()));
				properties.put("Length" , Double.parseDouble(field2 != null ? field2.getText() : String.valueOf(0)));

				properties.put("ArcWidth" , Double.parseDouble(field3 != null ? field3.getText() : String.valueOf(0)));
				properties.put("ArcLength" , Double.parseDouble(field4 != null ? field4.getText() : String.valueOf(0)));

				newShape.setProperties(properties);
			}
	    	else if(newShape.getClass() == Line.class) {
	    		Map<String, Double> properties = newShape.getProperties();

	    		center.x = (int) Double.parseDouble(field.getText());
	    		center.y = (int) Double.parseDouble(field2.getText());
	    		
	    		
				properties.put("pointx" , Double.parseDouble(field3.getText()));
				properties.put("pointy" , Double.parseDouble(field4.getText()));
				
				
				
				newShape.setProperties(properties);
				newShape.setPosition(center);
	    	}
			  engine.updateShape(shape, newShape);
	      }
	   }
	
	
	public Map getwidth() {
		return this.properties;
	}
	public String getlength() {
		return length;
	}
		

		
		
		
	

}
