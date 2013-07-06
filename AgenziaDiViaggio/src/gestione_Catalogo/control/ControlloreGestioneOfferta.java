/**
 * 
 */
package gestione_Catalogo.control;

import gestione_Catalogo.entity.Data;
import gestione_Catalogo.entity.Offerta;
import gestione_Catalogo.entity.Tratta;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaException;
import gestione_Catalogo.exception.OffertaInesistenteException;
import gestione_Catalogo.exception.OfferteNonPresentiException;
import gestione_Catalogo.exception.PrenotazioneException;
import gestione_Catalogo.exception.TrattaInesistenteException;

import java.text.ParseException;
import java.util.Iterator;
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
		
	public Set<Data> mostraOffertePerLaTratta(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException, OfferteNonPresentiException, OffertaInesistenteException{
		return catalogo.getChiaviOfferte(ambiente, mezzo, partenza, arrivo, via);
	}
	
	public String mostraListaOffertaInCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException, TrattaInesistenteException, OfferteNonPresentiException, OffertaInesistenteException{
		
		
		//NUOVA IMPLEMENTAZIONE, PRENDE LE OFFERTE DALLA MAPPA
		
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
	
	
	public void aggiungiOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, Integer[] data, int durata, int posti) throws TrattaInesistenteException, IDEsternoElementoException, OffertaException{
		
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
		Integer idTratta = tratta.getID();
		
		if (catalogo.verificaEsistenzaOfferta(idTratta, data)){
			throw new OffertaException("Offerta gia' esistente per il viaggio!");
		}
		Data dataPartenza = new Data(data[0], data[1], data[2], data[3], data[4]);
		Offerta nuovaOfferta = new Offerta(idTratta, dataPartenza, durata, posti);
		catalogo.aggiungiOffertaAlCatalogo(nuovaOfferta, tratta);
		log.aggiornaLogAggiungiOfferta(ambiente, mezzo, partenza, arrivo , via, dataPartenza, durata, posti);
	}
	
	public void rimuoviOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, String dataPartenza) throws TrattaInesistenteException, PrenotazioneException, OffertaInesistenteException, IDEsternoElementoException, ParseException{
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
		Integer idTratta = tratta.getID();
		
		if (catalogo.verificaEsistenzaPrenotazioni()){
			throw new PrenotazioneException("Ci sono prenotazioni attive! L'offerta non puo' essere rimossa.");
		}
		
		Data dataOfferta = Data.parseTimestamp(dataPartenza);
		Offerta offerta = catalogo.getOffertaByData(idTratta, dataOfferta);
		
		catalogo.rimuoviOffertaDalCatalogo(offerta, tratta);
		log.aggiornaLogRimuoviOfferta(ambiente, mezzo, partenza, arrivo, via, dataPartenza);
	}


}

