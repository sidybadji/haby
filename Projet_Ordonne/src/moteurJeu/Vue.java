package moteurJeu;

import javax.swing.*;

import game.MissileJoueur;
import game.Mur;
import game.Surprise;
import game.EnnemiImmobile;
import game.EnnemiIndestructible;
import game.Entite;
import game.EspaceJeu;
import game.Vaisseau;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class Vue extends JPanel {
    private EspaceJeu tableau; //le tableau de jeu
    private final int LARGEUR_VUE = 600;
    private final int HAUTEUR_VUE = 500;
    private boolean perdu;
    private int x;
    private int y;

    private boolean isCompleted = false;	

    public Vue(EspaceJeu tableau){
        this.tableau = tableau;
        setPreferredSize(new Dimension(LARGEUR_VUE, HAUTEUR_VUE));
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    public void perdre(){
        this.perdu = true;
    }

    public boolean partiePerdue(){
        return this.perdu;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);;
        if (perdu) {
            affichageGameOver(g);
        }else {
            affichage(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void affichage(Graphics g){
        //caméra centrée sur le vaisseau
        if (tableau.getVaisseau().isEstVisible()) {
            g.drawImage(tableau.getVaisseau().getImage(), LARGEUR_VUE / 2, HAUTEUR_VUE / 2, this);
        }
        Set<Entite> obj;
        //on filtre les objets pour enlever ceux qui sont hors de l'écran
        obj = tableau.getObjets().stream()
                .filter(e -> Math.abs(e.getX()-tableau.getVaisseau().getX())<LARGEUR_VUE/2 + 10 && Math.abs(e.getY()-tableau.getVaisseau().getY())<HAUTEUR_VUE/2 +10)
                .filter(e -> e.isEstVisible())
                .collect(Collectors.toSet());
        for (Entite e : obj) {
            g.drawImage(e.getImage(), e.getX() - tableau.getVaisseau().getX() + LARGEUR_VUE / 2, e.getY() - tableau.getVaisseau().getY() + HAUTEUR_VUE / 2, this);
        }
        
        afficherEtat(g);
        afficherEssence(g);
        afficherScore(g);
    }

    public void affichageGameOver(Graphics g){
        String msg = "Perdu";
        String desc = "Votre vaisseau a été détruit";
        String score = "Votre Score est de : " +tableau.getVaisseau().getScore();
        Font msg_f = new Font("Helvetica", Font.BOLD, 18);
        FontMetrics fm = getFontMetrics(msg_f);
        g.setColor(Color.white);
        g.setFont(msg_f);
        g.drawString(msg, (LARGEUR_VUE - fm.stringWidth(msg)) / 2, HAUTEUR_VUE / 2);
        g.drawString(desc, (LARGEUR_VUE - fm.stringWidth(desc)) /2, 3* (HAUTEUR_VUE /4));
        g.drawString(score, (LARGEUR_VUE - fm.stringWidth(score)) /2, 5* (HAUTEUR_VUE /6));
    }

    public void afficherEtat(Graphics g){
        String msg = "Etat du vaisseau : " + tableau.getVaisseau().getEtatVaisseau();
        Font msg_f = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(msg_f);
        g.setColor(Color.white);
        g.setFont(msg_f);
        g.drawString(msg, 0, 12);
    }

    public void afficherEssence(Graphics g){
        String msg = "Essence restante : " + tableau.getVaisseau().getEssence();
        Font msg_f = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(msg_f);
        g.setColor(Color.white);
        g.setFont(msg_f);
        g.drawString(msg, 0, 25);
    }

    public void afficherScore(Graphics g){
        String msg = "Score : " + tableau.getVaisseau().getScore();
        Font msg_f = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(msg_f);
        g.setColor(Color.white);
        g.setFont(msg_f);
        g.drawString(msg, 0, 37);
    }

	public int getLargeurVue(){
        return LARGEUR_VUE;
    }

    public int getHauteurVue(){
        return HAUTEUR_VUE;
    }
}
