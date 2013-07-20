package voyager.nove.control;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Set;

import voyager.nove.exception.IDEsternoElementoException;
import voyager.nove.exception.MappaException;
import voyager.nove.exception.OffertaException;
import voyager.nove.exception.OffertaInesistenteException;
import voyager.nove.exception.OfferteNonPresentiException;
import voyager.nove.exception.PrenotazioneException;
import voyager.nove.exception.TrattaInesistenteException;
import voyager.nove.model.viaggio.Catalogo;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.Tratta;
import voyager.nove.model.viaggio.basic.Data;

public class ControllerGestionePrenotazione {
	
	private static ControllerGestionePrenotazione singletonControllerGestionePrenotazione;
	
	static Catalogo catalogo;

	private ControllerGestionePrenotazione() {
		catalogo = Catalogo.getIstanza();
	}

	public static synchronized ControllerGestionePrenotazione getInstance() {
		if (singletonControllerGestionePrenotazione == null) {
			singletonControllerGestionePrenotazione = new ControllerGestionePrenotazione();
		}
		
		return singletonControllerGestionePrenotazione;
	}
	
	public void aggiungiOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, Integer[] data, int durata, int posti) throws TrattaInesistenteException, IDEsternoElementoException, OffertaException{
		
		Tratta tratta = catalogo.getTrattaByValue(ambiente, mezzo, partenza, arrivo, via);
		Integer idTratta = tratta.getID();
		Data dataPartenza = new Data(data[0], data[1], data[2], data[3], data[4]);
		
		if (catalogo.verificaEsistenzaOfferta(ambiente,mezzo,partenza,arrivo,via,dataPartenza)){
			throw new OffertaException("Offerta gia' esistente per il viaggio!");
		}
		
		Offerta nuovaOfferta = new Offerta(idTratta, dataPartenza, durata, posti);
		catalogo.aggiungiOffertaAlCatalogo(nuovaOfferta, tratta);
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
	}
	
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
				
		String stringaOfferte = "";
		
		Set<Data> s = catalogo.getChiaviOfferte(ambiente, mezzo, partenza, arrivo, via);
		Iterator<Data> it = s.iterator();
		
		while (it.hasNext()){
			
			Offerta o = catalogo.getOffertaFromMappa(ambiente, mezzo, partenza, arrivo, via, it.next());
			
			stringaOfferte += o.getData().stampaData() + "\t" + o.getDataArrivo().stampaData() + "\t" + o.getPosti() + "\n";

		}
		
		
		return stringaOfferte;
	}

}
