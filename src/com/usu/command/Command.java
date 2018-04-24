package com.usu.command;

import com.usu.drawingGUI.DrawingPalette;

public abstract class Command {
	public DrawingPalette targetDrawing;      // "Receiver" in the Command Pattern

    public abstract void execute();

	public DrawingPalette getTargetDrawing() {
		return targetDrawing;
	}

	public void setTargetDrawing(DrawingPalette targetDrawing) {
		this.targetDrawing = targetDrawing;
	}
}
