package com.usu.command.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import com.usu.command.AddCommand;
import com.usu.command.MoveVerticalCommand;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class MoveVerticalCommandTest {

	@Test
	public void MoveVerticalTest_MoveValue0() {
		Object[] commandParameters={"sun", new Point(40, 60), new Float(1)};
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		AddCommand addCommand = new AddCommand(commandParameters);
		addCommand.targetDrawing = dp;
		addCommand.execute();
		
		dp.getShapes().get(0).isSelected=true;
		commandParameters[0]=0;
		MoveVerticalCommand mvTest = new MoveVerticalCommand(commandParameters);
		mvTest.targetDrawing = dp;
		mvTest.execute();
		
		assertEquals(dp.getShapes().get(0).getLocation().y, dp.getShapes().get(0).getLocation().y);
	}

	@Test
	public void MoveVerticalTest_MoveValue() {
		Object[] commandParameters={"sun", new Point(40, 60), new Float(1)};
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		AddCommand addCommand = new AddCommand(commandParameters);
		addCommand.targetDrawing = dp;
		addCommand.execute();
		
		dp.getShapes().get(0).isSelected=true;
		commandParameters[0]=20;
		MoveVerticalCommand mvTest = new MoveVerticalCommand(commandParameters);
		mvTest.targetDrawing = dp;
		mvTest.execute();
		
		assertEquals(40, dp.getShapes().get(0).getLocation().y);
	}
}
