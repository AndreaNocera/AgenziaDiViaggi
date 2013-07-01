package gestioneutenti.view.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import gestioneutenti.model.Utente;

import javax.swing.table.AbstractTableModel;

public class TabellaUtentiModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMNS = {"Nome", "Cognome", "Città", "Data di Nascita", "Sesso", "Mail", "Ruolo", "Username", "Password"};
	private Utente[] userList;	
	
	public TabellaUtentiModel(Utente[] userList) {
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
			return currUser.getDatiUtente().getNome();
		case 1:
			return currUser.getDatiUtente().getCognome();
		case 2:
			return currUser.getDatiUtente().getCitta();
		case 3:
			GregorianCalendar calendar = currUser.getDatiUtente().getNascita();
			String calendarString = calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.YEAR);
			return calendarString;
		case 4:
			return currUser.getDatiUtente().getSesso();
		case 5:
			return currUser.getDatiUtente().getMail();
		case 6:
			return currUser.getRuolo().asString();
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
	
	public Utente[] getUserList() {
		return this.userList;
	}
}
