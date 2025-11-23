package edu.icet.service.impl;

import edu.icet.repositary.impl.UserLoginDetailsRepositoryImpl;
import edu.icet.repositary.UserLoginDetailsRepository;
import edu.icet.service.UserLoginService;

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