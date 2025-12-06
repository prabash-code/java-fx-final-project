package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.repository.UserLoginDetailsRepository;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserLoginDetailsRepositoryImpl implements UserLoginDetailsRepository {
    private Connection connection;

    public String searchByEmail(String email){
        String hashCode = null;
        try {
            connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email=?");
            preparedStatement.setObject(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                 hashCode= resultSet.getString("password");
                if(hashCode==null || hashCode.isEmpty()){
                    new Alert(Alert.AlertType.ERROR,"password and email not match").show();
                    return null;
                }
                return hashCode;
            }else{
                new Alert(Alert.AlertType.ERROR,"Emai not Recognized").show();
                return null;
            }

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String checkRole(String email) {
        connection=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from user where email=?");
            preparedStatement.setObject(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String idOfUser = resultSet.getString("userId");
                 if(idOfUser.substring(0,1).equalsIgnoreCase("A")){
                     return "Admin";
                 }else if(idOfUser.substring(0,1).equalsIgnoreCase("S")){
                     return "Staff";
                 }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
