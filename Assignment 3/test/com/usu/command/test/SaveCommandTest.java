package com.usu.command.test;

import org.junit.Test;

import com.usu.command.SaveCommand;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class SaveCommandTest {

	@Test(expected=NullPointerException.class)
	public void SaveCommandTest_FileNameNull() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
				
		String fileName = null;
		Object[] commandParameters = {fileName};
		SaveCommand sTest = new SaveCommand(commandParameters);
		sTest.targetDrawing = dp;
		sTest.execute();
	}

	@Test(expected=Exception.class)
	public void SaveCommandTest_FileNameEmpty() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		
		String fileName = "";
		Object[] commandParameters = {fileName};
		SaveCommand sTest = new SaveCommand(commandParameters);
		sTest.targetDrawing = dp;
		sTest.execute();
	}
	
	@Test
	public void SaveCommandTest_FileCorrect() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		
		String fileName = "a.json";
		Object[] commandParameters = {fileName};
		SaveCommand sTest = new SaveCommand(commandParameters);
		sTest.targetDrawing = dp;
		sTest.execute();
	}

}
