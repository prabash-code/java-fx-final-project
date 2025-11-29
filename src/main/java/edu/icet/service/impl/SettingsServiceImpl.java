package edu.icet.service.impl;

import edu.icet.model.dto.User;
import edu.icet.repository.SettingRepository;
import edu.icet.repository.impl.SettingRepositoryImpl;
import edu.icet.service.SetttingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsServiceImpl implements SetttingService {
    SettingRepository settingRepository=new SettingRepositoryImpl();
    @Override
    public ObservableList<User> getAllStaffMember() {
        ResultSet allStaffMembers = settingRepository.getAllStaffMembers();
        ObservableList<User>list= FXCollections.observableArrayList();
        try{

                while (allStaffMembers.next()) {
                    if(allStaffMembers.getString("role").equals("staff")) {
                        list.add(new User(
                                allStaffMembers.getString("UserId"),
                                allStaffMembers.getString("name"),
                                allStaffMembers.getString("email"),
                                allStaffMembers.getString("password"),
                                allStaffMembers.getString("role")

                        ));
                    }
                }

            return list;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeStaffMember(String id) {
        settingRepository.removeStaffMember(id);

    }
}
