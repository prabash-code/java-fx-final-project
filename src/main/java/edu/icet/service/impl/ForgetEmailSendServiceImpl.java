package edu.icet.service.impl;

import edu.icet.repository.UserLoginDetailsRepository;
import edu.icet.repository.impl.UserLoginDetailsRepositoryImpl;
import edu.icet.service.ForgetEmailSendService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class ForgetEmailSendServiceImpl implements ForgetEmailSendService {


    @Override
    public boolean sendOTP(String receiverMail, String message, String subject) {


        final String senderEmail = "prabashwithanachchi11111@gmail.com";
        final String password = "hojb iohi kxth duhk";

        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, password);
                }
            });

            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(senderEmail));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverMail));
            message1.setSubject(subject);
            message1.setText(message);

            Transport.send(message1);
            System.out.println(" email send");
            return true;

        } catch (Exception e) {
            System.out.println(" email not send");
            return false;
        }
    }

    @Override
    public String checkUser(String mailReceiver) {
        UserLoginDetailsRepository userLoginDetailsRepository = new UserLoginDetailsRepositoryImpl();
        return userLoginDetailsRepository.checkRole(mailReceiver);
    }

}
