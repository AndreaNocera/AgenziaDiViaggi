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
import gestione_Catalogo.exception.PrenotazioneException;
import gestione_Catalogo.exception.TrattaInesistenteException;

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
		
	public Set<Data> mostraOffertePerLaTratta(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException{
		return catalogo.getChiaviOfferte(ambiente, mezzo, partenza, arrivo, via);
	}
	
	public String mostraListaOffertaInCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException, TrattaInesistenteException, OffertaInesistenteException{
		
		System.out.println("Sono in mostraListaOffertaCatalogo");
		String stringaOfferte = "";
		//prendo tutte le chiavi della mappaOfferta
		Set<Data> s = mostraOffertePerLaTratta(ambiente, mezzo, partenza, arrivo , via);
		
		//prendo l'id della tratta
		Integer idTratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via).getID();
		
		//itero il set di Date, mi faccio dare l'offerta per quella data
		Iterator<Data> it = s.iterator();
		System.out.println("Numero iterator " + s.size());
		Offerta o;
		int i = 0;
		while (it.hasNext()){
			i++;
			System.out.println("Sono nel while del mostraListaOffertaCatalogo iterazione " + i);
			Data data = it.next();
			o = catalogo.getOffertaByData(idTratta, data);
			//Inserisce gli elementi nella stringa da ritornare
			stringaOfferte += o.getData().stampaData() + "\t" + o.getDataArrivo().stampaData() + "\t" + o.getPosti() + "\n";
		}
		
		return stringaOfferte;
		
		
		
//		//prendo l'id della tratta
//		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
//		//Inserisco in una stringa tutte le offerte per una tratta e la ritorno
//		return catalogo.getListaOffertePerLaTratta(tratta.getID());
	}
	
	
	public void aggiungiOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, Integer[] data, int durata, int posti) throws TrattaInesistenteException, IDEsternoElementoException, OffertaException{
		
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
		Integer idTratta = tratta.getID();
		
		if (catalogo.verificaEsistenzaOfferta(idTratta, data)){
			throw new OffertaException("Offerta gia' esistente");
		}
		
		Offerta nuovaOfferta = new Offerta(idTratta, new Data(data[0], data[1], data[2], data[3], data[4]), durata, posti);
		catalogo.aggiungiOffertaAlCatalogo(nuovaOfferta, tratta);
	}
	
	public void rimuoviOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, Data dataPartenza) throws TrattaInesistenteException, PrenotazioneException, OffertaInesistenteException, IDEsternoElementoException{
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
		Integer idTratta = tratta.getID();
		
		if (catalogo.verificaEsistenzaPrenotazioni()){
			throw new PrenotazioneException("Ci sono prenotazioni attive! L'offerta non puo' essere rimosso.");
		}
		
		Offerta offerta = catalogo.getOffertaByData(idTratta, dataPartenza);
		catalogo.rimuoviOffertaDalCatalogo(offerta, tratta);
	}


}

