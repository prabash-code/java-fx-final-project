package edu.icet.service;

import edu.icet.model.dto.CartItem;
import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Sale;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public interface SalesService {
    void placeOrder(Sale sale, ObservableList<CartItem> list);

    String getLastOrderId();

    Medicine SerchMedicine(String medicineName) throws SQLException;

    ObservableList<Sale> getAll();
}
