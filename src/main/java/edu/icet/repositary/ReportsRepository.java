package edu.icet.repositary;

import java.sql.ResultSet;

public interface ReportsRepository {
    public ResultSet getAll();


    ResultSet getAllSales();

    ResultSet getAllSuppliers();
}
