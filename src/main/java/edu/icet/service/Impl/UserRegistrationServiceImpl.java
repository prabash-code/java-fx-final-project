package edu.icet.service.Impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.User;
import edu.icet.repositary.Impl.UserRegistrationRepositoryImpl;
import edu.icet.repositary.UserRegistrationRepository;
import edu.icet.service.UserRegistrationService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistrationServiceImpl implements UserRegistrationService {
    UserRegistrationRepository userRegistrationRepository = new UserRegistrationRepositoryImpl();
    private Connection connection;

    @Override
    public void addCustomerLogingDetails() {

    }

    @Override
    public void RegisterNewUser(User user) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(user.getPassword().getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            userRegistrationRepository.registerNewUser(new User(user.getUserId(), user.getName(), user.getEmail(), hexString.toString(), user.getRole()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


}

