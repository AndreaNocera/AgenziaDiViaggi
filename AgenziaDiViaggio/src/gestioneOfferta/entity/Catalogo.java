/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestioneOfferta.entity;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import gestione_Catalogo.entity.Ambiente;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.entity.MezzoTrasporto;
import gestione_Catalogo.entity.StazioneArrivo;
import gestione_Catalogo.entity.StazionePartenza;
import gestione_Catalogo.exception.IDEsternoException;
import gestione_Catalogo.exception.MappaException;



/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Sonia
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */



public class Catalogo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	//attributi d'istanza
	private Mappa mappaAmbiente;
	
	
	//controllore
	
	public Catalogo(){
		mappaAmbiente = new Mappa();    //instanziato il catalogo, crea subito una mappa per gli ambienti
	}
	
	public boolean verificaEsistenzaViaggio(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo) throws IDEsternoException {
		
		/*
		 * Non va in exception: prima di prendere un elemento, verifica la sua esistenza...se c'�, lo prende, se non c'�, ritorna con false
		 */
		
		if (!mappaAmbiente.esistenzaElemento(ambiente)) return false;	//Se non c'� l'elemento ambiente nella prima mappa torna subito con false, altrimenti continua
		Elemento amb = mappaAmbiente.getElemento(ambiente);
		if (!amb.esistenzaElemento(mezzoTrasporto)) return false;  //se nn c'� il mezzo ritorna con false, altrimenti continua
		Elemento mez = amb.getElemento(mezzoTrasporto);
		if (!mez.esistenzaElemento(stazionePartenza)) return false;
		Elemento part = mez.getElemento(stazionePartenza);
		if (!part.esistenzaElemento(stazioneArrivo)) return false;
		
		// Se tutti i controlli hanno dato esisto negativo, allora il viaggio � gi� presente
		return true;
	}
	
	public boolean verificaEsistenzaOfferte(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo) throws IDEsternoException {
		
		//se la tabella della stazione di arrivo � vuota (non ha offerte) ritorna con false, altrimenti con true
		return !mappaAmbiente.getElemento(ambiente).getElemento(mezzoTrasporto).getElemento(stazionePartenza).getElemento(stazioneArrivo).listaChiaviElementi().isEmpty();
		
	}

	public void aggiungiViaggioAlCatalogo(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, String info) throws IDEsternoException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/*
		 * Bisogna sempre verificare, prima di aggiungere un elemento alla tabella, se questo elemento � gi� presente!
		 */
		
		//FASE 1:Aggiungo l'ambiente in mappaAmbienti
		if (!mappaAmbiente.esistenzaElemento(ambiente)){
			
			Elemento e;
			
			//classe c di nome ambiente
			Class<?> c = Class.forName("gestione_Catalogo.entity.Via"+ambiente);   // per classi in un package, va messo il nome del package!!!"
			
			//preparo i parametri
			Class<?> primoParametro	 = Class.forName("gestione_Catalogo.entity.IDEsterno");
			Class<?> secondoParametro	 = Class.forName("gestione_Catalogo.entity.Info");
			
			@SuppressWarnings("rawtypes")
			Class[] parametri = {primoParametro, secondoParametro};
			
			//prendo il costruttore della classe con i parametri indicati
			Constructor<?> costruttore = c.getConstructor(parametri);
			
			//creo l'oggetto
			e = (Elemento) costruttore.newInstance(new IDEsterno(ambiente.getIDEsterno()), new Info(""));
			
			//e lo aggiungo alla mappa
			mappaAmbiente.addElemento(ambiente.getIDEsterno(), e);
		}
		
		//FASE 2: Aggiungo mezzodiTrasporto nella mappa dell'ambiente prima aggiunto
		if (!mappaAmbiente.getElemento(ambiente).esistenzaElemento(mezzoTrasporto)){
			Elemento mt = new MezzoTrasporto(new IDEsterno(mezzoTrasporto.getIDEsterno()), new Info(""));
			mappaAmbiente.getElemento(ambiente).aggiungiElemento(mezzoTrasporto.getIDEsterno(), mt);
		}
		
		//FASE 3: Aggiungo stazionePartenza nella mappa del mezzo prima aggiunto
		if (!mappaAmbiente.getElemento(ambiente).getElemento(mezzoTrasporto).esistenzaElemento(stazionePartenza)){
			
			Elemento sp = new StazionePartenza(new IDEsterno(stazionePartenza.getIDEsterno()), new Info(""));
			mappaAmbiente.getElemento(ambiente).getElemento(mezzoTrasporto).aggiungiElemento(stazionePartenza.getIDEsterno(), sp);
			
		}
		
		//FASE 4: Aggiungo stazioneArrivo nella mappa della stazionePartenza prima aggiunta
		//non c'� bisogno di controllo, so gi� che non c'� (verificaEsistenzaViaggio());
		
		Elemento sa = new StazioneArrivo(new IDEsterno(stazioneArrivo.getIDEsterno()), new Info(info));
		mappaAmbiente.getElemento(ambiente).getElemento(mezzoTrasporto).getElemento(stazionePartenza).aggiungiElemento(stazioneArrivo.getIDEsterno(), sa);
		//System.out.println("Viaggio Aggiunto");
	}
	
	public void rimuoviViaggioDalCatalogo(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo) throws IDEsternoException {
	
		Elemento elementoAmbiente = mappaAmbiente.getElemento(ambiente);
		Elemento elementoMezzo = elementoAmbiente.getElemento(mezzoTrasporto);
		Elemento elementoPartenza = elementoMezzo.getElemento(stazionePartenza);
		
		// Rimuovo stazione di arrivo dalla tabella
		elementoPartenza.rimuoviElemento(stazioneArrivo.getIDEsterno());
		
		// Se la tabella della stazione di partenza non ha elementi, rimuovo la stazione di partenza
		if (elementoPartenza.listaChiaviElementi().isEmpty())
			elementoMezzo.rimuoviElemento(stazionePartenza.getIDEsterno());
		
		// Se la tabella del mezzo di trasporto non ha elementi, rimuovo il mezzo
		if (elementoMezzo.listaChiaviElementi().isEmpty())
			elementoAmbiente.rimuoviElemento(mezzoTrasporto.getIDEsterno());
		
		// Se la tabella dell'ambiente non ha elementi, rimuovo l'ambiente
		if (elementoAmbiente.listaChiaviElementi().isEmpty())
			mappaAmbiente.removeElemento(ambiente.getIDEsterno());
		
		//System.out.println("Viaggio Rimosso");
	}
	
	public Set<IDEsterno> getAmbienti() throws MappaException {
		Set<IDEsterno> ambienti = mappaAmbiente.listaChiaviElementi();
		if (ambienti.isEmpty()) {
			throw new MappaException("Nessun viaggio in catalogo!");
		} else {
			return ambienti;
		}
	}

	public Set<IDEsterno> getMezziDiTrasporto(Ambiente ambiente) throws IDEsternoException {

		if (mappaAmbiente.esistenzaElemento(ambiente))
			return mappaAmbiente.getElemento(ambiente).listaChiaviElementi();
		else
			throw new IDEsternoException("Ambiente "+ambiente.toString()+" non presente in catalogo");
	}

	public Set<IDEsterno> getStazioniDiPartenza(Ambiente ambiente, MezzoTrasporto mezzo) throws IDEsternoException {
		Elemento elementoAmbiente = mappaAmbiente.getElemento(ambiente);
		if (elementoAmbiente.esistenzaElemento(mezzo)) {
			return elementoAmbiente.getElemento(mezzo).listaChiaviElementi(); 
		} else {
			throw new IDEsternoException("Mezzo "+mezzo.toString()+" non presente in catalogo");
		}
	}
	
	public Set<IDEsterno> getStazioniDiArrivo(Ambiente ambiente, MezzoTrasporto mezzo, StazionePartenza partenza) throws IDEsternoException {
		Elemento elementoMezzo = mappaAmbiente.getElemento(ambiente).getElemento(mezzo);
		if (elementoMezzo.esistenzaElemento(partenza)) {
			return elementoMezzo.getElemento(partenza).listaChiaviElementi();
		} else {
			throw new IDEsternoException("Stazione di partenza "+partenza.toString()+" non presente in catalogo");
		}
	}
	
	public String getInfo(Ambiente ambiente, MezzoTrasporto mezzo, StazionePartenza partenza, StazioneArrivo arrivo) throws IDEsternoException{
		
		Elemento elementoPartenza = mappaAmbiente.getElemento(ambiente).getElemento(mezzo).getElemento(partenza);
		if (elementoPartenza.esistenzaElemento(arrivo)) {
			return elementoPartenza.getElemento(arrivo).getInfo();
		} else {
			throw new IDEsternoException("Stazione di arrivo "+arrivo+" non presente in catalogo");
		}
	}
}