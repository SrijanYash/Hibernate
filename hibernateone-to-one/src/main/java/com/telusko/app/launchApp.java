package com.telusko.app;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.telusko.module.answer;
import com.telusko.module.question;

public class launchApp {
    public static void main(String[] args) {
        
        Configuration config = null;
        SessionFactory sessionFactory = null;
        Session session = null;

        Transaction transaction = null;

        boolean flag = false;

        config=new Configuration();

        config.configure();

        sessionFactory = config.buildSessionFactory();

        session = sessionFactory.openSession();

        question q1 = new question();
        q1.setSid(1);
        q1.setQuestion("what is hibernate");

        answer a1 = new answer();
        a1.setSid(1);
        a1.setAnswer("it is an orm framework");

        q1.setAnswer(a1);

        question q2 = new question();
        q2.setSid(2);
        q2.setQuestion("what is life");

        answer a2 = new answer();
        a2.setSid(2);
        a2.setAnswer("it is hell");

        q2.setAnswer(a2);
        try
        {
            transaction = session.beginTransaction();
            session.persist(q1);
            session.persist(q2);
            flag=true;
        }
        catch(HibernateException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
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
