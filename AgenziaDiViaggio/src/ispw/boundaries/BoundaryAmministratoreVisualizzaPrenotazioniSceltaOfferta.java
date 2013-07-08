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
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo Boundary ProgettistaInserimentoCatalogo.
 */
public class BoundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta extends
		JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreAmministratore controlloreAmministratore = null;
	BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta = null;
	BoundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta = null;

	public static JPanel pannelloAmministatoreSceltaOfferta;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelVisualizza = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelGiorno;
	public JLabel labelMese;
	public JLabel labelAnno;
	public JLabel labelPrenotazioni = new JLabel();

	public JTextField giorno;
	public JTextField mese;
	public JTextField anno;
	public JTextField offertaInserita;

	public JTextArea areaVisualizzazione;

	// Bottone
	public JButton visualizzaOfferte;
	public JButton visualizzaPrenotazioni;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta(
			BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.controlloreAmministratore = ControlloreAmministratore.getInstance();
		this.boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta = boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta;
		this.boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta = this;

		pannelloAmministatoreSceltaOfferta = new JPanel();

		pannelloAmministatoreSceltaOfferta.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministatoreSceltaOfferta);
		pannelloAmministatoreSceltaOfferta.setLayout(null);

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

		pannelloAmministatoreSceltaOfferta.add(panelTitolo);

		labelGiorno = new JLabel();
		labelMese = new JLabel();
		labelAnno = new JLabel();
		giorno = new JTextField("", 20);
		mese = new JTextField("", 20);
		anno = new JTextField("", 20);

		// Setting label
		labelGiorno.setFont(new Font("Arial", 0, 18));
		labelGiorno.setLocation(50, 100);
		labelGiorno.setSize(50, 35);
		labelGiorno.setText("GG:");

		labelMese.setFont(new Font("Arial", 0, 18));
		labelMese.setLocation(150, 100);
		labelMese.setSize(50, 35);
		labelMese.setText("MM:");

		labelAnno.setFont(new Font("Arial", 0, 18));
		labelAnno.setLocation(250, 100);
		labelAnno.setSize(100, 35);
		labelAnno.setText("AAAA:");

		labelPrenotazioni.setFont(new Font("Arial", 0, 18));
		labelPrenotazioni.setLocation(
				AABoundaryAvvio.Frame.getWidth() / 2 + 100, 200);
		labelPrenotazioni.setSize(300, 35);
		labelPrenotazioni
				.setText("Inserisci l'id dell'offerta:");

		// Setting delle textBox
		giorno.setLocation(100, 100);
		giorno.setSize(40, 35);
		giorno.setFont(new Font("Arial", 0, 18));

		mese.setLocation(200, 100);
		mese.setSize(40, 35);
		mese.setFont(new Font("Arial", 0, 18));

		anno.setLocation(300, 100);
		anno.setSize(60, 35);
		anno.setFont(new Font("Arial", 0, 18));

		offertaInserita = new JTextField("", 20);
		offertaInserita.setLocation(AABoundaryAvvio.Frame.getWidth() / 2 + 100,
				250);
		offertaInserita.setSize(200, 35);
		offertaInserita.setFont(new Font("Arial", 0, 18));

		// Setting Buttons
		visualizzaOfferte = new JButton("Visualizza offerte");
		visualizzaOfferte.setLocation(500, 100);
		visualizzaOfferte.setSize(300, 35);
		visualizzaOfferte.setFont(new Font("Arial", 0, 20));

		visualizzaPrenotazioni = new JButton("Visualizza prenotazioni.");
		visualizzaPrenotazioni.setLocation(100, 400);
		visualizzaPrenotazioni.setSize(300, 50);
		visualizzaPrenotazioni.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione
				.setText("Inserire la data per ottenere le offerte.");
		areaVisualizzazione.setLocation(10, 200);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth() / 2, 200);

		panelVisualizza.setLayout(null);
		panelVisualizza.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelVisualizza.setLocation(5, altezzaTitolo);

		panelVisualizza.add(back);
		panelVisualizza.add(visualizzaOfferte);
		panelVisualizza.add(labelGiorno);
		panelVisualizza.add(labelMese);
		panelVisualizza.add(labelAnno);
		panelVisualizza.add(labelPrenotazioni);
		panelVisualizza.add(giorno);
		panelVisualizza.add(mese);
		panelVisualizza.add(anno);
		panelVisualizza.add(offertaInserita);
		panelVisualizza.add(visualizzaPrenotazioni);
		panelVisualizza.add(areaVisualizzazione);

		pannelloAmministatoreSceltaOfferta.add(panelVisualizza);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		visualizzaOfferte.addActionListener(buttonsListener);
		visualizzaPrenotazioni.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == visualizzaOfferte) {
				// Chiama il controllore e ottiene il catalogo
				List<String> listaOfferte;
				if (!controlloreAmministratore.verificaData(giorno.getText(),
						mese.getText())) {
					// Stampa di tutte le offerte disponibili in caso non si sia
					// inserita la data.
					try {
						listaOfferte = controlloreAmministratore
								.visualizzaOfferta(boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta
										.prelevaComboBoxCatalogo());
						areaVisualizzazione.setText("");
						if (listaOfferte.isEmpty()) {
							areaVisualizzazione
									.append("Nessuna offerta disponibile.");
						} else {
							for (String offerta : listaOfferte)
								areaVisualizzazione.append(offerta + '\n');
						}
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				} else {
					// Stampa delle offerte relative alla data inserita.
					Integer annoInteger;
					if (anno.getText().equals("")) {
						annoInteger = new Integer(2013);
					} else {
						annoInteger = new Integer(anno.getText());
					}
					try {
						listaOfferte = controlloreAmministratore.visualizzaOffertaByData(
								boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta
										.prelevaComboBoxCatalogo(), new Integer(
										giorno.getText()),
								new Integer(mese.getText()), annoInteger);
						areaVisualizzazione.setText("");
						if (listaOfferte.isEmpty()) {
							areaVisualizzazione
									.append("Nessuna offerta disponibile.");
						} else {
							for (String offerta : listaOfferte)
								areaVisualizzazione.append(offerta + '\n');
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}

			else if (event.getSource() == visualizzaPrenotazioni) {
				if (!controlloreAmministratore.verificaId(offertaInserita.getText())) {
					areaVisualizzazione.setText("");
					areaVisualizzazione.append("\n"
							+ "Inserisci id dell'offerta.");
				} else {
					try {
						pannelloAmministatoreSceltaOfferta.setVisible(false);
						new BoundaryAmministratoreVisualizzaPrenotazioni(
								boundaryAmministatoreVisualizzaPrenotazioniSceltaTratta,
								boundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException | CatalogoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NullPointerException e){
						BoundaryAmministratoreVisualizzaPrenotazioni.pannelloAmministratoreVisualizzaPrenotazioni.setVisible(false);
						pannelloAmministatoreSceltaOfferta.setVisible(true);
						areaVisualizzazione.setText("");
						areaVisualizzazione.append("Id offerta non esistente. Inserire id corretto.");
					}
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloAmministatoreSceltaOfferta.setVisible(false);
			BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta.pannelloAmministratoreSceltaTratta
					.setVisible(true);
		}
	}

	public Integer getIdOfferta() {
		return new Integer(offertaInserita.getText());
	}
}
