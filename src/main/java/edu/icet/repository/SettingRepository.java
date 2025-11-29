package edu.icet.repository;

import java.sql.ResultSet;

public interface SettingRepository {
    ResultSet getAllStaffMembers();

    void removeStaffMember(String id);
}
