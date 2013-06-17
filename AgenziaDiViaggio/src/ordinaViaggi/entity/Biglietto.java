package ordinaViaggi.entity;

import java.io.Serializable;
import java.util.List;

public class Biglietto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3821594773375207599L;
	private List<Traveler> listTravelers;
	public Biglietto(List<Traveler> listTravelers){
		this.listTravelers = listTravelers;
	}
	
	public List<Traveler> getTravelers() {
		return listTravelers;
	}
	public void setTraveler(List<Traveler> listTravelers) {
		this.listTravelers = listTravelers;
	}
	
}
