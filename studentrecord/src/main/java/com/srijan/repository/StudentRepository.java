package com.srijan.repository;

import java.util.List;
import java.util.Optional;

import com.srijan.model.Student;

public interface StudentRepository {
    void save(Student student);
    Optional<Student> findById(Long id);
    Optional<Student> findByStudentId(String studentId);
    Optional<Student> findByEmail(String email);
    List<Student> findAll();
    List<Student> findByMajor(String major);
    void update(Student student);
    void delete(Long id);
    void deleteByStudentId(String studentId);
    boolean existsByStudentId(String studentId);
    boolean existsByEmail(String email);
    long count();
}
