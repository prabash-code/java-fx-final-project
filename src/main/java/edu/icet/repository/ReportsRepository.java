package edu.icet.repository;

import java.sql.ResultSet;

public interface ReportsRepository {
    public ResultSet getAll();


    ResultSet getAllSales();


    ResultSet getAllSuppliers();
}
