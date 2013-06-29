package ordinaViaggi.boundaries;

import ordinaViaggi.control.ControllorePromotore;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.MapException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Gambella Riccardo
 * Boundary Promotore.
 */
public class BoundaryPromotore extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControllorePromotore controllorePromotore = null;
	
	public static JPanel  pannelloPromotore;

	//Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();
	
	
	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();
	
	public JLabel titolo = new JLabel();
	
	//Bottone
	public JButton visualizzaCatalogo; 
	public JButton inserisciCatalogo;
	public JButton rimuoviCatalogo;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;
	
	int border = 5;
	int altezzaTitolo = 30;
	
	public BoundaryPromotore()   //Qui parte la Deserializzazione
	{
		
		
		this.controllorePromotore = ControllorePromotore.getIstance();
				
		pannelloPromotore = new JPanel();
		
		pannelloPromotore.setSize(AABoundaryAvvio.Frame.getWidth(), AABoundaryAvvio.Frame.getHeight());	
		AABoundaryAvvio.Frame.add(pannelloPromotore);
		pannelloPromotore.setLayout(null);
		
		        
        panelTitolo.setLayout(null);
        panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Arial", 0, 30));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText("Gestore promotore");
        
        pannelloPromotore.add(panelTitolo);

		// Bottone visualizzaCatalogo
		visualizzaCatalogo 	= new JButton("Visualizza Catalogo");
		visualizzaCatalogo.setLocation(100,100);
		visualizzaCatalogo.setSize(panelTitolo.getWidth()/4, 50);
		visualizzaCatalogo.setFont(new Font("Arial", 0, 20));
		
		//Bottone back
		back = new JButton("back");
		back.setLocation(500, 300);
		back.setSize(panelTitolo.getWidth()/4, 50);
		back.setFont(new Font("Arial", 0, 20));
		
		//Bottone inserisciCatalogo
		inserisciCatalogo 	= new JButton("Inserisci in Catalogo");
		inserisciCatalogo.setLocation(100, 200);
		inserisciCatalogo.setSize(panelTitolo.getWidth()/4, 50);
		inserisciCatalogo.setFont(new Font("Arial", 0, 20));
		
		rimuoviCatalogo 	= new JButton("Rimozione Catalogo");
		rimuoviCatalogo.setLocation(100,300);
		rimuoviCatalogo.setSize(panelTitolo.getWidth()/4, 50);
		rimuoviCatalogo.setFont(new Font("Arial", 0, 20));
		
		
		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(), 350);
		panelButtons.setLocation(5, altezzaTitolo); 
		
		
		panelButtons.add(visualizzaCatalogo);
		panelButtons.add(inserisciCatalogo);
		panelButtons.add(rimuoviCatalogo);
		
		
		panelButtons.add(back);
		
		pannelloPromotore.add(panelButtons);
		
		//Istanziazione dei Listeners
		buttonsListener	= new GestoreButtons();
		backListener = new GestoreBack();
	  	
		//Listener dei bottoni
		visualizzaCatalogo.addActionListener(buttonsListener);
		inserisciCatalogo.addActionListener(buttonsListener);
		rimuoviCatalogo.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}
	
	
	private class GestoreButtons implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Stub di metodo generato automaticamente
			if(event.getSource() == visualizzaCatalogo){
				System.out.println("Catalogo presente.");
					try {
						controllorePromotore.printCatalogo();
					} catch (ControllerException e) {
						// TODO Blocco catch generato automaticamente
						e.printStackTrace();
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MapException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CatalogoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else if(event.getSource() == inserisciCatalogo){
					
						try {
							controllorePromotore.inserimentoCatalogo();
						} catch (ControllerException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MapException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (CatalogoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
			
			else if(event.getSource() == rimuoviCatalogo){
						
					try {
						controllorePromotore.rimozioneInCatalogo();
					} catch (ControllerException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MapException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CatalogoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
	
	private class GestoreBack implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			pannelloPromotore.setVisible(false);
			AABoundaryAvvio.pannello.setVisible(true);
		}
	}
}





















