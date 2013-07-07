package gestione_Catalogo.entity;

import gestione_Catalogo.dao.CatalogoDAO;
import gestione_Catalogo.dao.OffertaDAO;
import gestione_Catalogo.dao.TrattaDAO;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaInesistenteException;
import gestione_Catalogo.exception.OfferteNonPresentiException;
import gestione_Catalogo.exception.TrattaInesistenteException;

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
	private ArrayList<Offerta> listaOfferte;
	private ArrayList<Prenotazione> listaPrenotazioni;
	
	private MappaCatalogo mappaCatalogo;
	
	//costruttore
	private Catalogo() {
		listaTratte = new ArrayList<Tratta>();
		listaOfferte = new ArrayList<Offerta>();
		mappaCatalogo = new MappaCatalogo(); //istanziato il catalogo, creo subito una mappa per gli ambienti
		CatalogoDAO dao = CatalogoDAO.getIstanza();
		listaTratte = dao.getCatalogo();
		OffertaDAO offertaDao = OffertaDAO.getIstanza();
		listaOfferte = offertaDao.getListaOfferte();
		
		try {
			caricaCatalogo();
		} catch (IDEsternoElementoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// metodi
	public static Catalogo getIstanza(){
		if (istanza == null){
			istanza = new Catalogo();
		}
		return istanza;
	}
	
	
	public boolean verificaEsistenzaViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) throws IDEsternoElementoException{	
	/*	//IMPLEMENTAZIONE CON LISTA
		for (Tratta tratta : listaTratte){
			if (tratta.verifyExistence(ambiente, mezzo, cittaPartenza, cittaArrivo, via))
				return true;
		}
		return false;
	*/
		
		//IMPLEMENTAZIONE ORIGINARIA CON MAPPA
		/*
		 * Non va in exception: prima di prendere un elemento, verifica la sua esistenza...se c'è, lo prende, se non c'è, ritorna con false
		 */
		
		if (!mappaCatalogo.esistenzaElemento(ambiente)) return false;	//Se non c'è l'elemento ambiente nella prima mappa torna subito con false, altrimenti continua
		ElementoCatalogo amb = mappaCatalogo.getElemento(ambiente);
		if (!amb.esistenzaElemento(mezzo)) return false;  //se nn c'è il mezzo ritorna con false, altrimenti continua
		ElementoCatalogo mez = amb.getElemento(mezzo);
		if (!mez.esistenzaElemento(cittaPartenza)) return false;
		ElementoCatalogo part = mez.getElemento(cittaPartenza);
		if (!part.esistenzaElemento(cittaArrivo)) return false;
		ElementoCatalogo arr = part.getElemento(cittaArrivo);
		if (!arr.esistenzaElemento(via)) return false;
		
		// Se tutti i controlli hanno dato esisto negativo, allora il viaggio è già presente
		return true;
	}
	
	
	//NUOVA IMPLEMENTAZIONE CON MAPPA. Verifica se esista o meno una determinata offerta.
	public boolean verificaEsistenzaOfferta(String ambiente, String mezzo, String partenza, String arrivo, String via, Data dataPartenza) throws IDEsternoElementoException {
		return mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo).getElemento(via).esistenzaOfferta(dataPartenza);
	}

	//IMPLEMENTAZIONE CON LISTA. Verifica se esista o meno una determinata offerta.
	public boolean verificaEsistenzaOfferta(Integer idTratta, Data dataPartenza) {
		for (Offerta offerta : listaOfferte) {
			if (offerta.verifyExistence(idTratta, dataPartenza)){
				return true;
			}
		} return false;
		
	}

	
	//IMPLEMENTAZIONE ORIGINARIA CON MAPPA. Verifica se esistono offerte per un determinato viaggio. Un viaggio non puo' essere rimosso se esistono offerte ad esso associate.
	public boolean verificaEsistenzaOfferte(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) throws IDEsternoElementoException {
		return !mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(cittaPartenza).getElemento(cittaArrivo).getElemento(via).isEmpty();
	}
	
	//IMPLEMENTAZIONE CON LISTA. Verifica se esistono offerte per un determinato viaggio. Un viaggio non puo' essere rimosso se esistono offerte ad esso associate.
	public boolean verificaEsistenzaOfferte(Integer idTratta){
		for (Offerta offerta : listaOfferte){
			if(offerta.verifyExistence(idTratta))
				return true;
		}
		return false;
	}

	
	public boolean verificaEsistenzaPrenotazioni(){
		return false;
		
	}
	
	
	public void aggiungiViaggioAlCatalogo(Tratta tratta) throws IDEsternoElementoException{
		
		listaTratte.add(tratta);
		
		aggiungiInMappaCatalogo(tratta);
		
	}
	

	public void rimuoviViaggioDalCatalogo(Tratta tratta) throws IDEsternoElementoException{
		
		listaTratte.remove(tratta);
		
		rimuoviDaMappaCatalogo(tratta);
		
		TrattaDAO dao = TrattaDAO.getIstanza();
		dao.delete(tratta);
		
	}
	
	
	public void aggiungiOffertaAlCatalogo(Offerta offerta, Tratta tratta) throws IDEsternoElementoException{
		listaOfferte.add(offerta);
		
		aggiungiInMappaOfferte(tratta, offerta);
	}
	
	
	public void rimuoviOffertaDalCatalogo(Offerta offerta, Tratta tratta) throws IDEsternoElementoException, OffertaInesistenteException {
		listaOfferte.remove(offerta);
		
		rimuoviDaMappaOfferte(tratta, offerta);
		
		OffertaDAO dao = OffertaDAO.getIstanza();
		dao.delete(offerta);
		
	}


	public void caricaCatalogo() throws IDEsternoElementoException{
		for (Tratta tratta : listaTratte){
			aggiungiInMappaCatalogo(tratta);
			caricaOfferte(tratta);
		}
	}
	
	public void caricaOfferte(Tratta tratta) throws IDEsternoElementoException{
		for (Offerta offerta : listaOfferte){
			if (tratta.getID().equals(offerta.getIdTratta()))
				aggiungiInMappaOfferte(tratta, offerta);
		}
	}
	

	public Set<String> getChiaviAmbienti() throws MappaException {
		Set<String> ambienti = mappaCatalogo.keySet();
		if (ambienti.isEmpty())
			throw new MappaException("Non sono presenti Viaggi nel Catalogo.");
		else
			return ambienti;
	}
	
	public Set<String> getChiaviMezzi(String ambiente) throws IDEsternoElementoException {
		return mappaCatalogo.getElemento(ambiente).listaChiaviElementi();	
	}
	
	public Set<String> getChiaviCittaDiPartenza(String ambiente, String mezzo) throws IDEsternoElementoException {
		return mappaCatalogo.getElemento(ambiente).getElemento(mezzo).listaChiaviElementi(); 
	}
	
	public Set<String> getChiaviCittaDiArrivo(String ambiente, String mezzo, String partenza) throws IDEsternoElementoException {
		return mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).listaChiaviElementi();
	}
	
	public Set<String> getChiaviVia(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoElementoException{
		return  mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo).listaChiaviElementi();
	}
	
	
	public Set<Data> getChiaviOfferte(String ambiente, String mezzo, String partenza, String arrivo, String via) throws IDEsternoElementoException, OfferteNonPresentiException {
		Set<Data> offerte = mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo).getElemento(via).listaChiaviOfferte();
		if (offerte.isEmpty()){
			throw new OfferteNonPresentiException("Non ci sono offerte per questo Viaggio.");
		} else
			return offerte;
	
	}
	
	
	public Tratta getTrattaByValue(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) throws TrattaInesistenteException{
		for (Tratta tratta : listaTratte) {
			if (tratta.verifyExistence(ambiente, mezzo, cittaPartenza, cittaArrivo, via))
				return tratta;
		}
		throw new TrattaInesistenteException("Tratta non esistente.");
	}
	
	public Offerta getOffertaByData(Integer idTratta, Data dataPartenza) throws OffertaInesistenteException {
		//VECCHIA IMPLEMENTAZIONE DI MOSTRALISTAOFFERTAINCATALOGO
		for (Offerta offerta : listaOfferte){
			if (offerta.getIdTratta().equals(idTratta)){
				if (offerta.getData().equals(dataPartenza)) {
						return offerta;
				}
			}
		}
		throw new OffertaInesistenteException("Offerta inesistente.");
	}
	
	public Offerta getOffertaFromMappa(String ambiente, String mezzo, String partenza, String arrivo, String via, Data data) throws IDEsternoElementoException, OffertaInesistenteException {
		
		return mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo).getElemento(via).getOfferta(data);
	}
	
	
	private void aggiungiInMappaCatalogo(Tratta tratta) throws IDEsternoElementoException {
		/*
		 * Il controllo con esistenzaElemento() qui non e' piu' necessario, dal 
		 * momento che il metodo aggiungiElemento() in MappaCatalogo (a sua 
		 * volta richiamato dal metodo aggiungiElemento() in ElementoIntermedio) 
		 * aggiunge un elemento solo se la chiave non esiste gia'. 
		 */
		
		Ambiente ambiente = tratta.getAmbiente();
		Mezzo mezzo = tratta.getMezzo();
		Citta partenza = tratta.getPartenza();
		Citta arrivo = tratta.getArrivo();
		Via via = tratta.getVia();
		
		/*
		 * Bisogna sempre verificare, prima di aggiungere un elemento alla tabella, se questo elemento e' gia' presente!
		 */
		
		// Aggiungo l'ambiente in mappaCatalogo
		mappaCatalogo.aggiungiElemento(ambiente.getIDEsternoElemento(), ambiente);
		
		// Aggiungo il mezzo nella mappa dell'ambiente prima aggiunto
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).aggiungiElemento(mezzo.getIDEsternoElemento(), mezzo);
				
		// Aggiungo cittaPartenza nella mappa del mezzo prima aggiunto
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).aggiungiElemento(partenza.getIDEsternoElemento(), partenza);
		
		// Aggiungo stazioneArrivo nella mappa della cittaPartenza prima aggiunta
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).getElemento(partenza.getIDEsternoElemento()).aggiungiElemento(arrivo.getIDEsternoElemento(), arrivo);
				
		// Aggiungo via nella mappa delle citta' di Arrivo
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).getElemento(partenza.getIDEsternoElemento()).getElemento(arrivo.getIDEsternoElemento()).aggiungiElemento(via.getIDEsternoElemento(), via);

		//System.out.println("Viaggio Aggiunto");
		
	}
	
	
	private void rimuoviDaMappaCatalogo(Tratta tratta) throws IDEsternoElementoException {

		ElementoCatalogo elementoAmbiente = mappaCatalogo.getElemento(tratta.getAmbiente().getIDEsternoElemento());
		ElementoCatalogo elementoMezzo = elementoAmbiente.getElemento(tratta.getMezzo().getIDEsternoElemento());
		ElementoCatalogo elementoPartenza = elementoMezzo.getElemento(tratta.getPartenza().getIDEsternoElemento());
		ElementoCatalogo elementoArrivo = elementoPartenza.getElemento(tratta.getArrivo().getIDEsternoElemento());

		// Rimuovo via dalla mappa
		elementoArrivo.rimuoviElemento(tratta.getVia().getIDEsternoElemento());

		// Se la mappa della citta' di arrivo non ha elementi, rimuovo la citta' di arrivo;
		if (elementoArrivo.listaChiaviElementi().isEmpty())
			elementoPartenza.rimuoviElemento(tratta.getArrivo().getIDEsternoElemento());
		
		// Se la mappa della citta' di partenza non ha elementi, rimuovo la citta' di partenza
		if (elementoPartenza.listaChiaviElementi().isEmpty())
			elementoMezzo.rimuoviElemento(tratta.getPartenza().getIDEsternoElemento());
		
		// Se la mappa del mezzo non ha elementi, rimuovo il mezzo
		if (elementoMezzo.listaChiaviElementi().isEmpty())
			elementoAmbiente.rimuoviElemento(tratta.getMezzo().getIDEsternoElemento());
		
		// Se la mappa dell'ambiente non ha elementi, rimuovo l'ambiente
		if (elementoAmbiente.listaChiaviElementi().isEmpty())
			mappaCatalogo.rimuoviElemento(tratta.getAmbiente().getIDEsternoElemento());

		//System.out.println("Viaggio Rimosso");
		
	}


	private void aggiungiInMappaOfferte(Tratta tratta, Offerta offerta) throws IDEsternoElementoException {
		String ambiente = tratta.getAmbiente().getIDEsternoElemento();
		String mezzo = tratta.getMezzo().getIDEsternoElemento();
		String partenza = tratta.getPartenza().getIDEsternoElemento();
		String arrivo = tratta.getArrivo().getIDEsternoElemento();
		String via = tratta.getVia().getIDEsternoElemento();
		mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo).getElemento(via).aggiungiOfferta(offerta.getData(), offerta);
		
	}

	private void rimuoviDaMappaOfferte(Tratta tratta, Offerta offerta) throws IDEsternoElementoException, OffertaInesistenteException {
		String ambiente = tratta.getAmbiente().getIDEsternoElemento();
		String mezzo = tratta.getMezzo().getIDEsternoElemento();
		String partenza = tratta.getPartenza().getIDEsternoElemento();
		String arrivo = tratta.getArrivo().getIDEsternoElemento();
		String via = tratta.getVia().getIDEsternoElemento();
		mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo).getElemento(via).rimuoviOfferta(offerta.getData());
	}






	
	



}

