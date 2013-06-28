package gestione_Catalogo.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;

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
	
	
	public Set<String> getChiaviAmbienti() throws MappaException {
		
		Set<String> ambienti = mappaCatalogo.keySet();
		if (ambienti.isEmpty())
			throw new MappaException("Non sono presenti Viaggi nel Catalogo.");
		else
			return ambienti;
		
	}
	
	public Set<String> getChiaviMezzi(String ambiente) throws IDEsternoElementoException {
		
		if (mappaCatalogo.containsKey(ambiente))
			return mappaCatalogo.get(ambiente).listaChiaviElementi();
		else
			throw new IDEsternoElementoException("Ambiente "+ambiente+" non presente in catalogo");
		
	}
	
	public Set<String> getChiaviCittaDiPartenza(String ambiente, String mezzo) throws IDEsternoElementoException {
		
		ElementoIntermedio elementoAmbiente = mappaCatalogo.get(ambiente);
		if (elementoAmbiente.esistenzaElemento(mezzo))
			return elementoAmbiente.getElemento(mezzo).listaChiaviElementi(); 
		else
			throw new IDEsternoElementoException("Mezzo "+mezzo+" non presente in catalogo");
		
	}
	
	
	public Set<String> getChiaviCittaDiArrivo(String ambiente, String mezzo, String partenza) throws IDEsternoElementoException {
		
		ElementoIntermedio elementoMezzo = mappaCatalogo.get(ambiente).getElemento(mezzo);
		if (elementoMezzo.esistenzaElemento(partenza))
			return elementoMezzo.getElemento(partenza).listaChiaviElementi();
		else 
			throw new IDEsternoElementoException("Citta' di partenza "+partenza+" non presente in catalogo");
	
	}
	
	public Set<String> getChiaviVia(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoElementoException{
		
		ElementoIntermedio elementoPartenza = mappaCatalogo.get(ambiente).getElemento(mezzo).getElemento(partenza);
		if (elementoPartenza.esistenzaElemento(arrivo)){
			return  elementoPartenza.getElemento(arrivo).listaChiaviElementi();
		} else throw new IDEsternoElementoException("Citta' di arrivo "+arrivo+" non presente in catalogo");
		
	}
}

