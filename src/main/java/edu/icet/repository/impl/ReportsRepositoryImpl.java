package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.repository.ReportsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsRepositoryImpl implements ReportsRepository {
    Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public ResultSet getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from medicine");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getAllSales() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from  sales");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllSuppliers() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from  suppliers");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}