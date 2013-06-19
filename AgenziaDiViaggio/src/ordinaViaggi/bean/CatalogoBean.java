package ordinaViaggi.bean;

import java.io.Serializable;

public class CatalogoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String ambiente;
	private String mezzo;
	private String cittaPartenza;
	private String cittaArrivo;
	private String via;

	public CatalogoBean() {
		System.out.println("Istanziato bean!!");
		setId("");
		setAmbiente("");
		setMezzo("");
		setCittaPartenza("");
		setCittaArrivo("");
		setVia("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getMezzo() {
		return mezzo;
	}

	public void setMezzo(String mezzo) {
		this.mezzo = mezzo;
	}

	public String getCittaPartenza() {
		return cittaPartenza;
	}

	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public String getCittaArrivo() {
		return cittaArrivo;
	}

	public void setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}
	
	public boolean validate() {
		// Controllo sintattico
		if (this.id.equals("") || 
			this.ambiente.equals("") ||
			this.mezzo.equals("") ||
			this.cittaPartenza.equals("") ||
			this.cittaArrivo.equals("") ||
			this.via.equals(""))
			{
			return false;
		}
		return true;
	}

}
