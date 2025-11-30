package edu.icet.service.impl;

import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Supplier;
import edu.icet.repository.impl.SupplierRepositoryImpl;
import edu.icet.repository.SupplierRepository;
import edu.icet.service.SupplierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SupplierServiceImpl implements SupplierService {
    SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public void addNewSupplier(Supplier supplier) {
        try{
            supplierRepository.addNewSupplier(supplier);
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Duplicate Supplier Detected");
            alert.setContentText("A supplier with this name or ID already exists. Please try again.");
            alert.showAndWait();
        }

    }

    @Override
    public ObservableList<Supplier> getAll() {
        ObservableList<Supplier> list = FXCollections.observableArrayList();

        try {
            ResultSet allSuppliers = supplierRepository.getAllSuppliers();
            while (allSuppliers.next()) {
                list.add(new Supplier(
                                allSuppliers.getString("supplier_id"),
                                allSuppliers.getString("name"),
                                allSuppliers.getString("company"),
                                allSuppliers.getString("email"),
                                allSuppliers.getString("contact_number")
                        )
                );
            }
        } catch (SQLException e) {


            throw new RuntimeException(e);

        }
        return list;
    }

    @Override
    public void deleteSupplier(String mail) {
        supplierRepository.deleteSupplier(mail);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierRepository.updateSupplierDetails(supplier);
    }


    @Override
    public String generateSupplierId() {
        ResultSet lastId = supplierRepository.getLastId();

        try {
            if (lastId.next()) {
                return lastId.getString("supplier_id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier searchSupplier(String text) {

        try {
            ResultSet resultSet = supplierRepository.searchSupplierByname(text);

            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getString("supplier_id"),
                        resultSet.getString("name"),
                        resultSet.getString("company"),
                        resultSet.getString("email"),
                        resultSet.getString("contact_number"));


            } else {
                return null;
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "This is not at database").show();
            throw new RuntimeException(e);
        }
    }

}
