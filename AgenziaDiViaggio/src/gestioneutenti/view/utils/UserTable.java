package gestioneutenti.view.utils;

import gestioneutenti.model.Utente;

import javax.swing.*;

public class UserTable extends JTable{
	
	private static final long serialVersionUID = 1L;

	public UserTable(Utente[] users) {
		super(new UserTableModel(users));
		this.enableInputMethods(false);
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setAutoCreateRowSorter(true);
	}	
	
	public Utente getSelectedUser() {
		Utente user = new Utente();
		int viewRow = this.getSelectedRow();
		int modelRow = this.convertRowIndexToModel(viewRow);
		user.setNome((String) this.getModel().getValueAt(modelRow, 0));
		user.setCognome((String) this.getModel().getValueAt(modelRow, 1));
		user.setCittà((String) this.getModel().getValueAt(modelRow, 2));
		user.setNascita((String) this.getModel().getValueAt(modelRow, 3));
		user.setSesso((String) this.getModel().getValueAt(modelRow, 4));
		user.setMail((String) this.getModel().getValueAt(modelRow, 5));
		user.setRuolo((String) this.getModel().getValueAt(modelRow, 6));
		user.getLogin().setUsername((String) this.getModel().getValueAt(modelRow, 7));
		user.getLogin().setPassword((String) this.getModel().getValueAt(modelRow, 8));
		return user;
	}
}