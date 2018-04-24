package com.usu.command;

public class RemoveSelectedCommand extends Command {

	@Override
	public void execute() {
		if(targetDrawing != null)
			targetDrawing.deleteAllSelected();
	}

}
