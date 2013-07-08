package ispw.boundaries;

import ispw.control.ControlloreAmministratore;
import ispw.exception.CatalogoException;
import ispw.exception.DAOException;
import ispw.exception.DataException;
import ispw.exception.MapException;
import ispw.exception.OraException;
import ispw.indici.IndiciPlot;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * 
 * @author Gambella Riccardo Boundary del Cliente.
 */

public class BoundaryAmministratoreCalcolaIndici extends JFrame {

	/**
	 * 
	 */
	
	private ControlloreAmministratore controlloreAmministratore;
	private static final long serialVersionUID = 3260609773601731127L;

	public static JPanel pannelloAmministratoreVisualizzaIndici;

	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JLabel titolo = new JLabel();

	// Bottone
	public JButton tuttiViaggi;
	public JButton ultimoAnno;
	public JButton ultimaModifica;
	public JButton plotIndici;

	public JButton back;

	private GestoreButtons buttonsListener;
	private GestoreBack backListener;

	int border = 5;
	int altezzaTitolo = 30;

	public BoundaryAmministratoreCalcolaIndici() {

		try {
			controlloreAmministratore = ControlloreAmministratore.getInstance();
		} catch (DAOException | MapException | SQLException | DataException
				| OraException | CatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pannelloAmministratoreVisualizzaIndici = new JPanel();

		pannelloAmministratoreVisualizzaIndici.setSize(
				AABoundaryAvvio.Frame.getWidth(),
				AABoundaryAvvio.Frame.getHeight());
		AABoundaryAvvio.Frame.add(pannelloAmministratoreVisualizzaIndici);
		pannelloAmministratoreVisualizzaIndici.setLayout(null);

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

		pannelloAmministratoreVisualizzaIndici.add(panelTitolo);

		// Creazione bottone
		tuttiViaggi = new JButton("Calcola Indici tutti i viaggi.");
		tuttiViaggi.setLocation(100, 100);
		tuttiViaggi.setSize(400, 50);
		tuttiViaggi.setFont(new Font("Arial", 0, 20));

		ultimoAnno = new JButton("Calcola Indici ultimo anno.");
		ultimoAnno.setLocation(100, 200);
		ultimoAnno.setSize(400, 50);
		ultimoAnno.setFont(new Font("Arial", 0, 20));

		ultimaModifica = new JButton("Calcola indici ultima modifica.");
		ultimaModifica.setLocation(100, 300);
		ultimaModifica.setSize(400, 50);
		ultimaModifica.setFont(new Font("Arial", 0, 20));
		
		plotIndici = new JButton("Plot indici.");
		plotIndici.setLocation(100, 400);
		plotIndici.setSize(400, 50);
		plotIndici.setFont(new Font("Arial", 0, 20));

		// Bottone back
		back = new JButton("back");
		back.setLocation(600, 400);
		back.setSize(panelTitolo.getWidth() / 4, 50);
		back.setFont(new Font("Arial", 0, 20));

		panelButtons.setLayout(null);
		panelButtons.setSize(AABoundaryAvvio.Frame.getWidth(), AABoundaryAvvio.Frame.getHeight());
		panelButtons.setLocation(5, altezzaTitolo);

		panelButtons.add(tuttiViaggi);
		panelButtons.add(ultimoAnno);
		panelButtons.add(ultimaModifica);
		panelButtons.add(plotIndici);
		panelButtons.add(back);

		pannelloAmministratoreVisualizzaIndici.add(panelButtons);

		// Istanziazione dei Listeners
		buttonsListener = new GestoreButtons();
		backListener = new GestoreBack();

		// Listener dei bottoni
		tuttiViaggi.addActionListener(buttonsListener);
		ultimoAnno.addActionListener(buttonsListener);
		ultimaModifica.addActionListener(buttonsListener);
		plotIndici.addActionListener(buttonsListener);
		back.addActionListener(backListener);
	}

	private class GestoreButtons implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == tuttiViaggi) {
				
				try {
					controlloreAmministratore.calcolaIndiciTuttiViaggi();
				} catch (DataException | OraException | DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
						"Indici calcolati e salvati nel DB con tipo TuttiViaggi");
				
			} else if (event.getSource() == ultimoAnno) {
				try {
					controlloreAmministratore.calcolaIndiciUltimoAnno();
				} catch (DataException | OraException | DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(AABoundaryAvvio.Frame,
						"Indici calcolati e salvati nel DB con tipo UltimoAnno");
			} else if (event.getSource() == ultimaModifica) {
				pannelloAmministratoreVisualizzaIndici.setVisible(false);
				// new BoundaryAmministratoreVisualizzaIndiciUltimaModifica();
			} else if (event.getSource() == plotIndici) {
				/*IndiciPlot indiciPlot = IndiciPlot.getInstance();
				
				//Creazione del pannello da IndiciPlot
				JPanel pannelloPlot = indiciPlot.visualizzaPlotIndici("TuttiViaggi", "Mare", "Aliscafo");
				
				//Crea un frame per il plot
				JFrame framePlot = new JFrame();
				framePlot.setTitle("Plot indici.");

				Dimension dim = getToolkit().getScreenSize();
				framePlot.setSize(dim.width, dim.height);
				framePlot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				
				
				//Aggiunta del pannello ottenuto da IndiciPlot
				framePlot.add(pannelloPlot);
				framePlot.setVisible(true);
				*/
			}
		}
	}

	private class GestoreBack implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == back) {
				pannelloAmministratoreVisualizzaIndici.setVisible(false);
				BoundaryAmministratore.pannelloAmministratore.setVisible(true);
			}
		}
	}

}
