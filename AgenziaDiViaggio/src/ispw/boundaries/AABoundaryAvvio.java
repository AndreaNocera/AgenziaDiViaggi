package ispw.boundaries;

import ispw.entity.Session;
import ispw.test.TestThread;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * 
 * @author Gambella Riccardo Boudary di Avvio.
 * 
 */

public class AABoundaryAvvio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7371499973005380451L;

	// Pannelli

	public static JPanel pannello = new JPanel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	// Bottoni/
	public JButton login = new JButton("Login");
	public JButton visitatore = new JButton("Visitatore");
	public JButton cliente = new JButton("Gestione Cliente");
	public JButton venditore = new JButton("Gestione Venditore");
	public JButton gestoreEccezioni = new JButton("Gestione Eccezioni");
	public JButton promotore = new JButton("Gestione Promotore");
	public JButton progettista = new JButton("Gestione Progettista");
	public JButton amministratore = new JButton("Gestione Amministratore");

	// Ascoltatori di bottoni e relative azioni
	private Gestore gestoreListener;

	// Variabili statiche utili per tutti
	public static JFrame Frame;

	private AABoundaryAvvio boundaryAvvio;

	public AABoundaryAvvio() {
		
		//Test sui thread
		new TestThread();

		boundaryAvvio = this;

		// Salvare solo ascendenti
		Frame = this;
		this.setTitle("Boundary Avvio");

		Frame.setLayout(null);
		setSize(900, 600);
		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		pannello.setSize(Frame.getWidth(), Frame.getHeight());
		this.add(pannello);
		pannello.setLayout(null);

		int border = 5;
		int altezzaTitolo = 30;

		/* Title Panel */

		panelTitolo.setLayout(null);
		panelTitolo.setSize(this.getWidth(), altezzaTitolo * 3);
		panelTitolo.setLocation(5, 5);
		// panelTitolo.setBounds(0, 0, 800, 400);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 20));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 30);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Voyager");

		panelButtons.setLayout(null);
		panelButtons.setSize(getWidth(), getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(login);
		panelButtons.add(visitatore);
		panelButtons.add(cliente);
		panelButtons.add(venditore);
		panelButtons.add(gestoreEccezioni);
		panelButtons.add(promotore);
		panelButtons.add(progettista);
		panelButtons.add(amministratore);

		login.setBounds(100, 100, 200, 60);
		visitatore.setBounds(400, 100, 200, 60);
		cliente.setBounds(100, 100, 200, 60);
		cliente.setVisible(false);
		venditore.setBounds(400, 100, 200, 60);
		venditore.setVisible(false);
		gestoreEccezioni.setBounds(100, 200, 200, 60);
		gestoreEccezioni.setVisible(false);
		progettista.setBounds(400, 200, 200, 60);
		progettista.setVisible(false);
		promotore.setBounds(100, 300, 200, 60);
		promotore.setVisible(false);
		amministratore.setBounds(400, 300, 200, 60);
		amministratore.setVisible(false);

		/* Add panel of title and buttons */
		pannello.add(panelTitolo);
		pannello.add(panelButtons);

		// Definisci ascoltatori di bottoni e relative azioni
		gestoreListener = new Gestore();

		// Associa ascoltatori e bottoni
		login.addActionListener(gestoreListener);
		visitatore.addActionListener(gestoreListener);
		cliente.addActionListener(gestoreListener);
		venditore.addActionListener(gestoreListener);
		gestoreEccezioni.addActionListener(gestoreListener);
		promotore.addActionListener(gestoreListener);
		progettista.addActionListener(gestoreListener);
		amministratore.addActionListener(gestoreListener);

		// Rendi visibile questo frame;
		this.setVisible(true);

	}

	// Fine costruttore

	/*
	 * Deve far partire la boundary del promotore
	 */
	private class Gestore implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == login) {
				pannello.setVisible(false);
				new BoundaryLogin(boundaryAvvio);
			} else if (e.getSource() == visitatore) {
				pannello.setVisible(false);
				new BoundaryVisitatoreSceltaTratta();
			} else if (e.getSource() == cliente) {
				pannello.setVisible(false);
				new BoundaryCliente();
			} else if (e.getSource() == venditore) {
				pannello.setVisible(false);
				new BoundaryVenditore();
			} else if (e.getSource() == gestoreEccezioni) {
				pannello.setVisible(false);
				new BoundaryGestoreEccezioni();
			} else if (e.getSource() == promotore) {
				pannello.setVisible(false);
				new BoundaryPromotore();
			} else if (e.getSource() == progettista) {
				pannello.setVisible(false);
				new BoundaryProgettista();
			} else if (e.getSource() == amministratore) {
				pannello.setVisible(false);
				new BoundaryAmministratore();
			}
		}
	}

	public void setButtons() {
		// Gestione dei bottoni in base al ruolo
		login.setVisible(false);
		visitatore.setVisible(false);
		Session session = Session.getIstance();
		if (session.getRuolo().equals("Amministratore")) {
			cliente.setVisible(true);
			venditore.setVisible(true);
			gestoreEccezioni.setVisible(true);
			progettista.setVisible(true);
			promotore.setVisible(true);
			amministratore.setVisible(true);
		} else if (session.getRuolo().equals("Promotore")) {
			cliente.setVisible(true);
			venditore.setVisible(true);
			gestoreEccezioni.setVisible(true);
			progettista.setVisible(true);
			promotore.setVisible(true);
		} else if (session.getRuolo().equals("Progettista")) {
			cliente.setVisible(true);
			venditore.setVisible(true);
			gestoreEccezioni.setVisible(true);
			progettista.setVisible(true);
		} else if (session.getRuolo().equals("Gestore Eccezioni")) {
			cliente.setVisible(true);
			venditore.setVisible(true);
			gestoreEccezioni.setVisible(true);
		} else if (session.getRuolo().equals("Venditore")) {
			cliente.setVisible(true);
			venditore.setVisible(true);
		} else if (session.getRuolo().equals("Cliente")) {
			cliente.setVisible(true);
		} else {
			System.out.println("Ruolo non riconosciuto.");
		}
	}
}
