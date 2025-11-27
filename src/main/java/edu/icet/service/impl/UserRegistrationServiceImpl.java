package edu.icet.service.impl;

import edu.icet.model.dto.User;
import edu.icet.repository.impl.UserRegistrationRepositoryImpl;
import edu.icet.repository.UserRegistrationRepository;
import edu.icet.service.UserRegistrationService;

import java.sql.Connection;

public class UserRegistrationServiceImpl implements UserRegistrationService {
    UserRegistrationRepository userRegistrationRepository = new UserRegistrationRepositoryImpl();
    private Connection connection;

    @Override
    public void addCustomerLogingDetails() {

    }

    @Override
    public void RegisterNewUser(User user) {
        userRegistrationRepository.registerNewUser(user);

    }


    @Override
    public String generateNewUserId(String rolePrefix) {
        return userRegistrationRepository.getNewId(rolePrefix);

    }


}

