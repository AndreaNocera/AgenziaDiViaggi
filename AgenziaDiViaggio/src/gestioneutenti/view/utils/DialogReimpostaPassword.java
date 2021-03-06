/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.view.utils
 * 
 * @name DialogReimpostaPassword.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.view.utils;

import gestioneutenti.controller.ControllerLogin;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteInesistenteException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import utils.swing.GBCHelper;
import utils.swing.ScreenUtils;

public class DialogReimpostaPassword extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TITOLO = "Reimposta Password";
	
	private JPanel panelReimpostaPassword;
	
	private JLabel labelUsername;
	private JTextField textfieldUsername;
	private JButton buttonInviaResetCode;
	
	private ControllerLogin controllerLogin;
	
	public DialogReimpostaPassword(JFrame ownerFrame) {
		super(ownerFrame, "Reimposta Password", true);
		this.controllerLogin = ControllerLogin.getInstance();
		buildFrame();		
	}

	private void buildFrame() {
		
		//Initialization
		this.setLocation(ScreenUtils.getCentralScreenLocation(this));
		this.setTitle(TITOLO);
		this.setUndecorated(false);
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		//Panel SignIn
		this.panelReimpostaPassword = new JPanel();
		this.panelReimpostaPassword.setLayout(new GridBagLayout());
		this.labelUsername = new JLabel("Username");
		this.textfieldUsername = new JTextField("", 20);
		this.buttonInviaResetCode = new JButton("Invia Reset Code");
		this.buttonInviaResetCode.addActionListener(new InviaResetCodeListener());
		
		this.panelReimpostaPassword.add(labelUsername, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		this.panelReimpostaPassword.add(textfieldUsername, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		this.panelReimpostaPassword.add(buttonInviaResetCode, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(5, 0, 5, 0));
		
		//Dialog packing
		this.add(panelReimpostaPassword, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.pack();		
		
		//Default button
		this.getRootPane().setDefaultButton(buttonInviaResetCode);		
	}

	private class InviaResetCodeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			inviaResetCode();			
		}		
	}
	
	private void inviaResetCode() {
		String username = this.textfieldUsername.getText().toString();		
		try {
			this.controllerLogin.impostaResetCode(username);
			JOptionPane.showMessageDialog(getParent(), "ResetPassword inviato a " + username, "Info", JOptionPane.INFORMATION_MESSAGE);
		} catch (UtenteInesistenteException | DatiUtenteInconsistentiException | LoginInconsistenteException exc) {
			JOptionPane.showMessageDialog(getParent(), "Utente inesistente", "Info", JOptionPane.WARNING_MESSAGE);
		}
		
		this.setVisible(false);
	}

}
