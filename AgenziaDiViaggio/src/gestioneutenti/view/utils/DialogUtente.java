package gestioneutenti.view.utils;

import utils.DatePicker;
import utils.GBCHelper;
import gestioneutenti.exception.DatiUtenteInconsistentiException;
import gestioneutenti.exception.LoginInconsistenteException;
import gestioneutenti.model.DatiUtente;
import gestioneutenti.model.FactoryUtenti;
import gestioneutenti.model.Login;
import gestioneutenti.model.Utente;
import gestioneutenti.model.ruoli.Ruolo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

public class DialogUtente extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] SEX_CHOICES = {"Uomo", "Donna"};
	private static final String[] ROLE_CHOICES = {"Amministratore", "Promotore", "Progettista", "Venditore", "Cliente"};
	
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
	private JLabel labelPassword;
	private JTextField textfieldFirstname;
	private JTextField textfieldLastname;
	private JTextField textfieldCity;
	private DatePicker calendarBirth;
	private JComboBox <String>comboGender;
	private JTextField textfieldMail;
	private JComboBox <String>comboRole;
	private JCheckBox checkboxGeneratePassword;
	private JTextField textfieldUsername;
	private JTextField textfieldPassword;
	private JButton buttonOk;
	private JButton buttonCancel;
	
	private boolean validData;
	private boolean modifiedData;
	
	private Utente utente;
	
	public DialogUtente(JFrame ownerFrame) {
		super(ownerFrame, "Nuovo Utente", true);
		buildDialog();
		setValidData(false);
	}
	
	public DialogUtente(JFrame ownerFrame, Utente user) {
		super(ownerFrame, "Modifica Utente", true);
		buildDialog();
		setUserData(user);
		setValidData(true);
		setModifiedData(false);		
	}
	
	private void buildDialog() {
		
		//Initialization
		this.setResizable(false);
		this.setLayout(new GridBagLayout());		
		this.dialogMain = this;
		
		//Panel Main
		panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());
		labelFirstname = new JLabel("Nome");
		labelLastname = new JLabel("Cognome");
		labelCity = new JLabel("Città");
		labelBirth = new JLabel("Data di nascita");
		labelSex = new JLabel("Sesso");
		labelMail = new JLabel("Mail");
		labelRole = new JLabel("RuoloOld");		
		labelUsername = new JLabel("Username");
		labelPassword = new JLabel("Password");
		textfieldFirstname = new JTextField("", 20);
		textfieldFirstname.addActionListener(new ChangeListener());
		textfieldLastname = new JTextField("", 20);
		textfieldLastname.addActionListener(new ChangeListener());
		textfieldCity = new JTextField("", 20);
		textfieldCity.addActionListener(new ChangeListener());
		calendarBirth = new DatePicker();
		calendarBirth.addActionListener(new ChangeListener());
		comboGender = new JComboBox<String>(SEX_CHOICES);
		comboGender.addActionListener(new ChangeListener());
		textfieldMail = new JTextField("", 20);
		textfieldMail.addActionListener(new ChangeListener());
		comboRole = new JComboBox<String>(ROLE_CHOICES);	
		comboRole.addActionListener(new ChangeListener());
		textfieldUsername = new JTextField("", 20);
		textfieldUsername.addActionListener(new ChangeListener());
		textfieldPassword = new JTextField("", 20);
		textfieldPassword.addActionListener(new ChangeListener());
		checkboxGeneratePassword = new JCheckBox("Genera Password", false);
		checkboxGeneratePassword.addActionListener(new GeneratePasswordListener());
		panelMain.add(labelFirstname, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		panelMain.add(labelLastname, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelCity, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelBirth, new GBCHelper(0, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelSex, new GBCHelper(0, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelMail, new GBCHelper(0, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelRole, new GBCHelper(0, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelUsername, new GBCHelper(0, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(labelPassword, new GBCHelper(0, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));
		
		panelMain.add(textfieldFirstname, new GBCHelper(1, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 10, 15));
		panelMain.add(textfieldLastname, new GBCHelper(1, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(textfieldCity, new GBCHelper(1, 2).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(calendarBirth, new GBCHelper(1, 3).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(comboGender, new GBCHelper(1, 4).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(textfieldMail, new GBCHelper(1, 5).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(comboRole, new GBCHelper(1, 6).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(textfieldUsername, new GBCHelper(1, 7).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(checkboxGeneratePassword, new GBCHelper(1, 8).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 10, 15));
		panelMain.add(textfieldPassword, new GBCHelper(1, 9).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(5, 15, 15, 15));

		
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
	
	private void setValidData(boolean bool) {
		this.validData = bool;
	}
	
	private void setModifiedData(boolean bool) {
		this.modifiedData = bool;
	}
	
	public void generatePassword() {	
		textfieldPassword.setText("Prova Password generata");
		//textfieldPassword.setText(controller.generatePassword());
	}
	
	public class ChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setModifiedData(true);			
		}	
	}
	
	public class GeneratePasswordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkboxGeneratePassword.isSelected()) {
				generatePassword();
				textfieldPassword.setEditable(false);
			} else {
				textfieldPassword.setText("");
				textfieldPassword.setEditable(true);
			}
		}		
	}
	
	public class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			utente = getUtente();
			dialogMain.setVisible(false);			
		}		
	}
	
	public class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			validData = false;
			dialogMain.setVisible(false);
			dialogMain.dispose();
		}		
	}
	
	public void setUserData(Utente user) {
		DatiUtente dati = user.getDatiUtente();
		Ruolo ruolo = user.getRuolo();
		Login login = user.getLogin();
		
		
		this.textfieldFirstname.setText(dati.getNome());
		this.textfieldLastname.setText(dati.getCognome());
		this.textfieldCity.setText(dati.getCittà());
		this.calendarBirth.setDateAsGregorianCalendar(dati.getNascita());
		this.comboGender.setSelectedItem(dati.getSesso());
		this.textfieldMail.setText(dati.getMail());
		this.comboRole.setSelectedIndex(ruolo.getId());
		this.textfieldUsername.setText(login.getUsername());
		this.textfieldPassword.setText(login.getPassword());
	}
	
	public Utente getUserFromData() {
		
		String firstname = textfieldFirstname.getText().toString();
		String lastname = textfieldLastname.getText().toString();
		String city = textfieldCity.getText().toString();
		GregorianCalendar birth = calendarBirth.getDateAsGregorianCalendar();
		String sex = comboGender.getSelectedItem().toString();
		String mail = textfieldMail.getText().toString();
		int ruolo = comboRole.getSelectedIndex();
		String username = textfieldUsername.getText().toString();
		String password = textfieldPassword.getText().toString();
		
		Utente utente = null;
		
		try {
			FactoryUtenti fu = FactoryUtenti.getInstance();
			utente = fu.creaUtente(new DatiUtente(firstname, lastname, mail, city, birth, sex), ruolo, new Login(username, password));
		} catch (DatiUtenteInconsistentiException | LoginInconsistenteException e) {
			e.printStackTrace();
		}	
		
		setValidData(true);
		return utente;
	}
	
	public Utente getUtente() {
		return utente;
	}

	public boolean hasValidData() {
		return validData;
	}
	
	public boolean hasModifiedData() {
		return modifiedData;
	}
}
