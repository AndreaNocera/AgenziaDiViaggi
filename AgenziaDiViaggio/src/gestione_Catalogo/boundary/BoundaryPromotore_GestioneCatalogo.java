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
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaException;
import gestione_Catalogo.exception.TrattaException;
import gestione_Catalogo.exception.TrattaInesistenteException;


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

	
	//Elementi Pannello1
	private JButton bottoneAggiungiViaggio;
	private JButton bottoneRimuoviViaggio;
	private JButton bottoneChiudiPannello1;
    
    private AggiungiViaggioAA ascoltatoreBottoneAggiungiViaggio;
    private RimuoviViaggioAA ascoltatoreBottoneRimuoviViaggio;
    private ChiudiPannello1AA ascoltatoreBottoneChiudiPannello1;

    
    //Elementi Pannello2
    private JLabel	labelTitoloPannello2;
    
	private JRadioButton radioMarePannello2;
	private JRadioButton radioTerraPannello2;
	private JRadioButton radioAriaPannello2;

	private ButtonGroup gruppoAmbientePannello2;

	private JLabel labelMezziPannello2;
	private JComboBox<String> tendinaMezziPannello2;
	private JTextField campoMezziPannello2;

	private JLabel labelCittaPartenzaPannello2;
	private JComboBox<String> tendinaCittaPartenzaPannello2;
	private JTextField campoCittaPartenzaPannello2;

	private JLabel labelCittaArrivoPannello2;
	private JComboBox<String> tendinaCittaeArrivoPannello2;
	private JTextField campoCittaArrivoPannello2;
	
	private JLabel labelViaPannello2;
	private JTextField campoViaPannello2;
	
	private JLabel labelInfoPannello2;
	private JTextField campoInfoPannello2;

	private JButton bottoneAggiungi;
	private JButton bottoneSvuotaPannello2;
	
	private JButton bottoneChiudiPannello2;

	private SelezionaViaAriaPannello2AA ascoltatoreRadioButtonAriaPannello2;
	private SelezionaViaMarePannello2AA ascoltatoreRadioButtonMarePannello2;
	private SelezionaViaTerraPannello2AA ascoltatoreRadioButtonTerraPannello2;
	private ChiudiPannello2AA ascoltatoreBottoneChiudiPannello2;
	private AggiungiAA ascoltatoreBottoneAggiungi;
	private TendinaMezziPannello2AA ascoltatoreTendinaMezziPannello2;
	private TendinaCittaPartenzaPannello2AA ascoltatoreTendinaCittaPartenzaPannello2;
	private TendinaCittaArrivoPannello2AA ascoltatoreTendinaCittaArrivoPannello2;
	private SvuotaPannello2AA ascoltatoreBottoneSvuotaPannello2;

	
	//Elementi Pannello3
	
	private JLabel	labelTitoloPannello3;

	private JLabel labelAmbientePannello3;
	private JComboBox<String> tendinaAmbientePannello3;

	private JTextArea areaTestoPannello3;
	private JScrollPane scrollAreaTestoPannello3;
	
	private JLabel labelMezziPannello3;
	private JComboBox<String> tendinaMezziPannello3;

	private JLabel labelCittaPartenzaPannello3;
	private JComboBox<String> tendinaCittaPartenzaPannello3;

	private JLabel labelCittaArrivoPannello3;
	private JComboBox<String> tendinaCittaArrivoPannello3;
	
	private JLabel labelViaPannello3;
	private JComboBox<String> tendinaViaPannello3;

	private JButton bottoneRimuovi;
	private JButton bottoneSvuotaPannello3;
	
	private JButton bottoneChiudiPannello3;
	
	private TendinaAmbientePannello3AA ascoltatoreTendinaAmbientePannello3;
	private TendinaMezziPannello3AA ascoltatoreTendinaMezziPannello3;
	private TendinaPartenzePannello3AA ascoltatoreTendinaPartenzePannello3;
	private TendinaArriviPannello3AA ascoltatoreTendinaArriviPannello3;
	private TendinaViaPannello3AA ascoltatoreTendinaViaPannello3;
	private ChiudiPannello3AA ascoltatoreBottoneChiudiPannello3;
	private RimuoviAA ascoltatoreBottoneRimuovi;
	private SvuotaPannello3AA ascoltatoreBottoneSvuotaPannello3;


		
	/*
	 * Costruttore
	 */
	
	public BoundaryPromotore_GestioneCatalogo(JPanel panelNext){
		
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
		 * 
		 * Il superPanel di questa Boundary prende le dimensioni del pannello Passato
		 * 
		 */
		superPanel = panelNext;
		superPanel.setVisible(true);   //Si vede ora!
		
		
		
		
		/*
		 * 
		 * primo pannello: per i bottoni della gestione del catalogo, si apre quando viene premuto GESTIONE CATALOGO
		 * 
		 */

		panel1 = new JPanel();
		panel1.setSize(superPanel.getWidth(), superPanel.getHeight()/8-10);  //Il meno 10 serve per far vedere il contorno 
		panel1.setLocation(0,0);			//x=0 e y=0 rispetto al superPanel
		panel1.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel1);				//aggiungo il primo pannello al superPannello
		panel1.setVisible(true);
		
		bottoneAggiungiViaggio = new JButton("AGGIUNGI VIAGGIO");
		bottoneAggiungiViaggio.setBackground(Color.CYAN);
		bottoneAggiungiViaggio.setBounds(panel1.getWidth()/5, panel1.getHeight()/6, panel1.getWidth()/5, panel1.getHeight()/2);
		panel1.add(bottoneAggiungiViaggio);//aggiungo il bottone al secondo pannello
		
		
		bottoneRimuoviViaggio = new JButton("RIMUOVI VIAGGIO");
		bottoneRimuoviViaggio.setBackground(Color.YELLOW);
		bottoneRimuoviViaggio.setBounds(panel1.getWidth()/5*3, panel1.getHeight()/6, panel1.getWidth()/5, panel1.getHeight()/2);
		panel1.add(bottoneRimuoviViaggio);//aggiungo il bottone al secondo pannello
		
		
		bottoneChiudiPannello1 = new JButton("X");
		bottoneChiudiPannello1.setBackground(Color.RED);
		bottoneChiudiPannello1.setBounds(panel1.getWidth()/20*19-10, 0, panel1.getWidth()/20, panel1.getHeight()/2-3);
		panel1.add(bottoneChiudiPannello1);
		
		
		
		// ascoltatori per primo pannello
		ascoltatoreBottoneAggiungiViaggio = new AggiungiViaggioAA(); 		//creo ascoltatore per bottone
		bottoneAggiungiViaggio.addActionListener(ascoltatoreBottoneAggiungiViaggio); 	//associo ascoltatore al bottone
		
		ascoltatoreBottoneRimuoviViaggio = new RimuoviViaggioAA();			//creo ascoltatore per bottone
		bottoneRimuoviViaggio.addActionListener(ascoltatoreBottoneRimuoviViaggio);		//associo ascoltatore al bottone
		
		ascoltatoreBottoneChiudiPannello1 = new ChiudiPannello1AA();
		bottoneChiudiPannello1.addActionListener(ascoltatoreBottoneChiudiPannello1);
		
		
		/*
		 * 
		 * secondo pannello: questo pannello si attiva premendo il bottone AGGIUNGI VIAGGIO del primo pannello
		 * 
		 */
		
		
		panel2 = new JPanel();
		panel2.setSize(superPanel.getWidth(), superPanel.getHeight()/8*7);
		panel2.setLocation(0, superPanel.getHeight()/8);	
		panel2.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel2);
		panel2.setVisible(false); 			//deve essere invisibile all'inizio, attivato solo dal bottone
		
		labelTitoloPannello2 = new JLabel();	
		labelTitoloPannello2.setFont(new Font("Arial", 0, 20));
		labelTitoloPannello2.setBounds(panel2.getWidth()/3, panel2.getHeight()/49, panel2.getWidth()/3, panel2.getHeight()/7);
		labelTitoloPannello2.setVerticalAlignment(JLabel.CENTER);
		labelTitoloPannello2.setHorizontalAlignment(JLabel.CENTER);
		labelTitoloPannello2.setText("AGGIUNGI VIAGGIO");
		panel2.add(labelTitoloPannello2);
		
		
		radioAriaPannello2 = new JRadioButton("Via Aria");
		radioAriaPannello2.setBounds(panel2.getWidth()/7+100, panel2.getHeight()/6, panel2.getWidth()/7, panel2.getHeight()/14);
		panel2.add(radioAriaPannello2);   //aggiungo il radiobutton al pannello 1
		
		radioMarePannello2 = new JRadioButton("Via Mare");
		radioMarePannello2.setBounds(panel2.getWidth()/7*3, panel2.getHeight()/6, panel2.getWidth()/7, panel2.getHeight()/14);
		panel2.add(radioMarePannello2);
		
		radioTerraPannello2 = new JRadioButton("Via Terra");
		radioTerraPannello2.setBounds(panel2.getWidth()/7*5-100, panel2.getHeight()/6, panel2.getWidth()/7, panel2.getHeight()/14);
		panel2.add(radioTerraPannello2);
		
		gruppoAmbientePannello2 = new ButtonGroup();
		gruppoAmbientePannello2.add(radioAriaPannello2);
		gruppoAmbientePannello2.add(radioMarePannello2);
		gruppoAmbientePannello2.add(radioTerraPannello2);   //Ora solo uno di questi 3 e' selezionabile
		
		
		
		labelMezziPannello2 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello2.setFont(new Font("Arial", 0, 15));
		labelMezziPannello2.setBounds(panel2.getWidth()/7-10, panel2.getHeight()/6*2, panel2.getWidth()/6, 20);
		labelMezziPannello2.setText("Mezzo di Trasporto*");
		panel2.add(labelMezziPannello2);
		
		
		tendinaMezziPannello2 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello2.setBackground(Color.WHITE);
		tendinaMezziPannello2.setBounds(panel2.getWidth()/7-10, panel2.getHeight()/6*2+20, panel2.getWidth()/6, 20);
		tendinaMezziPannello2.setEnabled(false);
		panel2.add(tendinaMezziPannello2);
		
		campoMezziPannello2 = new JTextField(panel2.getWidth()/7);	  //campo per aggiungere dei nuovi mezzi
		campoMezziPannello2.setFont(new Font("Arial", 0, 18));
		campoMezziPannello2.setBounds(panel2.getWidth()/7-10, panel2.getHeight()/6*2+45, panel2.getWidth()/6, 20);
		campoMezziPannello2.setEditable(false);   				// all'inizio e' disattivato, si attiva solo con new...
		panel2.add(campoMezziPannello2);
		
		
		labelCittaPartenzaPannello2 = new JLabel();        //Etichetta per Stazione di partenza
		labelCittaPartenzaPannello2.setFont(new Font("Arial", 0, 15));
		labelCittaPartenzaPannello2.setBounds(panel2.getWidth()/7*3-10, panel2.getHeight()/6*2, panel2.getWidth()/6, 20);
		labelCittaPartenzaPannello2.setText("Citta' di Partenza*");
		panel2.add(labelCittaPartenzaPannello2);
		
		
		tendinaCittaPartenzaPannello2 = new JComboBox<String>();	  //Tendina per le partenze
		tendinaCittaPartenzaPannello2.setBackground(Color.WHITE);
		tendinaCittaPartenzaPannello2.setEnabled(false);
		tendinaCittaPartenzaPannello2.setBounds(panel2.getWidth()/7*3-10, panel2.getHeight()/6*2+20, panel2.getWidth()/6, 20);
		panel2.add(tendinaCittaPartenzaPannello2);
		
		
		campoCittaPartenzaPannello2 = new JTextField (panel2.getWidth()/7);	 //Campo per stazione di partenza
		campoCittaPartenzaPannello2.setFont(new Font("Arial",0,18));
		campoCittaPartenzaPannello2.setBounds(panel2.getWidth()/7*3-10, panel2.getHeight()/6*2+45, panel2.getWidth()/6, 20);
		campoCittaPartenzaPannello2.setEditable(false);
		panel2.add(campoCittaPartenzaPannello2);
		
		
		labelCittaArrivoPannello2 = new JLabel();        //Etichetta per Stazione di arrivo
		labelCittaArrivoPannello2.setFont(new Font("Arial", 0, 15));
		labelCittaArrivoPannello2.setBounds(panel2.getWidth()/7*5-10, panel2.getHeight()/6*2, panel2.getWidth()/6, 20);
		labelCittaArrivoPannello2.setText("Citta' di Arrivo*");
		panel2.add(labelCittaArrivoPannello2);
		
		tendinaCittaeArrivoPannello2 = new JComboBox<String>();     // Tendina per gli arrivi
		tendinaCittaeArrivoPannello2.setBackground(Color.WHITE);
		tendinaCittaeArrivoPannello2.setEnabled(false);
		tendinaCittaeArrivoPannello2.setBounds(panel2.getWidth()/7*5-10, panel2.getHeight()/6*2+20, panel2.getWidth()/6, 20);
		panel2.add(tendinaCittaeArrivoPannello2);
		
		
		campoCittaArrivoPannello2 = new JTextField (panel2.getWidth()/7);	 //Campo per stazione di arrivo
		campoCittaArrivoPannello2.setFont(new Font("Arial",0,18));
		campoCittaArrivoPannello2.setBounds(panel2.getWidth()/7*5-10, panel2.getHeight()/6*2+45, panel2.getWidth()/6, 20);
		campoCittaArrivoPannello2.setEditable(false);
		panel2.add(campoCittaArrivoPannello2);
		
		
		
		labelViaPannello2 = new JLabel();	//Etichetta per Stazioni Intermedie
		labelViaPannello2.setFont(new Font("Arial",0,15));
		labelViaPannello2.setBounds(panel2.getWidth()/7-10, panel2.getHeight()/6*3, panel2.getWidth()/6, 20);
		labelViaPannello2.setText("Via");
		panel2.add(labelViaPannello2);
		
		
		
	    campoViaPannello2 = new JTextField (panel2.getWidth()/6); //Campo per stazioni intermedie
	    campoViaPannello2.setFont(new Font("Arial", 0, 18));
	    campoViaPannello2.setBounds(panel2.getWidth()/7-10, panel2.getHeight()/6*3+20, panel2.getWidth()/6, 20);
	    campoViaPannello2.setEditable(false);
	    panel2.add(campoViaPannello2);
	    
	    
	    
	    labelInfoPannello2 = new JLabel();			//Etichetta per le info
	    labelInfoPannello2.setFont(new Font("Arial", 0, 15));
	    labelInfoPannello2.setBounds(panel2.getWidth()/7*3-10, panel2.getHeight()/6*3, panel2.getWidth()/6, 20);
	    labelInfoPannello2.setText("Info");
	    panel2.add(labelInfoPannello2);
	    
	    
	    
	    campoInfoPannello2 = new JTextField(panel2.getWidth()/7*3);			//Campo per le info
	    campoInfoPannello2.setFont(new Font("Arial", 0, 18));
	    campoInfoPannello2.setBounds(panel2.getWidth()/7*3-10, panel2.getHeight()/6*3+20, panel2.getWidth()/7*3, 20);
	    campoInfoPannello2.setEditable(false);
	    panel2.add(campoInfoPannello2);
	    
	    
		
		bottoneSvuotaPannello2 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello2.setBackground(Color.YELLOW);
		bottoneSvuotaPannello2.setBounds(panel2.getWidth()/5-30, panel2.getHeight()/6*4, panel2.getWidth()/5+10, panel2.getHeight()/14);
		panel2.add(bottoneSvuotaPannello2);
		
		bottoneAggiungi = new JButton("AGGIUNGI AL CATALOGO");
		bottoneAggiungi.setBackground(Color.ORANGE);
		bottoneAggiungi.setBounds(panel2.getWidth()/5*3+16, panel2.getHeight()/6*4, panel2.getWidth()/5+10, panel2.getHeight()/14);
		panel2.add(bottoneAggiungi);
		
		bottoneChiudiPannello2 = new JButton("X");
		bottoneChiudiPannello2.setBackground(Color.RED);
		bottoneChiudiPannello2.setBounds(panel2.getWidth()/20*19-10, 0, panel2.getWidth()/20, panel2.getHeight()/18);
		panel2.add(bottoneChiudiPannello2);
		
		
		//Ascoltatori pannello 2
		
		ascoltatoreRadioButtonAriaPannello2  		= new SelezionaViaAriaPannello2AA();
		ascoltatoreRadioButtonMarePannello2  		= new SelezionaViaMarePannello2AA();
		ascoltatoreRadioButtonTerraPannello2 		= new SelezionaViaTerraPannello2AA();
		ascoltatoreBottoneChiudiPannello2    		= new ChiudiPannello2AA();
		ascoltatoreBottoneAggiungi			 		= new AggiungiAA();
		ascoltatoreTendinaMezziPannello2	 		= new TendinaMezziPannello2AA();
		ascoltatoreTendinaCittaPartenzaPannello2 = new TendinaCittaPartenzaPannello2AA();
		ascoltatoreTendinaCittaArrivoPannello2    = new TendinaCittaArrivoPannello2AA();
		ascoltatoreBottoneSvuotaPannello2			= new SvuotaPannello2AA();
		
		
		
		radioAriaPannello2.addActionListener(ascoltatoreRadioButtonAriaPannello2);
		radioMarePannello2.addActionListener(ascoltatoreRadioButtonMarePannello2);
		radioTerraPannello2.addActionListener(ascoltatoreRadioButtonTerraPannello2);
		bottoneAggiungi.addActionListener(ascoltatoreBottoneAggiungi);
		bottoneChiudiPannello2.addActionListener(ascoltatoreBottoneChiudiPannello2);
		tendinaMezziPannello2.addActionListener(ascoltatoreTendinaMezziPannello2);
		tendinaCittaPartenzaPannello2.addActionListener(ascoltatoreTendinaCittaPartenzaPannello2);
		tendinaCittaeArrivoPannello2.addActionListener(ascoltatoreTendinaCittaArrivoPannello2);
		bottoneSvuotaPannello2.addActionListener(ascoltatoreBottoneSvuotaPannello2);
		
		
		
		/*
		 * 
		 * terzo pannello: questo pannello si attiva premendo il bottone RIMUOVI VIAGGIO del primo pannello
		 * 
		 */
		
		
		panel3 = new JPanel();
		panel3.setSize(superPanel.getWidth(), superPanel.getHeight()/8*7);
		panel3.setLocation(0, superPanel.getHeight()/8);	
		panel3.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel3);
		panel3.setVisible(false); 			//deve essere invisibile all'inizio, attivato solo dal bottone
		
		
		labelTitoloPannello3 = new JLabel();	
		labelTitoloPannello3.setFont(new Font("Arial", 0, 20));
		labelTitoloPannello3.setBounds(panel3.getWidth()/3, panel3.getHeight()/200, panel3.getWidth()/3, panel3.getHeight()/7);
		labelTitoloPannello3.setVerticalAlignment(JLabel.CENTER);
		labelTitoloPannello3.setHorizontalAlignment(JLabel.CENTER);
		labelTitoloPannello3.setText("RIMUOVI VIAGGIO");
		panel3.add(labelTitoloPannello3);
		
		areaTestoPannello3 = new JTextArea();
		areaTestoPannello3 = new JTextArea(panel3.getWidth()/40*38, panel3.getHeight()/6*3);
		areaTestoPannello3.setFont(new Font("Arial", 0, 15));
		areaTestoPannello3.setEditable(false);
		areaTestoPannello3.setLineWrap(false);
		scrollAreaTestoPannello3 = new JScrollPane(areaTestoPannello3);   //creo un piccolo scroll e lo aggiungo alla text area
		scrollAreaTestoPannello3.setBounds(panel3.getWidth()/40, panel3.getHeight()/7, panel3.getWidth()/40*38, panel3.getHeight()/6*3);
		panel3.add(scrollAreaTestoPannello3);
		
		
		
		labelAmbientePannello3 = new JLabel();        //Etichetta per i mezzi
		labelAmbientePannello3.setFont(new Font("Arial", 0, 15));
		labelAmbientePannello3.setBounds(panel2.getWidth()/11-35, panel3.getHeight()/6*4, panel3.getWidth()/6, 20);
		labelAmbientePannello3.setText("Ambiente");
		panel3.add(labelAmbientePannello3);
		
		
		tendinaAmbientePannello3 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaAmbientePannello3.setBackground(Color.WHITE);
		tendinaAmbientePannello3.setEnabled(false);
		tendinaAmbientePannello3.setBounds(panel3.getWidth()/11-35, panel3.getHeight()/6*4+20, panel3.getWidth()/6, 20);
		panel3.add(tendinaAmbientePannello3);

		
		
		
		labelMezziPannello3 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello3.setFont(new Font("Arial", 0, 15));
		labelMezziPannello3.setBounds(panel3.getWidth()/11*3-35, panel3.getHeight()/6*4, panel3.getWidth()/6, 20);
		labelMezziPannello3.setText("Mezzo di Trasporto");
		panel3.add(labelMezziPannello3);
		
		
		tendinaMezziPannello3 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello3.setBackground(Color.WHITE);
		tendinaMezziPannello3.setEnabled(false);
		tendinaMezziPannello3.setBounds(panel3.getWidth()/11*3-35, panel3.getHeight()/6*4+20, panel3.getWidth()/6, 20);
		panel3.add(tendinaMezziPannello3);
		
		
		labelCittaPartenzaPannello3 = new JLabel();        //Etichetta per Stazioni di partenza
		labelCittaPartenzaPannello3.setFont(new Font("Arial", 0, 15));
		labelCittaPartenzaPannello3.setBounds(panel3.getWidth()/11*5-35, panel3.getHeight()/6*4, panel3.getWidth()/6, 20);
		labelCittaPartenzaPannello3.setText("Citta' di Partenza");
		panel3.add(labelCittaPartenzaPannello3);
		
		
		tendinaCittaPartenzaPannello3 = new JComboBox<String>();	 //Tendina per stazioni di partenza
		tendinaCittaPartenzaPannello3.setBackground(Color.WHITE);
		tendinaCittaPartenzaPannello3.setBounds(panel3.getWidth()/11*5-35, panel3.getHeight()/6*4+20, panel3.getWidth()/6, 20);
		tendinaCittaPartenzaPannello3.setEnabled(false);
		panel3.add(tendinaCittaPartenzaPannello3);
		
		
		labelCittaArrivoPannello3 = new JLabel();        //Etichetta per Stazione di arrivo
		labelCittaArrivoPannello3.setFont(new Font("Arial", 0, 15));
		labelCittaArrivoPannello3.setBounds(panel3.getWidth()/11*7-35, panel3.getHeight()/6*4, panel3.getWidth()/6, 20);
		labelCittaArrivoPannello3.setText("Citta' di Arrivo");
		panel3.add(labelCittaArrivoPannello3);
		
		
		tendinaCittaArrivoPannello3 = new JComboBox<String>();	 //Tendina per stazioni di arrivo
		tendinaCittaArrivoPannello3.setBackground(Color.WHITE);
		tendinaCittaArrivoPannello3.setBounds(panel3.getWidth()/11*7-35, panel3.getHeight()/6*4+20, panel3.getWidth()/6, 20);
		tendinaCittaArrivoPannello3.setEnabled(false);
		panel3.add(tendinaCittaArrivoPannello3);
		
		
		labelViaPannello3 = new JLabel();	//Etichetta per Stazione intermedia
		labelViaPannello3.setFont(new Font("Arial", 0, 15));
		labelViaPannello3.setBounds(panel3.getWidth()/11*9-35, panel3.getHeight()/6*4, panel3.getWidth()/6, 20);
		labelViaPannello3.setText("Via");
		panel3.add(labelViaPannello3);
		
		
		tendinaViaPannello3 = new JComboBox<String>(); //Tendina per stazioni intermedie
		tendinaViaPannello3.setBackground(Color.WHITE);
		tendinaViaPannello3.setBounds(panel3.getWidth()/11*9-35, panel3.getHeight()/6*4+20, panel3.getWidth()/6, 20);
		tendinaViaPannello3.setEnabled(false);
		panel3.add(tendinaViaPannello3);
		
		bottoneSvuotaPannello3 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello3.setBackground(Color.YELLOW);
		bottoneSvuotaPannello3.setBounds(panel3.getWidth()/5-30, panel3.getHeight()/7*6-20, panel3.getWidth()/5+10, panel3.getHeight()/14);
		panel3.add(bottoneSvuotaPannello3);

		bottoneRimuovi = new JButton("RIMUOVI DAL CATALOGO");
		bottoneRimuovi.setBackground(Color.ORANGE);
		bottoneRimuovi.setBounds(panel3.getWidth()/5*3+16, panel3.getHeight()/7*6-20, panel3.getWidth()/5+10, panel3.getHeight()/14);
		panel3.add(bottoneRimuovi);
		
		
		
		bottoneChiudiPannello3 = new JButton("X");
		bottoneChiudiPannello3.setBackground(Color.RED);
		bottoneChiudiPannello3.setBounds(panel3.getWidth()/20*19-10, 0, panel3.getWidth()/20, panel3.getHeight()/18);
		panel3.add(bottoneChiudiPannello3);
		
		
		//Ascoltatori pannello 3
		
		ascoltatoreTendinaAmbientePannello3   = new TendinaAmbientePannello3AA();
		ascoltatoreTendinaMezziPannello3	  = new TendinaMezziPannello3AA();
		ascoltatoreTendinaPartenzePannello3   = new TendinaPartenzePannello3AA();
		ascoltatoreTendinaArriviPannello3     = new TendinaArriviPannello3AA();
		ascoltatoreTendinaViaPannello3 = new TendinaViaPannello3AA();
		ascoltatoreBottoneRimuovi			  = new RimuoviAA();
		ascoltatoreBottoneChiudiPannello3     = new ChiudiPannello3AA();
		ascoltatoreBottoneSvuotaPannello3 	  = new SvuotaPannello3AA();
		
		tendinaAmbientePannello3.addActionListener(ascoltatoreTendinaAmbientePannello3);
		tendinaMezziPannello3.addActionListener(ascoltatoreTendinaMezziPannello3);
		tendinaCittaPartenzaPannello3.addActionListener(ascoltatoreTendinaPartenzePannello3);
		tendinaCittaArrivoPannello3.addActionListener(ascoltatoreTendinaArriviPannello3);
		tendinaViaPannello3.addActionListener(ascoltatoreTendinaViaPannello3);
		bottoneRimuovi.addActionListener(ascoltatoreBottoneRimuovi);
		bottoneChiudiPannello3.addActionListener(ascoltatoreBottoneChiudiPannello3);
		bottoneSvuotaPannello3.addActionListener(ascoltatoreBottoneSvuotaPannello3);
		

		//Per finire carico il catalogo
		caricaCatalogo();
	}//FINE COSTRUTTORE
	
	
	// Aggiorna la tendina dei mezzi del primo pannello
	private void aggiornaTendinePannello2() {
		
		tendinaMezziPannello2.removeAllItems();    //rimuove tutti gli elementi dalla tendina
		tendinaCittaPartenzaPannello2.removeAllItems();
		tendinaCittaeArrivoPannello2.removeAllItems();

		
		try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
			Set<String> s = controllore.mostraMezziInCatalogo(ambienteScelto);
			Iterator<String> it = s.iterator();
			
			while(it.hasNext()) 					//itero l'insieme di chiavi
				tendinaMezziPannello2.addItem(it.next());  //ne aggiungo uno alla volta
			
		} catch (IDEsternoElementoException e) {
			System.out.println(e.getMessage()+"\n");
		}
		
		tendinaMezziPannello2.addItem("new..."); //per mettercene uno nuovo!!!
		campoCittaPartenzaPannello2.setText("");
		campoCittaArrivoPannello2.setText("");
		campoViaPannello2.setText("");
		campoInfoPannello2.setText("");
		
		tendinaMezziPannello2.setEnabled(true);
		campoViaPannello2.setEditable(true);  // attiva il campo "stazione intermedia"
		campoInfoPannello2.setEditable(true);
	}
	
	// Aggiorna la tendina degli ambienti del secondo pannello
	private void aggiornaTendinePannello3() { 
		
		tendinaAmbientePannello3.removeAllItems();
		tendinaAmbientePannello3.setEnabled(false);
							
		//imposto ambiente scelto
		ambienteScelto = "-----";
		
		try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
			Set<String> s = controllore.mostraAmbientiInCatalogo();
			Iterator<String> it = s.iterator();
			if (s.size() > 1){ //se c'e' piu' di un elemento visualizzo l'elemento neutro
				//devo aggiungere l'elemento vuoto
				tendinaAmbientePannello3.addItem("-----");
			}
				
			while(it.hasNext()){ 					//itero l'insieme di chiavi
				tendinaAmbientePannello3.addItem(it.next());  //ne aggiungo uno alla volta
			}
				
			tendinaAmbientePannello3.setEnabled(true);
			
			//visualizzo subito il catalogo	
			areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
			areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo);
			areaTestoPannello3.setCaretPosition(0);
					
		} catch (MappaException e) {
			areaTestoPannello3.setText(e.getMessage()+"\n");
		} catch (IDEsternoElementoException e) {
			areaTestoPannello3.setText(e.getMessage()+"\n");
		} catch (TrattaInesistenteException e) {
			areaTestoPannello3.setText(e.getMessage()+"\n");
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
		} catch (DeserializzazioneException e) {
			JOptionPane.showMessageDialog(null, "Non e' stato ancora salvato un catalogo.  Faccio partire il thread.");
		*/	avviaThread();
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
	
	private class AggiungiViaggioAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(true);  //attiva pannello 2
			
			bottoneAggiungiViaggio.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(false);
			bottoneChiudiPannello1.setEnabled(false);
		
		}
		
	}	
	
	private class RimuoviViaggioAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel3.setVisible(true);	//attiva pannello 3
			bottoneAggiungiViaggio.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(false);
			bottoneChiudiPannello1.setEnabled(false);
			
			//premendo il bottone, mi attiva subito la tendina ambiente implementata in aggiornaTendinaPannello3
			aggiornaTendinePannello3();
			

		}
		
	}
	
	private class ChiudiPannello1AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			superPanel.setVisible(false); 			    //chiude tutto questo pannello
			BoundaryPromotore.riattivaBottoni();      	//riattiva i bottoni
						
		}
	}
	

	
	/*
	 * Ascoltatori per elementi pannello 2
	 */
	
	
	private class SelezionaViaAriaPannello2AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Aria
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Aria";			//Cambia l'ambiente scelto
			aggiornaTendinePannello2();
		}
	
	}


	private class SelezionaViaMarePannello2AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Mare
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Mare"; 			//Cambia l'ambiente scelto
			aggiornaTendinePannello2();
		}
		
	}
	
	private class SelezionaViaTerraPannello2AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Terra
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Terra"; 			//Cambia l'ambiente scelto
			aggiornaTendinePannello2();
		}
		
	}
	
	private class TendinaMezziPannello2AA implements ActionListener{
		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			
			if (tendinaMezziPannello2.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaMezziPannello2.getSelectedItem().toString().equals("new...")){
					campoMezziPannello2.setEditable(true); //attiva il campo sotto per inserire un mezzo non ancora usato
					campoCittaPartenzaPannello2.setEditable(true);
					campoCittaArrivoPannello2.setEditable(true);
					tendinaCittaPartenzaPannello2.removeAllItems();
					tendinaCittaeArrivoPannello2.removeAllItems();
					tendinaCittaPartenzaPannello2.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di partenza sara' nuova, la tendina non serve!
					tendinaCittaeArrivoPannello2.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di arrivo sara' nuova, la tendina non serve!
				} else {
					campoMezziPannello2.setText("");
					campoMezziPannello2.setEditable(false);
					campoCittaPartenzaPannello2.setText("");
					campoCittaPartenzaPannello2.setEditable(false);
					campoCittaArrivoPannello2.setText("");
					campoCittaArrivoPannello2.setEditable(false);
					

		
					String mezzo = (String)tendinaMezziPannello2.getSelectedItem();
					
					tendinaCittaPartenzaPannello2.setEnabled(true);
					tendinaCittaPartenzaPannello2.removeAllItems();
					
					try {
						Set<String> set = controllore.mostraCittaDiPartenzaInCatalogo(ambienteScelto,mezzo);
						Iterator<String> it = set.iterator();
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaPartenzaPannello2.addItem(it.next());
						}
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 

					tendinaCittaPartenzaPannello2.addItem("new...");
					tendinaCittaPartenzaPannello2.setSelectedIndex(0);						
				}
				
			}	
			
		}
		
	}
	
	private class TendinaCittaPartenzaPannello2AA implements ActionListener{
		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (tendinaCittaPartenzaPannello2.getItemCount() != 0) {//ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaCittaPartenzaPannello2.getSelectedItem().toString().equals("new...")){
					campoCittaPartenzaPannello2.setEditable(true); //attiva il campo sotto per inserire un stazione di partenza non ancora usata
					campoCittaArrivoPannello2.setEditable(true);
					tendinaCittaeArrivoPannello2.removeAllItems();
					tendinaCittaeArrivoPannello2.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di arrivo sara' nuova, la tendina non serve!
				} else {
					campoCittaPartenzaPannello2.setText("");
					campoCittaPartenzaPannello2.setEditable(false);
					campoCittaArrivoPannello2.setText("");
					campoCittaArrivoPannello2.setEditable(false);
					
					String mezzo = (String) tendinaMezziPannello2.getSelectedItem();
					String partenza = (String) tendinaCittaPartenzaPannello2.getSelectedItem();
					
					tendinaCittaeArrivoPannello2.setEnabled(true);
					tendinaCittaeArrivoPannello2.removeAllItems();
					
					
		
					try {
						
						Set<String> set = controllore.mostraCittaDiArrivoInCatalogo(ambienteScelto, mezzo, partenza);
						Iterator<String> it = set.iterator();
						while (it.hasNext()){
								tendinaCittaeArrivoPannello2.addItem(it.next());
						}
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					tendinaCittaeArrivoPannello2.addItem("new...");
					tendinaCittaeArrivoPannello2.setSelectedIndex(0);
					
				}	
				
			}
			
			
		}
		
		
	}
	
	
	private class TendinaCittaArrivoPannello2AA implements ActionListener{

		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (tendinaCittaeArrivoPannello2.getItemCount() != 0) {//ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaCittaeArrivoPannello2.getSelectedItem().toString().equals("new...")){
					campoCittaArrivoPannello2.setEditable(true); //Attiva il campo sotto per inserire una stazione di arrivo non ancora usata
				} else {
					campoCittaArrivoPannello2.setText("");
					campoCittaArrivoPannello2.setEditable(false);
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
				if (tendinaMezziPannello2.getSelectedItem().toString().equals("new...")){
					mezzoTrasporto = uppercaseFirstLetters(campoMezziPannello2.getText());
				} else {
					mezzoTrasporto = (String) tendinaMezziPannello2.getSelectedItem();
				}
			
				if (!tendinaCittaPartenzaPannello2.isEnabled() || tendinaCittaPartenzaPannello2.getSelectedItem().toString().equals("new...")){
					cittaPartenza = uppercaseFirstLetters(campoCittaPartenzaPannello2.getText());
				} else {
					cittaPartenza = (String) tendinaCittaPartenzaPannello2.getSelectedItem();
				}
				
				if (!tendinaCittaeArrivoPannello2.isEnabled() || tendinaCittaeArrivoPannello2.getSelectedItem().toString().equals("new...")){
					cittaArrivo = uppercaseFirstLetters(campoCittaArrivoPannello2.getText());
				} else {
					cittaArrivo = (String) tendinaCittaeArrivoPannello2.getSelectedItem();
				}
			
				via = uppercaseFirstLetters(campoViaPannello2.getText());
				
				info = uppercaseFirstLetters(campoInfoPannello2.getText()); 
				
						
				//aggiungo il viaggio
				try {
					controlloSintatticoDati(mezzoTrasporto, cittaPartenza, cittaArrivo, via);
					
					int conferma = JOptionPane.showConfirmDialog(null, "Aggiungere il viaggio nel catalogo?", "Conferma Nuovo Viaggio in Catalogo", JOptionPane.YES_NO_OPTION);
					
					if (conferma == JOptionPane.YES_OPTION){	
						controllore.aggiungiViaggio(ambienteScelto, mezzoTrasporto, cittaPartenza, cittaArrivo, via, info);
						JOptionPane.showMessageDialog(null, "Il nuovo viaggio e' stato aggiunto correttamente nel catalogo.", "Viaggio Aggiunto", JOptionPane.INFORMATION_MESSAGE);
						aggiornaTendinePannello2(); //aggiorno le tendine
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
	
	
	
	private class SvuotaPannello2AA implements ActionListener{
		/*
		 * Svuota tutti i campi
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = null;
			
			
			gruppoAmbientePannello2.clearSelection();   //toglie la spunta ai radio bottoni
			
			tendinaMezziPannello2.removeAllItems();  //svuota le tendine
			tendinaCittaPartenzaPannello2.removeAllItems();
			tendinaCittaeArrivoPannello2.removeAllItems();
			
			campoMezziPannello2.setText("");		//svuota tutti i campi testo
			campoCittaPartenzaPannello2.setText("");
			campoCittaArrivoPannello2.setText("");
			campoViaPannello2.setText("");
			campoInfoPannello2.setText("");
			
			campoMezziPannello2.setEditable(false);
			campoCittaPartenzaPannello2.setEditable(false);
			tendinaMezziPannello2.setEnabled(false);
			tendinaCittaPartenzaPannello2.setEnabled(false);
			tendinaCittaeArrivoPannello2.setEnabled(false);
			campoCittaArrivoPannello2.setEditable(false);
			campoViaPannello2.setEditable(false);
			campoInfoPannello2.setEditable(false);
		}
		
	}
	
	private class ChiudiPannello2AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel2.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiViaggio.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(true);
			bottoneChiudiPannello1.setEnabled(true);
			
			
			//svuoto cmq il pannello
			gruppoAmbientePannello2.clearSelection();  //toglie la spunta ai radio bottoni
			ambienteScelto = null;
			
			tendinaMezziPannello2.removeAllItems();  //svuota le tendine
			tendinaCittaPartenzaPannello2.removeAllItems();
			tendinaCittaeArrivoPannello2.removeAllItems();
			
			tendinaMezziPannello2.setEnabled(false);
			tendinaCittaPartenzaPannello2.setEnabled(false);
			tendinaCittaeArrivoPannello2.setEnabled(false);
			
			campoMezziPannello2.setText("");		//svuota tutti i campi testo
			campoCittaPartenzaPannello2.setText("");
			campoCittaArrivoPannello2.setText("");
			campoViaPannello2.setText("");
			campoInfoPannello2.setText("");
			
			campoMezziPannello2.setEditable(false);
			campoCittaPartenzaPannello2.setEditable(false);
			campoCittaArrivoPannello2.setEditable(false);
			campoViaPannello2.setEditable(false);
			campoInfoPannello2.setEditable(false);
		}
	}

	
	
	
	/*
	 * Ascoltatori per elementi pannello 3
	 */
	
	
	private class TendinaAmbientePannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaMezziPannello3.removeAllItems();
			tendinaMezziPannello3.setEnabled(false);
			
			//prendo il valore di questa tendina
			ambienteScelto	= (String)tendinaAmbientePannello3.getSelectedItem();
			
			if (tendinaAmbientePannello3.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
			
					
					if(!ambienteScelto.equals("-----")){ //Solo se non e' l'elemento neutro
							
						try {  //cerca nella mappa tutte le chiavi da aggiungere in tendina
							Set<String> s = controllore.mostraMezziInCatalogo(ambienteScelto);
							Iterator<String> it = s.iterator();
							
							if (s.size() > 1){
								//inserisco l'elemento neutro
								tendinaMezziPannello3.addItem("-----");
							}
							
							while(it.hasNext()){ 					//itero l'insieme di chiavi
								tendinaMezziPannello3.addItem(it.next());  //ne aggiungo uno alla volta
							}
							tendinaMezziPannello3.setEnabled(true);
							
						} catch (IDEsternoElementoException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					//Aggiorno l'area testo che mostra il catalogo
					try {
						
						areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
						areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo);
						areaTestoPannello3.setCaretPosition(0);
						
					} catch (MappaException e1) {
						areaTestoPannello3.setText(e1.getMessage()+"\n");
					} catch (IDEsternoElementoException e1) {
						areaTestoPannello3.setText(e1.getMessage()+"\n");
					} catch (TrattaInesistenteException e1) {
						areaTestoPannello3.setText(e1.getMessage()+"\n");
					}
	
			}
		
		}
		
	}
	
	
	private class TendinaMezziPannello3AA implements ActionListener{
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaCittaPartenzaPannello3.removeAllItems();
			tendinaCittaPartenzaPannello3.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello3.getSelectedItem();   //Neanche servirebbe, in teoria...
			mezzoScelto = (String)tendinaMezziPannello3.getSelectedItem();
			
			if (tendinaMezziPannello3.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
	
				if(!mezzoScelto.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraCittaDiPartenzaInCatalogo(ambienteScelto, mezzoScelto);
						Iterator<String> it = s.iterator();
						
						if (s.size() > 1){
							//inserisco l'elemento neutro
							tendinaCittaPartenzaPannello3.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaPartenzaPannello3.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaCittaPartenzaPannello3.setEnabled(true);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}   
				}
				
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo);		
					areaTestoPannello3.setCaretPosition(0);
				
				} catch (MappaException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (TrattaInesistenteException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				}
						
	
			}
		
		}
		
	}
	
	private class TendinaPartenzePannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaCittaArrivoPannello3.removeAllItems();
			tendinaCittaArrivoPannello3.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello3.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello3.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello3.getSelectedItem();
			
			if (tendinaCittaPartenzaPannello3.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if(!partenzaScelta.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraCittaDiArrivoInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta);
						Iterator<String> it = s.iterator();
						
						if(s.size() > 1){
							//inserisco l'elemento neutro
							tendinaCittaArrivoPannello3.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaArrivoPannello3.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaCittaArrivoPannello3.setEnabled(true);
						tendinaCittaArrivoPannello3.setSelectedIndex(0);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
	
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello3.setCaretPosition(0);
					
				} catch (MappaException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (TrattaInesistenteException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				}
			
			}
			
		}
		
	}
	
	private class TendinaArriviPannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaViaPannello3.removeAllItems();
			tendinaViaPannello3.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello3.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello3.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello3.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello3.getSelectedItem();
			
			if (tendinaCittaArrivoPannello3.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if(!arrivoScelto.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraViaInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
						Iterator<String> it = s.iterator();
						
						if(s.size() > 1){
							//inserisco l'elemento neutro
							tendinaViaPannello3.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaViaPannello3.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaViaPannello3.setEnabled(true);
						tendinaViaPannello3.setSelectedIndex(0);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
	
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello3.setCaretPosition(0);
					
				} catch (MappaException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");	
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (TrattaInesistenteException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				}
			
			}
			
			ambienteScelto = (String) tendinaAmbientePannello3.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello3.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello3.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello3.getSelectedItem();
			
			if (tendinaCittaArrivoPannello3.getItemCount() != 0) {
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello3.setCaretPosition(0);
				} catch (MappaException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (TrattaInesistenteException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				}
				
			}
			
			
		}
		
	}
	
	
	private class TendinaViaPannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = (String) tendinaAmbientePannello3.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello3.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello3.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello3.getSelectedItem();
			viaScelta = (String) tendinaViaPannello3.getSelectedItem();
			
			if (tendinaViaPannello3.getItemCount() != 0) {
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello3.setCaretPosition(0);
				} catch (MappaException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (TrattaInesistenteException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				}
				
			}
			
		}
		
	}
	
	
	private class RimuoviAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (tendinaViaPannello3.getItemCount() != 0 && !viaScelta.equals("-----")){
				
				String via = (String) tendinaViaPannello3.getSelectedItem();
				
				// chiedo conferma
				int conferma = JOptionPane.showConfirmDialog(null, "Rimuovere il viaggio dal catalogo?", "Conferma Rimozione Viaggio", JOptionPane.YES_NO_OPTION);
				if (conferma == JOptionPane.YES_OPTION){
					
					// rimuovo il viaggio
					try {
						controllore.rimuoviViaggio(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, via);
						JOptionPane.showMessageDialog(null, "Il viaggio e' stato rimosso correttamente dal catalogo.", "Viaggio Rimosso", JOptionPane.INFORMATION_MESSAGE);
						//aggiorno tutti i campi dopo aver rimosso il viaggio
						aggiornaTendinePannello3();
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} catch (TrattaInesistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} catch (OffertaException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Attenzione!", JOptionPane.WARNING_MESSAGE);
					}
					
				}
					
			} else {
				JOptionPane.showMessageDialog(null, "Nessun viaggio selezionato!");
			}
			
		}
		
	}
	
	private class SvuotaPannello3AA implements ActionListener{
		/*
		 * Svuota tutti i campi
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (tendinaAmbientePannello3.isEnabled()){
								
				tendinaAmbientePannello3.setSelectedIndex(0); //la tendina torna al primo valore.
				
				if (tendinaAmbientePannello3.getItemCount()>1){
					tendinaMezziPannello3.removeAllItems();  //svuota le tendine
					tendinaMezziPannello3.setEnabled(false);//disattiva tutte le tendine

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
	
	private class ChiudiPannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel3.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiViaggio.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(true);
			bottoneChiudiPannello1.setEnabled(true);
			
			//svuoto comunque il pannello
			if (tendinaAmbientePannello3.getItemCount() != 0){
				
				tendinaAmbientePannello3.setSelectedIndex(0); //la tendina torna al primo valore.
				tendinaMezziPannello3.removeAllItems();  //svuota le tendine
				tendinaMezziPannello3.setEnabled(false);//disattiva tutte le tendine

			}
			
			ambienteScelto = null;
			mezzoScelto = null;
			partenzaScelta = null;
			arrivoScelto = null;
			viaScelta = null;

		}
	}
		
}