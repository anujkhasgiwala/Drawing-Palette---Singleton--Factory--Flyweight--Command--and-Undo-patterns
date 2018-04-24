package com.usu.command;

public class MoveHorizontalCommand extends Command {
	int moveBy;

	public MoveHorizontalCommand(Object[] commandParameters) {
		if (commandParameters.length > 0)
            moveBy = (int)commandParameters[0];
	}
	
	@Override
	public void execute() {
		targetDrawing.moveh(moveBy);
	}

}
