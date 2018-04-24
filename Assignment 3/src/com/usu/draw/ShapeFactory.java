package com.usu.draw;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
	
	public String resourceNamePattern;
    public Type referenceType;

    private Map<String, ShapeIntrinsicState> sharedShapes = new HashMap<String, ShapeIntrinsicState>();

    public ShapeWithAllState getShape(ShapeExtrinsicState shapeExtrinsicState) {
        String resourceName = String.format(resourceNamePattern, shapeExtrinsicState.shapeType);

        ShapeIntrinsicState shapeIntrinsicState;
        if (sharedShapes.containsKey(shapeExtrinsicState.shapeType))
            shapeIntrinsicState = sharedShapes.get(shapeExtrinsicState.shapeType);
        else {
            shapeIntrinsicState = new ShapeIntrinsicState();
            shapeIntrinsicState.loadFromResource(resourceName);
            sharedShapes.put(shapeExtrinsicState.shapeType, shapeIntrinsicState);
        }

        return new ShapeWithAllState(shapeIntrinsicState, shapeExtrinsicState);
    }
}
