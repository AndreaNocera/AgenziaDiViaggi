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
import gestione_Catalogo.exception.TrattaInesistenteException;

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
		if (catalogo.verificaEsistenzaViaggio(ambiente, mezzo, cittaPartenza, cittaArrivo, via)){
			//System.out.println("Viaggio gia' presente");
			throw new TrattaException("Il viaggio e' gia' presente nel catalogo!");
		} else {
			Tratta nuovaTratta = creaTratta(ambiente, mezzo, cittaPartenza, cittaArrivo, via, info);
			//aggiungo il viaggio
			catalogo.aggiungiViaggioAlCatalogo(nuovaTratta);
			log.aggiornaLogAggiungiViaggio(ambiente,mezzo,cittaPartenza,cittaArrivo,via);
		}
	}

	
	public void rimuoviViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) throws TrattaInesistenteException, OffertaException, IDEsternoElementoException {

		// verifico l'esistenza di offerte per il viaggio
		if (catalogo.verificaEsistenzaOfferte(ambiente,mezzo,cittaPartenza,cittaArrivo,via)){
			throw new OffertaException("Ci sono offerte attive! Il viaggio non puo' essere rimosso.");
		} else { 
			Tratta tratta = catalogo.getTrattaByValue(ambiente,mezzo,cittaPartenza,cittaArrivo,via);
			//rimuovo il viaggio
			catalogo.rimuoviViaggioDalCatalogo(tratta);
			log.aggiornaLogRimuoviViaggio(ambiente,mezzo,cittaPartenza,cittaArrivo,via);
		}
		
		//		VERIFICA ESISTENZA OFFERTE TRAMITE LISTA
	/*	Tratta tratta = catalogo.getTrattaByValue(ambiente,mezzo,cittaPartenza,cittaArrivo,via);

		// verifico l'esistenza di offerte per il viaggio
		if (catalogo.verificaEsistenzaOfferte(tratta.getID())){
			throw new OffertaException("Ci sono offerte attive! Il viaggio non puo' essere rimosso.");
		} else { //rimuovo il viaggio
			catalogo.rimuoviViaggioDalCatalogo(tratta);
			log.aggiornaLogRimuoviViaggio(ambiente,mezzo,cittaPartenza,cittaArrivo,via);
		}
	*/	
	}
	

	
	public String mostraCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via) throws MappaException, IDEsternoElementoException, TrattaInesistenteException {
		
		/*
		 * Ho ben 6 casi ...
		 * Caso a) Ambiente = ----- , devo mostrare tutto il catalogo
		 * Caso b) Mezzo = -----, devo mostrare tutti i viaggi aventi tutti lo stesso ambiente
		 * Caso c) Partenza = -----, devo mostrare tutti i viaggio aventi tutti lo stesso mezzo
		 * Caso d) Arrivo = -----, devo mostrare tutti i viaggi aventi stesso mezzo e stessa stazione di partenza
		 * Caso e) Via = -----, devo mostrare tutti i viaggi aventi stesso mezzo, stessa stazione di partenza e stessa stazione di arrivo
		 * Caso f) Il viaggio e' composto, verra' visualizzato solo un viaggio...
		 */
		
		Set<String> chiaviAmbiente;
		Set<String> chiaviMezzo;
		Set<String> chiaviPartenza;
		Set<String> chiaviArrivo;
		Set<String> chiaviVia;
		
		Iterator<String>	itAmbiente;		
		Iterator<String>	itMezzo;
		Iterator<String>	itPartenza;
		Iterator<String>	itArrivo;
		Iterator<String>	itVia;
		
		String amb;
		String mez;
		String cp;
		String ca;
		String v;
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
				chiaviMezzo = catalogo.getChiaviMezzi(amb);
				itMezzo = chiaviMezzo.iterator();
				
				while(itMezzo.hasNext()){	//itero tutte le chiavi mezzo
					
					mez = itMezzo.next();
					//Prendo tutte le chiavi di stazionePartenza
					chiaviPartenza = catalogo.getChiaviCittaDiPartenza(amb, mez);
					itPartenza = chiaviPartenza.iterator();
					
					while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
						
						cp = itPartenza.next();
						//Prendo tute le chiavi di stazioneArrivo
						chiaviArrivo = catalogo.getChiaviCittaDiArrivo(amb, mez, cp);
						itArrivo = chiaviArrivo.iterator();
						
						while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
							
							ca = itArrivo.next();
							//Prendo tute le chiavi di via
							chiaviVia = catalogo.getChiaviVia(amb, mez, cp, ca);
							itVia = chiaviVia.iterator();
							
							while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
								
								v = itVia.next();

								//Prendo le info memorizzate in tratta
								info = catalogo.getTrattaByValue(amb,mez,cp,ca,v).getInfo();
								
								//Metto in stringa le informazioni di questo viaggio;
								unViaggio = componiCatalogo(amb,mez,cp,ca,v,info);
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
			chiaviMezzo = catalogo.getChiaviMezzi(ambiente);
			itMezzo = chiaviMezzo.iterator();
			
			while(itMezzo.hasNext()){	//itero tutte le chiavi mezzo
				
				mez = itMezzo.next();
				//Prendo tutte le chiavi di stazionePartenza
				chiaviPartenza = catalogo.getChiaviCittaDiPartenza(ambiente, mez);
				itPartenza = chiaviPartenza.iterator();
				
				while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
					
					cp = itPartenza.next();
					//Prendo tute le chiavi di stazioneArrivo
					chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mez, cp);
					itArrivo = chiaviArrivo.iterator();
					
					while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
						
						ca = itArrivo.next();
						//Prendo tute le chiavi di via
						chiaviVia = catalogo.getChiaviVia(ambiente, mez, cp, ca);
						itVia = chiaviVia.iterator();
						
						while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
							
							v = itVia.next();

							//Prendo le info memorizzate in tratta
							info = catalogo.getTrattaByValue(ambiente,mez,cp,ca,v).getInfo();
							
							//Metto in stringa le informazioni di questo viaggio;
							unViaggio = componiCatalogo(ambiente,mez,cp,ca,v,info);
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
			chiaviPartenza = catalogo.getChiaviCittaDiPartenza(ambiente, mezzo);
			itPartenza = chiaviPartenza.iterator();
			
			while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
				
				cp = itPartenza.next();
				//Prendo tute le chiavi di stazioneArrivo
				chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mezzo, cp);
				itArrivo = chiaviArrivo.iterator();
				
				while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
					
					ca = itArrivo.next();
					//Prendo tute le chiavi di via
					chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, cp, ca);
					itVia = chiaviVia.iterator();
					
					while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
						
						v = itVia.next();

						//Prendo le info memorizzate in tratta
						info = catalogo.getTrattaByValue(ambiente,mezzo,cp,ca,v).getInfo();

						//Metto in stringa le informazioni di questo viaggio;
						unViaggio = componiCatalogo(ambiente, mezzo,cp,ca,v,info);
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
			chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mezzo, partenza);
			itArrivo = chiaviArrivo.iterator();
			
			while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
				
				ca = itArrivo.next();
				//Prendo tute le chiavi di via
				chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, partenza, ca);
				itVia = chiaviVia.iterator();
				
				while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
					
					v = itVia.next();

					//Prendo le info memorizzate in tratta
					info = catalogo.getTrattaByValue(ambiente,mezzo,partenza,ca,v).getInfo();

					//Metto in stringa le informazioni di questo viaggio;
					unViaggio = componiCatalogo(ambiente, mezzo, partenza,ca,v,info);
					//Lo aggiungo alla lista
					listaViaggi = listaViaggi + unViaggio;
				}

			}
			//ritorna con questa lista
			return listaViaggi;
		}
		
		
		//caso e)
		if (via.equals("-----")){
			//non serve iterare le chiavi di ambiente, mezzo, partenza e arrivo, li ho gia da parametro
			chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, partenza, arrivo);
			itVia = chiaviVia.iterator();
			
			while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
					
				v = itVia.next();

				//Prendo le info memorizzate in tratta
				info = catalogo.getTrattaByValue(ambiente,mezzo,partenza,arrivo,v).getInfo();

				//Metto in stringa le informazioni di questo viaggio;
				unViaggio = componiCatalogo(ambiente, mezzo, partenza, arrivo,v,info);
				//Lo aggiungo alla lista
				listaViaggi = listaViaggi + unViaggio;
			}

			
			//ritorna con questa lista
			return listaViaggi;
		}
		
		
		//caso f) non serve alcuna iterazione
		//Prendo le info memorizzate in tratta
		info = catalogo.getTrattaByValue(ambiente,mezzo,partenza,arrivo,via).getInfo();

		return (componiCatalogo(ambiente,mezzo,partenza,arrivo,via,info));
		
	}
	
	private String componiCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via, String info){
		
		
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
		
		//Poi la tratta (stazione di partenza : stazione di arrivo -> via)
		unViaggio += partenza + " : " + arrivo + "  ->  " + via;
		unViaggio += "\t";
		
		if (partenza.length()+arrivo.length()+via.length()<24){ //Se la lunghezza della somma di partenza, arrivo e via e' minore di 24, metto un altro tab
			unViaggio += "\t";
		}
	
		unViaggio += info;
		unViaggio += "\n";
		
		return unViaggio;
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