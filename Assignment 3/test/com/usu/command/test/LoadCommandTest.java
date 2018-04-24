package com.usu.command.test;

import org.junit.Test;

import com.usu.command.LoadCommand;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class LoadCommandTest {

	@Test(expected=IllegalArgumentException.class)
	public void LoadCommandTest_FileNameNull() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
				
		String fileName = null;
		Object[] commandParameters = {fileName};
		LoadCommand ldTest = new LoadCommand(commandParameters);
		ldTest.targetDrawing = dp;
		ldTest.execute();
	}

	@Test
	public void LoadCommandTest_FileNameEmpty() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		
		String fileName = "";
		Object[] commandParameters = {fileName};
		LoadCommand ldTest = new LoadCommand(commandParameters);
		ldTest.targetDrawing = dp;
		ldTest.execute();
	}
	
	@Test
	public void LoadCommandTest_FileCorrect() {
		DrawingPalette dp = new DrawingPalette();
		dp.shapeFactory = new ShapeFactory();
		dp.shapeFactory.resourceNamePattern = "img/%s.jpg";
		
		String fileName = "a.json";
		Object[] commandParameters = {fileName};
		LoadCommand ldTest = new LoadCommand(commandParameters);
		ldTest.targetDrawing = dp;
		ldTest.execute();
	}
}
