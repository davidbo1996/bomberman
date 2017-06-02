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
public int rightCase, leftCase, upCase, downCase;
private Bomber bonus;
private int portee;




	public Bombe(Bomber b) {
	super();
	joueur = b;
	etat=0;
	this.portee=2;
	

	
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
		        		Explosion(getPortee());
			        	ajouterBombeInventaire();
			        	etat = 2;
			        	System.out.println(d.toString() + "Delais etat2 / restart");
						timer.stop();
			        	timer.setInitialDelay(500);
						timer.start();
	        			break;
	        		case 2:
						Explosion2(getPortee());
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
		joueur.mp.ecrireTerrain(y,x,Map.C_VIDE);
		joueur.mp.majCase(y,x);			
	}
	
	public void placerBonus(int y, int x) {
		
		joueur.mp.bonus(y, x);
		joueur.mp.majCase(y,x);
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


	public void testCasePossible(int portee)
	{							
		upCase = downCase = leftCase = rightCase = 0;
		int x = posx;
		int y = posy;
		int i = 1;
		boolean sortir=false;
		while (i<=portee && !sortir) 
		{		
			switch(joueur.mp.lireTerrain(y+i, x))
			{
				case Map.C_VIDE:
					upCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					upCase++;
					sortir=true;
					//sortir= BonusBombeRouge();
					break;
				case Map.C_MUR:
					sortir=true;
					break;
				default:
					break;
			}
			i++;
		}
		i = 1;
		sortir= false;
		while (i<=portee && !sortir) 
		{		
			switch(joueur.mp.lireTerrain(y-i, x))
			{
				case Map.C_VIDE:
					downCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					downCase++;
					sortir=true;
					//sortir= BonusBombeRouge();
					break;
				case Map.C_MUR:
					sortir=true;
					break;
				default:
					break;
			}
			i++;
		}
		i = 1;
		sortir= false;
		while (i<=portee && !sortir) 
		{		
			switch(joueur.mp.lireTerrain(y, x+i))
			{
				case Map.C_VIDE:
					rightCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					rightCase++;
					sortir=true;
					//sortir= BonusBombeRouge();
					break;
				case Map.C_MUR:
					sortir=true;
					break;
				default:
					break;
			}
			i++;
		}
		i = 1;
		sortir= false;
		while (i<=portee && !sortir) 
		{		
			switch(joueur.mp.lireTerrain(y, x-i))
			{
				case Map.C_VIDE:
					leftCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					leftCase++;
					sortir=true;
					//sortir= BonusBombeRouge();
					break;
				case Map.C_MUR:
					sortir=true;
					break;
				default:
					break;
			}
			i++;
		}

		
	}
	
		
	public void Explosion(int portee)
	{
		int x = posx;
		int y = posy;
		testCasePossible(portee);	
		for (int i=1; i<=portee;i++)
		{

			if (i <=rightCase)
			{
				placerFeux(y,x+i);
			}
			if (i <=leftCase)
			{
				placerFeux(y,x-i);
			}			
			if (i <=upCase)
			{
				placerFeux(y+i,x);
			}			
			if (i <=downCase)
			{
				placerFeux(y-i,x);
			}
		}
		killJoueur();
	}

	public void Explosion2(int portee)
	{
		int x = posx;
		int y = posy;
		for (int i=0; i<=portee;i++)
		{

			if (i <=rightCase)
			{
				placerVide(y,x+i);
			}
			if (i <=leftCase)
			{
				placerVide(y,x-i);
			}			
			if (i <=upCase)
			{
				placerVide(y+i,x);
			}			
			if (i <=downCase)
			{
				placerVide(y-i,x);
			}

		}
	}
	
	
	public void killJoueur()
	{
		if (joueur.mp.lireTerrain(joueur.posY,joueur.posX) == Map.C_FEU) // Si le personnage se trouve la case d'une explosion 
		{
			System.out.println("explosion vraie Boum Joueur tué :( ");
			joueur.setNombreVie(joueur.getNombreVie()-1);// on lui enleve une vie 
			System.out.println("Il lui reste NombreVie = " + (joueur.getNombreVie()-1));
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
		public void Bonus(int y, int x)
		{
			// Si le joueur est sur la case du bonus, on supprime le bonus et on le transforme en case vide
			if(joueur.mp.lireTerrain(y,x) == Map.CB_VIE && joueur.mp.lireTerrain(y,x) == Map.C_BOMBERMAN) 
			{
				joueur.BonusVie();
				placerVide(y,x);

			}
		}
	public int getPortee() 
	{
		return portee;
	}
	public void setPortee(int portee) 
	{
		this.portee = portee;
	}
	public void BonusFlammeBleue()
	{
		if (getPortee()>1)
		{
			setPortee(getPortee()-1);
		}
	}
	public void BonusFlammeJaune()
	{
		if (getPortee()<10)
		{
			setPortee(getPortee()+1);
		}
	}
	public void BonusFlammeRouge()
	{
		this.portee=10;
	}
	public boolean BonusBombeRouge()
	{
		return false; 
	}
		
}
