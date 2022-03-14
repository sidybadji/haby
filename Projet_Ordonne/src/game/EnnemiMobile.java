package game;

public class EnnemiMobile extends Entite {

    public EnnemiMobile(int x, int y){
        super(x,y);
        chargerImage("src/resources/alien.png");
        getDimensions();
    }

    @Override
    public void bouger(){
        x -= Constante.VITESSE_ENNEMI_MOBILE;
    }

    @Override
    public void collision(Entite e){
        if(!(e instanceof Soin || e instanceof Essence)){
            setEstVisible(false);
        }
    }
}

