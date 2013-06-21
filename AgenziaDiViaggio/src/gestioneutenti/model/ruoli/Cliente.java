package gestioneutenti.model.ruoli;

import gestioneutenti.model.Competenza;

public class Cliente extends AbstractRuolo {
	
	private static Cliente singletonCliente = null;
	
	private static int ID = Ruolo.CLIENTE;
	private static String STRING = "Cliente";
	private static Competenza[] COMPETENZE = null;

	protected Cliente(int id, String string, Competenza[] competenze) {
		super(id, string, competenze);
	}

	public static synchronized Ruolo getInstance() {
		if (singletonCliente == null) {
			singletonCliente = new Cliente(ID, STRING, COMPETENZE);
			return singletonCliente;
		}
		
		return singletonCliente;
	}

}
