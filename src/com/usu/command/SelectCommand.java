package com.usu.command;

import java.awt.Point;

import com.usu.draw.Shape;

public class SelectCommand extends Command {

	private Point location;
	
	@Override
	public void execute() {
		Shape shape = targetDrawing!=null ? targetDrawing.findShapeAtPosition(location) : null;
        if (shape != null) {
        	shape.isSelected = !shape.isSelected;
            targetDrawing.isDirty = true;
        }
	}

	public SelectCommand(Object[] commandParameters) {
		if (commandParameters.length > 0)
            location = (Point) commandParameters[0];
	}

	public Point getLocation() {
		return location;
	}
}
