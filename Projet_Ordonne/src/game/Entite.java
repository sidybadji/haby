package game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Entite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    private boolean estVisible;
    

    public Entite(int x, int y){
        this.x = x;
        this.y = y;
        setEstVisible(true);
    }

    protected void getDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void chargerImage(String nom) {
        ImageIcon imageIcon = new ImageIcon(nom);
        image = imageIcon.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void setEstVisible(boolean b){
        estVisible  = b;
    }

    public abstract void collision(Entite e);

    public abstract void bouger();

	public boolean isEstVisible() {
		return estVisible;
	}

}
