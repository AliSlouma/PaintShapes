package eg.edu.alexu.csd.oop.draw.GUI;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.Shapes.DShape;
import eg.edu.alexu.csd.oop.draw.Shapes.Line;
import eg.edu.alexu.csd.oop.draw.Shapes.Triangle;
import eg.edu.alexu.csd.oop.draw.controller.ShapeHandler;
import eg.edu.alexu.csd.oop.draw.controller.UndoRedoOper;
import eg.edu.alexu.csd.oop.draw.controller.drawingEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class DrawCanvas extends JPanel {
	private DrawingEngine engine;
	private final int CANVAS_WIDTH,CANVAS_HEIGHT;
	private static DrawCanvas canvas = null;
	private ButtonGui buttons;
	private Shape currentShape;
	private ToolBar tool;
	private Point MovePoint;
	private ShapeHandler shapeHandlerEngine;
	private JFrame mainFrame;
	private DialogBox dialog ;
	private UndoRedoOper undoRedoOper;
	
	private DrawCanvas(drawingEngine engine, ButtonGui buttonGui, ToolBar toolBar, JFrame jframe)
	{
		this.undoRedoOper = UndoRedoOper.makeInstance(engine);
		this.mainFrame = jframe;
		shapeHandlerEngine = new ShapeHandler(engine);
		
		MovePoint = null;
		this.tool = toolBar;
		this.engine = engine;
		CANVAS_WIDTH = 1200;
		CANVAS_HEIGHT = 700;
		this.buttons = buttonGui;
		currentShape = null;
		
		this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		initialize();
	}

	public static synchronized DrawCanvas makeInstance(drawingEngine engine, ButtonGui buttonGui, ToolBar toolBar, JFrame jFrame)
	{
		if (canvas == null)
		{
			return canvas = new DrawCanvas(engine, buttonGui, toolBar, jFrame);
		}
		else
			return canvas;
	}

	@Override
	protected void paintComponent(Graphics g) { // called back via repaint()
		super.paintComponent(g);
		this.engine.refresh(g);
	}



	private void initialize()
	{
		this.setBackground(Color.white);
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (currentShape != null && currentShape.getClass() == Triangle.class && currentShape.getProperties().get("release").equals(1.0))
				{
					try {
						currentShape = (Shape) buttons.getChosenShape().clone();
					} catch (CloneNotSupportedException e1) {
					}
				}
				else {
					if ((mainFrame.getCursor().getType() == Cursor.DEFAULT_CURSOR) && (buttons.getChosenShape() != null)) {
						try {
							currentShape = (Shape) buttons.getChosenShape().clone();
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}
						engine.addShape(currentShape);
						currentShape.setColor(tool.getBorderColor());
						currentShape.setFillColor(tool.getColor());

						if (engine.getSupportedShapes().size() > 6 && currentShape.getClass() == engine.getSupportedShapes().get(6)) {
							currentShape.getProperties().replace("ArcWidth", 5.0);
							currentShape.getProperties().replace("ArcLength", 5.0);
						}
						if (currentShape.getClass() == Triangle.class) {

							Map<String, Double> properties = currentShape.getProperties();

							properties.put("secondpointx", (double) e.getX());
							properties.put("secondpointy", (double) e.getY());

							currentShape.setProperties(properties);
						} else if (currentShape.getClass() == Line.class) {
							Map<String, Double> properties = currentShape.getProperties();

							properties.put("pointx", (double) e.getX());
							properties.put("pointy", (double) e.getY());
							currentShape.setProperties(properties);
						} else {
							currentShape.setPosition(e.getPoint());
						}
					} else if (mainFrame.getCursor().getType() == Cursor.MOVE_CURSOR) {
						currentShape = contain(e.getPoint());
						if (currentShape != null) {
							try {
								Shape clonedShape = (Shape) currentShape.clone();
								engine.updateShape(currentShape, clonedShape);
								currentShape = clonedShape;
							} catch (CloneNotSupportedException ex) {

							}
							MovePoint = e.getPoint();
						} else {
							mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					} else if (mainFrame.getCursor().getType() == Cursor.SW_RESIZE_CURSOR) {
						currentShape = contain(e.getPoint());
						if (currentShape != null) {
							if (currentShape.getClass() == Triangle.class || currentShape.getClass() == Line.class)
								mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							else {
								try {
									Shape clonedShape = (Shape) currentShape.clone();
									engine.updateShape(currentShape, clonedShape);
									currentShape = clonedShape;
								} catch (CloneNotSupportedException ex) {

								}
							}
						} else {
							mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					}
					repaint();
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (currentShape != null && (currentShape.getClass() == Triangle.class)){

					Map<String, Double> properties = currentShape.getProperties();

					properties.put("release",1.0);
					((drawingEngine)engine).updateUndo();
					repaint();
				}else if (currentShape != null)
				{
					((drawingEngine)engine).updateUndo();
				}

			}
			public void mouseClicked (MouseEvent e) {
				
				if(getCursor().getType() == Cursor.N_RESIZE_CURSOR){
					Shape resizeShape = contain(e.getPoint());
					if (resizeShape != null){
						shapeHandlerEngine.select(resizeShape);
						mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

					}
				}
				if (mainFrame.getCursor().getType() == Cursor.CROSSHAIR_CURSOR)
				{
					currentShape = contain(e.getPoint());
					if (currentShape != null)
					{
						engine.removeShape(currentShape);
						mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

					}
				}

				if (getCursor().getType() == Cursor.DEFAULT_CURSOR && (currentShape != null && (currentShape.getClass() == Triangle.class))){

					currentShape = null;

				}
				
				else if (mainFrame.getCursor().getType() == Cursor.HAND_CURSOR)
				{
					Shape coloredShape = contain(e.getPoint());
					if (coloredShape != null){
						shapeHandlerEngine.Paint(coloredShape, tool.getColor(), tool.getBorderColor());
						mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				repaint();
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (currentShape != null){

					if(mainFrame.getCursor().getType() == Cursor.DEFAULT_CURSOR &&
							((currentShape.getClass() == Line.class) || (currentShape.getClass() == Triangle.class))){

						currentShape.setPosition(e.getPoint());
						((drawingEngine)engine).updateUndo();

					}
					else if (mainFrame.getCursor().getType() == Cursor.DEFAULT_CURSOR){

						Map<String, Double> properties = currentShape.getProperties();
						properties.put("Width", Math.abs(e.getX() - currentShape.getPosition().getX()));
						properties.put("Length", Math.abs(e.getY() - currentShape.getPosition().getY()));
					}
					else if (mainFrame.getCursor().getType() == Cursor.SW_RESIZE_CURSOR)
					{
						Map<String, Double> properties = currentShape.getProperties();
						properties.put("Width", Math.abs(e. getX() - currentShape.getPosition().getX()));
						properties.put("Length", Math.abs(e.getY() - currentShape.getPosition().getY()));
						((drawingEngine)engine).updateUndo();
					}
					else if (mainFrame.getCursor().getType() == Cursor.MOVE_CURSOR && currentShape != null)
					{
						Point drift = new Point((int)(e.getX()- MovePoint.getX()),(int)(e.getY() - MovePoint.getY()));
						MovePoint = e.getPoint();
						shapeHandlerEngine.move(currentShape, drift);
					}
					repaint();
					((drawingEngine)engine).updateUndo();
				}
			}

			public void mouseMoved (MouseEvent e) {
				if (currentShape != null && (currentShape.getClass() == Triangle.class)){
					
					double release = currentShape.getProperties().get("release");
					double movee = currentShape.getProperties().get("move");

					if ((currentShape.getClass() == Triangle.class) && ((int) release == 1 )&& ((int) movee == 0 )) {
						currentShape.setPosition(e.getPoint());
						repaint();
					}
				}
			}
		});
	}

	private Shape contain (Point clicked)
	{
		for (int i = engine.getShapes().length - 1; i >= 0; i--)
		{
			if(engine.getSupportedShapes().size() > 6 && (engine.getShapes()[i].getClass() == engine.getSupportedShapes().get(6)))
			{
				Point bottomleft = new Point();
				Point topright = new Point();
				Shape shape = engine.getShapes()[i];
				bottomleft.setLocation((int)(shape.getPosition().getX() - shape.getProperties().get("Width")),
						(int)(shape.getPosition().getY() + shape.getProperties().get("Length")));

				topright.setLocation((int)(shape.getPosition().getX() + shape.getProperties().get("Width")),
						(int)(shape.getPosition().getY() - shape.getProperties().get("Length")));


				if (shape.getPosition().getX() > bottomleft.getX() && shape.getPosition().getX() < topright.getX()
					&& shape.getPosition().getY() < bottomleft.getY() && shape.getPosition().getY() > topright.getY())
				{
					return shape;
				}
			}
			else if (((DShape)engine.getShapes()[i]).contain(clicked))
			{
				return engine.getShapes()[i];
			}
		}
		return null;
	}

}
