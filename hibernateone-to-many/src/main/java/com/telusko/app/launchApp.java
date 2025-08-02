package com.telusko.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.telusko.model.AnswerTable;
import com.telusko.model.QuestionTable;

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

        QuestionTable q1 = new QuestionTable();
        q1.setSid(1);
        q1.setQuestion("what is hibernate");

        AnswerTable a1 = new AnswerTable();
        a1.setSid(1);
        a1.setAnswer("it is an orm framework");
        a1.setQuestionTable(q1);

        AnswerTable a2 = new AnswerTable();
        a2.setSid(2);
        a2.setAnswer("it is a database related bullshit");
        a2.setQuestionTable(q1);

        AnswerTable a3 = new AnswerTable();
        a3.setSid(3);
        a3.setAnswer("it is a way to suiside");
        a3.setQuestionTable(q1);
       
        List<AnswerTable> ans = new ArrayList<AnswerTable>();
        ans.add(a1);
        ans.add(a2);
        ans.add(a3);

        q1.setAnswerList(ans);

        try
        {
            transaction = session.beginTransaction();
            session.persist(q1);
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
