package game;

public class Essence extends EnnemiImmobile {
    public Essence(int x, int y){
        super(x,y);
        chargerImage("src/resources/fuel.png");
        getDimensions();
    }

    @Override
    public void collision(Entite e){
        if(e instanceof Vaisseau) setEstVisible(false);
    }

    @Override
    public void bouger() {
        super.bouger();
    }
}
