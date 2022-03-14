package game;

public class MissileEnnemi extends Entite {

    public MissileEnnemi(int x, int y){
        super(x,y);
        chargerImage("src/resources/missile.png");
        getDimensions();
    }

    @Override
    public void collision(Entite e) {
        if(!(e instanceof Soin || e instanceof Essence)) {
            setEstVisible(false);
        }
    }

    @Override
    public void bouger(){
        x -= Constante.VITESSE_MISSILE_ENNEMI;
    }
}
