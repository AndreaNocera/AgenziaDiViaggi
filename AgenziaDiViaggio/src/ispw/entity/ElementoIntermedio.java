package ispw.entity;
/**
 * 
 * @author Gambella Riccardo
 *
 */
public class ElementoIntermedio extends ElementoCatalogo{
	private MapCatalogo<ElementoCatalogo> mapCatalogo;
	
	public ElementoIntermedio() {
		// TODO Auto-generated constructor stub
		super();
		setMapCatalogo(new MapCatalogo<ElementoCatalogo>());
	}
	ElementoIntermedio(Integer id, String valore){
		super(id,valore);
		setMapCatalogo(new MapCatalogo<ElementoCatalogo>());
	}
	public MapCatalogo<ElementoCatalogo> getMapCatalogo() {
		return mapCatalogo;
	}
	public void setMapCatalogo(MapCatalogo<ElementoCatalogo> mapCatalogo) {
		this.mapCatalogo = mapCatalogo;
	}

}
