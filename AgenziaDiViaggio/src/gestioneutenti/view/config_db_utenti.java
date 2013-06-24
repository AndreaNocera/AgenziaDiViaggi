package gestioneutenti.view;


/**
 * @author Jesus Cevallos
 *config_db_utenti contiene alcune costanti per la configurazione del db. 
 */
public class config_db_utenti {

	public static final String USER_CONNESSIONE = "Any";
	public static final String PASSWORD_CONNESSIONE = "";
	
	public static final String NOME_DB_UTENTI="utenti.sql";
	public static final String NOME_TABELLA_UTENTI="utenti";

	public static final String NOME_DRIVER= "com.mysql.jdbc.Driver";
	public  static final String NOME_URL = "jdbc:mysql://localhost/test?";
	public static final String PERCORSO_COMPLETO_DB = NOME_URL + NOME_DB_UTENTI;

	public static final String COLONNA_USERNAME="username";
	public static final String COLONNA_PASSWORD="password";
	public static final String COLONNA_NOME="nome";
	public static final String COLONNA_COGNOME="cognome";
	public static final String COLONNA_CITTA="citta";
	public static final String COLONNA_NASCITA="nascita";
	public static final String COLONNA_SESSO="sesso";
	public static final String COLONNA_MAIL="mail";
	public static final String COLONNA_RUOLO="ruolo";
	public static final int INDICE_USERNAME=1;
	public static final int INDICE_PASSWORD=2;
	public static final int INDICE_NOME=3;
	public static final int INDICE_COGNOME=4;
	public static final int INDICE_CITTA=5;
	public static final int INDICE_NASCITA=6;
	public static final int INDICE_SESSO=7;
	public static final int INDICE_MAIL=8;
	public static final int INDICE_RUOLO=9;



	public static final String STATEMENT_SALVA="INSERT INTO " + NOME_TABELLA_UTENTI+
			"(username, password, nome, cognome, citta, nascita, sesso, mail, ruolo) VALUES(?,?,?,?,?,?,?,?,?)";

	

} 