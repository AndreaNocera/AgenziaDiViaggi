package gestioneutenti.view;

import gestioneutenti.controller.ControllerLogin;
import gestioneutenti.exception.LoginErratoException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import utils.swing.GBCHelper;

public class BoundaryLogin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public static final String FRAME_TITLE = "Login";
	private static final int SCREEN_LOCATION_PROPORTION = 4;
	
	private JPanel panelLogin;
	
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JTextField textfieldUsername;
	private JPasswordField passwordfieldPassword;
	private JButton buttonLogin;
	private JLabel labelForgottenPassword;
	
	private ControllerLogin controllerLogin;
	
	public BoundaryLogin() {
		buildFrame();	
		this.controllerLogin = ControllerLogin.getInstance();
	}
	
	public void buildFrame() {
		
		//Initialization
		this.setDefaultScreenLocation();
		this.setTitle(FRAME_TITLE);			
		this.addWindowListener(new FrameClosingListener());	
		this.setUndecorated(false);
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		//Panel Login
		panelLogin = new JPanel();
		panelLogin.setLayout(new GridBagLayout());
		labelUsername = new JLabel("Username");
		labelPassword = new JLabel("Password");
		textfieldUsername = new JTextField("", 20);
		passwordfieldPassword = new JPasswordField("", 20);
		buttonLogin = new JButton("Login");
		buttonLogin.addActionListener(new LoginListener());
		labelForgottenPassword = new JLabel("Password dimenticata?");
		labelForgottenPassword.addMouseListener(new PasswordDimenticataListener());
		
		panelLogin.add(labelUsername, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		panelLogin.add(textfieldUsername, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		panelLogin.add(labelPassword, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		panelLogin.add(passwordfieldPassword, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 10, 0));
		panelLogin.add(buttonLogin, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(5, 0, 5, 0));
		panelLogin.add(labelForgottenPassword, new GBCHelper(0, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(5, 0, 5, 0));
		
		//Dialog packing
		this.add(panelLogin, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.pack();		
		
		//Default button
		this.getRootPane().setDefaultButton(buttonLogin);		
	}
	
	public class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			login();			
		}		
	}
	
	public class PasswordDimenticataListener extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			passwordDimenticata();
		}		
	}
	
	public void login() {
		String username = this.textfieldUsername.getText().toString();
		String password = String.valueOf(this.passwordfieldPassword.getPassword());
		Login login;
		try {
			login = new Login(username, password);
			Utente utente = this.controllerLogin.login(login);
			this.setVisible(false);
			this.controllerLogin.home(utente);
		} catch (LoginErratoException | LoginInconsistenteException e) {
			JOptionPane.showMessageDialog(getParent(), "Oops! Non sei stato riconosciuto!" + username + password, "Info", JOptionPane.INFORMATION_MESSAGE);		
		}	
	}
	
	public void passwordDimenticata() {
		String username = textfieldUsername.getText().toString();
		this.controllerLogin.mostraDialogReimpostaPassword(this, username);
	}
	
	public void setDefaultScreenLocation() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setLocation(screenWidth / SCREEN_LOCATION_PROPORTION, screenHeight / SCREEN_LOCATION_PROPORTION);
		
	}
	
	public class FrameClosingListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent event) {
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler uscire?", "Voyager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}		
	}
}
