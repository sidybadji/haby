package game;

public class EnnemiArme extends EnnemiMobile {
    //cooldown :  quand il atteint 0, l'ennemi tire
    private int missile_ennemi_cooldown;

    public EnnemiArme(int x, int y){
        super(x,y);
        this.missile_ennemi_cooldown = 0;
        //TODO : image à changer
        chargerImage("src/resources/alien.png");
        getDimensions();
    }

    @Override
    public void bouger(){
        x -= Constante.VITESSE_ENNEMI_MOBILE;
    }

    public void actualiserCooldown(){
        this.missile_ennemi_cooldown--;
    }

    public MissileEnnemi tirer(){
        if(missile_ennemi_cooldown<=0){
            this.missile_ennemi_cooldown = Constante.MISSILE_ENNEMI_COOLDOWN;
            return new MissileEnnemi(this.x - width, this.y + height / 2);
        }
        return null;
    }
}
