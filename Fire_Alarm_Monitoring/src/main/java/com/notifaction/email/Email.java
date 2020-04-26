package com.notifaction.email;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.mail.Session; 
import javax.mail.Transport; 
  
  
public class Email  
{ 
    // email ID of Recipient. 
    public static final String recipient = "it18107388@my.sliit.lk"; 
    // email ID of  Sender. 
    public static final String sender = "urbanrunes@gmail.com"; 
    public static final String host = "smtp.gmail.com"; 
	
	public static void sendMail() {
		// Getting system properties 
	      Properties properties = System.getProperties(); 
	  
	      // Setting up mail server 
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", "465");
	      properties.put("mail.smtp.ssl.enable", "true");
	      properties.put("mail.smtp.auth", "true");
	      
	      // creating session object to get properties 
	      Session session = Session.getDefaultInstance(properties, new Authenticator() {
	    	  protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("urbanrunes@gmail.com", "urbanrunes504");
			}
	      }); 
	      
	      session.setDebug(true);

		 try 
	      { 
	         // MimeMessage object. 
	         MimeMessage message = new MimeMessage(session); 
	  
	         // Set From Field: adding senders email to from field. 
	         message.setFrom(new InternetAddress(sender)); 
	  
	         // Set To Field: adding recipient's email to from field. 
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
	  
	         // Set Subject: subject of the email 
	         message.setSubject("Alert"); 
	  
	         // set body of the email. 
	         message.setText("Fire Alarm alert !"); 
	  
	         // Send email. 
	         Transport.send(message); 
	         System.out.println("Mail successfully sent"); 
	      } 
	      catch (MessagingException mex)  
	      { 
	         mex.printStackTrace(); 
	      } 
	}
	
} 
