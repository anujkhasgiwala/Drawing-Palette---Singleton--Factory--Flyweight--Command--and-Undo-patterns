package com.usu.drawingGUI.test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;

import com.usu.draw.ShapeExtrinsicState;
import com.usu.draw.ShapeFactory;
import com.usu.drawingGUI.DrawingPalette;

public class DrawingPaletteTest {

	DrawingPalette drawingPaletteTest = new DrawingPalette();
	
	private void initialize() {
		drawingPaletteTest.shapeFactory = new ShapeFactory();
		drawingPaletteTest.shapeFactory.resourceNamePattern = "img/%s.jpg";
	}
	
	@Test
	public void addShapeTest_ShapeNull() {
		drawingPaletteTest.add(null);
		assertEquals(0, drawingPaletteTest.getShapes().size());
	}
	
	@Test
	public void addShapeTest_Shape() {
		initialize();
		drawingPaletteTest.add(drawingPaletteTest.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		assertEquals(1, drawingPaletteTest.getShapes().size());
	}
	
	@Test
	public void clearTest(){
		initialize();
		drawingPaletteTest.getShapes().add(drawingPaletteTest.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		drawingPaletteTest.getShapes().add(drawingPaletteTest.shapeFactory.getShape(new ShapeExtrinsicState("cloud", new Point(40, 50), new Dimension(80, 80))));
		drawingPaletteTest.getShapes().add(drawingPaletteTest.shapeFactory.getShape(new ShapeExtrinsicState("land", new Point(40, 50), new Dimension(80, 80))));
		
		drawingPaletteTest.clear();
		assertEquals(0, drawingPaletteTest.getShapes().size());		
	}
	
	@Test
	public void findShapeAtPositionTest() {
		initialize();
		drawingPaletteTest.getShapes().add(drawingPaletteTest.shapeFactory.getShape(new ShapeExtrinsicState("sun", new Point(40, 50), new Dimension(80, 80))));
		assertNotNull(drawingPaletteTest.findShapeAtPosition(new Point(60,70)));
	}
}
