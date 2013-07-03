package gestione_Catalogo.entity;

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
	}


	public boolean verifyExistence(Integer idOfferta, Integer[] data){
		return false;
		
	}
	
	
	public boolean verifyExistence(Integer idTratta) {
		if (this.idTratta.equals(idTratta))
			return true;
		return false;
	}

	public Integer getIdOfferta(){
		return idOfferta;
	}
	
}
