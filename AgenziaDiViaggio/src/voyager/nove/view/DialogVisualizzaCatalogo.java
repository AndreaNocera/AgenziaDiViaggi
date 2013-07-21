/**
 * Integrazione modello R. Gambella
 */
package voyager.nove.view;

import voyager.nove.control.ControllorePromotore;
import voyager.nove.exception.CatalogoException;
import voyager.nove.exception.ControllerException;
import voyager.nove.exception.DAOException;
import voyager.nove.exception.DataException;
import voyager.nove.exception.MapException;
import voyager.nove.exception.OraException;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import utils.swing.GBCHelper;

/**
 * 
 *
 */  
public class DialogVisualizzaCatalogo extends JDialog {

	private static final long serialVersionUID = 1L;

	private JDialog dialogMe;
	private JTextArea areaTesto;
	private ControllorePromotore controllorePromotore;

	public DialogVisualizzaCatalogo(JFrame ownerframe) {
		super(ownerframe, "Aggiungi Viaggio", true);
		
		try {
			this.controllorePromotore = ControllorePromotore.getInstance();
		} catch (DAOException | MapException | SQLException | DataException
				| OraException | CatalogoException e) {
			e.printStackTrace();
		}
		
		this.dialogMe = this;
		buildDialog();
	}
	
	private void buildDialog() {

		this.dialogMe=this;
		this.dialogMe.setLayout(new GridBagLayout());
		this.areaTesto= new JTextArea();
		this.add(this.areaTesto, new GBCHelper(0, 0).setWeight(100, 900).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInsets(10, 10, 10, 10));
		
		try {
			for (String tratta : controllorePromotore.visualizzaCatalogo()) areaTesto.append(tratta + '\n');
		} catch (ControllerException | DAOException | MapException
				| CatalogoException | SQLException | DataException
				| OraException e) {
			e.printStackTrace();
		}

	}

}
