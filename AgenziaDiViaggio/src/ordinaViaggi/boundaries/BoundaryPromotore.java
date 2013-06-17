package ordinaViaggi.boundaries;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ordinaViaggi.control.ControllorePromotore;
import ordinaViaggi.exception.MapDAOException;

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

	private ControllorePromotore controllorePromotore =null;
	
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
	
	public BoundaryPromotore(ControllorePromotore controllorePromotore)   //Qui parte la Deserializzazione
	{
		
		
		this.controllorePromotore = controllorePromotore;
				
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
						ControllorePromotore.printCatalogo();
					} catch (MapDAOException e) {
						// TODO Blocco catch generato automaticamente
						e.printStackTrace();
					}
			}
			else if(event.getSource() == inserisciCatalogo){
					
					//Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo da StdIn
					String ambiente = null;
					String mezzo = null;
					String cittaPartenza = null;
					String cittaArrivo = null;
					String via = null;
					
					try {
						BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
						System.out.print("Inserire l'ambiente.\r\n");
						ambiente = input.readLine();
						System.out.print("Inserire il mezzo.\r\n");
						mezzo = input.readLine();
						System.out.print("Inserire la città di partenza.\r\n");
						cittaPartenza = input.readLine();
						System.out.print("Inserire la città di arrivo.\r\n");
						cittaArrivo = input.readLine();
						System.out.println("Inserire la via (Tratte intermedie)");
						via = input.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					try {
						/* Mockup. Data per inserite le chiavi e le info dei subElements */
						List<String> listaCatalogo = new ArrayList<String>();
						listaCatalogo.add(ambiente);
						listaCatalogo.add(mezzo);
						listaCatalogo.add(cittaPartenza);
						listaCatalogo.add(cittaArrivo);
						listaCatalogo.add(via);
						
						/* Info dei subElements */
						List<String> subElementsInfo = new ArrayList<String>();
						subElementsInfo.add("");
						subElementsInfo.add("");
						subElementsInfo.add("");
						subElementsInfo.add("");
						subElementsInfo.add("");
						
						
						ControllorePromotore.inserimentoInCatalogo(listaCatalogo, subElementsInfo);
					} catch (MapDAOException e) {
						// TODO Blocco catch generato automaticamente
						e.printStackTrace();
					}
					
					System.out.println("Stampa mappa Finale\n");
					
						try {
							ControllorePromotore.printCatalogo();
						} catch (MapDAOException e) {
							// TODO Blocco catch generato automaticamente
							e.printStackTrace();
						}
				}
			
			else if(event.getSource() == rimuoviCatalogo){
					System.out.println("Rimozione nel catalogo");
					
					//Richiesta rimozione di Ambiente, Mezzo, CittaPartenza, CittaArrivo da StdIn
					String ambiente = null;
					String mezzo = null;
					String cittaPartenza = null;
					String cittaArrivo = null;
					String via = null;
					
					try {
						BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
						System.out.print("Inserire l'ambiente.\r\n");
						ambiente = input.readLine();
						System.out.print("Inserire il mezzo.\r\n");
						mezzo = input.readLine();
						System.out.print("Inserire la città di partenza.\r\n");
						cittaPartenza = input.readLine();
						System.out.print("Inserire la città di arrivo.\r\n");
						cittaArrivo = input.readLine();
						System.out.println("Inserire la via (Tratte intermedie)");
						via = input.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					try {
						/* Mockup. Data per inserite le chiavi e le info dei subElements */
						List<String> listaCatalogo = new ArrayList<String>();
						listaCatalogo.add(ambiente);
						listaCatalogo.add(mezzo);
						listaCatalogo.add(cittaPartenza);
						listaCatalogo.add(cittaArrivo);
						listaCatalogo.add(via);
						
						ControllorePromotore.rimozioneInCatalogo(listaCatalogo);
					} catch (MapDAOException e) {
						// TODO Blocco catch generato automaticamente
						e.printStackTrace();
					}
					
					try {
						ControllorePromotore.printCatalogo();
					} catch (MapDAOException e) {
						// TODO Blocco catch generato automaticamente
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





















