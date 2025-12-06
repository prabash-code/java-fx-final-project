package edu.icet.service;

public interface ForgetEmailSendService {


    boolean sendOTP(String receiverMail, String message,String sublect);

    String checkUser(String mailReceiver);
}
