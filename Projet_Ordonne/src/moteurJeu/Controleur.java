package moteurJeu;

  import java.awt.*;
import javax.swing.Timer;

  import game.*;

  import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.HashSet;
  import java.util.stream.Collectors;

public class Controleur implements ActionListener {
    private EspaceJeu tableau;
    private Vaisseau vaisseau;
    private Vue vue;
    private Timer timer;
    private final int INTERVALLE = 15;
    private boolean pause;

    public Controleur(EspaceJeu t, Vaisseau vaiss, Vue v){
        pause = false;
        tableau = t;
        vaisseau = vaiss;
        vue = v;
        timer = new Timer(INTERVALLE, this);
        timer.start();
        vue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE && !pause) {
                    MissileJoueur m = vaisseau.tirer();
                    if(m!= null) {
                    	tableau.getObjets().add(m);
                    	Audio.playSound("/src/resources/tir1.wav");
                    }
                }
                if (key == KeyEvent.VK_LEFT && !pause) {
                	vaisseau.setDx(-vaisseau.getVitesse());
                	Audio.playSound("/src/audio/vaiss1.wav");
                }
                if (key == KeyEvent.VK_RIGHT && !pause) {
                    vaisseau.setDx(vaisseau.getVitesse());
                    Audio.playSound("/src/audio/vaiss1.wav");
                }
                if (key == KeyEvent.VK_UP && !pause) {
                    vaisseau.setDy(-vaisseau.getVitesse());
                    Audio.playSound("/src/audio/vaiss1.wav");
                }
                if (key == KeyEvent.VK_DOWN && !pause) {
                    vaisseau.setDy(vaisseau.getVitesse());
                    Audio.playSound("/src/audio/vaiss1.wav");
                }
                if (key == KeyEvent.VK_P){
                    pause = !pause;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    vaisseau.setDx(0);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    vaisseau.setDx(0);
                }
                if (key == KeyEvent.VK_UP) {
                    vaisseau.setDy(0);
                }
                if (key == KeyEvent.VK_DOWN) {
                    vaisseau.setDy(0);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(!pause) {
            enJeu();
            actualiserVaisseau();
            actualiserObjets();
            actualiserMissiles();
            collisions();
            vue.repaint();
        }
    }

    public void actualiserVaisseau(){
        if(vaisseau.isEstVisible()){
            vaisseau.actualiserCooldown();
            vaisseau.actualiserEssence();
            vaisseau.bouger();
        }else{
            vue.perdre();
        }
    }

    public void enJeu(){
        if(vue.partiePerdue()){
            timer.stop();
        }
    }

    public void actualiserMissiles(){
        Set<Entite> obj;
        obj = tableau.getObjets();
        for(Entite e : obj){
            //pour supprimer les missiles du joueur qui atteignent le côté droit de l'écran
            if(e instanceof MissileJoueur && e.getX()-vaisseau.getX()>=vue.getLargeurVue()/2) e.setEstVisible(false);
            //pour supprimer les missiles des ennemis qui atteignent le côté gauche de l'écran
            if(e instanceof MissileEnnemi && vaisseau.getX()-e.getX()>=vue.getLargeurVue()/2) e.setEstVisible(false);
        }
    }

    public void actualiserObjets(){
        Set<Entite> obj;
        //on filtre les objets pour enlever ceux qui sont hors de l'écran
        obj = tableau.getObjets().stream()
                .filter(e -> Math.abs(e.getX()-vaisseau.getX())<vue.getLargeurVue()/2 + 10 && Math.abs(e.getY()-vaisseau.getY())<vue.getHauteurVue()/2 +10)
                .collect(Collectors.toSet());
        for (Entite e : obj){
            if (e.isEstVisible()) {
                e.bouger();
                if(e instanceof EnnemiArme){
                    ((EnnemiArme) e).actualiserCooldown();
                    MissileEnnemi m = ((EnnemiArme) e).tirer();
                    if(m!=null){
                        tableau.getObjets().add(m);
                    }
                }
            }
        }
        for(Entite e : obj){
            if(! e.isEstVisible()){
                tableau.getObjets().remove(e);
            }
        }
    }

    public void collisions(){
        Rectangle r1 = vaisseau.getRectangle();
        Set<Entite> obj;
        obj = tableau.getObjets().stream()
                .filter(e -> Math.abs(e.getX()-vaisseau.getX())<vue.getLargeurVue()/2 + 10 && Math.abs(e.getY()-vaisseau.getY())<vue.getHauteurVue()/2 +10)
                .collect(Collectors.toSet());
        for(Entite e : obj){
            Rectangle r2 = e.getRectangle();
            for(Entite e2 : obj){
                Rectangle r3 = e2.getRectangle();
                if(e2 != e && r2.intersects(r3)){
                    e.collision(e2);
                    e2.collision(e);
                }
            }
            if(r1.intersects(r2)){
                e.collision(vaisseau);
                vaisseau.collision(e);
            }
        }
    }

    public void pause(){
    }
}
