package ordinaViaggi.boundaries;

import ordinaViaggi.control.ControlloreCliente;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.MapDAOException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo
 * Boundary Cliente Visualizza Offerta.
 */

public class BoundaryClientePrenotazioneViaggio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9140363044036613215L;
	// Controllore associato
	private ControlloreCliente controlloreCliente = null;
	// Boundary Precedenti
	private BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi = null;
	private BoundaryClienteVisualizzaOfferta boundaryClienteVisualizzaOfferta = null;
	//Componenti
	public static JPanel pannelloPrenotazioneViaggio = new JPanel();
	public JPanel panelTitolo;
	public JPanel panelInserimento;

	public JLabel titolo;
	public JLabel labelInserimento;
	public JLabel labelNome;
	public JLabel labelCognome;
	public JLabel labelIdPrenotazione;

	public JTextField nome;
	public JTextField cognome;
	public JTextField idPrenotazione;
	
	public JButton prenotazioneViaggio;
	public JButton backGestorePrenotazioneViaggio;

	// Bottoni
	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	// Costanti -> Salvare meglio da altre parti..Ex file conf
	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryClientePrenotazioneViaggio(
			BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi,
			BoundaryClienteVisualizzaOfferta boundaryClienteVisualizzaOfferta) {
		this.controlloreCliente = ControlloreCliente.getIstance();
		this.boundaryClienteOrdinaViaggi = boundaryClienteOrdinaViaggi;
		this.boundaryClienteVisualizzaOfferta = boundaryClienteVisualizzaOfferta;

		/*
		 * Comparsa del pannello Ordina viaggio Aggiunge al frame il pannello
		 * Ordina Viaggio e lo rende l'unico visibile.
		 */

		pannelloPrenotazioneViaggio = new JPanel();

		/* Impostazioni pannelloOrdinaViaggio */
		pannelloPrenotazioneViaggio.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloPrenotazioneViaggio);
		pannelloPrenotazioneViaggio.setLayout(null);

		panelTitolo = new JPanel();
		panelInserimento = new JPanel();

		titolo = new JLabel();
		labelInserimento = new JLabel();
		labelNome = new JLabel();
		labelCognome = new JLabel();
		labelIdPrenotazione = new JLabel();
		
		nome = new JTextField("",20);
		cognome = new JTextField("",20);
		idPrenotazione = new JTextField("",20);
		

		prenotazioneViaggio = new JButton("Prenotazione Viaggio");
		backGestorePrenotazioneViaggio = new JButton("back");


		/* Istanziazione dei Listeners */
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		/* Setting dei Listener */
		prenotazioneViaggio.addActionListener(buttonsListener);
		backGestorePrenotazioneViaggio.addActionListener(backListener);

		/* Setting dei pannelli */
		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		panelInserimento.setLayout(null);
		panelInserimento.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelInserimento.setLocation(5, 50);
		panelInserimento.add(labelInserimento);
		panelInserimento.add(labelNome);
		panelInserimento.add(labelCognome);
		panelInserimento.add(labelIdPrenotazione);
		panelInserimento.add(nome);
		panelInserimento.add(cognome);
		panelInserimento.add(idPrenotazione);
		panelInserimento.add(prenotazioneViaggio);
		panelInserimento.add(backGestorePrenotazioneViaggio);
		
		

		/* Setting dei Componenti */
		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Prenotazione Viaggio.");

		labelInserimento.setFont(new Font("Arial", 0, 30));
		labelInserimento.setLocation(border, 30);
		labelInserimento.setSize(panelInserimento.getWidth(), 35);
		labelInserimento.setHorizontalAlignment(JLabel.CENTER);
		labelInserimento.setVerticalAlignment(JLabel.CENTER);
		labelInserimento.setText("Inserisci i dati del passeggero.");
		
		labelNome.setFont(new Font("Arial", 0, 18));
		labelNome.setLocation(50, 100);
		labelNome.setSize(100, 35);
		labelNome.setText("Nome:");
		
		nome.setLocation(150, 100);
		nome.setSize(100, 35);
		nome.setFont(new Font("Arial",0,18));
		
		labelCognome.setFont(new Font("Arial", 0, 18));
		labelCognome.setLocation(50, 150);
		labelCognome.setSize(100, 35);
		labelCognome.setText("Cognome:");
		
		cognome.setLocation(150, 150);
		cognome.setSize(100, 35);
		cognome.setFont(new Font("Arial",0,18));
		
		labelIdPrenotazione.setFont(new Font("Arial", 0, 18));
		labelIdPrenotazione.setLocation(50, 200);
		labelIdPrenotazione.setSize(100, 35);
		labelIdPrenotazione.setText("Id: ");
		
		idPrenotazione.setLocation(150, 200);
		idPrenotazione.setSize(100, 35);
		idPrenotazione.setFont(new Font("Arial",0,18));

		prenotazioneViaggio.setLocation(100, 300);
		prenotazioneViaggio.setSize(300, 35);
		prenotazioneViaggio.setFont(new Font("Arial", 0, 20));

		backGestorePrenotazioneViaggio.setLocation(100, 400);
		backGestorePrenotazioneViaggio.setSize(200, 35);
		backGestorePrenotazioneViaggio.setFont(new Font("Arial", 0, 20));

		/* Add dei pannelli al pannello principale */
		pannelloPrenotazioneViaggio.add(panelTitolo);
		pannelloPrenotazioneViaggio.add(panelInserimento);

	}
	
	private class GestoreButtons implements ActionListener {
		/**
		 *  Va Salvato il biglietto
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == prenotazioneViaggio) {
				List<String> dati = new ArrayList<String>();
				dati.add(nome.getText());
				dati.add(cognome.getText());
				if(!controlloreCliente.verificaDatiTraveler(dati))
					System.out.println("Dati non inseriti correttamente.");
				else{
					List<String> listaCatalogo = boundaryClienteOrdinaViaggi.prelevaComboBoxCatalogo();
					List<String> listaOfferta = boundaryClienteVisualizzaOfferta.prelevaComboBoxOfferta();
					//Componi la lista totale
					for(String key : listaOfferta)
						listaCatalogo.add(key);
					//Poi dovrà essere generata automaticamente a partire dalle altre presenti nella mappa.
					String id = idPrenotazione.getText();
					
					try {
						controlloreCliente.inserimentoBiglietto(listaCatalogo, id,
								dati);
					} catch (ControllerException e) {
						// TODO Blocco catch generato automaticamente
						e.printStackTrace();
					}
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == backGestorePrenotazioneViaggio) {
				pannelloPrenotazioneViaggio.setVisible(false);
				BoundaryClienteVisualizzaOfferta.pannelloVisualizzaOfferta
						.setVisible(true);
			}
		}
	}
}
