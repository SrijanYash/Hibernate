package com.srijan.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    
    @Column(name = "student_id", unique = true, nullable = false, length = 20)
    private String studentId;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "phone", length = 15)
    private String phone;
    
    @Column(name = "major", length = 100)
    private String major;
    
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
    
    // Constructors
    public Student() {}
    
    public Student(String firstName, String lastName, String email, String studentId, 
                   LocalDate dateOfBirth, String phone, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentId = studentId;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.major = major;
        this.enrollmentDate = LocalDate.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
    
    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s %s', studentId='%s', email='%s', major='%s', enrollmentDate=%s}",
                id, firstName, lastName, studentId, email, major, enrollmentDate);
    }
}
