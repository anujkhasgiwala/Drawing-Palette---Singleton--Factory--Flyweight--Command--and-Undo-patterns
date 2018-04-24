package com.usu.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShapeWithAllState extends Shape {

	private ShapeIntrinsicState intrinsicState;
    public ShapeExtrinsicState extrinsicState;
    
	@Override
	public void draw(Graphics2D graphics, JPanel drawPanel) {
		if (graphics == null || intrinsicState == null) return;

        try {
			//graphics.drawImage(ImageIO.read( new File(intrinsicState.shapeName)), 80, 80, null);

        	Image image = ImageIO.read(new File(intrinsicState.shapeName));
        	drawPanel.getGraphics().drawImage(image, (int)extrinsicState.getLocation().x, (int)extrinsicState.getLocation().y, (int)extrinsicState.getSize().width, (int)extrinsicState.getSize().height, new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					return false;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

        if (extrinsicState.isSelected) {
            graphics.drawRect(extrinsicState.location.x, extrinsicState.location.y, extrinsicState.size.width, extrinsicState.size.height);

            DrawActionHandle(graphics, extrinsicState.location.x, extrinsicState.location.y);
            DrawActionHandle(graphics, extrinsicState.location.x + extrinsicState.size.width, extrinsicState.location.y);
            DrawActionHandle(graphics, extrinsicState.location.x, extrinsicState.location.y + extrinsicState.size.height);
            DrawActionHandle(graphics, extrinsicState.location.x + extrinsicState.size.width, extrinsicState.location.y + extrinsicState.size.height);
        }
	}
	
	ShapeWithAllState(ShapeIntrinsicState sharedPart, ShapeExtrinsicState nonsharedPart) {
        intrinsicState = sharedPart;
        extrinsicState = nonsharedPart;
    }
	
	private void DrawActionHandle(Graphics2D graphics, int x, int y) {
        graphics.fillRect(x - handleHalfSize, y - handleHalfSize, handleHalfSize*2, handleHalfSize*2);
    }
	
	@Override
	public boolean isSelected() {
		return extrinsicState.isSelected;
	}
	
	@Override
	public void setSelected(boolean value) {
		extrinsicState.isSelected = value;
    }

	@Override
	public Point getLocation() {
    	return extrinsicState.location;
    }
    
	@Override
    public void setLocation(Point value) {
    	extrinsicState.location = value;
    }

	@Override
    public Dimension getSize() {
    	return extrinsicState.size;
    }
	
	@Override
    public void setSize(Dimension value) {
		extrinsicState.size = value;
    }
}