package gestioneutenti.model;

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
		user.setFirstname((String) this.getModel().getValueAt(modelRow, 0));
		user.setLastname((String) this.getModel().getValueAt(modelRow, 1));
		user.setCity((String) this.getModel().getValueAt(modelRow, 2));
		user.setBirth((String) this.getModel().getValueAt(modelRow, 3));
		user.setGender((String) this.getModel().getValueAt(modelRow, 4));
		user.setMail((String) this.getModel().getValueAt(modelRow, 5));
		user.setRole((String) this.getModel().getValueAt(modelRow, 6));
		user.setUsername((String) this.getModel().getValueAt(modelRow, 7));
		user.setPassword((String) this.getModel().getValueAt(modelRow, 8));
		return user;
	}
}