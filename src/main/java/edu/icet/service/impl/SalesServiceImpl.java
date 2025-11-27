package edu.icet.service.impl;

import com.sun.javafx.collections.ImmutableObservableList;
import edu.icet.db.DBConnection;
import edu.icet.model.dto.CartItem;
import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Sale;
import edu.icet.model.dto.SaleDetails;
import edu.icet.repository.MedicineRepository;
import edu.icet.repository.SalesDetailsRepository;
import edu.icet.repository.SalesRepository;
import edu.icet.repository.impl.MedicineRepositoryImpl;
import edu.icet.repository.impl.SalesDetailsRepositoryImpl;
import edu.icet.repository.impl.SalesRepositoryImpl;
import edu.icet.service.MedicineService;
import edu.icet.service.SalesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesServiceImpl implements SalesService {
    SalesRepository salesRepository = new SalesRepositoryImpl();
    MedicineService medicineService = new MedicineServiceImpl();
    SalesDetailsRepository salesDetailsRepository = new SalesDetailsRepositoryImpl();
    MedicineRepository medicineRepository = new MedicineRepositoryImpl();


    @Override
    public void placeOrder(Sale sale, ObservableList<CartItem> list) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            salesRepository.addSales(connection, sale);

            for (CartItem cartItem : list) {
                salesDetailsRepository.add(new SaleDetails(
                        sale.getSaleId(),
                        cartItem.getItemId(),
                        cartItem.getQuantity()
                ));
                ResultSet resultSet = medicineRepository.searchItem(connection, cartItem.getItemId());
                if (resultSet.next()) {
                    Medicine medicine = new Medicine(
                            resultSet.getString("medicineId"),
                            resultSet.getString("brand"),
                            resultSet.getString("name"),
                            resultSet.getString("supplierId"),
                            resultSet.getDouble("unitPrice"),
                            resultSet.getInt("quantity"),
                            resultSet.getDate("manufactureDate").toLocalDate(),
                            resultSet.getDate("expireDate").toLocalDate());

                    medicineRepository.updateQuantity(connection, cartItem.getItemId(), (medicine.getQuantity() - cartItem.getQuantity()));
                }

            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public String getLastOrderId() {
        ResultSet lastId = salesRepository.getLastId();
        try {
            if (lastId.next()) {
                return lastId.getString("saleId");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Medicine SerchMedicine(String medicineName) throws SQLException {
        return medicineService.searchMedicine(medicineName);

    }

    @Override
    public ObservableList<Sale> getAll() {
        ResultSet allSaleDetails = salesRepository.getAllSaleDetails();
      ObservableList<Sale>list= FXCollections.observableArrayList();
        try {
            while (allSaleDetails.next()) {
                String totalStr = allSaleDetails.getString("total");
                double total=0.00;
                if(totalStr!=null && !totalStr.trim().isEmpty()){
                    total=Double.parseDouble(totalStr);
                }
                list.add(new Sale(allSaleDetails.getString("saleId"),
                        allSaleDetails.getDate("saleDate").toLocalDate(),
                        allSaleDetails.getString("customerName"),
                        allSaleDetails.getString("customerEmail"),
                        total
                ));

            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
