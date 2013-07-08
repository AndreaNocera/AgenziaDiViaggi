package ispw.boundaries;

import ispw.control.ControlloreProgettista;
import ispw.exception.CatalogoException;
import ispw.exception.ControllerException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.GestoreEccezioniException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
public class BoundaryProgettistaRimozioneOfferta extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreProgettista controlloreProgettista = null;
	BoundaryProgettistaGestioneOfferta boundaryProgettistaGestioneOfferta = null;

	public static JPanel pannelloPromotoreRimozioneOfferta;

	// Testo di Presentazione
	public JLabel labelRimozione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelIdOfferta;

	public JTextField offerta;

	// Bottone
	public JButton rimozioneOfferta;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	public JTextArea areaVisualizzazione;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryProgettistaRimozioneOfferta(
			BoundaryProgettistaGestioneOfferta boundaryProgettistaGestioneOfferta)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.controlloreProgettista = ControlloreProgettista.getInstance();
		this.boundaryProgettistaGestioneOfferta = boundaryProgettistaGestioneOfferta;

		pannelloPromotoreRimozioneOfferta = new JPanel();

		pannelloPromotoreRimozioneOfferta.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloPromotoreRimozioneOfferta);
		pannelloPromotoreRimozioneOfferta.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore ProgettistaInserimentoOfferta");

		pannelloPromotoreRimozioneOfferta.add(panelTitolo);

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setText("");
		areaVisualizzazione.setLocation(10, 400);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());

		labelIdOfferta = new JLabel();

		offerta = new JTextField("", 20);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 350);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Bottone rimozioneOfferta
		rimozioneOfferta = new JButton("Rimuovi in Offerta");
		rimozioneOfferta.setLocation(100, 350);
		rimozioneOfferta.setSize(300, 50);
		rimozioneOfferta.setFont(new Font("Arial", 0, 20));

		// Setting Label
		labelRimozione.setFont(new Font("Arial", 0, 30));
		labelRimozione.setLocation(border, 30);
		labelRimozione
				.setSize(pannelloPromotoreRimozioneOfferta.getWidth(), 35);
		labelRimozione.setHorizontalAlignment(JLabel.CENTER);
		labelRimozione.setVerticalAlignment(JLabel.CENTER);
		labelRimozione.setText("Inserisci l'id dell'offerta da rimuovere.");

		// Setting Label
		labelIdOfferta.setFont(new Font("Arial", 0, 18));
		labelIdOfferta.setLocation(100, 100);
		labelIdOfferta.setSize(150, 35);
		labelIdOfferta.setText("Offerta:");

		// Setting delle textBox
		offerta.setLocation(300, 100);
		offerta.setSize(200, 35);
		offerta.setFont(new Font("Arial", 0, 18));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(labelIdOfferta);

		panelButtons.add(offerta);

		panelButtons.add(labelRimozione);
		
		panelButtons.add(areaVisualizzazione);

		panelButtons.add(rimozioneOfferta);

		panelButtons.add(back);

		pannelloPromotoreRimozioneOfferta.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		rimozioneOfferta.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == rimozioneOfferta) {

				List<String> listaCatalogo = boundaryProgettistaGestioneOfferta
						.prelevaComboBoxCatalogo();
				try {
					if (!controlloreProgettista.verificaDati(offerta.getText())) {
						areaVisualizzazione.setText("");
						areaVisualizzazione
								.append("Dati non inseriti totalmente");
					} else {
						controlloreProgettista.rimozioneInOfferta(
								listaCatalogo, new Integer(offerta.getText()));
						JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
								"Offerta rimossa dal sistema.");
					}
				} catch (ControllerException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MapException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CatalogoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OraException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GestoreEccezioniException e) {
					// TODO Auto-generated catch block
					JOptionPane
							.showMessageDialog(AABoundaryAvvio.Frame,
									"Impossibile rimuovere offerta.\nEsistono prenotazioni relative all'offerta.");
				}

			}

		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloPromotoreRimozioneOfferta.setVisible(false);
			BoundaryProgettistaGestioneOfferta.pannelloGestoreOfferta
					.setVisible(true);
		}
	}
}
