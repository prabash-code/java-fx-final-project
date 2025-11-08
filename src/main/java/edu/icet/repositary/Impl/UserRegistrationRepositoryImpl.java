package edu.icet.repositary.Impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.User;
import edu.icet.repositary.UserLoginDetailsRepositary;
import edu.icet.repositary.UserRegistrationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistrationRepositoryImpl implements UserRegistrationRepository {
    Connection connection;
    @Override
    public void registerNewUser(User user) {
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into user(userId,name,email,password,role) values(?,?,?,?,?)");
            preparedStatement.setObject(1,user.getUserId());
            preparedStatement.setObject(2,user.getName());
            preparedStatement.setObject(3,user.getEmail());
            preparedStatement.setObject(4,user.getPassword());
            preparedStatement.setObject(5,user.getRole());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void getData() {
        connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from user");
    }
}
