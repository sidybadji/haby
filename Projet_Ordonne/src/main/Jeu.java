package main;

import javax.swing.*;

import game.EspaceJeu;
import game.Vaisseau;
import moteurJeu.Audio;
import moteurJeu.Controleur;
import moteurJeu.Vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class Jeu extends JFrame {
    private Vaisseau vaisseau;
    private EspaceJeu tableau;
    private Vue vue;
    private Controleur controleur;

    public Jeu(){
        EspaceJeu tableau = new EspaceJeu();
        Vue vue = new Vue(tableau);
        controleur = new Controleur(tableau, tableau.getVaisseau(), vue);
        add(vue);
        setResizable(false);
        pack();
        setTitle("Scramble");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Jeu j = new Jeu();
            j.setVisible(true);
        });
    }
}
