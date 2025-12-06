package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.Sale;
import edu.icet.repository.SalesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesRepositoryImpl implements SalesRepository {
    Connection connection= DBConnection.getInstance().getConnection();
    @Override
    public ResultSet getLastId() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select saleId from sales order by saleId desc limit 1  ");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addSales(Connection connection,Sale sale) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sales (saleId, saleDate, customerName,customerEmail,total) VALUES (?,?,?,?,?)");

            preparedStatement.setObject(1,sale.getSaleId());
            preparedStatement.setObject(2,sale.getSaleDate());
            preparedStatement.setObject(3,sale.getCustomerName());
            preparedStatement.setObject(4,sale.getCustomerEmail());
            preparedStatement.setObject(5,sale.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllSaleDetails() {
        Connection connection1=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection1.prepareStatement("Select * from sales");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet generatePdf(String text) {
        Connection connection1=DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection1.prepareStatement("select * from sales left join salesdetails on sales.saleId=salesdetails.saleId where sales.saleId=?;");
           preparedStatement.setObject(1,text);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
