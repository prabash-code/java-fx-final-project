package edu.icet.util.hibernate;

import edu.icet.model.entity.CustomerLoginDetailsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Hibernate {

    public void addLoginDetails(CustomerLoginDetailsEntity customerLoginDetailsEntity){

        Configuration config=new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(CustomerLoginDetailsEntity.class);

        SessionFactory sessionFactory=config.buildSessionFactory();
        Session session=sessionFactory.openSession();

        Transaction transaction=session.beginTransaction();
        session.persist(customerLoginDetailsEntity);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
