/**
 * 
 */
package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Mezzo extends ElementoCatalogo {
	
	private static int nextID = 1; 
	
	public Mezzo(IDEsternoElemento idEsternoElemento){
		super(nextID, idEsternoElemento);
		nextID++;
	}

}
