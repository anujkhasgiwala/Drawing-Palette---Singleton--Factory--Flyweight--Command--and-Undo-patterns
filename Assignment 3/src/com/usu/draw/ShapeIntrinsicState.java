package com.usu.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShapeIntrinsicState extends Shape {

	public static Color selectedBackgroundColor;
	public String shapeType;
	public BufferedImage image;
	public BufferedImage toolImage;
	//public BufferedImage toolImageSelected;
	
	public String shapeName;

	@Override
	public void draw(Graphics2D graphics, JPanel drawPannel) {
		try {
			throw new Exception("Cannot draw a shape with only intrinsic state");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void loadFromResource(String shapeName) {
		if (shapeName == null || shapeName.isEmpty()) return;
		
		this.shapeName = shapeName;
		try {
			image = ImageIO.read(new File(shapeName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		toolImage = new BufferedImage(toolSize.width, toolSize.height, BufferedImage.TYPE_INT_RGB);
		//toolImageSelected = new Bitmap(toolSize.width, toolSize.height);
		
		Graphics2D graphics = toolImage.createGraphics();
		
		/*graphics.drawImage(ToolImageSelected);*/
		//graphics.setColor(selectedBackgroundColor);
		graphics.drawImage(toolImage, 0, 0, null);

	}
	
	@Override
	public boolean isSelected() {
		return false;
	}
	
	@Override
	public void setSelected(boolean value) {
		try {
			throw new Exception("Cannot select a tree with only intrinsic state - the intrinsic state is immutable");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	public Point getLocation() {
		return new Point();
    }
    
	@Override
    public void setLocation(Point value) {
		try {
			throw new Exception("Cannot change a tree with only intrinsic state - the intrinsic state is immutable");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
    public Dimension getSize() {
		return new Dimension();
    }
	
	@Override
    public void setSize(Dimension value) {
		try {
			throw new Exception("Cannot draw a tree with only intrinsic state");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}