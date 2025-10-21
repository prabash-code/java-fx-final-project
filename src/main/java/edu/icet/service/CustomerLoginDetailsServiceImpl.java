package edu.icet.service;

import edu.icet.model.Entity.CustomerLoginDetailsEntity;
import edu.icet.model.dto.CustomerLoginDetails;
import edu.icet.repositary.CustomerLoginDetailsRepositary;
import edu.icet.repositary.CustomerLoginDetailsRepositaryImpl;

public class CustomerLoginDetailsServiceImpl implements CustomerLoginDetailsService{
    @Override
    public void addCustomerLogingDetails(CustomerLoginDetails customerLoginDetails) {
        CustomerLoginDetailsRepositary customerLoginDetailsRepositary=new CustomerLoginDetailsRepositaryImpl();

        customerLoginDetailsRepositary.addCustomerLoginData(new CustomerLoginDetailsEntity(
                customerLoginDetails.getUserName(),
                customerLoginDetails.getEmail(),
                customerLoginDetails.getPassword()
        ));
    }
    public void clearDetaInLoginForm(){

    }
}
