package edu.icet.service.Impl;

import edu.icet.repositary.Impl.UserLoginDetailsRepositoryImpl;
import edu.icet.repositary.UserLoginDetailsRepositary;
import edu.icet.service.UserLoginService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginServiceImpl implements UserLoginService {
    UserLoginDetailsRepositary userLoginDetailsRepositary = new UserLoginDetailsRepositoryImpl();

    @Override
    public boolean checkPassword(String email, String password) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            ResultSet resultSet = userLoginDetailsRepositary.searchByEmail(email);
            try {
                if (resultSet.next()) {
                    String password1 = null;
                    try {
                        password1 = resultSet.getString("password");
                        if (password1.equals(hexString)) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}