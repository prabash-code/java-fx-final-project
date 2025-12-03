package edu.icet.service;

import edu.icet.model.dto.ContactDetails;

public interface ChangeDetailsService {
    void updateDetails(String number, String email, String network, String address, String about);

    ContactDetails getDetails();
}
