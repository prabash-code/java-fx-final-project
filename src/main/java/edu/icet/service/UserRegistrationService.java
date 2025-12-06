package edu.icet.service;

import edu.icet.model.dto.User;
import javafx.collections.ObservableList;

public interface UserRegistrationService {
    void addCustomerLogingDetails();


    void RegisterNewUser(User user);

    String generateNewUserId(String substring);

    ObservableList<User> searchUserById(String email);

    void deleteByEmail(String email);

    void updateUserPassword(String email, String hashPassword);
}
