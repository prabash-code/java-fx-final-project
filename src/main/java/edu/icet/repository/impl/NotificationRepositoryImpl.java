package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.repository.NotificationRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationRepositoryImpl implements NotificationRepository {
    Connection connection= DBConnection.getInstance().getConnection();
    @Override
    public ResultSet getAllNotifications() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select medicineId,medicine.name as name,supplierId,quantity,expireDate,suppliers.email from medicine left join suppliers on medicine.supplierId=suppliers.company;");
            return  preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
