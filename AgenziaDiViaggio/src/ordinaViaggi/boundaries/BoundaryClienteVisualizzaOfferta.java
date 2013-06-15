package ordinaViaggi.boundaries;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ordinaViaggi.control.ControlloreCliente;
import ordinaViaggi.exception.MapDAOException;

/**
 * @author Gambella Riccardo
 * Boundary Cliente Visualizza Offerta.
 */

public class BoundaryClienteVisualizzaOfferta extends JFrame {

	private static final long serialVersionUID = 6580766971666655064L;

	// Controllore associato
	private ControlloreCliente controlloreCliente = null;
	// Boundary Precedente
	private BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi = null;
	//Oggetto Boundary
	private BoundaryClienteVisualizzaOfferta boundaryClienteVisualizzaOfferta = this;

	//Componenti
	public static JPanel pannelloVisualizzaOfferta = new JPanel();
	public JPanel panelTitolo;
	public JPanel panelComboBox;

	public JLabel titolo;
	public JLabel labelComboBox;
	public JLabel labelPostiDisponibili;

	public JTextField postiDisponibili;
	
	public JButton prenotazioneViaggio;
	public JButton backGestoreVisualizzaOfferta;

	// Bottoni
	private GestoreButtons buttonsListener;
	private GestoreBack backListener;
	private GestoreComboBox comboBoxListener;

	// ComboBox
	JComboBox<String> comboBoxData;
	JComboBox<String> comboBoxOra;

	// Costanti -> Salvare meglio da altre parti..Ex file conf
	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryClienteVisualizzaOfferta(
			ControlloreCliente controlloreCliente,
			BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi) {
		this.controlloreCliente = controlloreCliente;
		this.boundaryClienteOrdinaViaggi = boundaryClienteOrdinaViaggi;

		/*
		 * Comparsa del pannello Ordina viaggio Aggiunge al frame il pannello
		 * Ordina Viaggio e lo rende l'unico visibile.
		 */

		pannelloVisualizzaOfferta = new JPanel();

		/* Impostazioni pannelloOrdinaViaggio */
		pannelloVisualizzaOfferta.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloVisualizzaOfferta);
		pannelloVisualizzaOfferta.setLayout(null);

		panelTitolo = new JPanel();
		panelComboBox = new JPanel();

		titolo = new JLabel();
		labelComboBox = new JLabel();
		labelPostiDisponibili = new JLabel();
		
		postiDisponibili = new JTextField("",20);

		prenotazioneViaggio = new JButton("Prenotazione Viaggio");
		backGestoreVisualizzaOfferta = new JButton("back");
		/* Creazione delle ComboBox */
		comboBoxData = new JComboBox<String>();
		comboBoxOra = new JComboBox<String>();

		/*
		 * Gestione della Combo Box. Metodo AddComboBox: Setta la comboBox
		 * iniziale.
		 */
		comboBoxData.addItem("--");
		addComboBox(comboBoxData);
		comboBoxOra.addItem("--");

		/* Istanziazione dei Listeners */
		comboBoxListener = new GestoreComboBox();
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		/* Setting dei Listener */
		comboBoxData.addActionListener(comboBoxListener);
		comboBoxOra.addActionListener(comboBoxListener);
		prenotazioneViaggio.addActionListener(buttonsListener);
		backGestoreVisualizzaOfferta.addActionListener(backListener);

		/* Setting dei pannelli */
		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		panelComboBox.setLayout(null);
		panelComboBox.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelComboBox.setLocation(5, 50);
		panelComboBox.add(comboBoxData);
		panelComboBox.add(comboBoxOra);
		panelComboBox.add(labelComboBox);
		panelComboBox.add(labelPostiDisponibili);
		panelComboBox.add(postiDisponibili);
		panelComboBox.add(prenotazioneViaggio);
		panelComboBox.add(backGestoreVisualizzaOfferta);
		
		

		/* Setting dei Componenti */
		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Visualizza Offerta.");

		labelComboBox.setFont(new Font("Arial", 0, 30));
		labelComboBox.setLocation(border, 30);
		labelComboBox.setSize(panelComboBox.getWidth(), 35);
		labelComboBox.setHorizontalAlignment(JLabel.CENTER);
		labelComboBox.setVerticalAlignment(JLabel.CENTER);
		labelComboBox.setText("Seleziona il viaggio dalla ComboBox.");
		
		labelPostiDisponibili.setFont(new Font("Arial", 0, 18));
		labelPostiDisponibili.setLocation(350, 100);
		labelPostiDisponibili.setSize(150, 35);
		labelPostiDisponibili.setText("Posti disponibili:");
		
		postiDisponibili.setLocation(500, 100);
		postiDisponibili.setSize(100, 35);
		postiDisponibili.setFont(new Font("Arial",0,18));
		//postiDisponibili.setEditable(false);
		
		
		/* Setting posizioni comboBox */
		comboBoxData.setLocation(100, 100);
		comboBoxData.setSize(100, 35);
		comboBoxData.setSelectedIndex(0);
		comboBoxData.setEnabled(true);

		comboBoxOra.setLocation(200, 100);
		comboBoxOra.setSize(100, 35);
		comboBoxOra.setSelectedIndex(0);
		comboBoxOra.setEnabled(true);

		prenotazioneViaggio.setLocation(100, 200);
		prenotazioneViaggio.setSize(300, 35);
		prenotazioneViaggio.setFont(new Font("Arial", 0, 20));

		backGestoreVisualizzaOfferta.setLocation(100, 300);
		backGestoreVisualizzaOfferta.setSize(200, 35);
		backGestoreVisualizzaOfferta.setFont(new Font("Arial", 0, 20));

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

			List<String> mapList = new ArrayList<String>();
			List<String> listaCatalogo = boundaryClienteOrdinaViaggi
					.prelevaComboBoxCatalogo();

			// Se la comboBox interessata è quella della data
			if (e.getSource() == comboBoxData) {
				String data = (String) comboBoxData.getSelectedItem();

				if (!data.equals("--")) {

					// Svuota le comboBox successive
					if (comboBoxOra.getItemCount() > 1)
						svuotaComboBox(comboBoxOra);

					// Estrazione della mappa successiva
					listaCatalogo.add(data);
					try {
						ControlloreCliente.estrazioneMappa(listaCatalogo,
								mapList);
					} catch (MapDAOException e1) {
						// TODO Blocco catch generato automaticamente
						e1.printStackTrace();
					}
					// Inserisce nella comboBox gli elementi estratti
					for (String mapItem : mapList)
						comboBoxOra.addItem(mapItem);
				}
			}

			// Se la comboBox interessata è quella dei mezzi
			else if (e.getSource() == comboBoxOra) {
				
				String ora = (String) comboBoxOra.getSelectedItem();
				if (!ora.equals("--")) {

					/* Estrazione della mappa relativa all'ora, in quanto
					 * i posti sono un informazione contenuta nel SubElement
					 */
					listaCatalogo.add((String)comboBoxData.getSelectedItem());
					
					try {
						String posti = ControlloreCliente.getInfo(listaCatalogo, ora);
						postiDisponibili.setText(posti);
					} catch (MapDAOException e1) {
						// TODO Blocco catch generato automaticamente
						e1.printStackTrace();
					}
					
				}
			}
		}
	}

	private class GestoreButtons implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == prenotazioneViaggio) {
				List<String> listaCatalogo = boundaryClienteOrdinaViaggi.prelevaComboBoxCatalogo();
				if(ControlloreCliente.verificaDati(listaCatalogo)){
					pannelloVisualizzaOfferta.setVisible(false);
					new BoundaryClientePrenotazioneViaggio
					(controlloreCliente, boundaryClienteOrdinaViaggi, boundaryClienteVisualizzaOfferta);
				}
				else
					System.out.println("Dati non inseriti. Impossibile visualizzare offerta.");
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == backGestoreVisualizzaOfferta) {
				pannelloVisualizzaOfferta.setVisible(false);
				BoundaryClienteOrdinaViaggi.pannelloOrdinaViaggio
						.setVisible(true);
			}
		}
	}

	/*
	 * Metodi per compattare il codice
	 */
	private void addComboBox(JComboBox<String> comboBox) {
		List<String> listaCatalogo = boundaryClienteOrdinaViaggi
				.prelevaComboBoxCatalogo();
		if (!ControlloreCliente.verificaDati(listaCatalogo))
			System.out.println("Dati non inseriti totalmente");
		else {
			// Richiama il metodo estrazione Mappa da ControlloreCliente
			List<String> mapList = new ArrayList<String>();

			// Estrae la mappa iniziale
			try {
				ControlloreCliente.estrazioneMappa(listaCatalogo, mapList);
			} catch (MapDAOException e) {
				// TODO Blocco catch generato automaticamente
				e.printStackTrace();
			}

			if (mapList.isEmpty())
				System.out.println("Non c'è nessuna offerta nel sistema associata a questa tratta.");

			// Inserisce nella comboBox gli elementi estratti
			for (String mapItem : mapList)
				comboBox.addItem(mapItem);

		}
	}

	private void svuotaComboBox(JComboBox<String> comboBox) {
		for (int i = 0; i < comboBox.getItemCount(); i++)
			comboBox.removeItemAt(1);
	}
	
	public List<String> prelevaComboBoxOfferta(){
		//Richiesta inserimento di Ambiente, Mezzo, CittaPartenza, CittaArrivo da StdIn
		String data = (String)comboBoxData.getSelectedItem();
		String ora = (String)comboBoxOra.getSelectedItem();
		
		//Creazione della lista per verifica dati
		List<String> list = new ArrayList<String>();
		list.add(data);
		list.add(ora);
		
		return list;
	}
}
