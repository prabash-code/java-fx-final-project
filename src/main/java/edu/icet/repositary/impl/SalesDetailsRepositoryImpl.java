package edu.icet.repositary.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.SaleDetails;
import edu.icet.repositary.SalesDetailsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDetailsRepositoryImpl implements SalesDetailsRepository {
    Connection connection= DBConnection.getInstance().getConnection();

    @Override
    public void add(SaleDetails saleDetails) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO salesdetails(saleId, itemCode, orderQuantity) VALUES (?, ?, ?)");
            preparedStatement.setObject(1,saleDetails.getSaleId());
            preparedStatement.setObject(2,saleDetails.getItemCode());
            preparedStatement.setObject(3,saleDetails.getOrderQuantity());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
