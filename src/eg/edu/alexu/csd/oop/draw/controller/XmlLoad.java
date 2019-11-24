package eg.edu.alexu.csd.oop.draw.controller;

import java.awt.List;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class XmlLoad {

	ArrayList<Shape> shapes = new ArrayList<Shape>();

	public XmlLoad(String path) throws IOException {

		//System.out.println(path);
		XMLDecoder d = new XMLDecoder(
				new BufferedInputStream(new FileInputStream(path)));
		//	System.out.println(path);
		ArrayList<Shape> list = (ArrayList<Shape>) d.readObject();

		d.close();
		this.shapes = list;

	}
	public ArrayList<Shape> getLoadedShape (){
		return this.shapes;

	}



}
