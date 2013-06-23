package gestioneutenti.model.competenze;

public class Login extends AbstractCompetenza {
	
	private static Login singletonLogin;
	
	private static int ID = Competenza.LOGIN;
	private static String STRING = "Logout";

	protected Login(int id, String string) {
		super(id, string);
	}
	
	public static synchronized Login getInstance() {
		if (singletonLogin == null) {
			singletonLogin = new Login(ID, STRING);
			return singletonLogin;
		}
		
		return singletonLogin;
	}

}
