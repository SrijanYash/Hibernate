package com.telusko.module;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Answer")
public class answer {

        @Id
        @Column(name="answer_id")
        private Integer sid;

        private String Answer;

        public Integer getSid() {
            return sid;
        }

        public void setSid(Integer sid) {
            this.sid = sid;
        }

        public String getAnswer() {
            return Answer;
        }

        public void setAnswer(String answer) {
            Answer = answer;
        }

        @Override
        public String toString() {
            return "answer [sid=" + sid + ", Answer=" + Answer + "]";
        }

        public answer() {
            System.out.println("zero parameter constructor answer");
        }

}