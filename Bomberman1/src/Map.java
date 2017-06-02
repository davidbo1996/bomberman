import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import edu.princeton.cs.introcs.StdDraw;

public class Map {
	
	final static int C_MUR	 = 0; //              mur indestructible GRIS 
	final static int C_VIDE = 1; //               case vide          VERT 
	final static int C_MUR_DESTRUCTIBLE = 2;//    case               ORANGE
	final static int C_BOMBERMAN = 4;//                              ROND BLEU   
	final static int C_BOMBE = 5;//                                  ROND ROUGE
	final static int C_FEU = 6;//                                    ROND ORANGE
	final static int C_JOUEUR_BOMBE = 7;//                           ROND NOIR
	final static int CB_FLAMME_BLEUE = 10;//                         a definir 
	final static int CB_FLAMME_JAUNE = 11;
	final static int CB_FLAMME_ROUGE = 12;
	final static int CB_BOMBE_ROUGE = 13;
	final static int CB_VIE = 14;
	final static int CB_SPEED_UP = 15;
	final static int CB_SPEED_DOWN = 16;
	final static int CB_BOMBE_PLUS = 17;
	final static int CB_BOMBE_MOINS = 18;
	final static int CB_FLAMME_VERTE = 19;
	final static int CB_BOMBE_MINE = 20;
	final static int CB_BOMBE_REBONDISSANTE =21;
	final static int CB_LIGNE_BOMBE = 22;
	final static int CB_BOMB_KICK = 23;
	final static int CB_PASSE_MURAILLE = 24;
	final static int CB_BOUCLIER = 25;
	public static final int[][] lireTerrain = null;
	


	Bomber B1 ;
	Bomber B2 ;	

	private int[][] terrain = new int[][]{
		//  16
		//  yy
		//  yy terrain[y][x]
		//  00 xxxxxxxxx  20

		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,1,14,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,1,0,1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,10,0,11,0,12,0,13,0,14,0,15,0,16,0,17,0,18,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1,0,1,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,14,1,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};	
		
	public Map() {
		super();
		initMap();
		B1 =   new Bomber("alexis", 1, 1, this);
		B2 =   new Bomber("david", 15, 19, this);
	}

		/*{
// Matrice de test 
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};*/

				
	public void initMap(){//initialisation de la map 
		StdDraw.enableDoubleBuffering();
		//modification de la taille de la map 
		
		StdDraw.setCanvasSize(terrain[0].length*50,terrain.length*50);
		StdDraw.clear(StdDraw.BLACK);
		
		StdDraw.setXscale(-1,   terrain[0].length);
		StdDraw.setYscale(-1, terrain.length);
		for (int i=0; i<terrain[0].length; i++)
		{
			for (int j=0; j<terrain.length;j++)
			{
				majCase(j, i);
			}
		}
		StdDraw.show();
				
	}

	
	public void majCase(double y, double x) {
		//System.out.println("DEBUG x=" + x + " Y=" + y);
		switch(terrain[(int)y][(int) x]) 
		{
		case C_MUR: // mur indestructible
			StdDraw.picture(x, y, "img/Bricks/unbrick.png");
			break;
			case C_VIDE: // vide
				StdDraw.picture(x, y, "img/Bricks/floor.png");
				break;
			case C_MUR_DESTRUCTIBLE: // mur destructible
				StdDraw.picture(x, y, "img/Bricks/brick.png");
				break;
			case C_BOMBERMAN: // Bomber
				StdDraw.picture(x, y, "img/BomberMan/character1.png");
				break;
			case C_BOMBE: //Bombe
				StdDraw.picture(x, y, "img/Bombs/bomb.png");
				break;
			case C_FEU: //explosion
				StdDraw.picture(x, y, "img/Fire/Flamme.png");
				break;
			case C_JOUEUR_BOMBE://Bomberman + Bombe
				StdDraw.picture(x, y, "img/Bombs/bomb.png");
				break;
			case CB_FLAMME_BLEUE: 
				StdDraw.picture(x, y, "img/Bonus/Flamme_bleu.png");
				break;
			case CB_FLAMME_JAUNE: 
				StdDraw.picture(x, y, "img/Bonus/Flamme_jaune.png");
				break;
			case CB_FLAMME_ROUGE: 
				StdDraw.picture(x, y, "img/Bonus/Flamme_Rouge.png");
				break;
			case CB_BOMBE_ROUGE:
				StdDraw.picture(x, y, "img/Bonus/Bombe_Rouge.png");
				break;
			case CB_VIE: 
				StdDraw.picture(x, y, "img/Bonus/Vie.png");
				break;
			case CB_SPEED_UP: 
				StdDraw.picture(x, y, "img/Bonus/SpeedUp.png");
				break;
			case CB_SPEED_DOWN: 
				StdDraw.picture(x, y, "img/Bonus/SpeedDown.png");
				break;
			case CB_BOMBE_PLUS: 
				StdDraw.picture(x, y, "img/Bonus/BombUp.png");
				break;
			case CB_BOMBE_MOINS: 
				StdDraw.picture(x, y, "img/Bonus/BombDown.png");
				break;
			
			
			default:
				System.out.println("Probleme terrain Maj case");
				break;
			}	
	}		
	public void majMap()
	{
		StdDraw.enableDoubleBuffering();
		for (int i=0; i<terrain[0].length; i++){
			for (int j=0; j<terrain.length;j++){
				majCase(j,i);// Affichage du graphisme correspondant à la case
			}
		}
		StdDraw.show();		
	}

	public int lireTerrain(int y, int x) {
		if (!isXinside(x)) {
			System.out.println("Coordonnée X incorrecte");
		} else if (!isYinside(y)) {
			System.out.println("Coordonnée Y incorrecte");
		} else {
			//System.out.println("lecture terrain(" + y + "," + x + ")=" + terrain[y][x] );
			return terrain[y][x];
		}
		return -1; // Code de l'erreur
	}
	
	public int ecrireTerrain(int y, int x, int val) {
		if (!isXinside(x)) {
			System.out.println("Coordonnée X incorrecte");
		} else if (!isYinside(y)) {
			System.out.println("Coordonnée Y incorrecte");
		} else {
			//System.out.println("ecriture terrain(" + y + "," + x + ")=" + val );
			terrain[y][x] = val;
			return 0;
		}
		return -1; // Code de l'erreur
	}
	
	public void gameOver() {
		StdDraw.picture(100, 200, "img/GameOver/5.jpg");
	}
	
		
	public boolean isXinside(int x)
	{
		if (x >= terrain[0].length || x < 0) 
		{
			System.out.println("Coordonnée X incorrecte X = "+ x);
			return false;
		} 
		return true;
	}
	public boolean isYinside(int y)
	{
		if (y >= terrain.length || y < 0 ) 
		{
			System.out.println("Coordonnée Y incorrecte Y = "+ y);
			return false;
		}
		return true;
	}
	
	public void bonus(int y,int x) {
        //Génère un nombre aléatoire entre 0 et 5
        int r = 0 + (int) (Math.random() * (( 5	- 0) + 1));
        // Donc on a 20 % de chance d'avoir les bonus
        if(r <6){ 
            //Genere un nombre entre 1 et 8
            int p = 0 +(int) (Math.random() * ((8 - 1) + 1));
            switch(p){
                case 1:
                    // Flame Bleu
                	
                	ecrireTerrain(y,x, Map.CB_FLAMME_BLEUE); 
                	majCase(y,x);
                	System.out.println("Bombe bleu");
                	
                    break;
                case 2:
                  // Flame Jaune
                	ecrireTerrain(y,x, Map.CB_FLAMME_JAUNE);
                	majCase(y,x);
                	System.out.println("Flamme Jaune");
                    break;
                case 3:
                    // Flame Rouge
                	ecrireTerrain(y,x,Map.CB_FLAMME_ROUGE);
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	break;
                case 4: 
                	// Bombe rouge
                	ecrireTerrain(y,x,Map.CB_BOMBE_ROUGE);
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	break;
                case 5: 
                	//Vie
                	ecrireTerrain(y,x,Map.CB_VIE);
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	break; 
                	
                case 6:
                	// Speed Up 
                	ecrireTerrain(y,x,Map.CB_SPEED_UP);
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	
                	break; 
                case 7:
                	// Speed Down 
                	ecrireTerrain(y,x,Map.CB_SPEED_DOWN);
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	break; 
                	
                case 8: 
                	//Bombe Plus
                	ecrireTerrain(y,x,Map.CB_BOMBE_PLUS);
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	break; 
                	
                case 9:
                	//Bombe moins
                	ecrireTerrain(y,x,Map.CB_BOMBE_MOINS);
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	break;
                
                	
            }
        }
    }
	
	

	
}
