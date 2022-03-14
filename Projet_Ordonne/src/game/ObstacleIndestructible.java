package game;

public class ObstacleIndestructible extends EnnemiImmobile {

    public ObstacleIndestructible(int x, int y){
        super(x,y);
        chargerImage("src/resources/ground.png");
        getDimensions();
    }

    @Override
    public void collision(Entite e){
        //indestructible, il n'est pas détruit par une collision
    }

    @Override
    public void bouger() {
        //comme son ancêtre, immobile
        super.bouger();
    }
}