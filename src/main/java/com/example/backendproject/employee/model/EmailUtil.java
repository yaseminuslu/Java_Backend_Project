package com.example.backendproject.employee.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
    private static final String USERNAME = ""; // Your email
    private static final String PASSWORD = ""; // Your email password

    public static void sendActivationCodeByEmail(String toEmail, String activationCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp-mail.outlook.com"); // Update with your SMTP server
        properties.put("mail.smtp.port", "587"); // Update with your SMTP port

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Account Activation");
//            message.setText("Dear Employee,\nThank you for registering on our platform." + "\n\nActivation Code: " + activationCode );
            String htmlMessage = "<html><body>Dear Employee, Thank you for registering on our platform."
                    + "<br/><br/><strong>Activation Code: " + activationCode + "</strong></body></html>";

            message.setContent(htmlMessage, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Registration email sent to: " + toEmail + ", Activation Code: " + activationCode);
        } catch (MessagingException e) {
            System.err.println("Error sending registration email: " + e.getMessage());
        }
    }
}
