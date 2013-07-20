package utils.swing;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;

public class ExtendedDatePicker extends DatePicker {

	private static final long serialVersionUID = 1L;

	private static final int NUM_HOURS = 24;
	private static final int NUM_MINUTES = 60;
	private static final int NUM_SECONDS = 60;


	private static final Integer[] HOURS_CHOICES = new Integer[NUM_HOURS];
	private static final Integer[] MINUTE_CHOICES = new Integer[NUM_MINUTES];
	private static final Integer[] SEDOND_CHOICES = new Integer[NUM_SECONDS];

	private JComboBox<Integer> comboHour;
	private JComboBox<Integer> comboMinute;
	private JComboBox<Integer> comboSecond;

	public ExtendedDatePicker() {
		super();
		buildPanel();
	}

	public ExtendedDatePicker(GregorianCalendar date) {
		super();
		buildPanel();
		setDateAsGregorianCalendar(date);
	}
	
	protected void buildPanel() {
		buildComboChoices();
		comboHour = new JComboBox<Integer>(HOURS_CHOICES);
		comboMinute = new JComboBox<Integer>(MINUTE_CHOICES);
		comboSecond = new JComboBox<Integer>(SEDOND_CHOICES);
		
		this.add(comboHour);
		this.add(comboMinute);
		this.add(comboSecond);		

	}
	
	public void addActionListener(ActionListener listener) {
		super.addActionListener(listener);
		this.comboHour.addActionListener(listener);
		this.comboMinute.addActionListener(listener);
		this.comboSecond.addActionListener(listener);
	}

	public GregorianCalendar getDateAsGregorianCalendar() {
		GregorianCalendar gc=super.getDateAsGregorianCalendar();
		
		int hour = comboHour.getSelectedIndex() + 1;
		int minute = comboMinute.getSelectedIndex() + 1;
		int second = comboSecond.getSelectedIndex();
		
		gc.add(Calendar.HOUR_OF_DAY,hour);
		gc.add(Calendar.MINUTE,minute);
		gc.add(Calendar.SECOND,second);
		
		return gc;
	}
	
	public void setDateAsGregorianCalendar(GregorianCalendar date) {
		super.setDateAsGregorianCalendar(date);
		comboHour.setSelectedItem(date.get(Calendar.HOUR_OF_DAY));
		comboMinute.setSelectedItem(date.get(Calendar.MINUTE));
		comboSecond.setSelectedItem(date.get(Calendar.SECOND));
	}
	
	protected void buildComboChoices() {
		buildHourChoices();
		buildMinuteChoices();
		buildSecondChoices();
	}
	
	private void buildHourChoices() {
		for (int i = 0; i < NUM_HOURS; i ++) {
			HOURS_CHOICES[i] = i;
		}
	}
	
	private void buildMinuteChoices() {
		for (int i = 0; i < NUM_MINUTES; i ++) {
			MINUTE_CHOICES[i] = i;
		}
	}
	
	private void buildSecondChoices() {
		for (int i = 0; i < NUM_SECONDS; i ++) {
			SEDOND_CHOICES[i] = i;
		}
	}
	
	
}
