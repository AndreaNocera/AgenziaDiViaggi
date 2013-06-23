package gestioneutenti.exception;

public class RuoloException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuoloException() {}

	public RuoloException(int ruolo) {
		super("Ruolo non esistente : " + ruolo);
	}

}
