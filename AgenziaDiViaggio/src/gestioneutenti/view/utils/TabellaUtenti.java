package gestioneutenti.view.utils;
import gestioneutenti.model.Utente;

import javax.swing.*;

public class TabellaUtenti extends JTable{
	
	private static final long serialVersionUID = 1L;
	private TabellaUtentiModel tableModel;

	public TabellaUtenti(Utente[] users) {
		super();
		tableModel = new TabellaUtentiModel(users);
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