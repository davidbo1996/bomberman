import java.awt.Image;
import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import edu.princeton.cs.introcs.StdDraw;
	
	//0 = mur indestructible
	//1 = vide joueur avance
	//2 = mur cassable
	//3 = bonus
	//4 = pacman

public class Bomber {
	String nom ;
	public int posX = 1; // position sur l'axe des x 
	public int posY = 1; // position sur l'axe des y 
	public int initialX = 1;
	public int initialY = 1;
	public int score = 0;
	
	public boolean right, left, up, down;
	public int nombreVie = 3;
	private int nombreBombe;
	public boolean positionRight, positionLeft, positionUp, positionDown = false;
	Bombe bombe1 = new Bombe(this);
	Bombe bombe2 = new Bombe(this);
	Bombe bombe3 = new Bombe(this);
	
	
	Map mp;
	
	/*** Constructeur ***/
	public Bomber(String votreNom, int y, int x, Map myMap){
		mp=myMap;
		nom = votreNom;
		nombreBombe=3;
		initialX = posX = x;
		initialY = posY = y;
		placerJoueur(y,x);
	}
	
	public void placerInitialJoueur(){
		placerJoueur(initialY,initialX);
	}
	
public void poserBombe() // Methode permettant de poser 3 bombes max 
{
	if (getNombreBombe() != Map.C_MUR ){
	
		if (mp.lireTerrain(posY, posX) == Map.C_BOMBERMAN) {
			switch (getNombreBombe()){
			case 1:
				bombe1.PoserBombe();
				break;
			case 2:
				bombe2.PoserBombe();
				break;
			case 3:
				bombe3.PoserBombe();
				break;
			default:
				break;
			}
		}
	}
}

	public int getNombreBombe() {
	return nombreBombe;
}

public void setNombreBombe(int nombreBombe) {
	this.nombreBombe = nombreBombe;
}

	/*** Getters ***/
	public int getNombreVie(){
		return nombreVie;
	}
	public void setNombreVie(int nVie) {
		nombreVie = nVie;
	}

	public void placerJoueur(int y, int x)
	{
		// on efface l'ancienne position 
		if (mp.lireTerrain(posY, posX) == Map.C_JOUEUR_BOMBE ) //JOUEUR+BOMBE
		{
			mp.ecrireTerrain(posY, posX, Map.C_BOMBE);// on pose la bombe (BOMBE)
		}
		else 
		{
			mp.ecrireTerrain(posY, posX, Map.C_VIDE); // on efface l'ancienne CASE_VIDE
		}
		// on met a jour l'ancienne case 
		mp.majCase(posY,posX);
		
		posX = x; // Memoriser les nouvelles coordonnees du joueur
		posY = y;
		
		mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); // on pose le BOMBER à la nouvelle postion
		//on met a jour la nouvelle case 
		mp.majCase(posY,posX);
		
		
	}
	/*** Importer les sprites du bomberman ***/
	
	
	/******* Sprite du personnage*******/
	
	
	
	/****** Test mur ******/
	

	
	/***** HAUT ******/
	public boolean goUp()
	{		
		testDeplacementPossible();
		if (up==true){
			System.out.println("MouveUP y=" + (posY+1) +" x=" +posX);
			placerJoueur(posY+1, posX);
			return true;
		}
		return false;
	}
	/***** BAS *****/
	public void goDown()
	{
		testDeplacementPossible();
		if (down==true) {
			placerJoueur(posY-1, posX);
		}
	}			
	/***** DROITE ******/	
	public void goRight()
	{
		testDeplacementPossible();
		if (right==true){
			placerJoueur(posY, posX+1);
		}
	}

	/***** GAUCHE ****/
	public void goLeft()
	{
		testDeplacementPossible();
		if (left==true){
			placerJoueur(posY, posX-1);
		}
	}		
		
		//////////////////////////////////////////////////////////////////////////
	
	public void testDeplacementPossible(){							
		up = down = left = right = false;
		
		//On test le case la plus proche si elle est accessible on renvoit true 
		
		up = testCaseAccessible(posY+1, posX);
		down = testCaseAccessible(posY-1, posX);
		right = testCaseAccessible(posY, posX+1);
		left = testCaseAccessible(posY, posX-1);
		System.out.println("up=" + up + " down="+down + " left="+left + " right="+right);
		
	}
			
	public boolean testCaseAccessible(int y, int x) {
		boolean b = true;
		switch(mp.lireTerrain(y, x)){
			case Map.C_MUR:
			case Map.C_MUR_DESTRUCTIBLE:
			case Map.C_BOMBERMAN:
			case Map.C_BOMBE:
				b = false;
				break;
			default:
				b=true;
				break;
		}
		return b;
	}
/** Message défaite**/	
	
/*public  void messageDefaite() {
	if  ( (mp.B1.getNombreVie()==-1) || (mp.B2.getNombreVie()!=0) ) {
		
		StdDraw.picture(525, 500, "img/Victoire/VictoireJ1.png");
		
		
	}
	else if  ( (mp.B1.getNombreVie()!=0) && (mp.B2.getNombreVie()==-1)){
		StdDraw.picture(525, 500, "img/Victoire/VictoireJ1.png");
	}
}*/

}