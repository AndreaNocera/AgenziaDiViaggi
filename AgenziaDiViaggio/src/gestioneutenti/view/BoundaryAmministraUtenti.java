package gestioneutenti.view;

import gestioneutenti.controller.ControllerAmministraUtenti;
import gestioneutenti.model.Utente;
import gestioneutenti.view.utils.TabellaUtenti;
import gestioneutenti.view.utils.DialogUtente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import utils.GBCHelper;

public class BoundaryAmministraUtenti extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Amministrazione Utenti";	
	
	private JPanel panelMe;
	
	private JPanel panelTitle;
	private JPanel panelSearch;
	private JScrollPane panelTable;
	private JPanel panelButton;	
	
	private JLabel labelTitle;
	private JTextField textfieldSearch;
	private JButton buttonSearch;
	private JButton buttonNew;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private TabellaUtenti userTable;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
	
	public BoundaryAmministraUtenti() {
		this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
		this.panelMe = this;
		buildFrame();					
	}	
	
	public void newUser(Utente utente) {
		controllerAmministraUtenti.creaUtente(utente);
	}
	
	public void editUser(Utente utente) {		
		controllerAmministraUtenti.modificaUtente(utente);
	}
	
	public void deleteUser(String username) {
		controllerAmministraUtenti.rimuoviUtente(username);
	}
	
	public void searchUser(String query) {
		controllerAmministraUtenti.cercaUtente(query);
	}
	
	public class NewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DialogUtente dialog = new DialogUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			dialog.setVisible(true);
			if (dialog.hasValidData()) {
				newUser(dialog.getUtente());
			}
		}		
	}
	
	public class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utente user = userTable.getSelectedUser();
			DialogUtente dialog = new DialogUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe), user);
			dialog.setVisible(true);
			if (dialog.hasValidData()) {
				editUser(dialog.getUtente());
			}
		}		
	}
	
	public class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utente user = userTable.getSelectedUser();
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler rimuovere " + user.getLogin().getUsername() + "?", "Conferma Rimozione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				deleteUser(user.getLogin().getUsername());
			}
		}
	}
	
	public class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String query = textfieldSearch.getText().toString();
			searchUser(query);
		}
	}
	
	public class FrameClosingListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent event) {
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler uscire?", "Voyager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}		
	}
	
	private void buildFrame() {
		
		//Initialization
		this.setLayout(new GridBagLayout());
		
		
		//Panel Title
		panelTitle = new JPanel();		
		labelTitle = new JLabel(TITLE);
		labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.BOLD, 20));		
		panelTitle.add(labelTitle);
		
		
		//Panel Search
		panelSearch = new JPanel();		
		textfieldSearch = new JTextField("", 30); 
		buttonSearch = new JButton("Cerca");		
		panelSearch.add(textfieldSearch);
		panelSearch.add(buttonSearch);
		
		
		//Panel Table
		userTable = new TabellaUtenti(controllerAmministraUtenti.getUtenti());
		userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				buttonEdit.setEnabled(true);
				buttonDelete.setEnabled(true);
			}
			
		});
		panelTable = new JScrollPane(userTable);
		
		
		//Panel Button
		panelButton = new JPanel();
		panelButton.setLayout(new GridBagLayout());		
		buttonNew = new JButton("Nuovo");
		buttonEdit = new JButton("Modifica");
		buttonEdit.setEnabled(false);
		buttonDelete = new JButton("Rimuovi");	
		buttonDelete.setEnabled(false);
		buttonNew.addActionListener(new NewListener());
		buttonEdit.addActionListener(new EditListener());
		buttonDelete.addActionListener(new DeleteListener());
		buttonSearch.addActionListener(new SearchListener());	
		panelButton.add(buttonNew, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		panelButton.add(buttonEdit, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		panelButton.add(buttonDelete, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 0, 0));	
		
		
		//Frame Pack
		this.add(panelTitle, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 15, 15));
		this.add(panelSearch, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 10));
		this.add(panelTable, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));
		this.add(panelButton, new GBCHelper(1, 2).setWeight(0, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.NORTH).setInsets(10, 5, 0, 10));		
	}
	
}
