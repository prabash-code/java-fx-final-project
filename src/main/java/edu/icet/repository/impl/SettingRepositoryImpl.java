package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.repository.SettingRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingRepositoryImpl implements SettingRepository {
    @Override
    public ResultSet getAllStaffMembers() {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeStaffMember(String id) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where userId= ?");
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
