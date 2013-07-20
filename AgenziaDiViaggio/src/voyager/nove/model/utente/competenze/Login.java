/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.competenze
 * 
 * @name Login.java
 *
 * @description
 *
 * @author TEAM 9: Giacomo Marciani, Jesus Cevallos, Ilyas Aboki, Ludovic William
 * 
 */

package voyager.nove.model.utente.competenze;


public class Login extends AbstractCompetenza {
	
	private static final long serialVersionUID = 1L;

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
