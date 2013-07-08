package ispw.boundaries;

import ispw.control.ControllorePromotore;
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
public class BoundaryPromotoreRimozioneCatalogo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControllorePromotore controllorePromotore = null;

	public static JPanel pannelloPromotoreRimozioneCatalogo;

	// Testo di Presentazione
	public JLabel labelRimozione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelIdTratta;

	public JTextField tratta;

	// Bottone
	public JButton rimozioneCatalogo;
	public JButton back;

	public JTextArea areaVisualizzazione;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryPromotoreRimozioneCatalogo() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {

		this.controllorePromotore = ControllorePromotore.getInstance();

		pannelloPromotoreRimozioneCatalogo = new JPanel();

		pannelloPromotoreRimozioneCatalogo.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloPromotoreRimozioneCatalogo);
		pannelloPromotoreRimozioneCatalogo.setLayout(null);

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

		pannelloPromotoreRimozioneCatalogo.add(panelTitolo);

		labelIdTratta = new JLabel();

		tratta = new JTextField("", 20);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 350);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Bottone rimozioneCatalogo
		rimozioneCatalogo = new JButton("Rimozione in Catalogo");
		rimozioneCatalogo.setLocation(100, 350);
		rimozioneCatalogo.setSize(300, 50);
		rimozioneCatalogo.setFont(new Font("Arial", 0, 20));

		// Setting Label
		labelRimozione.setFont(new Font("Arial", 0, 30));
		labelRimozione.setLocation(border, 30);
		labelRimozione.setSize(pannelloPromotoreRimozioneCatalogo.getWidth(),
				35);
		labelRimozione.setHorizontalAlignment(JLabel.CENTER);
		labelRimozione.setVerticalAlignment(JLabel.CENTER);
		labelRimozione.setText("Inserisci l'id della tratta da rimuovere.");

		labelIdTratta.setFont(new Font("Arial", 0, 18));
		labelIdTratta.setLocation(100, 100);
		labelIdTratta.setSize(150, 35);
		labelIdTratta.setText("Tratta:");

		// Setting delle textBox
		tratta.setLocation(300, 100);
		tratta.setSize(200, 35);
		tratta.setFont(new Font("Arial", 0, 18));

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

		panelButtons.add(labelIdTratta);

		panelButtons.add(tratta);
		
		panelButtons.add(areaVisualizzazione);

		panelButtons.add(rimozioneCatalogo);

		panelButtons.add(back);

		pannelloPromotoreRimozioneCatalogo.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		rimozioneCatalogo.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == rimozioneCatalogo) {

				if (!controllorePromotore.verificaIdTratta(tratta.getText())) {
					areaVisualizzazione.setText("");
					areaVisualizzazione.append("Dati non inseriti totalmente");
				} else {
					try {
						controllorePromotore.rimozioneInCatalogo(new Integer(tratta.getText()));
						JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
								"Tratta rimossa.");
						tratta.setText("");
					} catch (ControllerException | IOException | DAOException
							| MapException | CatalogoException | SQLException
							| DataException | OraException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (GestoreEccezioniException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
								"Impossibile rimuovere tratta.\nEsistono offerte relative alla tratta.");
					}
				}

			}

		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloPromotoreRimozioneCatalogo.setVisible(false);
			BoundaryPromotore.pannelloPromotore.setVisible(true);
		}
	}
}
