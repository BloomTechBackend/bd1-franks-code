package com.frank.springproject.thedata;

import java.util.Objects;

// POJO we can use to send data backj and forth to server

public class UmaData {
        private String question;
        private String answer;

        public UmaData() {}

        public UmaData(String question, String answer) {
                this.question = question;
                this.answer = answer;
        }

        public String getQuestion() {
                return question;
        }
        public void setQuestion(String question) {
                this.question = question;
        }
        public String getAnswer() {
                return answer;
        }
        public void setAnswer(String answer) {
                this.answer = answer;
        }

        @Override
        public String toString() {
                return "UmaData{" +
                        "question='" + question + '\'' +
                        ", answer='" + answer + '\'' +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof UmaData)) return false;
                UmaData umaData = (UmaData) o;
                return getQuestion().equals(umaData.getQuestion()) && getAnswer().equals(umaData.getAnswer());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getQuestion(), getAnswer());
        }
}
