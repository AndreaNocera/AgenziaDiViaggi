package ordinaViaggi.entity;


import java.io.Serializable;

public abstract class SubElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8360037147402005199L;
	private Biglietto biglietto;
	private Map map;
	private String info;
	public SubElement(){
		map = null;
		info = null;
	}
	
	public SubElement(Map map){
		this.map = map;
		info = null;
	}
	
	/*SubElement intermedio*/
	public SubElement(Map map, String info){
		this.map = map;
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Biglietto getBiglietto() {
		return biglietto;
	}
	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}
	
}
