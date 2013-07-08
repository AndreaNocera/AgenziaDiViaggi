package ispw.boundaries;

import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;
import ispw.log.Log;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 
 * @author Gambella Riccardo
 * Boundary del Cliente.
 */

public class BoundaryAmministratore extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3260609773601731127L;

	public static JPanel pannelloAmministratore;

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();

	// Bottone
	public JButton visualizzaPrenotazioni;
	public JButton gestioneUtenti;
	public JButton calcolaIndici;
	public JButton stampaLog;
	
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratore() {

		pannelloAmministratore = new JPanel();

		pannelloAmministratore.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministratore);
		pannelloAmministratore.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore amministratore.");

		pannelloAmministratore.add(panelTitolo);

		// Creazione bottone
		visualizzaPrenotazioni = new JButton("Visualizza Prenotazioni");
		visualizzaPrenotazioni.setLocation(100, 100);
		visualizzaPrenotazioni.setSize(300, 50);
		visualizzaPrenotazioni.setFont(new Font("Arial", 0, 20));
		
		gestioneUtenti = new JButton("Gestione utenti");
		gestioneUtenti.setLocation(100, 200);
		gestioneUtenti.setSize(300, 50);
		gestioneUtenti.setFont(new Font("Arial", 0, 20));
		
		calcolaIndici = new JButton("Calcola indici");
		calcolaIndici.setLocation(100, 300);
		calcolaIndici.setSize(300, 50);
		calcolaIndici.setFont(new Font("Arial", 0, 20));
		
		stampaLog = new JButton("Stampa log");
		stampaLog.setLocation(100, 400);
		stampaLog.setSize(300, 50);
		stampaLog.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(500, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(), AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(visualizzaPrenotazioni);
		panelButtons.add(gestioneUtenti);
		panelButtons.add(calcolaIndici);
		panelButtons.add(stampaLog);
		panelButtons.add(back);

		pannelloAmministratore.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		visualizzaPrenotazioni.addActionListener(buttonsListener);
		gestioneUtenti.addActionListener(buttonsListener);
		calcolaIndici.addActionListener(buttonsListener);
		stampaLog.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == visualizzaPrenotazioni) {
				pannelloAmministratore.setVisible(false);
				/*
				 * Passaggio alla Boundary cliente Ordina Viaggi. Forse bisogna
				 * mettere singleton. Altrimenti crea una nuova
				 * BoundaryClienteOrdinaViaggi a ogni passaggio back->Ritorno.
				 */
				try {
					new BoundaryAmministratoreVisualizzaPrenotazioniSceltaTratta();
				} catch (DAOException | MapException | SQLException
						| DataException | OraException | CatalogoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(event.getSource() == gestioneUtenti){
				pannelloAmministratore.setVisible(false);
				new BoundaryAmministratoreGestioneUtenti();
			}
			else if(event.getSource() == calcolaIndici){
				pannelloAmministratore.setVisible(false);
				new BoundaryAmministratoreCalcolaIndici();
			}
			else if(event.getSource() == stampaLog){
				Log log = Log.getInstance();
				List<String> listaLog = log.CaricaLogLista();
				System.out.println("Log.");
				for(String temp : listaLog){
					System.out.println(temp);
				}
				JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
						"Log stampato su console.");
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == back) {
				pannelloAmministratore.setVisible(false);
				AABoundaryAvvio.pannello.setVisible(true);
			}
		}
	}

}
