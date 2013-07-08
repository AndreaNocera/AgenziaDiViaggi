package ispw.boundaries;

import ispw.control.ControlloreProgettista;
import ispw.exception.CatalogoException;
import ispw.exception.ControllerException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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

	public JLabel labelInserimento = new JLabel();

	private JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelGiorno;
	public JLabel labelMese;
	public JLabel labelAnno;
	public JLabel labelOraPartenza;
	public JLabel labelMinutiPartenza;
	public JLabel labelOraArrivo;
	public JLabel labelMinutiArrivo;
	public JLabel labelPosti;

	public JComboBox<String> giorno;
	public JComboBox<String> mese;
	public JTextField anno;
	public JComboBox<String> oraPartenza;
	public JComboBox<String> minutiPartenza;
	public JComboBox<String> oraArrivo;
	public JComboBox<String> minutiArrivo;
	public JTextField posti;

	public JTextArea areaVisualizzazione;

	// Bottone
	public JButton inserisciOfferta;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryProgettistaInserimentoOfferta(
			BoundaryProgettistaGestioneOfferta boundaryProgettistaGestioneOfferta)
			throws DAOException, MapException, SQLException, DataException,
			OraException, CatalogoException {

		this.controlloreProgettista = ControlloreProgettista.getInstance();
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

		// Setting area
		areaVisualizzazione = new JTextArea();
		areaVisualizzazione.setText("");
		areaVisualizzazione.setLocation(10, 400);
		areaVisualizzazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());

		labelGiorno = new JLabel();
		labelMese = new JLabel();
		labelAnno = new JLabel();
		labelOraPartenza = new JLabel();
		labelMinutiPartenza = new JLabel();
		labelOraArrivo = new JLabel();
		labelMinutiArrivo = new JLabel();
		labelPosti = new JLabel();

		giorno = new JComboBox<String>();
		mese = new JComboBox<String>();
		anno = new JTextField("", 20);
		oraPartenza = new JComboBox<String>();
		minutiPartenza = new JComboBox<String>();
		oraArrivo = new JComboBox<String>();
		minutiArrivo = new JComboBox<String>();
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

		labelInserimento.setFont(new Font("Arial", 0, 30));
		labelInserimento.setLocation(border, 30);
		labelInserimento.setSize(
				pannelloPromotoreInserimentoOfferta.getWidth(), 35);
		labelInserimento.setHorizontalAlignment(JLabel.CENTER);
		labelInserimento.setVerticalAlignment(JLabel.CENTER);
		labelInserimento.setText("Inserisci le informazioni sull'offerta.");

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

		labelOraPartenza.setFont(new Font("Arial", 0, 18));
		labelOraPartenza.setLocation(100, 220);
		labelOraPartenza.setSize(150, 35);
		labelOraPartenza.setText("Ora Partenza:");

		labelMinutiPartenza.setFont(new Font("Arial", 0, 18));
		labelMinutiPartenza.setLocation(100, 260);
		labelMinutiPartenza.setSize(150, 35);
		labelMinutiPartenza.setText("Minuti Partenza:");

		labelOraArrivo.setFont(new Font("Arial", 0, 18));
		labelOraArrivo.setLocation(540, 220);
		labelOraArrivo.setSize(150, 35);
		labelOraArrivo.setText("Ora Arrivo:");

		labelMinutiArrivo.setFont(new Font("Arial", 0, 18));
		labelMinutiArrivo.setLocation(540, 260);
		labelMinutiArrivo.setSize(150, 35);
		labelMinutiArrivo.setText("Minuti Arrivo:");

		labelPosti.setFont(new Font("Arial", 0, 18));
		labelPosti.setLocation(100, 300);
		labelPosti.setSize(150, 35);
		labelPosti.setText("Posti:");

		// Setting delle textBox
		addComboBox(giorno, mese, oraPartenza, minutiPartenza, oraArrivo,
				minutiArrivo);
		giorno.setLocation(300, 100);
		giorno.setSize(200, 35);
		giorno.setSelectedIndex(0);
		giorno.setFont(new Font("Arial", 0, 18));

		mese.setLocation(300, 140);
		mese.setSize(200, 35);
		mese.setSelectedIndex(0);
		mese.setFont(new Font("Arial", 0, 18));

		anno.setLocation(300, 180);
		anno.setSize(200, 35);
		anno.setFont(new Font("Arial", 0, 18));

		oraPartenza.setLocation(300, 220);
		oraPartenza.setSize(200, 35);
		oraPartenza.setSelectedIndex(0);
		oraPartenza.setFont(new Font("Arial", 0, 18));

		minutiPartenza.setLocation(300, 260);
		minutiPartenza.setSize(200, 35);
		minutiPartenza.setSelectedIndex(0);
		minutiPartenza.setFont(new Font("Arial", 0, 18));

		oraArrivo.setLocation(650, 220);
		oraArrivo.setSize(200, 35);
		oraArrivo.setSelectedIndex(0);
		oraArrivo.setFont(new Font("Arial", 0, 18));

		minutiArrivo.setLocation(650, 260);
		minutiArrivo.setSize(200, 35);
		minutiArrivo.setSelectedIndex(0);
		minutiArrivo.setFont(new Font("Arial", 0, 18));

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
		panelButtons.add(labelOraPartenza);
		panelButtons.add(labelMinutiPartenza);
		panelButtons.add(labelOraArrivo);
		panelButtons.add(labelMinutiArrivo);
		panelButtons.add(labelPosti);

		panelButtons.add(giorno);
		panelButtons.add(mese);
		panelButtons.add(anno);
		panelButtons.add(oraPartenza);
		panelButtons.add(minutiPartenza);
		panelButtons.add(oraArrivo);
		panelButtons.add(minutiArrivo);
		panelButtons.add(posti);

		panelButtons.add(areaVisualizzazione);

		panelButtons.add(labelInserimento);

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
					if (!controlloreProgettista.verificaDati(
							(String) giorno.getSelectedItem(),
							(String) mese.getSelectedItem(),
							(String) oraPartenza.getSelectedItem(),
							(String) minutiPartenza.getSelectedItem(),
							(String) oraArrivo.getSelectedItem(),
							(String) minutiArrivo.getSelectedItem(),
							posti.getText())) {
						areaVisualizzazione.setText("");
						areaVisualizzazione
								.append("Dati non inseriti totalmente");
					} else {
						Integer annoInteger;
						if (anno.getText().equals("")) {
							annoInteger = new Integer(2013);
						} else {
							annoInteger = new Integer(anno.getText());
						}
						Integer idofferta = controlloreProgettista
								.inserimentoInOfferta(
										listaCatalogo,
										new Integer((String) giorno
												.getSelectedItem()),
										new Integer((String) mese
												.getSelectedItem()),
										annoInteger,
										new Integer((String) oraPartenza
												.getSelectedItem()),
										new Integer((String) minutiPartenza
												.getSelectedItem()),
										new Integer((String) oraArrivo
												.getSelectedItem()),
										new Integer((String) minutiArrivo
												.getSelectedItem()),
										new Integer(posti.getText()));
						JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
								"Offerta inserita.\nId dell'offerta: "
										+ idofferta);
						giorno.setSelectedIndex(0);
						mese.setSelectedIndex(0);
						anno.setText("");
						oraPartenza.setSelectedIndex(0);
						minutiPartenza.setSelectedIndex(0);
						oraArrivo.setSelectedIndex(0);
						minutiArrivo.setSelectedIndex(0);
						posti.setText("");
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

	private void addComboBox(JComboBox<String> giorno, JComboBox<String> mese,
			JComboBox<String> oraPartenza, JComboBox<String> minutiPartenza,
			JComboBox<String> oraArrivo, JComboBox<String> minutiArrivo) {
		giorno.addItem("--");
		for (int i = 1; i <= 31; i++) {
			giorno.addItem(i + "");
		}

		mese.addItem("--");
		for (int i = 1; i <= 12; i++) {
			mese.addItem(i + "");
		}

		oraPartenza.addItem("--");
		for (int i = 0; i <= 23; i++) {
			if (i < 10)
				oraPartenza.addItem("0" + i);
			else
				oraPartenza.addItem(i + "");
		}

		minutiPartenza.addItem("--");
		for (int i = 0; i <= 59; i++) {
			if (i < 10)
				minutiPartenza.addItem("0" + i);
			else
				minutiPartenza.addItem(i + "");
		}

		oraArrivo.addItem("--");
		for (int i = 0; i <= 23; i++) {
			if (i < 10)
				oraArrivo.addItem("0" + i);
			else
				oraArrivo.addItem(i + "");
		}

		minutiArrivo.addItem("--");
		for (int i = 0; i <= 59; i++) {
			if (i < 10)
				minutiArrivo.addItem("0" + i);
			else
				minutiArrivo.addItem(i + "");
		}
	}
}
