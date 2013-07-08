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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Gambella Riccardo
 * Boundary Cliente Ordina Viaggi.
 */


public class BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5561695135134299299L;

	// Controllore associato
	private ControlloreAmministratore controlloreAmministratore = null;
	private BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta boundaryAmministratoreSceltaTratta = null;

	public static JPanel pannelloAmministratoreSceltaTratta;
	public JPanel panelTitolo;
	public JPanel panelComboBox;

	public JLabel titolo;
	public JLabel labelScelta = new JLabel();
	public JLabel labelComboBox;

	// Bottoni
	public JButton next;
	public JButton back;

	// Bottoni
	private GestoreButtons buttonsListener;
	private GestoreBack backListener;
	private GestoreComboBox comboBoxListener;

	// ComboBox
	JComboBox<String> comboBoxAmbienti;
	JComboBox<String> comboBoxMezzi;
	JComboBox<String> comboBoxCittaPartenza;
	JComboBox<String> comboBoxCittaArrivo;
	JComboBox<String> comboBoxVia;

	// Costanti -> Salvare meglio da altre parti..Ex file conf
	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {
		this.controlloreAmministratore = ControlloreAmministratore.getInstance();
		boundaryAmministratoreSceltaTratta = this;

		/*
		 * Comparsa del pannello Ordina viaggio Aggiunge al frame il pannello
		 * Ordina Viaggio e lo rende l'unico visibile.
		 */

		pannelloAmministratoreSceltaTratta = new JPanel();

		/* Impostazioni pannelloSelezionaViaggioOfferta */
		pannelloAmministratoreSceltaTratta.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministratoreSceltaTratta);
		pannelloAmministratoreSceltaTratta.setLayout(null);

		panelTitolo = new JPanel();
		panelComboBox = new JPanel();

		titolo = new JLabel();
		labelComboBox = new JLabel();

		next = new JButton("Next");
		back = new JButton("back");
		/* Creazione delle ComboBox */
		comboBoxAmbienti = new JComboBox<String>();
		comboBoxMezzi = new JComboBox<String>();
		comboBoxCittaPartenza = new JComboBox<String>();
		comboBoxCittaArrivo = new JComboBox<String>();
		comboBoxVia = new JComboBox<String>();

		/*
		 * Gestione della Combo Box. Metodo AddComboBox: Setta la comboBox
		 * iniziale.
		 */
		addComboBox(comboBoxAmbienti);
		comboBoxMezzi.addItem("--");
		comboBoxCittaPartenza.addItem("--");
		comboBoxCittaArrivo.addItem("--");
		comboBoxVia.addItem("--");

		/* Setting dei Listener */
		comboBoxListener = new GestoreComboBox();
		backListener = new GestoreBack();
		buttonsListener = new GestoreButtons();

		comboBoxAmbienti.addActionListener(comboBoxListener);
		comboBoxMezzi.addActionListener(comboBoxListener);
		comboBoxCittaPartenza.addActionListener(comboBoxListener);
		comboBoxCittaArrivo.addActionListener(comboBoxListener);
		comboBoxVia.addActionListener(comboBoxListener);

		next.addActionListener(buttonsListener);
		back.addActionListener(backListener);

		/* Setting dei pannelli */
		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		panelComboBox.setLayout(null);
		panelComboBox.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelComboBox.setLocation(5, 50);
		panelComboBox.add(comboBoxAmbienti);
		panelComboBox.add(comboBoxMezzi);
		panelComboBox.add(comboBoxCittaPartenza);
		panelComboBox.add(comboBoxCittaArrivo);
		panelComboBox.add(comboBoxVia);
		panelComboBox.add(labelComboBox);
		panelComboBox.add(next);
		panelComboBox.add(back);

		/* Setting dei Componenti */
		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Amministratore.");
		
		labelScelta.setFont(new Font("Arial", 0, 30));
		labelScelta.setLocation(border, 30);
		labelScelta.setSize(pannelloAmministratoreSceltaTratta.getWidth(), 35);
		labelScelta.setHorizontalAlignment(JLabel.CENTER);
		labelScelta.setVerticalAlignment(JLabel.CENTER);
		labelScelta.setText("Scegli la tratta di cui visualizzare le prenotazioni.");

		labelComboBox.setFont(new Font("Arial", 0, 30));
		labelComboBox.setLocation(border, 30);
		labelComboBox.setSize(panelComboBox.getWidth(), 35);
		labelComboBox.setHorizontalAlignment(JLabel.CENTER);
		labelComboBox.setVerticalAlignment(JLabel.CENTER);
		labelComboBox.setText("Seleziona il viaggio dalla ComboBox.");

		/* Setting posizioni comboBox */
		comboBoxAmbienti.setLocation(100, 100);
		comboBoxAmbienti.setSize(100, 35);
		comboBoxAmbienti.setSelectedIndex(0);
		comboBoxAmbienti.setEnabled(true);

		comboBoxMezzi.setLocation(200, 100);
		comboBoxMezzi.setSize(100, 35);
		comboBoxMezzi.setSelectedIndex(0);
		comboBoxMezzi.setEnabled(true);

		comboBoxCittaPartenza.setLocation(300, 100);
		comboBoxCittaPartenza.setSize(100, 35);
		comboBoxCittaPartenza.setSelectedIndex(0);
		comboBoxCittaPartenza.setEnabled(true);

		comboBoxCittaArrivo.setLocation(400, 100);
		comboBoxCittaArrivo.setSize(100, 35);
		comboBoxCittaArrivo.setSelectedIndex(0);
		comboBoxCittaArrivo.setEnabled(true);

		comboBoxVia.setLocation(500, 100);
		comboBoxVia.setSize(100, 35);
		comboBoxVia.setSelectedIndex(0);
		comboBoxVia.setEnabled(true);

		next.setLocation(100, 400);
		next.setSize(200, 35);
		next.setFont(new Font("Arial", 0, 20));

		back.setLocation(500, 400);
		back.setSize(200, 35);
		back.setFont(new Font("Arial", 0, 20));

		/* Add dei pannelli al pannello principale */
		pannelloAmministratoreSceltaTratta.add(panelTitolo);
		pannelloAmministratoreSceltaTratta.add(panelComboBox);

	}

	/*
	 * Listener della comboBox. Verifica la stringa selezionata e modifica le
	 * comboox di conseguenza.
	 */
	private class GestoreComboBox implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			List<String> selectedList = new ArrayList<String>();

			// Se la comboBox interessata è quella degli ambienti
			// Generazione della comboBox successiva: ComboBoxMezzi
			if (e.getSource() == comboBoxAmbienti) {
				String selected = (String) comboBoxAmbienti.getSelectedItem();

				// Svuota le comboBox successive
				if (comboBoxMezzi.getItemCount() > 1)
					svuotaComboBox(comboBoxMezzi);

				try {
					selectedList = controlloreAmministratore
							.estrazioneMezzi(selected);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MapException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (OraException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CatalogoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Inserisce nella comboBox gli elementi estratti
				for (String mapItem : selectedList)
					comboBoxMezzi.addItem(mapItem);

			}

			// Se la comboBox interessata è quella dei mezzi
			else if (e.getSource() == comboBoxMezzi) {
				String ambiente = (String) comboBoxAmbienti.getSelectedItem();
				String mezzo = (String) comboBoxMezzi.getSelectedItem();

				// Generazione della comboBox successiva: ComboBoxCittaPartenza
				if (!mezzo.equals("--")) {
					// Svuota le comboBox successive
					if (comboBoxCittaPartenza.getItemCount() > 1)
						svuotaComboBox(comboBoxCittaPartenza);

					try {
						selectedList = controlloreAmministratore
								.estrazioneCittaPartenza(ambiente, mezzo);
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MapException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OraException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : selectedList)
						comboBoxCittaPartenza.addItem(mapItem);
				}
			}

			// Se la comboBox interessata è quella delle città di partenza
			else if (e.getSource() == comboBoxCittaPartenza) {
				String ambiente = (String) comboBoxAmbienti.getSelectedItem();
				String mezzo = (String) comboBoxMezzi.getSelectedItem();
				String cittaPartenza = (String) comboBoxCittaPartenza
						.getSelectedItem();
				// Passaggi nelle iterazioni
				if (!cittaPartenza.equals("--")) {
					// Svuota le comboBox successive
					if (comboBoxCittaArrivo.getItemCount() > 1)
						svuotaComboBox(comboBoxCittaArrivo);

					try {
						selectedList = controlloreAmministratore
								.estrazioneCittaArrivo(ambiente, mezzo,
										cittaPartenza);
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MapException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OraException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : selectedList)
						comboBoxCittaArrivo.addItem(mapItem);

				}
			}

			// Se la comboBox interessata è quella delle città di arrivo
			else if (e.getSource() == comboBoxCittaArrivo) {
				String ambiente = (String) comboBoxAmbienti.getSelectedItem();
				String mezzo = (String) comboBoxMezzi.getSelectedItem();
				String cittaPartenza = (String) comboBoxCittaPartenza
						.getSelectedItem();
				String cittaArrivo = (String) comboBoxCittaArrivo
						.getSelectedItem();
				// Passaggi nelle iterazioni
				if (!cittaArrivo.equals("--")) {
					// Svuota le comboBox successive
					if (comboBoxVia.getItemCount() > 1)
						svuotaComboBox(comboBoxVia);

					try {
						selectedList = controlloreAmministratore.estrazioneVia(
								ambiente, mezzo, cittaPartenza, cittaArrivo);
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MapException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OraException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CatalogoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : selectedList)
						comboBoxVia.addItem(mapItem);

				}

			}

			// Se la comboBox interessata è quella delle città della via
			if (e.getSource() == comboBoxVia) {
			}

		}
	}

	private class GestoreButtons implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == next) {

				List<String> listaCatalogo = prelevaComboBoxCatalogo();
				// Controllo sull'inserimento corretto dei dati
				if (!controlloreAmministratore.verificaDati(listaCatalogo))
					System.out.println("Dati non inseriti totalmente");
				else {
					pannelloAmministratoreSceltaTratta.setVisible(false);
					try {
						new BoundaryAmministratoreVisualizzaPrenotazioniSceltaOfferta(
								boundaryAmministratoreSceltaTratta);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException | CatalogoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			pannelloAmministratoreSceltaTratta.setVisible(false);
			BoundaryAmministratore.pannelloAmministratore.setVisible(true);
		}
	}

	/*
	 * Metodi utili a compattare il codice.
	 */
	private void addComboBox(JComboBox<String> comboBox) {

		// Richiama il metodo estrazione Mappa da controlloreCliente
		List<String> selectedList = new ArrayList<String>();

		// Estrae la mappa iniziale
		try {
			selectedList = controlloreAmministratore.estrazioneAmbienti();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Inserisce nella comboBox gli elementi estratti
		for (String mapItem : selectedList)
			comboBox.addItem(mapItem);

	}

	private void svuotaComboBox(JComboBox<String> comboBox) {
		for (int i = 0; i < comboBox.getItemCount(); i++)
			comboBox.removeItemAt(1);
	}

	public List<String> prelevaComboBoxCatalogo() {
		// Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo
		// da StdIn
		String ambiente = (String) comboBoxAmbienti.getSelectedItem();
		String mezzo = (String) comboBoxMezzi.getSelectedItem();
		String cittaPartenza = (String) comboBoxCittaPartenza.getSelectedItem();
		String cittaArrivo = (String) comboBoxCittaArrivo.getSelectedItem();
		String via = (String) comboBoxVia.getSelectedItem();

		// Creazione della lista per verifica dati
		List<String> list = new ArrayList<String>();
		list.add(ambiente);
		list.add(mezzo);
		list.add(cittaPartenza);
		list.add(cittaArrivo);
		list.add(via);

		return list;
	}

}