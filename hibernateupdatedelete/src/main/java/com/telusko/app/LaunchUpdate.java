package com.telusko.app;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.telusko.model.Student;

public class LaunchUpdate {

    public static void main(String[] args) {
        
        SessionFactory sessionFactory = new Configuration().configure().
        addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = null;
        Transaction transaction = null;
        boolean flag =false;

        try
        {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student st = new Student();
            st.setSid(2);
            st.setsName("ayush");
            st.setScity("Lucknow");

            //updates the present record
            //you can also do saveorupdate(obj) to do both save and update
            //you can also perform update operation from merg(obj)
            //you can also delete the data form the database by using delete(obj)
            //you can also use remove(obj) this is recomeded hy hibernate to use for delete
            session.update(st);

            flag=true;

        }
        catch(HibernateException e )
        {
            e.printStackTrace();
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            if(flag==true)
            transaction.commit();
            else
            transaction.rollback();

            session.close();
            sessionFactory.close();
        }
    }

}
