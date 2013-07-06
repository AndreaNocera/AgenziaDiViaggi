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
 * @author Gambella Riccardo
 * Boundary Progettista.
 */

public class BoundaryProgettista extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1509189593795356946L;

	public static JPanel pannelloProgettista;
	public static JPanel pannelloSelezionaViaggioOfferta;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();

	// Bottoni
	public JButton gestioneOfferta;
	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryProgettista() 
	{

		pannelloProgettista = new JPanel();

		pannelloProgettista.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloProgettista);
		pannelloProgettista.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore Progettista.");

		pannelloProgettista.add(panelTitolo);

		// Creazione bottone
		gestioneOfferta = new JButton("Gestione Offerta");
		gestioneOfferta.setLocation(100, 100);
		gestioneOfferta.setSize(panelTitolo.getWidth() / 4, 50);
		gestioneOfferta.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(100, 200);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(), 350);
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(gestioneOfferta);
		panelButtons.add(back);

		pannelloProgettista.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		gestioneOfferta.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			/*
			 * Comparsa del pannello Ordina viaggio Aggiunge al frame il
			 * pannello Ordina Viaggio e lo rende l'unico visibile.
			 */
			if (event.getSource() == gestioneOfferta) {
				pannelloProgettista.setVisible(false);
				/*
				 * Passaggio alla Boundary cliente Ordina Viaggi. Forse bisogna
				 * mettere singleton. Altrimenti crea una nuova
				 * BoundaryClienteOrdinaViaggi a ogni passaggio back->Ritorno.
				 */
				try {
					new BoundaryProgettistaGestioneOfferta();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MapException e) {
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
				} catch (CatalogoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pannelloProgettista.setVisible(false);
			AABoundaryAvvio.pannello.setVisible(true);
		}
	}
}
