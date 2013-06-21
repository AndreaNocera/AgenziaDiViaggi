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
import gestione_Catalogo.exception.IDEsternoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaException;
import gestione_Catalogo.exception.SerializzazioneException;






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
	private String areaTestoCatalogo;
	private String areaTestoImp;

	//Pannelli
	private JPanel superPanel;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;

	
	//Elementi pannello1
	private JLabel labelTitolo;
	
	private JButton bottoneIndietro;
	private JButton bottoneEsci;
	
	private IndietroAA ascoltatoreBottoneIndietro;
	private EsciAA ascoltatoreBottoneEsci;
	
	//Elementi pannello2
	private JButton bottoneAggiungiViaggio;
	private JButton bottoneRimuoviViaggio;
    
    private AggiungiViaggioAA ascoltatoreBottoneAggiungiViaggio;
    private RimuoviViaggioAA ascoltatoreBottoneRimuoviViaggio;

    
    //Elementi pannello3
    private JLabel	labelTitoloPannello3;
    
	private JRadioButton radioMarePannello3;
	private JRadioButton radioTerraPannello3;
	private JRadioButton radioAriaPannello3;

	private ButtonGroup gruppoAmbientePannello3;

	private JLabel labelMezziPannello3;
	private JComboBox<String> tendinaMezziPannello3;
	private JTextField campoMezziPannello3;

	private JLabel labelStazionePartenzaPannello3;
	private JComboBox<String> tendinaStazionePartenzaPannello3;
	private JTextField campoStazionePartenzaPannello3;

	private JLabel labelStazioneArrivoPannello3;
	private JComboBox<String> tendinaStazioneArrivoPannello3;
	private JTextField campoStazioneArrivoPannello3;
	
	private JLabel labelStazioniIntermediePannello3;
	private JTextField campoStazioniIntermediePannello3;

	private JButton bottoneAggiungi;
	private JButton bottoneSvuotaPannello3;
	
	private JButton bottoneChiudiPannello3;

	private SelezionaViaAriaPannello3AA ascoltatoreRadioButtonAriaPannello3;
	private SelezionaViaMarePannello3AA ascoltatoreRadioButtonMarePannello3;
	private SelezionaViaTerraPannello3AA ascoltatoreRadioButtonTerraPannello3;
	private ChiudiPannello3AA ascoltatoreBottoneChiudiPannello3;
	private AggiungiAA ascoltatoreBottoneAggiungi;
	private TendinaMezziPannello3AA ascoltatoreTendinaMezziPannello3;
	private TendinaStazionePartenzaPannello3AA ascoltatoreTendinaStazionePartenzaPannello3;
	private TendinaStazioneArrivoPannello3AA ascoltatoreTendinaStazioneArrivoPannello3;
	private SvuotaPannello3AA ascoltatoreBottoneSvuotaPannello3;

	
	//Elementi pannello4
	
	private JLabel	labelTitoloPannello4;

	private JLabel labelAmbientePannello4;
	private JComboBox<String> tendinaAmbientePannello4;

	private JTextArea areaTestoPannello4;
	private JScrollPane scrollAreaTestoPannello4;
	
	private JLabel labelMezziPannello4;
	private JComboBox<String> tendinaMezziPannello4;

	private JLabel labelStazionePartenzaPannello4;
	private JComboBox<String> tendinaStazionePartenzaPannello4;

	private JLabel labelStazioneArrivoPannello4;
	private JComboBox<String> tendinaStazioneArrivoPannello4;
	
	private JLabel labelStazioneIntermediaPannello4;
	private JComboBox<String> tendinaStazioneIntermediaPannello4;

	private JButton bottoneRimuovi;
	private JButton bottoneSvuotaPannello4;
	
	private JButton bottoneChiudiPannello4;
	
	private TendinaAmbientePannello4AA ascoltatoreTendinaAmbientePannello4;
	private TendinaMezziPannello4AA ascoltatoreTendinaMezziPannello4;
	private TendinaPartenzePannello4AA ascoltatoreTendinaPartenzePannello4;
	private TendinaArriviPannello4AA ascoltatoreTendinaArriviPannello4;
	private ChiudiPannello4AA ascoltatoreBottoneChiudiPannello4;
	private RimuoviAA ascoltatoreBottoneRimuovi;
	private SvuotaPannello4AA ascoltatoreBottoneSvuotaPannello4;
	private JLabel labelInfoPannello3;
	private JTextField campoInfoPannello3;
	


		
	/*
	 * Costruttore
	 */
	
	public BoundaryPromotore_GestioneCatalogo(){
		
		ambienteScelto = null;
		mezzoScelto = null;
		partenzaScelta = null;
		arrivoScelto = null;
		areaTestoCatalogo = null;
		controllore = new ControlloreGestioneCatalogo();
		
		areaTestoImp = "AMBIENTE" + "\t\t" + "MEZZO" + "\t\t" + "PARTENZA" + "\t\t" + "ARRIVO" + "\t\t" + "INFO\n" +
		   "--------------" + "\t\t" + "----------" + "\t\t" + "----------------" + "\t\t" + "------------" + "\t\t" + "--------" + "\n";
		
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
		panel1.setSize(superPanel.getWidth(), superPanel.getHeight()/8-10);  //Il meno 10 serve per far vedere il contorno 
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
		 * secondo pannello: per i bottoni
		 */

		panel2 = new JPanel();
		panel2.setSize(superPanel.getWidth(), superPanel.getHeight()/8-10); //Il meno 10 serve a far vedere il contorno
		panel2.setLocation(0, superPanel.getHeight()/8);			//x=0 e y=0 rispetto al superPanel
		panel2.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel2);				//aggiungo il primo pannello al superPannello
		
		
		bottoneAggiungiViaggio = new JButton("AGGIUNGI VIAGGIO");
		bottoneAggiungiViaggio.setBackground(Color.CYAN);
		bottoneAggiungiViaggio.setBounds(panel2.getWidth()/5, panel2.getHeight()/6, panel2.getWidth()/5, panel2.getHeight()/2);
		panel2.add(bottoneAggiungiViaggio);//aggiungo il bottone al secondo pannello
		
		
		bottoneRimuoviViaggio = new JButton("RIMUOVI VIAGGIO");
		bottoneRimuoviViaggio.setBackground(Color.YELLOW);
		bottoneRimuoviViaggio.setBounds(panel2.getWidth()/5*3, panel2.getHeight()/6, panel2.getWidth()/5, panel2.getHeight()/2);
		panel2.add(bottoneRimuoviViaggio);//aggiungo il bottone al secondo pannello
		
		
		
		// ascoltatori per secondo pannello
		ascoltatoreBottoneAggiungiViaggio = new AggiungiViaggioAA(); 		//creo ascoltatore per bottone
		bottoneAggiungiViaggio.addActionListener(ascoltatoreBottoneAggiungiViaggio); 	//associo ascoltatore al bottone
		
		ascoltatoreBottoneRimuoviViaggio = new RimuoviViaggioAA();			//creo ascoltatore per bottone
		bottoneRimuoviViaggio.addActionListener(ascoltatoreBottoneRimuoviViaggio);		//associo ascoltatore al bottone
		
		
		/*
		 * terzo pannello: questo pannello si attiva premendo il bottone AGGIUNGI VIAGGIO
		 */
		
		
		panel3 = new JPanel();
		panel3.setSize(superPanel.getWidth(), superPanel.getHeight()/8*6);
		panel3.setLocation(0, superPanel.getHeight()/8*2);	
		panel3.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel3);
		panel3.setVisible(false); 			//deve essere invisibile all'inizio, attivato solo dal bottone
		
		labelTitoloPannello3 = new JLabel();	
		labelTitoloPannello3.setFont(new Font("Arial", 0, 20));
		labelTitoloPannello3.setBounds(panel3.getWidth()/3, panel3.getHeight()/49, panel3.getWidth()/3, panel3.getHeight()/7);
		labelTitoloPannello3.setVerticalAlignment(JLabel.CENTER);
		labelTitoloPannello3.setHorizontalAlignment(JLabel.CENTER);
		labelTitoloPannello3.setText("AGGIUNGI VIAGGIO");
		panel3.add(labelTitoloPannello3);
		
		
		radioAriaPannello3 = new JRadioButton("Via Aria");
		radioAriaPannello3.setBounds(panel3.getWidth()/7+100, panel3.getHeight()/7, panel3.getWidth()/7, panel3.getHeight()/14);
		panel3.add(radioAriaPannello3);   //aggiungo il radiobutton al pannello 3
		
		radioMarePannello3 = new JRadioButton("Via Mare");
		radioMarePannello3.setBounds(panel3.getWidth()/7*3, panel3.getHeight()/7, panel3.getWidth()/7, panel3.getHeight()/14);
		panel3.add(radioMarePannello3);
		
		radioTerraPannello3 = new JRadioButton("Via Terra");
		radioTerraPannello3.setBounds(panel3.getWidth()/7*5-100, panel3.getHeight()/7, panel3.getWidth()/7, panel3.getHeight()/14);
		panel3.add(radioTerraPannello3);
		
		gruppoAmbientePannello3 = new ButtonGroup();
		gruppoAmbientePannello3.add(radioAriaPannello3);
		gruppoAmbientePannello3.add(radioMarePannello3);
		gruppoAmbientePannello3.add(radioTerraPannello3);   //Ora solo uno di questi 3 e' selezionabile
		
		
		
		labelMezziPannello3 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello3.setFont(new Font("Arial", 0, 15));
		labelMezziPannello3.setBounds(panel3.getWidth()/7-10, panel3.getHeight()/7*2, panel3.getWidth()/6, 20);
		labelMezziPannello3.setText("Mezzo di Trasporto*");
		panel3.add(labelMezziPannello3);
		
		
		tendinaMezziPannello3 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello3.setBackground(Color.WHITE);
		tendinaMezziPannello3.setBounds(panel3.getWidth()/7-10, panel3.getHeight()/7*2+20, panel3.getWidth()/6, 20);
		tendinaMezziPannello3.setEnabled(false);
		panel3.add(tendinaMezziPannello3);
		
		campoMezziPannello3 = new JTextField(panel3.getWidth()/7);	  //campo per aggiungere dei nuovi mezzi
		campoMezziPannello3.setFont(new Font("Arial", 0, 18));
		campoMezziPannello3.setBounds(panel3.getWidth()/7-10, panel3.getHeight()/7*2+45, panel3.getWidth()/6, 20);
		campoMezziPannello3.setEditable(false);   				// all'inizio e' disattivato, si attiva solo con new...
		panel3.add(campoMezziPannello3);
		
		
		labelStazionePartenzaPannello3 = new JLabel();        //Etichetta per Stazione di partenza
		labelStazionePartenzaPannello3.setFont(new Font("Arial", 0, 15));
		labelStazionePartenzaPannello3.setBounds(panel3.getWidth()/7*3-10, panel3.getHeight()/7*2, panel3.getWidth()/6, 20);
		labelStazionePartenzaPannello3.setText("Stazione di Partenza*");
		panel3.add(labelStazionePartenzaPannello3);
		
		
		tendinaStazionePartenzaPannello3 = new JComboBox<String>();	  //Tendina per le partenze
		tendinaStazionePartenzaPannello3.setBackground(Color.WHITE);
		tendinaStazionePartenzaPannello3.setEnabled(false);
		tendinaStazionePartenzaPannello3.setBounds(panel3.getWidth()/7*3-10, panel3.getHeight()/7*2+20, panel3.getWidth()/6, 20);
		panel3.add(tendinaStazionePartenzaPannello3);
		
		
		campoStazionePartenzaPannello3 = new JTextField (panel3.getWidth()/7);	 //Campo per stazione di partenza
		campoStazionePartenzaPannello3.setFont(new Font("Arial",0,18));
		campoStazionePartenzaPannello3.setBounds(panel3.getWidth()/7*3-10, panel3.getHeight()/7*2+45, panel3.getWidth()/6, 20);
		campoStazionePartenzaPannello3.setEditable(false);
		panel3.add(campoStazionePartenzaPannello3);
		
		
		labelStazioneArrivoPannello3 = new JLabel();        //Etichetta per Stazione di arrivo
		labelStazioneArrivoPannello3.setFont(new Font("Arial", 0, 15));
		labelStazioneArrivoPannello3.setBounds(panel3.getWidth()/7*5-10, panel3.getHeight()/7*2, panel3.getWidth()/6, 20);
		labelStazioneArrivoPannello3.setText("Stazione di Arrivo*");
		panel3.add(labelStazioneArrivoPannello3);
		
		tendinaStazioneArrivoPannello3 = new JComboBox<String>();     // Tendina per gli arrivi
		tendinaStazioneArrivoPannello3.setBackground(Color.WHITE);
		tendinaStazioneArrivoPannello3.setEnabled(false);
		tendinaStazioneArrivoPannello3.setBounds(panel3.getWidth()/7*5-10, panel3.getHeight()/7*2+20, panel3.getWidth()/6, 20);
		panel3.add(tendinaStazioneArrivoPannello3);
		
		
		campoStazioneArrivoPannello3 = new JTextField (panel3.getWidth()/7);	 //Campo per stazione di arrivo
		campoStazioneArrivoPannello3.setFont(new Font("Arial",0,18));
		campoStazioneArrivoPannello3.setBounds(panel3.getWidth()/7*5-10, panel3.getHeight()/7*2+45, panel3.getWidth()/6, 20);
		campoStazioneArrivoPannello3.setEditable(false);
		panel3.add(campoStazioneArrivoPannello3);
		
		
		
		labelStazioniIntermediePannello3 = new JLabel();	//Etichetta per Stazioni Intermedie
		labelStazioniIntermediePannello3.setFont(new Font("Arial",0,15));
		labelStazioniIntermediePannello3.setBounds(panel3.getWidth()/7, panel3.getHeight()/7*3+20, panel3.getWidth()/6, 20);
		labelStazioniIntermediePannello3.setText("Stazioni Intermedie");
		panel3.add(labelStazioniIntermediePannello3);
		
		
		
	    campoStazioniIntermediePannello3 = new JTextField (panel3.getWidth()/7*5); //Campo per stazioni intermedie
	    campoStazioniIntermediePannello3.setFont(new Font("Arial", 0, 18));
	    campoStazioniIntermediePannello3.setBounds(panel3.getWidth()/7, panel3.getHeight()/7*3+40, panel3.getWidth()/7*5, 20);
	    campoStazioniIntermediePannello3.setEditable(false);
	    panel3.add(campoStazioniIntermediePannello3);
	    
	    
	    
	    labelInfoPannello3 = new JLabel();			//Etichetta per le info
	    labelInfoPannello3.setFont(new Font("Arial", 0, 15));
	    labelInfoPannello3.setBounds(panel3.getWidth()/7, panel3.getHeight()/7*4, panel3.getWidth()/6, 20);
	    labelInfoPannello3.setText("Info");
	    panel3.add(labelInfoPannello3);
	    
	    
	    
	    campoInfoPannello3 = new JTextField(panel3.getWidth()/7*5);			//Campo per le info
	    campoInfoPannello3.setFont(new Font("Arial", 0, 18));
	    campoInfoPannello3.setBounds(panel3.getWidth()/7, panel3.getHeight()/7*4+20, panel3.getWidth()/7*5, 20);
	    campoInfoPannello3.setEditable(false);
	    panel3.add(campoInfoPannello3);
	    
	    
		
		bottoneSvuotaPannello3 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello3.setBackground(Color.YELLOW);
		bottoneSvuotaPannello3.setBounds(panel3.getWidth()/5-30, panel3.getHeight()/7*5, panel3.getWidth()/5+10, panel3.getHeight()/14);
		panel3.add(bottoneSvuotaPannello3);
		
		bottoneAggiungi = new JButton("AGGIUNGI AL CATALOGO");
		bottoneAggiungi.setBackground(Color.ORANGE);
		bottoneAggiungi.setBounds(panel3.getWidth()/5*3+16, panel3.getHeight()/7*5, panel3.getWidth()/5+10, panel3.getHeight()/14);
		panel3.add(bottoneAggiungi);
		
		bottoneChiudiPannello3 = new JButton("X");
		bottoneChiudiPannello3.setBackground(Color.RED);
		bottoneChiudiPannello3.setBounds(panel3.getWidth()/20*19, 0, panel3.getWidth()/20, panel3.getHeight()/18);
		panel3.add(bottoneChiudiPannello3);
		
		
		//Ascoltatori pannello 3
		
		ascoltatoreRadioButtonAriaPannello3  		= new SelezionaViaAriaPannello3AA();
		ascoltatoreRadioButtonMarePannello3  		= new SelezionaViaMarePannello3AA();
		ascoltatoreRadioButtonTerraPannello3 		= new SelezionaViaTerraPannello3AA();
		ascoltatoreBottoneChiudiPannello3    		= new ChiudiPannello3AA();
		ascoltatoreBottoneAggiungi			 		= new AggiungiAA();
		ascoltatoreTendinaMezziPannello3	 		= new TendinaMezziPannello3AA();
		ascoltatoreTendinaStazionePartenzaPannello3 = new TendinaStazionePartenzaPannello3AA();
		ascoltatoreTendinaStazioneArrivoPannello3    = new TendinaStazioneArrivoPannello3AA();
		ascoltatoreBottoneSvuotaPannello3			= new SvuotaPannello3AA();
		
		
		
		radioAriaPannello3.addActionListener(ascoltatoreRadioButtonAriaPannello3);
		radioMarePannello3.addActionListener(ascoltatoreRadioButtonMarePannello3);
		radioTerraPannello3.addActionListener(ascoltatoreRadioButtonTerraPannello3);
		bottoneAggiungi.addActionListener(ascoltatoreBottoneAggiungi);
		bottoneChiudiPannello3.addActionListener(ascoltatoreBottoneChiudiPannello3);
		tendinaMezziPannello3.addActionListener(ascoltatoreTendinaMezziPannello3);
		tendinaStazionePartenzaPannello3.addActionListener(ascoltatoreTendinaStazionePartenzaPannello3);
		tendinaStazioneArrivoPannello3.addActionListener(ascoltatoreTendinaStazioneArrivoPannello3);
		bottoneSvuotaPannello3.addActionListener(ascoltatoreBottoneSvuotaPannello3);
		
		
		
		/*
		 * quarto pannello: questo pannello si attiva premendo il bottone RIMUOVI VIAGGIO
		 */
		
		
		panel4 = new JPanel();
		panel4.setSize(superPanel.getWidth(), superPanel.getHeight()/8*6);
		panel4.setLocation(0, superPanel.getHeight()/8*2);	
		panel4.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel4);
		panel4.setVisible(false); 			//deve essere invisibile all'inizio, attivato solo dal bottone
		
		
		labelTitoloPannello4 = new JLabel();	
		labelTitoloPannello4.setFont(new Font("Arial", 0, 20));
		labelTitoloPannello4.setBounds(panel4.getWidth()/3, panel4.getHeight()/200, panel4.getWidth()/3, panel4.getHeight()/7);
		labelTitoloPannello4.setVerticalAlignment(JLabel.CENTER);
		labelTitoloPannello4.setHorizontalAlignment(JLabel.CENTER);
		labelTitoloPannello4.setText("RIMUOVI VIAGGIO");
		panel4.add(labelTitoloPannello4);
		
		areaTestoPannello4 = new JTextArea();
		areaTestoPannello4 = new JTextArea(panel4.getWidth()/40*38, panel4.getHeight()/6*3);
		areaTestoPannello4.setFont(new Font("Arial", 0, 15));
		areaTestoPannello4.setEditable(false);
		areaTestoPannello4.setLineWrap(false);
		scrollAreaTestoPannello4 = new JScrollPane(areaTestoPannello4);   //creo un piccolo scroll e lo aggiungo alla text area
		scrollAreaTestoPannello4.setBounds(panel4.getWidth()/40, panel4.getHeight()/7, panel4.getWidth()/40*38, panel4.getHeight()/6*3);
		panel4.add(scrollAreaTestoPannello4);
		
		
		
		labelAmbientePannello4 = new JLabel();        //Etichetta per i mezzi
		labelAmbientePannello4.setFont(new Font("Arial", 0, 15));
		labelAmbientePannello4.setBounds(panel3.getWidth()/11-25, panel4.getHeight()/6*4, panel4.getWidth()/6, 20);
		labelAmbientePannello4.setText("Ambiente");
		panel4.add(labelAmbientePannello4);
		
		
		tendinaAmbientePannello4 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaAmbientePannello4.setBackground(Color.WHITE);
		tendinaAmbientePannello4.setEnabled(false);
		tendinaAmbientePannello4.setBounds(panel4.getWidth()/11-25, panel4.getHeight()/6*4+20, panel4.getWidth()/6, 20);
		panel4.add(tendinaAmbientePannello4);

		
		
		
		labelMezziPannello4 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello4.setFont(new Font("Arial", 0, 15));
		labelMezziPannello4.setBounds(panel3.getWidth()/11*3-25, panel4.getHeight()/6*4, panel4.getWidth()/6, 20);
		labelMezziPannello4.setText("Mezzi");
		panel4.add(labelMezziPannello4);
		
		
		tendinaMezziPannello4 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello4.setBackground(Color.WHITE);
		tendinaMezziPannello4.setEnabled(false);
		tendinaMezziPannello4.setBounds(panel4.getWidth()/11*3-25, panel4.getHeight()/6*4+20, panel4.getWidth()/6, 20);
		panel4.add(tendinaMezziPannello4);
		
		
		labelStazionePartenzaPannello4 = new JLabel();        //Etichetta per Stazioni di partenza
		labelStazionePartenzaPannello4.setFont(new Font("Arial", 0, 15));
		labelStazionePartenzaPannello4.setBounds(panel4.getWidth()/11*5-25, panel4.getHeight()/6*4, panel4.getWidth()/6, 20);
		labelStazionePartenzaPannello4.setText("Stazione di Partenza");
		panel4.add(labelStazionePartenzaPannello4);
		
		
		tendinaStazionePartenzaPannello4 = new JComboBox<String>();	 //Tendina per stazioni di partenza
		tendinaStazionePartenzaPannello4.setBackground(Color.WHITE);
		tendinaStazionePartenzaPannello4.setBounds(panel4.getWidth()/11*5-25, panel4.getHeight()/6*4+20, panel4.getWidth()/6, 20);
		tendinaStazionePartenzaPannello4.setEnabled(false);
		panel4.add(tendinaStazionePartenzaPannello4);
		
		
		labelStazioneArrivoPannello4 = new JLabel();        //Etichetta per Stazione di arrivo
		labelStazioneArrivoPannello4.setFont(new Font("Arial", 0, 15));
		labelStazioneArrivoPannello4.setBounds(panel4.getWidth()/11*7-25, panel4.getHeight()/6*4, panel4.getWidth()/6, 20);
		labelStazioneArrivoPannello4.setText("Stazione di Arrivo");
		panel4.add(labelStazioneArrivoPannello4);
		
		
		tendinaStazioneArrivoPannello4 = new JComboBox<String>();	 //Tendina per stazioni di arrivo
		tendinaStazioneArrivoPannello4.setBackground(Color.WHITE);
		tendinaStazioneArrivoPannello4.setBounds(panel4.getWidth()/11*7-25, panel4.getHeight()/6*4+20, panel4.getWidth()/6, 20);
		tendinaStazioneArrivoPannello4.setEnabled(false);
		panel4.add(tendinaStazioneArrivoPannello4);
		
		
		labelStazioneIntermediaPannello4 = new JLabel();	//Etichetta per Stazione intermedia
		labelStazioneIntermediaPannello4.setFont(new Font("Arial", 0, 15));
		labelStazioneIntermediaPannello4.setBounds(panel4.getWidth()/11*9-25, panel4.getHeight()/6*4, panel4.getWidth()/6, 20);
		labelStazioneIntermediaPannello4.setText("Stazione Intermedia");
		panel4.add(labelStazioneIntermediaPannello4);
		
		
		tendinaStazioneIntermediaPannello4 = new JComboBox<String>(); //Tendina per stazioni intermedie
		tendinaStazioneIntermediaPannello4.setBackground(Color.WHITE);
		tendinaStazioneIntermediaPannello4.setBounds(panel4.getWidth()/11*9-25, panel4.getHeight()/6*4+20, panel4.getWidth()/6, 20);
		tendinaStazioneIntermediaPannello4.setEnabled(false);
		panel4.add(tendinaStazioneIntermediaPannello4);
		
		bottoneSvuotaPannello4 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello4.setBackground(Color.YELLOW);
		bottoneSvuotaPannello4.setBounds(panel4.getWidth()/5-30, panel4.getHeight()/7*6-20, panel4.getWidth()/5+10, panel4.getHeight()/14);
		panel4.add(bottoneSvuotaPannello4);

		bottoneRimuovi = new JButton("RIMUOVI DAL CATALOGO");
		bottoneRimuovi.setBackground(Color.ORANGE);
		bottoneRimuovi.setBounds(panel4.getWidth()/5*3+16, panel4.getHeight()/7*6-20, panel4.getWidth()/5+10, panel4.getHeight()/14);
		panel4.add(bottoneRimuovi);
		
		
		
		bottoneChiudiPannello4 = new JButton("X");
		bottoneChiudiPannello4.setBackground(Color.RED);
		bottoneChiudiPannello4.setBounds(panel4.getWidth()/20*19, 0, panel4.getWidth()/20, panel4.getHeight()/18);
		panel4.add(bottoneChiudiPannello4);
		
		
		//Ascoltatori pannello 4
		
		ascoltatoreTendinaAmbientePannello4  = new TendinaAmbientePannello4AA();
		ascoltatoreTendinaMezziPannello4	 = new TendinaMezziPannello4AA();
		ascoltatoreTendinaPartenzePannello4  = new TendinaPartenzePannello4AA();
		ascoltatoreTendinaArriviPannello4    = new TendinaArriviPannello4AA();
		ascoltatoreBottoneRimuovi			 = new RimuoviAA();
		ascoltatoreBottoneChiudiPannello4    = new ChiudiPannello4AA();
		ascoltatoreBottoneSvuotaPannello4 	 = new SvuotaPannello4AA();
		
		tendinaAmbientePannello4.addActionListener(ascoltatoreTendinaAmbientePannello4);
		tendinaMezziPannello4.addActionListener(ascoltatoreTendinaMezziPannello4);
		tendinaStazionePartenzaPannello4.addActionListener(ascoltatoreTendinaPartenzePannello4);
		tendinaStazioneArrivoPannello4.addActionListener(ascoltatoreTendinaArriviPannello4);
		bottoneRimuovi.addActionListener(ascoltatoreBottoneRimuovi);
		bottoneChiudiPannello4.addActionListener(ascoltatoreBottoneChiudiPannello4);
		bottoneSvuotaPannello4.addActionListener(ascoltatoreBottoneSvuotaPannello4);
		

		//Per finire carico il catalogo
		caricaCatalogo();
	}//FINE COSTRUTTORE
	
	
	// Aggiorna la tendina dei mezzi del terzo pannello
	private void aggiornaTendinePannello3() {
		
		tendinaMezziPannello3.removeAllItems();    //rimuove tutti gli elementi dalla tendina
		tendinaStazionePartenzaPannello3.removeAllItems();
		tendinaStazioneArrivoPannello3.removeAllItems();

		
		try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
			Set<String> s = controllore.mostraMezziDiTrasportoInCatalogo(ambienteScelto);
			Iterator<String> it = s.iterator();
			
			while(it.hasNext()) 					//itero l'insieme di chiavi
				tendinaMezziPannello3.addItem(it.next());  //ne aggiungo uno alla volta
			
		} catch (IDEsternoException e) {
			System.out.println(e.getMessage()+"\n");
		}
		
		tendinaMezziPannello3.addItem("new..."); //per mettercene uno nuovo!!!
		campoStazionePartenzaPannello3.setText("");
		campoStazioneArrivoPannello3.setText("");
		campoStazioniIntermediePannello3.setText("");
		campoInfoPannello3.setText("");
		
		tendinaMezziPannello3.setEnabled(true);
		campoStazioniIntermediePannello3.setEditable(true);  // attiva il campo "stazione intermedia"
		campoInfoPannello3.setEditable(true);
	}
	
	// Aggiorna la tendina degli ambienti del quarto pannello
	private void aggiornaTendinePannello4() { 
		
		tendinaAmbientePannello4.removeAllItems();
		tendinaAmbientePannello4.setEnabled(false);
							
		//imposto ambiente scelto
		ambienteScelto = "-----";
		
		try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
			Set<String> s = controllore.mostraAmbientiInCatalogo();
			Iterator<String> it = s.iterator();
			if (s.size() > 1){ //se c'e' piu' di un elemento visualizzo l'elemento neutro
				//devo aggiungere l'elemento vuoto
				tendinaAmbientePannello4.addItem("-----");
			}
				
			while(it.hasNext()){ 					//itero l'insieme di chiavi
				tendinaAmbientePannello4.addItem(it.next());  //ne aggiungo uno alla volta
			}
				
			tendinaAmbientePannello4.setEnabled(true);
			
			//visualizzo subito il catalogo	
			areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
			areaTestoPannello4.setText(areaTestoImp + areaTestoCatalogo);
			areaTestoPannello4.setCaretPosition(0);
					
		} catch (MappaException e) {
			areaTestoPannello4.setText("Non sono presenti Viaggi nel Catalogo.\n");
		} catch (IDEsternoException e) {
			areaTestoPannello4.setText(e.getMessage()+"\n");
		} 

	}
	
	private void controlloSintatticoDati(String mezzo, String partenza, String arrivo) throws IDEsternoException{

		
		if (mezzo.equals("")||partenza.equals("")||arrivo.equals(""))
			throw new IDEsternoException("I campi con * sono obbligatori.");
		
		String s = mezzo+partenza+arrivo;
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (!Character.isLetter(s.charAt(i))&&!Character.isWhitespace(c))
				throw new IDEsternoException("Caratteri non validi.");
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
		try {
			controllore.carica();
		} catch (DeserializzazioneException e) {
			JOptionPane.showMessageDialog(null, "Non e' stato ancora salvato un catalogo.  Faccio partire il thread.");
			avviaThread();
			b = false;
		}
		
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
		
			try {  //Salva gli articoli all'uscita
				controllore.salva();
			} catch (SerializzazioneException e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
			
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
			
			try {  //Salva gli articoli all'uscita
				controllore.salva();
			} catch (SerializzazioneException e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
			
			System.exit(0);
		}
		
	}


	
	/*
	 * Classi Ascoltatori per bottoni pannello 2
	 */
	
	private class AggiungiViaggioAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel2.setVisible(true);
			panel3.setVisible(true);  //attiva pannello 3
			
			bottoneAggiungiViaggio.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(false);
		
		}
		
	}	
	
	private class RimuoviViaggioAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel2.setVisible(true);
			panel4.setVisible(true);	//attiva pannello 4
			bottoneAggiungiViaggio.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(false);
			
			//premendo il bottone, mi attiva subito la tendina ambiente implementata in aggiornaTendinaPannello4
			aggiornaTendinePannello4();
			

		}
		
	}
	

	
	/*
	 * Ascoltatori per elementi pannello 3
	 */
	
	
	private class SelezionaViaAriaPannello3AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Aria
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Aria";			//Cambia l'ambiente scelto
			aggiornaTendinePannello3();
		}
	
	}


	private class SelezionaViaMarePannello3AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Mare
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Mare"; 			//Cambia l'ambiente scelto
			aggiornaTendinePannello3();
		}
		
	}
	
	private class SelezionaViaTerraPannello3AA implements ActionListener{
		
		/*
		 *  Quando premo il bottone via Terra
		 */

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = "Terra"; 			//Cambia l'ambiente scelto
			aggiornaTendinePannello3();
		}
		
	}
	
	private class TendinaMezziPannello3AA implements ActionListener{
		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			
			if (tendinaMezziPannello3.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaMezziPannello3.getSelectedItem().toString().equals("new...")){
					campoMezziPannello3.setEditable(true); //attiva il campo sotto per inserire un mezzo non ancora usato
					campoStazionePartenzaPannello3.setEditable(true);
					campoStazioneArrivoPannello3.setEditable(true);
					tendinaStazionePartenzaPannello3.removeAllItems();
					tendinaStazioneArrivoPannello3.removeAllItems();
					tendinaStazionePartenzaPannello3.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di partenza sara' nuova, la tendina non serve!
					tendinaStazioneArrivoPannello3.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di arrivo sara' nuova, la tendina non serve!
				} else {
					campoMezziPannello3.setText("");
					campoMezziPannello3.setEditable(false);
					campoStazionePartenzaPannello3.setText("");
					campoStazionePartenzaPannello3.setEditable(false);
					campoStazioneArrivoPannello3.setText("");
					campoStazioneArrivoPannello3.setEditable(false);
					

		
					String mezzo = (String)tendinaMezziPannello3.getSelectedItem();
					
					tendinaStazionePartenzaPannello3.setEnabled(true);
					tendinaStazionePartenzaPannello3.removeAllItems();
					
					try {
						Set<String> set = controllore.mostraStazioniDiPartenzaInCatalogo(ambienteScelto,mezzo);
						Iterator<String> it = set.iterator();
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaStazionePartenzaPannello3.addItem(it.next());
						}
					} catch (IDEsternoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 

					tendinaStazionePartenzaPannello3.addItem("new...");
					tendinaStazionePartenzaPannello3.setSelectedIndex(0);						
				}
				
			}	
			
		}
		
	}
	
	private class TendinaStazionePartenzaPannello3AA implements ActionListener{
		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (tendinaStazionePartenzaPannello3.getItemCount() != 0) {//ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaStazionePartenzaPannello3.getSelectedItem().toString().equals("new...")){
					campoStazionePartenzaPannello3.setEditable(true); //attiva il campo sotto per inserire un stazione di partenza non ancora usata
					campoStazioneArrivoPannello3.setEditable(true);
					tendinaStazioneArrivoPannello3.removeAllItems();
					tendinaStazioneArrivoPannello3.setEnabled(false); //se il mezzo e' nuovo, anche la stazione di arrivo sara' nuova, la tendina non serve!
				} else {
					campoStazionePartenzaPannello3.setText("");
					campoStazionePartenzaPannello3.setEditable(false);
					campoStazioneArrivoPannello3.setText("");
					campoStazioneArrivoPannello3.setEditable(false);
					
					String mezzo = (String) tendinaMezziPannello3.getSelectedItem();
					String partenza = (String) tendinaStazionePartenzaPannello3.getSelectedItem();
					
					tendinaStazioneArrivoPannello3.setEnabled(true);
					tendinaStazioneArrivoPannello3.removeAllItems();
					
					
		
					try {
						
						Set<String> set = controllore.mostraStazioniDiArrivoInCatalogo(ambienteScelto, mezzo, partenza);
						Iterator<String> it = set.iterator();
						while (it.hasNext()){
								tendinaStazioneArrivoPannello3.addItem(it.next());
						}
						
					} catch (IDEsternoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					tendinaStazioneArrivoPannello3.addItem("new...");
					tendinaStazioneArrivoPannello3.setSelectedIndex(0);
					
				}	
				
			}
			
			
		}
		
		
	}
	
	
	private class TendinaStazioneArrivoPannello3AA implements ActionListener{

		/*
		 * Quando seleziono un elemento dalla tendina...
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (tendinaStazioneArrivoPannello3.getItemCount() != 0) {//ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if (tendinaStazioneArrivoPannello3.getSelectedItem().toString().equals("new...")){
					campoStazioneArrivoPannello3.setEditable(true); //Attiva il campo sotto per inserire una stazione di arrivo non ancora usata
				} else {
					campoStazioneArrivoPannello3.setText("");
					campoStazioneArrivoPannello3.setEditable(false);
				}
			}
			
		}
		
	}
	
	private class AggiungiAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (ambienteScelto != null){
				
				String mezzoTrasporto;
				String stazionePartenza;
				String stazioneArrivo;
				String stazioneIntermedia;
				String info;
			
				//le informazioni possono essere prese o da tendina o da campo testo...
				if (tendinaMezziPannello3.getSelectedItem().toString().equals("new...")){
					mezzoTrasporto = uppercaseFirstLetters(campoMezziPannello3.getText());
				} else {
					mezzoTrasporto = (String) tendinaMezziPannello3.getSelectedItem();
				}
			
				if (!tendinaStazionePartenzaPannello3.isEnabled() || tendinaStazionePartenzaPannello3.getSelectedItem().toString().equals("new...")){
					stazionePartenza = uppercaseFirstLetters(campoStazionePartenzaPannello3.getText());
				} else {
					stazionePartenza = (String) tendinaStazionePartenzaPannello3.getSelectedItem();
				}
				
				if (!tendinaStazioneArrivoPannello3.isEnabled() || tendinaStazioneArrivoPannello3.getSelectedItem().toString().equals("new...")){
					stazioneArrivo = uppercaseFirstLetters(campoStazioneArrivoPannello3.getText());
				} else {
					stazioneArrivo = (String) tendinaStazioneArrivoPannello3.getSelectedItem();
				}
			
				//nessun problema per la stazione Intermedia
				if (campoStazioniIntermediePannello3.getText().equals("")){
					stazioneIntermedia = "Diretto";
				} else stazioneIntermedia = uppercaseFirstLetters(campoStazioniIntermediePannello3.getText());
				
				//per le info
				
				if (campoInfoPannello3.getText().equals(""))
					info = "No Info"; 
				else 
					info = uppercaseFirstLetters(campoInfoPannello3.getText()); 
				
						
				//aggiungo il viaggio
				try {
					controlloSintatticoDati(mezzoTrasporto, stazionePartenza, stazioneArrivo);
					
					int conferma = JOptionPane.showConfirmDialog(null, "Aggiungere il viaggio nel catalogo?", "Conferma Nuovo Viaggio in Catalogo", JOptionPane.YES_NO_OPTION);
					
					if (conferma == JOptionPane.YES_OPTION){	
						controllore.aggiungiViaggio(ambienteScelto, mezzoTrasporto, stazionePartenza, stazioneArrivo, stazioneIntermedia, info);
						JOptionPane.showMessageDialog(null, "Il nuovo viaggio e' stato aggiunto correttamente nel catalogo.", "Viaggio Aggiunto", JOptionPane.INFORMATION_MESSAGE);
						aggiornaTendinePannello3(); //aggiorno le tendine
					}
					
				} catch (IDEsternoException e1) {
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
	
	
	
	private class SvuotaPannello3AA implements ActionListener{
		/*
		 * Svuota tutti i campi
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ambienteScelto = null;
			
			
			gruppoAmbientePannello3.clearSelection();   //toglie la spunta ai radio bottoni
			
			tendinaMezziPannello3.removeAllItems();  //svuota le tendine
			tendinaStazionePartenzaPannello3.removeAllItems();
			tendinaStazioneArrivoPannello3.removeAllItems();
			
			campoMezziPannello3.setText("");		//svuota tutti i campi testo
			campoStazionePartenzaPannello3.setText("");
			campoStazioneArrivoPannello3.setText("");
			campoStazioniIntermediePannello3.setText("");
			campoInfoPannello3.setText("");
			
			campoMezziPannello3.setEditable(false);
			campoStazionePartenzaPannello3.setEditable(false);
			tendinaMezziPannello3.setEnabled(false);
			tendinaStazionePartenzaPannello3.setEnabled(false);
			tendinaStazioneArrivoPannello3.setEnabled(false);
			campoStazioneArrivoPannello3.setEditable(false);
			campoStazioniIntermediePannello3.setEditable(false);
			campoInfoPannello3.setEditable(false);
		}
		
	}
	
	private class ChiudiPannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel3.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiViaggio.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(true);
			
			
			//svuoto cmq il pannello
			gruppoAmbientePannello3.clearSelection();  //toglie la spunta ai radio bottoni
			ambienteScelto = null;
			
			tendinaMezziPannello3.removeAllItems();  //svuota le tendine
			tendinaStazionePartenzaPannello3.removeAllItems();
			
			tendinaMezziPannello3.setEnabled(false);
			tendinaStazionePartenzaPannello3.setEnabled(false);
			
			campoMezziPannello3.setText("");		//svuota tutti i campi testo
			campoStazionePartenzaPannello3.setText("");
			campoStazioneArrivoPannello3.setText("");
			campoStazioniIntermediePannello3.setText("");
			campoInfoPannello3.setText("");
			
			campoMezziPannello3.setEditable(false);
			campoStazionePartenzaPannello3.setEditable(false);
			campoStazioneArrivoPannello3.setEditable(false);
			campoStazioniIntermediePannello3.setEditable(false);
			campoInfoPannello3.setEditable(false);
		}
	}

	
	
	
	/*
	 * Ascoltatori per elementi pannello 4
	 */
	
	
	private class TendinaAmbientePannello4AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaMezziPannello4.removeAllItems();
			tendinaMezziPannello4.setEnabled(false);
			
			//prendo il valore di questa tendina
			ambienteScelto	= (String)tendinaAmbientePannello4.getSelectedItem();
			
			if (tendinaAmbientePannello4.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
			
					
					if(!ambienteScelto.equals("-----")){ //Solo se non e' l'elemento neutro
							
						try {  //cerca nella mappa tutte le chiavi da aggiungere in tendina
							Set<String> s = controllore.mostraMezziDiTrasportoInCatalogo(ambienteScelto);
							Iterator<String> it = s.iterator();
							
							if (s.size() > 1){
								//inserisco l'elemento neutro
								tendinaMezziPannello4.addItem("-----");
							}
							
							while(it.hasNext()){ 					//itero l'insieme di chiavi
								tendinaMezziPannello4.addItem(it.next());  //ne aggiungo uno alla volta
							}
							tendinaMezziPannello4.setEnabled(true);
							
						} catch (IDEsternoException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					//Aggiorno l'area testo che mostra il catalogo
					try {
						
						areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
						areaTestoPannello4.setText(areaTestoImp + areaTestoCatalogo);
						areaTestoPannello4.setCaretPosition(0);
						
					} catch (MappaException e1) {
						areaTestoPannello4.setText("Non sono presenti Viaggi nel Catalogo.\n");
						
					} catch (IDEsternoException e1) {
						areaTestoPannello4.setText(e1.getMessage()+"\n");
					}
	
			}
		
		}
		
	}
	
	
	private class TendinaMezziPannello4AA implements ActionListener{
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaStazionePartenzaPannello4.removeAllItems();
			tendinaStazionePartenzaPannello4.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello4.getSelectedItem();   //Neanche servirebbe, in teoria...
			mezzoScelto = (String)tendinaMezziPannello4.getSelectedItem();
			
			if (tendinaMezziPannello4.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
	
				if(!mezzoScelto.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraStazioniDiPartenzaInCatalogo(ambienteScelto, mezzoScelto);
						Iterator<String> it = s.iterator();
						
						if (s.size() > 1){
							//inserisco l'elemento neutro
							tendinaStazionePartenzaPannello4.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaStazionePartenzaPannello4.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaStazionePartenzaPannello4.setEnabled(true);
						
					} catch (IDEsternoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}   
				}
				
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
					areaTestoPannello4.setText(areaTestoImp + areaTestoCatalogo);		
					areaTestoPannello4.setCaretPosition(0);
				
				} catch (MappaException e1) {
					areaTestoPannello4.setText("Non sono presenti Viaggi nel Catalogo.\n");
					
				} catch (IDEsternoException e1) {
					areaTestoPannello4.setText(e1.getMessage()+"\n");
				}
						
	
			}
		
		}
		
	}
	
	private class TendinaPartenzePannello4AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaStazioneArrivoPannello4.removeAllItems();
			tendinaStazioneArrivoPannello4.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello4.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello4.getSelectedItem();
			partenzaScelta = (String) tendinaStazionePartenzaPannello4.getSelectedItem();
			
			if (tendinaStazionePartenzaPannello4.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if(!partenzaScelta.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraStazioniDiArrivoInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta);
						Iterator<String> it = s.iterator();
						
						if(s.size() > 1){
							//inserisco l'elemento neutro
							tendinaStazioneArrivoPannello4.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaStazioneArrivoPannello4.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaStazioneArrivoPannello4.setEnabled(true);
						tendinaStazioneArrivoPannello4.setSelectedIndex(0);
						
					} catch (IDEsternoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
	
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
					areaTestoPannello4.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello4.setCaretPosition(0);
					
				} catch (MappaException e1) {
					areaTestoPannello4.setText("Non sono presenti Viaggi nel Catalogo.\n");
					
				} catch (IDEsternoException e1) {
					areaTestoPannello4.setText(e1.getMessage()+"\n");
				}
			
			}
			
		}
		
	}
	
	private class TendinaArriviPannello4AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ambienteScelto = (String) tendinaAmbientePannello4.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello4.getSelectedItem();
			partenzaScelta = (String) tendinaStazionePartenzaPannello4.getSelectedItem();
			arrivoScelto = (String)tendinaStazioneArrivoPannello4.getSelectedItem();
			
			if (tendinaStazioneArrivoPannello4.getItemCount() != 0) {
				//Aggiorno l'area testo che mostra il catalogo
				try {
					
					areaTestoCatalogo = controllore.mostraCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
					areaTestoPannello4.setText(areaTestoImp + areaTestoCatalogo);
					areaTestoPannello4.setCaretPosition(0);
				} catch (MappaException e1) {
					areaTestoPannello4.setText("Non sono presenti Viaggi nel Catalogo.\n");
					
				} catch (IDEsternoException e1) {
					areaTestoPannello4.setText(e1.getMessage()+"\n");
				}
				
			}
			
			
		}
		
	}
	
	
	private class RimuoviAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (tendinaStazioneArrivoPannello4.getItemCount() != 0 && !arrivoScelto.equals("-----")){
				
				String stazioneArrivo = (String) tendinaStazioneArrivoPannello4.getSelectedItem();
				
				// chiedo conferma
				int conferma = JOptionPane.showConfirmDialog(null, "Rimuovere il viaggio dal catalogo?", "Conferma Rimozione Viaggio", JOptionPane.YES_NO_OPTION);
				if (conferma == JOptionPane.YES_OPTION){
					
					// rimuovo il viaggio
					try {
						controllore.rimuoviViaggio(ambienteScelto, mezzoScelto, partenzaScelta, stazioneArrivo);
						JOptionPane.showMessageDialog(null, "Il viaggio e' stato rimosso correttamente dal catalogo.", "Viaggio Rimosso", JOptionPane.INFORMATION_MESSAGE);
						//aggiorno tutti i campi dopo aver rimosso il viaggio
						aggiornaTendinePannello4();
						
					} catch (IDEsternoException e1) {
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
	
	private class SvuotaPannello4AA implements ActionListener{
		/*
		 * Svuota tutti i campi
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (tendinaAmbientePannello4.isEnabled()){
								
				tendinaAmbientePannello4.setSelectedIndex(0); //la tendina torna al primo valore.
				
				if (tendinaAmbientePannello4.getItemCount()>1){
					tendinaMezziPannello4.removeAllItems();  //svuota le tendine
					tendinaStazionePartenzaPannello4.removeAllItems();
					tendinaStazioneArrivoPannello4.removeAllItems();
				
					tendinaMezziPannello4.setEnabled(false);//disattiva tutte le tendine
					tendinaStazionePartenzaPannello4.setEnabled(false);
					tendinaStazioneArrivoPannello4.setEnabled(false);
					
					ambienteScelto = null;
					mezzoScelto = null;
					partenzaScelta = null;
					arrivoScelto = null;

				}
				
		//	} else {
		//		JOptionPane.showMessageDialog(null, "Nessun viaggio in catalogo!", "Catalogo Vuoto", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		
	}
	
	private class ChiudiPannello4AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel4.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiViaggio.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviViaggio.setEnabled(true);
			
			//svuoto comunque il pannello
			if (tendinaAmbientePannello4.getItemCount() != 0){
				
				tendinaAmbientePannello4.setSelectedIndex(0); //la tendina torna al primo valore.
				tendinaMezziPannello4.removeAllItems();  //svuota le tendine
				tendinaStazionePartenzaPannello4.removeAllItems();
				tendinaStazioneArrivoPannello4.removeAllItems();
				
				tendinaMezziPannello4.setEnabled(false);//disattiva tutte le tendine
				tendinaStazionePartenzaPannello4.setEnabled(false);
				tendinaStazioneArrivoPannello4.setEnabled(false);
				
			}
			
			ambienteScelto = null;
			mezzoScelto = null;
			partenzaScelta = null;
			arrivoScelto = null;

		}
	}
		
}
