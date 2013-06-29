package ordinaViaggi.boundaries;

import ordinaViaggi.control.ControllorePromotore;
import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelAmbiente;
	public JLabel labelMezzo;
	public JLabel labelTipoMezzo;
	public JLabel labelCittaPartenza;
	public JLabel labelCittaArrivo;
	public JLabel labelVia;

	public JTextField ambiente;
	public JTextField mezzo;
	public JTextField tipoMezzo;
	public JTextField cittaPartenza;
	public JTextField cittaArrivo;
	public JTextField via;

	// Bottone
	public JButton rimozioneCatalogo;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryPromotoreRimozioneCatalogo() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {

		this.controllorePromotore = ControllorePromotore.getIstance();

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

		labelAmbiente = new JLabel();
		labelMezzo = new JLabel();
		labelTipoMezzo = new JLabel();
		labelCittaPartenza = new JLabel();
		labelCittaArrivo = new JLabel();
		labelVia = new JLabel();

		ambiente = new JTextField("", 20);
		mezzo = new JTextField("", 20);
		tipoMezzo = new JTextField("", 20);
		cittaPartenza = new JTextField("", 20);
		cittaArrivo = new JTextField("", 20);
		via = new JTextField("", 20);

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
		labelAmbiente.setFont(new Font("Arial", 0, 18));
		labelAmbiente.setLocation(100, 100);
		labelAmbiente.setSize(150, 35);
		labelAmbiente.setText("Ambiente:");

		labelMezzo.setFont(new Font("Arial", 0, 18));
		labelMezzo.setLocation(100, 140);
		labelMezzo.setSize(150, 35);
		labelMezzo.setText("Mezzo:");

		labelTipoMezzo.setFont(new Font("Arial", 0, 18));
		labelTipoMezzo.setLocation(100, 180);
		labelTipoMezzo.setSize(150, 35);
		labelTipoMezzo.setText("Tipo:");

		labelCittaPartenza.setFont(new Font("Arial", 0, 18));
		labelCittaPartenza.setLocation(100, 220);
		labelCittaPartenza.setSize(150, 35);
		labelCittaPartenza.setText("Citta di partenza:");

		labelCittaArrivo.setFont(new Font("Arial", 0, 18));
		labelCittaArrivo.setLocation(100, 260);
		labelCittaArrivo.setSize(150, 35);
		labelCittaArrivo.setText("Citta di arrivo:");

		labelVia.setFont(new Font("Arial", 0, 18));
		labelVia.setLocation(100, 300);
		labelVia.setSize(150, 35);
		labelVia.setText("Via:");

		// Setting delle textBox
		ambiente.setLocation(300, 100);
		ambiente.setSize(200, 35);
		ambiente.setFont(new Font("Arial", 0, 18));

		mezzo.setLocation(300, 140);
		mezzo.setSize(200, 35);
		mezzo.setFont(new Font("Arial", 0, 18));

		tipoMezzo.setLocation(300, 180);
		tipoMezzo.setSize(200, 35);
		tipoMezzo.setFont(new Font("Arial", 0, 18));

		cittaPartenza.setLocation(300, 220);
		cittaPartenza.setSize(200, 35);
		cittaPartenza.setFont(new Font("Arial", 0, 18));

		cittaArrivo.setLocation(300, 260);
		cittaArrivo.setSize(200, 35);
		cittaArrivo.setFont(new Font("Arial", 0, 18));

		via.setLocation(300, 300);
		via.setSize(200, 35);
		via.setFont(new Font("Arial", 0, 18));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(labelAmbiente);
		panelButtons.add(labelMezzo);
		panelButtons.add(labelTipoMezzo);
		panelButtons.add(labelCittaPartenza);
		panelButtons.add(labelCittaArrivo);
		panelButtons.add(labelVia);

		panelButtons.add(ambiente);
		panelButtons.add(mezzo);
		panelButtons.add(tipoMezzo);
		panelButtons.add(cittaPartenza);
		panelButtons.add(cittaArrivo);
		panelButtons.add(via);

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

				String mezzoConcatenato = mezzo.getText() + " "
						+ tipoMezzo.getText();
				if (!controllorePromotore.verificaDati(ambiente.getText(),
						mezzoConcatenato, cittaPartenza.getText(),
						cittaArrivo.getText(), via.getText())) {
					System.out.println("Dati non inseriti totalmente");
				} else {
					try {
						controllorePromotore.rimozioneInCatalogo(
								ambiente.getText(), mezzoConcatenato,
								cittaPartenza.getText(), cittaArrivo.getText(),
								via.getText());
					} catch (ControllerException | IOException | DAOException
							| MapException | CatalogoException | SQLException
							| DataException | OraException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
