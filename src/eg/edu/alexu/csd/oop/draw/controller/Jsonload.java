package eg.edu.alexu.csd.oop.draw.controller;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.Shapes.Ellipse;

public class Jsonload {

	ArrayList<Shape> shapes = new ArrayList<Shape>();
	String path ;
	private drawingEngine engine;

	public Jsonload(String file, DrawingEngine engine) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		this.engine = (drawingEngine) engine;
		this.path=file;
		this.Initialize();

	}
	public void Initialize() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		try
		{
			File file = new File(this.path);

			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder newstring = new StringBuilder();
			String st;
			while ((st = br.readLine()) != null) {
				newstring.append(st);
			}

			String[] arrOfStr = newstring.toString().split("[{},:\" ]+");

			int current =-1;
			int j=4;

			while(!arrOfStr[j].equals("]")) {
				current =-1;
				boolean flag = false;
				if(Shape.class.isAssignableFrom(Class.forName(arrOfStr[j]))) {
					flag = true;
					/*for (int k = 0; k < engine.getSupportedShapes().size(); k++) {
						//System.out.println(engine.getSupportedShapes().get(k).toString());
						if (("class " + arrOfStr[j]).equals(engine.getSupportedShapes().get(k).toString())) {
							current = k;
							break;
						}
					}*/
				}
				Shape shape = null;
				if (arrOfStr[j].equals("eg.edu.alexu.csd.oop.draw.RoundRectangle") && engine.getSupportedShapes().size() == 6)
					flag = false;

				if (flag) {
					shape = (Shape) Class.forName(arrOfStr[j]).getConstructor().newInstance();
					j += 3;
					shape.setPosition(new Point((int) Double.parseDouble(arrOfStr[j]), (int) Double.parseDouble(arrOfStr[j += 2])));

					shape.setFillColor(new Color(Integer.parseInt(arrOfStr[j += 3]), Integer.parseInt(arrOfStr[j += 2]), Integer.parseInt(arrOfStr[j += 2])));
					//	System.out.println(shape.getFillColor());
					shape.setColor(new Color(Integer.parseInt(arrOfStr[j += 3]), Integer.parseInt(arrOfStr[j += 2]), Integer.parseInt(arrOfStr[j += 2])));
					//	System.out.println(shape.getColor());

					Map<String, Double> proberties = new HashMap<String, Double>();
					j += 2;
					while (!arrOfStr[j].equals("class") && !arrOfStr[j].equals("]")) {
						//	System.out.println(arrOfStr[j]);
						//	System.out.println(arrOfStr[j+1]);

						proberties.put(arrOfStr[j++], Double.parseDouble(arrOfStr[j++]));
					}
					shape.setProperties(proberties);
					shapes.add(shape);
					if (arrOfStr[j].equals("class"))
						j++;
					if (arrOfStr[j].equals("]"))
						break;
				}
				else {
					while (!arrOfStr[j].equals("class") && !arrOfStr[j].equals("]")) {
						j++;
					}

					if(arrOfStr[j].equals("class")) {
						j++;
						continue;}
					if(arrOfStr[j].equals("]"))
						break;


				}
			}
			br.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	public ArrayList<Shape> loadShapes(){
		return this.shapes;
	}


}
