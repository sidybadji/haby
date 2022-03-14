package game;

public class EnnemiImmobile extends Entite {

    public EnnemiImmobile(int x, int y){
        super(x,y);
        chargerImage("src/resources/unnamed.png");
        getDimensions();
    }

    @Override
    public void collision(Entite e){
        setEstVisible(false);
    }

    @Override
    public void bouger(){
        //rien ne se passe car l'objet est immobile
    }
}

