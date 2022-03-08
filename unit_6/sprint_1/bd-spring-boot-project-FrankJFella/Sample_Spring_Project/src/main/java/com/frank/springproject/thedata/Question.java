package com.frank.springproject.thedata;

import java.util.Objects;

public class Question {
        private String questionText;

        public Question() {}

        public String getQuestionText() {
                return questionText;
        }

        public void setQuestionText(String questionText) {
                this.questionText = questionText;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Question)) return false;
                Question question = (Question) o;
                return getQuestionText().equals(question.getQuestionText());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getQuestionText());
        }

        @Override
        public String toString() {
                return "Question{" +
                        "questionText='" + questionText + '\'' +
                        '}';
        }
}
