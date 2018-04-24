package com.usu.command.test;

import static org.junit.Assert.assertFalse;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;

import com.usu.command.DeselectCommand;
import com.usu.draw.ShapeExtrinsicState;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class DeselctCommandTest {

	@Test
	public void DeselctCommandExecuteTest() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("cloud", new Point(40, 50), new Dimension(80, 80))));
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("land", new Point(40, 50), new Dimension(80, 80))));
		
		DeselectCommand dsTest = new DeselectCommand();
		dsTest.targetDrawing = dp;
		
		dsTest.execute();
		
		assertFalse(dp.getShapes().get(0).isSelected);
	}

}
