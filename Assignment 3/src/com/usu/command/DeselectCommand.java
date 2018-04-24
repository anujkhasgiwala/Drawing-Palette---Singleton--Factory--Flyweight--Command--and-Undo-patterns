package com.usu.command;

import javax.swing.JPanel;

public class DeselectCommand extends Command {
	JPanel panel;
	
	@Override
	public void execute() {
		targetDrawing.deselectAll();
	}
	
	public DeselectCommand() {
	}
}
