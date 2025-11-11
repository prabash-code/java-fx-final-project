package edu.icet.service.Impl;

import edu.icet.repositary.Impl.UserLoginDetailsRepositoryImpl;
import edu.icet.repositary.UserLoginDetailsRepository;
import edu.icet.service.UserLoginService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginServiceImpl implements UserLoginService {
    UserLoginDetailsRepository userLoginDetailsRepository = new UserLoginDetailsRepositoryImpl();

    @Override
    public String checkPassword(String email) {
        return userLoginDetailsRepository.searchByEmail(email);
    }

    @Override
    public String checkUserRole(String email) {
        return userLoginDetailsRepository.checkRole(email);

    }
}