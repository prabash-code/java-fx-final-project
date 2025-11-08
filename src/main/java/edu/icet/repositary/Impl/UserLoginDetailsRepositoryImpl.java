package edu.icet.repositary.Impl;

import edu.icet.db.DBConnection;
import edu.icet.repositary.UserLoginDetailsRepositary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserLoginDetailsRepositoryImpl implements UserLoginDetailsRepositary {
    private Connection connection;
    public ResultSet searchByEmail(String email){

        try {
            connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email=?");
            preparedStatement.setObject(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
