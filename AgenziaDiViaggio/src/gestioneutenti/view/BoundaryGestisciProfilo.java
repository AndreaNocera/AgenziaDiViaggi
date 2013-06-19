package gestioneutenti.view;

import gestioneutenti.model.Utente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import utils.DatePicker;
import utils.GBCHelper;

public class BoundaryGestisciProfilo extends JDialog{
	
	private static final long serialVersionUID = 1L;

	private static final String[] SEX_CHOICES = {"Uomo", "Donna"};
	
	public JDialog dialogMain;
	
	private JPanel panelMain;
	private JPanel panelButton;
	
	private JLabel labelFirstname;
	private JLabel labelLastname;
	private JLabel labelCity;
	private JLabel labelBirth;
	private JLabel labelSex;	
	private JLabel labelMail;
	private JLabel labelRole;
	private JLabel labelUsername;
	private JLabel labelCurrentPassword;
	private JLabel labelNewPassword;
	private JLabel labelConfirmNewPassword;
	private JTextField textfieldFirstname;
	private JTextField textfieldLastname;
	private JTextField textfieldCity;
	private DatePicker calendarBirth;
	private JComboBox <String>comboGender;
	private JTextField textfieldMail;
	private JLabel labelContentRole;
	private JLabel labelContentUsername;
	private JCheckBox checkboxChangePassword;
	private JPasswordField passwordfieldCurrentPassword;
	private JPasswordField passwordfieldNewPassword;
	private JPasswordField passwordfieldConfirmNewPassword;
	private JButton buttonOk;
	private JButton buttonCancel;
	
	private Utente user;
	
	private boolean modifiedPassword;
		
	public BoundaryGestisciProfilo(JFrame ownerFrame, Utente user) {
		super(ownerFrame, "Gestione Profilo", true);
		this.user = user;
		buildDialog();
		this.modifiedPassword = false;
		dialogMain = this;
	}
	
	private void buildDialog() {
		
		//Initialization
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		
		//Panel Main
		panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
		labelFirstname = new JLabel("Nome");
		labelLastname = new JLabel("Cognome");
		labelCity = new JLabel("Città");
		labelBirth = new JLabel("Data di nascita");
		labelSex = new JLabel("Sesso");
		labelMail = new JLabel("Mail");
		labelRole = new JLabel("Ruolo");		
		labelUsername = new JLabel("Username");
		labelCurrentPassword = new JLabel("Password");
		labelNewPassword = new JLabel("Nuova Password");
		labelConfirmNewPassword = new JLabel("Conferma");
		textfieldFirstname = new JTextField(this.user.getNome(), 20);
		textfieldLastname = new JTextField(this.user.getCognome(), 20);
		textfieldCity = new JTextField(this.user.getCittà(), 20);
		calendarBirth = new DatePicker(this.user.getNascita());
		comboGender = new JComboBox<String>(SEX_CHOICES);
		comboGender.setSelectedItem(this.user.getSesso());
		textfieldMail = new JTextField(this.user.getMail(), 20);
		labelContentRole = new JLabel(this.user.getRuolo());
		labelContentUsername = new JLabel(this.user.getLogin().getUsername());
		checkboxChangePassword = new JCheckBox("Cambia password", false);
		checkboxChangePassword.addActionListener(new ChangePasswordListener());
		passwordfieldCurrentPassword = new JPasswordField("", 20);
		passwordfieldCurrentPassword.setEditable(false);
		passwordfieldNewPassword = new JPasswordField("", 20);
		passwordfieldNewPassword.setEditable(false);
		passwordfieldConfirmNewPassword = new JPasswordField("", 20);
		passwordfieldConfirmNewPassword.setEditable(false);
		panelMain.add(labelFirstname, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		panelMain.add(labelLastname, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelCity, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelBirth, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelSex, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelMail, new GBCHelper(0, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelRole, new GBCHelper(0, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelUsername, new GBCHelper(0, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelCurrentPassword, new GBCHelper(0, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelNewPassword, new GBCHelper(0, 10).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelConfirmNewPassword, new GBCHelper(0, 11).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		panelMain.add(textfieldFirstname, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		panelMain.add(textfieldLastname, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(textfieldCity, new GBCHelper(1, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(calendarBirth, new GBCHelper(1, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(comboGender, new GBCHelper(1, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(textfieldMail, new GBCHelper(1, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelContentRole, new GBCHelper(1, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelContentUsername, new GBCHelper(1, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(checkboxChangePassword, new GBCHelper(1, 8).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(passwordfieldCurrentPassword, new GBCHelper(1, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(passwordfieldNewPassword, new GBCHelper(1, 10).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(passwordfieldConfirmNewPassword, new GBCHelper(1, 11).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));

		
		//Panel Button
		panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(1, 2, 5, 5));
		buttonOk = new JButton("Ok");
		buttonOk.addActionListener(new OkListener());
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new CancelListener());
		panelButton.add(buttonCancel);
		panelButton.add(buttonOk);
		
		
		//Dialog Pack
		this.add(panelMain, new GBCHelper(0, 0).setWeight(100, 0).setInsets(15, 15, 15, 15));
		this.add(panelButton, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(15, 15, 15, 15));
		this.pack();
		
		
		//Default button
		this.getRootPane().setDefaultButton(buttonOk);
	}
	
	public class ChangePasswordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkboxChangePassword.isSelected()) {
				passwordfieldCurrentPassword.setEditable(true);
				passwordfieldNewPassword.setEditable(true);
				passwordfieldConfirmNewPassword.setEditable(true);
				setHasModifiedPassword(true);				
				
			} else {
				passwordfieldCurrentPassword.setText("");
				passwordfieldNewPassword.setText("");
				passwordfieldConfirmNewPassword.setText("");
				passwordfieldCurrentPassword.setEditable(false);
				passwordfieldNewPassword.setEditable(false);
				passwordfieldConfirmNewPassword.setEditable(false);
				setHasModifiedPassword(false);
			}			
		}		
	}
	
	public class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkFormDataCompleteness()) {
				if (!hasModifiedPassword() || hasModifiedPassword() && checkPassword()) {
					//if(controller.updateProfile(getUserData()) {
					JOptionPane.showMessageDialog(getParent(), "Profilo correttamente aggiornato!", "Gestione Profilo", JOptionPane.INFORMATION_MESSAGE);		
					dialogMain.setVisible(false);
				}									
			}			
		}		
	}
	
	public class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dialogMain.setVisible(false);
			dialogMain.dispose();
		}		
	}
	
	public boolean checkFormDataCompleteness() {
		for (String info : getFormData()) {
			if (info.toString().isEmpty()) {
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
		String newPassword = new String(passwordfieldNewPassword.getPassword());
		String confirmNewPassword = new String(passwordfieldConfirmNewPassword.getPassword());
		
		if (!confirmNewPassword.equals(newPassword)) {
			JOptionPane.showMessageDialog(getParent(), "Oops! Password non confermata!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public boolean checkCurrentPassword() {
		String currentPassword = this.user.getLogin().getPassword();
		String password = new String(passwordfieldCurrentPassword.getPassword());
		
		if (!password.equals(currentPassword)) {
			JOptionPane.showMessageDialog(getParent(), "Oops! Password non corretta!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public String[] getFormData() {
		String firstname = textfieldFirstname.getText().toString();
		String lastname = textfieldLastname.getText().toString();
		String city = textfieldCity.getText().toString();
		String birth = calendarBirth.getDateAsString().toString();
		String sex = comboGender.getSelectedItem().toString();
		String mail = textfieldMail.getText().toString();
		String role = labelContentRole.getText().toString();
		String username = labelContentUsername.getText().toString();
		if (hasModifiedPassword()) {
			String currentPassword = new String(passwordfieldCurrentPassword.getPassword());
			String newPassword = new String(passwordfieldNewPassword.getPassword());
			String confirmNewPassword = new String(passwordfieldConfirmNewPassword.getPassword());
			String[] data = {firstname, lastname, city, birth, sex, mail, role, username, currentPassword, newPassword, confirmNewPassword};
			return data;			
		} else {
			String password = this.user.getLogin().getPassword();
			String[] data = {firstname, lastname, city, birth, sex, mail, role, username, password};	
			return data;
		}		
	}
	
	public boolean hasModifiedPassword() {
		return this.modifiedPassword;
	}
	
	public void setHasModifiedPassword(boolean bool) {
		this.modifiedPassword = bool;
	}
}
