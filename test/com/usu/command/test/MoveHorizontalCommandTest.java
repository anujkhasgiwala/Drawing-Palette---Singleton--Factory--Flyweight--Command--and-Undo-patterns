package com.usu.command.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import com.usu.command.AddCommand;
import com.usu.command.MoveHorizontalCommand;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class MoveHorizontalCommandTest {

	@Test
	public void MoveHorizontalTest_MoveValue0() {
		Object[] commandParameters={"sun", new Point(40, 60), new Float(1)};
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		AddCommand addCommand = new AddCommand(commandParameters);
		addCommand.targetDrawing = dp;
		addCommand.execute();
		
		commandParameters[0]=0;
		MoveHorizontalCommand mhTest = new MoveHorizontalCommand(commandParameters);
		mhTest.targetDrawing = dp;
		mhTest.execute();
		
		assertEquals(dp.getShapes().get(0).getLocation().x, dp.getShapes().get(0).getLocation().x);
	}

	@Test
	public void MoveHorizontalTest_MoveValue() {
		Object[] commandParameters={"sun", new Point(40, 60), new Float(1)};
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		AddCommand addCommand = new AddCommand(commandParameters);
		addCommand.targetDrawing = dp;
		addCommand.execute();
		
		dp.getShapes().get(0).isSelected=true;
		
		commandParameters[0]=20;
		MoveHorizontalCommand mhTest = new MoveHorizontalCommand(commandParameters);
		mhTest.targetDrawing = dp;
		mhTest.execute();
		
		assertEquals(20, dp.getShapes().get(0).getLocation().x);
	}
}
