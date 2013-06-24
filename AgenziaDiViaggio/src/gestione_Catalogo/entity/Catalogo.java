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
	private MappaCatalogo mappaAmbiente;
	
	
	//controllore
	
	public Catalogo(){
		mappaAmbiente = new MappaCatalogo();    //instanziato il catalogo, crea subito una mappa per gli ambienti
			
		
	}
	
	public boolean verificaEsistenzaViaggio(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException {
		
		/*
		 * Non va in exception: prima di prendere un elemento, verifica la sua esistenza...se c'e', lo prende, se non c'e', ritorna con false
		 */
		
		/*
		 * E' stato modifica: invoca il metodo esistenzaElemento(Elemento e) che invoca Equals!
		 */
		
		if (!mappaAmbiente.esistenzaElemento(ambiente)) return false;	//Se non c'e' l'elemento ambiente nella prima mappa torna subito con false, altrimenti continua
		
		ElementoCatalogo amb = mappaAmbiente.getElemento(ambiente.getIDEsternoElemento());		
		if (!amb.esistenzaElemento(mezzoTrasporto)) return false;  //se nn c'e' il mezzo ritorna con false, altrimenti continua
		
		ElementoCatalogo mez = amb.getElemento(mezzoTrasporto.getIDEsternoElemento());
		if (!mez.esistenzaElemento(stazionePartenza)) return false;
		
		ElementoCatalogo part = mez.getElemento(stazionePartenza.getIDEsternoElemento());
		if (!part.esistenzaElemento(stazioneArrivo)) return false;
		
		ElementoCatalogo arr = part.getElemento(stazioneArrivo.getIDEsternoElemento());
		if (!arr.esistenzaElemento(stazioneIntermedia)) return false;
		
		// Se tutti i controlli hanno dato esisto negativo, allora il viaggio e' gia' presente
		return true;
	}
	
	public boolean verificaEsistenzaOfferte(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException {
		
		//se la tabella della stazione di arrivo e' vuota (non ha offerte) ritorna con false, altrimenti con true
		return !mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzoTrasporto.getIDEsternoElemento()).getElemento(stazionePartenza.getIDEsternoElemento()).getElemento(stazioneArrivo.getIDEsternoElemento()).getElemento(stazioneIntermedia.getIDEsternoElemento()).listaChiaviElementi().isEmpty();
		
	}

	
	
	public void aggiungiViaggioAlCatalogo(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/*
		 * Bisogna sempre verificare, prima di aggiungere un elemento alla tabella, se questo elemento e' gia' presente!
		 */
		
		//FASE 1:Aggiungo l'ambiente in mappaAmbienti
		if (!mappaAmbiente.esistenzaElemento(ambiente)){
			
			//Se non c'e', lo aggiungo
			mappaAmbiente.addElemento(ambiente.getIDEsternoElemento(), ambiente);
		}
		
		//FASE 2: Aggiungo mezzodiTrasporto nella mappa dell'ambiente prima aggiunto
		if (!mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).esistenzaElemento(mezzoTrasporto)){
	
			//se non c'e' lo aggiungo
			mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).aggiungiElemento(mezzoTrasporto.getIDEsternoElemento(), mezzoTrasporto);
		}
		
		//FASE 3: Aggiungo stazionePartenza nella mappa del mezzo prima aggiunto
		if (!mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzoTrasporto.getIDEsternoElemento()).esistenzaElemento(stazionePartenza)){
			
			//se non c'e' lo aggiungo
			mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzoTrasporto.getIDEsternoElemento()).aggiungiElemento(stazionePartenza.getIDEsternoElemento(), stazionePartenza);
			
		}
		
		//FASE 4: Aggiungo stazioneArrivo nella mappa della stazionePartenza prima aggiunta
		if (!mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzoTrasporto.getIDEsternoElemento()).getElemento(stazionePartenza.getIDEsternoElemento()).esistenzaElemento(stazioneArrivo)){
			//se non c'e' lo aggiungo
			mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzoTrasporto.getIDEsternoElemento()).getElemento(stazionePartenza.getIDEsternoElemento()).aggiungiElemento(stazioneArrivo.getIDEsternoElemento(), stazioneArrivo);
		}
		
		
		//FASE 5: Aggiungo stazione intermedia nella mappa delle stazioni di Arrivo
		//non c'e' bisogno di controllo, so gia' che non c'e' (verificaEsistenzaViaggio());
		mappaAmbiente.getElemento(ambiente.getIDEsternoElemento()).getElemento(mezzoTrasporto.getIDEsternoElemento()).getElemento(stazionePartenza.getIDEsternoElemento()).getElemento(stazioneArrivo.getIDEsternoElemento()).aggiungiElemento(stazioneIntermedia.getIDEsternoElemento(), stazioneIntermedia);
		//System.out.println("Viaggio Aggiunto");
	}
	
	public void rimuoviViaggioDalCatalogo(Ambiente ambiente, MezzoTrasporto mezzoTrasporto, StazionePartenza stazionePartenza, StazioneArrivo stazioneArrivo, StazioneIntermedia stazioneIntermedia) throws IDEsternoElementoException {
	
		ElementoCatalogo elementoAmbiente = mappaAmbiente.getElemento(ambiente.getIDEsternoElemento());
		ElementoCatalogo elementoMezzo = elementoAmbiente.getElemento(mezzoTrasporto.getIDEsternoElemento());
		ElementoCatalogo elementoPartenza = elementoMezzo.getElemento(stazionePartenza.getIDEsternoElemento());
		ElementoCatalogo elementoArrivo = elementoPartenza.getElemento(stazioneArrivo.getIDEsternoElemento());
		
		// Rimuovo stazione intermedia dalla tabella
		elementoArrivo.rimuoviElemento(stazioneIntermedia.getIDEsternoElemento());
		
		// Se la tabella della stazione di arrivo non ha elementi, rimuovo la stazione di arrivo;
		if (elementoArrivo.listaChiaviElementi().isEmpty())
			elementoPartenza.rimuoviElemento(stazioneArrivo.getIDEsternoElemento());
		
		// Se la tabella della stazione di partenza non ha elementi, rimuovo la stazione di partenza
		if (elementoPartenza.listaChiaviElementi().isEmpty())
			elementoMezzo.rimuoviElemento(stazionePartenza.getIDEsternoElemento());
		
		// Se la tabella del mezzo di trasporto non ha elementi, rimuovo il mezzo
		if (elementoMezzo.listaChiaviElementi().isEmpty())
			elementoAmbiente.rimuoviElemento(mezzoTrasporto.getIDEsternoElemento());
		
		// Se la tabella dell'ambiente non ha elementi, rimuovo l'ambiente
		if (elementoAmbiente.listaChiaviElementi().isEmpty())
			mappaAmbiente.removeElemento(ambiente.getIDEsternoElemento());

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
		
		ElementoCatalogo elementoAmbiente = mappaAmbiente.getElemento(ambiente);
		if (elementoAmbiente.esistenzaElemento(mezzo))
			return elementoAmbiente.getElemento(mezzo).listaChiaviElementi(); 
		else
			throw new IDEsternoElementoException("Mezzo "+mezzo+" non presente in catalogo");
		
	}
	
	
	public Set<String> getChiaviStazioniDiArrivo(String ambiente, String mezzo, String partenza) throws IDEsternoElementoException {
		
		ElementoCatalogo elementoMezzo = mappaAmbiente.getElemento(ambiente).getElemento(mezzo);
		if (elementoMezzo.esistenzaElemento(partenza))
			return elementoMezzo.getElemento(partenza).listaChiaviElementi();
		else 
			throw new IDEsternoElementoException("Stazione di partenza "+partenza+" non presente in catalogo");
	
	}
	
	public Set<String> getChiaviStazioniIntermedie(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoElementoException{
		
		ElementoCatalogo elementoPartenza = mappaAmbiente.getElemento(ambiente).getElemento(mezzo).getElemento(partenza);
		if (elementoPartenza.esistenzaElemento(arrivo)){
			return  elementoPartenza.getElemento(arrivo).listaChiaviElementi();
		} else throw new IDEsternoElementoException("Stazione di arrivo "+arrivo+" non presente in catalogo");
	}
	
	public String getInfo(String ambiente, String mezzo, String partenza, String arrivo, String intermedia) throws IDEsternoElementoException{
		
		ElementoCatalogo elementoArrivo = mappaAmbiente.getElemento(ambiente).getElemento(mezzo).getElemento(partenza).getElemento(arrivo);
		if (elementoArrivo.esistenzaElemento(intermedia)){
			return  ((StazioneIntermedia) elementoArrivo.getElemento(intermedia)).getInfo().toString();
		} else {
			throw new IDEsternoElementoException("Stazione intermedia "+intermedia+" non presente in catalogo");
		}
		
	}

	
}