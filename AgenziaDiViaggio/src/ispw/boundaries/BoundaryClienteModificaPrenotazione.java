package ispw.boundaries;

import ispw.control.ControlloreCliente;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Gambella Riccardo Boundary PromotoreInserimentoCatalogo.
 */
public class BoundaryClienteModificaPrenotazione extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497687086928751350L;

	private ControlloreCliente controlloreCliente = null;
	private BoundaryClienteModificaPrenotazione boundaryClienteModificaPrenotazione;
	public static JPanel pannelloModificaPrenotazione;

	// Testo di Presentazione
	public JLabel labelModifica = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();
	public JLabel labelIdOfferta;

	public JTextField prenotazione;

	// Bottone
	public JButton aggiuntaBiglietti;
	public JButton rimozioneBiglietti;
	public JButton back;

	public JTextArea areaVisualizzazione;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryClienteModificaPrenotazione() throws DAOException,
			MapException, SQLException, DataException, OraException,
			CatalogoException {

		this.controlloreCliente = ControlloreCliente.getInstance();
		this.boundaryClienteModificaPrenotazione = this;
		pannelloModificaPrenotazione = new JPanel();

		pannelloModificaPrenotazione.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloModificaPrenotazione);
		pannelloModificaPrenotazione.setLayout(null);

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

		pannelloModificaPrenotazione.add(panelTitolo);

		labelIdOfferta = new JLabel();

		prenotazione = new JTextField("", 20);

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 350);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		aggiuntaBiglietti = new JButton("Aggiunta Biglietti");
		aggiuntaBiglietti.setLocation(100, 150);
		aggiuntaBiglietti.setSize(300, 50);
		aggiuntaBiglietti.setFont(new Font("Arial", 0, 20));
		
		rimozioneBiglietti = new JButton("Rimozione Biglietti");
		rimozioneBiglietti.setLocation(100, 250);
		rimozioneBiglietti.setSize(300, 50);
		rimozioneBiglietti.setFont(new Font("Arial", 0, 20));

		// Setting Label
		labelModifica.setFont(new Font("Arial", 0, 30));
		labelModifica.setLocation(border, 30);
		labelModifica.setSize(pannelloModificaPrenotazione.getWidth(), 35);
		labelModifica.setHorizontalAlignment(JLabel.CENTER);
		labelModifica.setVerticalAlignment(JLabel.CENTER);
		labelModifica
				.setText("Inserisci l'id dell'prenotazione da modificare.");

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

		panelButtons.add(labelModifica);

		panelButtons.add(areaVisualizzazione);

		panelButtons.add(aggiuntaBiglietti);

		panelButtons.add(rimozioneBiglietti);
		
		panelButtons.add(back);

		pannelloModificaPrenotazione.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		aggiuntaBiglietti.addActionListener(buttonsListener);
		rimozioneBiglietti.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == aggiuntaBiglietti) {

				try {
					if (!controlloreCliente.verificaPrenotazione(prenotazione
							.getText())) {
						areaVisualizzazione.setText("");
						areaVisualizzazione
								.append("Inserire id della prenotazione.");
					} else {
						pannelloModificaPrenotazione.setVisible(false);
						new BoundaryClienteAggiuntaBiglietti(boundaryClienteModificaPrenotazione);
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
			else if(event.getSource() == rimozioneBiglietti){
				if (!controlloreCliente.verificaPrenotazione(prenotazione
						.getText())) {
					areaVisualizzazione.setText("");
					areaVisualizzazione
							.append("Inserire id della prenotazione.");
				} else {
					pannelloModificaPrenotazione.setVisible(false);
					try {
						new BoundaryClienteRimuoviBiglietti(boundaryClienteModificaPrenotazione);
					} catch (DAOException | MapException | SQLException
							| DataException | OraException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch(CatalogoException e){
						// TODO Auto-generated catch block
						areaVisualizzazione.setText("");
						areaVisualizzazione.append("Id della prenotazione non presente.");
					}
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloModificaPrenotazione.setVisible(false);
			BoundaryCliente.pannelloCliente
					.setVisible(true);
		}
	}
	
	public Integer getIdPrenotazione(){
		return new Integer(prenotazione.getText());
	}
}
