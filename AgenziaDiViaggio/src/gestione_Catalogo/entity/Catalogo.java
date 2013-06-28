/**
 * 
 */
package gestione_Catalogo.entity;

import java.util.ArrayList;

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
		
	}
	
	// metodi
	public static Catalogo getIstanza(){
		if (istanza == null){
			istanza = new Catalogo();
		}
		return istanza;
	}
}

