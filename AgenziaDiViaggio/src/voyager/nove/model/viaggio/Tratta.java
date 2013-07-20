package voyager.nove.model.viaggio;

import voyager.nove.model.viaggio.basic.Data;
import voyager.nove.model.viaggio.basic.Info;
import voyager.nove.persistence.dao.TrattaDAO;

public class Tratta {

	private Integer ID;
	
	private Ambiente ambiente;
	private Mezzo mezzo;
	private Citta partenza;
	private Citta arrivo;
	private Via via;
	
	private Info info;
	
	private Data dataInserimento;
	
	
	public Tratta(Ambiente ambiente, Mezzo mezzo, Citta partenza, Citta arrivo, Via via, Info info){
		
		TrattaDAO dao = TrattaDAO.getIstanza();
		ID = dao.getNextId();
		this.ambiente = ambiente;
		this.mezzo = mezzo;
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.via = via;
		this.info = info;
		dataInserimento = new Data();
		
		this.info.updateInfo("--- Inserito il " + dataInserimento.stampaData());
		
		dao.insert(this);
		
	}

	
	public Tratta(Integer ID, Ambiente ambiente, Mezzo mezzo, Citta partenza, Citta arrivo, Via via, Info info, Data dataInserimento){
		
		this.ID = ID;
		this.ambiente = ambiente;
		this.mezzo = mezzo;
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.via = via;
		this.info = info;
		
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
	
	public boolean verifyExistence(String ambiente, String mezzo, String partenza, String arrivo, String via) {
		if (this.ambiente.getIDEsternoElemento().equals(ambiente) && this.mezzo.getIDEsternoElemento().equals(mezzo)
				&& this.partenza.getIDEsternoElemento().equals(partenza) && this.arrivo.getIDEsternoElemento().equals(arrivo) 
				&& this.via.getIDEsternoElemento().equals(via))
			return true;
		return false;
	}
	
}
