package ispw.boundaries;

import ispw.control.ControlloreVenditore;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo Boundary PromotoreInserimentoCatalogo.
 */
public class BoundaryVenditoreDisdiciPrenotazione extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreVenditore controlloreVenditore = null;

	public static JPanel pannelloVenditoreDisdiciPrenotazione;

	// Testo di Presentazione
	public JLabel labelRimozione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelIdOfferta;

	public JTextField prenotazione;

	// Bottone
	public JButton disdiciPrenotazione;
	public JButton back;

	public JTextArea areaVisualizzazione;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryVenditoreDisdiciPrenotazione() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {

		this.controlloreVenditore = ControlloreVenditore.getInstance();

		pannelloVenditoreDisdiciPrenotazione = new JPanel();

		pannelloVenditoreDisdiciPrenotazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloVenditoreDisdiciPrenotazione);
		pannelloVenditoreDisdiciPrenotazione.setLayout(null);

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

		pannelloVenditoreDisdiciPrenotazione.add(panelTitolo);

		labelIdOfferta = new JLabel();

		prenotazione = new JTextField("", 20);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 350);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Bottone rimozioneOfferta
		disdiciPrenotazione = new JButton("Disdici prenotazione");
		disdiciPrenotazione.setLocation(100, 350);
		disdiciPrenotazione.setSize(300, 50);
		disdiciPrenotazione.setFont(new Font("Arial", 0, 20));

		// Setting Label
		labelRimozione.setFont(new Font("Arial", 0, 30));
		labelRimozione.setLocation(border, 30);
		labelRimozione.setSize(pannelloVenditoreDisdiciPrenotazione.getWidth(), 35);
		labelRimozione.setHorizontalAlignment(JLabel.CENTER);
		labelRimozione.setVerticalAlignment(JLabel.CENTER);
		labelRimozione
				.setText("Inserisci l'id dell'prenotazione da rimuovere.");

		// Setting Label
		labelIdOfferta.setFont(new Font("Arial", 0, 18));
		labelIdOfferta.setLocation(100, 100);
		labelIdOfferta.setSize(150, 35);
		labelIdOfferta.setText("Prenotazione:");

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setLocation(10, 500);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());

		// Setting delle textBox
		prenotazione.setLocation(300, 100);
		prenotazione.setSize(200, 35);
		prenotazione.setFont(new Font("Arial", 0, 18));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(labelIdOfferta);

		panelButtons.add(prenotazione);

		panelButtons.add(labelRimozione);

		panelButtons.add(areaVisualizzazione);

		panelButtons.add(disdiciPrenotazione);

		panelButtons.add(back);

		pannelloVenditoreDisdiciPrenotazione.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		disdiciPrenotazione.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == disdiciPrenotazione) {

				try {
					if (!controlloreVenditore.verificaPrenotazione(prenotazione
							.getText())) {
						areaVisualizzazione.setText("");
						areaVisualizzazione
								.append("Inserire id della prenotazione.");
					} else {

						controlloreVenditore.rimozioneInPrenotazione(new Integer(
								prenotazione.getText()));
						JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
								"Prenotazione disdetta.");
					}
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MapException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CatalogoException e) {
					// TODO Auto-generated catch block
					areaVisualizzazione.setText("");
					areaVisualizzazione.append("Id della prenotazione non presente.");
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloVenditoreDisdiciPrenotazione.setVisible(false);
			BoundaryVenditore.pannelloVenditore
					.setVisible(true);
		}
	}
}
