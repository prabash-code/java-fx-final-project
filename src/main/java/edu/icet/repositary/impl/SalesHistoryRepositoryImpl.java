package edu.icet.repositary.impl;

import edu.icet.db.DBConnection;
import edu.icet.repositary.SalesHistoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesHistoryRepositoryImpl implements SalesHistoryRepository {
    Connection connection= DBConnection.getInstance().getConnection();
    @Override
    public ResultSet getAllDetails() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from sales");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
