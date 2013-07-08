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
public class BoundaryClienteAggiuntaBiglietti extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreCliente controlloreCliente = null;
	BoundaryClienteModificaPrenotazione boundaryClienteModificaPrenotazione = null;

	public static JPanel pannelloClienteAggiuntaBiglietti;

	// Testo di Presentazione
	private JLabel labelBigliettiInseriti;
	public JLabel labelListaBiglietti;
	public JLabel labelNome;
	public JLabel labelCognome;
	public JLabel labelEmail;

	public JTextField nome;
	public JTextField cognome;
	public JTextField email;
	private JTextField bigliettiInseriti;

	public JPanel panelTitolo = new JPanel();
	public JPanel panelPrenotazione = new JPanel();

	public JLabel titolo = new JLabel();

	public JTextArea areaVisualizzazione;

	// Bottone
	private JButton inserisciBiglietto;
	private JButton visualizzaBiglietti;
	private JButton aggiuntaBiglietti;
	
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	// Lista per l'inserimento multiplo di biglietti
	private List<String> listaNomi = new ArrayList<String>();
	private List<String> listaCognomi = new ArrayList<String>();
	private List<String> listaEmail = new ArrayList<String>();
	private List<String> listaBiglietti = new ArrayList<String>();

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryClienteAggiuntaBiglietti(
			BoundaryClienteModificaPrenotazione boundaryClienteModificaPrenotazione)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.controlloreCliente = ControlloreCliente.getInstance();
		this.boundaryClienteModificaPrenotazione = boundaryClienteModificaPrenotazione;

		pannelloClienteAggiuntaBiglietti = new JPanel();

		pannelloClienteAggiuntaBiglietti.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloClienteAggiuntaBiglietti);
		pannelloClienteAggiuntaBiglietti.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Inserire i dati del passeggero");

		pannelloClienteAggiuntaBiglietti.add(panelTitolo);

		labelBigliettiInseriti = new JLabel();
		labelListaBiglietti = new JLabel();
		labelNome = new JLabel();
		labelCognome = new JLabel();
		labelEmail = new JLabel();
		bigliettiInseriti = new JTextField("", 20);
		nome = new JTextField("", 20);
		cognome = new JTextField("", 20);
		email = new JTextField("", 20);

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setLocation(10, 100);
		areaVisualizzazione.setSize(300, 300);

		// Setting label
		labelBigliettiInseriti.setFont(new Font("Arial", 0, 18));
		labelBigliettiInseriti.setLocation(600, 250);
		labelBigliettiInseriti.setSize(200, 35);
		labelBigliettiInseriti.setText("Biglietti inseriti:");
		
		labelListaBiglietti.setFont(new Font("Arial", 0, 18));
		labelListaBiglietti.setLocation(100, 50);
		labelListaBiglietti.setSize(200, 35);
		labelListaBiglietti.setText("Lista biglietti:");

		labelNome.setFont(new Font("Arial", 0, 18));
		labelNome.setLocation(350, 50);
		labelNome.setSize(200, 35);
		labelNome.setText("Inserire il nome:");

		labelCognome.setFont(new Font("Arial", 0, 18));
		labelCognome.setLocation(350, 150);
		labelCognome.setSize(200, 35);
		labelCognome.setText("Inserire il cognome:");

		labelEmail.setFont(new Font("Arial", 0, 18));
		labelEmail.setLocation(350, 250);
		labelEmail.setSize(200, 35);
		labelEmail.setText("Inserire la mail:");

		// Setting delle textBox
		bigliettiInseriti.setText("0");
		bigliettiInseriti.setLocation(600, 300);
		bigliettiInseriti.setSize(200, 35);
		bigliettiInseriti.setFont(new Font("Arial", 0, 18));
		bigliettiInseriti.setEditable(false);

		nome.setLocation(350, 100);
		nome.setSize(200, 35);
		nome.setFont(new Font("Arial", 0, 18));

		cognome.setLocation(350, 200);
		cognome.setSize(200, 35);
		cognome.setFont(new Font("Arial", 0, 18));

		email.setLocation(350, 300);
		email.setSize(200, 35);
		email.setFont(new Font("Arial", 0, 18));

		// Setting bottoni

		inserisciBiglietto = new JButton("Inserisci biglietto.");
		inserisciBiglietto.setLocation(600, 100);
		inserisciBiglietto.setSize(200, 50);
		inserisciBiglietto.setFont(new Font("Arial", 0, 20));
		
		visualizzaBiglietti = new JButton("Visualizza Biglietti.");
		visualizzaBiglietti.setLocation(100, 400);
		visualizzaBiglietti.setSize(panelTitolo.getWidth() / 4, 50);
		visualizzaBiglietti.setFont(new Font("Arial", 0, 20));

		aggiuntaBiglietti = new JButton("Aggiungi biglietto.");
		aggiuntaBiglietti.setLocation(350, 400);
		aggiuntaBiglietti.setSize(panelTitolo.getWidth() / 4, 50);
		aggiuntaBiglietti.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(600, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelPrenotazione.setLayout(null);
		panelPrenotazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelPrenotazione.setLocation(5, altezzaTitolo);

		panelPrenotazione.add(labelNome);
		panelPrenotazione.add(labelCognome);
		panelPrenotazione.add(labelEmail);
		panelPrenotazione.add(labelListaBiglietti);
		panelPrenotazione.add(labelBigliettiInseriti);
		panelPrenotazione.add(nome);
		panelPrenotazione.add(cognome);
		panelPrenotazione.add(email);
		panelPrenotazione.add(bigliettiInseriti);
		panelPrenotazione.add(inserisciBiglietto);
		panelPrenotazione.add(visualizzaBiglietti);
		panelPrenotazione.add(aggiuntaBiglietti);
		panelPrenotazione.add(back);
		panelPrenotazione.add(areaVisualizzazione);

		pannelloClienteAggiuntaBiglietti.add(panelPrenotazione);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		back.addActionListener(backListener);
		inserisciBiglietto.addActionListener(buttonsListener);
		visualizzaBiglietti.addActionListener(buttonsListener);
		aggiuntaBiglietti.addActionListener(buttonsListener);

		// Inizializzazione dell'area visualizzazione

		try {
			listaBiglietti = controlloreCliente
					.getListaBigliettiByIdPrenotazione(boundaryClienteModificaPrenotazione
							.getIdPrenotazione());
		} catch (CatalogoException e) {
			// TODO Auto-generated catch block
			pannelloClienteAggiuntaBiglietti.setVisible(false);
			BoundaryClienteModificaPrenotazione.pannelloModificaPrenotazione
					.setVisible(true);
			boundaryClienteModificaPrenotazione.areaVisualizzazione
					.setText("Id della prenotazione non presente.");
		}
		areaVisualizzazione.setText("");
		for (String str : listaBiglietti)
			areaVisualizzazione.append(str + "\n");
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == inserisciBiglietto) {
				if (!controlloreCliente.verificaDatiViaggiatore(nome.getText(),
						cognome.getText(), email.getText())){
					areaVisualizzazione.setText("");
					areaVisualizzazione.append("Inserire i dati del traveler.");
				}
				else {
					listaNomi.add(nome.getText());
					listaCognomi.add(cognome.getText());
					listaEmail.add(email.getText());
					nome.setText("");
					cognome.setText("");
					email.setText("");
					Integer bigliettiIncrementato = new Integer(
							bigliettiInseriti.getText()) + 1;
					// Incrementa il numero di biglietti inseriti
					bigliettiInseriti.setText(bigliettiIncrementato.toString());
				}
			}
			
			else if (event.getSource() == visualizzaBiglietti) {
				try {
					listaBiglietti = controlloreCliente
							.getListaBigliettiByIdPrenotazione(boundaryClienteModificaPrenotazione
									.getIdPrenotazione());
				} catch (CatalogoException e) {
					// TODO Auto-generated catch block
					panelPrenotazione.setVisible(false);
					BoundaryClienteModificaPrenotazione.pannelloModificaPrenotazione
							.setVisible(true);
				}
				areaVisualizzazione.setText("");
				for (String str : listaBiglietti)
					areaVisualizzazione.append(str + "\n");

			}

			else if (event.getSource() == aggiuntaBiglietti) {
				if (bigliettiInseriti.getText().equals("0"))
					areaVisualizzazione
							.append("Inserire almeno un biglietto per effettuare la prenotazione.");
				else {
					Integer idPrenotazione = boundaryClienteModificaPrenotazione
							.getIdPrenotazione();
					// L'acquirente è lo username dell'utente. Può essere
					// estratto dalle sue informazioni.
					try {

						controlloreCliente.aggiuntaBiglietti(idPrenotazione,
								listaNomi, listaCognomi, listaEmail);
						bigliettiInseriti.setText("0");
						JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
								"Biglietti aggiunti.");
						
						
						
						//Setting dell'area visualizzazione con la lista dei biglietti
						try {
							listaBiglietti = controlloreCliente
									.getListaBigliettiByIdPrenotazione(boundaryClienteModificaPrenotazione
											.getIdPrenotazione());
						} catch (CatalogoException e) {
							// TODO Auto-generated catch block
							pannelloClienteAggiuntaBiglietti.setVisible(false);
							BoundaryClienteModificaPrenotazione.pannelloModificaPrenotazione
									.setVisible(true);
							boundaryClienteModificaPrenotazione.areaVisualizzazione
									.setText("Id della prenotazione non presente.");
						}
						areaVisualizzazione.setText("");
						for (String str : listaBiglietti)
							areaVisualizzazione.append(str + "\n");
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CatalogoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloClienteAggiuntaBiglietti.setVisible(false);
			BoundaryClienteModificaPrenotazione.pannelloModificaPrenotazione
					.setVisible(true);
		}
	}
}
