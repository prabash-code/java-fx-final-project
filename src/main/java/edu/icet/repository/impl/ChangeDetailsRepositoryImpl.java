package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.repository.ChangeDetailsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ChangeDetailsRepositoryImpl implements ChangeDetailsRepository {
    Connection connection= DBConnection.getInstance().getConnection();
    @Override
    public void updateData(String number, String email, String network, String address, String about) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update ContactDetails set number=?,network=?,address=?,about=? where email=?;");
            preparedStatement.setObject(1,number);
            preparedStatement.setObject(2,network);
            preparedStatement.setObject(3,address);
            preparedStatement.setObject(4,about);
            preparedStatement.setObject(5,email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from ContactDetails;");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
