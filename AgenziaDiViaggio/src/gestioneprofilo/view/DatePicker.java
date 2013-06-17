package gestioneprofilo.view;

import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;

public class DatePicker extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final int NUM_DAYS = 31;
	private static final int NUM_MONTHS = 12;
	private static final int FIRST_YEAR = 1900;
	private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	private static final int NUM_YEARS = CURRENT_YEAR - FIRST_YEAR;
	
	private static final String[] MONTH_CHOICES = new String[NUM_MONTHS + 1];
	private static final String[] DAY_CHOICES = new String[NUM_DAYS + 1];
	private static final String[] YEAR_CHOICES = new String[NUM_YEARS + 1];
	
	private static final String DAY_TITLE = "Giorno";
	private static final String MONTH_TITLE = "Mese";
	private static final String YEAR_TITLE = "Anno";
	private static final String SEPARATOR = " ";
	
	private JComboBox<String> comboDay;
	private JComboBox<String> comboMonth;
	private JComboBox<String> comboYear;
	
	public DatePicker() {
		super();
		buildPanel();
	}
	
	public DatePicker(String date) {
		super();
		buildPanel();
		this.setDateAsString(date);
	}
	
	public void addActionListener(ActionListener listener) {
		this.comboDay.addActionListener(listener);
		this.comboMonth.addActionListener(listener);
		this.comboYear.addActionListener(listener);
	}
	
	public String getDateAsString() {
		if (hasValidData()) {
			String day = comboDay.getSelectedItem().toString();
			String month = comboMonth.getSelectedItem().toString();
			String year = comboYear.getSelectedItem().toString();
			
			String date = day + SEPARATOR + month + SEPARATOR + year;
			
			return date;
		} else {
			return "";
		}		
	}
	
	public void setDateAsString(String date) {
		String[] separatedData = date.split(SEPARATOR);
		comboDay.setSelectedItem(separatedData[0]);
		comboMonth.setSelectedItem(separatedData[1]);
		comboYear.setSelectedItem(separatedData[2]);		
	}
	
	public int[] getDateAsInt() {
		if (hasValidData()) {
			int[] date = new int[3];
			date[0] = Integer.valueOf(comboDay.getSelectedItem().toString());
			date[1] = Integer.valueOf(comboMonth.getSelectedItem().toString());
			date[2] = Integer.valueOf(comboYear.getSelectedItem().toString());
			
			return date;
		} else {
			return null;
		}		
	}
	
	public void setDateAsInt(int day, int month, int year) {
		comboDay.setSelectedItem(String.valueOf(day));
		comboMonth.setSelectedItem(String.valueOf(month));
		comboYear.setSelectedItem(String.valueOf(year));
	}
		
	public boolean hasValidData() {
		String day = comboDay.getSelectedItem().toString();
		String month = comboMonth.getSelectedItem().toString();
		String year = comboYear.getSelectedItem().toString();
		
		if (day.equals(DAY_TITLE) || month.equals(MONTH_TITLE) || year.equals(YEAR_TITLE)) {
			return false;
		} else {
			return true;
		}
	}
	
 	public void buildPanel() {
		buildComboChoices();
		comboDay = new JComboBox<String>(DAY_CHOICES);
		comboMonth = new JComboBox<String>(MONTH_CHOICES);
		comboYear = new JComboBox<String>(YEAR_CHOICES);
		
		comboDay.setSelectedItem(DAY_TITLE);
		comboMonth.setSelectedItem(MONTH_TITLE);
		comboYear.setSelectedItem(YEAR_TITLE);
		
		this.add(comboDay);
		this.add(comboMonth);
		this.add(comboYear);		
	}	
	
	public void buildComboChoices() {
		buildDayChoices();
		buildMonthChoices();
		buildYearChoices();
	}
	
	public void buildDayChoices() {
		DAY_CHOICES[0] = DAY_TITLE;
		
		for (int i = 1; i <= NUM_DAYS; i ++) {
			DAY_CHOICES[i] = String.valueOf(i);
		}
	}
	
	public void buildMonthChoices() {
		MONTH_CHOICES[0] = MONTH_TITLE;
		
		for (int i = 1; i <= NUM_MONTHS; i ++) {
			MONTH_CHOICES[i] = String.valueOf(i);
		}
	}
	
	public void buildYearChoices() {
		YEAR_CHOICES[0] = YEAR_TITLE;
		
		for (int i = 1; i <= NUM_YEARS; i ++) {
			YEAR_CHOICES[i] = String.valueOf(CURRENT_YEAR - i);
		}
	}

}
