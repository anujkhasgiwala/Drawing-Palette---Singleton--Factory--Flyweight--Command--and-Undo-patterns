package com.usu.command;

import javax.swing.JPanel;

public class SaveCommand extends Command {

	private String filename;
	private JPanel panel;
	
    public SaveCommand(Object[] commandParameters) {
    	if (commandParameters.length > 0)
            filename = (String)commandParameters[0];
    	if (commandParameters.length > 1)
            panel = (JPanel)commandParameters[1];
    }
	
	@Override
	public void execute() {
		if(targetDrawing!=null)
			targetDrawing.save(filename);
	}

}
