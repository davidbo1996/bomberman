import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import edu.princeton.cs.introcs.StdDraw;



public class Main {
	

	
	public static void main(String[] args){
		 /** Menu du bomberman qui ne fonctionne pas correctement**/
		Menu menu = new Menu();
		

		Map mp = new Map();
	
		while ( (mp.B1.getNombreVie()!=0) && (mp.B2.getNombreVie()!=0) ) // Tant que le joueur est en vie 
		{	
			// DEPLACEMENTS JOUEUR 1				
			if (StdDraw.isKeyPressed(68))
			{
				mp.B1.goRight();
			}
			else if (StdDraw.isKeyPressed(83)) {
				mp.B1.goDown();
			}
			else if (StdDraw.isKeyPressed(81)) {
				mp.B1.goLeft(); 
			}
			else if (StdDraw.isKeyPressed(90)) {
				mp.B1.goUp();  
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_F))
			{
				mp.B1.poserBombe();
			}
		
			// DEPLACEMENTS JOUEUR 2 				 
			if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
			{
				mp.B2.goRight();
			}
			else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
				mp.B2.goDown();
			}
			else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
				mp.B2.goLeft(); 
			}
			else if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
				mp.B2.goUp();  
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_0))
			{
				mp.B2.poserBombe();
			}
			
			StdDraw.pause(100);
			StdDraw.show();	
			
			
				
			}
		/*if  ( (mp.B1.getNombreVie()==-1) || (mp.B2.getNombreVie()!=0) ) {
			
			StdDraw.picture(525, 500, "img/Victoire/VictoireJ1.png");
			
			
		}
		else if  ( (mp.B1.getNombreVie()!=0) && (mp.B2.getNombreVie()==-1)){
			StdDraw.picture(525, 500, "img/Victoire/VictoireJ1.png");
		}*/
		
		}
		
	}
	

