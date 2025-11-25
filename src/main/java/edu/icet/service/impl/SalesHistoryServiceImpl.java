package edu.icet.service.impl;

import edu.icet.model.dto.SalesHistory;
import edu.icet.repositary.SalesHistoryRepository;
import edu.icet.repositary.impl.SalesHistoryRepositoryImpl;
import edu.icet.service.SalesHistoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesHistoryServiceImpl implements SalesHistoryService {

    SalesHistoryRepository salesHistoryRepository = new SalesHistoryRepositoryImpl();
    ObservableList<SalesHistory> list = FXCollections.observableArrayList();

    @Override
    public ObservableList<SalesHistory> getAll() {
        ResultSet allDetails = salesHistoryRepository.getAllDetails();

        try {
            while (allDetails.next()) {
                list.add(new SalesHistory(
                        allDetails.getString("saleId"),
                        allDetails.getString("customerName"),
                        allDetails.getDate("saleDate").toLocalDate(),
                        allDetails.getString("customerEmail")

                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}