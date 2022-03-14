package game;

public class EnnemiIndestructible extends EnnemiMobile {

    public EnnemiIndestructible(int x, int y) {
        super(x, y);
        //TODO : image à changer
        chargerImage("src/resources/vaiEnn.png");
        getDimensions();
    }

    @Override
    public void bouger() {
        x -= Constante.VITESSE_ENNEMI_MOBILE;
    }

    @Override
    public void collision(Entite e) {
        //indestructible, il ne se passe rien
    }
}
