package com.telusko.model;

//creating the table
@javax.persistence.Entity
@javax.persistence.Table(name="sable")//tells the table name
public class Student {

    //declaring primary key
    @javax.persistence.Id
    @javax.persistence.Column(name="SID")
    private Integer sid;

    @javax.persistence.Column(name="SNAME")
    private String sName;

    @javax.persistence.Column(name="SCITY")
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
