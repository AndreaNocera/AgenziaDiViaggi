package ordinaViaggi.boundaries;

import ordinaViaggi.control.ControlloreCliente;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

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
public class BoundaryClientePrenotaViaggio extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreCliente controlloreCliente = null;
	BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi = null;
	BoundaryClienteVisualizzaOfferta boundaryClienteVisualizzaOfferta = null;

	public static JPanel pannelloClientePrenotaViaggio;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();
	public JLabel labelNome;
	public JLabel labelCognome;
	public JLabel labelEmail;

	public JTextField nome;
	public JTextField cognome;
	public JTextField email;

	public JPanel panelTitolo = new JPanel();
	public JPanel panelPrenotazione = new JPanel();

	public JLabel titolo = new JLabel();

	public JTextArea areaVisualizzazione;

	// Bottone
	private JButton prenotazioneViaggio;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryClientePrenotaViaggio(
			BoundaryClienteOrdinaViaggi boundaryClienteOrdinaViaggi,
			BoundaryClienteVisualizzaOfferta boundaryClienteVisualizzaOfferta)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.controlloreCliente = ControlloreCliente.getIstance();
		this.boundaryClienteOrdinaViaggi = boundaryClienteOrdinaViaggi;
		this.boundaryClienteVisualizzaOfferta = boundaryClienteVisualizzaOfferta;

		pannelloClientePrenotaViaggio = new JPanel();

		pannelloClientePrenotaViaggio.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloClientePrenotaViaggio);
		pannelloClientePrenotaViaggio.setLayout(null);

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

		pannelloClientePrenotaViaggio.add(panelTitolo);

		labelNome = new JLabel();
		labelCognome = new JLabel();
		labelEmail = new JLabel();
		nome = new JTextField("", 20);
		cognome = new JTextField("", 20);
		email = new JTextField("", 20);

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setLocation(10, 500);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());

		// Setting label
		labelNome.setFont(new Font("Arial", 0, 18));
		labelNome.setLocation(300, 50);
		labelNome.setSize(200, 35);
		labelNome.setText("Inserire il nome:");

		labelCognome.setFont(new Font("Arial", 0, 18));
		labelCognome.setLocation(300, 150);
		labelCognome.setSize(200, 35);
		labelCognome.setText("Inserire il cognome:");

		labelEmail.setFont(new Font("Arial", 0, 18));
		labelEmail.setLocation(300, 250);
		labelEmail.setSize(200, 35);
		labelEmail.setText("Inserire la mail:");

		// Setting delle textBox
		nome.setLocation(300, 100);
		nome.setSize(200, 35);
		nome.setFont(new Font("Arial", 0, 18));

		cognome.setLocation(300, 200);
		cognome.setSize(200, 35);
		cognome.setFont(new Font("Arial", 0, 18));

		email.setLocation(300, 300);
		email.setSize(200, 35);
		email.setFont(new Font("Arial", 0, 18));

		// Setting bottoni
		prenotazioneViaggio = new JButton("Prenota viaggio.");
		prenotazioneViaggio.setLocation(200, 400);
		prenotazioneViaggio.setSize(panelTitolo.getWidth() / 4, 50);
		prenotazioneViaggio.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelPrenotazione.setLayout(null);
		panelPrenotazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelPrenotazione.setLocation(5, altezzaTitolo);

		panelPrenotazione.add(labelNome);
		panelPrenotazione.add(labelCognome);
		panelPrenotazione.add(labelEmail);
		panelPrenotazione.add(nome);
		panelPrenotazione.add(cognome);
		panelPrenotazione.add(email);
		panelPrenotazione.add(prenotazioneViaggio);
		panelPrenotazione.add(back);
		panelPrenotazione.add(areaVisualizzazione);

		pannelloClientePrenotaViaggio.add(panelPrenotazione);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		back.addActionListener(backListener);
		prenotazioneViaggio.addActionListener(buttonsListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == prenotazioneViaggio) {
				if (!controlloreCliente.verificaDatiViaggiatore(nome.getText(),
						cognome.getText(), email.getText()))
					areaVisualizzazione.append("Inserire i dati del traveler.");
				else {
					List<String> listaCatalogo = boundaryClienteOrdinaViaggi
							.prelevaComboBoxCatalogo();
					Integer idOfferta = boundaryClienteVisualizzaOfferta
							.getIdOfferta();
					//L'acquirente è lo username dell'utente. Può essere estratto dalle sue informazioni.
					String acquirente = "Cliente";
					try {
						controlloreCliente.inserimentoInPrenotazione(listaCatalogo,
								idOfferta, acquirente, nome.getText(),
								cognome.getText(), email.getText());
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloClientePrenotaViaggio.setVisible(false);
			BoundaryClienteVisualizzaOfferta.pannelloClienteVisualizzaOfferta
					.setVisible(true);
		}
	}
}
