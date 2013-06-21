package gestioneutenti.model.ruoli;

public class RuoloNonEsistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuoloNonEsistenteException() {}

	public RuoloNonEsistenteException(int ruolo) {
		super("Ruolo non esistente : " + ruolo);
	}

}
