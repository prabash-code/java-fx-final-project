package edu.icet.repositary;


import java.sql.ResultSet;

public interface UserLoginDetailsRepository {
    public String searchByEmail(String email);

    String checkRole(String email);
}
