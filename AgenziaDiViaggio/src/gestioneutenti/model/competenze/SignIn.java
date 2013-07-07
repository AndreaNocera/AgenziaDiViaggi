/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.competenze
 * 
 * @name SignIn.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package gestioneutenti.model.competenze;

public class SignIn extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

	private static SignIn singletonLogin;
	
	private static int ID = Competenza.LOGIN;
	private static String STRING = "Logout";

	protected SignIn(int id, String string) {
		super(id, string);
	}
	
	public static synchronized SignIn getInstance() {
		if (singletonLogin == null) {
			singletonLogin = new SignIn(ID, STRING);
			return singletonLogin;
		}
		
		return singletonLogin;
	}

}
