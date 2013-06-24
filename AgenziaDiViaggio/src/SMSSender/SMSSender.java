/*
 * Autori: Luca Paoli e Jessica Lucia
 * 
 */

/*Libreria SMS Trend scaricata da: http://www.inviosmstrend.it/sdk/sms_gateway_java_ita.zip*/

package SMSSender;

import com.oe.sdk.connection.SMSCConnection;
import com.oe.sdk.connection.SMSCConnectionFactory;
import com.oe.sdk.exceptions.SMSCException;
import com.oe.sdk.model.SMS;
import com.oe.sdk.model.SMSType;

/*Il file jar contenete la libreria dava problemi così i relativi sorgenti sono stati inclusi nel progetto!*/

public class SMSSender{
	/*Credenziali di accesso al servizio SMS Trend*/
	
	private final static String username = "user";
	private final static String password = "password";
	
	private SMSSender(){}
	
	public static void inviaSMS(String numero, String testo) throws SMSCException
	{
		//host e porto si trovano nel file di configurazione "sdk.properties" del progetto!
		
		SMSCConnection connection = SMSCConnectionFactory.openConnection(username, password);
		
		SMS sms = new SMS();
		
		sms.setSms_type(SMSType.SILVER);			//tipo di servizio di invio di SMS (silver, gold, gold plus)
		sms.addSmsRecipient(numero);				//aggiunge il destinatario (numero di telefono)
		sms.setMessage(testo);						//aggiunge il testo del messaggio
		sms.setImmediate();							//specifica di inviare subito l'SMS
		
		connection.sendSMS(sms);					//invia l'SMS
		connection.logout();						//chiude la connessione
	}
}