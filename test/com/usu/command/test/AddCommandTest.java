package com.usu.command.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import com.usu.command.AddCommand;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class AddCommandTest {

	@Test(expected=NullPointerException.class)
	public void AddCommandTest_NullShapeType() {
		String shapeType = null;
		Object[] commandParameters={shapeType, new Point(40, 60), new Float(1)};
		AddCommand addCommandTest = new AddCommand(commandParameters);
		addCommandTest.execute();
	}
	
	//There won't be any output or returning value
	@Test
	public void AddCommandTest_EmptyShapeType() {
		Object[] commandParameters={"", new Point(40, 60), new Float(1)};
		AddCommand addCommandTest = new AddCommand(commandParameters);
		addCommandTest.execute();
	}
	
	@Test(expected=NullPointerException.class)
	public void AddCommandTest_NullLocation() {
		Point p = null;
		Object[] commandParameters={"sun", p, new Float(1)};
		AddCommand addCommandTest = new AddCommand(commandParameters);
		addCommandTest.targetDrawing = new DrawingPalette();
		addCommandTest.execute();
	}
	
	@Test
	public void AddCommandTest_EmptyLocation() {
		Object[] commandParameters={"sun", new Point(), new Float(1)};
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		AddCommand addCommandTest = new AddCommand(commandParameters);
		addCommandTest.targetDrawing = dp;
		addCommandTest.execute();
		assertEquals(-40, dp.getShapes().get(0).getLocation().x);
		assertEquals(-40, dp.getShapes().get(0).getLocation().y);
	}
	
	@Test
	public void AddCommandTest_AllCorrect() {
		Object[] commandParameters={"sun", new Point(40, 60), new Float(1)};
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		AddCommand addCommandTest = new AddCommand(commandParameters);
		addCommandTest.targetDrawing = dp;
		addCommandTest.execute();
		assertEquals(1, dp.getShapes().size());
	}
}
