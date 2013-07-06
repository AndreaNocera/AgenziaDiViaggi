package gestione_Catalogo.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Data extends GregorianCalendar {

	private static final long serialVersionUID = 1L;
	

	//costruttori
	
	public Data(){
		super();
	}
	
	public Data(Date date){
		super.setTime(date);
	}
	
	public Data(int giorno, int mese, int anno){
		super(anno, mese-1,giorno);
	}
	
	public Data(int giorno, int mese, int anno, int ora, int minuti){
		super(anno, mese-1,giorno, ora, minuti);
	}
	
	public Data(int giorno, int mese, int anno, int ora, int minuti, int secondi){
		super(anno, mese-1,giorno, ora, minuti, secondi);
	}
		
	
	//metodi accessori
	public Timestamp getDataForDB(){
		return new Timestamp(super.getTimeInMillis());
	}
	
	public int getAnno(){
		return super.get(GregorianCalendar.YEAR);
	}
	
	public int getMese(){
		return super.get(GregorianCalendar.MONTH)+1;
	}
	
	public int getGiorno(){
		return super.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public int getOra(){
		return super.get(GregorianCalendar.HOUR_OF_DAY);
	}
	
	public int getMinuto(){
		return super.get(GregorianCalendar.MINUTE);
	}
		
	
/*	//metodi mutatori
	public void aggiungiMinuti(int minuti){
		super.add(GregorianCalendar.MINUTE, minuti);
	}
	
	public void aggiungiOre(int ore){
		super.add(GregorianCalendar.HOUR_OF_DAY, ore);
	}
	
	public void aggiungiOreMinuti(int ore, int minuti){
		super.add(GregorianCalendar.HOUR_OF_DAY, ore);
		super.add(GregorianCalendar.MINUTE, minuti);
	}
*/	
	
	//altri metodi
	public String stampaData(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		return sdf.format(super.getTime());
	}
	
	public static String stampaDataAttuale(){ //Formato es. 10/01/2012 - 21:10:35
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
		return sdf.format(getInstance().getTime());
	}
	
	
	public Data getNuovaData(int minuti){
		Data nuovaData = (Data) this.clone();
		nuovaData.add(GregorianCalendar.MINUTE, minuti);
		return nuovaData;
	}
	
	public Data getNuovaData(int ore, int minuti){
		Data nuovaData = (Data) this.clone();
		nuovaData.add(GregorianCalendar.HOUR_OF_DAY, ore);
		nuovaData.add(GregorianCalendar.MINUTE, minuti);
		return nuovaData;
	}
	
	
	public static Data parseTimestamp(String timestamp) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		Date d = sdf.parse(timestamp);
		Data data = new Data();
		data.setTime(d);
		return data;
	}
	

}
