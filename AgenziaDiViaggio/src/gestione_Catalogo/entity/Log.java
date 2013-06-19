/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */


package gestione_Catalogo.entity;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import gestione_Catalogo.exception.FileInesistenteException;




/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Sonia
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Log implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//attributi d'istanza
	private Orologio data;
	private String log;
	
	//costruttore
	public Log(){
		data = new Orologio();
		log = "";
	}
	

	//Metodi
	public void aggiornaLogAggiungiViaggio(String ambiente, String mezzoTrasporto, String stazionePartenza, String stazioneArrivo, String info){
		log = "[" + data.stampaDataAttuale() + "] AGGIUNTO Viaggio Via " + ambiente + ":\n" 									
				+ mezzoTrasporto + "  ->  " + stazionePartenza + " : " + stazioneArrivo + "  ->  " + info + "\n";
		System.out.println(log);
		salvaLog(log); 
	}
	
	public void aggiornaLogRimuoviViaggio(String ambiente, String mezzoTrasporto, String stazionePartenza, String stazioneArrivo, String info){
		log = "[" + data.stampaDataAttuale() + "] RIMOSSO Viaggio Via " + ambiente + ":\n" 
				+ mezzoTrasporto + "  ->  " + stazionePartenza + " : " + stazioneArrivo + "  ->  " + info + "\n";
		System.out.println(log);
		salvaLog(log);
	}
	

	
	public boolean creaNuovoLog(){ //Rinomino il file log.txt e ne creo un altro.
		
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		File file1 = new File(path);
		if(!file1.exists()){
			System.out.println("Il File: "+path+ " non esiste.");
			return false;
		}
		
		//Rinomino file
		boolean loop = true;
		int n=1;
		while(loop){
			String Dir2 = "Save";
			new File(Dir2).mkdir();
			String path2 = Dir+"/log("+n+").txt";
			File file2 = new File(path2);
			
			if(!file2.exists()){ //Se il file2 non esiste, rinomina file1 in file2
				if(!file1.renameTo(file2)) System.out.println("Impossibile rinominare il file");
				break;
			}
			n++;
		}
		
		//creo il file "log.txt"
		FileWriter fw;
		try {
			fw = new FileWriter(file1,true);
			fw.write("");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
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