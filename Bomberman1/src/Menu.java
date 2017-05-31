


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;





public class Menu  {

    JFrame frame;
    


    public Menu() {
    	
    	
        frame = new JFrame("BomberMan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
       

        JPanel panel = new JPanel();
        
        
        JPanel Frame = (JPanel) frame.getGlassPane();
        
        
        
        Frame.setVisible(true);
        panel.add(new JLabel(new ImageIcon("img/Menu/title.png")));
// Bouton 1
        JButton Bouton1 = new JButton("Jouer");
        Bouton1.setLayout(null);
        Bouton1.setBounds(640,360,300,200);
        Frame.add(Bouton1);
// Bouton 2 
       
        JButton Bouton2 = new JButton("Quitter");
        Bouton2.setLayout(null);
        Bouton2.setBounds(640,300,300,200);
        Frame.add(Bouton2);
 
        
        
        //Choix de l'utilisateur
        
        
        

        Bouton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting Game");
            	 Map Jouer = new Map();
                frame.dispose(); 
                
              
            }
        }
        
        );

 

        Bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                frame.dispose(); // Ferme la fenêtre
                System.out.println("Quitter");
            }
        });


        frame.add(panel);

        frame.setVisible(true);
    }


	
        }
 

