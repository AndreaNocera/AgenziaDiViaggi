package gestione_Catalogo.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.TrattaException;

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
		listaTratte = new ArrayList<Tratta>();
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
		//return listaTratte.contains(tratta);
		return false;
	}
	
	public boolean verificaEsistenzaOfferte(Tratta tratta){
		return false;
		
	}
	
	
	public void aggiungiViaggioAlCatalogo(Tratta tratta){
		
		listaTratte.add(tratta);
		
		aggiungiInMappaCatalogo(tratta);
	}
	




	public void rimuoviViaggioDalCatalogo(Tratta tratta) throws IDEsternoElementoException{
		
		listaTratte.remove(tratta);
		
		rimuoviDaMappaCatalogo(tratta);
		

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
			return mappaCatalogo.getElemento(ambiente).listaChiaviElementi();
		else
			throw new IDEsternoElementoException("Ambiente "+ambiente+" non presente in catalogo");
		
	}
	
	public Set<String> getChiaviCittaDiPartenza(String ambiente, String mezzo) throws IDEsternoElementoException {
		
		ElementoIntermedio elementoAmbiente = mappaCatalogo.getElemento(ambiente);
		if (elementoAmbiente.esistenzaElemento(mezzo))
			return elementoAmbiente.getElemento(mezzo).listaChiaviElementi(); 
		else
			throw new IDEsternoElementoException("Mezzo "+mezzo+" non presente in catalogo");
		
	}
	
	
	public Set<String> getChiaviCittaDiArrivo(String ambiente, String mezzo, String partenza) throws IDEsternoElementoException {
		
		ElementoIntermedio elementoMezzo = mappaCatalogo.getElemento(ambiente).getElemento(mezzo);
		if (elementoMezzo.esistenzaElemento(partenza))
			return elementoMezzo.getElemento(partenza).listaChiaviElementi();
		else 
			throw new IDEsternoElementoException("Citta' di partenza "+partenza+" non presente in catalogo");
	
	}
	
	public Set<String> getChiaviVia(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoElementoException{
		
		ElementoIntermedio elementoPartenza = mappaCatalogo.getElemento(ambiente).getElemento(mezzo).getElemento(partenza);
		if (elementoPartenza.esistenzaElemento(arrivo)){
			return  elementoPartenza.getElemento(arrivo).listaChiaviElementi();
		} else throw new IDEsternoElementoException("Citta' di arrivo "+arrivo+" non presente in catalogo");
		
	}
	
	public Tratta getTrattaByValue(String ambiente, String mezzo, String partenza, String arrivo, String via) throws TrattaException{
		for (Tratta tratta : listaTratte) {
			if (tratta.getAmbiente().getIDEsternoElemento().equalsIgnoreCase(ambiente))
				if (tratta.getMezzo().getIDEsternoElemento().equalsIgnoreCase(mezzo))
					if (tratta.getPartenza().getIDEsternoElemento().equalsIgnoreCase(partenza))
						if (tratta.getArrivo().getIDEsternoElemento().equalsIgnoreCase(arrivo))
							if (tratta.getVia().getIDEsternoElemento().equalsIgnoreCase(via))
								return tratta;
		}
		throw new TrattaException("Tratta non esistente");

	}
	
	
	public Tratta getTrattaByValue(Ambiente ambiente, Mezzo mezzo, Citta cittaPartenza, Citta cittaArrivo, Via via) throws TrattaException{
		for (Tratta tratta : listaTratte) {
			if (tratta.verifyExistance(ambiente, mezzo, cittaPartenza, cittaArrivo,
					via))
				return tratta;
		}
		throw new TrattaException("Tratta non esistente");
	}
	
	
	
	private void aggiungiInMappaCatalogo(Tratta tratta) {
		
		Ambiente ambiente = tratta.getAmbiente();
		Mezzo mezzo = tratta.getMezzo();
		Citta partenza = tratta.getPartenza();
		Citta arrivo = tratta.getArrivo();
		Via via = tratta.getVia();
		
		//Aggiungo l'ambiente in mappaCatalogo
		mappaCatalogo.aggiungiElemento(ambiente.getIDEsternoElemento(), ambiente);
		
		//Aggiungo il mezzo nella mappa dell'ambiente prima aggiunto
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).aggiungiElemento(mezzo.getIDEsternoElemento(), mezzo);
		
		//Aggiungo cittaPartenza nella mappa del mezzo prima aggiunto
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).aggiungiElemento(partenza.getIDEsternoElemento(), partenza);
			
		//Aggiungo stazioneArrivo nella mappa della cittaPartenza prima aggiunta
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).getElemento(partenza.getIDEsternoElemento()).aggiungiElemento(arrivo.getIDEsternoElemento(), arrivo);
	
		//Aggiungo via nella mappa delle citta' di Arrivo
		mappaCatalogo.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzo.getIDEsternoElemento()).getElemento(partenza.getIDEsternoElemento()).getElemento(arrivo.getIDEsternoElemento()).aggiungiElemento(via.getIDEsternoElemento(), via);
		//System.out.println("Viaggio Aggiunto");
		
	}
	
	
	private void rimuoviDaMappaCatalogo(Tratta tratta) throws IDEsternoElementoException {
		
		ElementoIntermedio elementoAmbiente = mappaCatalogo.getElemento(tratta.getAmbiente().getIDEsternoElemento());
		ElementoIntermedio elementoMezzo = mappaCatalogo.getElemento(tratta.getMezzo().getIDEsternoElemento());
		ElementoIntermedio elementoPartenza = mappaCatalogo.getElemento(tratta.getPartenza().getIDEsternoElemento());
		ElementoIntermedio elementoArrivo = mappaCatalogo.getElemento(tratta.getArrivo().getIDEsternoElemento());

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
}

