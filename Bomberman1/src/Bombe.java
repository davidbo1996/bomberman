import edu.princeton.cs.introcs.StdDraw;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Bombe extends JLabel {
private String nom;
private String Bomber;
private Bomber joueur;
private int posx;
private int posy;
Timer timer; 
private int etat=1;
private Bomber bonus;


	public Bombe(Bomber b) {
	super();
	joueur = b;
	etat=0;
	
    // Création et lancement du timer
}
	  private Timer createTimer()
	  {
	    // Création d'une instance de listener 
	    // associée au timer
	    ActionListener action = new ActionListener ()
	      {
	        // Méthode appelée à chaque tic du timer
	        public void actionPerformed (ActionEvent event)
	        {

	        	{
	        	    Date d = new Date();
	        		switch (etat) {
	        		case 1:
		        		Explosion();
			        	ajouterBombeInventaire();
			        	etat = 2;
			        	System.out.println(d.toString() + "Delais etat2 / restart");
						timer.stop();
			        	timer.setInitialDelay(500);
						timer.start();
	        			break;
	        		case 2:
						Explosion2();
						etat=0;
			        	System.out.println(d.toString()+ "Delais etat0 / stop");
						timer.stop();
						break;
	        		default:
	        			break;
	        		}
	        	}
	        }
	      };

	      return new Timer(3500, action);
	    
	 }

	public void PoserBombe()
	{
	    Date d = new Date();
		etat=1;
		timer = createTimer();
		timer.start();
    	System.out.println(d.toString() + "Delais etat1 / start");

		enleverBombeInventaire();
		// on fixe la position de la bombe en lui donnant les coordonnees du joueur
		posx=joueur.posX;
		posy=joueur.posY;
		placerBomberEtBombe(posy,posx);
	}
	
	public void placerBomberEtBombe(int y, int x)
	{
		joueur.mp.ecrireTerrain(y, x, Map.C_JOUEUR_BOMBE);
		joueur.mp.majCase(y,x);
		System.out.println("placer J+B " + y + " " + x);
	}
	
	public void placerFeux(int y, int x)
	{
		joueur.mp.ecrireTerrain(y,x,Map.C_FEU);
		joueur.mp.majCase(y,x);
		
	}
	
	
	public void placerVide(int y, int x)
	{
		if (joueur.mp.lireTerrain(y, x) == Map.C_FEU && joueur.mp.lireTerrain(y, x) == Map.C_MUR_DESTRUCTIBLE) {
			joueur.mp.bonus(y, x);
		}
				
				
				else {
				joueur.mp.ecrireTerrain(y,x,Map.C_VIDE);
				joueur.mp.majCase(y,x);
				}
				
		}
	
	public void placerBonus(int y, int x) {
		
		joueur.mp.bonus(y, x);
	}
			
	
	public void enleverBombeInventaire() 
	{
		joueur.setNombreBombe(joueur.getNombreBombe()-1);
		System.out.println("Nombre de bombe"+joueur.getNombreBombe());
		
	}
	
	public void ajouterBombeInventaire()
	{
		joueur.setNombreBombe(joueur.getNombreBombe()+1);
		System.out.println("Nombre de bombe"+joueur.getNombreBombe());
	}
	public boolean testCaseAccessibleExplosionBombe(int y, int x) {
		boolean b = true;
		switch(joueur.mp.lireTerrain(y, x)){
			case Map.C_MUR:
				b = false;
				break;
			default:
				b=true;
				break;
		}
		return b;
	}
	public void Explosion(/*int portee*/)
	{
		etat=2;
		System.out.println("etat =" + etat);
		int x=posx;
		int y = posy;
/*
		for (int i=0;i<portee; i++)
		{
			if (joueur.mp.lireTerrain(y,x+i)!= Map.C_MUR)
			{
				placerFeux(y,x+1); //premier cas
				if (joueur.mp.lireTerrain(y,x+i+2)!= Map.C_MUR &&joueur.mp.lireTerrain(y,x+i+2) != Map.C_MUR_DESTRUCTIBLE )
				{ // right 
					placerFeux(y,x+i+1);
					//System.out.println("Explosion Right fonctionne");
				}
			}
			if (joueur.mp.lireTerrain(y,x-i) != Map.C_MUR)
			{
				placerFeux(y,x-1);
				if (joueur.mp.lireTerrain(y,x-i-2)!= Map.C_MUR && joueur.mp.lireTerrain(y,x-i-2) != Map.C_MUR_DESTRUCTIBLE  )
				{ 
					// left 
					placerFeux(y,x-i-1);
					//System.out.println("Explosion Left fonctionne");
				}
			}
			if (joueur.mp.lireTerrain(y-i,x) != Map.C_MUR)
			{
				placerFeux(y-1,x);
				if (joueur.mp.lireTerrain(y-i-2,x)!= Map.C_MUR && joueur.mp.lireTerrain(y-i-2,x) != Map.C_MUR_DESTRUCTIBLE )
				{ // down 
					placerFeux(y-i-1,x);
					//System.out.println("Explosion Down fonctionne");
				}
			}
			if (joueur.mp.lireTerrain(y+i,x) != Map.C_MUR)
			{
				placerFeux(y+1,x);
				if (joueur.mp.lireTerrain(y+i+2,x) != Map.C_MUR && joueur.mp.lireTerrain(y+i+2,x) != Map.C_MUR_DESTRUCTIBLE )
				{ // up 
					placerFeux(y+i+1,x);
					//System.out.println(" Explosion up fonctionne");
				}
			}
		}*/	
		if (joueur.mp.lireTerrain(y,x+1)!= Map.C_MUR)
		{
			placerFeux(y,x+1); //premier cas
			if (joueur.mp.lireTerrain(y,x+2)!= Map.C_MUR &&joueur.mp.lireTerrain(y,x+2) != Map.C_MUR_DESTRUCTIBLE )
			{ // right 
				placerFeux(y,x+2);
				//System.out.println("Explosion Right fonctionne");
			}
		}
		if (joueur.mp.lireTerrain(y,x-1) != Map.C_MUR)
		{
			placerFeux(y,x-1);
			if (joueur.mp.lireTerrain(y,x-2)!= Map.C_MUR && joueur.mp.lireTerrain(y,x-2) != Map.C_MUR_DESTRUCTIBLE  )
			{ 
				// left 
				placerFeux(y,x-2);
				//System.out.println("Explosion Left fonctionne");
			}
		}
		if (joueur.mp.lireTerrain(y-1,x) != Map.C_MUR)
		{
			placerFeux(y-1,x);
			if (joueur.mp.lireTerrain(y-2,x)!= Map.C_MUR && joueur.mp.lireTerrain(y-2,x) != Map.C_MUR_DESTRUCTIBLE )
			{ // down 
				placerFeux(y-2,x);
				//System.out.println("Explosion Down fonctionne");
			}
		}
		if (joueur.mp.lireTerrain(y+1,x) != Map.C_MUR)
		{
			placerFeux(y+1,x);
			if (joueur.mp.lireTerrain(y+2,x) != Map.C_MUR && joueur.mp.lireTerrain(y+2,x) != Map.C_MUR_DESTRUCTIBLE )
			{ // up 
				placerFeux(y+2,x);
				//System.out.println(" Explosion up fonctionne");
			}
		}
		
		
		//On verifie que pour chaque cas 

		killJoueur();
	}
	
	public void Explosion2()
	{
		int x = posx;
		int y = posy;
		//Au niveau de la bombe
		if (joueur.mp.lireTerrain(y,x) == Map.C_BOMBE )
		{
			placerVide(y,x);
		}
		if (joueur.mp.lireTerrain(y,x+1) == Map.C_FEU   )
		{ // right 
			placerVide(y,x+1);
		}
		if (joueur.mp.lireTerrain(y,x-1) ==Map.C_FEU)
		{ 
			// left 
			placerVide(y,x-1);
		}
		if (joueur.mp.lireTerrain(y-1,x) == Map.C_FEU)
		{ // down 
			placerVide(y-1,x);
		}
		if (joueur.mp.lireTerrain(y+1,x) == Map.C_FEU)
		{ // up 
			placerVide(y+1,x);
		}
		
		/*** 2 ieme case à vérifier ****/
		//On verifie que pour chaque cas 
		if (joueur.mp.lireTerrain(y,x+1) != Map.C_MUR )
		{
			if (joueur.mp.lireTerrain(y,x+2) == Map.C_FEU)
			{ // right 
				placerVide(y,x+2);
				//System.out.println("Explosion Right fonctionne");
			}
		}

		if (joueur.mp.lireTerrain(y,x-1)!= Map.C_MUR)
		{
			if (joueur.mp.lireTerrain(y,x-2) == Map.C_FEU)
			{ 
				// left 
				placerVide(y,x-2);
				//System.out.println("Explosion Left fonctionne");
			}
		}

	
		if (joueur.mp.lireTerrain(y-1,x) != Map.C_MUR )
		{
			if (joueur.mp.lireTerrain(y-2,x) == Map.C_FEU)
			{ // down 
				
				placerVide(y-2,x);
				//System.out.println("Explosion Down fonctionne");
			}
		}
	
		if (joueur.mp.lireTerrain(y+1,x) != Map.C_MUR)
		{
			if (joueur.mp.lireTerrain(y+2,x) == Map.C_FEU)
			{ // up 
				placerVide(y+2,x);
				joueur.mp.bonus(y, x);
				//System.out.println(" Explosion up fonctionne");
			}	
		}
	
	}
	
	
	public void killJoueur()
	{
		if (joueur.mp.lireTerrain(joueur.posY,joueur.posX) == Map.C_FEU) // Si le personnage se trouve la case d'une explosion 
		{
			System.out.println("explosion vraie Boum Joueur tué :( ");
			joueur.setNombreVie(joueur.getNombreVie()-1);// on lui enleve une vie 
			System.out.println(joueur.getNombreVie()-1);
			joueur.placerInitialJoueur();
			
		}
		else
		{
			System.out.println("joueur toujours vivant :) ");
		}
	}
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = joueur.posX;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = joueur.posY;
	}
	
	// Supprimer les bonus 
		public void Bonus() {
			int x = posx; 
			int y = posy;
			// Si le joueur est sur la case du bonus, on supprime le bonus et on le transforme en case vide
			if(joueur.mp.lireTerrain(y,x) == Map.CB_FLAMME_BLEUE && joueur.mp.lireTerrain(y,x) == Map.C_BOMBERMAN) {
				placerVide(y,x);
			}
			
			if(joueur.mp.lireTerrain(y,x) == Map.CB_FLAMME_JAUNE && joueur.mp.lireTerrain(y,x) == Map.C_BOMBERMAN) {
				placerVide(y,x);
			}
			
			if(joueur.mp.lireTerrain(y,x) == Map.CB_FLAMME_ROUGE && joueur.mp.lireTerrain(y,x) == Map.C_BOMBERMAN) {
				placerVide(y,x);
			}
			if(joueur.mp.lireTerrain(y,x) == Map.CB_BOMBE_ROUGE && joueur.mp.lireTerrain(y,x) == Map.C_BOMBERMAN) {
				placerVide(y,x);
			}
			
		}
		
		// Créer les bonus de la bombe
		
		public void Flamme_bleu(){
			
		}
		
		public void Flamme_jouge(){
			
		}
		
		public void Flamme_jaune() {
			
		}

}
