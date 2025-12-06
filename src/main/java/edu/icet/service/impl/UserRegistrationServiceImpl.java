package edu.icet.service.impl;

import edu.icet.model.dto.User;
import edu.icet.repository.impl.UserRegistrationRepositoryImpl;
import edu.icet.repository.UserRegistrationRepository;
import edu.icet.service.UserRegistrationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistrationServiceImpl implements UserRegistrationService {
    UserRegistrationRepository userRegistrationRepository = new UserRegistrationRepositoryImpl();
    private Connection connection;

    @Override
    public void addCustomerLogingDetails() {
    }

    @Override
    public void RegisterNewUser(User user) {
        userRegistrationRepository.registerNewUser(user);
        String role = user.getRole();
    }

    @Override
    public String generateNewUserId(String rolePrefix) {
        return userRegistrationRepository.getNewId(rolePrefix);
    }

    @Override
    public ObservableList<User> searchUserById(String email) {

        ResultSet resultSet = userRegistrationRepository.searchUser(email);
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("userId"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                ));
            }
        return  list;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByEmail(String email) {
        userRegistrationRepository.deleteUserByEmail(email);
    }

    @Override
    public void updateUserPassword(String email, String hashPassword) {
        userRegistrationRepository.updateUserPassword(email,hashPassword);
    }
}



