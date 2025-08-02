package com.srijan.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.srijan.model.Student;
import com.srijan.repository.StudentRepository;

public class StudentService {
    private final StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public void addStudent(String firstName, String lastName, String email, String studentId,
                          LocalDate dateOfBirth, String phone, String major) {
        
        // Validation
        if (studentRepository.existsByStudentId(studentId)) {
            throw new IllegalArgumentException("Student ID already exists: " + studentId);
        }
        
        if (studentRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
        
        Student student = new Student(firstName, lastName, email, studentId, dateOfBirth, phone, major);
        studentRepository.save(student);
    }
    
    public Optional<Student> findStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }
    
    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }
    
    public void updateStudent(Student student) {
        studentRepository.update(student);
    }
    
    public void deleteStudentByStudentId(String studentId) {
        studentRepository.deleteByStudentId(studentId);
    }
    
    public long getStudentCount() {
        return studentRepository.count();
    }
}