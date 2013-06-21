package ordinaViaggi.boundaries;

import ordinaViaggi.control.ControlloreAmministratore;
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

/**
 * 
 * @author Gambella Riccardo Boundary Amministratore Ordina Viaggi.
 */

public class BoundaryAmministratoreVisualizzaOfferta extends JFrame {

	private static final long serialVersionUID = 6580766971666655064L;

	// Controllore associato
	private ControlloreAmministratore controlloreAmministratore = null;
	private BoundaryAmministratoreVisualizzaOfferta boundaryAmministratoreVisualizzaOfferta = this;

	public static JPanel pannelloVisualizzaOfferta;
	public JPanel panelTitolo;
	public JPanel panelComboBox;

	public JLabel titolo;
	public JLabel labelComboBox;

	public JButton visualizzaPrenotazioni;

	public JButton backGestoreOrdinaViaggio;

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
	JComboBox<String> comboBoxData;
	JComboBox<String> comboBoxOra;

	// Costanti -> Salvare meglio da altre parti..Ex file conf
	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreVisualizzaOfferta() {
		this.controlloreAmministratore = ControlloreAmministratore.getIstance();

		/*
		 * Comparsa del pannello Ordina viaggio Aggiunge al frame il pannello
		 * Ordina Viaggio e lo rende l'unico visibile.
		 */

		pannelloVisualizzaOfferta = new JPanel();

		/* Impostazioni pannelloVisualizzaOfferta */
		pannelloVisualizzaOfferta.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloVisualizzaOfferta);
		pannelloVisualizzaOfferta.setLayout(null);

		panelTitolo = new JPanel();
		panelComboBox = new JPanel();

		titolo = new JLabel();
		labelComboBox = new JLabel();

		visualizzaPrenotazioni = new JButton("Visualizzazione Offerta");
		backGestoreOrdinaViaggio = new JButton("back");
		/* Creazione delle ComboBox */
		comboBoxAmbienti = new JComboBox<String>();
		comboBoxMezzi = new JComboBox<String>();
		comboBoxCittaPartenza = new JComboBox<String>();
		comboBoxCittaArrivo = new JComboBox<String>();
		comboBoxVia = new JComboBox<String>();
		comboBoxData = new JComboBox<String>();
		comboBoxOra = new JComboBox<String>();

		/*
		 * Gestione della Combo Box. Metodo AddComboBox: Setta la comboBox
		 * iniziale.
		 */
		addComboBox(comboBoxAmbienti);
		comboBoxMezzi.addItem("--");
		comboBoxCittaPartenza.addItem("--");
		comboBoxCittaArrivo.addItem("--");
		comboBoxVia.addItem("--");
		comboBoxData.addItem("--");
		comboBoxOra.addItem("--");

		/* Istanziazione dei Listeners */
		comboBoxListener = new GestoreComboBox();
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		/* Setting dei Listener */
		comboBoxAmbienti.addActionListener(comboBoxListener);
		comboBoxMezzi.addActionListener(comboBoxListener);
		comboBoxCittaPartenza.addActionListener(comboBoxListener);
		comboBoxCittaArrivo.addActionListener(comboBoxListener);
		comboBoxVia.addActionListener(comboBoxListener);
		comboBoxData.addActionListener(comboBoxListener);
		comboBoxOra.addActionListener(comboBoxListener);

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
		panelComboBox.add(comboBoxAmbienti);
		panelComboBox.add(comboBoxMezzi);
		panelComboBox.add(comboBoxCittaPartenza);
		panelComboBox.add(comboBoxCittaArrivo);
		panelComboBox.add(comboBoxVia);
		panelComboBox.add(comboBoxData);
		panelComboBox.add(comboBoxOra);
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

		comboBoxData.setLocation(600, 100);
		comboBoxData.setSize(100, 35);
		comboBoxData.setSelectedIndex(0);
		comboBoxData.setEnabled(true);

		comboBoxOra.setLocation(700, 100);
		comboBoxOra.setSize(100, 35);
		comboBoxOra.setSelectedIndex(0);
		comboBoxOra.setEnabled(true);

		visualizzaPrenotazioni.setLocation(100, 200);
		visualizzaPrenotazioni.setSize(300, 35);
		visualizzaPrenotazioni.setFont(new Font("Arial", 0, 20));

		backGestoreOrdinaViaggio.setLocation(100, 300);
		backGestoreOrdinaViaggio.setSize(200, 35);
		backGestoreOrdinaViaggio.setFont(new Font("Arial", 0, 20));

		/* Add dei pannelli al pannello principale */
		pannelloVisualizzaOfferta.add(panelTitolo);
		pannelloVisualizzaOfferta.add(panelComboBox);

	}

	/*
	 * Listener della comboBox. Verifica la stringa selezionata e modifica le
	 * combobox di conseguenza.
	 */
	private class GestoreComboBox implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			List<String> selectedList = new ArrayList<String>();
			List<String> mapList = new ArrayList<String>();

			// Se la comboBox interessata è quella degli ambienti
			// Generazione della comboBox successiva: ComboBoxMezzi
			if (e.getSource() == comboBoxAmbienti) {
				String selected = (String) comboBoxAmbienti.getSelectedItem();

				// Svuota le comboBox successive
				if (comboBoxMezzi.getItemCount() > 1)
					svuotaComboBox(comboBoxMezzi);

				// Estrazione della mappa successiva
				selectedList.add(selected);
				try {
					controlloreAmministratore.estrazioneLista(selectedList,
							mapList);
				} catch (ControllerException e1) {
					// TODO Blocco catch generato automaticamente
					e1.printStackTrace();
				}
				// Inserisce nella comboBox gli elementi estratti
				for (String mapItem : mapList)
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

					// Estrazione della mappa successiva
					selectedList.add(ambiente);
					selectedList.add(mezzo);

					try {
						controlloreAmministratore.estrazioneLista(selectedList,
								mapList);
					} catch (ControllerException e1) {
						// TODO Blocco catch generato automaticamente
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : mapList)
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

					// Estrazione della mappa successiva
					selectedList.add(ambiente);
					selectedList.add(mezzo);
					selectedList.add(cittaPartenza);

					try {
						controlloreAmministratore.estrazioneLista(selectedList,
								mapList);
					} catch (ControllerException e1) {
						// TODO Blocco catch generato automaticamente
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : mapList)
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

					// Estrazione della mappa successiva
					selectedList.add(ambiente);
					selectedList.add(mezzo);
					selectedList.add(cittaPartenza);
					selectedList.add(cittaArrivo);

					try {
						controlloreAmministratore.estrazioneLista(selectedList,
								mapList);
					} catch (ControllerException e1) {
						// TODO Blocco catch generato automaticamente
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : mapList)
						comboBoxVia.addItem(mapItem);
				}

			}

			// Se la comboBox interessata è quella delle città della via
			else if (e.getSource() == comboBoxVia) {
				String ambiente = (String) comboBoxAmbienti.getSelectedItem();
				String mezzo = (String) comboBoxMezzi.getSelectedItem();
				String cittaPartenza = (String) comboBoxCittaPartenza
						.getSelectedItem();
				String cittaArrivo = (String) comboBoxCittaArrivo
						.getSelectedItem();
				String via = (String) comboBoxVia.getSelectedItem();
				// Passaggi nelle iterazioni
				if (!via.equals("--")) {
					// Svuota le comboBox successive
					if (comboBoxData.getItemCount() > 1)
						svuotaComboBox(comboBoxData);

					// Estrazione della mappa successiva
					selectedList.add(ambiente);
					selectedList.add(mezzo);
					selectedList.add(cittaPartenza);
					selectedList.add(cittaArrivo);
					selectedList.add(via);

					try {
						controlloreAmministratore.estrazioneLista(selectedList,
								mapList);
					} catch (ControllerException e1) {
						// TODO Blocco catch generato automaticamente
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : mapList)
						comboBoxData.addItem(mapItem);
				}

			}

			else if (e.getSource() == comboBoxData) {
				String ambiente = (String) comboBoxAmbienti.getSelectedItem();
				String mezzo = (String) comboBoxMezzi.getSelectedItem();
				String cittaPartenza = (String) comboBoxCittaPartenza
						.getSelectedItem();
				String cittaArrivo = (String) comboBoxCittaArrivo
						.getSelectedItem();
				String via = (String) comboBoxVia.getSelectedItem();
				String data = (String) comboBoxData.getSelectedItem();
				// Passaggi nelle iterazioni
				if (!data.equals("--")) {
					// Svuota le comboBox successive
					if (comboBoxOra.getItemCount() > 1)
						svuotaComboBox(comboBoxOra);

					// Estrazione della mappa successiva
					selectedList.add(ambiente);
					selectedList.add(mezzo);
					selectedList.add(cittaPartenza);
					selectedList.add(cittaArrivo);
					selectedList.add(via);
					selectedList.add(data);

					try {
						controlloreAmministratore.estrazioneLista(selectedList,
								mapList);
					} catch (ControllerException e1) {
						// TODO Blocco catch generato automaticamente
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : mapList)
						comboBoxOra.addItem(mapItem);
				}

			}

			else if (e.getSource() == comboBoxOra) {
			}
		}
	}

	private class GestoreButtons implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			/*
			 * Passaggio alla boundary BoundaryAmministratoreVisualizzaOfferta I
			 * dati del catalogo devono essere inseriti nelle ComboBox.
			 */
			if (event.getSource() == visualizzaPrenotazioni) {
				pannelloVisualizzaOfferta.setVisible(false);
				/*
				 * Passaggio alla Boundary Amministratore Visualizza Offerta.
				 * Forse bisogna mettere singleton. Altrimenti crea una nuova
				 * BoundaryClienteOrdinaViaggi a ogni passaggio back->Ritorno.
				 */
				new BoundaryAmministratoreVisualizzaPrenotazioni(
						boundaryAmministratoreVisualizzaOfferta);

			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == backGestoreOrdinaViaggio) {
				pannelloVisualizzaOfferta.setVisible(false);
				BoundaryAmministratore.pannelloAmministratore.setVisible(true);
			}
		}
	}

	/*
	 * Metodi per compattare il codice
	 */
	private void addComboBox(JComboBox<String> comboBox) {

		// Richiama il metodo estrazione Mappa da ControlloreAmministratore
		List<String> selectedList = new ArrayList<String>();
		List<String> mapList = new ArrayList<String>();

		// Estrae la mappa iniziale
		try {
			controlloreAmministratore.estrazioneLista(selectedList, mapList);
		} catch (ControllerException e) {
			// TODO Blocco catch generato automaticamente
			e.printStackTrace();
		}

		// Inserisce nella comboBox gli elementi estratti
		for (String mapItem : mapList)
			comboBox.addItem(mapItem);
	}

	private void svuotaComboBox(JComboBox<String> comboBox) {
		for (int i = 0; i < comboBox.getItemCount(); i++)
			comboBox.removeItemAt(1);
	}

	public List<String> prelevaComboBox() {
		// Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo
		// da StdIn
		String ambiente = (String) comboBoxAmbienti.getSelectedItem();
		String mezzo = (String) comboBoxMezzi.getSelectedItem();
		String cittaPartenza = (String) comboBoxCittaPartenza.getSelectedItem();
		String cittaArrivo = (String) comboBoxCittaArrivo.getSelectedItem();
		String via = (String) comboBoxVia.getSelectedItem();
		String data = (String) comboBoxData.getSelectedItem();
		String ora = (String) comboBoxOra.getSelectedItem();

		// Creazione della lista per verifica dati
		List<String> list = new ArrayList<String>();
		list.add(ambiente);
		list.add(mezzo);
		list.add(cittaPartenza);
		list.add(cittaArrivo);
		list.add(via);
		list.add(data);
		list.add(ora);

		return list;
	}
}
