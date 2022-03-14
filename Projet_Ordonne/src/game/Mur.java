package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Mur extends ObstacleIndestructible{
	
	private Image image;
	
	public Mur(int x, int y) {
		super(x, y);
		
		chargerImage("src/resources/mur.png");
        getDimensions();
	}

}
