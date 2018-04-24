package com.usu.command;

public class UndoCommand extends Command {
	CommandFactory commandFactory;
	
	public UndoCommand(Object[] commandParameters) {
		if (commandParameters.length > 0)
			commandFactory = (CommandFactory)commandParameters[0];
	}

	@Override
	public void execute() {
		if(!commandFactory.undoCommandStack.empty() && (!(commandFactory.undoCommandStack.peek() instanceof NewCommand) && commandFactory.undoCommandStack.size() > 0)) {
			commandFactory.undoCommandStack.pop();
		} else {
			
		}
	}
}
