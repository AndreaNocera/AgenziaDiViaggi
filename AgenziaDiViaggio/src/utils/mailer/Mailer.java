/**
 * @project WebVoyager
 * 
 * @package utils.mailer
 * 
 * @name Mailer.java
 *
 * @description
 *
 * @author Giacomo Marciani (TEAM 9)
 * 
 */

package utils.mailer;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class Mailer {
	
	private static Mailer singletonMailer = null;
	private static final String USERNAME = "giacomo.marciani.voyager@gmail.com";
	private static final String MAIL = "giacomo.marciani.voyager@gmail.com";
	private static final String PASSWORD = "ispwvoyager";

	private Mailer() {}

	public static synchronized Mailer getInstance() {
		if (singletonMailer == null) {
			singletonMailer = new Mailer();
			return singletonMailer;
		}
		
		return singletonMailer;
	}
	
	public void inviaMail(String mailDestinatario, String oggetto, String messaggio) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		  });
 
		try { 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDestinatario));
			message.setSubject(oggetto);
			message.setText(messaggio); 
			Transport.send(message); 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
