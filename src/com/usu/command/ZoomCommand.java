package com.usu.command;

public class ZoomCommand extends Command {
	float newScale;

	public ZoomCommand(Object[] commandParameters) {
		if (commandParameters.length > 0)
            newScale = (float)commandParameters[0];
	}
	
	@Override
	public void execute() {
		targetDrawing.changeScale(newScale);
	}

}
