package ispw.boundaries;

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

/**
 * 
 * @author Gambella Riccardo
 * Boundary del Cliente.
 */

public class BoundaryAmministratoreGestioneUtenti extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3260609773601731127L;

	public static JPanel pannelloGestioneUtenti;


	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();

	// Bottone
	public JButton visualizzaUtenti;
	public JButton inserisciUtente;
	public JButton rimuoviUtente;
	

	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreGestioneUtenti() {

		pannelloGestioneUtenti = new JPanel();

		pannelloGestioneUtenti.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloGestioneUtenti);
		pannelloGestioneUtenti.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore amministratore utenti.");

		pannelloGestioneUtenti.add(panelTitolo);

		// Creazione bottoni
		
		visualizzaUtenti = new JButton("Visualizza utenti");
		visualizzaUtenti.setLocation(100, 100);
		visualizzaUtenti.setSize(300, 50);
		visualizzaUtenti.setFont(new Font("Arial", 0, 20));
		
		inserisciUtente = new JButton("Inserimento utente");
		inserisciUtente.setLocation(100, 200);
		inserisciUtente.setSize(300, 50);
		inserisciUtente.setFont(new Font("Arial", 0, 20));
		
		rimuoviUtente = new JButton("Rimozione utente");
		rimuoviUtente.setLocation(100, 300);
		rimuoviUtente.setSize(300, 50);
		rimuoviUtente.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(100, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(), AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(visualizzaUtenti);
		panelButtons.add(inserisciUtente);
		panelButtons.add(rimuoviUtente);
		panelButtons.add(back);

		pannelloGestioneUtenti.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		visualizzaUtenti.addActionListener(buttonsListener);
		inserisciUtente.addActionListener(buttonsListener);
		rimuoviUtente.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == visualizzaUtenti){
				pannelloGestioneUtenti.setVisible(false);
				try {
					new BoundaryAmministratoreVisualizzaUtenti();
				} catch (DAOException | MapException | SQLException
						| DataException | OraException | CatalogoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (event.getSource() == inserisciUtente) {
				pannelloGestioneUtenti.setVisible(false);
				try {
					new BoundaryAmministratoreInserimentoUtente();
				} catch (DAOException | MapException | SQLException
						| DataException | OraException | CatalogoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(event.getSource() == rimuoviUtente){
				pannelloGestioneUtenti.setVisible(false);
				try {
					new BoundaryAmministratoreRimozioneUtente();
				} catch (DAOException | MapException | SQLException
						| DataException | OraException | CatalogoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == back) {
				pannelloGestioneUtenti.setVisible(false);
				BoundaryAmministratore.pannelloAmministratore.setVisible(true);
			}
		}
	}

}
