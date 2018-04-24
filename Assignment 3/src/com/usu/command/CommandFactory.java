package com.usu.command;

import java.util.Stack;

import com.usu.drawingGUI.DrawingPalette;

public class CommandFactory {
	public DrawingPalette targetDrawing;
	
	Stack<Command> undoCommandStack = new Stack<Command>();
	
	private static CommandFactory commandFactory;

	public DrawingPalette getTargetDrawing() {
		return targetDrawing;
	}

	public void setTargetDrawing(DrawingPalette targetDrawing) {
		this.targetDrawing = targetDrawing;
	}
	
	public Command create(String commandType, Object... commandParameters) {
        if (commandType == null || commandType.isEmpty()) return null;

        Command command=null;
        switch (commandType.trim().toUpperCase()) {
	        case "NEW":
	        	command = new NewCommand();
	        	break;
        	case "ADD":
                command = new AddCommand(commandParameters);
                break;
            case "REMOVE":
                command = new RemoveSelectedCommand();
                break;
            case "SELECT":
                command = new SelectCommand(commandParameters);
                break;
            case "DESELECT":
                command = new DeselectCommand();
                break;
            case "LOAD":
                command = new LoadCommand(commandParameters);
                break;
            case "SAVE":
                command = new SaveCommand(commandParameters);
                break;
            case "ZOOM":
                command = new ZoomCommand(commandParameters);
                break;
            case "UNDO":
            	command = new UndoCommand(commandParameters);
            	break;
            case "MOVEH":
            	command = new MoveHorizontalCommand(commandParameters);
            	break;
            case "MOVEV":
            	command = new MoveVerticalCommand(commandParameters);
        }

        if (command!=null) {
            if(!(command instanceof UndoCommand)) {
            	command.targetDrawing = targetDrawing;
            	undoCommandStack.push(command);
            }
        }

        return command;
    }
	
	private CommandFactory() {

	}
	
	public static synchronized CommandFactory getInstance() {
		if(commandFactory == null)
			commandFactory = new CommandFactory();
		return commandFactory;
	}
}
