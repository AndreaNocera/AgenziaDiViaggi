/*
 * Autori: Luca Paoli, Jessica Lucia
 * 
*/

package ispw.MailSender;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender{
	private static final String server_host = "mail.smtp.host";			//da modificare
	private static final String nome_server = "my-server";					//da modificare
	private static final String mail_sorgente = "voyager@mymail.com";		//da modificare
	private static final String oggetto_mail = "informazioni da Voyager";	//da modificare
	//private final String tipo_mime = "text/plain";
	
	private MailSender(){}
	
	public static void inviaMail(String mail_destinatario, String testo)
	throws MessagingException, IOException{
		if(mail_destinatario == null || mail_destinatario.replaceAll(" ", "").compareTo("") == 0)
			throw new IOException("Destinatario non valido!");
		
		if(testo == null || testo.replaceAll(" ", "").compareTo("") == 0)
			throw new IOException("Testo non valido!");
		
		Properties props = new Properties();							//servono per gestire la sessione
	    
		props.put(server_host, nome_server);
	    
	    Session session = Session.getInstance(props);					//ottiene la sessione in base alle proprietà settate in precedenza

        MimeMessage msg = new MimeMessage(session);						//istanziazione del messaggio
        
        msg.setFrom(new InternetAddress(mail_sorgente));				//setta la sorgente
        msg.setRecipients(Message.RecipientType.TO, mail_destinatario);	//setta il destinatario
        msg.setSubject(oggetto_mail);									//setta l'oggetto della mail
        msg.setSentDate(new Date());									//data di spedizione
        msg.setText(testo);												//testo del messaggio
        
        Transport.send(msg);											//invia la mail
	}
}