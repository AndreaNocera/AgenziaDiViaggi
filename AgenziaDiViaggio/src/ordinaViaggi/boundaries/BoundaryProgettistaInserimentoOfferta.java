package ordinaViaggi.boundaries;

import ordinaViaggi.control.ControlloreProgettista;
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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo Boundary PromotoreInserimentoCatalogo.
 */
public class BoundaryProgettistaInserimentoOfferta extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreProgettista controlloreProgettista = null;
	BoundaryProgettistaGestioneOfferta boundaryProgettistaGestioneOfferta = null;

	public static JPanel pannelloPromotoreInserimentoOfferta;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelGiorno;
	public JLabel labelMese;
	public JLabel labelAnno;
	public JLabel labelOra;
	public JLabel labelMinuti;
	public JLabel labelPosti;

	public JTextField giorno;
	public JTextField mese;
	public JTextField anno;
	public JTextField ora;
	public JTextField minuti;
	public JTextField posti;

	// Bottone
	public JButton inserisciOfferta;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryProgettistaInserimentoOfferta(
			BoundaryProgettistaGestioneOfferta boundaryProgettistaGestioneOfferta)
			throws DAOException, MapException, SQLException, DataException, OraException, CatalogoException {

		this.controlloreProgettista = ControlloreProgettista.getIstance();
		this.boundaryProgettistaGestioneOfferta = boundaryProgettistaGestioneOfferta;

		pannelloPromotoreInserimentoOfferta = new JPanel();

		pannelloPromotoreInserimentoOfferta.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloPromotoreInserimentoOfferta);
		pannelloPromotoreInserimentoOfferta.setLayout(null);

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

		pannelloPromotoreInserimentoOfferta.add(panelTitolo);

		labelGiorno = new JLabel();
		labelMese = new JLabel();
		labelAnno = new JLabel();
		labelOra = new JLabel();
		labelMinuti = new JLabel();
		labelPosti = new JLabel();

		giorno = new JTextField("", 20);
		mese = new JTextField("", 20);
		anno = new JTextField("", 20);
		ora = new JTextField("", 20);
		minuti = new JTextField("", 20);
		posti = new JTextField("", 20);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 350);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		// Bottone inserisciOfferta
		inserisciOfferta = new JButton("Inserisci in Offerta");
		inserisciOfferta.setLocation(100, 350);
		inserisciOfferta.setSize(300, 50);
		inserisciOfferta.setFont(new Font("Arial", 0, 20));

		// Setting Label
		labelGiorno.setFont(new Font("Arial", 0, 18));
		labelGiorno.setLocation(100, 100);
		labelGiorno.setSize(150, 35);
		labelGiorno.setText("Giorno:");

		labelMese.setFont(new Font("Arial", 0, 18));
		labelMese.setLocation(100, 140);
		labelMese.setSize(150, 35);
		labelMese.setText("Mese");

		labelAnno.setFont(new Font("Arial", 0, 18));
		labelAnno.setLocation(100, 180);
		labelAnno.setSize(150, 35);
		labelAnno.setText("Anno:");

		labelOra.setFont(new Font("Arial", 0, 18));
		labelOra.setLocation(100, 220);
		labelOra.setSize(150, 35);
		labelOra.setText("Ora:");

		labelMinuti.setFont(new Font("Arial", 0, 18));
		labelMinuti.setLocation(100, 260);
		labelMinuti.setSize(150, 35);
		labelMinuti.setText("Minuti:");

		labelPosti.setFont(new Font("Arial", 0, 18));
		labelPosti.setLocation(100, 300);
		labelPosti.setSize(150, 35);
		labelPosti.setText("Posti:");

		// Setting delle textBox
		giorno.setLocation(300, 100);
		giorno.setSize(200, 35);
		giorno.setFont(new Font("Arial", 0, 18));

		mese.setLocation(300, 140);
		mese.setSize(200, 35);
		mese.setFont(new Font("Arial", 0, 18));

		anno.setLocation(300, 180);
		anno.setSize(200, 35);
		anno.setFont(new Font("Arial", 0, 18));

		ora.setLocation(300, 220);
		ora.setSize(200, 35);
		ora.setFont(new Font("Arial", 0, 18));

		minuti.setLocation(300, 260);
		minuti.setSize(200, 35);
		minuti.setFont(new Font("Arial", 0, 18));

		posti.setLocation(300, 300);
		posti.setSize(200, 35);
		posti.setFont(new Font("Arial", 0, 18));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(labelGiorno);
		panelButtons.add(labelMese);
		panelButtons.add(labelAnno);
		panelButtons.add(labelMinuti);
		panelButtons.add(labelOra);
		panelButtons.add(labelPosti);

		panelButtons.add(giorno);
		panelButtons.add(mese);
		panelButtons.add(anno);
		panelButtons.add(ora);
		panelButtons.add(minuti);
		panelButtons.add(posti);

		panelButtons.add(inserisciOfferta);

		panelButtons.add(back);

		pannelloPromotoreInserimentoOfferta.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		inserisciOfferta.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == inserisciOfferta) {

				List<String> listaCatalogo = boundaryProgettistaGestioneOfferta
						.prelevaComboBoxCatalogo();
				try {
					if (!controlloreProgettista.verificaDati(giorno.getText(),
							mese.getText(), ora.getText(), minuti.getText(),
							posti.getText())) {
						System.out.println("Dati non inseriti totalmente");
					} else {
						Integer annoInteger;
						if (anno.getText().equals(""))
							annoInteger = new Integer(2013);
						else
							annoInteger = new Integer(anno.getText());

						controlloreProgettista.inserimentoInOfferta(listaCatalogo, new Integer(
								giorno.getText()), new Integer(mese.getText()),
								annoInteger, new Integer(ora.getText()),
								new Integer(minuti.getText()), new Integer(
										posti.getText()));
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
				}

			}

		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloPromotoreInserimentoOfferta.setVisible(false);
			BoundaryProgettistaGestioneOfferta.pannelloGestoreOfferta
					.setVisible(true);
		}
	}
}
