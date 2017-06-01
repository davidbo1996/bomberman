import java.awt.Image;
import java.awt.event.KeyEvent;
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
		{0,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,1,0,1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,0},
		{0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1,0,1,0},
		{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};	
		
	public Map() {
		super();
		initMap();
		
		
		B1 =   new Bomber("Alexis", 1, 1, this);
		B2 =   new Bomber("David", 15, 19, this);
		
		
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
		int longueur =50; 
		int largeur = 50;
		StdDraw.enableDoubleBuffering();
		//modification de la taille de la map 
		
		StdDraw.setCanvasSize(terrain[0].length*longueur,terrain.length*largeur);
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
				
				
				/*StdDraw.setPenColor(46, 95, 61);
				StdDraw.filledSquare(x,y,0.505);
				StdDraw.setPenColor(StdDraw.GRAY);
				StdDraw.filledSquare(x, y, 0.40);
				break;*/
			
			case C_VIDE: // vide
				StdDraw.picture(x, y, "img/Bricks/floor.png");
				
				/*StdDraw.setPenColor(46, 95, 61);
				StdDraw.filledSquare(x,y,0.505);
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledSquare(x, y, 0.40);*/
				break;
			case C_MUR_DESTRUCTIBLE: // mur destructible
				StdDraw.picture(x, y, "img/Bricks/brick.png");
				
				
				/*StdDraw.setPenColor(46, 95, 61);
				StdDraw.filledSquare(x,y,0.505);
				StdDraw.setPenColor(StdDraw.ORANGE);
				StdDraw.filledSquare(x, y, 0.40);*/
				break;
			case C_BOMBERMAN: // Bomber
				StdDraw.picture(x, y, "img/BomberMan/character1.png");
				break;
			case C_BOMBE: //Bombe
				StdDraw.picture(x, y, "img/Bombs/bomb.png");
				/*StdDraw.setPenColor(46, 95, 61);
				StdDraw.filledCircle(x,y,0.505);
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.filledCircle(x, y, 0.40);*/
				break;
			case C_FEU: //explosion
				StdDraw.picture(x, y, "img/Fire/Flamme.png");
				
				/*StdDraw.setPenColor(46, 95, 61);
				StdDraw.filledCircle(x,y,0.505);
				StdDraw.setPenColor(StdDraw.ORANGE);
				StdDraw.filledCircle(x, y, 0.40);*/
				break;
			case C_JOUEUR_BOMBE://Bomberman + Bombe
				StdDraw.setPenColor(46, 95, 61);
				StdDraw.filledCircle(x,y,0.505);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledCircle(x, y, 0.40);
				break;
			case CB_FLAMME_BLEUE: 
				StdDraw.picture(x, y, "img/Bonus/Flamme_bleu.png");
				
				/*StdDraw.setPenColor(46, 95, 61);
				StdDraw.filledSquare(x,y,0.505);
				StdDraw.setPenColor(StdDraw.YELLOW);
				StdDraw.filledSquare(x, y, 0.40);*/
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
		if (x >= terrain[0].length || x < 0) {
			System.out.println("Coordonnée X incorrecte");
		} else if (y >= terrain.length || y < 0 ) {
			System.out.println("Coordonnée Y incorrecte");
		} else {
			//System.out.println("lecture terrain(" + y + "," + x + ")=" + terrain[y][x] );
			return terrain[y][x];
		}
		return -1; // Code de l'erreur
	}
	
	public int ecrireTerrain(int y, int x, int val) {
		if (x >= terrain[0].length || x < 0) {
			System.out.println("Coordonnée X incorrecte");
		} else if (y >= terrain.length || y < 0 ) {
			System.out.println("Coordonnée Y incorrecte");
		} else {
			//System.out.println("ecriture terrain(" + y + "," + x + ")=" + val );
			terrain[y][x] = val;
			return 0;
		}
		return -1; // Code de l'erreur
	}
	
	
	

	public void bonus(int y,int x) {
        //Génère un nombre aléatoire entre 0 et 5
        int r = 0 + (int) (Math.random() * (( 5	- 0) + 1));
        // Donc on a 20 % de chance d'avoir les bonus
        if(r <1){ 
            //Generate Number 1,2,3,4
            int p = (int) (Math.random() * ((4 - 1) + 1));
            switch(p){
                case 1:
                    // Flame Bleu
                	
                	ecrireTerrain(y,x, Map.CB_FLAMME_BLEUE); 
                	majCase(y,x);
                	System.out.println("Flamme bleu");
                	
                    break;
                case 2:
                  // Flame Jaune
                	
                	ecrireTerrain(y,x, Map.CB_FLAMME_JAUNE);
                	majCase(y,x);
                	System.out.println("Flamme Jaune");
                    break;
                case 3:
                	// Flamme Rouge
                	
                	ecrireTerrain(y,x, Map.CB_FLAMME_ROUGE); 
                	majCase(y,x);
                	System.out.println("Flamme Rouge");
                	break;
                case 4: 
                	// Bombe rouge
                	
                	ecrireTerrain(y,x, Map.CB_BOMBE_ROUGE); 
                	majCase(y,x);
                	System.out.println("Bombe Rouge");
                	break;
                	
       
            }
        }
        
    }
	
	
		
}
	
	

	

