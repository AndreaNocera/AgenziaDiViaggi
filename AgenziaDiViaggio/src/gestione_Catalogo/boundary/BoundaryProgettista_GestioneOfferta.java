/**
 * 
 */
package gestione_Catalogo.boundary;




import gestione_Catalogo.control.ControlloreGestioneOfferta;
import gestione_Catalogo.entity.Data;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaException;
import gestione_Catalogo.exception.TrattaException;
import gestione_Catalogo.exception.TrattaInesistenteException;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @authors
 * Remo Sperlongano
 * Ivan Torre
 */
public class BoundaryProgettista_GestioneOfferta {
	
	
	/*
	 * Attributi di istanza
	 */
	
	//Entita'
	private ControlloreGestioneOfferta controllore;
	private String ambienteScelto;
	private String mezzoScelto;
	private String partenzaScelta;
	private String arrivoScelto;
	private String viaScelta;
	private String offertaScelta;
	private String areaTestoOfferta;
	private String areaTestoCatalogo;
	private String areaTestoImp;

	//Pannelli
	private JPanel superPanel;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	//Elementi Pannello1
    private JButton bottoneAggiungiOfferta;
	private JButton bottoneRimuoviOfferta;
	private JButton bottoneChiudiPannello1;
	    
	private AggiungiOffertaAA ascoltatoreBottoneAggiungiOfferta;
	private RimuoviOffertaAA ascoltatoreBottoneRimuoviOfferta;
    private ChiudiPannello1AA ascoltatoreBottoneChiudiPannello1;
    
    
    //Elementi Pannello2
    private JLabel	labelTitoloPannello2;

	private JLabel labelAmbientePannello2;
	private JComboBox<String> tendinaAmbientePannello2;

	private JTextArea areaTestoPannello2;
	private JScrollPane scrollAreaTestoPannello2;
	
	private JLabel labelMezziPannello2;
	private JComboBox<String> tendinaMezziPannello2;

	private JLabel labelCittaPartenzaPannello2;
	private JComboBox<String> tendinaCittaPartenzaPannello2;

	private JLabel labelCittaArrivoPannello2;
	private JComboBox<String> tendinaCittaArrivoPannello2;
	
	private JLabel labelViaPannello2;
	private JComboBox<String> tendinaViaPannello2;
	
	private JLabel labelDataPannello2;
	
	private JTextField campoGiornoPannello2;
	private JLabel labelGiornoPannello2;
	
	private JLabel labelSlash1Pannello2;
	
	private JLabel labelMesePannello2;
	private JComboBox<Integer> tendinaMesePannello2;
	
	private JLabel labelSlash2Pannello2;
	
	private JLabel labelAnnoPannello2;
	private JComboBox<Integer> tendinaAnnoPannello2;
	
	private JLabel labelOraPannello2;
	
	private JLabel labelOrePannello2;
	private JComboBox<Integer> tendinaOrePannello2;
	
	private JLabel labelPuntiniPannello2;
	
	private JLabel labelMinutiPannello2;
	private JComboBox<String> tendinaMinutoPannello2;
	
	private JLabel labelDurataPannello2;
	
	private JTextField campoDurataPannello2;
	private JLabel labelInMinutiPannello2;
	
	private JLabel labelPostiPannello2;
	private JTextField campoPostiPannello2;

	private JButton bottoneAggiungi;
	private JButton bottoneSvuotaPannello2;
	
	private JButton bottoneChiudiPannello2;
	
	private TendinaAmbientePannello2AA ascoltatoreTendinaAmbientePannello2;
	private TendinaMezziPannello2AA ascoltatoreTendinaMezziPannello2;
	private TendinaPartenzePannello2AA ascoltatoreTendinaPartenzePannello2;
	private TendinaArriviPannello2AA ascoltatoreTendinaArriviPannello2;
	private TendinaViaPannello2AA ascoltatoreTendinaViaPannello2;
	private ChiudiPannello2AA ascoltatoreBottoneChiudiPannello2;
	private AggiungiAA ascoltatoreBottoneAggiungi;
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
	
	private JLabel labelOffertaPannello3;
	private JComboBox<String> tendinaOffertaPannello3;

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
	
	
	


    
    
    public BoundaryProgettista_GestioneOfferta(JPanel panelNext){
    	
    	ambienteScelto = null;
		mezzoScelto = null;
		partenzaScelta = null;
		arrivoScelto = null;
		viaScelta = null;
		areaTestoOfferta = null;
		controllore = new ControlloreGestioneOfferta();
		
		areaTestoImp = "Offerte per il viaggo:   ";
		
		/*
		 * 
		 * Il superPanel di questa Boundary prende le dimensioni del pannello Passato
		 * 
		 */
		superPanel = panelNext;
		superPanel.setVisible(true);   //Si vede ora!
		
		
		
		
		/*
		 * 
		 * primo pannello: per i bottoni della gestione dell'offerta, si apre quando viene premuto GESTIONE OFFERTA 
		 * 
		 */

		panel1 = new JPanel();
		panel1.setSize(superPanel.getWidth(), superPanel.getHeight()/8-10);  //Il meno 10 serve per far vedere il contorno 
		panel1.setLocation(0,0);			//x=0 e y=0 rispetto al superPanel
		panel1.setLayout(null); 			//ora il pannello puo' contenere oggetti
		superPanel.add(panel1);				//aggiungo il primo pannello al superPannello
		panel1.setVisible(true);
		
		bottoneAggiungiOfferta = new JButton("AGGIUNGI OFFERTA");
		bottoneAggiungiOfferta.setBackground(Color.CYAN);
		bottoneAggiungiOfferta.setBounds(panel1.getWidth()/5, panel1.getHeight()/6, panel1.getWidth()/5, panel1.getHeight()/2);
		panel1.add(bottoneAggiungiOfferta);//aggiungo il bottone al secondo pannello
		
		
		bottoneRimuoviOfferta = new JButton("RIMUOVI OFFERTA");
		bottoneRimuoviOfferta.setBackground(Color.YELLOW);
		bottoneRimuoviOfferta.setBounds(panel1.getWidth()/5*3, panel1.getHeight()/6, panel1.getWidth()/5, panel1.getHeight()/2);
		panel1.add(bottoneRimuoviOfferta);//aggiungo il bottone al secondo pannello
		
		
		bottoneChiudiPannello1 = new JButton("X");
		bottoneChiudiPannello1.setBackground(Color.RED);
		bottoneChiudiPannello1.setBounds(panel1.getWidth()/20*19-10, 0, panel1.getWidth()/20, panel1.getHeight()/2-3);
		panel1.add(bottoneChiudiPannello1);
		
		
		
		// ascoltatori per primo pannello
		ascoltatoreBottoneAggiungiOfferta = new AggiungiOffertaAA(); 		//creo ascoltatore per bottone
		bottoneAggiungiOfferta.addActionListener(ascoltatoreBottoneAggiungiOfferta); 	//associo ascoltatore al bottone
		
		ascoltatoreBottoneRimuoviOfferta = new RimuoviOffertaAA();			//creo ascoltatore per bottone
		bottoneRimuoviOfferta.addActionListener(ascoltatoreBottoneRimuoviOfferta);		//associo ascoltatore al bottone
		
		ascoltatoreBottoneChiudiPannello1 = new ChiudiPannello1AA();
		bottoneChiudiPannello1.addActionListener(ascoltatoreBottoneChiudiPannello1);
    	
    
    
    /*
     * 
     * secondo pannello: questo pannello si attiva premendo il bottone AGGIUNGI OFFERTA
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
		labelTitoloPannello2.setBounds(panel2.getWidth()/3, panel2.getHeight()/200, panel2.getWidth()/3, panel2.getHeight()/7);
		labelTitoloPannello2.setVerticalAlignment(JLabel.CENTER);
		labelTitoloPannello2.setHorizontalAlignment(JLabel.CENTER);
		labelTitoloPannello2.setText("AGGIUNGI OFFERTA");
		panel2.add(labelTitoloPannello2);
		    
		    
	    labelAmbientePannello2 = new JLabel();        //Etichetta per i mezzi
		labelAmbientePannello2.setFont(new Font("Arial", 0, 15));
		labelAmbientePannello2.setBounds(panel2.getWidth()/11-35, panel2.getHeight()/6, panel2.getWidth()/6, 20);
		labelAmbientePannello2.setText("Ambiente");
		panel2.add(labelAmbientePannello2);
			
			
		tendinaAmbientePannello2 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaAmbientePannello2.setBackground(Color.WHITE);
		tendinaAmbientePannello2.setEnabled(false);
		tendinaAmbientePannello2.setBounds(panel2.getWidth()/11-35, panel2.getHeight()/6+20, panel2.getWidth()/6, 20);
		panel2.add(tendinaAmbientePannello2);

			
			
			
		labelMezziPannello2 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello2.setFont(new Font("Arial", 0, 15));
		labelMezziPannello2.setBounds(panel2.getWidth()/11*3-35, panel2.getHeight()/6, panel2.getWidth()/6, 20);
		labelMezziPannello2.setText("Mezzo di Trasporto");
		panel2.add(labelMezziPannello2);
			
			
		tendinaMezziPannello2 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello2.setBackground(Color.WHITE);
		tendinaMezziPannello2.setEnabled(false);
		tendinaMezziPannello2.setBounds(panel2.getWidth()/11*3-35, panel2.getHeight()/6+20, panel2.getWidth()/6, 20);
		panel2.add(tendinaMezziPannello2);
			
			
		labelCittaPartenzaPannello2 = new JLabel();        //Etichetta per Stazioni di partenza
		labelCittaPartenzaPannello2.setFont(new Font("Arial", 0, 15));
		labelCittaPartenzaPannello2.setBounds(panel2.getWidth()/11*5-35, panel2.getHeight()/6, panel2.getWidth()/6, 20);
		labelCittaPartenzaPannello2.setText("Citta' di Partenza");
		panel2.add(labelCittaPartenzaPannello2);
			
			
		tendinaCittaPartenzaPannello2 = new JComboBox<String>();	 //Tendina per stazioni di partenza
		tendinaCittaPartenzaPannello2.setBackground(Color.WHITE);
		tendinaCittaPartenzaPannello2.setBounds(panel2.getWidth()/11*5-35, panel2.getHeight()/6+20, panel2.getWidth()/6, 20);
		tendinaCittaPartenzaPannello2.setEnabled(false);
		panel2.add(tendinaCittaPartenzaPannello2);
			
			
		labelCittaArrivoPannello2 = new JLabel();        //Etichetta per Stazione di arrivo
		labelCittaArrivoPannello2.setFont(new Font("Arial", 0, 15));
		labelCittaArrivoPannello2.setBounds(panel2.getWidth()/11*7-35, panel2.getHeight()/6, panel2.getWidth()/6, 20);
		labelCittaArrivoPannello2.setText("Citta' di Arrivo");
		panel2.add(labelCittaArrivoPannello2);
			
			
		tendinaCittaArrivoPannello2 = new JComboBox<String>();	 //Tendina per stazioni di arrivo
		tendinaCittaArrivoPannello2.setBackground(Color.WHITE);
		tendinaCittaArrivoPannello2.setBounds(panel2.getWidth()/11*7-35, panel2.getHeight()/6+20, panel2.getWidth()/6, 20);
		tendinaCittaArrivoPannello2.setEnabled(false);
		panel2.add(tendinaCittaArrivoPannello2);
			
			
		labelViaPannello2 = new JLabel();	//Etichetta per Stazione intermedia
		labelViaPannello2.setFont(new Font("Arial", 0, 15));
		labelViaPannello2.setBounds(panel2.getWidth()/11*9-35, panel2.getHeight()/6, panel2.getWidth()/6, 20);
		labelViaPannello2.setText("Via");
		panel2.add(labelViaPannello2);
		
		
			
		tendinaViaPannello2 = new JComboBox<String>(); //Tendina per stazioni intermedie
		tendinaViaPannello2.setBackground(Color.WHITE);
		tendinaViaPannello2.setBounds(panel2.getWidth()/11*9-35, panel2.getHeight()/6+20, panel2.getWidth()/6, 20);
		tendinaViaPannello2.setEnabled(false);
		panel2.add(tendinaViaPannello2);
		
		
		
		areaTestoPannello2 = new JTextArea();
		areaTestoPannello2 = new JTextArea(panel2.getWidth()/40*38, panel2.getHeight()/6*3);
		areaTestoPannello2.setFont(new Font("Arial", 0, 15));
		areaTestoPannello2.setEditable(false);
		areaTestoPannello2.setLineWrap(false);
		scrollAreaTestoPannello2 = new JScrollPane(areaTestoPannello2);   //creo un piccolo scroll e lo aggiungo alla text area
		scrollAreaTestoPannello2.setBounds(panel2.getWidth()/11-35, panel2.getHeight()/6*2-20, panel2.getWidth()/2-50, panel2.getHeight()/6*3);
		panel2.add(scrollAreaTestoPannello2);
		
		
		labelDataPannello2 = new JLabel();	//Etichetta per Data
		labelDataPannello2.setFont(new Font("Arial",0,15));
		labelDataPannello2.setBounds(panel2.getWidth()/2+50, panel2.getHeight()/6*2, panel2.getWidth()/12, 20);
		labelDataPannello2.setText("Data");
		panel2.add(labelDataPannello2);
		
		
		labelGiornoPannello2 = new JLabel();	//Etichetta elementi data
		labelGiornoPannello2.setFont(new Font("Arial",0,15));
		labelGiornoPannello2.setBounds(panel2.getWidth()/24*13+50, panel2.getHeight()/6*2-20, panel2.getWidth()/36, 20);
		labelGiornoPannello2.setText("GG");
		panel2.add(labelGiornoPannello2); 
		
		
		campoGiornoPannello2 = new JTextField(panel2.getWidth()/18); //campo per il giorno
		campoGiornoPannello2.setFont(new Font("Arial", 0, 18));
		campoGiornoPannello2.setBounds(panel2.getWidth()/24*13+50, panel2.getHeight()/6*2, panel2.getWidth()/36, 20);
		campoGiornoPannello2.setEditable(false);
	    panel2.add(campoGiornoPannello2);
	    
	    
	    
	    labelSlash1Pannello2 = new JLabel();	//Etichetta elementi data
	    labelSlash1Pannello2.setFont(new Font("Arial",0,15));
	    labelSlash1Pannello2.setBounds(panel2.getWidth()/24*13+85, panel2.getHeight()/6*2, panel2.getWidth()/36, 20);
	    labelSlash1Pannello2.setText("/");
		panel2.add(labelSlash1Pannello2); 
		
		
	
		labelMesePannello2 = new JLabel();	//Etichetta elementi data
		labelMesePannello2.setFont(new Font("Arial",0,15));
		labelMesePannello2.setBounds(panel2.getWidth()/24*13+105, panel2.getHeight()/6*2-20, panel2.getWidth()/36, 20);
		labelMesePannello2.setText("MM");
		panel2.add(labelMesePannello2); 
		
		
		
		tendinaMesePannello2 = new JComboBox<Integer>(); //Tendina per mesi
		tendinaMesePannello2.setBackground(Color.WHITE);
		for (int i=1; i<=12; i++){
			tendinaMesePannello2.addItem(new Integer(i));
		}
		tendinaMesePannello2.setBounds(panel2.getWidth()/24*13+95, panel2.getHeight()/6*2, panel2.getWidth()/24, 20);
		tendinaMesePannello2.setEnabled(false);
		panel2.add(tendinaMesePannello2);
		
		
		labelSlash2Pannello2 = new JLabel();	//Etichetta elementi data
		labelSlash2Pannello2.setFont(new Font("Arial",0,15));
		labelSlash2Pannello2.setBounds(panel2.getWidth()/24*13+140, panel2.getHeight()/6*2, panel2.getWidth()/36, 20);
		labelSlash2Pannello2.setText("/");
		panel2.add(labelSlash2Pannello2); 
		
		
		labelAnnoPannello2 = new JLabel();	//Etichetta elementi data
		labelAnnoPannello2.setFont(new Font("Arial",0,15));
		labelAnnoPannello2.setBounds(panel2.getWidth()/24*13+160, panel2.getHeight()/6*2-20, panel2.getWidth()/18, 20);
		labelAnnoPannello2.setText("YYYY");
		panel2.add(labelAnnoPannello2); 
		
		
		
		tendinaAnnoPannello2 = new JComboBox<Integer>(); //Tendina per anni
		tendinaAnnoPannello2.setBackground(Color.WHITE);

		for (int i=2013; i<2020; i++){
			tendinaAnnoPannello2.addItem(i);
		}
		tendinaAnnoPannello2.setBounds(panel2.getWidth()/24*13+150, panel2.getHeight()/6*2, panel2.getWidth()/18, 20);
		tendinaAnnoPannello2.setEnabled(false);
		panel2.add(tendinaAnnoPannello2);
		
		
		
		labelOraPannello2 = new JLabel();	//Etichetta Ora
		labelOraPannello2.setFont(new Font("Arial", 0, 15));
		labelOraPannello2.setBounds(panel2.getWidth()/4*3+50, panel2.getHeight()/6*2, panel2.getWidth()/12, 20);
		labelOraPannello2.setText("Ora");
		panel2.add(labelOraPannello2);
		
		
		labelOrePannello2 = new JLabel();	//Etichetta elementi ora
		labelOrePannello2.setFont(new Font("Arial", 0, 15));
		labelOrePannello2.setBounds(panel2.getWidth()/24*19+60, panel2.getHeight()/6*2-20, panel2.getWidth()/12, 20);
		labelOrePannello2.setText("HH");
		panel2.add(labelOrePannello2);
		
		
		tendinaOrePannello2 = new JComboBox<Integer>(); //Tendina per ore
		tendinaOrePannello2.setBackground(Color.WHITE);
		
		for (int i=0; i<24; i++){
			tendinaOrePannello2.addItem(i);
		}
		tendinaOrePannello2.setBounds(panel2.getWidth()/24*19+50, panel2.getHeight()/6*2, panel2.getWidth()/24, 20);
		tendinaOrePannello2.setEnabled(false);
		panel2.add(tendinaOrePannello2);
		
		
		labelPuntiniPannello2 = new JLabel();	//Etichetta elementi data
		labelPuntiniPannello2.setFont(new Font("Arial",0,15));
		labelPuntiniPannello2.setBounds(panel2.getWidth()/24*19+95, panel2.getHeight()/6*2, panel2.getWidth()/36, 20);
		labelPuntiniPannello2.setText(":");
		panel2.add(labelPuntiniPannello2); 
		
		
		
		labelMinutiPannello2 = new JLabel();	//Etichetta elementi ora
		labelMinutiPannello2.setFont(new Font("Arial", 0, 15));
		labelMinutiPannello2.setBounds(panel2.getWidth()/24*19+115, panel2.getHeight()/6*2-20, panel2.getWidth()/12, 20);
		labelMinutiPannello2.setText("MM");
		panel2.add(labelMinutiPannello2);
		
   	
		
		tendinaMinutoPannello2 = new JComboBox<String>(); //Tendina per ore
		tendinaMinutoPannello2.setBackground(Color.WHITE);
		for (int i=0; i<60; i++){
			String min="";
			if (i<10){
				min = "0";
			}
			min+=i;
			tendinaMinutoPannello2.addItem(min);
		}
		tendinaMinutoPannello2.setBounds(panel2.getWidth()/24*19+105, panel2.getHeight()/6*2, panel2.getWidth()/24, 20);
		tendinaMinutoPannello2.setEnabled(false);
		panel2.add(tendinaMinutoPannello2);
    
		
		
		labelDurataPannello2 = new JLabel();	//Etichetta Ora
		labelDurataPannello2.setFont(new Font("Arial", 0, 15));
		labelDurataPannello2.setBounds(panel2.getWidth()/2+50, panel2.getHeight()/6*3, panel2.getWidth()/12, 20);
		labelDurataPannello2.setText("Durata");
		panel2.add(labelDurataPannello2);
		
		
		labelInMinutiPannello2 = new JLabel();	//Etichetta elementi ora
		labelInMinutiPannello2.setFont(new Font("Arial", 0, 15));
		labelInMinutiPannello2.setBounds(panel2.getWidth()/24*13+80, panel2.getHeight()/6*3-20, panel2.getWidth()/12, 20);
		labelInMinutiPannello2.setText("in Minuti");
		panel2.add(labelInMinutiPannello2);
		
		
	    campoDurataPannello2 = new JTextField (panel2.getWidth()/6); //Campo per stazioni intermedie
	    campoDurataPannello2.setFont(new Font("Arial", 0, 18));
	    campoDurataPannello2.setBounds(panel2.getWidth()/24*13+60, panel2.getHeight()/6*3, panel2.getWidth()/12, 20);
	    campoDurataPannello2.setEditable(false);
	    panel2.add(campoDurataPannello2); 
	    
	    
	    labelPostiPannello2 = new JLabel();	//Etichetta elementi ora
	    labelPostiPannello2.setFont(new Font("Arial", 0, 15));
	    labelPostiPannello2.setBounds(panel2.getWidth()/24*19, panel2.getHeight()/6*3-20, panel2.getWidth()/8, 20);
	    labelPostiPannello2.setText("Posti Disponibili");
		panel2.add(labelPostiPannello2);
		
		
	    campoPostiPannello2 = new JTextField (panel2.getWidth()/6); //Campo per stazioni intermedie
	    campoPostiPannello2.setFont(new Font("Arial", 0, 18));
	    campoPostiPannello2.setBounds(panel2.getWidth()/24*19, panel2.getHeight()/6*3, panel2.getWidth()/8, 20);
	    campoPostiPannello2.setEditable(false);
	    panel2.add(campoPostiPannello2); 
	    
	    
	    bottoneSvuotaPannello2 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello2.setBackground(Color.YELLOW);
		bottoneSvuotaPannello2.setBounds(panel2.getWidth()/5*3-60, panel2.getHeight()/6*4, panel2.getWidth()/5, panel2.getHeight()/14);
		panel2.add(bottoneSvuotaPannello2);
		
		bottoneAggiungi = new JButton("AGGIUNGI OFFERTA");
		bottoneAggiungi.setBackground(Color.ORANGE);
		bottoneAggiungi.setBounds(panel2.getWidth()/5*4-25, panel2.getHeight()/6*4, panel2.getWidth()/5, panel2.getHeight()/14);
		panel2.add(bottoneAggiungi);
		
		bottoneChiudiPannello2 = new JButton("X");
		bottoneChiudiPannello2.setBackground(Color.RED);
		bottoneChiudiPannello2.setBounds(panel2.getWidth()/20*19-10, 0, panel2.getWidth()/20, panel2.getHeight()/18);
		panel2.add(bottoneChiudiPannello2);
    
		
		//ascoltatori per il pannello 2
		
		
		ascoltatoreTendinaAmbientePannello2 = new TendinaAmbientePannello2AA();
		tendinaAmbientePannello2.addActionListener(ascoltatoreTendinaAmbientePannello2);
		
		ascoltatoreTendinaMezziPannello2 = new TendinaMezziPannello2AA();
		tendinaMezziPannello2.addActionListener(ascoltatoreTendinaMezziPannello2);

		ascoltatoreTendinaPartenzePannello2 = new TendinaPartenzePannello2AA();
		tendinaCittaPartenzaPannello2.addActionListener(ascoltatoreTendinaPartenzePannello2);
		
		ascoltatoreTendinaArriviPannello2 = new TendinaArriviPannello2AA();
		tendinaCittaArrivoPannello2.addActionListener(ascoltatoreTendinaArriviPannello2);
		
		ascoltatoreTendinaViaPannello2 = new TendinaViaPannello2AA();
		tendinaViaPannello2.addActionListener(ascoltatoreTendinaViaPannello2);
		
		ascoltatoreBottoneChiudiPannello2 = new ChiudiPannello2AA();
		bottoneChiudiPannello2.addActionListener(ascoltatoreBottoneChiudiPannello2);
		
		ascoltatoreBottoneAggiungi = new AggiungiAA();
		bottoneAggiungi.addActionListener(ascoltatoreBottoneAggiungi);
		
		ascoltatoreBottoneSvuotaPannello2 = new SvuotaPannello2AA();
		bottoneSvuotaPannello2.addActionListener(ascoltatoreBottoneSvuotaPannello2);
		
		
		/*
		 * 
		 * terzo pannello: si attiva quando viene premuto il pulsante RIMUOVI OFFERTA
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
		labelTitoloPannello3.setText("RIMUOVI OFFERTA");
		panel3.add(labelTitoloPannello3);
		    
		    
	    labelAmbientePannello3 = new JLabel();        //Etichetta per i mezzi
		labelAmbientePannello3.setFont(new Font("Arial", 0, 15));
		labelAmbientePannello3.setBounds(panel3.getWidth()/11-35, panel3.getHeight()/6, panel3.getWidth()/6, 20);
		labelAmbientePannello3.setText("Ambiente");
		panel3.add(labelAmbientePannello3);
		
		
		tendinaAmbientePannello3 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaAmbientePannello3.setBackground(Color.WHITE);
		tendinaAmbientePannello3.setEnabled(false);
		tendinaAmbientePannello3.setBounds(panel3.getWidth()/11-35, panel3.getHeight()/6+20, panel3.getWidth()/6, 20);
		panel3.add(tendinaAmbientePannello3);

			
			
			
		labelMezziPannello3 = new JLabel();        //Etichetta per i mezzi
		labelMezziPannello3.setFont(new Font("Arial", 0, 15));
		labelMezziPannello3.setBounds(panel3.getWidth()/11*3-35, panel3.getHeight()/6, panel3.getWidth()/6, 20);
		labelMezziPannello3.setText("Mezzo di Trasporto");
		panel3.add(labelMezziPannello3);
			
			
		tendinaMezziPannello3 = new JComboBox<String>();	  //Tendina per i mezzi
		tendinaMezziPannello3.setBackground(Color.WHITE);
		tendinaMezziPannello3.setEnabled(false);
		tendinaMezziPannello3.setBounds(panel3.getWidth()/11*3-35, panel3.getHeight()/6+20, panel3.getWidth()/6, 20);
		panel3.add(tendinaMezziPannello3);
			
			
		labelCittaPartenzaPannello3 = new JLabel();        //Etichetta per Stazioni di partenza
		labelCittaPartenzaPannello3.setFont(new Font("Arial", 0, 15));
		labelCittaPartenzaPannello3.setBounds(panel3.getWidth()/11*5-35, panel3.getHeight()/6, panel3.getWidth()/6, 20);
		labelCittaPartenzaPannello3.setText("Citta' di Partenza");
		panel3.add(labelCittaPartenzaPannello3);
			
			
		tendinaCittaPartenzaPannello3 = new JComboBox<String>();	 //Tendina per stazioni di partenza
		tendinaCittaPartenzaPannello3.setBackground(Color.WHITE);
		tendinaCittaPartenzaPannello3.setBounds(panel3.getWidth()/11*5-35, panel3.getHeight()/6+20, panel3.getWidth()/6, 20);
		tendinaCittaPartenzaPannello3.setEnabled(false);
		panel3.add(tendinaCittaPartenzaPannello3);
			
			
		labelCittaArrivoPannello3 = new JLabel();        //Etichetta per Stazione di arrivo
		labelCittaArrivoPannello3.setFont(new Font("Arial", 0, 15));
		labelCittaArrivoPannello3.setBounds(panel3.getWidth()/11*7-35, panel3.getHeight()/6, panel3.getWidth()/6, 20);
		labelCittaArrivoPannello3.setText("Citta' di Arrivo");
		panel3.add(labelCittaArrivoPannello3);
			
			
		tendinaCittaArrivoPannello3 = new JComboBox<String>();	 //Tendina per stazioni di arrivo
		tendinaCittaArrivoPannello3.setBackground(Color.WHITE);
		tendinaCittaArrivoPannello3.setBounds(panel3.getWidth()/11*7-35, panel3.getHeight()/6+20, panel3.getWidth()/6, 20);
		tendinaCittaArrivoPannello3.setEnabled(false);
		panel3.add(tendinaCittaArrivoPannello3);
			
			
		labelViaPannello3 = new JLabel();	//Etichetta per Stazione intermedia
		labelViaPannello3.setFont(new Font("Arial", 0, 15));
		labelViaPannello3.setBounds(panel3.getWidth()/11*9-35, panel3.getHeight()/6, panel3.getWidth()/6, 20);
		labelViaPannello3.setText("Via");
		panel3.add(labelViaPannello3);
		
		
			
		tendinaViaPannello3 = new JComboBox<String>(); //Tendina per stazioni intermedie
		tendinaViaPannello3.setBackground(Color.WHITE);
		tendinaViaPannello3.setBounds(panel3.getWidth()/11*9-35, panel3.getHeight()/6+20, panel3.getWidth()/6, 20);
		tendinaViaPannello3.setEnabled(false);
		panel3.add(tendinaViaPannello3);
		
		
		
		areaTestoPannello3 = new JTextArea();
		areaTestoPannello3 = new JTextArea(panel3.getWidth()/40*38, panel3.getHeight()/6*3);
		areaTestoPannello3.setFont(new Font("Arial", 0, 15));
		areaTestoPannello3.setEditable(false);
		areaTestoPannello3.setLineWrap(false);
		scrollAreaTestoPannello3 = new JScrollPane(areaTestoPannello3);   //creo un piccolo scroll e lo aggiungo alla text area
		scrollAreaTestoPannello3.setBounds(panel3.getWidth()/11-35, panel3.getHeight()/6*2-20, panel3.getWidth()/2-50, panel3.getHeight()/6*3);
		panel3.add(scrollAreaTestoPannello3);
		
		
		
		labelOffertaPannello3 = new JLabel();	//Etichetta elementi data
		labelOffertaPannello3.setFont(new Font("Arial",0,15));
		labelOffertaPannello3.setBounds(panel3.getWidth()/4*2+100, panel3.getHeight()/6*2-20, panel3.getWidth()/4, 20);
		labelOffertaPannello3.setText("Offerta");
		panel3.add(labelOffertaPannello3); 
		
		
		
		tendinaOffertaPannello3 = new JComboBox<String>(); //Tendina per stazioni intermedie
		tendinaOffertaPannello3.setBackground(Color.WHITE);
		tendinaOffertaPannello3.setBounds(panel3.getWidth()/4*2+100, panel3.getHeight()/6*2, panel3.getWidth()/4, 20);
		tendinaOffertaPannello3.setEnabled(false);
		panel3.add(tendinaOffertaPannello3);
		
		
		
		bottoneSvuotaPannello3 = new JButton("AZZERA CAMPI");
		bottoneSvuotaPannello3.setBackground(Color.YELLOW);
		bottoneSvuotaPannello3.setBounds(panel3.getWidth()/5*3-60, panel3.getHeight()/6*4, panel3.getWidth()/5, panel3.getHeight()/14);
		panel3.add(bottoneSvuotaPannello3);
		
		bottoneRimuovi = new JButton("RIMUOVI OFFERTA");
		bottoneRimuovi.setBackground(Color.ORANGE);
		bottoneRimuovi.setBounds(panel3.getWidth()/5*4-25, panel3.getHeight()/6*4, panel3.getWidth()/5, panel3.getHeight()/14);
		panel3.add(bottoneRimuovi);
		
		bottoneChiudiPannello3 = new JButton("X");
		bottoneChiudiPannello3.setBackground(Color.RED);
		bottoneChiudiPannello3.setBounds(panel3.getWidth()/20*19-10, 0, panel3.getWidth()/20, panel3.getHeight()/18);
		panel3.add(bottoneChiudiPannello3);
    
		
		//ascoltatori per il pannello 3
		
		
		ascoltatoreTendinaAmbientePannello3 = new TendinaAmbientePannello3AA();
		tendinaAmbientePannello3.addActionListener(ascoltatoreTendinaAmbientePannello3);
		
		ascoltatoreTendinaMezziPannello3 = new TendinaMezziPannello3AA();
		tendinaMezziPannello3.addActionListener(ascoltatoreTendinaMezziPannello3);

		ascoltatoreTendinaPartenzePannello3 = new TendinaPartenzePannello3AA();
		tendinaCittaPartenzaPannello3.addActionListener(ascoltatoreTendinaPartenzePannello3);
		
		ascoltatoreTendinaArriviPannello3 = new TendinaArriviPannello3AA();
		tendinaCittaArrivoPannello3.addActionListener(ascoltatoreTendinaArriviPannello3);
		
		ascoltatoreTendinaViaPannello3 = new TendinaViaPannello3AA();
		tendinaViaPannello3.addActionListener(ascoltatoreTendinaViaPannello3);
		
		ascoltatoreBottoneChiudiPannello3 = new ChiudiPannello3AA();
		bottoneChiudiPannello3.addActionListener(ascoltatoreBottoneChiudiPannello3);
		
		ascoltatoreBottoneRimuovi = new RimuoviAA();
		bottoneRimuovi.addActionListener(ascoltatoreBottoneRimuovi);
		
		ascoltatoreBottoneSvuotaPannello3 = new SvuotaPannello3AA();
		bottoneSvuotaPannello3.addActionListener(ascoltatoreBottoneSvuotaPannello3);
		
	
    }
    
    
    
    	private void aggiornaTendinePannello2() { 
    		
    		tendinaAmbientePannello2.removeAllItems();
    		tendinaAmbientePannello2.setEnabled(false);
    		
    		areaTestoPannello2.setText("");
    		
    		//imposto ambiente scelto
    		ambienteScelto = "-----";
    		
    		try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
    			Set<String> s = controllore.mostraAmbientiInCatalogo();
    			Iterator<String> it = s.iterator();
    			if (s.size() > 1){ //se c'e' piu' di un elemento visualizzo l'elemento neutro
    				//devo aggiungere l'elemento vuoto
    				tendinaAmbientePannello2.addItem("-----");
    			}
    				
    			while(it.hasNext()){ 					//itero l'insieme di chiavi
    				tendinaAmbientePannello2.addItem(it.next());  //ne aggiungo uno alla volta
    			}
    				
    			tendinaAmbientePannello2.setEnabled(true);
    			
    					
    		} catch (MappaException e) {
    			areaTestoPannello3.setText(e.getMessage()+"\n");
    		} 

    	}
    	
    	
    	private void svuotaPartePannello2(){
    		
    		campoGiornoPannello2.setText("");
			campoGiornoPannello2.setEditable(false);
			
			tendinaMesePannello2.setSelectedIndex(0);
			tendinaMesePannello2.setEnabled(false);
			
			tendinaAnnoPannello2.setSelectedIndex(0);
			tendinaAnnoPannello2.setEnabled(false);
			
			tendinaOrePannello2.setSelectedIndex(0);
			tendinaOrePannello2.setEnabled(false);
			
			tendinaMinutoPannello2.setSelectedIndex(0);
			tendinaMinutoPannello2.setEnabled(false);
			
			campoDurataPannello2.setText("");
			campoDurataPannello2.setEditable(false);
			
			campoPostiPannello2.setText("");
			campoPostiPannello2.setEditable(false);
			
			areaTestoPannello2.setText("");
    	}
    	
    	
    	
    	private void aggiornaTendinePannello3() { 
    		
    		tendinaAmbientePannello3.removeAllItems();
    		tendinaAmbientePannello3.setEnabled(false);
    		
    		areaTestoPannello3.setText("");
    		
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
    			
    					
    		} catch (MappaException e) {
    			areaTestoPannello3.setText(e.getMessage()+"\n");
    		} 

    	}
    	
    	
    
		private void controlloSintatticoDati() {
			// TODO Auto-generated method stub
			
		}
		
    
    
    
    
    
    
    
    
    /*
	 * Classi Ascoltatori per bottoni pannello 1
	 */
	
	private class AggiungiOffertaAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel2.setVisible(true);  //attiva pannello 2
			
			bottoneAggiungiOfferta.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviOfferta.setEnabled(false);
			bottoneChiudiPannello1.setEnabled(false);
			
			aggiornaTendinePannello2();
		
		}
		
	}	
	
	private class RimuoviOffertaAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel1.setVisible(false);
			panel1.setVisible(true);
			panel3.setVisible(true);	//attiva pannello 3
			bottoneAggiungiOfferta.setEnabled(false); //disattiva i bottoni
			bottoneRimuoviOfferta.setEnabled(false);
			bottoneChiudiPannello1.setEnabled(false);
			
			
			aggiornaTendinePannello3();
			

		}
		
	}
	
	private class ChiudiPannello1AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			superPanel.setVisible(false); 			    //chiude tutto questo pannello
			BoundaryProgettista.riattivaBottoni();      	//riattiva i bottoni
						
		}
	}
	
	
	/*
	 * Ascoltatori per elementi pannello 2
	 */
	
	
	private class TendinaAmbientePannello2AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto parte del pannello 2
			svuotaPartePannello2();
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaMezziPannello2.removeAllItems();
			tendinaMezziPannello2.setEnabled(false);
			
			//prendo il valore di questa tendina
			ambienteScelto	= (String)tendinaAmbientePannello2.getSelectedItem();
			
			if (tendinaAmbientePannello2.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
			
					
					if(!ambienteScelto.equals("-----")){ //Solo se non e' l'elemento neutro
							
						try {  //cerca nella mappa tutte le chiavi da aggiungere in tendina
							Set<String> s = controllore.mostraMezziInCatalogo(ambienteScelto);
							Iterator<String> it = s.iterator();
							
							if (s.size() > 1){
								//inserisco l'elemento neutro
								tendinaMezziPannello2.addItem("-----");
							}
							
							while(it.hasNext()){ 					//itero l'insieme di chiavi
								tendinaMezziPannello2.addItem(it.next());  //ne aggiungo uno alla volta
							}
							tendinaMezziPannello2.setEnabled(true);
							
						} catch (IDEsternoElementoException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
					}
					
	
			}
		
		}
		
	}
	
	
	private class TendinaMezziPannello2AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto parte del pannello 2
			svuotaPartePannello2();
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaCittaPartenzaPannello2.removeAllItems();
			tendinaCittaPartenzaPannello2.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello2.getSelectedItem();   //Neanche servirebbe, in teoria...
			mezzoScelto = (String)tendinaMezziPannello2.getSelectedItem();
			
			if (tendinaMezziPannello2.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
	
				if(!mezzoScelto.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraCittaDiPartenzaInCatalogo(ambienteScelto, mezzoScelto);
						Iterator<String> it = s.iterator();
						
						if (s.size() > 1){
							//inserisco l'elemento neutro
							tendinaCittaPartenzaPannello2.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaPartenzaPannello2.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaCittaPartenzaPannello2.setEnabled(true);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}   
				}
						
	
			}
		
		}
		
	}
	
	private class TendinaPartenzePannello2AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto parte del pannello 2
			svuotaPartePannello2();
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaCittaArrivoPannello2.removeAllItems();
			tendinaCittaArrivoPannello2.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello2.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello2.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello2.getSelectedItem();
			
			if (tendinaCittaPartenzaPannello2.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if(!partenzaScelta.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraCittaDiArrivoInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta);
						Iterator<String> it = s.iterator();
						
						if(s.size() > 1){
							//inserisco l'elemento neutro
							tendinaCittaArrivoPannello2.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaCittaArrivoPannello2.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaCittaArrivoPannello2.setEnabled(true);
						tendinaCittaArrivoPannello2.setSelectedIndex(0);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
			
			}
			
		}
		
	}
	
	private class TendinaArriviPannello2AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto parte del pannello 2
			svuotaPartePannello2();
			
			//Svuotiamo tutte le tendine successive (non le precedenti) e le disattiviamo
			tendinaViaPannello2.removeAllItems();
			tendinaViaPannello2.setEnabled(false);
			
			//prendo il valore delle altre tendina
			ambienteScelto = (String) tendinaAmbientePannello2.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello2.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello2.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello2.getSelectedItem();
			
			if (tendinaCittaArrivoPannello2.getItemCount() != 0) { //ci deve essere almeno un elemento, se ho la tendina vuota mi va in null pointer exception.
				
				if(!arrivoScelto.equals("-----")){ //Solo se non e' l'elemento neutro
					
					try { //cerca nella mappa tutte le chiavi da aggiungere in tendina
						Set<String> s = controllore.mostraViaInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto);
						Iterator<String> it = s.iterator();
						
						if(s.size() > 1){
							//inserisco l'elemento neutro
							tendinaViaPannello2.addItem("-----");
						}
						
						while(it.hasNext()){ 					//itero l'insieme di chiavi
							tendinaViaPannello2.addItem(it.next());  //ne aggiungo uno alla volta
						}
						
						tendinaViaPannello2.setEnabled(true);
						tendinaViaPannello2.setSelectedIndex(0);
						
					} catch (IDEsternoElementoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
	
			
			ambienteScelto = (String) tendinaAmbientePannello2.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello2.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello2.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello2.getSelectedItem();
			
				
			}
			
			
		}
		
	}
	
	
	private class TendinaViaPannello2AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//svuoto parte del pannello2
			svuotaPartePannello2();
			
			ambienteScelto = (String) tendinaAmbientePannello2.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello2.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello2.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello2.getSelectedItem();
			viaScelta = (String) tendinaViaPannello2.getSelectedItem();
			
			campoGiornoPannello2.setEditable(true);
			tendinaMesePannello2.setEnabled(true);
			tendinaAnnoPannello2.setEnabled(true);
			tendinaOrePannello2.setEnabled(true);
			tendinaMinutoPannello2.setEnabled(true);
			campoDurataPannello2.setEditable(true);
			campoPostiPannello2.setEditable(true);
			
			//Modifico la stringa da immettere nella textArea (DA SISTEMARE!!!)	
			if (tendinaViaPannello2.getItemCount() != 0 && !viaScelta.equals("-----")){
				String via = (String) tendinaViaPannello2.getSelectedItem();
				
				
				//Imposto areatestoCatalogo
				areaTestoCatalogo = ambienteScelto + " " + mezzoScelto + " " + partenzaScelta + " : " + arrivoScelto + " -> " + viaScelta + "\n\n"  +
									"ORA PARTENZA\tORA ARRIVO\t\tPOSTI\n" +
									"-----------\t\t----------\t\t----------\n";
				
				//ImpostoareaTestoOfferta
				try {
					
						areaTestoOfferta = controllore.mostraListaOffertaInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, via);
					
				} catch (IDEsternoElementoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				} catch (TrattaInesistenteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
				areaTestoPannello2.setText(areaTestoImp + areaTestoCatalogo + areaTestoOfferta);
				
					
			
			}
			
		}
		
	}
	
		private class AggiungiAA implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {

				if (tendinaViaPannello2.getItemCount() != 0 && !viaScelta.equals("-----")){
					String via = (String) tendinaViaPannello2.getSelectedItem();
					
					int giorno = Integer.parseInt(campoGiornoPannello2.getText());
					int mese = (Integer) tendinaMesePannello2.getSelectedItem();
					int anno = (Integer) tendinaAnnoPannello2.getSelectedItem();
					int ora = (Integer) tendinaOrePannello2.getSelectedItem();
					int minuto = Integer.parseInt((String) tendinaMinutoPannello2.getSelectedItem());
					int durata = Integer.parseInt(campoDurataPannello2.getText());
					int posti = Integer.parseInt(campoPostiPannello2.getText());
					
					Integer[] data = {giorno, mese, anno, ora, minuto};
					
					
					controlloSintatticoDati();
					
					// chiedo conferma
					int conferma = JOptionPane.showConfirmDialog(null, "Aggiungere l'offerta per il viaggio?", "Conferma Aggiunta Offerta", JOptionPane.YES_NO_OPTION);
					if (conferma == JOptionPane.YES_OPTION){
						
						//aggiungo l'offerta
						try {
							
							controllore.aggiungiOfferta(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, via, data, durata, posti);
							aggiornaTendinePannello2();   //aggiorno il pannello 2
						} catch (TrattaInesistenteException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						} catch (IDEsternoElementoException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						} catch (OffertaException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				}
				
			}

		}
	
	private class SvuotaPannello2AA implements ActionListener{
		/*
		 * Svuota tutti i campi
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (tendinaAmbientePannello2.isEnabled()){
								
				tendinaAmbientePannello2.setSelectedIndex(0); //la tendina torna al primo valore.
				
				if (tendinaAmbientePannello2.getItemCount()>1){
					tendinaMezziPannello2.removeAllItems();  //svuota le tendine
					tendinaMezziPannello2.setEnabled(false);//disattiva tutte le tendine

					ambienteScelto = null;
					mezzoScelto = null;
					partenzaScelta = null;
					arrivoScelto = null;
					viaScelta = null;

				}
				
			campoGiornoPannello2.setText("");
			campoGiornoPannello2.setEditable(false);
			
			tendinaMesePannello2.setSelectedIndex(0);
			tendinaMesePannello2.setEnabled(false);
			
			tendinaAnnoPannello2.setSelectedIndex(0);
			tendinaAnnoPannello2.setEnabled(false);
			
			tendinaOrePannello2.setSelectedIndex(0);
			tendinaOrePannello2.setEnabled(false);
			
			tendinaMinutoPannello2.setSelectedIndex(0);
			tendinaMinutoPannello2.setEnabled(false);
			
			campoDurataPannello2.setText("");
			campoDurataPannello2.setEditable(false);
			
			campoPostiPannello2.setText("");
			campoPostiPannello2.setEditable(false);
			
			areaTestoPannello2.setText("");
			
				
		//	} else {
		//		JOptionPane.showMessageDialog(null, "Nessun viaggio in catalogo!", "Catalogo Vuoto", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		
	}
	
	private class ChiudiPannello2AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel2.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiOfferta.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviOfferta.setEnabled(true);
			bottoneChiudiPannello1.setEnabled(true);
			
			//svuoto comunque il pannello
			if (tendinaAmbientePannello2.getItemCount() != 0){
				
				tendinaAmbientePannello2.setSelectedIndex(0); //la tendina torna al primo valore.
				tendinaMezziPannello2.removeAllItems();  //svuota le tendine
				tendinaMezziPannello2.setEnabled(false);//disattiva tutte le tendine

			}
			
			ambienteScelto = null;
			mezzoScelto = null;
			partenzaScelta = null;
			arrivoScelto = null;
			viaScelta = null;
			
			campoGiornoPannello2.setText("");
			campoGiornoPannello2.setEditable(false);
			
			tendinaMesePannello2.setSelectedIndex(0);
			tendinaMesePannello2.setEnabled(false);
			
			tendinaAnnoPannello2.setSelectedIndex(0);
			tendinaAnnoPannello2.setEnabled(false);
			
			tendinaOrePannello2.setSelectedIndex(0);
			tendinaOrePannello2.setEnabled(false);
			
			tendinaMinutoPannello2.setSelectedIndex(0);
			tendinaMinutoPannello2.setEnabled(false);
			
			campoDurataPannello2.setText("");
			campoDurataPannello2.setEditable(false);
			
			campoPostiPannello2.setText("");
			campoPostiPannello2.setEditable(false);
			
			areaTestoPannello2.setText("");

		}
	}
	
	/*
	 * Ascoltatori per Pannello3
	 */
	
	
	private class TendinaAmbientePannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto l'area testo
			areaTestoPannello3.setText("");
			
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
					
	
			}
		
		}
		
	}
	
	
	private class TendinaMezziPannello3AA implements ActionListener{
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto l'area testo
			areaTestoPannello3.setText("");
			
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
						
	
			}
		
		}
		
	}
	
	private class TendinaPartenzePannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto l'area testo
			areaTestoPannello3.setText("");
			
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
			
			}
			
		}
		
	}
	
	private class TendinaArriviPannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Svuoto l'area testo
			areaTestoPannello3.setText("");
			
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
	
			
			ambienteScelto = (String) tendinaAmbientePannello3.getSelectedItem();
			mezzoScelto = (String) tendinaMezziPannello3.getSelectedItem();
			partenzaScelta = (String) tendinaCittaPartenzaPannello3.getSelectedItem();
			arrivoScelto = (String)tendinaCittaArrivoPannello3.getSelectedItem();
			
				
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
			
			//Svuoto tutte le tendine e l'area testo
			tendinaOffertaPannello3.removeAllItems();
			tendinaOffertaPannello3.setEnabled(false);
			
			areaTestoPannello3.setText("");
			areaTestoOfferta="";
			
			
			
			if (tendinaViaPannello3.getItemCount() != 0) {
				try {
					
					if (!viaScelta.equals("-----")){
						areaTestoCatalogo = ambienteScelto + " " + mezzoScelto + " " + partenzaScelta + " : " + arrivoScelto + " -> " + viaScelta + "\n";
						
						
						Set<Data> set = controllore.mostraOffertePerLaTratta(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
						if(set.size() == 0){	
					    	areaTestoOfferta = "Non ci sono offerte per la tratta";
					    } else {
					    	
						    Iterator<Data> it = set.iterator();
						    if(set.size() > 1){
								//inserisco l'elemento neutro
								tendinaOffertaPannello3.addItem("-----");
							}
						    
						    while(it.hasNext()){
						    	Data d = it.next();
						    	//inserisco l'elemento in tendina
						    	tendinaOffertaPannello3.addItem(d.stampaData());
						    }
						    
						    tendinaOffertaPannello3.setEnabled(true);
						    tendinaOffertaPannello3.setSelectedIndex(0);
					    }
						
						
					}
					
					
					//Imposto areatestoCatalogo
					areaTestoCatalogo = ambienteScelto + " " + mezzoScelto + " " + partenzaScelta + " : " + arrivoScelto + " -> " + viaScelta + "\n\n"  +
										"ORA PARTENZA\tORA ARRIVO\t\tPOSTI\n" +
										"-----------\t\t----------\t\t----------\n";
					
					//ImpostoareaTestoOfferta
					areaTestoOfferta = controllore.mostraListaOffertaInCatalogo(ambienteScelto, mezzoScelto, partenzaScelta, arrivoScelto, viaScelta);
					
					areaTestoPannello3.setText(areaTestoImp + areaTestoCatalogo + areaTestoOfferta);
					
						
				
				
					
				} catch (IDEsternoElementoException e1) {
					areaTestoPannello3.setText(e1.getMessage()+"\n");
				} catch (TrattaInesistenteException e) {
					areaTestoPannello3.setText(e.getMessage()+"\n");
				} 
				
			} 
			
		}
		
	}
	
	
	private class RimuoviAA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			/* DA IMPLEMENTARE
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
					} catch (TrattaException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} catch (OffertaException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), e1.toString(), JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
					
			} else {
				JOptionPane.showMessageDialog(null, "Nessun viaggio selezionato!");
			} */
			
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
			
			areaTestoPannello3.setText("");
			
				
		//	} else {
		//		JOptionPane.showMessageDialog(null, "Nessun viaggio in catalogo!", "Catalogo Vuoto", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		
	}
	
	private class ChiudiPannello3AA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			panel3.setVisible(false); 					//chiude questo pannello
			bottoneAggiungiOfferta.setEnabled(true);	//riattiva i bottoni
			bottoneRimuoviOfferta.setEnabled(true);
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
			offertaScelta = null;
			
			areaTestoPannello3.setText("");

		}
	}

}
