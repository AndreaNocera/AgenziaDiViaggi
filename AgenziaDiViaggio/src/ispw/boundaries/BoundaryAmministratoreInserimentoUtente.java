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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo Boundary PromotoreInserimentoCatalogo.
 */
public class BoundaryAmministratoreInserimentoUtente extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreAmministratore controlloreAmministratore = null;

	public static JPanel pannelloInserimentoUtente;

	// Testo di Presentazione
	public JLabel labelInserimento = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelUsername;
	public JLabel labelPassword;
	public JLabel labelNome;
	public JLabel labelCognome;

	// Da trasformare in JComboBox creando la tabellia ruoli
	public JLabel labelRuolo;

	public JTextField username;
	public JTextField password;
	public JTextField nome;
	public JTextField cognome;
	public JComboBox<String> ruolo;

	// Bottone
	public JButton inserisciUtente;
	public JButton back;

	public JTextArea areaVisualizzazione;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreInserimentoUtente() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {

		this.controlloreAmministratore = ControlloreAmministratore.getInstance();

		pannelloInserimentoUtente = new JPanel();

		pannelloInserimentoUtente.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloInserimentoUtente);
		pannelloInserimentoUtente.setLayout(null);

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

		pannelloInserimentoUtente.add(panelTitolo);

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setText("");
		areaVisualizzazione.setLocation(10, 400);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());

		labelUsername = new JLabel();
		labelPassword = new JLabel();
		labelNome = new JLabel();
		labelCognome = new JLabel();
		labelRuolo = new JLabel();

		username = new JTextField("", 20);
		password = new JTextField("", 20);
		nome = new JTextField("", 20);
		cognome = new JTextField("", 20);
		ruolo = new JComboBox<String>();

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 350);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Bottone inserisciUtente
		inserisciUtente = new JButton("Inserisci in Catalogo");
		inserisciUtente.setLocation(100, 350);
		inserisciUtente.setSize(300, 50);
		inserisciUtente.setFont(new Font("Arial", 0, 20));

		// Setting Label

		labelInserimento.setFont(new Font("Arial", 0, 30));
		labelInserimento.setLocation(border, 30);
		labelInserimento.setSize(pannelloInserimentoUtente.getWidth(), 35);
		labelInserimento.setHorizontalAlignment(JLabel.CENTER);
		labelInserimento.setVerticalAlignment(JLabel.CENTER);
		labelInserimento.setText("Inserisci le informazioni sulla tratta.");

		labelUsername.setFont(new Font("Arial", 0, 18));
		labelUsername.setLocation(100, 100);
		labelUsername.setSize(150, 35);
		labelUsername.setText("Username:");

		labelPassword.setFont(new Font("Arial", 0, 18));
		labelPassword.setLocation(100, 140);
		labelPassword.setSize(150, 35);
		labelPassword.setText("Password:");

		labelNome.setFont(new Font("Arial", 0, 18));
		labelNome.setLocation(100, 180);
		labelNome.setSize(150, 35);
		labelNome.setText("Nome:");

		labelCognome.setFont(new Font("Arial", 0, 18));
		labelCognome.setLocation(100, 220);
		labelCognome.setSize(150, 35);
		labelCognome.setText("Cognome:");

		labelRuolo.setFont(new Font("Arial", 0, 18));
		labelRuolo.setLocation(100, 260);
		labelRuolo.setSize(150, 35);
		labelRuolo.setText("Ruolo:");

		// Setting delle textBox
		username.setLocation(300, 100);
		username.setSize(200, 35);
		username.setFont(new Font("Arial", 0, 18));

		password.setLocation(300, 140);
		password.setSize(200, 35);
		password.setFont(new Font("Arial", 0, 18));

		nome.setLocation(300, 180);
		nome.setSize(200, 35);
		nome.setFont(new Font("Arial", 0, 18));

		cognome.setLocation(300, 220);
		cognome.setSize(200, 35);
		cognome.setFont(new Font("Arial", 0, 18));

		/* Setting posizioni comboBox */
		addComboBox(ruolo);
		ruolo.setLocation(300, 260);
		ruolo.setSize(200, 35);
		ruolo.setSelectedIndex(0);
		ruolo.setEnabled(true);

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(labelInserimento);
		panelButtons.add(labelUsername);
		panelButtons.add(labelPassword);
		panelButtons.add(labelNome);
		panelButtons.add(labelCognome);
		panelButtons.add(labelRuolo);

		panelButtons.add(username);
		panelButtons.add(password);
		panelButtons.add(nome);
		panelButtons.add(cognome);
		panelButtons.add(ruolo);

		panelButtons.add(areaVisualizzazione);

		panelButtons.add(inserisciUtente);

		panelButtons.add(back);

		pannelloInserimentoUtente.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		inserisciUtente.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == inserisciUtente) {

				if (!controlloreAmministratore.verificaDatiUtente(
						username.getText(), password.getText(), nome.getText(),
						cognome.getText(), (String)ruolo.getSelectedItem())) {
					areaVisualizzazione.setText("");
					areaVisualizzazione.append("Dati non inseriti totalmente.");
				} else {
					try {
						//Inserimento dell'utente nel database.
						controlloreAmministratore.inserimentoUtente(
								username.getText(), password.getText(),
								nome.getText(), cognome.getText(), (String)ruolo.getSelectedItem());
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
							"Utente inserito.");
					//Azzeramento TextBox
					username.setText("");
					password.setText("");
					nome.setText("");
					cognome.setText("");
					ruolo.setSelectedIndex(0);
				}

			}

		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloInserimentoUtente.setVisible(false);
			BoundaryAmministratoreGestioneUtenti.pannelloGestioneUtenti.setVisible(true);
		}
	}
	
	private void addComboBox(JComboBox<String> comboBox) {
		comboBox.addItem("--");
		comboBox.addItem("Cliente");
		comboBox.addItem("Venditore");
		comboBox.addItem("Gestore Eccezioni");
		comboBox.addItem("Progettista");
		comboBox.addItem("Promotore");
		comboBox.addItem("Amministratore");
	}
}
