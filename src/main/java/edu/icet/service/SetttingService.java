package edu.icet.service;

import edu.icet.model.dto.User;
import javafx.collections.ObservableList;

public interface SetttingService {
    ObservableList<User> getAllStaffMember();

    void removeStaffMember(String id);
}
