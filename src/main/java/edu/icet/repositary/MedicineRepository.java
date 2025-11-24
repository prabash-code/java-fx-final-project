package edu.icet.repositary;

import edu.icet.model.dto.Medicine;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public interface MedicineRepository {
    ResultSet getLastId();

    void addNewMedicine(Medicine medicine);

    ResultSet getAllMedicine();

    ResultSet searchItem(Connection connection,String text);

    void updateMedicine(Connection connection, Medicine medicine);

    void deleteMedicine(String text);

    boolean updateQuantity(Connection connection, String itemId, int i);
}
