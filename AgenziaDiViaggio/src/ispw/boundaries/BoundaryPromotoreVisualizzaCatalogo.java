package ispw.boundaries;

import ispw.control.ControllorePromotore;
import ispw.exception.CatalogoException;
import ispw.exception.ControllerException;
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
 * @author Gambella Riccardo Boundary PromotoreInserimentoCatalogo.
 */
public class BoundaryPromotoreVisualizzaCatalogo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControllorePromotore controllorePromotore = null;

	public static JPanel pannelloPromotoreVisualizzaCatalogo;

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

	public BoundaryPromotoreVisualizzaCatalogo() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException, ControllerException {

		this.controllorePromotore = ControllorePromotore.getInstance();

		pannelloPromotoreVisualizzaCatalogo = new JPanel();

		pannelloPromotoreVisualizzaCatalogo.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloPromotoreVisualizzaCatalogo);
		pannelloPromotoreVisualizzaCatalogo.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore PromotoreVisualizzaCatalogo");

		pannelloPromotoreVisualizzaCatalogo.add(panelTitolo);

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
		List<String> listaTratte;
		listaTratte = controllorePromotore.visualizzaCatalogo();
		for (String tratta : listaTratte)
			areaVisualizzazione.append(tratta + '\n');

		panelVisualizza.setLayout(null);
		panelVisualizza.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelVisualizza.setLocation(5, altezzaTitolo);

		panelVisualizza.add(back);
		panelVisualizza.add(areaVisualizzazione);

		pannelloPromotoreVisualizzaCatalogo.add(panelVisualizza);

		// Istanziazione dei Listeners
		backListener = new GestoreBack();

		// Listener dei bottoni
		back.addActionListener(backListener);
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloPromotoreVisualizzaCatalogo.setVisible(false);
			BoundaryPromotore.pannelloPromotore.setVisible(true);
		}
	}
}
