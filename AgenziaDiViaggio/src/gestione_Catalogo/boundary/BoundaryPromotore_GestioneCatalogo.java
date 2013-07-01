/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.boundary;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gestione_Catalogo.thread.PromotoreThread;

import gestione_Catalogo.control.ControlloreGestioneCatalogo;
import gestione_Catalogo.exception.DeserializzazioneException;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaException;
import gestione_Catalogo.exception.SerializzazioneException;
import gestione_Catalogo.exception.TrattaException;






public class BoundaryPromotore_GestioneCatalogo {
	
	/*
	 * Attributi di istanza
	 */
	
	//Entita'
	private ControlloreGestioneCatalogo controllore;
	private String ambienteScelto;
	private String mezzoScelto;
	private String partenzaScelta;
	private String arrivoScelto;
	private String viaScelta;
	private String areaTestoCatalogo;
	private String areaTestoImp;

	//Pannelli
	private JPanel superPanel;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;

	
	//Elementi pannello1
	private JLabel labelTitolo;
	
	private JButton bottoneIndietro;
	private JButton bottoneEsci;
	
	private IndietroAA ascoltatoreBottoneIndietro;
	private EsciAA ascoltatoreBottoneEsci;
	
	
	//Elementi pannello2
	
	private JButton bottoneGestioneCatalogoPannello2;
	
    private GestioneCatalogoAA ascoltatoreGestioneCatalogo;
	
	//Elementi Pannello3
	private JButton bottoneAggiungiViaggio;
	private JButton bottoneRimuoviViaggio;
	private JButton bottoneChiudiPannello3;
    
    private AggiungiViaggioAA ascoltatoreBottoneAggiungiViaggio;
    private RimuoviViaggioAA ascoltatoreBottoneRimuoviViaggio;
    private ChiudiPannello3AA ascoltatoreBottoneChiudiPannello3;

    
    //Elementi Pannello4
    private JLabel	labelTitoloPannello4;
    
	private JRadioButton radioMarePannello4;
	private JRadioButton radioTerraPannello4;
	private JRadioButton radioAriaPannello4;

	private ButtonGroup gruppoAmbientePannello4;

	private JLabel labelMezziPannello4;
	private JComboBox<String> tendinaMezziPannello4;
	private JTextField campoMezziPannello4;

	private JLabel labelCittaPartenzaPannello4;
	private JComboBox<String> tendinaCittaPartenzaPannello4;
	private JTextField campoCittaPartenzaPannello4;

	private JLabel labelCittaArrivoPannello4;
	private JComboBox<String> tendinaCittaeArrivoPannello4;
	private JTextField campoCittaArrivoPannello4;
	
	private JLabel labelViaPannello4;
	private JTextField campoViaPannello4;
	
	private JLabel labelInfoPannello4;
	private JTextField campoInfoPannello4;

	private JButton bottoneAggiungi;
	private JButton bottoneSvuotaPannello4;
	
	private JButton bottoneChiudiPannello4;

	private SelezionaViaAriaPannello4AA ascoltatoreRadioButtonAriaPannello4;
	private SelezionaViaMarePannello4AA ascoltatoreRadioButtonMarePannello4;
	private SelezionaViaTerraPannello4AA ascoltatoreRadioButtonTerraPannello4;
	private ChiudiPannello4AA ascoltatoreBottoneChiudiPannello4;
	private AggiungiAA ascoltatoreBottoneAggiungi;
	private TendinaMezziPannello4AA ascoltatoreTendinaMezziPannello4;
	private TendinaCittaPartenzaPannello4AA ascoltatoreTendinaCittaPartenzaPannello4;
	private TendinaCittaArrivoPannello4AA ascoltatoreTendinaCittaArrivoPannello4;
	private SvuotaPannello4AA ascoltatoreBottoneSvuotaPannello4;

	
	//Elementi Pannello5
	
	private JLabel	labelTitoloPannello5;

	private JLabel labelAmbientePannello5;
	private JComboBox<String> tendinaAmbientePannello5;

	private JTextArea areaTestoPannello5;
	private JScrollPane scrollAreaTestoPannello5;
	
	private JLabel labelMezziPannello5;
	private JComboBox<String> tendinaMezziPannello5;

	private JLabel labelCittaPartenzaPannello5;
	private JComboBox<String> tendinaCittaPartenzaPannello5;

	private JLabel labelCittaArrivoPannello5;
	private JComboBox<String> tendinaCittaArrivoPannello5;
	
	private JLabel labelViaPannello5;
	private JComboBox<String> tendinaViaPannello5;

	private JButton bottoneRimuovi;
	private JButton bottoneSvuotaPannello5;
	
	private JButton bottoneChiudiPannello5;
	
	private TendinaAmbientePannello5AA ascoltatoreTendinaAmbientePannello5;
	private TendinaMezziPannello5AA ascoltatoreTendinaMezziPannello5;
	private TendinaPartenzePannello5AA ascoltatoreTendinaPartenzePannello5;
	private TendinaArriviPannello5AA ascoltatoreTendinaArriviPannello5;
	private TendinaIntermediViaPannello5AA ascoltatoreTendinaViaPannello5;
	private ChiudiPannello5AA ascoltatoreBottoneChiudiPannello5;
	private RimuoviAA ascoltatoreBottoneRimuovi;
	private SvuotaPannello5AA ascoltatoreBottoneSvuotaPannello5;

	


		
	/*
	 * Costruttore
	 */
	
	public BoundaryPromotore_GestioneCatalogo(){
		
		ambienteScelto = null;
		mezzoScelto = null;
		partenzaScelta = null;
		arrivoScelto = null;
		viaScelta = null;
		areaTestoCatalogo = null;
		controllore = new ControlloreGestioneCatalogo();
		
		areaTestoImp = "AMBIENTE" + "\t" + "MEZZO" + "\t\t" + "TRATTA" + "\t\t\t"  + "INFO\n" +
		   "--------------" + "\t" + "----------" + "\t\t" + "------------" + "\t\t\t" + "---------" + "\n";
		
		/*
		 * definisco il SuperPannello
		 */
		superPanel = new JPanel();
		superPanel.setSize(BoundaryAAAprimaria.confinePrincipale.getWidth(), BoundaryAAAprimaria.confinePrincipale.getHeight());
		superPanel.setBackground(Color.BLACK); 					//provo a mettere il sottopannellonero...si vedono i contorni?
		superPanel.setLayout(null); 							//ora il pannello puo' contenere altri pannelli
		BoundaryAAAprimaria.confinePrincipale.add(superPanel); 	//aggiungo il pannello al confine (chiamato staticamente)
	//	BoundaryAAAprimaria.confinePrincipale.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		
		
		
		
		/*
		 * primo pannello: per il titolo
		 */
		
		panel1 = new JPanel();
		panel1.setSize(superPanel.getWidth(), superPanel.getHeight()/10-10);  //Il meno 10 serve per far vedere il contorno 
		panel1.setLocation(0, 0);			//x=0 e y=0 rispetto al superPanel
		panel1.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel1);				//aggiungo il primo pannello al superPannello
		
		labelTitolo = new JLabel();  		//Etichetta per il titolo
		labelTitolo.setFont(new Font("Arial", 0, 30));
		labelTitolo.setLocation(0, 0);
		labelTitolo.setSize(panel1.getWidth(), panel1.getHeight());
		labelTitolo.setHorizontalAlignment(JLabel.CENTER);
		labelTitolo.setVerticalAlignment(JLabel.CENTER);
		labelTitolo.setText("GESTIONE CATALOGO");
		panel1.add(labelTitolo);			//aggiungo titolo al pannello1
		
		
		bottoneIndietro = new JButton("INDIETRO");
		bottoneIndietro.setBackground(Color.ORANGE);
		bottoneIndietro.setBounds(panel1.getWidth()/18, panel1.getHeight()/4, panel1.getWidth()/9, panel1.getHeight()/2);
		panel1.add(bottoneIndietro);//aggiungo il bottone al primo pannello
		
		bottoneEsci = new JButton("ESCI");
		bottoneEsci.setBackground(Color.RED);
		bottoneEsci.setBounds(panel1.getWidth()/18*15, panel1.getHeight()/4, panel1.getWidth()/9, panel1.getHeight()/2);
		panel1.add(bottoneEsci);//aggiungo il bottone al primo pannello
		
		//Ascoltatori per primo pannello
		
		ascoltatoreBottoneIndietro = new IndietroAA();
		bottoneIndietro.addActionListener(ascoltatoreBottoneIndietro);
		
		ascoltatoreBottoneEsci = new EsciAA();
		bottoneEsci.addActionListener(ascoltatoreBottoneEsci);
		

		/*
		 * 
		 * Secondo pannello: per i bottoni relativi alle funzioni dell'utente loggato
		 * 
		 * 
		 */
		
		panel2 = new JPanel();
		panel2.setSize(superPanel.getWidth(), superPanel.getHeight()/10-10);
		panel2.setLocation(0, superPanel.getHeight()/10);
		panel2.setLayout(null);
		superPanel.add(panel2);
		
		
		bottoneGestioneCatalogoPannello2 = new JButton("GESTIONE CATALOGO");
		bottoneGestioneCatalogoPannello2.setBackground(Color.ORANGE);
		bottoneGestioneCatalogoPannello2.setBounds(panel2.getWidth()/5*2, panel2.getHeight()/4, panel2.getWidth()/5, panel2.getHeight()/2);
		panel2.add(bottoneGestioneCatalogoPannello2);
		
		
		//Ascoltatori per secondo pannello
		ascoltatoreGestioneCatalogo = new GestioneCatalogoAA();
		bottoneGestioneCatalogoPannello2.addActionListener(ascoltatoreGestioneCatalogo);
		
		
		
		/*
		 * terzo pannello: per i bottoni della gestione del catalogo, si apre quando viene premuto GESTIONE CATALOGO del secondo pannello
		 */

		panel3 = new JPanel();
		panel3.setSize(superPanel.getWidth(), superPanel.getHeight()/10-10); //Il meno 10 serve a far vedere il contorno
		panel3.setLocation(0, superPanel.getHeight()/10*2);			//x=0 e y=0 rispetto al superPanel
		panel3.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel3);				//aggiungo il primo pannello al superPannello
		panel3.setVisible(false);
		
		bottoneAggiungiViaggio = new JButton("AGGIUNGI VIAGGIO");
		bottoneAggiungiViaggio.setBackground(Color.CYAN);
		bottoneAggiungiViaggio.setBounds(panel3.getWidth()/5, panel3.getHeight()/6, panel3.getWidth()/5, panel3.getHeight()/2);
		panel3.add(bottoneAggiungiViaggio);//aggiungo il bottone al secondo pannello
		
		
		bottoneRimuoviViaggio = new JButton("RIMUOVI VIAGGIO");
		bottoneRimuoviViaggio.setBackground(Color.YELLOW);
		bottoneRimuoviViaggio.setBounds(panel3.getWidth()/5*3, panel3.getHeight()/6, panel3.getWidth()/5, panel3.getHeight()/2);
		panel3.add(bottoneRimuoviViaggio);//aggiungo il bottone al secondo pannello
		
		
		bottoneChiudiPannello3 = new JButton("X");
		bottoneChiudiPannello3.setBackground(Color.RED);
		bottoneChiudiPannello3.setBounds(panel3.getWidth()/20*19-10, 0, panel3.getWidth()/20, panel3.getHeight()/2-3);
		panel3.add(bottoneChiudiPannello3);
		
		
		
		// ascoltatori per terzo pannello
		ascoltatoreBottoneAggiungiViaggio = new AggiungiViaggioAA(); 		//creo ascoltatore per bottone
		bottoneAggiungiViaggio.addActionListener(ascoltatoreBottoneAggiungiViaggio); 	//associo ascoltatore al bottone
		
		ascoltatoreBottoneRimuoviViaggio = new RimuoviViaggioAA();			//creo ascoltatore per bottone
		bottoneRimuoviViaggio.addActionListener(ascoltatoreBottoneRimuoviViaggio);		//associo ascoltatore al bottone
		
		ascoltatoreBottoneChiudiPannello3 = new ChiudiPannello3AA();
		bottoneChiudiPannello3.addActionListener(ascoltatoreBottoneChiudiPannello3);
		
		/*
		 * quarto pannello: questo pannello si attiva premendo il bottone AGGIUNGI VIAGGIO del terzo pannello
		 */
		
		
		panel4 = new JPanel();
		panel4.setSize(superPanel.getWidth(), superPanel.getHeight()/10*7);
		panel4.setLocation(0, superPanel.getHeight()/10*3);	
		panel4.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel4);
		panel4.setVisible(false); 			//deve essere invisibile all'inizio, attivato solo dal bottone
		
		labelTitoloPannello4 = new JLabel();	
		labelTitoloPannello4.setFont(new Font("Arial", 0, 20));
		labelTitoloPannello4.setBounds(panel4.getWidth()/3, panel4.getHeight()/49, panel4.getWidth()/3, panel4.getHeight()/7);
		labelTitoloPannello4.setVerticalAlignment(JLabel.CENTER);
		labelTitoloPannello4.setHorizontalAlignment(JLabel.CENTER);
		labelTitoloPannello4.setText("AGGIUNGI VIAGGIO");
		panel4.add(labelTitoloPannello4);
		
		
		radioAriaPannello4 = new JRadioButton("Via Aria");
		radioAriaPannello4.setBounds(panel4.getWidth()/7+100, panel4.getHeight()/6, panel4.getWidth()/7, panel4.getHeight()/14);
		panel4.add(radioAriaPannello4);   //aggiungo il radiobutton al pannello 3
		
		radioMarePannello4 = new JRadioButton("Via Mare");
		radioMarePannello4.setBounds(panel4.getWidth()/7*3, panel4.getHeight()/6, panel4.getWidth()/7, panel4.getHeight()/14);
		panel4.add(radioMarePannello4);
		
		radioTerraPannello4 = new JRadioButton("Via Terra");
		radioTerraPannello4.setBounds(panel4.getWidth()/7*5-100, panel4.getHeight()/6, panel4.getWidth()/7, panel4.getHeight()/14);
		panel4.add(radioTerraPannello4);
		
		gruppoAmbientePannello4 = new ButtonGroup();
		gruppoAmbientePannello4.add(radioAriaPannello4);
		gruppoAmbientePannello4.add(radioMarePannello4);
		gruppoAmbientePannello4.add(radioTerraPannello4);   //Ora solo uno di questi 3 e' selezionabile
		
		
		
		labelMezziPannello4 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello4.setFont(new Font("Arial", 0, 15));
		labelMezziPannello4.setBounds(panel4.getWidth()/7-10, panel4.getHeight()/6*2, panel4.getWidth()/6, 20);
		labelMezziPannello4.setText("Mezzo di Trasporto*");
		panel4.add(labelMezziPannello4);
		
		
		tendinaMezziPannello4 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello4.setBackground(Color.WHITE);
		tendinaMezziPannello4.setBounds(panel4.getWidth()/7-10, panel4.getHeight()/6*2+20, panel4.getWidth()/6, 20);
		tendinaMezziPannello4.setEnabled(false);
		panel4.add(tendinaMezziPannello4);
		
		campoMezziPannello4 = new JTextField(panel4.getWidth()/7);	  //campo per aggiungere dei nuovi mezzi
		campoMezziPannello4.setFont(new Font("Arial", 0, 18));
		campoMezziPannello4.setBounds(panel4.getWidth()/7-10, panel4.getHeight()/6*2+45, panel4.getWidth()/6, 20);
		campoMezziPannello4.setEditable(false);   				// all'inizio e' disattivato, si attiva solo con new...
		panel4.add(campoMezziPannello4);
		
		
		labelCittaPartenzaPannello4 = new JLabel();        //Etichetta per Stazione di partenza
		labelCittaPartenzaPannello4.setFont(new Font("Arial", 0, 15));
		labelCittaPartenzaPannello4.setBounds(panel4.getWidth()/7*3-10, panel4.getHeight()/6*2, panel4.getWidth()/6, 20);
		labelCittaPartenzaPannello4.setText("Citta' di Partenza*");
		panel4.add(labelCittaPartenzaPannello4);
		
		
		tendinaCittaPartenzaPannello4 = new JComboBox<String>();	  //Tendina per le partenze
		tendinaCittaPartenzaPannello4.setBackground(Color.WHITE);
		tendinaCittaPartenzaPannello4.setEnabled(false);
		tendinaCittaPartenzaPannello4.setBounds(panel4.getWidth()/7*3-10, panel4.getHeight()/6*2+20, panel4.getWidth()/6, 20);
		panel4.add(tendinaCittaPartenzaPannello4);
		
		
		campoCittaPartenzaPannello4 = new JTextField (panel4.getWidth()/7);	 //Campo per stazione di partenza
		campoCittaPartenzaPannello4.setFont(new Font("Arial",0,18));
		campoCittaPartenzaPannello4.setBounds(panel4.getWidth()/7*3-10, panel4.getHeight()/6*2+45, panel4.getWidth()/6, 20);
		campoCittaPartenzaPannello4.setEditable(false);
		panel4.add(campoCittaPartenzaPannello4);
		
		
		labelCittaArrivoPannello4 = new JLabel();        //Etichetta per Stazione di arrivo
		labelCittaArrivoPannello4.setFont(new Font("Arial", 0, 15));
		labelCittaArrivoPannello4.setBounds(panel4.getWidth()/7*5-10, panel4.getHeight()/6*2, panel4.getWidth()/6, 20);
		labelCittaArrivoPannello4.setText("Citta' di Arrivo*");
		panel4.add(labelCittaArrivoPannello4);
		
		tendinaCittaeArrivoPannello4 = new JComboBox<String>();     // Tendina per gli arrivi
		tendinaCittaeArrivoPannello4.setBackground(Color.WHITE);
		tendinaCittaeArrivoPannello4.setEnabled(false);
		tendinaCittaeArrivoPannello4.setBounds(panel4.getWidth()/7*5-10, panel4.getHeight()/6*2+20, panel4.getWidth()/6, 20);
		panel4.add(tendinaCittaeArrivoPannello4);
		
		
		campoCittaArrivoPannello4 = new JTextField (panel4.getWidth()/7);	 //Campo per stazione di arrivo
		campoCittaArrivoPannello4.setFont(new Font("Arial",0,18));
		campoCittaArrivoPannello4.setBounds(panel4.getWidth()/7*5-10, panel4.getHeight()/6*2+45, panel4.getWidth()/6, 20);
		campoCittaArrivoPannello4.setEditable(false);
		panel4.add(campoCittaArrivoPannello4);
		
		
		
		labelViaPannello4 = new JLabel();	//Etichetta per Stazioni Intermedie
		labelViaPannello4.setFont(new Font("Arial",0,15));
		labelViaPannello4.setBounds(panel4.getWidth()/7-10, panel4.getHeight()/6*3, panel4.getWidth()/6, 20);
		labelViaPannello4.setText("Via");
		panel4.add(labelViaPannello4);
		
		
		
	    campoViaPannello4 = new JTextField (panel4.getWidth()/6); //Campo per stazioni intermedie
	    campoViaPannello4.setFont(new Font("Arial", 0, 18));
	    campoViaPannello4.setBounds(panel4.getWidth()/7-10, panel4.getHeight()/6*3+20, panel4.getWidth()/6, 20);
	    campoViaPannello4.setEditable(false);
	    panel4.add(campoViaPannello4);
	    
	    
	    
	    labelInfoPannello4 = new JLabel();			//Etichetta per le info
	    labelInfoPannello4.setFont(new Font("Arial", 0, 15));
	    labelInfoPannello4.setBounds(panel4.getWidth()/7*3-10, panel4.getHeight()/6*3, panel4.getWidth()/6, 20);
	    labelInfoPannello4.setText("Info");
	    panel4.add(labelInfoPannello4);
	    
	    
	    
	    campoInfoPannello4 = new JTextField(panel4.getWidth()/7*3);			//Campo per le info
	    campoInfoPannello4.setFont(new Font("Arial", 0, 18));
	    campoInfoPannello4.setBounds(panel4.getWidth()/7*3-10, panel4.getHeight()/6*3+20, panel4.getWidth()/7*3, 20);
	    campoInfoPannello4.setEditable(false);
	    panel4.add(campoInfoPannello4);
	    
	    
		
		bottoneSvuotaPannello4 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello4.setBackground(Color.YELLOW);
		bottoneSvuotaPannello4.setBounds(panel4.getWidth()/5-30, panel4.getHeight()/6*4, panel4.getWidth()/5+10, panel4.getHeight()/14);
		panel4.add(bottoneSvuotaPannello4);
		
		bottoneAggiungi = new JButton("AGGIUNGI AL CATALOGO");
		bottoneAggiungi.setBackground(Color.ORANGE);
		bottoneAggiungi.setBounds(panel4.getWidth()/5*3+16, panel4.getHeight()/6*4, panel4.getWidth()/5+10, panel4.getHeight()/14);
		panel4.add(bottoneAggiungi);
		
		bottoneChiudiPannello4 = new JButton("X");
		bottoneChiudiPannello4.setBackground(Color.RED);
		bottoneChiudiPannello4.setBounds(panel4.getWidth()/20*19-10, 0, panel4.getWidth()/20, panel4.getHeight()/18);
		panel4.add(bottoneChiudiPannello4);
		
		
		//Ascoltatori pannello 4
		
		ascoltatoreRadioButtonAriaPannello4  		= new SelezionaViaAriaPannello4AA();
		ascoltatoreRadioButtonMarePannello4  		= new SelezionaViaMarePannello4AA();
		ascoltatoreRadioButtonTerraPannello4 		= new SelezionaViaTerraPannello4AA();
		ascoltatoreBottoneChiudiPannello4    		= new ChiudiPannello4AA();
		ascoltatoreBottoneAggiungi			 		= new AggiungiAA();
		ascoltatoreTendinaMezziPannello4	 		= new TendinaMezziPannello4AA();
		ascoltatoreTendinaCittaPartenzaPannello4 = new TendinaCittaPartenzaPannello4AA();
		ascoltatoreTendinaCittaArrivoPannello4    = new TendinaCittaArrivoPannello4AA();
		ascoltatoreBottoneSvuotaPannello4			= new SvuotaPannello4AA();
		
		
		
		radioAriaPannello4.addActionListener(ascoltatoreRadioButtonAriaPannello4);
		radioMarePannello4.addActionListener(ascoltatoreRadioButtonMarePannello4);
		radioTerraPannello4.addActionListener(ascoltatoreRadioButtonTerraPannello4);
		bottoneAggiungi.addActionListener(ascoltatoreBottoneAggiungi);
		bottoneChiudiPannello4.addActionListener(ascoltatoreBottoneChiudiPannello4);
		tendinaMezziPannello4.addActionListener(ascoltatoreTendinaMezziPannello4);
		tendinaCittaPartenzaPannello4.addActionListener(ascoltatoreTendinaCittaPartenzaPannello4);
		tendinaCittaeArrivoPannello4.addActionListener(ascoltatoreTendinaCittaArrivoPannello4);
		bottoneSvuotaPannello4.addActionListener(ascoltatoreBottoneSvuotaPannello4);
		
		
		
		/*
		 * quinto pannello: questo pannello si attiva premendo il bottone RIMUOVI VIAGGIO del terzo pannello
		 */
		
		
		panel5 = new JPanel();
		panel5.setSize(superPanel.getWidth(), superPanel.getHeight()/10*7);
		panel5.setLocation(0, superPanel.getHeight()/10*3);	
		panel5.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel5);
		panel5.setVisible(false); 			//deve essere invisibile all'inizio, attivato solo dal bottone
		
		
		labelTitoloPannello5 = new JLabel();	
		labelTitoloPannello5.setFont(new Font("Arial", 0, 20));
		labelTitoloPannello5.setBounds(panel5.getWidth()/3, panel5.getHeight()/200, panel5.getWidth()/3, panel5.getHeight()/7);
		labelTitoloPannello5.setVerticalAlignment(JLabel.CENTER);
		labelTitoloPannello5.setHorizontalAlignment(JLabel.CENTER);
		labelTitoloPannello5.setText("RIMUOVI VIAGGIO");
		panel5.add(labelTitoloPannello5);
		
		areaTestoPannello5 = new JTextArea();
		areaTestoPannello5 = new JTextArea(panel5.getWidth()/40*38, panel5.getHeight()/6*3);
		areaTestoPannello5.setFont(new Font("Arial", 0, 15));
		areaTestoPannello5.setEditable(false);
		areaTestoPannello5.setLineWrap(false);
		scrollAreaTestoPannello5 = new JScrollPane(areaTestoPannello5);   //creo un piccolo scroll e lo aggiungo alla text area
		scrollAreaTestoPannello5.setBounds(panel5.getWidth()/40, panel5.getHeight()/7, panel5.getWidth()/40*38, panel5.getHeight()/6*3);
		panel5.add(scrollAreaTestoPannello5);
		
		
		
		labelAmbientePannello5 = new JLabel();        //Etichetta per i mezzi
		labelAmbientePannello5.setFont(new Font("Arial", 0, 15));
		labelAmbientePannello5.setBounds(panel4.getWidth()/11-35, panel5.getHeight()/6*4, panel5.getWidth()/6, 20);
		labelAmbientePannello5.setText("Ambiente");
		panel5.add(labelAmbientePannello5);
		
		
		tendinaAmbientePannello5 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaAmbientePannello5.setBackground(Color.WHITE);
		tendinaAmbientePannello5.setEnabled(false);
		tendinaAmbientePannello5.setBounds(panel5.getWidth()/11-35, panel5.getHeight()/6*4+20, panel5.getWidth()/6, 20);
		panel5.add(tendinaAmbientePannello5);

		
		
		
		labelMezziPannello5 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello5.setFont(new Font("Arial", 0, 15));
		labelMezziPannello5.setBounds(panel5.getWidth()/11*3-35, panel5.getHeight()/6*4, panel5.getWidth()/6, 20);
		labelMezziPannello5.setText("Mezzo di Trasporto");
		panel5.add(labelMezziPannello5);
		
		
		tendinaMezziPannello5 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello5.setBackground(Color.WHITE);
		tendinaMezziPannello5.setEnabled(false);
		tendinaMezziPannello5.setBounds(panel5.getWidth()/11*3-35, panel5.getHeight()/6*4+20, panel5.getWidth()/6, 20);
		panel5.add(tendinaMezziPannello5);
		
		
		labelCittaPartenzaPannello5 = new JLabel();        //Etichetta per Stazioni di partenza
		labelCittaPartenzaPannello5.setFont(new Font("Arial", 0, 15));
		labelCittaPartenzaPannello5.setBounds(panel5.getWidth()/11*5-35, panel5.getHeight()/6*4, panel5.getWidth()/6, 20);
		labelCittaPartenzaPannello5.setText("Citta' di Partenza");
		panel5.add(labelCittaPartenzaPannello5);
		
		
		tendinaCittaPartenzaPannello5 = new JComboBox<String>();	 //Tendina per stazioni di partenza
		tendinaCittaPartenzaPannello5.setBackground(Color.WHITE);
		tendinaCittaPartenzaPannello5.setBounds(panel5.getWidth()/11*5-35, panel5.getHeight()/6*4+20, panel5.getWidth()/6, 20);
		tendinaCittaPartenzaPannello5.setEnabled(false);
		panel5.add(tendinaCittaPartenzaPannello5);
		
		
		labelCittaArrivoPannello5 = new JLabel();        //Etichetta per Stazione di arrivo
		labelCittaArrivoPannello5.setFont(new Font("Arial", 0, 15));
		labelCittaArrivoPannello5.setBounds(panel5.getWidth()/11*7-35, panel5.getHeight()/6*4, panel5.getWidth()/6, 20);
		labelCittaArrivoPannello5.setText("Citta' di Arrivo");
		panel5.add(labelCittaArrivoPannello5);
		
		
		tendinaCittaArrivoPannello5 = new JComboBox<String>();	 //Tendina per stazioni di arrivo
		tendinaCittaArrivoPannello5.setBackground(Color.WHITE);
		tendinaCittaArrivoPannello5.setBounds(panel5.getWidth()/11*7-35, panel5.getHeight()/6*4+20, panel5.getWidth()/6, 20);
		tendinaCittaArrivoPannello5.setEnabled(false);
		panel5.add(tendinaCittaArrivoPannello5);
		
		
		labelViaPannello5 = new JLabel();	//Etichetta per Stazione intermedia
		labelViaPannello5.setFont(new Font("Arial", 0, 15));
		labelViaPannello5.setBounds(panel5.getWidth()/11*9-35, panel5.getHeight()/6*4, panel5.getWidth()/6, 20);
		labelViaPannello5.setText("Via");
		panel5.add(labelViaPannello5);
		
		
		tendinaViaPannello5 = new JComboBox<String>(); //Tendina per stazioni intermedie
		tendinaViaPannello5.setBackground(Color.WHITE);
		tendinaViaPannello5.setBounds(panel5.getWidth()/11*9-35, panel5.getHeight()/6*4+20, panel5.getWidth()/6, 20);
		tendinaViaPannello5.setEnabled(false);
		panel5.add(tendinaViaPannello5);
		
		bottoneSvuotaPannello5 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello5.setBackground(Color.YELLOW);
		bottoneSvuotaPannello5.setBounds(panel5.getWidth()/5-30, panel5.getHeight()/7*6-20, panel5.getWidth()/5+10, panel5.getHeight()/14);
		panel5.add(bottoneSvuotaPannello5);

		bottoneRimuovi = new JButton("RIMUOVI DAL CATALOGO");
		bottoneRimuovi.setBackground(Color.ORANGE);
		bottoneRimuovi.setBounds(panel5.getWidth()/5*3+16, panel5.getHeight()/7*6-20, panel5.getWidth()/5+10, panel5.getHeight()/14);
		panel5.add(bottoneRimuovi);
		
		
		
		bottoneChiudiPannello5 = new JButton("X");
		bottoneChiudiPannello5.setBackground(Color.RED);
		bottoneChiudiPannello5.setBounds(panel5.getWidth()/20*19-10, 0, panel5.getWidth()/20, panel5.getHeight()/18);
		panel5.add(bottoneChiudiPannello5);
		
		
		//Ascoltatori pannello 5
		
		ascoltatoreTendinaAmbientePannello5   = new TendinaAmbientePannello5AA();
		ascoltatoreTendinaMezziPannello5	  = new TendinaMezziPannello5AA();
		ascoltatoreTendinaPartenzePannello5   = new TendinaPartenzePannello5AA();
		ascoltatoreTendinaArriviPannello5     = new TendinaArriviPannello5AA();
		ascoltatoreTendinaViaPannello5 = new TendinaIntermediViaPannello5AA();
		ascoltatoreBottoneRimuovi			  = new RimuoviAA();
		ascoltatoreBottoneChiudiPannello5     = new ChiudiPannello5AA();
		ascoltatoreBottoneSvuotaPannello5 	  = new SvuotaPannello5AA();
		
		tendinaAmbientePannello5.addActionListener(ascoltatoreTendinaAmbientePannello5);
		tendinaMezziPannello5.addActionListener(ascoltatoreTendinaMezziPannello5);
		tendinaCittaPartenzaPannello5.addActionListener(ascoltatoreTendinaPartenzePannello5);
		tendinaCittaArrivoPannello5.addActionListener(ascoltatoreTendinaArriviPannello5);
		tendinaViaPannello5.addActionListener(ascoltatoreTendinaViaPannello5);
		bottoneRimuovi.addActionListener(ascoltatoreBottoneRimuovi);
		bottoneChiudiPannello5.addActionListener(ascoltatoreBottoneChiudiPannello5);
		bottoneSvuotaPannello5.addActionListener(ascoltatoreBottoneSvuotaPannello5);
		

		//Per finire carico il catalogo
		caricaCatalogo();
	}//FINE COSTRUTTORE
	
	
	// Aggiorna la tendina dei mezzi del terzo pannello
	private void aggiornaTendinePannello4() {
		
		tendinaMezziPannello4.removeAllItems();    //rimuove tutti gli elementi dalla tendina
		tendinaCittaPartenzaPannello4.removeAllItems();
		tendinaCittaeArrivoPannello4.removeAllItems();

		
		try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
			Set<String> s = controllore.mostraMezziInCatalogo(ambienteScelto);
			Iterator<String> it = s.iterator();
			
			while(it.hasNext()) 					//itero l'insieme di chiavi
				tendinaMezziPannello4.addItem(it.next());  //ne aggiungo uno alla volta
			
		} catch (IDEsternoElementoException e) {
			System.out.println(e.getMessage()+"\n");
		}
		
		tendinaMezziPannello4.addItem("new..."); //per mettercene uno nuovo!!!
		campoCittaPartenzaPannello4.setText("");
		campoCittaArrivoPannello4.setText("");
		campoViaPannello4.setText("");
		campoInfoPannello4.setText("");
		
		tendinaMezziPannello4.setEnabled(true);
		campoViaPannello4.setEditable(true);  // attiva il campo "stazione intermedia"
		campoInfoPannello4.setEditable(true);
	}
	
	// Aggiorna la tendina degli ambienti del quarto pannello
	private void aggiornaTendinePannello5() { 
		
		tendinaAmbientePannello5.removeAllItems();
		tendinaAmbientePannello5.setEnabled(false);
							
		//imposto ambiente scelto
		ambienteScelto = "-----";
		
		try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
			Set<String> s = controllore.mostraAmbientiInCatalogo();
			Iterator<String> it = s.iterator();
			if (s.size() > 1){ //se c'e' piu' di un elemento visualizzo l'elemento neutro
				//devo aggiungere l'elemento vuoto
				tendinaAmbientePannello5.addItem("-----");
			}
				
			while(it.hasNext()){ 					//itero l'insieme di chiavi
				tendinaAmbientePannello5.addItem(it.next());  //ne aggiungo uno alla volta
			}
				
			tendinaAmbientePannello5.setEnabled(true);
			
			//visualizzo subito il catalogo	
			areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
			areaTestoPannello5.setText(areaTestoImp + areaTestoCatalogo);
			areaTestoPannello5.setCaretPosition(0);
					
		} catch (MappaException e) {
			areaTestoPannello5.setText(e.getMessage()+"\n");
		} catch (IDEsternoElementoException e) {
			areaTestoPannello5.setText(e.getMessage()+"\n");
		} catch (TrattaException e) {
			areaTestoPannello5.setText(e.getMessage()+"\n");
		} 

	}
	
	private void controlloSintatticoDati(String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException{
		
		if (mezzo.equals("")||partenza.equals("")||arrivo.equals(""))
			throw new IDEsternoElementoException("I campi con * sono obbligatori.");
		
		
		String s = mezzo+partenza+arrivo+via;
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (!Character.isLetter(s.charAt(i))&&!Character.isWhitespace(c))
				throw new IDEsternoElementoException("Caratteri non validi. Controllare i dati inseriti...");
		}
	
	}
	
	
	//rende maiuscola la prima lettera di ogni parola della stringa passata come parametro, sostituisce spazi multipli con spazio singolo
	private String uppercaseFirstLetters(String str) {
	    boolean prevWasWhiteSp = true;
	    char[] chars = str.trim().replaceAll("\\s+"," ").toLowerCase().toCharArray();
	    for (int i = 0; i < chars.length; i++) {
	        if (Character.isLetter(chars[i])){
	            if (prevWasWhiteSp) {
	                chars[i] = Character.toUpperCase(chars[i]);    
	            } 
	            prevWasWhiteSp = false;
	        } else {
	            prevWasWhiteSp = Character.isWhitespace(chars[i]);
	        }
	    }
	    return new String(chars);
	}
	
	
	private void caricaCatalogo(){
		
		boolean b = true;
		/*try {
			controllore.carica();
		} catch (DeserializzazioneException e) {*/
			JOptionPane.showMessageDialog(null, "Non e' stato ancora salvato un catalogo.  Faccio partire il thread.");
			avviaThread();
			b = false;
	//	}
		
		if (b){
			JOptionPane.showMessageDialog(null, "E' stato caricato il catalogo", "Catalogo Caricato", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	private void avviaThread(){
   
			
			//parte il gestore (aggiunge il prodotto) 
			PromotoreThread tPromotore = new PromotoreThread(controllore);
			Thread ttgestore = new Thread(tPromotore);
			ttgestore.start();
			JOptionPane.showMessageDialog(null, " Un Thread ha popolato il catalogo ", "Thread Partito!!!", JOptionPane.INFORMATION_MESSAGE);
			

		}
	
	

	
	
	/*
	 * Classi Ascoltatori per bottoni pannello 1
	 */
	
	private class IndietroAA implements ActionListener{

		@Override
		/*
		 * Torna al pannello precedente e serializza!
		 */
		public void actionPerformed(ActionEvent arg0) {
		
		/*	try {  //Salva gli articoli all'uscita
				controllore.salva();
			} catch (SerializzazioneException e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
		*/	
			superPanel.setVisible(false);
			BoundaryAAAprimaria.superPanel.setVisible(true);
		//	BoundaryAAAprimaria.confinePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
	}
	
	private class EsciAA implements ActionListener{

		@Override
		/*
		 * Esce dal programma ma serializza!!!!
		 */
		public void actionPerformed(ActionEvent arg0) {
			
	/*		try {  //Salva gli articoli all'uscita
				controllore.salva();
			} catch (SerializzazioneException e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
	*/		
			System.exit(0);
		}
		
	}
	
	
	
	/*
	 * Classi Ascoltatori per bottoni pannello 2
	 */
	
	private class GestioneCatalogoAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel2.setVisible(true);
			panel3.setVisible(true); //attiva pannello 3
			
			
			bottoneGestioneCatalogoPannello2.setEnabled(false);  //disattiva il bottone del pannello2
		}
		
	}


	
	/*
	 * Classi Ascoltatori per bottoni pannello 3
	 */
	
	private class AggiungiViaggioAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel2.setVisible(true);
			panel3.setVisible(false);
			panel3.setVisible(true);
			panel4.setVisible(true);  //attiva pannello 4
			
			bottoneAggiungiViaggio.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(false);
			bottoneChiudiPannello3.setEnabled(false);
		
		}
		
	}	
	
	private class RimuoviViaggioAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel2.setVisible(true);
			panel3.setVisible(false);
			panel3.setVisible(true);
			panel5.setVisible(true);	//attiva pannello 5
			bottoneAggiungiViaggio.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(false);
			bottoneChiudiPannello3.setEnabled(false);
			
			//premendo il bottone, mi attiva subito la tendina ambiente implementata in aggiornaTendinaPannello5
			aggiornaTendinePannello5();
			

		}
		
	}
	
	private class ChiudiPannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel3.setVisible(false); 					//chiude questo pannello
			bottoneGestioneCatalogoPannello2.setEnabled(true);	//riattiva i bottoni
						
		}
	}
	

	
	/*
	 * Ascoltatori per elementi pannello 4
	 */
	
	
	private class SelezionaViaAriaPannello4AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Aria
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Aria";			//Cambia l'ambiente scelto
			aggiornaTendinePannello4();
		}
	
	}


	private class SelezionaViaMarePannello4AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Mare
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Mare"; 			//Cambia l'ambiente scelto
			aggiornaTendinePannello4();
		}
		
	}
	
	private class SelezionaViaTerraPannello4AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Terra
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Terra"; 			//Cambia l'ambiente scelto
			aggiornaTendinePannello4();
		}
		
	}
	
	private class TendinaMezziPannello4AA implements ActionListener{
		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			
			if (tendinaMezziPannello4.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaMezziPannello4.getSelectedItem().toString().equals("new...")){
					campoMezziPannello4.setEditable(true); //attiva il campo sotto per inserire un mezzo non ancora usato
					campoCittaPartenzaPannello4.setEditable(true);
					campoCittaArrivoPannello4.setEditable(true);
					tendinaCittaPartenzaPannello4.removeAllItems();
					tendinaCittaeArrivoPannello4.removeAllItems();
					tendinaCittaPartenzaPannello4.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di partenza sara' nuova, la tendina non serve!
					tendinaCittaeArrivoPannello4.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di arrivo sara' nuova, la tendina non serve!
				} else {
					campoMezziPannello4.setText("");
					campoMezziPannello4.setEditable(false);
					campoCittaPartenzaPannello4.setText("");
					campoCittaPartenzaPannello4.setEditable(false);
					campoCittaArrivoPannello4.setText("");
					campoCittaArrivoPannello4.setEditable(false);
					

		
					String mezzo = (String)tendinaMezziPannello4.getSelectedItem();
					
					tendinaCittaPartenzaPannello4.setEnabled(true);
					tendinaCittaPartenzaPannello4.removeAllItems();
					
					try {
						Set<String> set = controllore.mostraCittaDiPartenzaInCatalogo(ambienteScelto,mezzo);
						Iterator<String> it = set.iterator();
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaPartenzaPannello4.addItem(it.next());
						}
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 

					tendinaCittaPartenzaPannello4.addItem("new...");
					tendinaCittaPartenzaPannello4.setSelectedIndex(0);						
				}
				
			}	
			
		}
		
	}
	
	private class TendinaCittaPartenzaPannello4AA implements ActionListener{
		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (tendinaCittaPartenzaPannello4.getItemCount() != 0) {//ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaCittaPartenzaPannello4.getSelectedItem().toString().equals("new...")){
					campoCittaPartenzaPannello4.setEditable(true); //attiva il campo sotto per inserire un stazione di partenza non ancora usata
					campoCittaArrivoPannello4.setEditable(true);
					tendinaCittaeArrivoPannello4.removeAllItems();
					tendinaCittaeArrivoPannello4.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di arrivo sara' nuova, la tendina non serve!
				} else {
					campoCittaPartenzaPannello4.setText("");
					campoCittaPartenzaPannello4.setEditable(false);
					campoCittaArrivoPannello4.setText("");
					campoCittaArrivoPannello4.setEditable(false);
					
					String mezzo = (String) tendinaMezziPannello4.getSelectedItem();
					String partenza = (String) tendinaCittaPartenzaPannello4.getSelectedItem();
					
					tendinaCittaeArrivoPannello4.setEnabled(true);
					tendinaCittaeArrivoPannello4.removeAllItems();
					
					
		
					try {
						
						Set<String> set = controllore.mostraCittaDiArrivoInCatalogo(ambienteScelto, mezzo, partenza);
						Iterator<String> it = set.iterator();
						while (it.hasNext()){
								tendinaCittaeArrivoPannello4.addItem(it.next());
						}
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					tendinaCittaeArrivoPannello4.addItem("new...");
					tendinaCittaeArrivoPannello4.setSelectedIndex(0);
					
				}	
				
			}
			
			
		}
		
		
	}
	
	
	private class TendinaCittaArrivoPannello4AA implements ActionListener{

		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (tendinaCittaeArrivoPannello4.getItemCount() != 0) {//ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaCittaeArrivoPannello4.getSelectedItem().toString().equals("new...")){
					campoCittaArrivoPannello4.setEditable(true); //Attiva il campo sotto per inserire una stazione di arrivo non ancora usata
				} else {
					campoCittaArrivoPannello4.setText("");
					campoCittaArrivoPannello4.setEditable(false);
				}
			}
			
		}
		
	}
	
	private class AggiungiAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (ambienteScelto != null){
				
				String mezzoTrasporto;
				String cittaPartenza;
				String cittaArrivo;
				String via;
				String info;
			
				//le informazioni possono essere prese o da tendina o da campo testo...
				if (tendinaMezziPannello4.getSelectedItem().toString().equals("new...")){
					mezzoTrasporto = uppercaseFirstLetters(campoMezziPannello4.getText());
				} else {
					mezzoTrasporto = (String) tendinaMezziPannello4.getSelectedItem();
				}
			
				if (!tendinaCittaPartenzaPannello4.isEnabled() || tendinaCittaPartenzaPannello4.getSelectedItem().toString().equals("new...")){
					cittaPartenza = uppercaseFirstLetters(campoCittaPartenzaPannello4.getText());
				} else {
					cittaPartenza = (String) tendinaCittaPartenzaPannello4.getSelectedItem();
				}
				
				if (!tendinaCittaeArrivoPannello4.isEnabled() || tendinaCittaeArrivoPannello4.getSelectedItem().toString().equals("new...")){
					cittaArrivo = uppercaseFirstLetters(campoCittaArrivoPannello4.getText());
				} else {
					cittaArrivo = (String) tendinaCittaeArrivoPannello4.getSelectedItem();
				}
			
				via = uppercaseFirstLetters(campoViaPannello4.getText());
				
				info = uppercaseFirstLetters(campoInfoPannello4.getText()); 
				
						
				//aggiungo il viaggio
				try {
					controlloSintatticoDati(mezzoTrasporto, cittaPartenza, cittaArrivo, via);
					
					int conferma = JOptionPane.showConfirmDialog(null, "Aggiungere il viaggio nel catalogo?", "Conferma Nuovo Viaggio in Catalogo", JOptionPane.YES_NO_OPTION);
					
					if (conferma == JOptionPane.YES_OPTION){	
						controllore.aggiungiViaggio(ambienteScelto, mezzoTrasporto, cittaPartenza, cittaArrivo, via, info);
						JOptionPane.showMessageDialog(null, "Il nuovo viaggio e' stato aggiunto correttamente nel catalogo.", "Viaggio Aggiunto", JOptionPane.INFORMATION_MESSAGE);
						aggiornaTendinePannello4(); //aggiorno le tendine
					}
					
				} catch (IDEsternoElementoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Attenzione!", JOptionPane.WARNING_MESSAGE);
				} catch (TrattaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Attenzione!", JOptionPane.WARNING_MESSAGE);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
	
			
			} else {
				JOptionPane.showMessageDialog(null, "Selezionare un ambiente...");			
			}
			
		}
		
	}
	
	
	
	private class SvuotaPannello4AA implements ActionListener{
		/*
		 * Svuota tutti i campi
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = null;
			
			
			gruppoAmbientePannello4.clearSelection();   //toglie la spunta ai radio bottoni
			
			tendinaMezziPannello4.removeAllItems();  //svuota le tendine
			tendinaCittaPartenzaPannello4.removeAllItems();
			tendinaCittaeArrivoPannello4.removeAllItems();
			
			campoMezziPannello4.setText("");		//svuota tutti i campi testo
			campoCittaPartenzaPannello4.setText("");
			campoCittaArrivoPannello4.setText("");
			campoViaPannello4.setText("");
			campoInfoPannello4.setText("");
			
			campoMezziPannello4.setEditable(false);
			campoCittaPartenzaPannello4.setEditable(false);
			tendinaMezziPannello4.setEnabled(false);
			tendinaCittaPartenzaPannello4.setEnabled(false);
			tendinaCittaeArrivoPannello4.setEnabled(false);
			campoCittaArrivoPannello4.setEditable(false);
			campoViaPannello4.setEditable(false);
			campoInfoPannello4.setEditable(false);
		}
		
	}
	
	private class ChiudiPannello4AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel4.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiViaggio.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(true);
			bottoneChiudiPannello3.setEnabled(true);
			
			
			//svuoto cmq il pannello
			gruppoAmbientePannello4.clearSelection();  //toglie la spunta ai radio bottoni
			ambienteScelto = null;
			
			tendinaMezziPannello4.removeAllItems();  //svuota le tendine
			tendinaCittaPartenzaPannello4.removeAllItems();
			tendinaCittaeArrivoPannello4.removeAllItems();
			
			tendinaMezziPannello4.setEnabled(false);
			tendinaCittaPartenzaPannello4.setEnabled(false);
			tendinaCittaeArrivoPannello4.setEnabled(false);
			
			campoMezziPannello4.setText("");		//svuota tutti i campi testo
			campoCittaPartenzaPannello4.setText("");
			campoCittaArrivoPannello4.setText("");
			campoViaPannello4.setText("");
			campoInfoPannello4.setText("");
			
			campoMezziPannello4.setEditable(false);
			campoCittaPartenzaPannello4.setEditable(false);
			campoCittaArrivoPannello4.setEditable(false);
			campoViaPannello4.setEditable(false);
			campoInfoPannello4.setEditable(false);
		}
	}

	
	
	
	/*
	 * Ascoltatori per elementi pannello 5
	 */
	
	
	private class TendinaAmbientePannello5AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaMezziPannello5.removeAllItems();
			tendinaMezziPannello5.setEnabled(false);
			
			//prendo il valore di questa tendina
			ambienteScelto	= (String)tendinaAmbientePannello5.getSelectedItem();
			
			if (tendinaAmbientePannello5.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
			
					
					if(!ambienteScelto.equals("-----")){ //Solo se non e' l'elemento neutro
							
						try {  //cerca nella mappa tutte le chiavi da aggiungere in tendina
							Set<String> s = controllore.mostraMezziInCatalogo(ambienteScelto);
							Iterator<String> it = s.iterator();
							
							if (s.size() > 1){
								//inserisco l'elemento neutro
								tendinaMezziPannello5.addItem("-----");
							}
							
							while(it.hasNext()){ 					//itero l'insieme di chiavi
								tendinaMezziPannello5.addItem(it.next());  //ne aggiungo uno alla volta
							}
							tendinaMezziPannello5.setEnabled(true);
							
						} catch (IDEsternoElementoException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					//Aggiorno l'area testo che mostra il catalogo
					try {
						
						areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
						areaTestoPannello5.setText(areaTestoImp + areaTestoCatalogo);
						areaTestoPannello5.setCaretPosition(0);
						
					} catch (MappaException e1) {
						areaTestoPannello5.setText(e1.getMessage()+"\n");
					} catch (IDEsternoElementoException e1) {
						areaTestoPannello5.setText(e1.getMessage()+"\n");
					} catch (TrattaException e1) {
						areaTestoPannello5.setText(e1.getMessage()+"\n");
					}
	
			}
		
		}
		
	}
	
	
	private class TendinaMezziPannello5AA implements ActionListener{
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaCittaPartenzaPannello5.removeAllItems();
			tendinaCittaPartenzaPannello5.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello5.getSelectedItem();   //Neanche servirebbe, in teoria...
			mezzoScelto = (String)tendinaMezziPannello5.getSelectedItem();
			
			if (tendinaMezziPannello5.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
	
				if(!mezzoScelto.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraCittaDiPartenzaInCatalogo(ambienteScelto, mezzoScelto);
						Iterator<String> it = s.iterator();
						
						if (s.size() > 1){
							//inserisco l'elemento neutro
							tendinaCittaPartenzaPannello5.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaPartenzaPannello5.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaCittaPartenzaPannello5.setEnabled(true);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}   
				}
				
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello5.setText(areaTestoImp + areaTestoCatalogo);		
					areaTestoPannello5.setCaretPosition(0);
				
				} catch (MappaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (TrattaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				}
						
	
			}
		
		}
		
	}
	
	private class TendinaPartenzePannello5AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaCittaArrivoPannello5.removeAllItems();
			tendinaCittaArrivoPannello5.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello5.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello5.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello5.getSelectedItem();
			
			if (tendinaCittaPartenzaPannello5.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if(!partenzaScelta.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraCittaDiArrivoInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta);
						Iterator<String> it = s.iterator();
						
						if(s.size() > 1){
							//inserisco l'elemento neutro
							tendinaCittaArrivoPannello5.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaArrivoPannello5.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaCittaArrivoPannello5.setEnabled(true);
						tendinaCittaArrivoPannello5.setSelectedIndex(0);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
	
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello5.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello5.setCaretPosition(0);
					
				} catch (MappaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (TrattaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				}
			
			}
			
		}
		
	}
	
	private class TendinaArriviPannello5AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaViaPannello5.removeAllItems();
			tendinaViaPannello5.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello5.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello5.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello5.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello5.getSelectedItem();
			
			if (tendinaCittaArrivoPannello5.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if(!arrivoScelto.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraViaInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
						Iterator<String> it = s.iterator();
						
						if(s.size() > 1){
							//inserisco l'elemento neutro
							tendinaViaPannello5.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaViaPannello5.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaViaPannello5.setEnabled(true);
						tendinaViaPannello5.setSelectedIndex(0);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
	
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello5.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello5.setCaretPosition(0);
					
				} catch (MappaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");	
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (TrattaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				}
			
			}
			
			ambienteScelto = (String) tendinaAmbientePannello5.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello5.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello5.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello5.getSelectedItem();
			
			if (tendinaCittaArrivoPannello5.getItemCount() != 0) {
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello5.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello5.setCaretPosition(0);
				} catch (MappaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (TrattaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				}
				
			}
			
			
		}
		
	}
	
	
	private class TendinaIntermediViaPannello5AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = (String) tendinaAmbientePannello5.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello5.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello5.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello5.getSelectedItem();
			viaScelta = (String) tendinaViaPannello5.getSelectedItem();
			
			if (tendinaViaPannello5.getItemCount() != 0) {
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello5.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello5.setCaretPosition(0);
				} catch (MappaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				} catch (TrattaException e1) {
					areaTestoPannello5.setText(e1.getMessage()+"\n");
				}
				
			}
			
		}
		
	}
	
	
	private class RimuoviAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (tendinaViaPannello5.getItemCount() != 0 && !viaScelta.equals("-----")){
				
				String via = (String) tendinaViaPannello5.getSelectedItem();
				
				// chiedo conferma
				int conferma = JOptionPane.showConfirmDialog(null, "Rimuovere il viaggio dal catalogo?", "Conferma Rimozione Viaggio", JOptionPane.YES_NO_OPTION);
				if (conferma == JOptionPane.YES_OPTION){
					
					// rimuovo il viaggio
					try {
						controllore.rimuoviViaggio(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, via);
						JOptionPane.showMessageDialog(null, "Il viaggio e' stato rimosso correttamente dal catalogo.", "Viaggio Rimosso", JOptionPane.INFORMATION_MESSAGE);
						//aggiorno tutti i campi dopo aver rimosso il viaggio
						aggiornaTendinePannello5();
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} catch (TrattaException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} catch (OffertaException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), e1.toString(), JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
					
			} else {
				JOptionPane.showMessageDialog(null, "Nessun viaggio selezionato!");
			}
			
		}
		
	}
	
	private class SvuotaPannello5AA implements ActionListener{
		/*
		 * Svuota tutti i campi
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (tendinaAmbientePannello5.isEnabled()){
								
				tendinaAmbientePannello5.setSelectedIndex(0); //la tendina torna al primo valore.
				
				if (tendinaAmbientePannello5.getItemCount()>1){
					tendinaMezziPannello5.removeAllItems();  //svuota le tendine
					tendinaMezziPannello5.setEnabled(false);//disattiva tutte le tendine

					ambienteScelto = null;
					mezzoScelto = null;
					partenzaScelta = null;
					arrivoScelto = null;
					viaScelta = null;

				}
				
		//	} else {
		//		JOptionPane.showMessageDialog(null, "Nessun viaggio in catalogo!", "Catalogo Vuoto", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		
	}
	
	private class ChiudiPannello5AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel5.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiViaggio.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(true);
			bottoneChiudiPannello3.setEnabled(true);
			
			//svuoto comunque il pannello
			if (tendinaAmbientePannello5.getItemCount() != 0){
				
				tendinaAmbientePannello5.setSelectedIndex(0); //la tendina torna al primo valore.
				tendinaMezziPannello5.removeAllItems();  //svuota le tendine
				tendinaMezziPannello5.setEnabled(false);//disattiva tutte le tendine

			}
			
			ambienteScelto = null;
			mezzoScelto = null;
			partenzaScelta = null;
			arrivoScelto = null;
			viaScelta = null;

		}
	}
		
}
