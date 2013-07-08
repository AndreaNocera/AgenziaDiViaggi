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
public class BoundaryAmministratoreVisualizzaUtenti extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	ControlloreAmministratore controlloreAmministratore = null;

	public static JPanel pannelloAmministratoreVisualizzaUtenti;

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

	public BoundaryAmministratoreVisualizzaUtenti() throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException{

		this.controlloreAmministratore = ControlloreAmministratore.getInstance();

		pannelloAmministratoreVisualizzaUtenti = new JPanel();

		pannelloAmministratoreVisualizzaUtenti.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministratoreVisualizzaUtenti);
		pannelloAmministratoreVisualizzaUtenti.setLayout(null);

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

		pannelloAmministratoreVisualizzaUtenti.add(panelTitolo);

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

		pannelloAmministratoreVisualizzaUtenti.add(panelPrenotazione);

		backListener = new GestoreBack();

		// Listener dei bottoni
		back.addActionListener(backListener);

		// Inizializzazione dell'area visualizzazione

		List<String> listaUtenti = controlloreAmministratore
				.visualizzaUtenti();
		areaVisualizzazione.setText("");
		for (String utente : listaUtenti) {
			areaVisualizzazione.append(utente + "\n");
		}

	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloAmministratoreVisualizzaUtenti.setVisible(false);
			BoundaryAmministratoreGestioneUtenti.pannelloGestioneUtenti
					.setVisible(true);
		}
	}
}
