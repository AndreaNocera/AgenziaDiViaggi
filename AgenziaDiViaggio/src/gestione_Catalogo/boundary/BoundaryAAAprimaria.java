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
 * Questa Boundary � primaria perch� definisce Il Frame
 * 
 */



public class BoundaryAAAprimaria extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	
	//attributi istanza
	
	public static JFrame confinePrincipale; //� statico:il frame � unico per tutta l'applicazione
	
	public static JPanel superPanel;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

	private JLabel labelTitolo;
	private JLabel labelProprietari;
	private JLabel labelLogin;

	private JButton bottoneGestioneCatalogo;

	private AAGestioneCatalogo ascoltatoreBottoneGestioneCatalogo;

	
	
	
	
	
	
	//costruttore
	public BoundaryAAAprimaria(){
		
		
		
		//crea la Finestra
		confinePrincipale = new JFrame();
		
		confinePrincipale.setTitle("Voyager - Agenzia Viaggi");
		
		
		Dimension dim = getToolkit().getScreenSize();
		confinePrincipale.setSize(dim.width/3*2,dim.height/4*3);
		confinePrincipale.setLocation(dim.width/6 , dim.height/8);
		confinePrincipale.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		confinePrincipale.setVisible(true);
		confinePrincipale.setResizable(false);
		
		
		
		//definisco un Panel principale che contiene 4pannelli
		
		superPanel = new JPanel();
		superPanel.setSize(confinePrincipale.getWidth(), confinePrincipale.getHeight());
		superPanel.setLayout(null); 		//ora il pannello pu� contenere altri pannelli
		confinePrincipale.add(superPanel); 	//aggiungo il pannello al confine
		
		
		//definisco il primo pannello: per il titolo
		panel1 = new JPanel();
		panel1.setSize(superPanel.getWidth(), superPanel.getHeight()/4);
		panel1.setLocation(0, 0);			//x=0 e y=0 rispetto al superPanel
		panel1.setLayout(null); 			//ora il pannello pu� contenere oggetti
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
		panel2.setLayout(null); 			//ora il pannello pu� contenere oggetti
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
		panel3.setLayout(null); 			//ora il pannello pu� contenere oggetti
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
		panel4.setLayout(null); 			//ora il pannello pu� contenere oggetti
		superPanel.add(panel4);				//aggiungo il quarto pannello al superPannello
		
		
		
		bottoneGestioneCatalogo = new JButton("GESTIONE CATALOGO");
		bottoneGestioneCatalogo.setBounds(panel4.getWidth()/5*2, 0, panel4.getWidth()/5, panel4.getHeight()/3);
		panel4.add(bottoneGestioneCatalogo);//aggiungo il bottone al quarto pannello
		
		ascoltatoreBottoneGestioneCatalogo = new AAGestioneCatalogo();
		bottoneGestioneCatalogo.addActionListener(ascoltatoreBottoneGestioneCatalogo);
	}
	
	
	
	private class AAGestioneCatalogo implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {


			superPanel.setVisible(false); 					//nasconde questo superpannello
			new BoundaryPromotore_GestioneCatalogo();		//crea istanza della classe del prossimo superPannello


		}
		
			
		
	}
	

}



