package gestioneOfferta.entity;

import gestione_Catalogo.exception.IDEsternoElementoException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class ListaOfferte {
	
	private static final String persistencyName = "listaofferte";
	private static Mappa mappaOfferte;
	private static ArrayList<? extends Elemento> listaOfferte;
	
	public void inizializza() {
			if (mappaOfferte == null) {
				mappaOfferte = new Mappa();
			}
			if (listaOfferte == null) {
				listaOfferte = new ArrayList<Elemento>();
			}
			
			//ora bisogna popolare tutto questo interpellando la persistenza.
	}
	
	public void aggiungiOfferta(Offerta offerta) { 
		try {
			mappaOfferte.addElemento(offerta.getIdViaggio(), null);
			mappaOfferte.getElemento(offerta.getIdViaggio().aggiungiElemento(offerta.getIdOfferta(), offerta);
		} catch (IDEsternoElementoException e) { e.printStackTrace(); }
	}
	
	public void rimuoviViaggioInOfferta(IDEsterno id) {
		try {
			mappaOfferte.removeElemento(id);
		} catch (IDEsternoElementoException e) { e.printStackTrace(); }
	}
	
	public boolean esistenzaOfferta(IDEsterno idViaggio, IDEsterno idOfferta) {
		if (mappaOfferte.containsKey(idViaggio)) {
			try {
				if (mappaOfferte.getElemento(idViaggio).getElemento(idOfferta) != null ) {
					return true;
				}
			} catch (IDEsternoElementoException e) { e.printStackTrace(); }
		}
		return false;
	}
	
	public boolean esistenzaViaggioInOfferta(IDEsterno idViaggio) {
		return mappaOfferte.esistenzaElemento(new Offerta(idViaggio,null,null,0 , 0, null));
	}
	
	public static String getPersistencyName() {
		return persistencyName;
	}
	
	
	
}
