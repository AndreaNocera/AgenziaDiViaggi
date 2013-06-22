/*
 * Autori:
 * Remo Sperlongano
 * Ivan Torre
 */
package gestione_Catalogo.control;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

import gestione_Catalogo.entity.Ambiente;
import gestione_Catalogo.entity.Catalogo;
import gestione_Catalogo.entity.DeserializzaOggetti;
import gestione_Catalogo.entity.IDEsterno;
import gestione_Catalogo.entity.Info;
import gestione_Catalogo.entity.Log;
import gestione_Catalogo.entity.MezzoTrasporto;
import gestione_Catalogo.entity.SerializzaOggetti;
import gestione_Catalogo.entity.StazioneArrivo;
import gestione_Catalogo.entity.StazioneIntermedia;
import gestione_Catalogo.entity.StazionePartenza;
import gestione_Catalogo.exception.DeserializzazioneException;
import gestione_Catalogo.exception.IDEsternoException;
import gestione_Catalogo.exception.MappaException;
import gestione_Catalogo.exception.OffertaException;
import gestione_Catalogo.exception.SerializzazioneException;



/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @authors Remo Sperlongano, Ivan Torre
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */


public class ControlloreGestioneCatalogo {
	
	
	//attributi d'istanza
	protected static Catalogo catalogo;   //quando ci sara' la gerarchia dei controllori, avro' un solo catalogo visibile a tutti questi.
	protected static Log log;


	//costruttore
	public ControlloreGestioneCatalogo(){
		
		if (catalogo == null){
			catalogo = new Catalogo();
		}
		if (log == null){
			log = new Log();
		}
		
	}
	
	
	
	//Metodi
	public void aggiungiViaggio(String ambiente, String mezzoTrasporto, String stazionePartenza, String stazioneArrivo, String stazioneIntermedia,  String info) throws IDEsternoException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		/*
		 * FASE 1 : creo l'oggetto Ambiente
		 */
			
		//classe c di nome ambiente
		Class<?> c = Class.forName("gestione_Catalogo.entity.Via"+ambiente);   // per classi in un package, va messo il nome del package!!!"
		
		//preparo i parametri
		Class<?> primoParametro	 = Class.forName("gestione_Catalogo.entity.IDEsterno");
		
		Class<?>[] parametri = {primoParametro};
		
		//prendo il costruttore della classe con i parametri indicati
		Constructor<?> costruttore = c.getConstructor(parametri);
		
		//creo l'oggetto
		Ambiente a = (Ambiente) costruttore.newInstance(new IDEsterno(ambiente));
		
		/*
		 * FASE 2: creo gli altri oggetti
		 */
		MezzoTrasporto mt = new MezzoTrasporto(new IDEsterno(mezzoTrasporto));
		StazionePartenza sp = new StazionePartenza(new IDEsterno(stazionePartenza));
		StazioneArrivo sa = new StazioneArrivo(new IDEsterno(stazioneArrivo));
		StazioneIntermedia si;
		if (stazioneIntermedia.equalsIgnoreCase("")){
			if (info.equalsIgnoreCase("")){
				si = new StazioneIntermedia();
			} else {
				si = new StazioneIntermedia(new Info(info));
			}	
		} else {
			if (info.equalsIgnoreCase("")){
				si = new StazioneIntermedia(new IDEsterno(stazioneIntermedia));
			} else {
				si = new StazioneIntermedia(new IDEsterno(stazioneIntermedia), new Info(info));
			}
		}
		
		
		//verifico l'esistenza del viaggio nel catalogo
		if (catalogo.verificaEsistenzaViaggio(a, mt, sp, sa, si)){
			//System.out.println("Viaggio gia' presente");
			throw new IDEsternoException("Il viaggio e' gia' presente nel catalogo!");
		} else {
			//aggiungo il viaggio
			catalogo.aggiungiViaggioAlCatalogo(a, mt, sp, sa, si);
			log.aggiornaLogAggiungiViaggio(a.getIDEsterno(), mt.getIDEsterno(), sp.getIDEsterno(), sa.getIDEsterno(), si.getIDEsterno());
		}
	}
	
	
	public void rimuoviViaggio(String ambiente, String mezzoTrasporto, String stazionePartenza, String stazioneArrivo, String stazioneIntermedia) throws IDEsternoException, OffertaException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		/*
		 * FASE 1 : creo l'oggetto Ambiente
		 */
			
		//classe c di nome ambiente
		Class<?> c = Class.forName("gestione_Catalogo.entity.Via"+ambiente);   // per classi in un package, va messo il nome del package!!!"
		
		//preparo i parametri
		Class<?> primoParametro	 = Class.forName("gestione_Catalogo.entity.IDEsterno");
		
		Class<?>[] parametri = {primoParametro};
		
		//prendo il costruttore della classe con i parametri indicati
		Constructor<?> costruttore = c.getConstructor(parametri);
		
		//creo l'oggetto
		Ambiente a = (Ambiente) costruttore.newInstance(new IDEsterno(ambiente));
		
		/*
		 * FASE 2: creo gli altri oggetti
		 */
		MezzoTrasporto mt = new MezzoTrasporto(new IDEsterno(mezzoTrasporto));
		StazionePartenza sp = new StazionePartenza(new IDEsterno(stazionePartenza));
		StazioneArrivo sa = new StazioneArrivo(new IDEsterno(stazioneArrivo));
		StazioneIntermedia si = new StazioneIntermedia(new IDEsterno(stazioneIntermedia));
		
		//verifico l'esistenza del viaggio nel catalogo
		if (!catalogo.verificaEsistenzaViaggio(a, mt, sp, sa, si)){
			//System.out.println("Viaggio non presente");
			throw new IDEsternoException("Il viaggio non e' presente nel catalogo!");
		} 
		// verifico l'esistenza di offerte per il viaggio
		if (catalogo.verificaEsistenzaOfferte(a, mt, sp, sa, si)){
			throw new OffertaException("Ci sono offerte attive! Il viaggio non puo' essere rimosso.");
		} else { //rimuovo il viaggio
			catalogo.rimuoviViaggioDalCatalogo(a, mt, sp, sa, si);
			log.aggiornaLogRimuoviViaggio(a.getIDEsterno(), mt.getIDEsterno(), sp.getIDEsterno(), sa.getIDEsterno(), si.getIDEsterno());
		}

	}

	
	public Set<String> mostraAmbientiInCatalogo() throws MappaException {
		
		return catalogo.getChiaviAmbienti();
		
	}

	
	public Set<String> mostraMezziDiTrasportoInCatalogo(String ambiente) throws IDEsternoException {
		
		return catalogo.getChiaviMezziDiTrasporto(ambiente);
		
	}
	

	public Set<String> mostraStazioniDiPartenzaInCatalogo(String ambiente, String mezzo) throws IDEsternoException {
		
		return catalogo.getChiaviStazioniDiPartenza(ambiente, mezzo);
				
	}


	public Set<String> mostraStazioniDiArrivoInCatalogo(String ambiente, String mezzo, String partenza) throws IDEsternoException {

		return catalogo.getChiaviStazioniDiArrivo(ambiente, mezzo, partenza);
		
	}
	
	public Set<String> mostraStazioniIntermedieInCatalogo(String ambiente, String mezzo, String partenza, String arrivo) throws IDEsternoException{
		return catalogo.getChiaviStazioniIntermedie(ambiente, mezzo, partenza, arrivo);
	}
	
	public String mostraCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String stazioneIntermedia) throws MappaException, IDEsternoException{
		
		/*
		 * Ho ben 6 casi ...
		 * Caso a) Ambiente = ----- , devo mostrare tutto il catalogo
		 * Caso b) Mezzo = -----, devo mostrare tutti i viaggi aventi tutti lo stesso ambiente
		 * Caso c) Partenza = -----, devo mostrare tutti i viaggio aventi tutti lo stesso mezzo
		 * Caso d) Arrivo = -----, devo mostrare tutti i viaggi aventi stesso mezzo e stessa stazione di partenza
		 * Caso e) StazioneIntermedia = -----, devo mostrare tutti i viaggi aventi stesso mezzo, stessa stazione di partenza e stessa stazione di arrivo
		 * Caso f) Il viaggio e' composto, verra' visualizzato solo un viaggio...
		 */
		
		Set<String> chiaviAmbiente;
		Set<String> chiaviMezzo;
		Set<String> chiaviPartenza;
		Set<String> chiaviArrivo;
		Set<String> chiaviIntermedie;
		
		Iterator<String>	itAmbiente;		
		Iterator<String>	itMezzo;
		Iterator<String>	itPartenza;
		Iterator<String>	itArrivo;
		Iterator<String>	itIntermedie;
		
		String amb;
		String mez;
		String sp;
		String sa;
		String si;
		String info;
		
		String listaViaggi = "";
		String unViaggio;

		//caso a)
		if (ambiente.equals("-----")){
			
			//Prendo tutte le chiavi di ambiente
			chiaviAmbiente = catalogo.getChiaviAmbienti();
			itAmbiente = chiaviAmbiente.iterator();
			
			while(itAmbiente.hasNext()){  //itero le chiavi ambiente
				
				amb = itAmbiente.next();
				//Prendo tutte le chiavi di Mezzo
				chiaviMezzo = catalogo.getChiaviMezziDiTrasporto(amb);
				itMezzo = chiaviMezzo.iterator();
				
				while(itMezzo.hasNext()){	//itero tutte le chiavi mezzo
					
					mez = itMezzo.next();
					//Prendo tutte le chiavi di stazionePartenza
					chiaviPartenza = catalogo.getChiaviStazioniDiPartenza(amb, mez);
					itPartenza = chiaviPartenza.iterator();
					
					while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
						
						sp = itPartenza.next();
						//Prendo tute le chiavi di stazioneArrivo
						chiaviArrivo = catalogo.getChiaviStazioniDiArrivo(amb, mez, sp);
						itArrivo = chiaviArrivo.iterator();
						
						while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
							
							sa = itArrivo.next();
							//Prendo tute le chiavi di stazioneIntermedia
							chiaviIntermedie = catalogo.getChiaviStazioniIntermedie(amb, mez, sp, sa);
							itIntermedie = chiaviIntermedie.iterator();
							
							while(itIntermedie.hasNext()){	//itero tutte le stazioni intermedie;
								
								si = itIntermedie.next();
								//Prendo le info memorizzate in stazione arrivo
								info = catalogo.getInfo(amb, mez, sp, sa, si);
								//Metto in stringa le informazioni di questo viaggio;
								unViaggio = componiCatalogo(amb,mez,sp,sa,si,info);
								//Lo aggiungo alla lista
								listaViaggi = listaViaggi + unViaggio;
							}
			
						}
					}
				}
			}
			
			//ritorna con questa lista
			return listaViaggi;
		}
		
		
		
		
		
		//caso b)
		if (mezzo.equals("-----")){
			
			// non serve iterare le chiavi di ambiente, ho gia da parametro quello giusto
			chiaviMezzo = catalogo.getChiaviMezziDiTrasporto(ambiente);
			itMezzo = chiaviMezzo.iterator();
			
			while(itMezzo.hasNext()){	//itero tutte le chiavi mezzo
				
				mez = itMezzo.next();
				//Prendo tutte le chiavi di stazionePartenza
				chiaviPartenza = catalogo.getChiaviStazioniDiPartenza(ambiente, mez);
				itPartenza = chiaviPartenza.iterator();
				
				while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
					
					sp = itPartenza.next();
					//Prendo tute le chiavi di stazioneArrivo
					chiaviArrivo = catalogo.getChiaviStazioniDiArrivo(ambiente, mez, sp);
					itArrivo = chiaviArrivo.iterator();
					
					while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
						
						sa = itArrivo.next();
						//Prendo tute le chiavi di stazioneIntermedia
						chiaviIntermedie = catalogo.getChiaviStazioniIntermedie(ambiente, mez, sp, sa);
						itIntermedie = chiaviIntermedie.iterator();
						
						while(itIntermedie.hasNext()){	//itero tutte le stazioni intermedie;
							
							si = itIntermedie.next();
							//Prendo le info memorizzate in stazione arrivo
							info = catalogo.getInfo(ambiente, mez, sp, sa, si);
							//Metto in stringa le informazioni di questo viaggio;
							unViaggio = componiCatalogo(ambiente,mez,sp,sa,si,info);
							//Lo aggiungo alla lista
							listaViaggi = listaViaggi + unViaggio;
						}
		
					}
				}
			}
			//ritorna questa lista
			return listaViaggi;
		}
		
		
		
		
		//caso c)
		if (partenza.equals("-----")){
			
			//non serve iterare le chiavi ambiente e mezzo, li ho gia da parametro
			chiaviPartenza = catalogo.getChiaviStazioniDiPartenza(ambiente, mezzo);
			itPartenza = chiaviPartenza.iterator();
			
			while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
				
				sp = itPartenza.next();
				//Prendo tute le chiavi di stazioneArrivo
				chiaviArrivo = catalogo.getChiaviStazioniDiArrivo(ambiente, mezzo, sp);
				itArrivo = chiaviArrivo.iterator();
				
				while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
					
					sa = itArrivo.next();
					//Prendo tute le chiavi di stazioneIntermedia
					chiaviIntermedie = catalogo.getChiaviStazioniIntermedie(ambiente, mezzo, sp, sa);
					itIntermedie = chiaviIntermedie.iterator();
					
					while(itIntermedie.hasNext()){	//itero tutte le stazioni intermedie;
						
						si = itIntermedie.next();
						//Prendo le info memorizzate in stazione arrivo
						info = catalogo.getInfo(ambiente, mezzo, sp, sa, si);
						//Metto in stringa le informazioni di questo viaggio;
						unViaggio = componiCatalogo(ambiente, mezzo,sp,sa,si,info);
						//Lo aggiungo alla lista
						listaViaggi = listaViaggi + unViaggio;
					}
	
				}
			}
			//ritorna con questa lista
			return listaViaggi;
		}
		
		
		
		
		//caso d)
		if (arrivo.equals("-----")){
			//non serve iterare le chiavi di ambiente, mezzo e partenza, li ho gia da parametro
			chiaviArrivo = catalogo.getChiaviStazioniDiArrivo(ambiente, mezzo, partenza);
			itArrivo = chiaviArrivo.iterator();
			
			while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
				
				sa = itArrivo.next();
				//Prendo tute le chiavi di stazioneIntermedia
				chiaviIntermedie = catalogo.getChiaviStazioniIntermedie(ambiente, mezzo, partenza, sa);
				itIntermedie = chiaviIntermedie.iterator();
				
				while(itIntermedie.hasNext()){	//itero tutte le stazioni intermedie;
					
					si = itIntermedie.next();
					//Prendo le info memorizzate in stazione arrivo
					info = catalogo.getInfo(ambiente, mezzo, partenza, sa, si);
					//Metto in stringa le informazioni di questo viaggio;
					unViaggio = componiCatalogo(ambiente, mezzo, partenza,sa,si,info);
					//Lo aggiungo alla lista
					listaViaggi = listaViaggi + unViaggio;
				}

			}
			//ritorna con questa lista
			return listaViaggi;
		}
		
		
		//caso e)
		if (stazioneIntermedia.equals("-----")){
			//non serve iterare le chiavi di ambiente, mezzo, partenza e arrivo, li ho gia da parametro
			chiaviIntermedie = catalogo.getChiaviStazioniIntermedie(ambiente, mezzo, partenza, arrivo);
			itIntermedie = chiaviIntermedie.iterator();
			
			while(itIntermedie.hasNext()){	//itero tutte le stazioni intermedie;
					
				si = itIntermedie.next();
				//Prendo le info memorizzate in stazione arrivo
				info = catalogo.getInfo(ambiente, mezzo, partenza, arrivo, si);
				//Metto in stringa le informazioni di questo viaggio;
				unViaggio = componiCatalogo(ambiente, mezzo, partenza, arrivo,si,info);
				//Lo aggiungo alla lista
				listaViaggi = listaViaggi + unViaggio;
			}

			
			//ritorna con questa lista
			return listaViaggi;
		}
		
		
		//caso f) non serve alcuna iterazione
		
		//Prendo le info memorizzate in stazione intermedia
		info = catalogo.getInfo(ambiente, mezzo, partenza, arrivo, stazioneIntermedia);
		return (componiCatalogo(ambiente,mezzo,partenza,arrivo,stazioneIntermedia,info));
		
	}
	
	private String componiCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String intermedia, String info){
		
		
		String unViaggio;
		
		//Prima l'ambiente
		unViaggio = ambiente;
		unViaggio += "\t"; //aggiungo due tab
		
		//Dopo l'ambiente, il mezzo
		unViaggio += mezzo;
		//Devo giocare con il tab: succede che se metto un elemento con piu' di 13 caratteri, mi zompa il tab!
		unViaggio += "\t";  //Metto un tab
		
		if (mezzo.length()<13){ //Se la lunghezza del mezzo e' minore di 13, metto un altro tab
			unViaggio += "\t";
		}
		
		//Poi la tratta (stazione di partenza : stazione di arrivo -> stazione intermedia)
		unViaggio += partenza + " : " + arrivo + "  ->  " + intermedia;
		unViaggio += "\t";
		
		if (partenza.length()+arrivo.length()+intermedia.length()<24){ //Se la lunghezza della somma di partenza, arrivo e stazione intermedia e' minore di 24, metto un altro tab
			unViaggio += "\t";
		}
	
		unViaggio += info;
		unViaggio += "\n";
		
		return unViaggio;
	}
	
	
	/*
	 * Da Spostare nel controllore Madre il prima possibile
	 */
	
	public void salva() throws SerializzazioneException{
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/articoliSFile.dat";
		SerializzaOggetti articoliSer = new SerializzaOggetti(path);
		articoliSer.serializza(catalogo);
	}
	
	public void carica() throws DeserializzazioneException{
		if (!new File("Save/articoliSFile.dat").exists()) 
		{
			//return false;
			throw new DeserializzazioneException("");
		}
				
		DeserializzaOggetti articoliSer = new DeserializzaOggetti("Save/articoliSFile.dat");
		catalogo =  (Catalogo)articoliSer.deserializza();
	}

}
