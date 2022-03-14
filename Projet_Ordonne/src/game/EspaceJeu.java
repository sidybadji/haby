package game;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.awt.*;
import javax.swing.*;

public class EspaceJeu {

    //potentiellement plusieurs listes pour regrouper différents types d'objets
    private Set<Entite> objets = new HashSet<>();
    private Vaisseau vaisseau;
    
    private final int OFFSET = 30;
    private final int SPACE = 16;

    private final int VAISSEAU_POS_INITIALE_X = 100;
    private final int VAISSEAU_POS_INITIALE_Y = 200;

    private int longueur = 0;
    private int hauteur = 0;
    
    private int getLongueur() {
    	return this.longueur;
    }
    private int getHauteur() {
    	return this.hauteur;
    }

	public int getOFFSET() {
		return OFFSET;
	}
	public int getSPACE() {
		return SPACE;
	}
	public int getVAISSEAU_POS_INITIALE_X() {
		return VAISSEAU_POS_INITIALE_X;
	}
	public int getVAISSEAU_POS_INITIALE_Y() {
		return VAISSEAU_POS_INITIALE_Y;
	}
	public String getLevel() {
		return level;
	}

    

    private String level
	= "#####################################################################                         #####################################################################\n"
	+ "#####################################################################                         #####################################################################\n"
	+ "#####################################################################                         #####################################################################\n"
	+ "#####################################################################                         #####################################################################\n"
	+ "###################################       S            ##############                         #####     &   E        ########                 ##          #########\n"
	+ "#  ########     ############           E   &   E  I     #############                         #####               S   #########    S      E   ##          #########\n"
	+ "#    ####        #########      I       @     @    @     #########  #                         #####    ##########   E #######       #####     ## E  ##    #########\n"
	+ "#     ##           ######       &                     @    ######   #                         ######      #######     #######       #####     ##    #     #   ....#\n"
	+ "#     #                           &     &   @    &          ####    #                         #### $      #######     ####    S   ###  &      ##    #     #    ...#\n"
	+ "#                         @       @    @     @               ##     #                         ###       #########      ####      ###          ##    #     #    ...#\n"
	+ "#                 @I        E I                   & @  I     #      #                         #####     ##########      ###      ###     #####      #     #    ...#\n"
	+ "#                    &  @   &   @  I     &     @     @              #                         ##      ############       ###     ###     #####       #    #       #\n"
	+ "#                           I       @  &        &        I  &   &   ############################     ##############      ##      ###  E    #####     #    #      ##\n"
	+ "#                &  @     @   E              @        E             @          &  @      @    &     ##############        #   E  ###       ####     #   E ###    ##\n"
	+ "#          @                    @    E    @     I   &      &   E  &   S     &     E   &  @  &     @ ##############        #      ###       ####     #     ###    ##\n"
	+ "#      E        &      &   &     @   @        @          &  @   &            &S       @     &       ##############        #      ###  E   $###  E  #      ###    ##\n"
	+ "#         #                  &            ###########     I          ############################################       ###      ###     ######     #     ###    ##\n"
	+ "#     S  ##     #  I      #   #    @     ############      E   S    #                         ################         ###       ###     #####      #     ###    ##\n"
	+ "#       ####   ##     S  ##  ###       ##############    S     @   ##                         ################  E     ###        ###      #####    #      ###    ##\n"
	+ "#    & #####  ### S E   ########  E   ################           ####                         ################      ######       ###     ######    ##     ###    ##\n"
	+ "##     ##### ####    ##########      ##################  S  E  ######                         ###############      ######          #    ######     #      ###    ##\n"
	+ "#################   ######     S   E ###################     ########                         #############       ####### &      #     ######      ###    ###    ##\n"
	+ "#####################################################################                         #########         #############     #                  #    #    E ##\n"
	+ "#####################################################################                         ########               II  E        #               E  #    I      ##\n"
	+ "#####################################################################                         #######                II           #################################\n"
	+ "#####################################################################                         #####################################################################\n";

    public EspaceJeu(){
        vaisseau = new Vaisseau(VAISSEAU_POS_INITIALE_X, VAISSEAU_POS_INITIALE_Y);
        initWorld();
    }
    
    private void initWorld() {
    	 
    	int x = OFFSET;
    	int y = OFFSET;
    	
    	Mur mur;
    	Surprise surp;
    	Soin soin;
    	Essence ess;
    	EnnemiArme ea;
    	EnnemiIndestructible eind;
    	EnnemiImmobile ei;
    	
    	for(int i=0; i<level.length(); i++) {
    		
    		char item = level.charAt(i);
    		
    		switch(item) {
    		
    			case '\n' :
    				y += SPACE;
    				if(this.longueur < x) 
    					this.longueur = x;
    				x = OFFSET;
    				break;
    			case '#' :
    				mur = new Mur(x, y);
    				objets.add(mur);
    				x += SPACE;
    				break;
    			case '$' :
    				surp = new Surprise(x, y);
    				objets.add(surp);
    				x += SPACE;
    				break;
    			case '&' :
    				eind = new EnnemiIndestructible(x, y);
    				objets.add(eind);
    				x += SPACE;
    				break;
    			case '@' :
    				ea = new EnnemiArme(x, y);
    				objets.add(ea);
    				x += SPACE;
    				break;
				case 'S' :
					soin = new Soin(x, y);
					objets.add(soin);
					x += SPACE;
					break;
				case 'E' :
					ess = new Essence(x, y);
					objets.add(ess);
					x += SPACE;
					break;
				case 'I' :
					ei = new EnnemiImmobile(x,y);
					objets.add(ei);
					x += SPACE;
					break;
    			case ' ' :
    				x += SPACE;
    				break;
    			default :
    				break;
    		}
    		hauteur = y;
    	}
    }


    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public Set<Entite> getObjets(){
        return this.objets;
    }
}
