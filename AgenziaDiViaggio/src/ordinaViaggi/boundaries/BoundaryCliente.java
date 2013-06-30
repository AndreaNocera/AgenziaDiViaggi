package ordinaViaggi.boundaries;

import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.DataException;
import ordinaViaggi.exception.MapException;
import ordinaViaggi.exception.OraException;

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

public class BoundaryCliente extends JFrame {
	private static final long serialVersionUID = 1L;

	public static JPanel pannelloCliente;

	// Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();

	// Bottone
	public JButton ordinaViaggio;

	public JButton backGestoreCliente;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryCliente() {

		pannelloCliente = new JPanel();

		pannelloCliente.setSize(AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloCliente);
		pannelloCliente.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(AABoundaryAvvio.Frame.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Arial", 0, 30));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Gestore cliente.");

		pannelloCliente.add(panelTitolo);

		// Creazione bottone
		ordinaViaggio = new JButton("Ordina Viaggio");
		ordinaViaggio.setLocation(100, 100);
		ordinaViaggio.setSize(panelTitolo.getWidth() / 4, 50);
		ordinaViaggio.setFont(new Font("Arial", 0, 20));

		// Bottone back
		backGestoreCliente = new JButton("back");
		backGestoreCliente.setLocation(100, 200);
		backGestoreCliente.setSize(panelTitolo.getWidth() / 4, 50);
		backGestoreCliente.setFont(new Font("Arial", 0, 20));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(), 350);
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(ordinaViaggio);
		panelButtons.add(backGestoreCliente);

		pannelloCliente.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		ordinaViaggio.addActionListener(buttonsListener);
		backGestoreCliente.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == ordinaViaggio) {
				pannelloCliente.setVisible(false);
				/*
				 * Passaggio alla Boundary cliente Ordina Viaggi. Forse bisogna
				 * mettere singleton. Altrimenti crea una nuova
				 * BoundaryClienteOrdinaViaggi a ogni passaggio back->Ritorno.
				 */
				try {
					new BoundaryClienteOrdinaViaggi();
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
			if (event.getSource() == backGestoreCliente) {
				pannelloCliente.setVisible(false);
				AABoundaryAvvio.pannello.setVisible(true);
			}
		}
	}

}
