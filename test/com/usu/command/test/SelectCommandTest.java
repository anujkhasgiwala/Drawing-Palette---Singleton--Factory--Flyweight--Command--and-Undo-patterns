package com.usu.command.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;

import com.usu.command.SelectCommand;
import com.usu.draw.ShapeExtrinsicState;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class SelectCommandTest {

	@Test
	public void SelectCommandTest_NullLocation() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		
		Point p = null;
		Object[] commandParameters={p};
		SelectCommand selectCommandTest = new SelectCommand(commandParameters);
		assertNull(selectCommandTest.getLocation());
	}
	
	@Test
	public void SelectCommandTest_EmptyLocation() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		
		Point p = new Point();
		Object[] commandParameters={p};
		SelectCommand selectCommandTest = new SelectCommand(commandParameters);
		
		selectCommandTest.targetDrawing = dp;
		
		selectCommandTest.execute();
		
		assertFalse(dp.getShapes().get(0).isSelected);
	}
	
	@Test
	public void SelectCommandTest_AllCorrect() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		dp.getShapes().add(dp.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		
		Object[] commandParameters={new Point(60, 60)};
		SelectCommand selectCommandTest = new SelectCommand(commandParameters);
		
		selectCommandTest.targetDrawing = dp;
		
		selectCommandTest.execute();
		
		assertTrue(dp.getShapes().get(0).isSelected);
		assertTrue(dp.isDirty);
	}
}
