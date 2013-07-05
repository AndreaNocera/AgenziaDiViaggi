/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.view
 * 
 * @name BoundaryLogin.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.view;

import gestioneutenti.controller.ControllerLogin;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.exception.UtenteInesistenteException;
import gestioneutenti.model.bean.LoginBean;
import gestioneutenti.model.bean.UtenteBean;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import utils.swing.FrameClosingListener;
import utils.swing.GBCHelper;
import utils.swing.ScreenUtils;

public class BoundaryLogin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TITOLO = "Login";
	
	private JPanel panelLogin;
	
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JTextField textfieldUsername;
	private JPasswordField passwordfieldPassword;
	private JButton buttonLogin;
	private JLabel labelPasswordDimenticata;
	
	private ControllerLogin controllerLogin;
	
	public BoundaryLogin() {
		buildFrame();	
		this.controllerLogin = ControllerLogin.getInstance();
	}
	
	public void buildFrame() {
		
		//Initialization
		this.setTitle(TITOLO);	
		this.setLocation(ScreenUtils.getCentralScreenLocation(this));
		this.addWindowListener(new FrameClosingListener(this));	
		this.setUndecorated(false);
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		//Panel Login
		this.panelLogin = new JPanel();
		this.panelLogin.setLayout(new GridBagLayout());
		this.labelUsername = new JLabel("Username");
		this.labelPassword = new JLabel("Password");
		this.textfieldUsername = new JTextField("", 20);
		this.passwordfieldPassword = new JPasswordField("", 20);
		this.buttonLogin = new JButton("Login");
		this.buttonLogin.addActionListener(new LoginListener());
		this.labelPasswordDimenticata = new JLabel("Password dimenticata?");
		this.labelPasswordDimenticata.addMouseListener(new PasswordDimenticataListener());
		
		this.panelLogin.add(this.labelUsername, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		this.panelLogin.add(this.textfieldUsername, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		this.panelLogin.add(this.labelPassword, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 0, 0));
		this.panelLogin.add(this.passwordfieldPassword, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 0, 10, 0));
		this.panelLogin.add(this.buttonLogin, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(5, 0, 5, 0));
		this.panelLogin.add(this.labelPasswordDimenticata, new GBCHelper(0, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(5, 0, 5, 0));
		
		//Frame packing
		this.add(this.panelLogin, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.pack();		
		
		//Default button
		this.getRootPane().setDefaultButton(this.buttonLogin);		
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
		
		try {
			UtenteBean utenteBean = this.controllerLogin.login(new LoginBean().setUsername(username).setPassword(password));
			this.controllerLogin.home(utenteBean);
			this.setVisible(false);
		} catch (UtenteInesistenteException | LoginInconsistenteException exc) {
			JOptionPane.showMessageDialog(getParent(), "Oops! Non sei stato riconosciuto!" + username + password, "Info", JOptionPane.INFORMATION_MESSAGE);		
		}			
	}
	
	public void passwordDimenticata() {
		this.controllerLogin.mostraDialogReimpostaPassword(this);
	}
	
}
