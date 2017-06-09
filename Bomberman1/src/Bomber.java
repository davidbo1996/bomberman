import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;

	import edu.princeton.cs.introcs.StdDraw;

public class Bomber {
	String nom ;

	public int posX = 1; // position sur l'axe des x 
	public int posY = 1; // position sur l'axe des y 
	public int initialX = 1;
	public int initialY = 1;
	private int speed;
	private int nombreVie;
	private int nombreBombe;
	
	public boolean right, left, up, down;
	public boolean positionRight, positionLeft, positionUp, positionDown = false;
	
	
	Bombe bombe1 = new Bombe(this);
	Bombe bombe2 = new Bombe(this);
	Bombe bombe3 = new Bombe(this);
	Bombe bombe4 = new Bombe(this);
	Bombe bombe5 = new Bombe(this);
	Bombe bombe6 = new Bombe(this);
	Bombe bombe7 = new Bombe(this);
	Map mp;
	
	/*** Constructeur ***/
	public Bomber(String votreNom, int y, int x, Map myMap){
		mp=myMap;
		nom = votreNom;
		this.nombreVie=3;
		this.nombreBombe=3;
		this.speed=100;
		initialX = posX = x;
		initialY = posY = y;
		placerJoueur(y,x);
		System.out.println("Le joueur 1 à NBVIE= "+ getNombreVie());	
	}
	
	/*** GETTERS ***/
	public int getNombreBombe() 
	{
	return nombreBombe;
	}
	public int getNombreVie()
	{
		return nombreVie;
	}
	public int getSpeed() {
		return speed;
	}

	/*** Setters ***/
	public void setNombreVie(int nVie) {
		nombreVie = nVie;
	}
	public void setNombreBombe(int nombreBombe) 
	{
	this.nombreBombe = nombreBombe;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
	
	
	public void placerInitialJoueur(){
		placerJoueur(initialY,initialX);
	}
	
	public void poserBombe() // Methode permettant de poser un nombre de bombes max 
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
				case 4:
					bombe4.PoserBombe();
					break;
				case 5:
					bombe5.PoserBombe();
					break;
				case 6:
					bombe6.PoserBombe();
					break;
				case 7:
					bombe7.PoserBombe();
					break;
				default:
					break;
				}
			}
		}
	}


	public void placerJoueur(int y, int x)
	{
		// on efface l'ancienne position 
		System.out.println("PlacerJoueur Y = "+y+" X = "+x);
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
		switch (mp.lireTerrain(y, x))// Activation des bonus en regardant le type  de la case suivante 
		{
		case Map.CB_VIE:
			BonusVie();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); // on pose le BOMBER à la nouvelle position
			mp.majCase(posY,posX);//on met a jour la nouvelle case 
			break;
		case Map.CB_SPEED_UP:
			BonusSpeedUp();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN);
			mp.majCase(posY,posX);
			break;
		case Map.CB_SPEED_DOWN:
			BonusSpeedDown();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN);
			mp.majCase(posY,posX);
			break;
		case Map.CB_BOMBE_PLUS:
			BonusBombePlus();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN);
			mp.majCase(posY,posX);
			//StdDraw.clear();
			//mp.initMap();
			break;
		case Map.CB_BOMBE_MOINS:
			BonusBombeMoins();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); 
			mp.majCase(posY,posX);
			//StdDraw.clear();
			//mp.initMap();
			break;
		case Map.CB_FLAMME_BLEUE:
			activerBonusFlammeBleue();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); 
			mp.majCase(posY,posX);
			break;
		case Map.CB_FLAMME_JAUNE:
			activerBonusFlammeJaune();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); 
			mp.majCase(posY,posX);
			break;
		case Map.CB_FLAMME_ROUGE:
			activerBonusFlammeRouge();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); 
			mp.majCase(posY,posX);
			break;
		case Map.CB_BOMBE_ROUGE:
			activerBonusBombeRouge();
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); 
			mp.majCase(posY,posX);
			break;
		
		default:
			mp.ecrireTerrain(posY, posX, Map.C_BOMBERMAN); // on pose le BOMBER à la nouvelle postion
			//on met a jour la nouvelle case 
			mp.majCase(posY,posX);
			break;
		}

		
		
	}
		
	/***** HAUT ******/
	public void goUp()
	{		
		testDeplacementPossible();
		if (up==true){
			System.out.println("MouveUP y=" + (posY+1) +" x=" +posX);
			placerJoueur(posY+1, posX);
		}
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
	
	
	public void killJoueur()
	{
		if (mp.lireTerrain(posY, posX) == Map.C_FEU) // Si le personnage se trouve la case d'une explosion 
		{
			System.out.println("explosion vraie Boum Joueur tué :( ");
			setNombreVie(getNombreVie()-1);// on lui enleve une vie 
			System.out.println("Il lui reste NombreVie = " + (getNombreVie()-1));
			placerInitialJoueur();
		}
//		else
//		{
//			System.out.println("joueur toujours vivant :) ");
//		}
	}
	
	/*** BONUS BOMBERMAN ***/
	public void BonusVie() 
	{
		setNombreVie(getNombreVie()+1);
		//System.out.println("Le joueur 1 à NBVIE ="+ getNombreVie());
		//System.out.println("Le joueur à NBombe ="+ getNombreBombe());
	}
	
	public void BonusSpeedUp()
	{
		setSpeed(getSpeed()-25);
		if (getSpeed() <0 ) 
			{
			setSpeed(0);
			}
		
	}
	
	public void BonusSpeedDown()
	{
		setSpeed(getSpeed()+25);
		//speed = speed + 50;
		if (getSpeed() > 500 ) 
			{
			setSpeed(0);
			StdDraw.pause(200);
			}
		
	}
	
	public void BonusBombePlus()
	{
		if (getNombreBombe() < 7)
		{
			setNombreBombe(getNombreBombe()+2);
			//System.out.println("Vous avez "+ getNombreBombe()+" bombes" );
		}
	}
	
	public void BonusBombeMoins(){
		if (getNombreBombe() >=4){
			
			setNombreBombe(getNombreBombe()-2);
			//System.out.println("Vous avez "+ getNombreBombe()+" bombes" );
		}
		else if(getNombreBombe()==3)
		{
			setNombreBombe(getNombreBombe()-1);
			//System.out.println("Vous avez "+ getNombreBombe()+" bombes" );
		}	
	}
	
	/*** ACTIVATION BONUS BOMBE ***/
	public void activerBonusFlammeBleue()
	{
		bombe1.BonusFlammeBleue();
		bombe2.BonusFlammeBleue();
		bombe3.BonusFlammeBleue();
		bombe4.BonusFlammeBleue();
		bombe5.BonusFlammeBleue();
		bombe6.BonusFlammeBleue();
		bombe7.BonusFlammeBleue();
	}
	public void activerBonusFlammeJaune()
	{
		bombe1.bonusFlammeJaune();
		bombe2.bonusFlammeJaune();
		bombe3.bonusFlammeJaune();
		bombe4.bonusFlammeJaune();
		bombe5.bonusFlammeJaune();
		bombe6.bonusFlammeJaune();
		bombe7.bonusFlammeJaune();
	}
	
	public void activerBonusFlammeRouge()
	{
		bombe1.BonusFlammeRouge();
		bombe2.BonusFlammeRouge();
		bombe3.BonusFlammeRouge();
		bombe4.BonusFlammeRouge();
		bombe5.BonusFlammeRouge();
		bombe6.BonusFlammeRouge();
		bombe7.BonusFlammeRouge();
	}
	public void activerBonusBombeRouge()
	{
		bombe1.BonusBombeRouge();
		bombe2.BonusBombeRouge();
		bombe3.BonusBombeRouge();
		bombe4.BonusBombeRouge();
		bombe5.BonusBombeRouge();
		bombe6.BonusBombeRouge();
		bombe7.BonusBombeRouge();	
	}
	


}