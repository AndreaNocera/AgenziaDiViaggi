package voyager.nove.control;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Set;

import voyager.nove.exception.IDEsternoElementoException;
import voyager.nove.exception.MappaException;
import voyager.nove.exception.OffertaException;
import voyager.nove.exception.OffertaInesistenteException;
import voyager.nove.exception.OfferteNonPresentiException;
import voyager.nove.exception.PrenotazioneException;
import voyager.nove.exception.TrattaException;
import voyager.nove.exception.TrattaInesistenteException;
import voyager.nove.model.viaggio.Ambiente;
import voyager.nove.model.viaggio.Catalogo;
import voyager.nove.model.viaggio.Citta;
import voyager.nove.model.viaggio.Mezzo;
import voyager.nove.model.viaggio.Offerta;
import voyager.nove.model.viaggio.Tratta;
import voyager.nove.model.viaggio.Via;
import voyager.nove.model.viaggio.basic.Data;
import voyager.nove.model.viaggio.basic.IDEsternoElemento;
import voyager.nove.model.viaggio.basic.Info;

public class ControllerGestioneCatalogo {
	
	private static ControllerGestioneCatalogo singletonControllerGestioneCatalogo;
	
	static Catalogo catalogo;

	private ControllerGestioneCatalogo() {
		catalogo = Catalogo.getIstanza();
	}

	public static synchronized ControllerGestioneCatalogo getInstance() {
		if (singletonControllerGestioneCatalogo == null) {
			singletonControllerGestioneCatalogo = new ControllerGestioneCatalogo();
		}
		
		return singletonControllerGestioneCatalogo;
	}
	
	public void aggiungiViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via, String info) throws TrattaException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IDEsternoElementoException {
		if (via.equals("")) via = Via.DIRETTO;
		
		if (catalogo.verificaEsistenzaViaggio(ambiente, mezzo, cittaPartenza, cittaArrivo, via)){
			throw new TrattaException("Il viaggio e' gia' presente nel catalogo!");
		} else {
			Tratta nuovaTratta = creaTratta(ambiente, mezzo, cittaPartenza, cittaArrivo, via, info);
			catalogo.aggiungiViaggioAlCatalogo(nuovaTratta);
		}
	}
	
	public void rimuoviViaggio(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via) throws TrattaInesistenteException, OffertaException, IDEsternoElementoException {

		if (catalogo.verificaEsistenzaOfferte(ambiente,mezzo,cittaPartenza,cittaArrivo,via)){
			throw new OffertaException("Ci sono offerte attive! Il viaggio non puo' essere rimosso.");
		} else { 
			Tratta tratta = catalogo.getTrattaByValue(ambiente,mezzo,cittaPartenza,cittaArrivo,via);
			catalogo.rimuoviViaggioDalCatalogo(tratta);
		}	
	}
	
	public String mostraCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via) throws MappaException, IDEsternoElementoException, TrattaInesistenteException {
		
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

		if (ambiente.equals("-----")){
			
			chiaviAmbiente = catalogo.getChiaviAmbienti();
			itAmbiente = chiaviAmbiente.iterator();
			
			while(itAmbiente.hasNext()){  
				
				amb = itAmbiente.next();
				chiaviMezzo = catalogo.getChiaviMezzi(amb);
				itMezzo = chiaviMezzo.iterator();
				
				while(itMezzo.hasNext()){	
					
					mez = itMezzo.next();
					chiaviPartenza = catalogo.getChiaviCittaDiPartenza(amb, mez);
					itPartenza = chiaviPartenza.iterator();
					
					while(itPartenza.hasNext()){   
						
						cp = itPartenza.next();
						chiaviArrivo = catalogo.getChiaviCittaDiArrivo(amb, mez, cp);
						itArrivo = chiaviArrivo.iterator();
						
						while(itArrivo.hasNext()){	
							
							ca = itArrivo.next();
							chiaviVia = catalogo.getChiaviVia(amb, mez, cp, ca);
							itVia = chiaviVia.iterator();
							
							while(itVia.hasNext()){	
								
								v = itVia.next();

								info = catalogo.getTrattaByValue(amb,mez,cp,ca,v).getInfo();
								
								unViaggio = componiCatalogo(amb,mez,cp,ca,v,info);
								listaViaggi = listaViaggi + unViaggio;
							}
			
						}
					}
				}
			}
			
			return listaViaggi;
		}		
		
		if (mezzo.equals("-----")){
			
			chiaviMezzo = catalogo.getChiaviMezzi(ambiente);
			itMezzo = chiaviMezzo.iterator();
			
			while(itMezzo.hasNext()){	
				
				mez = itMezzo.next();
				chiaviPartenza = catalogo.getChiaviCittaDiPartenza(ambiente, mez);
				itPartenza = chiaviPartenza.iterator();
				
				while(itPartenza.hasNext()){   
					
					cp = itPartenza.next();
					chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mez, cp);
					itArrivo = chiaviArrivo.iterator();
					
					while(itArrivo.hasNext()){	
						
						ca = itArrivo.next();
						chiaviVia = catalogo.getChiaviVia(ambiente, mez, cp, ca);
						itVia = chiaviVia.iterator();
						
						while(itVia.hasNext()){	
							
							v = itVia.next();

							info = catalogo.getTrattaByValue(ambiente,mez,cp,ca,v).getInfo();
							
							unViaggio = componiCatalogo(ambiente,mez,cp,ca,v,info);
							listaViaggi = listaViaggi + unViaggio;
						}
		
					}
				}
			}
			
			return listaViaggi;
		}	
		
		if (partenza.equals("-----")){
			
			chiaviPartenza = catalogo.getChiaviCittaDiPartenza(ambiente, mezzo);
			itPartenza = chiaviPartenza.iterator();
			
			while(itPartenza.hasNext()){  
				
				cp = itPartenza.next();
				chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mezzo, cp);
				itArrivo = chiaviArrivo.iterator();
				
				while(itArrivo.hasNext()){
					
					ca = itArrivo.next();
					chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, cp, ca);
					itVia = chiaviVia.iterator();
					
					while(itVia.hasNext()){	
						
						v = itVia.next();

						info = catalogo.getTrattaByValue(ambiente,mezzo,cp,ca,v).getInfo();

						unViaggio = componiCatalogo(ambiente, mezzo,cp,ca,v,info);
						listaViaggi = listaViaggi + unViaggio;
					}
	
				}
			}
			
			return listaViaggi;
		}		
		
		if (arrivo.equals("-----")){
			chiaviArrivo = catalogo.getChiaviCittaDiArrivo(ambiente, mezzo, partenza);
			itArrivo = chiaviArrivo.iterator();
			
			while(itArrivo.hasNext()){	
				
				ca = itArrivo.next();
				chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, partenza, ca);
				itVia = chiaviVia.iterator();
				
				while(itVia.hasNext()){	
					
					v = itVia.next();

					info = catalogo.getTrattaByValue(ambiente,mezzo,partenza,ca,v).getInfo();

					unViaggio = componiCatalogo(ambiente, mezzo, partenza,ca,v,info);
					listaViaggi = listaViaggi + unViaggio;
				}

			}
			
			return listaViaggi;
		}	
		
		if (via.equals("-----")){
			chiaviVia = catalogo.getChiaviVia(ambiente, mezzo, partenza, arrivo);
			itVia = chiaviVia.iterator();
			
			while(itVia.hasNext()){
					
				v = itVia.next();

				info = catalogo.getTrattaByValue(ambiente,mezzo,partenza,arrivo,v).getInfo();

				unViaggio = componiCatalogo(ambiente, mezzo, partenza, arrivo,v,info);
				listaViaggi = listaViaggi + unViaggio;
			}

			return listaViaggi;
		}
		
		info = catalogo.getTrattaByValue(ambiente,mezzo,partenza,arrivo,via).getInfo();

		return (componiCatalogo(ambiente,mezzo,partenza,arrivo,via,info));
		
	}
	
	private String componiCatalogo(String ambiente, String mezzo, String partenza, String arrivo, String via, String info){
		
		String unViaggio;
		
		unViaggio = ambiente;
		unViaggio += "\t"; 
		
		unViaggio += mezzo;
		unViaggio += "\t";  
		
		if (mezzo.length()<13){
			unViaggio += "\t";
		}
		
		unViaggio += partenza + " : " + arrivo + "  ->  " + via;
		unViaggio += "\t";
		
		if (partenza.length()+arrivo.length()+via.length()<24){ 
			unViaggio += "\t";
		}
	
		unViaggio += info;
		unViaggio += "\n";
		
		return unViaggio;
	}	
	
	private Tratta creaTratta(String ambiente, String mezzo, String cittaPartenza, String cittaArrivo, String via, String info) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

		Class<?> c = Class.forName("gestione_Catalogo.entity."+ambiente);   
		
		Class<?> primoParametro	 = Class.forName("gestione_Catalogo.entity.IDEsternoElemento");
		
		Class<?>[] parametri = {primoParametro};
		
		Constructor<?> costruttore = c.getConstructor(parametri);
		
		Ambiente a = (Ambiente) costruttore.newInstance(new IDEsternoElemento(ambiente));

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
