package gestione_Catalogo.entity;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Tratta {

	
	//attributi di istanza
	private Integer ID;
	
	private Ambiente ambiente;
	private Mezzo mezzo;
	private Citta partenza;
	private Citta arrivo;
	private Via via;
	
	private Info info;
	
	private Data dataInserimento;
	
	
	public Tratta(Ambiente ambiente, Mezzo mezzo, Citta partenza, Citta arrivo, Via via, Info info){
		
		this.ambiente = ambiente;
		this.mezzo = mezzo;
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.via = via;
		this.setInfo(info);
		
		dataInserimento = new Data();
		
		this.info.updateInfo("--- Inserito il " + dataInserimento.stampaData());
	}

	
	public Tratta(Integer ID, Ambiente ambiente, Mezzo mezzo, Citta partenza, Citta arrivo, Via via, Info info, Data dataInserimento){
		
		this.setID(ID);
		
		this.ambiente = ambiente;
		this.mezzo = mezzo;
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.via = via;
		this.setInfo(info);
		
		this.dataInserimento = dataInserimento;

	}

	
	public Integer getID() {
		return ID;
	}


	public void setID(Integer iD) {
		ID = iD;
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
