/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.boundary;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/* 
 * Questa Boundary e' primaria perche' definisce Il Frame
 * 
 */



public class BoundaryAAAprimaria extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	
	//attributi istanza
	
	public static JFrame confinePrincipale; //E' statico:il frame e' unico per tutta l'applicazione
	
	public static JPanel superPanel;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

	private JLabel labelTitolo;
	private JLabel labelProprietari;
	private JLabel labelLogin;

	private JButton bottoneProgettista;
	private JButton bottonePromotore;
	private JButton bottoneVenditore;

	private BottoneProgettistaAA ascoltatoreBottoneProgettista;
	private BottonePromotoreAA ascoltatoreBottonePromotore;
	private BottoneVenditoreAA ascoltatoreBottoneVenditore;

	
	
	
	
	
	
	//costruttore
	public BoundaryAAAprimaria(){
		
		
		
		//crea la Finestra
		confinePrincipale = new JFrame();
		
		confinePrincipale.setTitle("Voyager - Agenzia Viaggi");
		
		
		Dimension dim = getToolkit().getScreenSize();
		confinePrincipale.setSize(dim.width/4*3,dim.height/6*5);
		confinePrincipale.setLocation(dim.width/8 , dim.height/12);
		confinePrincipale.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		confinePrincipale.setVisible(true);
		confinePrincipale.setResizable(false);
		
		
		
		//definisco un Panel principale che contiene 4pannelli
		
		superPanel = new JPanel();
		superPanel.setSize(confinePrincipale.getWidth(), confinePrincipale.getHeight());
		superPanel.setLayout(null); 		//ora il pannello puo' contenere altri pannelli
		confinePrincipale.add(superPanel); 	//aggiungo il pannello al confine
		
		
		//definisco il primo pannello: per il titolo
		panel1 = new JPanel();
		panel1.setSize(superPanel.getWidth(), superPanel.getHeight()/4);
		panel1.setLocation(0, 0);			//x=0 e y=0 rispetto al superPanel
		panel1.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel1);				//aggiungo il primo pannello al superPannello
		
		labelTitolo = new JLabel();  		//Etichetta per il titolo
		labelTitolo.setFont(new Font("Arial", 0, 50));
		labelTitolo.setLocation(0, 0);
		labelTitolo.setSize(panel1.getWidth(), panel1.getHeight());
		labelTitolo.setHorizontalAlignment(JLabel.CENTER);
		labelTitolo.setVerticalAlignment(JLabel.CENTER);
		labelTitolo.setText("VOYAGER");
		panel1.add(labelTitolo);			//aggiungo titolo al pannello1
		
		
		//definisco il secondo pannello: per i proprietari
		panel2 = new JPanel();
		panel2.setSize(superPanel.getWidth(), superPanel.getHeight()/4);
		panel2.setLocation(0, superPanel.getHeight()/4);
		panel2.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel2);				//aggiungo il secondo pannello al superPannello
		
		labelProprietari = new JLabel();	//Etichetta dei proprietari
		labelProprietari.setFont(new Font("Arial", 0, 20));
		labelProprietari.setLocation(0, 0);
		labelProprietari.setSize(panel2.getWidth(), 20);
		labelProprietari.setHorizontalAlignment(JLabel.CENTER);
		labelProprietari.setVerticalAlignment(JLabel.CENTER);
		labelProprietari.setText("Caso d'uso GestioneCatalogo");
		panel2.add(labelProprietari);		//aggiungo proprietari al pannello2
		
		
		
		//definisco il terzo pannello: per il login
		panel3 = new JPanel();
		panel3.setSize(superPanel.getWidth(), superPanel.getHeight()/4);
		panel3.setLocation(0, superPanel.getHeight()/4*2);
		panel3.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel3);				//aggiungo il terzo pannello al superPannello
		
		
		labelLogin = new JLabel();			//Sostituisce momentaneamente il login
		labelLogin.setFont(new Font("Arial", 0, 15));
		labelLogin.setLocation(0,0);
		labelLogin.setSize(panel3.getWidth(), panel3.getHeight());
		labelLogin.setHorizontalAlignment(JLabel.CENTER);
		labelLogin.setVerticalAlignment(JLabel.CENTER);
		labelLogin.setText("Login momentaneamente non disponibile");
		panel3.add(labelLogin);				//aggiungo l'etichetta del Login
		
		
		
		//definisco il quarto pannello: per il bottone
		panel4 = new JPanel();
		panel4.setSize(superPanel.getWidth(), superPanel.getHeight()/4);
		panel4.setLocation(0, superPanel.getHeight()/4*3);
		panel4.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel4);				//aggiungo il quarto pannello al superPannello
		
		
		bottoneVenditore = new JButton("VENDITORE");
		bottoneVenditore.setBounds(panel4.getWidth()/12*3, panel4.getHeight()/3, panel4.getWidth()/6, panel4.getHeight()/6);
		panel4.add(bottoneVenditore);//aggiungo il bottone al quarto pannello
		
		ascoltatoreBottoneVenditore = new BottoneVenditoreAA();
		bottoneVenditore.addActionListener(ascoltatoreBottoneVenditore);
		
		
		
		bottoneProgettista = new JButton("PROGETTISTA");
		bottoneProgettista.setBounds(panel4.getWidth()/12*5, panel4.getHeight()/3, panel4.getWidth()/6, panel4.getHeight()/6);
		panel4.add(bottoneProgettista);
		
		ascoltatoreBottoneProgettista = new BottoneProgettistaAA();
		bottoneProgettista.addActionListener(ascoltatoreBottoneProgettista);
		
		
		
		
		bottonePromotore = new JButton("PROMOTORE");
		bottonePromotore.setBounds(panel4.getWidth()/12*7, panel4.getHeight()/3, panel4.getWidth()/6, panel4.getHeight()/6);
		panel4.add(bottonePromotore);//aggiungo il bottone al quarto pannello
		
		ascoltatoreBottonePromotore = new BottonePromotoreAA();
		bottonePromotore.addActionListener(ascoltatoreBottonePromotore);
		
		

		
		
	}
	
	
	
	private class BottonePromotoreAA implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {


			superPanel.setVisible(false); 					//nasconde questo superpannello
			new BoundaryPromotore();		//crea istanza della classe del prossimo superPannello


		}
	}
	
	private class BottoneProgettistaAA implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {


			superPanel.setVisible(false); 					//nasconde questo superpannello
			new BoundaryProgettista();		//crea istanza della classe del prossimo superPannello


		}
	}
	
	
	private class BottoneVenditoreAA implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {


			superPanel.setVisible(false); 					//nasconde questo superpannello
			new BoundaryVenditore();		//crea istanza della classe del prossimo superPannello


		}
	}
	

}




