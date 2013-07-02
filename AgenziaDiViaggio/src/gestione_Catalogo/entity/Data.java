package gestione_Catalogo.entity;

import java.sql.Timestamp;
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
	
	public Data(int anno, int mese, int giorno){
		super(anno, mese-1,giorno);
	}
	
	public Data(int anno, int mese, int giorno, int ora, int minuti){
		super(anno, mese-1,giorno, ora, minuti);
	}
	
	public Data(int anno, int mese, int giorno, int ora, int minuti, int secondi){
		super(anno, mese-1,giorno, ora, minuti, secondi);
	}
		
	
	//metodi accessori
	public Timestamp getDataForDB(){
		return new Timestamp(super.getTimeInMillis());
	}
	
	
	
	//metodi mutatori
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
	
	
	//altri metodi
	public String stampaData(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataEora = sdf.format(super.getTime());
		
		return dataEora;
	}
	
	public String stampaDataAttuale(){ //Formato es. 10/01/2012 - 21:10:35
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataEora = sdf.format(super.getInstance().getTime());
		
		return dataEora;
	}
	
}
