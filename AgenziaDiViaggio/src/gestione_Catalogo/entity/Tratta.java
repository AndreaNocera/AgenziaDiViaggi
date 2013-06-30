package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Tratta {
	
	private static Integer ID =0;
	
	//attributi di istanza
	private Ambiente ambiente;
	private Mezzo mezzo;
	private Citta partenza;
	private Citta arrivo;
	private Via via;
	
	private Info info;
	
	private Data dataInserimento;
	
	
	public Tratta(Ambiente ambiente, Mezzo mezzo, Citta partenza, Citta arrivo, Via via, Info info){
		ID+=1;
		
		this.ambiente = ambiente;
		this.mezzo = mezzo;
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.via = via;
		this.setInfo(info);
		
		dataInserimento = new Data();
		
		this.info.updateInfo("--- Inserito il " + dataInserimento.stampaData());
	}

	
	public Ambiente getAmbiente() {
		return ambiente;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public Citta getPartenza() {
		return partenza;
	}

	public Citta getArrivo() {
		return arrivo;
	}

	public Via getVia() {
		return via;
	}

	public String getInfo() {
		return info.toString();
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Data getDataInserimento() {
		return dataInserimento;
	}

	public boolean verifyExistance(Ambiente ambiente, Mezzo mezzo,
			Citta partenza, Citta arrivo, Via via) {
		if (this.ambiente.equals(ambiente) && this.mezzo.equals(mezzo)
				&& this.partenza.equals(partenza)
				&& this.arrivo.equals(arrivo) && this.via.equals(via))
			return true;
		return false;
	}
	
}
