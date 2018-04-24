package com.usu.command;

import javax.swing.JPanel;

public class LoadCommand extends Command {

	private String filename;
	
	private JPanel drawPanel;
	
	public LoadCommand() {
		
	}
	
    public LoadCommand(Object[] commandParameters) {
        if (commandParameters.length > 0)
            filename = (String)commandParameters[0];
        if (commandParameters.length > 1)
            drawPanel = (JPanel)commandParameters[1];
    }
    
	@Override
	public void execute() {
		if(targetDrawing != null)
        	targetDrawing.load(filename);
	}
}
