package br.com.emailproject.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.emailproject.model.Email;
import br.com.emailproject.util.LogUtil;

@Stateless
public class EmailService extends Thread {
    
	private List<Email> emails=new ArrayList<>();
	public static final String HEADER_CONTEXT="text/html; charset=UTF-8";
	
	public void enviarEmail(Email email) {
		emails.add(email);
		this.send();
	}
	
	public void enviarEmail(List<Email> email) {
		email.forEach(e->emails.add(e));
		this.send();
	}
	
	private EmailService copy() {
		EmailService emailService=new EmailService();
		emailService.emails=emails;
		return emailService;
	}
	
	private void send() {
	   new Thread(this.copy()).start();
	}
	
	@Override
	public void run() {
	   Properties prop=new Properties();
	   prop.put("mail.smtp.starttls.enable","true");
	   prop.put("mail.smtp.host",System.getProperty("email-project.mail.smtp.host"));
	   prop.put("mail.smtp.port",System.getProperty("email-project.mail.smtp.port"));
	   
	   Session session=Session.getInstance(prop);
	   session.setDebug(false);
	   
	   for(Email email:emails) {
		   try {
			   Message message=new MimeMessage(session);
			   message.setFrom(new InternetAddress(System.getProperty("email-project.mail.smtp.from"),"Michael Soares"));
			   
			   if(email.getDestinatario().contains("/")) {
				   List<InternetAddress> emailsLocal=new ArrayList<>();
				   for (String e :email.getDestinatario().split("/")) {
                       emailsLocal.add(new InternetAddress(e));
				   }
				   message.addRecipients(Message.RecipientType.TO,emailsLocal.toArray(new InternetAddress[0]));
			   }else {
				   InternetAddress para=new InternetAddress(email.getDestinatario());
				   message.addRecipient(Message.RecipientType.TO, para);
			   }
			   
			   message.setSubject(email.getAssunto());
			   MimeBodyPart textPart=new MimeBodyPart();
			   textPart.setHeader("Content-Type",HEADER_CONTEXT);
			   textPart.setContent(email.getCorpoDoEmail(),HEADER_CONTEXT);
			   
			   Multipart mp=new MimeMultipart();
			   mp.addBodyPart(textPart);
			   message.setContent(mp);
			   
			   Transport.send(message);
			   
			}catch (MessagingException | UnsupportedEncodingException e) {
				LogUtil.getLogger(EmailService.class).error("Erro ao enviar e-mail! "+e.getMessage());
			}    
	   }
	   
	}
	
	
}
