package game;

public class Soin extends EnnemiImmobile {
    public Soin(int x, int y){
        super(x,y);
        chargerImage("src/resources/repair.png");
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
