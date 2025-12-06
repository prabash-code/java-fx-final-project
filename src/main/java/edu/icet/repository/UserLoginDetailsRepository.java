package edu.icet.repository;


public interface UserLoginDetailsRepository {
    public String searchByEmail(String email);

    String checkRole(String email);
}
