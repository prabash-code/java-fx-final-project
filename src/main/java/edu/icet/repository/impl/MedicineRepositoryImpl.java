package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.Medicine;
import edu.icet.repository.MedicineRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MedicineRepositoryImpl implements MedicineRepository {
    Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public ResultSet getLastId() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select medicineId from medicine order by medicineId desc limit 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addNewMedicine( Medicine medicine) {
        Connection con = null;
        if (connection != null) {
            con = connection;
        } else {
            con = this.connection;
        }
        try {
            PreparedStatement preparedStatement = con.prepareStatement("Insert into medicine values(?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1, medicine.getMedicineId());
            preparedStatement.setObject(2, medicine.getBrand());
            preparedStatement.setObject(3, medicine.getName());
            preparedStatement.setObject(4, medicine.getSupplierId());
            preparedStatement.setObject(5, medicine.getUnitPrice());
            preparedStatement.setObject(6, medicine.getQuantity());
            preparedStatement.setObject(7, medicine.getManufactureDate());
            preparedStatement.setObject(8, medicine.getExpireDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllMedicine() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from medicine");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet
    searchItem(Connection connection,String text) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from medicine where medicineId=?");
            preparedStatement.setObject(1, text);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateMedicine(Connection connection,Medicine medicine) {
        Connection con=null;

        if(connection!=null){
            con=connection;
        }
        else{
            con=this.connection;
        }
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE medicine SET brand=?, supplierId=?, unitPrice=?, quantity=?, manufactureDate=?, expireDate=?, name=? where medicineId=?");
            preparedStatement.setObject(1, medicine.getBrand());
            preparedStatement.setObject(2, medicine.getSupplierId());
            preparedStatement.setObject(3, medicine.getUnitPrice());
            preparedStatement.setObject(4, medicine.getQuantity());
            preparedStatement.setObject(5, medicine.getManufactureDate());
            preparedStatement.setObject(6, medicine.getExpireDate());
            preparedStatement.setObject(7, medicine.getName());
            preparedStatement.setObject(8, medicine.getMedicineId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMedicine(String text) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from medicine where medicineId =?");
            preparedStatement.setObject(1, text);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateQuantity(Connection connection, String medicineId, int id) {
        Connection con=null;

        if(connection!=null){
            con=connection;
        }
        else{
            con=this.connection;
        }
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE medicine SET quantity=? where medicineId=?");
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,medicineId);
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet searchItemByName(Connection connection, String text) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from medicine where name=?");
            preparedStatement.setObject(1, text);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
