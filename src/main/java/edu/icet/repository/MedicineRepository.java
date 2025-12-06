package edu.icet.repository;

import edu.icet.model.dto.Medicine;

import java.sql.Connection;
import java.sql.ResultSet;

public interface MedicineRepository {
    ResultSet getLastId();

    void addNewMedicine(Medicine medicine);

    ResultSet getAllMedicine();

    ResultSet searchItem(Connection connection,String text);

    void updateMedicine(Connection connection, Medicine medicine);

    void deleteMedicine(String text);

    boolean updateQuantity(Connection connection, String itemId, int id);

    ResultSet searchItemByName(Connection connection, String text);
}
