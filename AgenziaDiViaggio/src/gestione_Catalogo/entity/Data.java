package gestione_Catalogo.entity;

import java.text.SimpleDateFormat;
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
	
	public Data(int giorno, int mese, int anno){
		data = new GregorianCalendar(giorno, mese-1,anno);
	}
	
	
	//metodi accessori
	public GregorianCalendar getData(){
		return data;
	}
	
	//metodi mutatori
	public void aggiornaData(){
		data = new GregorianCalendar();
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
