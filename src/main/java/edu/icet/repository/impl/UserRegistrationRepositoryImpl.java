package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.User;
import edu.icet.repository.UserRegistrationRepository;
import edu.icet.util.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistrationRepositoryImpl implements UserRegistrationRepository {
    Connection connection;



    @Override
    public void registerNewUser(User user) {
        String hashedPw= Security.hashPassword(user.getPassword());
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into user(userId,name,email,password,role) values(?,?,?,?,?)");
            preparedStatement.setObject(1,user.getUserId());
            preparedStatement.setObject(2, user.getName());
            preparedStatement.setObject(3, user.getEmail());
            preparedStatement.setObject(4, hashedPw);
            preparedStatement.setObject(5, user.getRole());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getNewId(String rolePrefix) {

        String prefix = rolePrefix.substring(0, 1).toUpperCase();
        try {
            connection=DBConnection.getInstance().getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement("Select * from user where userId like ? Order by userId desc limit 1");
            preparedStatement.setString(1, prefix + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String lastId = resultSet.getString("userId");
                int num = Integer.parseInt(lastId.substring(1)) + 1;
                return rolePrefix + String.format("%03d", num);
            } else {
                return rolePrefix + "001";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet searchUser(String emailUser) {

        try {
            Connection connection1=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection1.prepareStatement("Select * from user where email=?");
            preparedStatement.setObject(1,emailUser);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteUserByEmail(String userEmail) {

        try {
            Connection connection1=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection1.prepareStatement("delete from user where email=?");
            preparedStatement.setObject(1,userEmail);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateUserPassword(String email, String hashPassword) {
        try {
            Connection connection1=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection1.prepareStatement("Update user set password=? where email=? ");
            preparedStatement.setObject(1,hashPassword);
            preparedStatement.setObject(2,email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}