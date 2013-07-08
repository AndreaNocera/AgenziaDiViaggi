package ispw.boundaries;

import ispw.control.ControlloreProgettista;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

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

/**
 * @author Gambella Riccardo Boundary ProgettistaInserimentoCatalogo.
 */
public class BoundaryProgettistaVisualizzazioneOfferta extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreProgettista controlloreProgettista = null;
	BoundaryProgettistaGestioneOfferta boundaryProgettistaGestioneOfferta = null;

	public static JPanel pannelloProgettistaVisualizzaOfferta;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelVisualizza = new JPanel();

	public JLabel titolo = new JLabel();

	public JTextArea areaVisualizzazione;

	// Bottone
	public JButton back;

	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryProgettistaVisualizzazioneOfferta(
			BoundaryProgettistaGestioneOfferta boundaryProgettistaGestioneOfferta)
			throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {

		this.controlloreProgettista = ControlloreProgettista.getInstance();
		this.boundaryProgettistaGestioneOfferta = boundaryProgettistaGestioneOfferta;

		pannelloProgettistaVisualizzaOfferta = new JPanel();

		pannelloProgettistaVisualizzaOfferta.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloProgettistaVisualizzaOfferta);
		pannelloProgettistaVisualizzaOfferta.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore ProgettistaVisualizzaCatalogo");

		pannelloProgettistaVisualizzaOfferta.add(panelTitolo);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setLocation(10, 100);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(), 300);

		// Chiama il controllore e ottiene il catalogo
		List<String> listaOfferte;
		try {

			listaOfferte = controlloreProgettista
					.visualizzaOfferta(boundaryProgettistaGestioneOfferta
							.prelevaComboBoxCatalogo());
			for (String offerta : listaOfferte)
				areaVisualizzazione.append(offerta + '\n');
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
		}

		panelVisualizza.setLayout(null);
		panelVisualizza.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelVisualizza.setLocation(5, altezzaTitolo);

		panelVisualizza.add(back);
		panelVisualizza.add(areaVisualizzazione);

		pannelloProgettistaVisualizzaOfferta.add(panelVisualizza);

		// Istanziazione dei Listeners
		backListener = new GestoreBack();

		// Listener dei bottoni
		back.addActionListener(backListener);
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloProgettistaVisualizzaOfferta.setVisible(false);
			BoundaryProgettistaGestioneOfferta.pannelloGestoreOfferta.setVisible(true);
		}
	}
}
