package edu.icet.repositary;

import edu.icet.model.dto.Medicine;

import java.sql.ResultSet;

public interface MedicineRepository {
    ResultSet getLastId();

    void addNewMedicine(Medicine medicine);
}
