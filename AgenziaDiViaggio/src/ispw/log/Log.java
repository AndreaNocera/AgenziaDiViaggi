package ispw.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/*Classe con metodi statici per la gestione del Log*/
public class Log {
	private static final String nomeFileLog = "Log.txt";
	private static Log instance;
	
	private Log(){}
	
	public static Log getInstance(){
		if(instance == null)
			instance = new Log();
		return instance;
	}
	
	/* Scrive l'operazione compiuta da un utente sul sistema.
	 * Se il Log non esiste lo crea.
	 */
	public synchronized void ScriviLog(String Ruolo, String evento){
		try{
			FileWriter file = new FileWriter(nomeFileLog, true);		//apre in append
			PrintWriter outw = new PrintWriter(file);
			
			outw.print(" * ");
			outw.print(getHeader());
			outw.print(Ruolo);
			outw.print(" --> ");
			outw.print(evento);
			outw.println(";");
			
			outw.close();
			file.close();
		}catch(IOException ioe){
			System.err.println("Qualcosa e' andato storto nel salvataggio del Log!");
			ioe.printStackTrace();
		}
	}
	
	/*Restituisce il Log delle operazioni*/
	public synchronized String CaricaLog(){
		StringBuilder strbld = new StringBuilder();
		
		try {
			FileReader file = new FileReader(nomeFileLog);
			BufferedReader buffread = new BufferedReader(file);
			String temp;
			
			while((temp = buffread.readLine()) != null)
				strbld.append(temp);
			
			buffread.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File Log non trovato!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strbld.toString();
	}
	
	/*Restituisce il Log delle operazioni*/
	public synchronized List<String> CaricaLogLista(){
		List<String> listaLog = new ArrayList<String>();
		
		try {
			FileReader file = new FileReader(nomeFileLog);
			BufferedReader buffread = new BufferedReader(file);
			String temp;
			
			while((temp = buffread.readLine()) != null)
				listaLog.add(temp);
			
			buffread.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File Log non trovato!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Usa la reflection per capire se la lista è di tipo "ArrayList"
		 * e in caso ne riduce la dimensione al valore giusto
		 */
		if(listaLog.getClass() == ArrayList.class)
			((ArrayList<String>)listaLog).trimToSize();
		
		return listaLog;
	}
	
	/*Ottiene l'intestazione con la data e l'ora corrente.*/
	@SuppressWarnings("static-access")
	private static String getHeader(){
		Calendar calendario = new GregorianCalendar(TimeZone.getDefault());
		
		int giorno = calendario.get(calendario.DAY_OF_MONTH);
		int mese = calendario.get(calendario.MONTH);
		int anno = calendario.get(calendario.YEAR);
		
		int ora = calendario.get(calendario.HOUR_OF_DAY);		//formato 24 oe
		int minuti = calendario.get(calendario.MINUTE);
		int secondi = calendario.get(calendario.SECOND);
		
		StringBuilder header = new StringBuilder();
		
		header.append("[");
		header.append(giorno);
		header.append("/");
		header.append(mese);
		header.append("/");
		header.append(anno);
		header.append(" ");
		header.append(ora);
		header.append(":");
		header.append(minuti);
		header.append(":");
		header.append(secondi);
		header.append("] ");
		
		return header.toString();
	}
	
	/*Cancella completamente il file di Log.*/
	public static void CancellaLog(){
		File log = new File(nomeFileLog);
		
		if(!log.exists())
			return;
		
		log.delete();
	}
	
	public static void main(String[] args){
		Log log = Log.getInstance();
		log.ScriviLog("Aministratore", "Aggiunto utente");
		log.ScriviLog("Cliente", "Prenotato un viaggio");
		
		List<String> listaLog = log.CaricaLogLista();
		
		for(String temp : listaLog)
			System.out.println(temp);
	}
}
