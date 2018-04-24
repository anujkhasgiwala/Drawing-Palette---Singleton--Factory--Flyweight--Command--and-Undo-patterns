package com.usu.command;

public class MoveVerticalCommand extends Command {
	int moveBy;

	public MoveVerticalCommand(Object[] commandParameters) {
		if (commandParameters.length > 0)
            moveBy = (int)commandParameters[0];
	}
	
	@Override
	public void execute() {
		targetDrawing.movev(moveBy);
	}

}
