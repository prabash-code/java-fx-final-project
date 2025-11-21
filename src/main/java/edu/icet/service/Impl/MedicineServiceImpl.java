package edu.icet.service.Impl;

import edu.icet.model.dto.Medicine;
import edu.icet.repositary.Impl.MedicineRepositoryImpl;
import edu.icet.repositary.MedicineRepository;
import edu.icet.service.MedicineService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineServiceImpl implements MedicineService {
    MedicineRepository medicineRepository=new MedicineRepositoryImpl();
    @Override
    public void addNewMedicine(Medicine medicine) {
        medicineRepository.addNewMedicine(medicine);

    }

    @Override
    public String generateMedicineId() {
        ResultSet lastId = medicineRepository.getLastId();
        try {
            if (lastId.next()) {
                return lastId.getString("medicineId");

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
