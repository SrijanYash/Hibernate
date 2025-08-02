package com.telusko.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
// import javax.persistence.Table;

@Entity
public class QuestionTable {

        @Id
        @Column(name="question_id")
        private Integer sid;

        private String Question; 

        @OneToMany(cascade=CascadeType.ALL)
        private List<AnswerTable> answerList;

        @Override
        public String toString() {
            return "QuestionTable [sid=" + sid + ", Question=" + Question + ", answerList=" + answerList + "]";
        }

        public void setSid(Integer sid) {
            this.sid = sid;
        }

        public Integer getSid() {
            return sid;
        }

        public String getQuestion() {
            return Question;
        }

        public void setQuestion(String Question) {
            this.Question = Question;
        }

        public QuestionTable() {
            System.out.println("zero parameter constructor question");
        }

    public List<AnswerTable> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerTable> answerList) {
        this.answerList = answerList;
    }

        

}
