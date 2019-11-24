package eg.edu.alexu.csd.oop.draw.controller;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.Shapes.*;
import eg.edu.alexu.csd.oop.draw.Shapes.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class drawingEngine implements DrawingEngine {
	private ArrayList<Shape> Shapes;

	private List<Class<? extends Shape>> Classes;
	private UndoRedoOper undoRedoOper;
	Json json ;
	public drawingEngine()
	{
		this.undoRedoOper = UndoRedoOper.makeInstance(this);
		Shapes = new ArrayList<>();
		Classes = new ArrayList<>();
		initialize();
		undoRedoOper.clear(this);
		this.json=Json.makeInstance();
		
	}

	@Override
	
	public void refresh(Graphics canvas) {
		for (Shape shape : this.Shapes) {
			shape.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		Shapes.add(shape);
		 try {
            undoRedoOper.addShapes(this.Shapes);
        } catch (CloneNotSupportedException e) {

        }
	}

	@Override
	public void removeShape(Shape shape) {
		Shapes.remove(shape);
		try {
			undoRedoOper.addShapes(this.Shapes);
		} catch (CloneNotSupportedException e) {
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		int ShapePos = Shapes.indexOf(oldShape);
		Shapes.remove(oldShape);
		Shapes.add(ShapePos, newShape);
		try {
			undoRedoOper.addShapes(this.Shapes);
		} catch (CloneNotSupportedException e) {

		}
	}

	@Override
	public Shape[] getShapes() {
		Shape []ar = new Shape[0];
		return Shapes.toArray(ar);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return Classes;
	}

	@Override
	public void installPluginShape(String jarPath) {
		try {
			JarFile jarFile = new JarFile(jarPath);
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + jarPath +"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if(je.isDirectory() || !je.getName().endsWith(".class")){
					continue;
				}
				String className = je.getName().substring(0,je.getName().length()-6);
				className = className.replace('/', '.');
				Class loadClass = cl.loadClass(className);
				if (Shape.class.isAssignableFrom(loadClass))
				{
					Classes.add((Class<Shape>)loadClass);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		try
		{
			this.Shapes = (ArrayList<Shape>) this.undoRedoOper.undo();}
		catch (NullPointerException | CloneNotSupportedException e)
		{

		}
	}

	@Override
	public void redo() {
		try {
			this.Shapes = (ArrayList<Shape>) this.undoRedoOper.redo();}
		catch (NullPointerException | CloneNotSupportedException e)
		{

		}
	}

	@Override
	public void save(String path) {

		try {
			if (path.charAt(path.length() - 1) == 'n'){
			json.save(path, Shapes);}
			else {
				Xmll x = new Xmll(path, Shapes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String path) {
		Jsonload load = null;
		XmlLoad x = null;
		try {
			if(path.charAt(path.length()-1) == 'n') {
				load = new Jsonload(path,this);
				this.Shapes = load.loadShapes();
			}

			else {
				x = new XmlLoad(path);
				this.Shapes = x.getLoadedShape();
				undoRedoOper.clear(this);
				check();
			}
		} catch (InstantiationException | IOException | SecurityException | NoSuchMethodException | InvocationTargetException | IllegalArgumentException | IllegalAccessException | ClassNotFoundException e) {

		}
	}
	private void check()
	{
		for (int i = 0; i < this.Shapes.size();i++)
		{
			if (this.Shapes.get(i).getClass().toString().equals("eg.edu.alexu.csd.oop.draw.RoundRectangle") && Classes.size() < 7)
			{
				this.Shapes.remove(i);
			}
		}
	}
	public void updateUndo()
    {
        undoRedoOper.updatetheShapes();
    }

	private void initialize()
	{
		this.Classes.add(Circle.class);
		this.Classes.add(Ellipse.class);
		this.Classes.add(Line.class);
		this.Classes.add(Rectangle.class);
		this.Classes.add(Square.class);
		this.Classes.add(Triangle.class);

	}

}
