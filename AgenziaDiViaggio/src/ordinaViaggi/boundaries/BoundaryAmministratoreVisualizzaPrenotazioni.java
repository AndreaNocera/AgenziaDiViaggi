package ordinaViaggi.boundaries;

import ordinaViaggi.control.ControlloreAmministratore;
import ordinaViaggi.exception.ControllerException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Gambella Riccardo Boundary Amministratore Ordina Viaggi.
 */

public class BoundaryAmministratoreVisualizzaPrenotazioni extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4501877161965740674L;
	// Controllore associato
	private ControlloreAmministratore controlloreAmministratore = null;
	private BoundaryAmministratoreVisualizzaOfferta boundaryAmministratoreVisualizzaOfferta = null;
	private BoundaryAmministratoreVisualizzaPrenotazioni boundaryAmministratoreVisualizzaPrenotazioni = this;

	public static JPanel pannelloVisualizzaPrenotazioni;
	public JPanel panelTitolo;
	public JPanel panelComboBox;

	public JLabel titolo;
	public JLabel labelComboBox;

	public JTextArea prenotazioni;

	public JButton visualizzaPrenotazioni;

	public JButton backGestoreOrdinaViaggio;

	// Bottoni
	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	// Costanti -> Salvare meglio da altre parti..Ex file conf
	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreVisualizzaPrenotazioni(BoundaryAmministratoreVisualizzaOfferta boundaryAmministratoreVisualizzaOfferta) {
		this.controlloreAmministratore = ControlloreAmministratore.getIstance();
		this.boundaryAmministratoreVisualizzaOfferta = boundaryAmministratoreVisualizzaOfferta;

		/*
		 * Comparsa del pannello Ordina viaggio Aggiunge al frame il pannello
		 * Ordina Viaggio e lo rende l'unico visibile.
		 */

		pannelloVisualizzaPrenotazioni = new JPanel();

		/* Impostazioni pannelloVisualizzaPrenotazioni */
		pannelloVisualizzaPrenotazioni.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloVisualizzaPrenotazioni);
		pannelloVisualizzaPrenotazioni.setLayout(null);

		panelTitolo = new JPanel();
		panelComboBox = new JPanel();

		titolo = new JLabel();
		labelComboBox = new JLabel();

		prenotazioni = new JTextArea();

		visualizzaPrenotazioni = new JButton("Visualizzazione Prenotazioni");
		backGestoreOrdinaViaggio = new JButton("back");

		/* Istanziazione dei Listeners */
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		visualizzaPrenotazioni.addActionListener(buttonsListener);
		backGestoreOrdinaViaggio.addActionListener(backListener);

		/* Setting dei pannelli */
		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		panelComboBox.setLayout(null);
		panelComboBox.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelComboBox.setLocation(5, 50);
		panelComboBox.add(labelComboBox);
		panelComboBox.add(visualizzaPrenotazioni);
		panelComboBox.add(backGestoreOrdinaViaggio);

		/* Setting dei Componenti */
		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Visualizza offerta.");

		labelComboBox.setFont(new Font("Arial", 0, 30));
		labelComboBox.setLocation(border, 30);
		labelComboBox.setSize(panelComboBox.getWidth(), 35);
		labelComboBox.setHorizontalAlignment(JLabel.CENTER);
		labelComboBox.setVerticalAlignment(JLabel.CENTER);
		labelComboBox.setText("Prenotazioni relative al viaggio selezionato.");

		visualizzaPrenotazioni.setLocation(100, 200);
		visualizzaPrenotazioni.setSize(300, 35);
		visualizzaPrenotazioni.setFont(new Font("Arial", 0, 20));

		backGestoreOrdinaViaggio.setLocation(100, 300);
		backGestoreOrdinaViaggio.setSize(200, 35);
		backGestoreOrdinaViaggio.setFont(new Font("Arial", 0, 20));

		/* Add dei pannelli al pannello principale */
		pannelloVisualizzaPrenotazioni.add(panelTitolo);
		pannelloVisualizzaPrenotazioni.add(panelComboBox);

	}

	private class GestoreButtons implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			List<String> mapList = new ArrayList<String>();
			
			/*
			 * Visualizza le prenotazioni relative al viaggio selezionato.
			 * Preleva i dati immessi nelle comboBox.
			 */
			if (event.getSource() == visualizzaPrenotazioni) {
				List<String> listaOfferta = boundaryAmministratoreVisualizzaOfferta
						.prelevaComboBox();
				try {
					controlloreAmministratore.estrazioneLista(listaOfferta,
							mapList);
					/*
					 * Scendo nella mappa successiva in quanto non mi
					 * interessano i posti. Successivamente sarebbe
					 * consigliabile integrare i posti nel SubElement.
					 */
					System.out.println("Insieme di prenotazioni: ");
					for (String key : mapList){
						List<String> datiTraveler;
						datiTraveler = controlloreAmministratore
								.getTicket(listaOfferta, key);
						System.out.println("Id Prenotazione: " + key);
						for(String dato: datiTraveler)
							System.out.println(dato);
					}
					
				} catch (ControllerException e) {
					// TODO Blocco catch generato automaticamente
					e.printStackTrace();
				}

			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == backGestoreOrdinaViaggio) {
				pannelloVisualizzaPrenotazioni.setVisible(false);
				BoundaryAmministratoreVisualizzaOfferta.pannelloVisualizzaOfferta
						.setVisible(true);
			}
		}
	}
}
