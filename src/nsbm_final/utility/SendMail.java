/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
    
    public static void mail(String sender,String mailContent) {

		final String username = "nsbmgreenuniversityhomagama@gmail.com"; // enter sender's mail id
		final String password = "nsbm@123";// enter sender's password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session;
                session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("nsbmgreenuniversityhomagama@gmail.com")); // same email id
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(sender));// whome u have to send mails that person id
			message.setSubject("Exam Grades");
			message.setText(mailContent
				+ "\n\n No spam to my email, please!");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
    
}
