/**
 * 
 */
package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Tratta {

	//attributi di istanza
	private Ambiente ambiente;
	private Mezzo mezzo;
	private Citta partenza;
	private Citta arrivo;
	private Via via;
	
	private Info info;
	
	
	public Tratta(Ambiente ambiente, Mezzo mezzo, Citta partenza, Citta arrivo, Via via){
		this.setAmbiente(ambiente);
		this.setMezzo(mezzo);
		this.setPartenza(partenza);
		this.setArrivo(arrivo);
		this.setVia(via);
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public Citta getPartenza() {
		return partenza;
	}

	public void setPartenza(Citta partenza) {
		this.partenza = partenza;
	}

	public Citta getArrivo() {
		return arrivo;
	}

	public void setArrivo(Citta arrivo) {
		this.arrivo = arrivo;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
}
