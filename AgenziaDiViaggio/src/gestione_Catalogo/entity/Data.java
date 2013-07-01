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
public class Data {
	
	//attributi d'istanza
	private GregorianCalendar data;
	
	//costruttore
	public Data(){
		data = new GregorianCalendar();		
	}
	
	public Data(Date date){
		data.setTime(date);
	}
	
	public Data(int anno, int mese, int giorno){
		data = new GregorianCalendar(anno, mese-1,giorno);
	}
	
	public Data(int anno, int mese, int giorno, int ora, int minuti){
		data = new GregorianCalendar(anno, mese-1,giorno, ora, minuti);
	}
	
	
	//metodi accessori
	public GregorianCalendar getDataGregorian(){
		return data;
	}
	
	public Timestamp getDataForDB(){
		return new Timestamp(data.getTimeInMillis());
	}
	
	//metodi mutatori
	public void aggiornaData(){
		data = new GregorianCalendar();
	}
	
	public void aggiungiMinuti(int minuti){
		data.add(GregorianCalendar.MINUTE, minuti);
	}
	
	public void aggiungiOre(int ore){
		data.add(GregorianCalendar.HOUR_OF_DAY, ore);
	}
	
	public void aggiungiOreMinuti(int ore, int minuti){
		data.add(GregorianCalendar.HOUR_OF_DAY, ore);
		data.add(GregorianCalendar.HOUR_OF_DAY, minuti);
	}
	
	//altri metodi
	public String stampaData(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataEora = sdf.format(data.getTime());
		
		return dataEora;
	}
	
	public String stampaDataAttuale(){ //Formato es. 10/01/2012 - 21:10:35
		aggiornaData();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataEora = sdf.format(data.getTime());
		
		return dataEora;
	}
	
}
