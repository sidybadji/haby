package game;

public class Vaisseau extends Entite {
    //déplacement du vaisseau, valeurs modifiées par le contrôleur
    private int dx;
    private int dy;
    //cooldown :  quand il atteint 0, le vaisseau peut tirer
    private int missile_cooldown;
    private int etat_vaisseau;
    private int essence;
    private int chute;
    private static int score = 0;
    private HighScore meilleurScore;
    public Vaisseau(int x, int y) {
        super(x,y);
        this.missile_cooldown = 0;
        this.chute = 0;
        this.essence = Constante.ESSENCE_MAX;
        this.etat_vaisseau = Constante.ETAT_VAISSEAU_MAX;
        chargerImage("src/resources/vaisso.png");
        getDimensions();
    }

    public void bouger(){
        x += dx;
        y += dy + chute;
    }

    /*
        cette méthode est appellée dans actionPerformed() dans Controleur, qui est appelée (par défaut)
        toutes les 15 ms. avec un cooldown de base de 30 le vaisseau peut donc tirer un missile toutes
        les 450 ms.
     */
    public void actualiserCooldown(){
        this.missile_cooldown--;
    }

    public void actualiserEssence(){
        if(essence <=0) {
            this.chute = 1;
        }else{
            this.essence--;
        }
    }

    public MissileJoueur tirer() {
        if (missile_cooldown <= 0) {
            missile_cooldown = Constante.MISSILE_JOUEUR_COOLDOWN;
            return new MissileJoueur(this.x + width, this.y + height / 2, this);
        }
        return null;
    }

    public void setDx(int dx){
        this.dx = dx;
    }

    public void setDy(int dy){
        this.dy = dy;
    }

    public int getVitesse(){
        return Constante.VITESSE_VAISSEAU;
    }

    public int getEtatVaisseau(){
        return this.etat_vaisseau;
    }

    public int getEssence(){
        return this.essence;
    }

    public static int getScore(){
        return score;
    }

    public static void addScore(int x){
        score += x;
    }
    

    @Override
    public void collision(Entite e) {
        if(e instanceof MissileEnnemi) {
            etat_vaisseau -= Constante.DEGAT_MISSILE_ENNEMI;
            if (etat_vaisseau <= 0) {
                setEstVisible(false);
            }
        }else if(e instanceof Soin) {
            etat_vaisseau += Constante.SOIN;
            if (etat_vaisseau > Constante.ETAT_VAISSEAU_MAX) etat_vaisseau = Constante.ETAT_VAISSEAU_MAX;
        }else if(e instanceof Essence){
            essence += Constante.ESSENCE;
            if(essence > Constante.ESSENCE_MAX) essence = Constante.ESSENCE_MAX;
        }else{
            setEstVisible(false);
        }
    }
    
}
