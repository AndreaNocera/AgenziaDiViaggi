package ordinaViaggi.boundaries;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ordinaViaggi.control.ControlloreAmministratore;
import ordinaViaggi.control.ControlloreCliente;
import ordinaViaggi.control.ControlloreProgettista;
import ordinaViaggi.control.ControllorePromotore;

/**
 * 
 * @author Gambella Riccardo
 * Boudary di Avvio.
 *
 */
	
	public class AABoundaryAvvio extends JFrame
	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7371499973005380451L;

		//Pannelli
		
		public static JPanel pannello = new JPanel();	
		
		public JPanel panelTitolo = new JPanel();
		public JPanel panelButtons = new JPanel();
		
		public JLabel titolo = new JLabel();
		//Bottoni/
		public JButton cliente = new JButton("Gestione Cliente");
		public JButton promotore = new JButton("Gestione Promotore"); 
		public JButton progettista = new JButton("Gestione Progettista"); 
		public JButton amministratore = new JButton("Gestione Amministratore");
		 
		
		// Ascoltatori di bottoni e relative azioni
		private Gestore gestoreListener;
		
		// Variabili statiche utili per tutti
		public static JFrame Frame;
		


		public AABoundaryAvvio()
		{
			
			//Salvare solo ascendenti
			Frame = this;
			this.setTitle("Boundary Avvio");
			
			Frame.setLayout(null);
			setSize(900, 600);
			Dimension dim = getToolkit().getScreenSize();
	        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        setResizable(false);
	        
	        pannello.setSize(Frame.getWidth(), Frame.getHeight());	
	        this.add(pannello);
	        pannello.setLayout(null);
	       
	        int border = 5;
			int altezzaTitolo = 30;
	       	
	        /*Title Panel*/
	         
	        panelTitolo.setLayout(null);
	        panelTitolo.setSize(this.getWidth(), altezzaTitolo*3);
	        panelTitolo.setLocation(5, 5);  
	        //panelTitolo.setBounds(0, 0, 800, 400); 
	        panelTitolo.add(titolo);
	        
	        titolo.setFont(new Font("Arial", 0, 20));
	        titolo.setLocation(border, border);
	        titolo.setSize(panelTitolo.getWidth(), 30);
	        titolo.setHorizontalAlignment(JLabel.CENTER);
	        titolo.setVerticalAlignment(JLabel.CENTER);
	        titolo.setText("Voyager");
	        
	        
	        panelButtons.setLayout(null);
	        panelButtons.setSize(getWidth(), getHeight());
	        panelButtons.setLocation(5, altezzaTitolo);
	        
	        panelButtons.add(promotore);
	        panelButtons.add(progettista);
	        panelButtons.add(cliente);
	        panelButtons.add(amministratore);
	        promotore.setBounds(100, 100, 200, 60);
	        progettista.setBounds(100, 200, 200, 60);
	        cliente.setBounds(100, 300, 200, 60);
	        amministratore.setBounds(100, 400, 200, 60);
	            
	        /* Add panel of title and buttons*/
	        pannello.add(panelTitolo);
	        pannello.add(panelButtons);

			
			// Definisci ascoltatori di bottoni e relative azioni
			gestoreListener	= new Gestore();
		
			
		    // Associa ascoltatori e bottoni
			promotore.addActionListener(gestoreListener);
			progettista.addActionListener(gestoreListener);
			cliente.addActionListener(gestoreListener);
			amministratore.addActionListener(gestoreListener);

			//Rendi visibile questo frame;			
			this.setVisible(true);	
			
		}
		// Fine costruttore
			
		/* Deve far partire la boundary del promotore
		 * 
		 */
		private class Gestore implements ActionListener
		{
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == promotore){
					pannello.setVisible(false);
					ControllorePromotore.gestionePromotore();	
				}
				else if(e.getSource() == cliente){
					pannello.setVisible(false);
					ControlloreCliente.gestioneCliente();
				}
				else if(e.getSource() == progettista){
					pannello.setVisible(false);
					ControlloreProgettista.gestioneProgettista();
				}
				else if(e.getSource() == amministratore){
					pannello.setVisible(false);
					ControlloreAmministratore.gestioneAmministratore();
				}
			}
		}	
}
	
	
	
	
	
	