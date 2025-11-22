package edu.icet.repositary;

import edu.icet.model.dto.Medicine;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public interface MedicineRepository {
    ResultSet getLastId();

    void addNewMedicine(Medicine medicine);

    ResultSet getAllMedicine();

    ResultSet searchItem(String text);

    void updateMedicine(Medicine medicine);

    void deleteMedicine(String text);
}
