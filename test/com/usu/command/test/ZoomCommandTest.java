package com.usu.command.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import com.usu.command.AddCommand;
import com.usu.command.ZoomCommand;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class ZoomCommandTest {

	@Test
	public void ZoomCommandTest_MoveValue() {
		Object[] commandParameters={"sun", new Point(40, 60), new Float(1)};
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		AddCommand addCommand = new AddCommand(commandParameters);
		addCommand.targetDrawing = dp;
		addCommand.execute();
		
		dp.getShapes().get(0).isSelected=true;
		
		commandParameters[0]=1.5f;
		ZoomCommand zTest = new ZoomCommand(commandParameters);
		zTest.targetDrawing = dp;
		zTest.execute();
		
		assertEquals(120, dp.getShapes().get(0).getSize().width);
	}
}
