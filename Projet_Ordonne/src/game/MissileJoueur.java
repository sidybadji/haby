package game;

public class MissileJoueur extends Entite{
    private Vaisseau proprietaire;

    public MissileJoueur(int x, int y, Vaisseau p){
        super(x,y);
        this.proprietaire = p;
        chargerImage("src/resources/missile.png");
        getDimensions();
    }

    @Override
    public void collision(Entite e) {
        if(!(e instanceof Soin || e instanceof Essence)) {
            setEstVisible(false);
            if(e instanceof MissileEnnemi) Vaisseau.addScore(Constante.SCORE_MISSILE_ENNEMI);
            else if(e instanceof EnnemiImmobile && !(e instanceof ObstacleIndestructible)) Vaisseau.addScore(Constante.SCORE_ENNEMI_IMMOBILE);
            else if(e instanceof EnnemiMobile && !(e instanceof EnnemiIndestructible)) Vaisseau.addScore(Constante.SCORE_ENNEMI_MOBILE);
            else if(e instanceof EnnemiArme) Vaisseau.addScore(Constante.SCORE_ENNEMI_ARME);
            else if(e instanceof Surprise);
        }
    }

    @Override
    public void bouger(){
        x += Constante.VITESSE_MISSILE_JOUEUR;
    }
}
