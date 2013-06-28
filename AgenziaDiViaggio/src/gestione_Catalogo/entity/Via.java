/**
 * 
 */
package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Via extends ElementoCatalogo  {
	
	private MappaOfferta mappaOfferta;
	
	public Via(){
		
		mappaCatalogo = null;
		mappaOfferta = new MappaOfferta();
		
		
	}

}
