package com.usu.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class Shape {
	
	/*public static Pen SelectedPen = new Pen(Color.DarkGray);
    public static Brush HandlesBrush = new SolidBrush(Color.Black);*/
    public static int handleHalfSize = 3;
    public static Dimension toolSize = new Dimension(64,64);

    public Point location = new Point(0, 0);
    public Dimension size = new Dimension(0, 0);
    public boolean isSelected = false;

    public abstract void draw(Graphics2D graphics, JPanel drawPannel);

	public static int getHandleHalfSize() {
		return handleHalfSize;
	}

	public static void setHandleHalfSize(int handleHalfSize) {
		Shape.handleHalfSize = handleHalfSize;
	}

	public static Dimension getToolSize() {
		return toolSize;
	}

	public static void setToolSize(Dimension toolSize) {
		Shape.toolSize = toolSize;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
