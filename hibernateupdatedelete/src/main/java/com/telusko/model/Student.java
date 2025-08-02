package com.telusko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="studenttable")//tells the table name
// @Cacheable
// @Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Student {

    //declaring primary key
    @Id
    @Column(name="SID")
    private Integer sid;

    @Column(name="SNAME")
    private String sName; 

    @Column(name="SCITY")
    private String scity;

    public Student()
    {
        System.out.println("Zero parameter");
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getScity() {
        return scity;
    }

    public void setScity(String scity) {
        this.scity = scity;
    }

    @Override
    public String toString() {
        return "Student [sid=" + sid + ", sName=" + sName + ", scity=" + scity + "]";
    }
}
