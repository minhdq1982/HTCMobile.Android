package com.tvo.htc.model;

/**
 * Create by Ngocji on 10/25/2018
 **/


public class AnswerSurvey {
    public int id;
    public String question;
    public String answer;
    public Object data;

    public AnswerSurvey(int id, String question, String answer, Object data) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.data = data;
    }
}
