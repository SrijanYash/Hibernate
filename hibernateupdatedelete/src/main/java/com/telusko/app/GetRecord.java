package com.telusko.app;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.telusko.model.Student;

public class GetRecord {

    public static void main(String[] args) {
        
        SessionFactory sessionFactory = new Configuration().configure()
        .addAnnotatedClass(Student.class).buildSessionFactory();
        //transaction is not needed in the retrival of the data

        //level 1 cache is stored in a session the data called of the same id 
        //level 2 cache can added from ehcache
        Session session1 = null;
        Session session2 = null;

        try
        {
            //lazy loading loades the parameter given in the get function only
            //eager loading loades the whole constructor of the entity given
            session1 = sessionFactory.openSession();
            Student student1 = session1.get(Student.class,1);
            System.out.println(student1);

            session2 = sessionFactory.openSession();
            Student student2 = session2.get(Student.class, 1);
            System.out.println(student2);

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
            session1.close();
            session2.close();
            sessionFactory.close();
        }

    }
}
