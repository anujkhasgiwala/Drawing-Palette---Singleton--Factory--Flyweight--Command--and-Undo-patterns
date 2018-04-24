package com.usu.command;

import java.awt.Dimension;
import java.awt.Point;

import com.usu.draw.Shape;
import com.usu.draw.ShapeExtrinsicState;

public class AddCommand extends Command {

	private int normalWidth = 80;
    private int normalHeight = 80;
	
    private String shapeType;
    private Point location;
    private float scale;
    
	public AddCommand() {
	}
	
	public AddCommand(Object[] commandParameters) {
		if (commandParameters.length > 0)
            shapeType = (String)commandParameters[0];
        if (commandParameters.length > 1)
            location = (Point)commandParameters[1];
        else
            location = new Point(0, 0);

        if (commandParameters.length > 2)
            scale = (float) commandParameters[2];
        else
            scale = 1.0F;
	}
    
    @Override
	public void execute() {
    	if ((shapeType == null && shapeType.isEmpty()) || targetDrawing == null) return;

        Dimension shapeSize = new Dimension((int)Math.round(normalWidth * scale), (int)Math.round(normalHeight * scale));
        
        Point shapeLocation = new Point(location.x - shapeSize.width / 2, location.y - shapeSize.height / 2);

        ShapeExtrinsicState extrinsicState = new ShapeExtrinsicState(shapeType, shapeLocation, shapeSize);
        
        Object shape = targetDrawing.shapeFactory.getShape(extrinsicState);
        targetDrawing.add((Shape)shape);
	}

}
