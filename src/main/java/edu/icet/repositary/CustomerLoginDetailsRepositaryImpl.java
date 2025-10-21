package edu.icet.repositary;

import edu.icet.model.Entity.CustomerLoginDetailsEntity;
import edu.icet.util.hibernate.Hibernate;

public class CustomerLoginDetailsRepositaryImpl implements CustomerLoginDetailsRepositary{
    @Override
        public void addCustomerLoginData(CustomerLoginDetailsEntity customerLoginDetails){
            Hibernate hibernate=new Hibernate();
            hibernate.addLoginDetails(customerLoginDetails);

        }

}
