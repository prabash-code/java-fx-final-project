package edu.icet.service;

import edu.icet.model.dto.User;

public interface UserRegistrationService {
    void addCustomerLogingDetails();


    void RegisterNewUser(User user);

    String generateNewUserId(String substring);
}
