package com.telusko.module;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class question {

        @Id
        @Column(name="question_id")
        private Integer sid;

        private String Question; 

        @OneToOne(cascade=CascadeType.ALL)
        private answer answer;

        @Override
        public String toString() {
            return "Question [sid=" + sid + ", Question=" + Question + ", answer=" + answer + "]";
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

        public answer getAnswer() {
            return answer;
        }

        public void setQuestion(String Question) {
            this.Question = Question;
        }

        public void setAnswer(answer answer) {
            this.answer = answer;
        }

        public question() {
            System.out.println("zero parameter constructor question");
        }

        

}
