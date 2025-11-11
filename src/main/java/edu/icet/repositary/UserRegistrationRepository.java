package edu.icet.repositary;

import edu.icet.model.dto.User;

public interface UserRegistrationRepository {
    void registerNewUser(User user);

    String getNewId(String rolePrefix);
}
