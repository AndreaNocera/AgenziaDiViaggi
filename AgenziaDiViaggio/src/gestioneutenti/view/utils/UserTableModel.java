package gestioneutenti.view.utils;

import gestioneutenti.model.Utente;

import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMNS = {"Nome", "Cognome", "Città", "Data di Nascita", "Sesso", "Mail", "Ruolo", "Username", "Password"};
	private Utente[] userList;	
	
	public UserTableModel(Utente[] userList) {
		this.userList = userList;
	}
	
	public int getRowCount() {
		return this.userList.length;
	}
	
	public int getColumnCount() {
		return COLUMNS.length;
	}
	
	public Object getValueAt(int row, int column) {
		Utente currUser = this.userList[row];
		
		switch(column) {
		case 0:
			return currUser.getNome();
		case 1:
			return currUser.getCognome();
		case 2:
			return currUser.getCittà();
		case 3:
			return currUser.getNascita();
		case 4:
			return currUser.getSesso();
		case 5:
			return currUser.getMail();
		case 6:
			return currUser.getRuolo();
		case 7:
			return currUser.getLogin().getUsername();
		case 8:
			return currUser.getLogin().getPassword();
		default:
			return "";
		}
	}
	
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
}
