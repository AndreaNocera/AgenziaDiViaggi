package ordinaViaggi.boundaries;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ordinaViaggi.control.ControlloreAmministratore;

/**
 * @author Gambella Riccardo Boundary Amministratore.
 */
public class BoundaryAmministratore extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControlloreAmministratore controlloreAmministratore = null;

	public static JPanel pannelloAmministratore;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();

	// Bottone
	public JButton visualizzaPrenotazioni;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratore(
			ControlloreAmministratore controlloreAmministratore) {

		this.controlloreAmministratore = controlloreAmministratore;

		pannelloAmministratore = new JPanel();

		pannelloAmministratore.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministratore);
		pannelloAmministratore.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Amministratore");

		pannelloAmministratore.add(panelTitolo);

		// Bottone visualizzaCatalogo
		visualizzaPrenotazioni = new JButton("Visualizza Prenotazioni");
		visualizzaPrenotazioni.setLocation(100, 100);
		visualizzaPrenotazioni.setSize(panelTitolo.getWidth() / 3, 50);
		visualizzaPrenotazioni.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(100, 300);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(), 350);
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(visualizzaPrenotazioni);

		panelButtons.add(back);

		pannelloAmministratore.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		visualizzaPrenotazioni.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Stub di metodo generato automaticamente
			if (event.getSource() == visualizzaPrenotazioni) {
				pannelloAmministratore.setVisible(false);
				/*
				 * Passaggio alla Boundary Amministratore Visualizza Offerta. 
				 * Forse bisogna mettere singleton. Altrimenti crea una nuova
				 * BoundaryClienteOrdinaViaggi a ogni passaggio back->Ritorno.
				 */
				new BoundaryAmministratoreVisualizzaOfferta(controlloreAmministratore);
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloAmministratore.setVisible(false);
			AABoundaryAvvio.pannello.setVisible(true);
		}
	}
}
