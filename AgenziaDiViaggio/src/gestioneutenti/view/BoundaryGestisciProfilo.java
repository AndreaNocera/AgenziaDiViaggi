package gestioneutenti.view;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.view
 * 
 * @name BoundaryGestisciProfilo.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import gestioneutenti.model.bean.UtenteBean;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import utils.swing.DatePicker;
import utils.swing.GBCHelper;

public class BoundaryGestisciProfilo extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private static final String[] SESSO = {"Uomo", "Donna"};
	
	private static final String TITOLO = "Gestione Profilo";
	
	private JPanel panelMe;
	private JPanel panelTitolo;
	private JPanel panelMain;
	private JPanel panelBottoni;
	
	private JLabel labelTitolo;
	
	private JLabel labelNome;
	private JLabel labelCognome;
	private JLabel labelCitta;
	private JLabel labelNascita;
	private JLabel labelSesso;	
	private JLabel labelMail;
	private JLabel labelRuolo;
	private JLabel labelUsername;
	private JLabel labelPasswordCorrente;
	private JLabel labelNuovaPassword;
	private JLabel labelConfermaNuovaPassword;
	private JTextField textfieldNome;
	private JTextField textfieldCognome;
	private JTextField textfieldCitta;
	private DatePicker calendarNascita;
	private JComboBox <String>comboSesso;
	private JTextField textfieldMail;
	private JLabel labelContenutoRuolo;
	private JLabel labelContenutoUsername;
	private JCheckBox checkboxCambiaPassword;
	private JPasswordField passwordfieldPasswordCorrente;
	private JPasswordField passwordfieldNuovaPassword;
	private JPasswordField passwordfieldConfermaNuovaPassword;
	private JButton buttonOk;
	private JButton buttonAnnulla;
	
	private UtenteBean utenteBean;
	
	private boolean passwordModificata;
		
	public BoundaryGestisciProfilo() {
		this.panelMe = this;
		this.passwordModificata = false;
		buildDialog();		
	}	
	
	public void setUtente(UtenteBean utenteBean) {
		this.utenteBean = utenteBean;
		this.addAncestorListener(new RefreshListener());
	}
	
	private void aggiornaUI() {
		this.textfieldNome.setText(utenteBean.getNome());
		this.textfieldCognome.setText(utenteBean.getCognome());
		this.textfieldCitta.setText(utenteBean.getCitta());
		this.calendarNascita.setDateAsGregorianCalendar(utenteBean.getNascita());
		this.comboSesso.setSelectedItem(utenteBean.getSesso());
		this.textfieldMail.setText(utenteBean.getMail());
		this.labelContenutoRuolo.setText(utenteBean.getRuolo().asString());
		this.labelContenutoUsername.setText(utenteBean.getUsername());			
	}
	
	private void buildDialog() {
		
		//Initialization
		this.setLayout(new GridBagLayout());
				
		//Panel Title
		this.panelTitolo = new JPanel();
		this.panelTitolo.setLayout(new GridBagLayout());
		this.labelTitolo = new JLabel(TITOLO);
		this.panelTitolo.add(labelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInsets(15, 15, 15, 15));
				
		//Panel Main
		this.panelMain = new JPanel();
		this.panelMain.setLayout(new GridBagLayout());
		this.labelNome = new JLabel("Nome");
		this.labelCognome = new JLabel("Cognome");
		this.labelCitta = new JLabel("Città");
		this.labelNascita = new JLabel("Data di nascita");
		this.labelSesso = new JLabel("Sesso");
		this.labelMail = new JLabel("Mail");
		this.labelRuolo = new JLabel("Ruolo");		
		this.labelUsername = new JLabel("Username");
		this.labelPasswordCorrente = new JLabel("Password");
		this.labelNuovaPassword = new JLabel("Nuova Password");
		this.labelConfermaNuovaPassword = new JLabel("Conferma");
		this.textfieldNome = new JTextField("", 20);
		this.textfieldCognome = new JTextField("", 20);
		this.textfieldCitta = new JTextField("", 20);
		this.calendarNascita = new DatePicker(new GregorianCalendar(27, 06, 1990));
		this.comboSesso = new JComboBox<String>(SESSO);
		this.comboSesso.setSelectedItem("Uomo");
		this.textfieldMail = new JTextField("", 20);
		this.labelContenutoRuolo = new JLabel("");
		this.labelContenutoUsername = new JLabel("");
		this.checkboxCambiaPassword = new JCheckBox("Cambia password", false);
		this.checkboxCambiaPassword.addActionListener(new CambiaPasswordListener());
		this.passwordfieldPasswordCorrente = new JPasswordField("", 20);
		this.passwordfieldPasswordCorrente.setEditable(false);
		this.passwordfieldNuovaPassword = new JPasswordField("", 20);
		this.passwordfieldNuovaPassword.setEditable(false);
		this.passwordfieldConfermaNuovaPassword = new JPasswordField("", 20);
		this.passwordfieldConfermaNuovaPassword.setEditable(false);
		
		this.panelMain.add(this.labelNome, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.labelCognome, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelCitta, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelNascita, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelSesso, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelMail, new GBCHelper(0, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelRuolo, new GBCHelper(0, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelUsername, new GBCHelper(0, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelPasswordCorrente, new GBCHelper(0, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelNuovaPassword, new GBCHelper(0, 10).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelConfermaNuovaPassword, new GBCHelper(0, 11).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		this.panelMain.add(this.textfieldNome, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		this.panelMain.add(this.textfieldCognome, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldCitta, new GBCHelper(1, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.calendarNascita, new GBCHelper(1, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.comboSesso, new GBCHelper(1, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.textfieldMail, new GBCHelper(1, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelContenutoRuolo, new GBCHelper(1, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.labelContenutoUsername, new GBCHelper(1, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.checkboxCambiaPassword, new GBCHelper(1, 8).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.passwordfieldPasswordCorrente, new GBCHelper(1, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.passwordfieldNuovaPassword, new GBCHelper(1, 10).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		this.panelMain.add(this.passwordfieldConfermaNuovaPassword, new GBCHelper(1, 11).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		//Panel Button
		this.panelBottoni = new JPanel();
		this.panelBottoni.setLayout(new GridLayout(1, 2, 5, 5));
		this.buttonOk = new JButton("Ok");
		this.buttonOk.addActionListener(new OkListener());
		this.buttonAnnulla = new JButton("Cancel");
		this.buttonAnnulla.addActionListener(new AnnullaListener());
		this.panelBottoni.add(this.buttonAnnulla);
		this.panelBottoni.add(this.buttonOk);
				
		//Panel Pack
		this.add(this.panelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.add(this.panelMain, new GBCHelper(0, 1).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.add(this.panelBottoni, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(15, 15, 15, 15));
	}
	
	private class RefreshListener implements AncestorListener {

		@Override
		public void ancestorAdded(AncestorEvent event) {
			//utenteBean = retrieveUtente(utenteBean.getUsername());
			aggiornaUI();			
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
			
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
			
		}
		
	}
	
	private class CambiaPasswordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkboxCambiaPassword.isSelected()) {
				passwordfieldPasswordCorrente.setEditable(true);
				passwordfieldNuovaPassword.setEditable(true);
				passwordfieldConfermaNuovaPassword.setEditable(true);
				setHasModifiedPassword(true);				
				
			} else {
				passwordfieldPasswordCorrente.setText("");
				passwordfieldNuovaPassword.setText("");
				passwordfieldConfermaNuovaPassword.setText("");
				passwordfieldPasswordCorrente.setEditable(false);
				passwordfieldNuovaPassword.setEditable(false);
				passwordfieldConfermaNuovaPassword.setEditable(false);
				setHasModifiedPassword(false);
			}			
		}		
	}
	
	private class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkFormDataCompleteness()) {
				if (!hasModifiedPassword() || hasModifiedPassword() && checkPassword()) {
					//if(controller.updateProfile(getUserData()) {
					JOptionPane.showMessageDialog(getParent(), "Profilo correttamente aggiornato!", "Gestione Profilo", JOptionPane.INFORMATION_MESSAGE);		
					//dialogMain.setVisible(false);
				}									
			}			
		}		
	}
	
	private class AnnullaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panelMe.setVisible(false);
		}		
	}
	
	public boolean checkFormDataCompleteness() {
		for (Object info : getFormData()) {
			if (info.toString().isEmpty() || info == null) {
				JOptionPane.showMessageDialog(getParent(), "Oops! Dati mancanti!", "Warning", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkPassword() {
		if (!hasModifiedPassword() || (checkCurrentPassword() && checkNewPasswordConfirmation())) return true;
		return false;
	}
	
	public boolean checkNewPasswordConfirmation() {
		String newPassword = new String(passwordfieldNuovaPassword.getPassword());
		String confirmNewPassword = new String(passwordfieldConfermaNuovaPassword.getPassword());
		
		if (!confirmNewPassword.equals(newPassword)) {
			JOptionPane.showMessageDialog(getParent(), "Oops! Password non confermata!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public boolean checkCurrentPassword() {
		String currentPassword = this.utenteBean.getPassword();
		String password = new String(passwordfieldPasswordCorrente.getPassword());
		
		if (!password.equals(currentPassword)) {
			JOptionPane.showMessageDialog(getParent(), "Oops! Password non corretta!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public Object[] getFormData() {
		String firstname = textfieldNome.getText().toString();
		String lastname = textfieldCognome.getText().toString();
		String city = textfieldCitta.getText().toString();
		GregorianCalendar birth = calendarNascita.getDateAsGregorianCalendar();
		String sex = comboSesso.getSelectedItem().toString();
		String mail = textfieldMail.getText().toString();
		if (hasModifiedPassword()) {
			String currentPassword = new String(passwordfieldPasswordCorrente.getPassword());
			String newPassword = new String(passwordfieldNuovaPassword.getPassword());
			String confirmNewPassword = new String(passwordfieldConfermaNuovaPassword.getPassword());
			Object[] data = {firstname, lastname, city, birth, sex, mail, currentPassword, newPassword, confirmNewPassword};
			return data;			
		} else {
			String password = this.utenteBean.getPassword();
			Object[] data = {firstname, lastname, city, birth, sex, mail, password};	
			return data;
		}		
	}
	
	public boolean hasModifiedPassword() {
		return this.passwordModificata;
	}
	
	public void setHasModifiedPassword(boolean bool) {
		this.passwordModificata = bool;
	}
	
}
