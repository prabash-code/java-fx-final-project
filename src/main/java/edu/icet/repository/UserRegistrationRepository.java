package edu.icet.repository;

import edu.icet.model.dto.User;

import java.sql.ResultSet;

public interface UserRegistrationRepository {
    void registerNewUser(User user);

    String getNewId(String rolePrefix);

    ResultSet searchUser(String emailUser);

    void deleteUserByEmail(String userEmail);

    void updateUserPassword(String email, String hashPassword);
}
