package ispw.entity;
/**
 * 
 * @author Gambella Riccardo
 *
 */
public class ElementoFinale extends ElementoCatalogo{
	private MapOfferta mapOfferta;
	public ElementoFinale() {
		// TODO Auto-generated constructor stub
		super();
		setMapOfferta(new MapOfferta());
	}
	public ElementoFinale(Integer id, String valore) {
		// TODO Auto-generated constructor stub
		super(id,valore);
		setMapOfferta(new MapOfferta());
	}
	public MapOfferta getMapOfferta() {
		return mapOfferta;
	}
	public void setMapOfferta(MapOfferta mapOfferta) {
		this.mapOfferta = mapOfferta;
	}
}
