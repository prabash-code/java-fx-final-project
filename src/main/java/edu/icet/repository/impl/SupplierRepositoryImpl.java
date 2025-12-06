package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.Supplier;
import edu.icet.repository.SupplierRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRepositoryImpl implements SupplierRepository {

    Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public void addNewSupplier(Supplier supplier) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO suppliers (supplier_id,name, company, email, contact_number) VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1, supplier.getSupplierId());
            preparedStatement.setObject(2, supplier.getName());
            preparedStatement.setObject(3, supplier.getCompany());
            preparedStatement.setObject(4, supplier.getEmail());
            preparedStatement.setObject(5, supplier.getPhone());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add supplier", e);
        }
    }

    @Override
    public ResultSet getLastId() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT supplier_id FROM suppliers ORDER BY supplier_id DESC LIMIT 1");

            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch last ID", e);
        }
    }

    @Override
    public ResultSet getAllSuppliers() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM suppliers");
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all suppliers", e);
        }
    }

    @Override
    public void deleteSupplier(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM suppliers WHERE supplier_id= ?");

            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete supplier", e);
        }
    }


    @Override
    public void updateSupplierDetails(Supplier supplier) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE suppliers SET name=?, company=?, email=?, contact_number=? where supplier_id =?");
            preparedStatement.setObject(1, supplier.getName());
            preparedStatement.setObject(2, supplier.getCompany());
            preparedStatement.setObject(3, supplier.getEmail());
            preparedStatement.setObject(4, supplier.getPhone());
            preparedStatement.setObject(5, supplier.getSupplierId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchSupplierByname(String text) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from suppliers where company=?");
            preparedStatement.setObject(1, text);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
