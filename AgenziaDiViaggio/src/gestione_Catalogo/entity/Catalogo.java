/**
 * 
 */
package gestione_Catalogo.entity;

import java.util.ArrayList;
import java.util.Set;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Catalogo {
	
	//attributi di classe
	private static Catalogo istanza;
	
	//attributi di istanza
	private ArrayList<Tratta> listaTratte;
	private MappaCatalogo mappaCatalogo;
	
	//costruttore
	private Catalogo() {
		mappaCatalogo = new MappaCatalogo(); //istanziato il catalogo, creo subito una mappa per gli ambienti
	}
	
	
	// metodi
	public static Catalogo getIstanza(){
		if (istanza == null){
			istanza = new Catalogo();
		}
		return istanza;
	}
	
	
	public boolean verificaEsistenzaViaggio(Tratta tratta){
		return false;
		
	}
	
	public boolean verificaEsistenzaOfferte(Tratta tratta){
		return false;
		
	}
	
	
	public void aggiungiViaggioAlCatalogo(Tratta tratta){
		
	}
	
	public void rimuoviViaggioDalCatalogo(Tratta tratta){
		
	}
	
	
	public Set<String> getChiaviAmbienti(){
		return mappaCatalogo.keySet();
	}
	
	public Set<String> getChiaviMezzi(String ambiente){
		return mappaCatalogo.get(ambiente).listaChiaviElementi();
	}
}

