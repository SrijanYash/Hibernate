package com.srijan.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.srijan.model.Student;

public class StudentRepositoryImpl implements StudentRepository {
    private final SessionFactory sessionFactory;
    
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error saving student: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<Student> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Student student = session.get(Student.class, id);
            return Optional.ofNullable(student);
        }
    }
    
    @Override
    public Optional<Student> findByStudentId(String studentId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student s WHERE s.studentId = :studentId", Student.class);
            query.setParameter("studentId", studentId);
            return query.uniqueResultOptional();
        }
    }
    
    @Override
    public Optional<Student> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student s WHERE s.email = :email", Student.class);
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        }
    }
    
    @Override
    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }
    
    @Override
    public List<Student> findByMajor(String major) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student s WHERE s.major = :major", Student.class);
            query.setParameter("major", major);
            return query.list();
        }
    }
    
    @Override
    public void update(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error updating student: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error deleting student: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void deleteByStudentId(String studentId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Student s WHERE s.studentId = :studentId");
            query.setParameter("studentId", studentId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Error deleting student: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean existsByStudentId(String studentId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(s) FROM Student s WHERE s.studentId = :studentId", Long.class);
            query.setParameter("studentId", studentId);
            return query.uniqueResult() > 0;
        }
    }
    
    @Override
    public boolean existsByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(s) FROM Student s WHERE s.email = :email", Long.class);
            query.setParameter("email", email);
            return query.uniqueResult() > 0;
        }
    }
    
    @Override
    public long count() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(s) FROM Student s", Long.class);
            return query.uniqueResult();
        }
    }
}
