package edu.icet.service;

public interface UserLoginService {
    String checkPassword(String email);

    String checkUserRole(String email);
}
