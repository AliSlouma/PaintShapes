package eg.edu.alexu.csd.oop.draw.controller;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UndoRedoOper {

    private static UndoRedoOper undoRedoOper = null;
    private List<List<Shape> > undoShapes;
    private int currentPos;
    private drawingEngine engine;
    private UndoRedoOper(drawingEngine engine)
    {
        this.engine = engine;
        currentPos = -1;
        undoShapes = new ArrayList<>();
    }
    public static synchronized UndoRedoOper makeInstance(drawingEngine engine)
    {
        if (undoRedoOper == null)
        {
            return undoRedoOper = new UndoRedoOper(engine);
        }
        else
            return undoRedoOper;
    }
    protected List<Shape> undo() throws CloneNotSupportedException {
        if (undoShapes.size() == 0 || currentPos == 0)
        {
            throw new NullPointerException(); }

        currentPos--;
        return DeepClone(undoShapes.get(currentPos));
    }

    public List<Shape> redo() throws CloneNotSupportedException {
        if (currentPos + 1 == undoShapes.size())
        {
            throw new NullPointerException();
        }
        currentPos++;
        return DeepClone(undoShapes.get(currentPos));

    }
    public void addShapes(List<Shape> shapes) throws CloneNotSupportedException {
        if (undoShapes.size() == 21)
        {
            if (currentPos == 20){
            undoShapes.remove(0);
            currentPos--;}
            else
                undoShapes.remove(currentPos + 1);

        }
        if (currentPos + 1 < undoShapes.size())
        {
        undoShapes.add(currentPos + 1 ,this.DeepClone(shapes));
        undoShapes = undoShapes.subList(0, currentPos + 2);
        }
        else
        {
            undoShapes.add(this.DeepClone(shapes));
        }
        currentPos++;
    }
    void clear(drawingEngine newengine)
    {
        undoShapes.clear();
        this.engine = newengine;
        undoShapes.add(Arrays.asList(newengine.getShapes()));
        currentPos = 0;
    }
    public void updatetheShapes()
    {
        undoShapes.remove(currentPos);
        undoShapes.add(currentPos, Arrays.asList(this.engine.getShapes()));
    }

    private List<Shape>DeepClone(List<Shape> shapes) throws CloneNotSupportedException {
        List<Shape> newShapes = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++)
        {
            newShapes.add((Shape) shapes.get(i).clone());
        }
        return newShapes;
    }

}
