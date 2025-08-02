package com.telusko.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AnswerTable {

        @Id
        @Column(name="answer_id")
        private Integer sid;

        private String Answer;

        @ManyToOne(cascade=CascadeType.ALL)
        private QuestionTable questionTable;

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
            return "AnswerTable [sid=" + sid + ", Answer=" + Answer + ", questionTable=" + questionTable + "]";
        }

        public AnswerTable() {
            System.out.println("zero parameter constructor answer");
        }

    public QuestionTable getQuestionTable() {
        return questionTable;
    }

    public void setQuestionTable(QuestionTable questionTable) {
        this.questionTable = questionTable;
    }

}