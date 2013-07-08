package ispw.boundaries;

import ispw.control.ControlloreAmministratore;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Gambella Riccardo Boundary ProgettistaInserimentoCatalogo.
 */
public class BoundaryAmministratoreVisualizzaPrenotazioni extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta = null;
	BoundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta = null;
	ControlloreAmministratore controlloreAmministratore = null;

	public static JPanel pannelloAmministratoreVisualizzaPrenotazioni;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelPrenotazione = new JPanel();

	public JLabel titolo = new JLabel();

	public JTextArea areaVisualizzazione;

	// Bottone
	public JButton back;

	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreVisualizzaPrenotazioni(
			BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta,
			BoundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta = boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta;
		this.boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta = boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta;
		this.controlloreAmministratore = ControlloreAmministratore.getInstance();

		pannelloAmministratoreVisualizzaPrenotazioni = new JPanel();

		pannelloAmministratoreVisualizzaPrenotazioni.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministratoreVisualizzaPrenotazioni);
		pannelloAmministratoreVisualizzaPrenotazioni.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Amministratore");

		pannelloAmministratoreVisualizzaPrenotazioni.add(panelTitolo);

		// Setting area
		areaVisualizzazione = new JTextArea();

		areaVisualizzazione.setLocation(10, 50);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(), 350);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelPrenotazione.setLayout(null);
		panelPrenotazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelPrenotazione.setLocation(5, altezzaTitolo);

		panelPrenotazione.add(back);
		panelPrenotazione.add(areaVisualizzazione);

		pannelloAmministratoreVisualizzaPrenotazioni.add(panelPrenotazione);

		backListener = new GestoreBack();

		// Listener dei bottoni
		back.addActionListener(backListener);

		// Inizializzazione dell'area visualizzazione

		List<String> listaCatalogo = boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta
				.prelevaComboBoxCatalogo();
		Integer idOfferta = boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta
				.getIdOfferta();
		List<String> prenotazioni = controlloreAmministratore
				.visualizzaPrenotazioni(listaCatalogo, idOfferta);
		for (String prenotazione : prenotazioni) {
			areaVisualizzazione.append(prenotazione);
		}

	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloAmministratoreVisualizzaPrenotazioni.setVisible(false);
			BoundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta.pannelloAmministatoreSceltaOfferta
					.setVisible(true);
		}
	}
}
