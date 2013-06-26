/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Orologio implements Serializable {


	private static final long serialVersionUID = 1L;
	
	//attributi d'istanza
	private GregorianCalendar data;
	
	//costruttore
	public Orologio(){
		data = new GregorianCalendar();	
		
	}
	
	public Orologio(int giorno, int mese, int anno){
		data = new GregorianCalendar(giorno, mese-1,anno);
	}
	
	public Orologio(Date date){
		data.setTime(date);
	}
	
	//metodi accessori
	public GregorianCalendar getData(){
		return data;
	}
	
	public java.sql.Date getDataPerDB(){
		

		java.util.Date utilStartDate = data.getTime();
		java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		return sqlStartDate;

	}
	
	//metodi mutatori
	public void aggiornaData(){
		data = new GregorianCalendar();
	}
	
	//altri metodi
	public String stampaDataAttuale() //Formato es. 10/01/2012 - 21:10:35
	{
		aggiornaData();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataEora = sdf.format(data.getTime());
		
		return dataEora;
		
		
	}
	
	
	

}
