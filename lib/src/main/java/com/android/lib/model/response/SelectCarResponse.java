package com.android.lib.model.response;

import java.util.List;

public class SelectCarResponse extends BaseResponse {

    private List<Item> data;

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    public static class Item {
        /**
         * questionNo : 1
         * question : 1. Gia đình bạn đang sống có bao nhiêu người?
         * answers : [{"answerLetter":"a","answer":"a. 1-3 người"},{"answerLetter":"b","answer":"b. 4-5 người"},{"answerLetter":"c","answer":"c. Nhiều hơn 5 người"}]
         */

        private int questionNo;
        private String question;
        private List<Answer> answers;

        public int getQuestionNo() {
            return questionNo;
        }

        public void setQuestionNo(int questionNo) {
            this.questionNo = questionNo;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

        public static class Answer {
            /**
             * answerLetter : a
             * answer : a. 1-3 người
             */

            private String answerLetter;
            private String answer;

            public String getAnswerLetter() {
                return answerLetter;
            }

            public void setAnswerLetter(String answerLetter) {
                this.answerLetter = answerLetter;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }
    }
}
