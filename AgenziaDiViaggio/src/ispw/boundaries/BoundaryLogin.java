package ispw.boundaries;


import ispw.control.ControlloreLogin;
import ispw.entity.Session;
import ispw.exception.DAOException;
import ispw.exception.MoreThanOneException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Gambella Riccardo
 * Boudary di Avvio.
 *
 */
	
	public class BoundaryLogin extends JFrame
	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7371499973005380451L;

	private ControlloreLogin controlloreLogin = null;
	private AABoundaryAvvio boundaryAvvio = null;

	public static JPanel pannelloLogin = new JPanel();

	// Testo di Presentazione
	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelUsername;
	public JLabel labelPassword;

	public JTextField usernameTextBox;
	public JPasswordField passwordTextBox;

	// Bottone
	public JButton login;

	private GestoreButtons buttonsListener;

	public JTextArea areaVisualizzazione;
	

	int border = 5;
	int altezzaTitolo = 30;
	
		public BoundaryLogin(AABoundaryAvvio boundaryAvvio)
		{
			
	        controlloreLogin = ControlloreLogin.getInstance();
	        this.boundaryAvvio = boundaryAvvio;

			pannelloLogin = new JPanel();

			pannelloLogin.setSize(AABoundaryAvvio.Frame.getWidth(),
					AABoundaryAvvio.Frame.getHeight());
			AABoundaryAvvio.Frame.add(pannelloLogin);
			pannelloLogin.setLayout(null);

			panelTitolo.setLayout(null);
			panelTitolo.setSize(900, 45);
			panelTitolo.setLocation(5, 5);
			panelTitolo.add(titolo);

			titolo.setFont(new Font("Arial", 0, 30));
			titolo.setLocation(border, border);
			titolo.setSize(panelTitolo.getWidth(), 35);
			titolo.setHorizontalAlignment(JLabel.CENTER);
			titolo.setVerticalAlignment(JLabel.CENTER);
			titolo.setText("Gestore login.");

			pannelloLogin.add(panelTitolo);

			areaVisualizzazione = new JTextArea();
			areaVisualizzazione.setText("");
			areaVisualizzazione.setLocation(10, 400);
			areaVisualizzazione.setSize(900, 600);

			// Setting label
			labelUsername = new JLabel();
			labelUsername.setFont(new Font("Arial", 0, 18));
			labelUsername.setLocation(100, 100);
			labelUsername.setSize(200, 35);
			labelUsername.setText("Username:");

			labelPassword = new JLabel();
			labelPassword.setFont(new Font("Arial", 0, 18));
			labelPassword.setLocation(100, 200);
			labelPassword.setSize(200, 35);
			labelPassword.setText("Password:");
			
			usernameTextBox = new JTextField("", 20);
			usernameTextBox.setLocation(300, 100);
			usernameTextBox.setSize(200, 35);
			usernameTextBox.setFont(new Font("Arial", 0, 18));

			passwordTextBox = new JPasswordField("", 20);
			passwordTextBox.setLocation(300, 200);
			passwordTextBox.setSize(200, 35);
			passwordTextBox.setFont(new Font("Arial", 0, 18));

			// Creazione bottoni
			login = new JButton("Login");
			login.setLocation(100, 300);
			login.setSize(250, 50);
			login.setFont(new Font("Arial", 0, 20));

			panelButtons.setLayout(null);
			panelButtons.setSize(900, 600);
			panelButtons.setLocation(5, altezzaTitolo);

			panelButtons.add(areaVisualizzazione);
			panelButtons.add(labelUsername);
			panelButtons.add(labelPassword);
			panelButtons.add(usernameTextBox);
			panelButtons.add(passwordTextBox);
			panelButtons.add(login);

			pannelloLogin.add(panelButtons);

			// Istanziazione dei Listeners
			buttonsListener = new GestoreButtons();

			// Listener dei bottoni
			login.addActionListener(buttonsListener);
			
		}

		private class GestoreButtons implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == login) {
					if (!controlloreLogin.verificaDati(usernameTextBox.getText(),
							passwordTextBox.getPassword())) {
						areaVisualizzazione.setText("Inserire i dati totalmente.");
					} else {
						try {
							String ruolo = controlloreLogin.login(usernameTextBox.getText(), passwordTextBox.getPassword());
							if(ruolo == null){
								areaVisualizzazione.setText("Username o password non corretti. Riprovare");
								usernameTextBox.setText("");
								passwordTextBox.setText("");
							}
							//Utente trovato
							else{
								//Creazione della sessione
								Session session = Session.getIstance();
								session.setUsername(usernameTextBox.getText());
								session.setRuolo(ruolo);
								boundaryAvvio.setButtons();
								/*
								 * Inizio dell'applicazione con il login
								 */
								pannelloLogin.setVisible(false);
								AABoundaryAvvio.pannello.setVisible(true);
							}
						} catch (MoreThanOneException | DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
	}