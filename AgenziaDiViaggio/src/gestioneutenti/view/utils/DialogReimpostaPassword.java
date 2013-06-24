package gestioneutenti.view.utils;

import gestioneutenti.controller.ControllerLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import utils.swing.GBCHelper;

public class DialogReimpostaPassword extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	public static final String DIALOG_TITLE = "Reimposta Password";
	private static final int SCREEN_LOCATION_PROPORTION = 4;
	
	private JPanel panelReimpostaPassword;
	
	private JLabel labelResetCode;
	private JLabel labelPassword;
	private JTextField textfieldResetCode;
	private JPasswordField passwordfieldPassword;
	private JButton buttonReset;
	
	private ControllerLogin controllerLogin;
	
	private String username;
	
	public DialogReimpostaPassword(JFrame ownerFrame, String username) {
		super(ownerFrame, "Reimposta Password", true);
		buildFrame();		
		this.username = username;
		this.controllerLogin = ControllerLogin.getInstance();
		JOptionPane.showMessageDialog(getParent(), "ResetCode inviato a " + this.username, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void buildFrame() {
		
		//Initialization
		this.setDefaultScreenLocation();
		this.setTitle(DIALOG_TITLE);
		this.setUndecorated(false);
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		//Panel Login
		panelReimpostaPassword = new JPanel();
		panelReimpostaPassword.setLayout(new GridBagLayout());
		labelResetCode = new JLabel("Reset Code");
		labelPassword = new JLabel("Nuova Password");
		textfieldResetCode = new JTextField("", 20);
		passwordfieldPassword = new JPasswordField("", 20);
		buttonReset = new JButton("Reimposta");
		buttonReset.addActionListener(new ResetListener());
		
		panelReimpostaPassword.add(labelResetCode, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		panelReimpostaPassword.add(textfieldResetCode, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		panelReimpostaPassword.add(labelPassword, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		panelReimpostaPassword.add(passwordfieldPassword, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 10, 0));
		panelReimpostaPassword.add(buttonReset, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(5, 0, 5, 0));
		
		//Dialog packing
		this.add(panelReimpostaPassword, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.pack();		
		
		//Default button
		this.getRootPane().setDefaultButton(buttonReset);		
	}
	
	public class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			reset();			
		}		
	}
	
	public void reset() {
		String resetCode = this.textfieldResetCode.getText().toString();
		String password = this.passwordfieldPassword.getPassword().toString();
		if(this.controllerLogin.reimpostaPassword(username, password, resetCode)) {
			this.controllerLogin.chiudi(this);
			JOptionPane.showMessageDialog(getParent(), "Password reimpostata correttamente", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void setDefaultScreenLocation() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / SCREEN_LOCATION_PROPORTION, screenHeight / SCREEN_LOCATION_PROPORTION);
		
	}

}
