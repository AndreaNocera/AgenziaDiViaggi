/**
 * 
 */
package voyager.nove.view.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

 

import voyager.nove.control.ControllorePromotore;
import voyager.nove.exception.CatalogoException;
import voyager.nove.exception.ControllerException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.exception.GestoreEccezioniException;
import voyager.nove.exception.MapException;
import voyager.nove.exception.OraException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utils.swing.GBCHelper;
import utils.swing.JHintTextField;

/**
 * 
 *  
 */
public class DialogEliminaViaggio extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String ELIMINA= "Elimina";
	private static final String HINT_TRATTA= "id tratta";

	private JDialog dialogMe;

	private JPanel panelTextField;
	private JPanel panelbottoni;
	private JHintTextField campoTratta;
	private JTextArea areaTesto;
	private JButton bottoneElimina;

	private ControllorePromotore controllorePromotore;
	/**
	 * @param ownerframe
	 */
	public DialogEliminaViaggio(JFrame ownerframe) {
		super(ownerframe, "Aggiungi Viaggio", true);



		try {
			this.controllorePromotore = ControllorePromotore.getInstance();
		} catch (DAOException | MapException | SQLException | DataException
				| OraException | CatalogoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		this.dialogMe = this;
		buildDialog();
	}


	private void buildDialog() {

		this.dialogMe=this;
		this.dialogMe.setLayout(new GridBagLayout());

		this.panelbottoni=new JPanel();
		this.panelbottoni.setLayout(new GridLayout(1,3));
		this.panelTextField=new JPanel();
		this.panelTextField.setLayout(new GridBagLayout());

		this.bottoneElimina = new JButton(ELIMINA);
		this.bottoneElimina.addActionListener(new GestoreButtons());

		//this.labelindicazione= new JLabel(HINT_TRATTA);
		this.campoTratta= new JHintTextField(HINT_TRATTA);
		this.campoTratta.validate();
		this.areaTesto= new JTextArea();

		this.panelTextField.add(campoTratta, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.CENTER).setInsets(5, 15, 10, 15));	
		this.panelTextField.add(areaTesto, new GBCHelper(0, 1).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInsets(5, 5, 5, 5));
		this.panelbottoni.add(bottoneElimina);

		this.add(this.panelTextField, new GBCHelper(0, 0).setWeight(100, 900).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));
		this.add(this.panelbottoni, new GBCHelper(0, 1).setWeight(100, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));

	}

	private class GestoreButtons implements ActionListener {
		private JButton azioneChiamata;

		@Override
		public void actionPerformed(ActionEvent event) {
			azioneChiamata=(JButton) event.getSource();

			switch(azioneChiamata.getText()){

			case ELIMINA:

				if (!controllorePromotore.verificaIdTratta(campoTratta.getText())) {
					areaTesto.setText("");
					areaTesto.append("Inserisci l'id della tratta da eliminare");
				} else {
					try {
						controllorePromotore.rimozioneInCatalogo(new Integer(campoTratta.getText()));
						JOptionPane.showMessageDialog(null,
								"Tratta rimossa.");
						campoTratta.setText("");
					} catch (ControllerException | IOException | DAOException
							| MapException | CatalogoException | SQLException
							| DataException | OraException e) {
						e.printStackTrace();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (GestoreEccezioniException e) {
						JOptionPane.showMessageDialog(null,
								"Impossibile rimuovere tratta.\nEsistono offerte relative alla tratta.");
					}
				}
				break;
			}

		}
	}
}
