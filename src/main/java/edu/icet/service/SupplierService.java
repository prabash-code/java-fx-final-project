package edu.icet.service;

import edu.icet.model.dto.Supplier;
import javafx.collections.ObservableList;

public interface SupplierService {
    void addNewSupplier(Supplier supplier);

    ObservableList<Supplier> getAll();

    void deleteSupplier(String id);

    void updateSupplier(Supplier supplier);

    String generateSupplierId();


    Supplier searchSupplier(String text);
}
