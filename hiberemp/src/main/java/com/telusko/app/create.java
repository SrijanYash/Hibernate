package com.telusko.app;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.telusko.model.Employee;

public class create {

    public static void main(String[] args) {
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction =null;
        boolean flag=false;

        try {
            session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            Employee emp = new Employee(1,"Srijan",100000);
            Employee emp2 = new Employee(2,"Kavya",1000);
            Employee emp3 = new Employee(3,"ayush",10);

            session.persist(emp);
            session.persist(emp2);
            session.persist(emp3);

            flag=true;

        }catch( HibernateException e ){
            e.printStackTrace();
        } 
         catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(flag==true)
            transaction.commit();
            else
            transaction.rollback();

            session.close();
            sessionFactory.close();
        }

    }

    // public <T> T saveObject(T entity) {
    //     Session session = null;
    //     Transaction transaction = null;
        
    //     try {
    //         // Get the session factory and open a new session
    //         session = sessionFactory.openSession();
            
    //         // Begin a transaction
    //         transaction = session.beginTransaction();
            
    //         // Save the entity to the database
    //         session.save(entity);
            
    //         // Commit the transaction
    //         transaction.commit();
            
    //         return entity;
    //     } catch (HibernateException e) {
    //         // Rollback the transaction in case of error
    //         if (transaction != null) {
    //             transaction.rollback();
    //         }
    //         throw e;
    //     } finally {
    //         // Close the session to release resources
    //         if (session != null && session.isOpen()) {
    //             session.close();
    //         }
    //     }
    // }

}
