package game;

public class Surprise extends EnnemiImmobile {
    /*
    cette classe est censée définir un objet destructible et immobile qui
    donne un avantage aléatoire au joueur (vie, points, essence ou autre)
     */
    //TODO : définition du comportement de l'objet en cas de destruction par le joueur

    public Surprise(int x, int y){
        super(x,y);
        chargerImage("");
        getDimensions();
    }

    @Override
    public void collision(Entite e){
        super.collision(e);
    }

    @Override
    public void bouger() {
        super.bouger();
    }
}