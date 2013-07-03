/**
 * 
 */
package gestione_Catalogo.control;

import gestione_Catalogo.entity.Data;
import gestione_Catalogo.entity.Offerta;
import gestione_Catalogo.entity.Tratta;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.TrattaInesistenteException;

import java.util.Set;

/**
 * @authors
 * Remo Sperlongano
 * Ivan Torre
 */
public class ControlloreGestioneOfferta extends Controllore {
	
	
	
	
	public ControlloreGestioneOfferta(){
		super();
	}
	
	
	//metodi in comune con ControlloreGestioneCatalogo
	public Set<String> mostraAmbientiInCatalogo() throws MappaException {
		return catalogo.getChiaviAmbienti();
	}
	
	public Set<String> mostraMezziInCatalogo(String ambiente) throws IDEsternoElementoException {
		return catalogo.getChiaviMezzi(ambiente);
	}

	public Set<String> mostraCittaDiPartenzaInCatalogo(String ambiente, String mezzo) throws IDEsternoElementoException {
		return catalogo.getChiaviCittaDiPartenza(ambiente, mezzo);		
	}

	public Set<String> mostraCittaDiArrivoInCatalogo(String ambiente, String mezzo, String partenza) throws IDEsternoElementoException {
		return catalogo.getChiaviCittaDiArrivo(ambiente, mezzo, partenza);
	}
	
	public Set<String> mostraViaInCatalogo(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoElementoException{
		return catalogo.getChiaviVia(ambiente, mezzo, partenza, arrivo);
	}
		
	public Set<Integer> mostraOffertePerLaTratta(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException{
		return catalogo.getChiaviOfferte(ambiente, mezzo, partenza, arrivo, via);
	}
	
	
	public void aggiungiOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, Integer[] data, int durata, int posti) throws TrattaInesistenteException, IDEsternoElementoException{
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
		Integer idTratta = tratta.getID();
		if (catalogo.verificaEsistenzaOfferta(idTratta, data)){
			
		}
		Offerta nuovaOfferta = new Offerta(idTratta, new Data(data[0], data[1], data[2], data[3], data[4]), durata, posti);
		catalogo.aggiungiOffertaAlCatalogo(nuovaOfferta, tratta);
	}


}

