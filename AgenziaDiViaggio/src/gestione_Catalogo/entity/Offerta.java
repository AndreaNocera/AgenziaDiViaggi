package gestione_Catalogo.entity;

import gestione_Catalogo.dao.OffertaDAO;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class Offerta {
	
	private Integer idOfferta;
	private Integer idTratta;
	private Data dataPartenza;
	private Data dataArrivo;
	private Integer posti;
	

	public Offerta(Integer idTratta, Data dataPartenza, Integer durata, Integer posti) {
		this.idTratta = idTratta;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataPartenza.getNuovaData(durata);
		this.posti = posti;
		
		//inserisco l'offerta appena creata nel db
		OffertaDAO dao = OffertaDAO.getIstanza();
		this.idOfferta = dao.insertAndReturnId(idTratta, dataPartenza, dataPartenza, posti);
	}
	
	
	
	public Offerta(Integer idOfferta, Integer idTratta, Data dataPartenza, Data dataArrivo, Integer posti){
		this.idOfferta = idOfferta;
		this.idTratta = idTratta;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.posti = posti;
	}


	public boolean verifyExistence(Integer idTratta, Integer[] data){
		//serve per verificare se l'offerta da inserire � gia presente
		Data nuovadata = new Data(data[0],data[1],data[2],data[3],data[4]);
		if(this.idTratta.equals(idTratta) &&
		   this.dataPartenza.equals(nuovadata)) return true;
		else return false;
		
	}
	
	
	public boolean verifyExistence(Integer idTratta) {
		//serve per verificare se nella lista delle offerte, c'� almeno un offerta per una particolare tratta
		//evita di eliminare quella tratta
		if (this.idTratta.equals(idTratta))
			return true;
		return false;
	}

	public Integer getIdOfferta(){
		return idOfferta;
	}
	
	public Data getData(){
		return dataPartenza;
	}
	
	public Data getDataArrivo(){
		return dataArrivo;
	}
	
	public Integer getIdTratta(){
		return idTratta;
	}
	
	public Integer getPosti(){
		return posti;
	}
	
}
