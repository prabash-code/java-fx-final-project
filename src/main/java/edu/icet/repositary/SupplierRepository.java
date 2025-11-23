package edu.icet.repositary;

import edu.icet.model.dto.Supplier;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public interface SupplierRepository {
    void addNewSupplier(Supplier supplier);

    ResultSet getLastId();

    ResultSet getAllSuppliers();

    void deleteSupplier(String mail);

    void updateSupplierDetails(Supplier supplier);
}
