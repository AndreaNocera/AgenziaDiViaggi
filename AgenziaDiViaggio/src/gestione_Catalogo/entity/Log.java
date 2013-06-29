package gestione_Catalogo.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import gestione_Catalogo.exception.FileInesistenteException;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Log {
	
	//attributi di classe
	private static Log istanza;
	
	//attributi d'istanza
	private Data data;
	private String log;
	
	
	//costruttore
	private Log() {
		data = new Data();
		log = "";
	}
	
	// metodi
	public static Log getIstanza(){
		if (istanza == null){
			istanza = new Log();
		}
		return istanza;
	}

	
	//Metodi
	public void aggiornaLogAggiungiViaggio(Tratta tratta){
		String ambiente = tratta.getAmbiente().getIDEsternoElemento().toString();
		String mezzo = tratta.getMezzo().getIDEsternoElemento().toString();
		String cittaPartenza = tratta.getPartenza().getIDEsternoElemento().toString();
		String cittaArrivo = tratta.getArrivo().getIDEsternoElemento().toString();
		String via = tratta.getVia().getIDEsternoElemento().toString();
		
		log = "[" + data.stampaDataAttuale() + "] AGGIUNTO Viaggio Via " + ambiente + ":\n" 									
				+ mezzo + "  ->  " + cittaPartenza + " : " + cittaArrivo + "  ->  " + via + "\n";
		System.out.println(log);
		salvaLog(log); 
	}
	
	//Metodi
	public void aggiornaLogRimuoviViaggio(Tratta tratta){
		String ambiente = tratta.getAmbiente().getIDEsternoElemento().toString();
		String mezzo = tratta.getMezzo().getIDEsternoElemento().toString();
		String cittaPartenza = tratta.getPartenza().getIDEsternoElemento().toString();
		String cittaArrivo = tratta.getArrivo().getIDEsternoElemento().toString();
		String via = tratta.getVia().getIDEsternoElemento().toString();
		
		log = "[" + data.stampaDataAttuale() + "] RIMOSSO Viaggio Via " + ambiente + ":\n" 									
				+ mezzo + "  ->  " + cittaPartenza + " : " + cittaArrivo + "  ->  " + via + "\n";
		System.out.println(log);
		salvaLog(log); 
	}

	public String caricaLog() throws FileInesistenteException {
		String contenutoFile = "";
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		FileReader fr;
		
		File file = new File(path);
		try {			
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); 
			String s;
			while((s = br.readLine()) != null) {
				contenutoFile = contenutoFile + s+"\n";
			} 
			fr.close();
		} catch (FileNotFoundException e) {
			throw new FileInesistenteException("Attenzione! File Inesistente!");
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return contenutoFile;
	}
	

	private void salvaLog(String input) { //salva su file la stringa di input
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		FileWriter fw;
		try {
			File file = new File(path);
			fw = new FileWriter(file,true);
			fw.write(input+"\r\n");
			fw.flush();
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
