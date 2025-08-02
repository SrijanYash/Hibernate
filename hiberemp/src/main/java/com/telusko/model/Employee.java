package com.telusko.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Employee {

    @Id
    private Integer Id;

    private String Name;

    private Integer Salary;

    public Employee(Integer id, String name, Integer salary) {
        this.Id = id;
        this.Name = name;
        this.Salary = salary;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer Salary) {
        this.Salary = Salary;
    }

    @Override
    public String toString() {
        return "Employee [Id=" + Id + ", Name=" + Name + ", Salary=" + Salary + "]";
    }

    // public void create(int i, String n, int s)
    // {
    //     Id=i;
    //     Name=n;
    //     Salary=s;
    // }

}
