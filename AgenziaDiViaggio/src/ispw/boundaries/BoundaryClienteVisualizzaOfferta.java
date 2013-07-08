package ispw.boundaries;

import ispw.control.ControlloreCliente;
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
public class BoundaryClienteVisualizzaOfferta extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreCliente controlloreCliente = null;
	BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi = null;
	BoundaryClienteVisualizzaOfferta boundaryClienteVisualizzaOfferta = null;

	public static JPanel pannelloClienteVisualizzaOfferta;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelVisualizza = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelGiorno;
	public JLabel labelMese;
	public JLabel labelAnno;
	public JLabel inserisciOfferta = new JLabel();

	public JTextField giorno;
	public JTextField mese;
	public JTextField anno;
	public JTextField offertaInserita;

	public JTextArea areaVisualizzazione;

	// Bottone
	public JButton visualizzaOfferte;
	public JButton prenotazioneViaggio;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryClienteVisualizzaOfferta(
			BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.controlloreCliente = ControlloreCliente.getInstance();
		this.boundaryClienteOrdinaViaggi = boundaryClienteOrdinaViaggi;
		this.boundaryClienteVisualizzaOfferta = this;

		pannelloClienteVisualizzaOfferta = new JPanel();

		pannelloClienteVisualizzaOfferta.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloClienteVisualizzaOfferta);
		pannelloClienteVisualizzaOfferta.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Cliente");

		pannelloClienteVisualizzaOfferta.add(panelTitolo);

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

		inserisciOfferta.setFont(new Font("Arial", 0, 18));
		inserisciOfferta.setLocation(
				AABoundaryAvvio.Frame.getWidth() / 2 + 100, 200);
		inserisciOfferta.setSize(300, 35);
		inserisciOfferta.setText("Inserisci l'id dell'offerta:");

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

		prenotazioneViaggio = new JButton("Prenota viaggio.");
		prenotazioneViaggio.setLocation(200, 400);
		prenotazioneViaggio.setSize(panelTitolo.getWidth() / 4, 50);
		prenotazioneViaggio.setFont(new Font("Arial", 0, 20));

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
		panelVisualizza.add(inserisciOfferta);
		panelVisualizza.add(giorno);
		panelVisualizza.add(mese);
		panelVisualizza.add(anno);
		panelVisualizza.add(offertaInserita);
		panelVisualizza.add(prenotazioneViaggio);
		panelVisualizza.add(areaVisualizzazione);

		pannelloClienteVisualizzaOfferta.add(panelVisualizza);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		visualizzaOfferte.addActionListener(buttonsListener);
		prenotazioneViaggio.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == visualizzaOfferte) {
				// Chiama il controllore e ottiene il catalogo
				List<String> listaOfferte;
				if (!controlloreCliente.verificaData(giorno.getText(),
						mese.getText())) {
					// Stampa di tutte le offerte disponibili in caso non si sia
					// inserita la data.
					try {
						listaOfferte = controlloreCliente
								.visualizzaOfferta(boundaryClienteOrdinaViaggi
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
						listaOfferte = controlloreCliente
								.visualizzaOffertaByData(
										boundaryClienteOrdinaViaggi
												.prelevaComboBoxCatalogo(),
										new Integer(giorno.getText()),
										new Integer(mese.getText()),
										annoInteger);
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

			else if (event.getSource() == prenotazioneViaggio) {
				if (!controlloreCliente.verificaId(offertaInserita.getText())) {
					areaVisualizzazione.append("\n"
							+ "Insersci id dell'offerta.");
				} else {
					try {
						pannelloClienteVisualizzaOfferta.setVisible(false);
						new BoundaryClientePrenotaViaggio(
								boundaryClienteOrdinaViaggi,
								boundaryClienteVisualizzaOfferta);
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
		public void actionPerformed(ActionEvent arg0) {
			pannelloClienteVisualizzaOfferta.setVisible(false);
			BoundaryClienteOrdinaViaggi.pannelloGestoreOrdinaViaggi
					.setVisible(true);
		}
	}

	public Integer getIdOfferta() {
		return new Integer(offertaInserita.getText());
	}
}
