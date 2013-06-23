/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */

package gestione_Catalogo.entity;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;



/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @authors Remo Sperlongano e Ivan Torre
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
	
	public boolean verificaEsistenzaViaggio(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException {
		
		/*
		 * Non va in exception: prima di prendere un elemento, verifica la sua esistenza...se c'e', lo prende, se non c'e', ritorna con false
		 */
		
		/*
		 * E' stato modifica: invoca il metodo esistenzaElemento(Elemento e) che invoca Equals!
		 */
		
		if (!mappaAmbiente.esistenzaElemento(ambiente)) return false;	//Se non c'e' l'elemento ambiente nella prima mappa torna subito con false, altrimenti continua
		
		Elemento amb = mappaAmbiente.getElemento(ambiente.getIDEsterno());		
		if (!amb.esistenzaElemento(mezzoTrasporto)) return false;  //se nn c'e' il mezzo ritorna con false, altrimenti continua
		
		Elemento mez = amb.getElemento(mezzoTrasporto.getIDEsterno());
		if (!mez.esistenzaElemento(stazionePartenza)) return false;
		
		Elemento part = mez.getElemento(stazionePartenza.getIDEsterno());
		if (!part.esistenzaElemento(stazioneArrivo)) return false;
		
		Elemento arr = part.getElemento(stazioneArrivo.getIDEsterno());
		if (!arr.esistenzaElemento(stazioneIntermedia)) return false;
		
		// Se tutti i controlli hanno dato esisto negativo, allora il viaggio e' gia' presente
		return true;
	}
	
	public boolean verificaEsistenzaOfferte(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException {
		
		//se la tabella della stazione di arrivo e' vuota (non ha offerte) ritorna con false, altrimenti con true
		return !mappaAmbiente.getElemento(ambiente.getIDEsterno()).getElemento(mezzoTrasporto.getIDEsterno()).getElemento(stazionePartenza.getIDEsterno()).getElemento(stazioneArrivo.getIDEsterno()).getElemento(stazioneIntermedia.getIDEsterno()).listaChiaviElementi().isEmpty();
		
	}

	
	
	public void aggiungiViaggioAlCatalogo(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/*
		 * Bisogna sempre verificare, prima di aggiungere un elemento alla tabella, se questo elemento e' gia' presente!
		 */
		
		//FASE 1:Aggiungo l'ambiente in mappaAmbienti
		if (!mappaAmbiente.esistenzaElemento(ambiente)){
			
			//Se non c'e', lo aggiungo
			mappaAmbiente.addElemento(ambiente.getIDEsterno(), ambiente);
		}
		
		//FASE 2: Aggiungo mezzodiTrasporto nella mappa dell'ambiente prima aggiunto
		if (!mappaAmbiente.getElemento(ambiente.getIDEsterno()).esistenzaElemento(mezzoTrasporto)){
	
			//se non c'e' lo aggiungo
			mappaAmbiente.getElemento(ambiente.getIDEsterno()).aggiungiElemento(mezzoTrasporto.getIDEsterno(), mezzoTrasporto);
		}
		
		//FASE 3: Aggiungo stazionePartenza nella mappa del mezzo prima aggiunto
		if (!mappaAmbiente.getElemento(ambiente.getIDEsterno()).getElemento(mezzoTrasporto.getIDEsterno()).esistenzaElemento(stazionePartenza)){
			
			//se non c'e' lo aggiungo
			mappaAmbiente.getElemento(ambiente.getIDEsterno()).getElemento(mezzoTrasporto.getIDEsterno()).aggiungiElemento(stazionePartenza.getIDEsterno(), stazionePartenza);
			
		}
		
		//FASE 4: Aggiungo stazioneArrivo nella mappa della stazionePartenza prima aggiunta
		if (!mappaAmbiente.getElemento(ambiente.getIDEsterno()).getElemento(mezzoTrasporto.getIDEsterno()).getElemento(stazionePartenza.getIDEsterno()).esistenzaElemento(stazioneArrivo)){
			//se non c'e' lo aggiungo
			mappaAmbiente.getElemento(ambiente.getIDEsterno()).getElemento(mezzoTrasporto.getIDEsterno()).getElemento(stazionePartenza.getIDEsterno()).aggiungiElemento(stazioneArrivo.getIDEsterno(), stazioneArrivo);
		}
		
		
		//FASE 5: Aggiungo stazione intermedia nella mappa delle stazioni di Arrivo
		//non c'e' bisogno di controllo, so gia' che non c'e' (verificaEsistenzaViaggio());
		mappaAmbiente.getElemento(ambiente.getIDEsterno()).getElemento(mezzoTrasporto.getIDEsterno()).getElemento(stazionePartenza.getIDEsterno()).getElemento(stazioneArrivo.getIDEsterno()).aggiungiElemento(stazioneIntermedia.getIDEsterno(), stazioneIntermedia);
		//System.out.println("Viaggio Aggiunto");
	}
	
	public void rimuoviViaggioDalCatalogo(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException {
	
		Elemento elementoAmbiente = mappaAmbiente.getElemento(ambiente.getIDEsterno());
		Elemento elementoMezzo = elementoAmbiente.getElemento(mezzoTrasporto.getIDEsterno());
		Elemento elementoPartenza = elementoMezzo.getElemento(stazionePartenza.getIDEsterno());
		Elemento elementoArrivo = elementoPartenza.getElemento(stazioneArrivo.getIDEsterno());
		
		// Rimuovo stazione intermedia dalla tabella
		elementoArrivo.rimuoviElemento(stazioneIntermedia.getIDEsterno());
		
		// Se la tabella della stazione di arrivo non ha elementi, rimuovo la stazione di arrivo;
		if (elementoArrivo.listaChiaviElementi().isEmpty())
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

	
	
	public Set<String> getChiaviAmbienti() throws MappaException {

		Set<String> ambienti = mappaAmbiente.listaChiaviElementi();
		if (ambienti.isEmpty())
			throw new MappaException("Nessun viaggio in catalogo!");
		else
			return ambienti;
				
	}

	
	
	public Set<String> getChiaviMezziDiTrasporto(String ambiente) throws IDEsternoElementoException {

		if (mappaAmbiente.esistenzaElemento(ambiente))
			return mappaAmbiente.getElemento(ambiente).listaChiaviElementi();
		else
			throw new IDEsternoElementoException("Ambiente "+ambiente+" non presente in catalogo");
	}

	
	
	public Set<String> getChiaviStazioniDiPartenza(String ambiente, String mezzo) throws IDEsternoElementoException {
		
		Elemento elementoAmbiente = mappaAmbiente.getElemento(ambiente);
		if (elementoAmbiente.esistenzaElemento(mezzo))
			return elementoAmbiente.getElemento(mezzo).listaChiaviElementi(); 
		else
			throw new IDEsternoElementoException("Mezzo "+mezzo+" non presente in catalogo");
		
	}
	
	
	public Set<String> getChiaviStazioniDiArrivo(String ambiente, String mezzo, String partenza) throws IDEsternoElementoException {
		
		Elemento elementoMezzo = mappaAmbiente.getElemento(ambiente).getElemento(mezzo);
		if (elementoMezzo.esistenzaElemento(partenza))
			return elementoMezzo.getElemento(partenza).listaChiaviElementi();
		else 
			throw new IDEsternoElementoException("Stazione di partenza "+partenza+" non presente in catalogo");
	
	}
	
	public Set<String> getChiaviStazioniIntermedie(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoElementoException{
		
		Elemento elementoPartenza = mappaAmbiente.getElemento(ambiente).getElemento(mezzo).getElemento(partenza);
		if (elementoPartenza.esistenzaElemento(arrivo)){
			return  elementoPartenza.getElemento(arrivo).listaChiaviElementi();
		} else throw new IDEsternoElementoException("Stazione di arrivo "+arrivo+" non presente in catalogo");
	}
	
	public String getInfo(String ambiente, String mezzo, String partenza, String arrivo, String intermedia) throws IDEsternoElementoException{
		
		Elemento elementoArrivo = mappaAmbiente.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo);
		if (elementoArrivo.esistenzaElemento(intermedia)){
			return  ((StazioneIntermedia) elementoArrivo.getElemento(intermedia)).getInfo().toString();
		} else {
			throw new IDEsternoElementoException("Stazione intermedia "+intermedia+" non presente in catalogo");
		}
		
	}

	
}