package eg.edu.alexu.csd.oop.draw.controller;

import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Xmll {

	public Xmll (String file ,ArrayList<Shape> shapes) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(file);
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.setExceptionListener(e -> System.out.println("Exception! :"+e.toString()));
		encoder.writeObject(shapes);
		encoder.close();
		fos.close();
	}



}
