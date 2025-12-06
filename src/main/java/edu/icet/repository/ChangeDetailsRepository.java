package edu.icet.repository;

import java.sql.ResultSet;

public interface ChangeDetailsRepository {
    void updateData(String number, String email, String network, String address, String about);
    ResultSet getAll();
}
