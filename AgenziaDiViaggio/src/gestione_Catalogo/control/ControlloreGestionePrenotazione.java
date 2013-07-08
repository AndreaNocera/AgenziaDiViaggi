package gestione_Catalogo.control;

import gestione_Catalogo.entity.Data;
import gestione_Catalogo.entity.Offerta;
import gestione_Catalogo.entity.Tratta;
import gestione_Catalogo.exception.IDEsternoElementoException;
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
public class ControlloreGestionePrenotazione extends Controllore{
	
	//costruttore
	public ControlloreGestionePrenotazione(){
		super();
	}
	
	
	public void aggiungiOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, Integer[] data, int durata, int posti) throws TrattaInesistenteException, IDEsternoElementoException, OffertaException{
		
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
		Integer idTratta = tratta.getID();
		Data dataPartenza = new Data(data[0], data[1], data[2], data[3], data[4]);
		
	/*		VERIFICA ESISTENZA OFFERTE NELLA LISTA
		if (catalogo.verificaEsistenzaOfferta(idTratta, dataPartenza)){
			throw new OffertaException("Offerta gia' esistente per il viaggio!");
		}
	*/
		if (catalogo.verificaEsistenzaOfferta(ambiente,mezzo,partenza,arrivo,via,dataPartenza)){
			throw new OffertaException("Offerta gia' esistente per il viaggio!");
		}
		
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
