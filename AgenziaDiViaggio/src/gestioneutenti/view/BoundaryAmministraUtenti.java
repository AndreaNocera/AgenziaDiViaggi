package gestioneutenti.view;

/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.view
 * 
 * @name BoundaryAmministraUtenti.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

import gestioneutenti.controller.ControllerAmministraUtenti;
import gestioneutenti.model.Utente;
import gestioneutenti.view.utils.TabellaUtenti;
import gestioneutenti.view.utils.DialogUtente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utils.swing.GBCHelper;

public class BoundaryAmministraUtenti extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TITOLO = "Amministrazione Utenti";	
	
	private JPanel panelMe;
	
	private JPanel panelTitolo;
	private JPanel panelCerca;
	private JScrollPane panelTabella;
	private JPanel panelBottoni;	
	
	private JLabel labelTitolo;
	private JTextField textfieldCerca;
	private JButton buttonCerca;
	private JButton buttonNuovo;
	private JButton buttonModifica;
	private JButton buttonRimuovi;
	private TabellaUtenti tabellaUtenti;
	
	private ControllerAmministraUtenti controllerAmministraUtenti;
	
	public BoundaryAmministraUtenti() {
		this.controllerAmministraUtenti = ControllerAmministraUtenti.getInstance();
		this.panelMe = this;
		buildFrame();					
	}	
	
	private void buildFrame() {
		
		//Initialization
		this.setLayout(new GridBagLayout());
				
		//Panel Title
		this.panelTitolo = new JPanel();		
		this.labelTitolo = new JLabel(TITOLO);
		this.labelTitolo.setFont(new Font(this.labelTitolo.getFont().getName(), Font.BOLD, 20));		
		this.panelTitolo.add(labelTitolo);
				
		//Panel Search
		this.panelCerca = new JPanel();		
		this.textfieldCerca = new JTextField("", 30); 
		this.buttonCerca = new JButton("Cerca");		
		this.panelCerca.add(this.textfieldCerca);
		this.panelCerca.add(this.buttonCerca);
				
		//Panel Table
		this.tabellaUtenti = new TabellaUtenti(this.controllerAmministraUtenti.getUtenti());
		this.tabellaUtenti.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				buttonModifica.setEnabled(true);
				buttonRimuovi.setEnabled(true);
			}
			
		});
		
		this.panelTabella = new JScrollPane(tabellaUtenti);
				
		//Panel Button
		this.panelBottoni = new JPanel();
		this.panelBottoni.setLayout(new GridBagLayout());		
		this.buttonNuovo = new JButton("Nuovo");
		this.buttonModifica = new JButton("Modifica");
		this.buttonModifica.setEnabled(false);
		this.buttonRimuovi = new JButton("Rimuovi");	
		this.buttonRimuovi.setEnabled(false);
		this.buttonNuovo.addActionListener(new NewListener());
		this.buttonModifica.addActionListener(new EditListener());
		this.buttonRimuovi.addActionListener(new DeleteListener());
		this.buttonCerca.addActionListener(new SearchListener());	
		this.panelBottoni.add(this.buttonNuovo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		this.panelBottoni.add(this.buttonModifica, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 10, 0));
		this.panelBottoni.add(this.buttonRimuovi, new GBCHelper(0, 2).setWeight(100, 0).setFill(GridBagConstraints.HORIZONTAL).setInsets(0, 0, 0, 0));	
				
		//Frame Pack
		this.add(this.panelTitolo, new GBCHelper(0, 0).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.WEST).setInsets(15, 15, 15, 15));
		this.add(this.panelCerca, new GBCHelper(0, 1).setWeight(100, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 10));
		this.add(this.panelTabella, new GBCHelper(0, 2).setWeight(100, 100).setFill(GridBagConstraints.BOTH).setInsets(10, 10, 10, 10));
		this.add(this.panelBottoni, new GBCHelper(1, 2).setWeight(0, 100).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.NORTH).setInsets(10, 5, 0, 10));		
	}
	
	private class NewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DialogUtente dialog = new DialogUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe));
			dialog.setVisible(true);
			if (dialog.hasValidData()) {
				newUser(dialog.getUtente());
			}
		}		
	}
	
	private class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utente user = tabellaUtenti.getSelectedUser();
			DialogUtente dialog = new DialogUtente((JFrame)SwingUtilities.getWindowAncestor(panelMe), user);
			dialog.setVisible(true);
			if (dialog.hasValidData()) {
				editUser(dialog.getUtente());
			}
		}		
	}
	
	private class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utente user = tabellaUtenti.getSelectedUser();
			int choice = JOptionPane.showConfirmDialog(getParent(), "Sei sicuro di voler rimuovere " + user.getLogin().getUsername() + "?", "Conferma Rimozione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				deleteUser(user.getLogin().getUsername());
			}
		}
	}
	
	private class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String query = textfieldCerca.getText().toString();
			searchUser(query);
		}
	}
	
	private void newUser(Utente utente) {
		this.controllerAmministraUtenti.creaUtente(utente);
	}
	
	private void editUser(Utente utente) {		
		this.controllerAmministraUtenti.modificaUtente(utente);
	}
	
	private void deleteUser(String username) {
		this.controllerAmministraUtenti.rimuoviUtente(username);
	}
	
	private void searchUser(String query) {
		this.controllerAmministraUtenti.cercaUtente(query);
	}		
	
}
