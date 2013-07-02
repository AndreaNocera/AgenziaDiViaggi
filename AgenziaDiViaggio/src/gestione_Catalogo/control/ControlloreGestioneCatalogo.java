package gestione_Catalogo.control;

import gestione_Catalogo.entity.Ambiente;
import gestione_Catalogo.entity.Citta;
import gestione_Catalogo.entity.IDEsternoElemento;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.entity.Mezzo;
import gestione_Catalogo.entity.Tratta;
import gestione_Catalogo.entity.Via;
import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaException;
import gestione_Catalogo.exception.TrattaException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class ControlloreGestioneCatalogo extends ControlloreGestioneOfferta {
	
	//costruttore
	public ControlloreGestioneCatalogo(){
		super();
	}
	
	
	//metodi
	public void aggiungiViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via, String info) throws TrattaException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IDEsternoElementoException {
		if (via.equals("")) via = Via.DIRETTO;
		
		//verifico l'esistenza del viaggio nel catalogo
		if (catalogo.verificaEsistenzaViaggio(ambiente, mezzo, cittaPartenza, cittaArrivo, via, info)){
			//System.out.println("Viaggio gia' presente");
			throw new TrattaException("Il viaggio e' gia' presente nel catalogo!");
		} else {
			Tratta nuovaTratta = creaTratta(ambiente, mezzo, cittaPartenza, cittaArrivo, via, info);
			//aggiungo il viaggio
			catalogo.aggiungiViaggioAlCatalogo(nuovaTratta);
			log.aggiornaLogAggiungiViaggio(ambiente,mezzo,cittaPartenza,cittaArrivo,via);
		}
	}

	
	public void rimuoviViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) throws TrattaException, OffertaException, IDEsternoElementoException {
		
		Tratta tratta = catalogo.getTrattaByValue(ambiente,mezzo,cittaPartenza,cittaArrivo,via);

		// verifico l'esistenza di offerte per il viaggio
		if (catalogo.verificaEsistenzaOfferte(tratta)){
			throw new OffertaException("Ci sono offerte attive! Il viaggio non puo' essere rimosso.");
		} else { //rimuovo il viaggio
			catalogo.rimuoviViaggioDalCatalogo(tratta);
			log.aggiornaLogRimuoviViaggio(ambiente,mezzo,cittaPartenza,cittaArrivo,via);
		}
		
	}
	

	
	
	private Tratta creaTratta(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via, String info) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		/*
		 * FASE 1 : creo l'oggetto Ambiente
		 */
			
		//classe c di nome ambiente
		Class<?> c = Class.forName("gestione_Catalogo.entity."+ambiente);   // per classi in un package, va messo il nome del package!!!"
		
		//preparo i parametri
		Class<?> primoParametro	 = Class.forName("gestione_Catalogo.entity.IDEsternoElemento");
		
		Class<?>[] parametri = {primoParametro};
		
		//prendo il costruttore della classe con i parametri indicati
		Constructor<?> costruttore = c.getConstructor(parametri);
		
		//creo l'oggetto
		Ambiente a = (Ambiente) costruttore.newInstance(new IDEsternoElemento(ambiente));
		/*
		 * FASE 2: creo gli altri oggetti
		 */
		Mezzo mt = new Mezzo(new IDEsternoElemento(mezzo));
		Citta cp = new Citta(new IDEsternoElemento(cittaPartenza));
		Citta ca = new Citta(new IDEsternoElemento(cittaArrivo));
		Via v = new Via(new IDEsternoElemento(via));
		
		Info i;
		if (info.equals("")){
			i = new Info();
		} else {
			i = new Info(info);
		}
		
		return new Tratta (a,mt,cp,ca,v,i);
		
	}
	
	
}