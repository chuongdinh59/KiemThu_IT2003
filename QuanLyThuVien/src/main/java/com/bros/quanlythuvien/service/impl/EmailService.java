/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

/**
 *
 * @author Dinh Chuong
 */
import java.io.UnsupportedEncodingException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.internet.MimeUtility;

public class EmailService {

    private String host = "smtp.gmail.com";
    private String port = "587";
    private  String username = "hakoru1a@gmail.com";
    private  String password = "pxunqhwpxlgbekzd";

   
    public void sendEmail(String toAddress, String subject, String message) throws MessagingException, UnsupportedEncodingException {
        // Set properties for SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create session with username and password authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create message object and set recipients, subject, and body
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
        msg.setContent(message, "text/plain; charset=utf-8");

        // Send the message
        Transport.send(msg);
    }
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        EmailService sm = new EmailService();
//        sm.sendEmail("qthcsdlchu59@gmail.com", "Thư Viên Dân Chơi ", "Duyệt thẻ thư viện");
    
        sm.sendEmail("tuthattha232@gmail.com", "Thư Viên Dân Chơi ", "Duyệt thẻ thư viện");
    }
}
