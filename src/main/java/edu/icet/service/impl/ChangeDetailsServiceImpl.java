package edu.icet.service.impl;

import edu.icet.model.dto.ContactDetails;
import edu.icet.repository.ChangeDetailsRepository;
import edu.icet.repository.impl.ChangeDetailsRepositoryImpl;
import edu.icet.service.ChangeDetailsService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeDetailsServiceImpl implements ChangeDetailsService {
    ChangeDetailsRepository changeDetailsRepository = new ChangeDetailsRepositoryImpl();

    @Override
    public void updateDetails(String number, String email, String network, String address, String about) {
        changeDetailsRepository.updateData(number, email, network, address, about);

    }

    @Override
    public ContactDetails getDetails() {

        ResultSet all = changeDetailsRepository.getAll();
        ContactDetails contactDetails = null;
        try {
            while (all.next()) {
                contactDetails = new ContactDetails(
                        all.getString("number"),
                        all.getString("email"),
                        all.getString("network"),
                        all.getString("address"),
                        all.getString("about"));
            }
            return contactDetails;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
