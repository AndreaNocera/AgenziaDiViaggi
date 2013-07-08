package ispw.boundaries;

import ispw.control.ControlloreAmministratore;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;
import ispw.exception.UtenteException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo Boundary PromotoreInserimentoCatalogo.
 */
public class BoundaryAmministratoreRimozioneUtente extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreAmministratore controlloreAmministratore = null;

	public static JPanel pannelloAmministratoreRimozioneUtente;

	// Testo di Presentazione
	public JLabel labelRimozione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelUsernameUtente;

	public JTextField username;

	// Bottone
	public JButton rimozioneUtente;
	public JButton back;

	public JTextArea areaVisualizzazione;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreRimozioneUtente() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {

		this.controlloreAmministratore = ControlloreAmministratore.getInstance();

		pannelloAmministratoreRimozioneUtente = new JPanel();

		pannelloAmministratoreRimozioneUtente.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministratoreRimozioneUtente);
		pannelloAmministratoreRimozioneUtente.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore PromotoreInserimentoCatalogo");

		pannelloAmministratoreRimozioneUtente.add(panelTitolo);

		labelUsernameUtente = new JLabel();

		username = new JTextField("", 20);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 350);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Bottone rimozioneUtente
		rimozioneUtente = new JButton("Rimuovi Utente");
		rimozioneUtente.setLocation(100, 350);
		rimozioneUtente.setSize(300, 50);
		rimozioneUtente.setFont(new Font("Arial", 0, 20));

		// Setting Label
		labelRimozione.setFont(new Font("Arial", 0, 30));
		labelRimozione.setLocation(border, 30);
		labelRimozione.setSize(
				pannelloAmministratoreRimozioneUtente.getWidth(), 35);
		labelRimozione.setHorizontalAlignment(JLabel.CENTER);
		labelRimozione.setVerticalAlignment(JLabel.CENTER);
		labelRimozione
				.setText("Inserisci lo username dell'utente da rimuovere..");

		labelUsernameUtente.setFont(new Font("Arial", 0, 18));
		labelUsernameUtente.setLocation(100, 100);
		labelUsernameUtente.setSize(150, 35);
		labelUsernameUtente.setText("Username:");

		// Setting delle textBox
		username.setLocation(300, 100);
		username.setSize(200, 35);
		username.setFont(new Font("Arial", 0, 18));

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setText("");
		areaVisualizzazione.setLocation(10, 400);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(labelUsernameUtente);

		panelButtons.add(username);

		panelButtons.add(areaVisualizzazione);

		panelButtons.add(rimozioneUtente);

		panelButtons.add(back);

		pannelloAmministratoreRimozioneUtente.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		rimozioneUtente.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == rimozioneUtente) {

				if (!controlloreAmministratore.verificaUsernameUtente(username
						.getText())) {
					areaVisualizzazione.setText("");
					areaVisualizzazione.append("Dati non inseriti totalmente");
				} else {
					try {
						controlloreAmministratore.rimozioneUtente(username
								.getText());
					} catch (DAOException | SQLException | UtenteException e) {
						// TODO Auto-generated catch block
						areaVisualizzazione.setText("");
						areaVisualizzazione
								.append("Errore nella rimozione dell'utente.");
					}
					JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
							"Utente rimosso.");
					username.setText("");
				}

			}

		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloAmministratoreRimozioneUtente.setVisible(false);
			BoundaryAmministratoreGestioneUtenti.pannelloGestioneUtenti
					.setVisible(true);
		}
	}
}
