package com.frank.springproject.thedata;

import java.util.Objects;

public class Answer {
        private String theAnswer;

        public Answer() {}

        public String getTheAnswer() {
                return theAnswer;
        }

        public void setTheAnswer(String theAnswer) {
                this.theAnswer = theAnswer;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Answer)) return false;
                Answer answer = (Answer) o;
                return Objects.equals(getTheAnswer(), answer.getTheAnswer());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getTheAnswer());
        }

        @Override
        public String toString() {
                return "Answer{" +
                        "theAnswer='" + theAnswer + '\'' +
                        '}';
        }
}
