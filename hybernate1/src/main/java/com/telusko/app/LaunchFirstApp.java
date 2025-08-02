package com.telusko.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.telusko.model.Student;

public class LaunchFirstApp {

    public static void main(String[] args) {
        
        //configure object
        Configuration config = new Configuration();

        //configuring config file to the object
        config.configure("hibernate.cfg.xml");

        //create session factory
        SessionFactory sessionFactory = config.buildSessionFactory();

        //trasaction within the sessions
        try ( //getting the session object from the sessionfactory
                Session session = sessionFactory.openSession()) {
            //trasaction within the sessions
            Transaction transaction = session.beginTransaction();
            //making the changes
            Student student = new Student();
            student.setSid(1);
            student.setsName("Sijan");
            student.setScity("Lucknow");
            //performing operation
            session.save(student);
            //performing transaction
            transaction.commit();
            //closing the session
        }
    }
}
