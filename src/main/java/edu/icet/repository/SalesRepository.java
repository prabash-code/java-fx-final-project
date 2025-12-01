package edu.icet.repository;

import edu.icet.model.dto.Sale;

import java.sql.Connection;
import java.sql.ResultSet;

public interface SalesRepository {
    ResultSet getLastId();

    void addSales(Connection connection, Sale sale);

    ResultSet getAllSaleDetails();

    ResultSet generatePdf(String text);
}
