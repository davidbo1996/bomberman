import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Main {
	

	
	public static void main(String[] args){

		Map mp = new Map();
		
		while ( (mp.B1.getNombreVie()!=0) && (mp.B2.getNombreVie()!=0) ) // Tant que le joueur est en vie 
		{	
			/*System.out.println("B1 NVIE = "+ mp.B1.getNombreVie());
			System.out.println("B1 NBombe = "+ mp.B1.getNombreBombe());*/
			mp.B1.killJoueur(); // gérer l'impact des bombes posées par l'autre joueur
			// DEPLACEMENTS JOUEUR 1				
			if (StdDraw.isKeyPressed(68))
			{
				mp.B1.goRight();
				StdDraw.pause(mp.B1.getSpeed());
			}
			else if (StdDraw.isKeyPressed(83)) {
				mp.B1.goDown();
				StdDraw.pause(mp.B1.getSpeed());
			}
			else if (StdDraw.isKeyPressed(81)) {
				mp.B1.goLeft(); 
				StdDraw.pause(mp.B1.getSpeed());
			}
			else if (StdDraw.isKeyPressed(90)) {
				mp.B1.goUp();  
				StdDraw.pause(mp.B1.getSpeed());
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_F))
			{
				mp.B1.poserBombe();
				StdDraw.pause(mp.B1.getSpeed());
			}
			//mp.majScoreJ1(Integer.toString(mp.B1.score) ,Integer.toString( mp.B1.getNombreVie()));

		
			// DEPLACEMENTS JOUEUR 2 
			mp.B2.killJoueur(); // gérer l'impact des bombes posées par l'autre joueur

			if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
			{
				mp.B2.goRight();
				StdDraw.pause(mp.B2.getSpeed());
			}
			else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
				mp.B2.goDown();
				StdDraw.pause(mp.B2.getSpeed());
			}
			else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
				mp.B2.goLeft();
				StdDraw.pause(mp.B2.getSpeed());
			}
			else if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
				mp.B2.goUp();  
				StdDraw.pause(mp.B2.getSpeed());
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_0))
			{
				mp.B2.poserBombe();
				StdDraw.pause(mp.B2.getSpeed());
			}
			//mp.majScoreJ2(Integer.toString(mp.B2.score) ,Integer.toString( mp.B2.getNombreVie()));
			StdDraw.show();	
		}
		
		
	}
}
