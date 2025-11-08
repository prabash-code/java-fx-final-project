package edu.icet.repositary;


import java.sql.ResultSet;

public interface UserLoginDetailsRepositary {
    public ResultSet searchByEmail(String email);
}
