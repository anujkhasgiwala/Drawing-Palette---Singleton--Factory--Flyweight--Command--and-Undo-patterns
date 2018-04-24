package com.usu.command.test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;

import com.usu.draw.ShapeExtrinsicState;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class NewCommandTest {

	@Test
	public void NewCommandExecuteTest() {
		DrawingPalette dp = new DrawingPalette();
		
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("cloud", new Point(40, 50), new Dimension(80, 80))));
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("land", new Point(40, 50), new Dimension(80, 80))));
		
		dp.clear();
		
		assertEquals(0, dp.getShapes().size());
	}

}
