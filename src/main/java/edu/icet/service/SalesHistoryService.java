package edu.icet.service;

import edu.icet.model.dto.SalesHistory;
import javafx.collections.ObservableList;

public interface SalesHistoryService {
    public ObservableList<SalesHistory> getAll();
}
