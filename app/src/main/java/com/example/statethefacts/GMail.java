package com.example.statethefacts;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class provides the application with access to a Gmail account
 * for sending the player history to the user of StateTheFacts
 *
 *  @author Michael Gibson
 *  @version 1.0
 *  @since 12/8/2020
 */
public class GMail {

    // declaration of variables
    final String emailPort = "587";// gmail's smtp port
    final String smtpAuth = "true";
    final String starttls = "true";
    final String emailHost = "smtp.gmail.com";


    String fromEmail;
    String fromPassword;
    String toEmail;
    String emailSubject;
    String emailBody;

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    // Constructors

    public GMail() {

    }

    /**
     * creating a GMail object will set the mail server properties for the app and prepare
     * the app for sending the user history to their email address.
     * @param fromEmail
     * @param fromPassword
     * @param toEmail
     * @param emailSubject
     * @param emailBody
     */
    public GMail(String fromEmail, String fromPassword,
                 String toEmail, String emailSubject, String emailBody) {
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmail = toEmail;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);

        // log the mail server properties have been set
        Log.i("GMail", "Mail server properties set.");
    }

    /**
     * createEmailMessage() is the method that creates the message for sending the
     * user information to their email
     * @return
     * @throws AddressException
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        emailMessage.addRecipient(Message.RecipientType.TO,
                new InternetAddress(toEmail));

        // removed information for multiple recipients

        /*  for (String toEmail : toEmailList) {
            Log.i("GMail", "toEmail: " + toEmail);
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
        } */

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");// for a html email
        // emailMessage.setText(emailBody);// for a text email
        Log.i("GMail", "Email Message created.");
        return emailMessage;
    }

    /**
     * sendEmail is called to send the message based on the object's variables
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail() throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
        Log.i("GMail", "allrecipients: " + emailMessage.getAllRecipients());
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        Log.i("GMail", "Email sent successfully.");
    }

}
