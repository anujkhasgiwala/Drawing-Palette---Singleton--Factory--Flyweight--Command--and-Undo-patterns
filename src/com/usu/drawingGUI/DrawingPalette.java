package com.usu.drawingGUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usu.draw.Shape;
import com.usu.draw.ShapeExtrinsicState;
import com.usu.draw.ShapeFactory;
import com.usu.draw.ShapeWithAllState;

public class DrawingPalette {

	Shape shape;

	public ShapeFactory shapeFactory;

	private List<Shape> shapes = new ArrayList<Shape>();

	private Object lock = new Object();

	public boolean isDirty;

	private static ObjectMapper mapper = new ObjectMapper();

	public void add(Shape shape) {
		if (shape != null) {
			synchronized(lock) {
				shapes.add(shape);
				isDirty = true;
			}
		}
	}

	public void clear() {
		synchronized(lock) {
			shapes.clear();
			isDirty = true;
		}
	}

	public void removeShape(Shape shape) {
		if (shape != null) {
			synchronized(lock) {
				if (this.shape == shape)
					this.shape = null;
				shapes.remove(shape);
				isDirty = true;
			}
		}
	}

	public boolean draw(Graphics2D graphics, JPanel mainPanel) {
		boolean didARedraw = false;
		synchronized(lock) {
			if (isDirty) {
				for(Shape shape : shapes)
					shape.draw(graphics, mainPanel);
				isDirty = true;
				didARedraw = true;
			}
		}
		return didARedraw;
	}

	public Shape findShapeAtPosition(Point location) {
		Shape result;
		synchronized(lock) {
			for(Shape shape : shapes){
				if(location.x >= shape.getLocation().x && location.x < (shape.getLocation().x + shape.getSize().width) && location.y >= shape.getLocation().y && location.y < (shape.getLocation().y + shape.getSize().height)) {
					result = shape;
					return result;
				}
			}
		}
		return null;
	}

	public void deleteAllSelected() {
		synchronized(lock) {
			shapes.removeIf(s -> s.isSelected);
			isDirty = true;
		}
	}

	public void load(String fileName) {
		AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
				
		JSONParser parser = new JSONParser();
		JSONArray shapesArray = new JSONArray();
		try {
			shapesArray = (JSONArray)parser.parse(new InputStreamReader(s3client.getObject(new GetObjectRequest("oosd-assignment", fileName)).getObjectContent()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*List<ShapeWithAllState> readShapes = new ArrayList<>();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			readShapes = mapper.readValue(new File(fileName), new TypeReference<List<ShapeWithAllState>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		synchronized(lock) {
			this.shapes.clear();
			for(Object shapeObject : shapesArray) {
				Point location = new Point(((Double)((JSONObject)((JSONObject)((JSONObject)shapeObject).get("extrinsicState")).get("location")).get("x")).intValue(), ((Double)((JSONObject)((JSONObject)((JSONObject)shapeObject).get("extrinsicState")).get("location")).get("y")).intValue());
				Dimension size = new Dimension(((Double)((JSONObject)((JSONObject)((JSONObject)shapeObject).get("extrinsicState")).get("size")).get("width")).intValue(), ((Double)((JSONObject)((JSONObject)((JSONObject)shapeObject).get("extrinsicState")).get("size")).get("height")).intValue());
				ShapeExtrinsicState extrinsicState = new ShapeExtrinsicState((String)((JSONObject)((JSONObject)shapeObject).get("extrinsicState")).get("shapeType"), location, size);
				Shape shape = shapeFactory.getShape(extrinsicState);
				shapes.add(shape);
			}
			isDirty = true;
		}
	}

	public void save(String fileName) {
		try {
			mapper.writeValue(new File(fileName), shapes);
		} catch (JsonGenerationException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
		s3client.putObject(new PutObjectRequest("oosd-assignment", fileName.split("\\\\")[(fileName.split("\\\\").length - 1)], new File(fileName)));
	}

	public void deselectAll() {
		synchronized(lock) {
			for(Shape shape : shapes) {
				shape.isSelected = false;
			}
			isDirty = true;
		}    
	}

	public void drawRectangle(Point location, JPanel mainPanel) {
		for(Shape shape : shapes){
			if(location.x >= shape.getLocation().x && location.x < (shape.getLocation().x + shape.getSize().width) && location.y >= shape.getLocation().y && location.y < (shape.getLocation().y + shape.getSize().height)) {
				mainPanel.getGraphics().drawRect(shape.getLocation().x, shape.getLocation().y, shape.getSize().width, shape.getSize().height);
			}
		}
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	/*private void removeRectangle(Shape shape, JPanel mainPanel) {
		mainPanel.getGraphics().drawRect(shape.getLocation().x, shape.getLocation().y, shape.getSize().width, shape.getSize().height);
		((Graphics2D)mainPanel.getGraphics()).setStroke(((Graphics2D)mainPanel.getGraphics()).getStroke());
	}*/

	public void changeScale(float newScale) {
		Shape newShape = null;
		int index = 0;
		Dimension shapeSize = new Dimension((int)Math.round(80 * newScale), (int)Math.round(80 * newScale));

		for (Shape shape : shapes) {
			index++;
			if(shape.isSelected) {
				newShape = shapeFactory.getShape(new ShapeExtrinsicState(((ShapeWithAllState)shape).extrinsicState.shapeType, new Point(shape.getLocation().x - shapeSize.width / 2, shape.getLocation().y - shapeSize.height / 2), shapeSize));
			}
		}

		if(newShape != null)
			shapes.set(index-1, newShape);
	}
	
	public void moveh(int moveBy) {
		Shape newShape = null;
		int index = 0;

		for (Shape shape : shapes) {
			if(shape.isSelected) {
				newShape = shapeFactory.getShape(new ShapeExtrinsicState(((ShapeWithAllState)shape).extrinsicState.shapeType, new Point(shape.getLocation().x + moveBy, shape.getLocation().y), shape.getSize()));
			}
			index++;
		}

		if(newShape != null)
			shapes.set(index-1, newShape);
	}
	
	public void movev(int moveBy) {
		Shape newShape = null;
		int index = 0;

		for (Shape shape : shapes) {
			index++;
			if(shape.isSelected) {
				newShape = shapeFactory.getShape(new ShapeExtrinsicState(((ShapeWithAllState)shape).extrinsicState.shapeType, new Point(shape.getLocation().x, shape.getLocation().y + moveBy), shape.getSize()));
			}
		}

		if(newShape != null)
			shapes.set(index-1, newShape);
	}
}
