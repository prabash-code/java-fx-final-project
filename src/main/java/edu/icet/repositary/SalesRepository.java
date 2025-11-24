package edu.icet.repositary;

import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Sale;

import java.sql.Connection;
import java.sql.ResultSet;

public interface SalesRepository {
    ResultSet getLastId();

    void addSales(Connection connection, Sale sale);

}
