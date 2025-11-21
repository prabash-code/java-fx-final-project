package edu.icet.service;

import edu.icet.model.dto.Medicine;

public interface MedicineService {
    void addNewMedicine(Medicine medicine);

    String generateMedicineId();
}
