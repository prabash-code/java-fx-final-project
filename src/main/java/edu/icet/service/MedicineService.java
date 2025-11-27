package edu.icet.service;

import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Supplier;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.sql.Connection;
import java.sql.SQLException;

public interface MedicineService {
    void addNewMedicine(Medicine medicine);

    String generateMedicineId();

    ObservableList<Medicine> getAll();
    Medicine searchMedicine(String text) throws SQLException;

    void UpdateMedicine(Medicine medicine);

    void deleteMedicine(String text);


    Medicine searchMedicineByName(String text);
}

