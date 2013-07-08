package gestione_Catalogo.control;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */

import java.util.Iterator;
import java.util.Set;

import gestione_Catalogo.entity.Catalogo;
import gestione_Catalogo.entity.Data;
import gestione_Catalogo.entity.Log;
import gestione_Catalogo.entity.Offerta;

import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaInesistenteException;
import gestione_Catalogo.exception.OfferteNonPresentiException;
import gestione_Catalogo.exception.TrattaInesistenteException;


public abstract class Controllore {

	//attributi di classe
	protected static Catalogo catalogo;
	protected Log log;
	
	//costruttore
	public Controllore() {
		
		catalogo = Catalogo.getIstanza();
		log = new Log();
		
	}
	
	
	
	//metodi in comune
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
		
	public Set<Data> mostraOffertePerLaTratta(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException, OfferteNonPresentiException, OffertaInesistenteException{
		return catalogo.getChiaviOfferte(ambiente, mezzo, partenza, arrivo, via);
	}
	
	
	public String mostraListaOffertaInCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException, TrattaInesistenteException, OfferteNonPresentiException, OffertaInesistenteException{
		
		
		//NUOVA IMPLEMENTAZIONE, PRENDE LE OFFERTE DALLA MAPPA :)
		
		String stringaOfferte = "";
		
		//prendo tutte le chiavi dalla mappa
		Set<Data> s = catalogo.getChiaviOfferte(ambiente, mezzo, partenza, arrivo, via);
		Iterator<Data> it = s.iterator();
		
		while (it.hasNext()){
			
			Offerta o = catalogo.getOffertaFromMappa(ambiente, mezzo, partenza, arrivo, via, it.next());
			
			//Inserisce gli elementi nella stringa da ritornare
			stringaOfferte += o.getData().stampaData() + "\t" + o.getDataArrivo().stampaData() + "\t" + o.getPosti() + "\n";

		}
		
		
		return stringaOfferte;
		
		
		/*   	VECCHIA IMPLEMENTAZIONE, PRENDE LE OFFERTE DALL'ARRAYLIST
		String stringaOfferte = "";
		//prendo tutte le chiavi della mappaOfferta
		Set<Data> s = catalogo.getChiaviOfferte(ambiente, mezzo, partenza, arrivo , via);
		
		//prendo l'id della tratta
		Integer idTratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via).getID();
		
		//itero il set di Date, mi faccio dare l'offerta per quella data
		Iterator<Data> it = s.iterator();
		
		while (it.hasNext()){
			Data data = it.next();
			Offerta o = catalogo.getOffertaByData(idTratta, data);
			//Inserisce gli elementi nella stringa da ritornare
			stringaOfferte += o.getData().stampaData() + "\t" + o.getDataArrivo().stampaData() + "\t" + o.getPosti() + "\n";
		}
		
		return stringaOfferte;
		
		
		
//		//prendo l'id della tratta
//		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
//		//Inserisco in una stringa tutte le offerte per una tratta e la ritorno
//		return catalogo.getListaOffertePerLaTratta(tratta.getID()); */
	}
	

	
}
