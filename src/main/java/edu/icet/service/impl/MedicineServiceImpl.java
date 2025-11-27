package edu.icet.service.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.Medicine;
import edu.icet.repository.impl.MedicineRepositoryImpl;
import edu.icet.repository.MedicineRepository;
import edu.icet.service.MedicineService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MedicineServiceImpl implements MedicineService {
    Connection connection = DBConnection.getInstance().getConnection();
    MedicineRepository medicineRepository = new MedicineRepositoryImpl();

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

    @Override
    public ObservableList<Medicine> getAll() {
        ObservableList<Medicine> list = FXCollections.observableArrayList();


        try {
            ResultSet allMedicine = medicineRepository.getAllMedicine();
            while (allMedicine.next()) {
                list.add(new Medicine(
                                allMedicine.getString("medicineId"),
                                allMedicine.getString("brand"),
                                allMedicine.getString("name"),
                                allMedicine.getString("supplierId"),
                                allMedicine.getDouble("unitPrice"),
                                allMedicine.getInt("quantity"),
                                allMedicine.getDate("manufactureDate").toLocalDate(),
                                allMedicine.getDate("expireDate").toLocalDate()
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Medicine searchMedicine(String text) throws SQLException {
        try {
            ResultSet resultSet = medicineRepository.searchItem(connection,text);
            if (resultSet.next()) {
                return new Medicine(
                        resultSet.getString("medicineId"),
                        resultSet.getString("brand"),
                        resultSet.getString("name"),
                        resultSet.getString("supplierId"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("manufactureDate").toLocalDate(),
                        resultSet.getDate("expireDate").toLocalDate());

            }else{
                return null;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"This is not at database").show();
            throw new RuntimeException(e);
        }

    }


    @Override
    public void UpdateMedicine(Medicine medicine) {

        medicineRepository.updateMedicine(connection, medicine);
    }

    @Override
    public void deleteMedicine(String text) {
        medicineRepository.deleteMedicine(text);
    }
}

