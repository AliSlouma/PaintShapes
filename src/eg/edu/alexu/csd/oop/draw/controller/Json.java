package eg.edu.alexu.csd.oop.draw.controller;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Json {
    private static Json json = null;
    private Json()
    {
    }
    static synchronized Json makeInstance()
    {
        if (json == null)
        {
            return  json = new Json();
        }
        else
            return json;
    }
    void save(String file, ArrayList<Shape> shapes) throws FileNotFoundException {

        FileOutputStream outJson = new FileOutputStream(file);
        writeOnJson(outJson, shapes);
    }
    private void writeOnJson(FileOutputStream file, ArrayList<Shape> shapes)
    {
        try {
            StringBuilder jsonFile = new StringBuilder();
            jsonFile.append("{\n\"shapes\": \n[\n");
            for (Shape shape : shapes) {
                jsonFile.append("{\n");
                jsonFile.append("\"").append(shape.getClass().toString()).append("\": ");
                jsonFile.append("\n{");
                if (shape.getPosition() == null)
                    shape.setPosition(new Point(0,0));

                jsonFile.append("\n\"position\": \n{\n\"x\": ").append(shape.getPosition().getX()).append(",\n\"y\": ").append(shape.getPosition().getY()).append("\n},\n");

                if (shape.getFillColor() == null)
                    shape.setFillColor(Color.white);

                jsonFile.append("\"fillcolor\": \n{\n\"r\": ").append(shape.getFillColor().getRed()).append(",\n\"g\": ").append(shape.getFillColor().getGreen()).append(",\n\"b\": ").append(shape.getFillColor().getBlue());
                jsonFile.append("\n},\n");

                if (shape.getFillColor() == null)
                    shape.setFillColor(Color.white);

                jsonFile.append("\"bordercolor\": \n{\n\"r\": ").append(shape.getColor().getRed()).append(",\n\"g\": ").append(shape.getColor().getGreen()).append(",\n\"b\": ").append(shape.getColor().getBlue());
                jsonFile.append("\n},\n");

                jsonFile.append("\"properties\": \n{\n");
                if (shape.getProperties() == null)
                {
                    shape.setProperties(new HashMap<>());
                    shape.getProperties().put("Width", 0.0);
                    shape.getProperties().put("Height", 0.0);
                }
                for (Map.Entry<String, Double> entry : shape.getProperties().entrySet()) {
                    //copy.put(entry.getKey(),Double.valueOf(entry.getValue()));
                    jsonFile.append("\"").append(entry.getKey()).append("\": ").append(entry.getValue().toString()).append(",\n");
                }
                jsonFile = new StringBuilder(jsonFile.substring(0, jsonFile.length() - 2));
                jsonFile.append("\n}\n");
                jsonFile.append("\n}},\n");
            }
            if (jsonFile.length() > 1)
            {
                jsonFile = new StringBuilder(jsonFile.substring(0, jsonFile.length() - 2));
            }
            jsonFile.append("\n]\n}");
            file.write(jsonFile.toString().getBytes());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
