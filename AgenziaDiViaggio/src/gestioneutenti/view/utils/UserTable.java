package gestioneutenti.view.utils;
import gestioneutenti.model.Utente;

import javax.swing.*;

public class UserTable extends JTable{
	
	private static final long serialVersionUID = 1L;
	private UserTableModel tableModel;

	public UserTable(Utente[] users) {
		super();
		tableModel = new UserTableModel(users);
		super.setModel(tableModel);
		this.enableInputMethods(false);
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setAutoCreateRowSorter(true);
	}	
	
	public Utente getSelectedUser() {
		int viewRow = this.getSelectedRow();
		int modelRow = this.convertRowIndexToModel(viewRow);
		
		return this.tableModel.getUserList()[modelRow];
	}
}