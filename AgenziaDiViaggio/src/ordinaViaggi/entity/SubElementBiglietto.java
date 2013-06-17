package ordinaViaggi.entity;


public class SubElementBiglietto extends SubElement{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7663215606192945304L;
	private Biglietto biglietto;
	/*Subelement finale*/
	public SubElementBiglietto(Map map, String info, Biglietto biglietto){
		super(map,info);
		this.biglietto = biglietto;
	}
	
	public SubElementBiglietto(Map map, String info){
		super(map,info);
		this.biglietto = null;
	}
	
	public SubElementBiglietto(Map map, Biglietto biglietto){
		super(map);
		this.biglietto = biglietto;
	}
	
	public Biglietto getBiglietto() {
		return biglietto;
	}
	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}
}
