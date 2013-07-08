package ispw.boundaries;

import ispw.control.ControlloreVenditore;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo Boundary ProgettistaInserimentoCatalogo.
 */
public class BoundaryVenditoreRimuoviBiglietti extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreVenditore controlloreVenditore = null;
	BoundaryVenditoreModificaPrenotazione boundaryVenditoreModificaPrenotazione = null;

	public static JPanel pannelloClienteRimuoviBiglietti;

	// Testo di Presentazione
	private JLabel labelBigliettiRimossi;
	private JLabel labelInserisciIdBiglietto;

	private JTextField idbiglietto;
	private JTextField bigliettiRimossi;

	public JPanel panelTitolo = new JPanel();
	public JPanel panelPrenotazione = new JPanel();

	public JLabel titolo = new JLabel();

	public JTextArea areaVisualizzazione;

	// Bottone
	private JButton inserisciBigliettoDaRimuovere;
	private JButton rimozioneBiglietti;
	private JButton visualizzaBiglietti;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	// Lista per l'inserimento multiplo di biglietti
	private List<String> listaBigliettiDaRimuovere = new ArrayList<String>();
	private List<String> listaBiglietti = new ArrayList<String>();

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryVenditoreRimuoviBiglietti(
			BoundaryVenditoreModificaPrenotazione boundaryVenditoreModificaPrenotazione)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.controlloreVenditore = ControlloreVenditore.getInstance();
		this.boundaryVenditoreModificaPrenotazione = boundaryVenditoreModificaPrenotazione;

		pannelloClienteRimuoviBiglietti = new JPanel();

		pannelloClienteRimuoviBiglietti.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloClienteRimuoviBiglietti);
		pannelloClienteRimuoviBiglietti.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Inserire i biglietti da rimuovere");

		pannelloClienteRimuoviBiglietti.add(panelTitolo);

		labelBigliettiRimossi = new JLabel();
		labelInserisciIdBiglietto = new JLabel();
		bigliettiRimossi = new JTextField("", 20);
		idbiglietto = new JTextField("", 20);

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setText("");
		areaVisualizzazione.setLocation(10, 200);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth() / 2, 200);

		// Setting label
		labelBigliettiRimossi.setFont(new Font("Arial", 0, 18));
		labelBigliettiRimossi.setLocation(600, 250);
		labelBigliettiRimossi.setSize(200, 35);
		labelBigliettiRimossi.setText("Biglietti rimossi:");

		// Setting label
		labelInserisciIdBiglietto.setFont(new Font("Arial", 0, 18));
		labelInserisciIdBiglietto.setLocation(100, 100);
		labelInserisciIdBiglietto.setSize(200, 35);
		labelInserisciIdBiglietto.setText("Inserisci id biglietto:");

		// Setting delle textBox
		bigliettiRimossi.setText("0");
		bigliettiRimossi.setLocation(600, 300);
		bigliettiRimossi.setSize(200, 35);
		bigliettiRimossi.setFont(new Font("Arial", 0, 18));
		bigliettiRimossi.setEditable(false);

		idbiglietto.setFont(new Font("Arial", 0, 18));
		idbiglietto.setLocation(300, 100);
		idbiglietto.setSize(150, 35);
		idbiglietto.setText("");

		// Setting bottoni

		inserisciBigliettoDaRimuovere = new JButton("Rimuovi biglietto.");
		inserisciBigliettoDaRimuovere.setLocation(600, 100);
		inserisciBigliettoDaRimuovere.setSize(200, 50);
		inserisciBigliettoDaRimuovere.setFont(new Font("Arial", 0, 20));

		visualizzaBiglietti = new JButton("Visualizza biglietti.");
		visualizzaBiglietti.setLocation(50, 400);
		visualizzaBiglietti.setSize(panelTitolo.getWidth() / 4, 50);
		visualizzaBiglietti.setFont(new Font("Arial", 0, 20));

		rimozioneBiglietti = new JButton("Conferma rimozione.");
		rimozioneBiglietti.setLocation(300, 400);
		rimozioneBiglietti.setSize(panelTitolo.getWidth() / 4, 50);
		rimozioneBiglietti.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(600, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelPrenotazione.setLayout(null);
		panelPrenotazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelPrenotazione.setLocation(5, altezzaTitolo);

		panelPrenotazione.add(labelBigliettiRimossi);
		panelPrenotazione.add(labelInserisciIdBiglietto);
		panelPrenotazione.add(bigliettiRimossi);
		panelPrenotazione.add(inserisciBigliettoDaRimuovere);
		panelPrenotazione.add(rimozioneBiglietti);
		panelPrenotazione.add(visualizzaBiglietti);
		panelPrenotazione.add(idbiglietto);
		panelPrenotazione.add(back);
		panelPrenotazione.add(areaVisualizzazione);

		pannelloClienteRimuoviBiglietti.add(panelPrenotazione);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		back.addActionListener(backListener);
		visualizzaBiglietti.addActionListener(buttonsListener);
		inserisciBigliettoDaRimuovere.addActionListener(buttonsListener);
		rimozioneBiglietti.addActionListener(buttonsListener);

		// Inizializzazione dell'area visualizzazione

		try {
			listaBiglietti = controlloreVenditore
					.getListaBigliettiByIdPrenotazione(boundaryVenditoreModificaPrenotazione
							.getIdPrenotazione());
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			pannelloClienteRimuoviBiglietti.setVisible(false);
			BoundaryVenditoreModificaPrenotazione.pannelloVenditoreModificaPrenotazione
					.setVisible(true);
			boundaryVenditoreModificaPrenotazione.areaVisualizzazione
					.setText("Id della prenotazione non presente.");
		}
		areaVisualizzazione.setText("");
		for (String str : listaBiglietti)
			areaVisualizzazione.append(str + "\n");
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == visualizzaBiglietti) {
				try {
					listaBiglietti = controlloreVenditore
							.getListaBigliettiByIdPrenotazione(boundaryVenditoreModificaPrenotazione
									.getIdPrenotazione());
				} catch (CatalogoException e) {
					// TODO Auto-generated catch block
					pannelloClienteRimuoviBiglietti.setVisible(false);
					BoundaryVenditoreModificaPrenotazione.pannelloVenditoreModificaPrenotazione
							.setVisible(true);
				}
				areaVisualizzazione.setText("");
				for (String str : listaBiglietti)
					areaVisualizzazione.append(str + "\n");

			} else if (event.getSource() == inserisciBigliettoDaRimuovere) {
				if (!controlloreVenditore.verificaId(idbiglietto.getText())){
					areaVisualizzazione.setText("");
					areaVisualizzazione.append("Inserire l'id del biglietto da rimuovere.");
				}
				else {
					listaBigliettiDaRimuovere.add(idbiglietto.getText());
					idbiglietto.setText("");
					Integer bigliettiIncrementato = new Integer(
							bigliettiRimossi.getText()) + 1;
					// Incrementa il numero di biglietti inseriti
					bigliettiRimossi.setText(bigliettiIncrementato.toString());
				}
			}

			else if (event.getSource() == rimozioneBiglietti) {
				if (bigliettiRimossi.getText().equals("0"))
					areaVisualizzazione
							.append("Inserire almeno un biglietto per effettuare la rimozione o uscire.");
				else {
					Integer idPrenotazione = boundaryVenditoreModificaPrenotazione
							.getIdPrenotazione();
					try {
						boolean prenotazioneCancellata = controlloreVenditore
								.rimozioneBiglietti(idPrenotazione,
										listaBigliettiDaRimuovere);
						// Il cliente ha rimosso tutti i biglietti dalla
						// prenotazione.
						if (prenotazioneCancellata) {
							JOptionPane
									.showMessageDialog(AABoundaryAvvio.Frame,
											"Tutti i biglietti sono stati rimossi.\nPrenotazione cancellata.");
							pannelloClienteRimuoviBiglietti.setVisible(false);
							BoundaryVenditoreModificaPrenotazione.pannelloVenditoreModificaPrenotazione
									.setVisible(true);
							return;
						}

						JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
								"Biglietti rimossi.");
						// Modifica biglietti rimossi a 0
						bigliettiRimossi.setText("0");
						
						// Modifica dell'area visualizzazione

						listaBiglietti = controlloreVenditore
								.getListaBigliettiByIdPrenotazione(boundaryVenditoreModificaPrenotazione
										.getIdPrenotazione());
					} catch (CatalogoException e) {
						// TODO Auto-generated catch block
						pannelloClienteRimuoviBiglietti.setVisible(false);
						BoundaryVenditoreModificaPrenotazione.pannelloVenditoreModificaPrenotazione
								.setVisible(true);
						boundaryVenditoreModificaPrenotazione.areaVisualizzazione
								.setText("Id della prenotazione non presente.");
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MapException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					areaVisualizzazione.setText("");
					for (String str : listaBiglietti)
						areaVisualizzazione.append(str + "\n");
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloClienteRimuoviBiglietti.setVisible(false);
			BoundaryVenditoreModificaPrenotazione.pannelloVenditoreModificaPrenotazione
					.setVisible(true);
		}
	}
}
