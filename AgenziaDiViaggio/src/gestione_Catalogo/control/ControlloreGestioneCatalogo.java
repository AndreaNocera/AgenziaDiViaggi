package gestione_Catalogo.control;

import gestione_Catalogo.exception.IDEsternoElementoException;
import gestione_Catalogo.exception.MappaException;

import java.util.Iterator;
import java.util.Set;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class ControlloreGestioneCatalogo extends Controllore {
	
	//costruttore
	public ControlloreGestioneCatalogo(){
		super();
	}
	
	
	//metodi
	public void aggiungiViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via, String info) {
		
	}

	
	public void rimuoviViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) {
		
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
	
	
	public String mostraCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via) throws MappaException, IDEsternoElementoException{
		
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
		String sp;
		String sa;
		String si;
		String info;
		
		String listaViaggi = "";
		String unViaggio;
/*
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
						
						sp = itPartenza.next();
						//Prendo tute le chiavi di stazioneArrivo
						chiaviArrivo = catalogo.getChiaviCittaDiArrivo(amb, mez, sp);
						itArrivo = chiaviArrivo.iterator();
						
						while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
							
							sa = itArrivo.next();
							//Prendo tute le chiavi di via
							chiaviVia = catalogo.getChiaviVia(amb, mez, sp, sa);
							itVia = chiaviVia.iterator();
							
							while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
								
								si = itVia.next();
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
			chiaviMezzo = catalogo.getChiaviMezzi(ambiente);
			itMezzo = chiaviMezzo.iterator();
			
			while(itMezzo.hasNext()){	//itero tutte le chiavi mezzo
				
				mez = itMezzo.next();
				//Prendo tutte le chiavi di stazionePartenza
				chiaviPartenza = catalogo.getChiaviCittaDiPartenza(ambiente, mez);
				itPartenza = chiaviPartenza.iterator();
				
				while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
					
					sp = itPartenza.next();
					//Prendo tute le chiavi di stazioneArrivo
					chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mez, sp);
					itArrivo = chiaviArrivo.iterator();
					
					while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
						
						sa = itArrivo.next();
						//Prendo tute le chiavi di via
						chiaviVia = catalogo.getChiaviVia(ambiente, mez, sp, sa);
						itVia = chiaviVia.iterator();
						
						while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
							
							si = itVia.next();
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
			chiaviPartenza = catalogo.getChiaviCittaDiPartenza(ambiente, mezzo);
			itPartenza = chiaviPartenza.iterator();
			
			while(itPartenza.hasNext()){   //itero tutte le chiavi partenza
				
				sp = itPartenza.next();
				//Prendo tute le chiavi di stazioneArrivo
				chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mezzo, sp);
				itArrivo = chiaviArrivo.iterator();
				
				while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
					
					sa = itArrivo.next();
					//Prendo tute le chiavi di via
					chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, sp, sa);
					itVia = chiaviVia.iterator();
					
					while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
						
						si = itVia.next();
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
			chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mezzo, partenza);
			itArrivo = chiaviArrivo.iterator();
			
			while(itArrivo.hasNext()){	//itero tutte le stazioni di arrivo;
				
				sa = itArrivo.next();
				//Prendo tute le chiavi di via
				chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, partenza, sa);
				itVia = chiaviVia.iterator();
				
				while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
					
					si = itVia.next();
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
		if (via.equals("-----")){
			//non serve iterare le chiavi di ambiente, mezzo, partenza e arrivo, li ho gia da parametro
			chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, partenza, arrivo);
			itVia = chiaviVia.iterator();
			
			while(itVia.hasNext()){	//itero tutte le stazioni intermedie;
					
				si = itVia.next();
				//Prendo le info memorizzate in stazione arrivo
				info = catalogo.getInfo(ambiente, mezzo, partenza, arrivo, si);
				//Metto in stringa le informazioni di questo viaggio;
				unViaggio = componiCatalogo(ambiente, mezzo, partenza, arrivo,si,info);
				//Lo aggiungo alla lista
				listaViaggi = listaViaggi + unViaggio;
			}

	*/		
			//ritorna con questa lista
			return listaViaggi;
	/*	}
		
		
		//caso f) non serve alcuna iterazione
		
		//Prendo le info memorizzate in stazione intermedia
		info = catalogo.getInfo(ambiente, mezzo, partenza, arrivo, via);
		return (componiCatalogo(ambiente,mezzo,partenza,arrivo,via,info));
	*/	
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
	
	
}
