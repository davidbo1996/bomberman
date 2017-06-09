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
private int portee;
private boolean sortir,bombeRouge; 


	/*** CONSTRUCTEUR ***/
	public Bombe(Bomber b)
	{
	super();
	joueur = b;
	etat=0;
	this.portee=2;
	this.sortir=false;
	this.bombeRouge=true;
    // Création et lancement du timer
	}
	
	/*** GETTERS ***/
	public int getPosx() 
	{
		return posx;
	}
	public int getPosy() 
	{
		return posy;
	}
	public int getPortee() 
	{
		return portee;
	}
	public boolean isSortir()
	{
		return sortir;
	}
	public boolean isbombeRouge() {
		return bombeRouge;
	}


	/*** SETTERS ***/
	public void setPosx(int posx) 
	{
		this.posx = joueur.posX;
	}
	public void setPosy(int posy) 
	{
		this.posy = joueur.posY;
	}
	public void setPortee(int portee) 
	{
		this.portee = portee;
	}
	public void setSortir(boolean sortir) 
	{
		this.sortir = sortir;
	}
	public void setbombeRouge(boolean bombeRouge) {
		this.bombeRouge = bombeRouge;
	}
	
	/*** TIMER ***/
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
	
	/***   PLACER OBJETS DANS MAP ***/
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
	public void placerBonus(int y, int x)
	{	
		joueur.mp.bonus(y, x);
		joueur.mp.majCase(y,x);
	}
			
	/*** INVENTAIRE BOMBE ***/
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
		this.sortir=false;
		
		while (i<=portee && !isSortir()) 
		{		
			switch(joueur.mp.lireTerrain(y+i, x))
			{
				case Map.C_VIDE:
				case Map.C_BOMBERMAN:
					upCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					upCase++;
					joueur.mp.bonus(y+i,x);
					setSortir(isbombeRouge());
					break;
				case Map.C_MUR:
					setSortir(true);
					break;
				default:
					break;
			}
			i++;
		}
		i = 1;
		setSortir(false);
		while (i<=portee && !isSortir()) 
		{		
			switch(joueur.mp.lireTerrain(y-i, x))
			{
				case Map.C_VIDE:
				case Map.C_BOMBERMAN:
					downCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					downCase++;
					joueur.mp.bonus(y-i,x);
					setSortir(isbombeRouge());
					break;
				case Map.C_MUR:
					setSortir(true);
					break;
				default:
					break;
			}
			i++;
		}
		i = 1;
		setSortir(false);
		while (i<=portee && !isSortir()) 
		{		
			switch(joueur.mp.lireTerrain(y, x+i))
			{
				case Map.C_VIDE:
				case Map.C_BOMBERMAN:
					rightCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					rightCase++;
					joueur.mp.bonus(y,x+i);
					setSortir(isbombeRouge());
					break;
				case Map.C_MUR:
					setSortir(true);
					break;
				default:
					break;
			}
			i++;
		}
		i = 1;
		setSortir(false);
		while (i<=portee && !isSortir()) 
		{		
			switch(joueur.mp.lireTerrain(y, x-i))
			{
				case Map.C_VIDE:
				case Map.C_BOMBERMAN:
					leftCase++;
					break;
				case Map.C_MUR_DESTRUCTIBLE:
					leftCase++;
					joueur.mp.bonus(y,x-i);
					setSortir(isbombeRouge());
					break;
				case Map.C_MUR:
					setSortir(true);
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
		
		if (!joueur.mp.isBonus(y,x)) { 
			placerFeux(y,x);
		}
		testCasePossible(portee);	
		for (int i=1; i<=portee;i++)
		{

			if (i <=rightCase)
			{
				if (!joueur.mp.isBonus(y,x+i)) { 
					placerFeux(y,x+i);
				}
			}
			if (i <=leftCase)
			{
				if (!joueur.mp.isBonus(y,x-i)) 
				{ 
				placerFeux(y,x-i);
				}
			}			
			if (i <=upCase)
			{
				if (!joueur.mp.isBonus(y+i,x)) 
				{ 
				placerFeux(y+i,x);
				}
			}			
			if (i <=downCase)
			{
				if (!joueur.mp.isBonus(y-i,x)) 
				{ 
				placerFeux(y-i,x);
				}
			}
		}
	}

	public void Explosion2(int portee)
	{
		int x = posx;
		int y = posy;
		for (int i=0; i<=portee;i++)
		{

			if (i <=rightCase)
			{
				if (!joueur.mp.isBonus(y,x+i)) { 
					placerVide(y,x+i);
				}
			}
			if (i <=leftCase)
			{
				if (!joueur.mp.isBonus(y,x-i)) 
				{ 
					placerVide(y,x-i);
				}

			}			
			if (i <=upCase)
			{
				if (!joueur.mp.isBonus(y+i,x)) 
				{
				placerVide(y+i,x);
				}
			}			
			if (i <=downCase)
			{
				if (!joueur.mp.isBonus(y-i,x)) 
				{
				placerVide(y-i,x);
				}
			}

		}
	}
	

		
	/*** METHODE BONUS BOMBE ***/
	public void BonusFlammeBleue()
	{
		if (getPortee()>1)
		{
			setPortee(getPortee()-1);
		}
	}
	public void bonusFlammeJaune()
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
	public void BonusBombeRouge()
	{
		setbombeRouge(false); 
	}
		
}
