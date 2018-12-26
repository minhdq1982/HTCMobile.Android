package com.tvo.htc.model;

import android.text.InputType;

import com.tvo.htc.view.component.CpnSurvey;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Ngocji on 10/26/2018
 **/


public class Question {
    private CpnSurvey.Type type = CpnSurvey.Type.TEXT;
    private boolean require = false;
    private String question = "";
    private String description = "";
    private int inputType = InputType.TYPE_CLASS_TEXT;
    private List<String> answers = new ArrayList<>();
    private List<String> subAnswer = new ArrayList<>();

    private String dataPref;

    private int selection = -1;
    private boolean multiSelection = false;

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public boolean isMultiSelection() {
        return multiSelection;
    }

    public void setMultiSelection(boolean multiSelection) {
        this.multiSelection = multiSelection;
    }

    public List<String> getSubAnswer() {
        return subAnswer;
    }

    public void setSubAnswer(List<String> subAnswer) {
        this.subAnswer = subAnswer;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public void setType(CpnSurvey.Type type) {
        this.type = type;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public CpnSurvey.Type getType() {
        return type;
    }

    public boolean isRequire() {
        return require;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getDataPref() {
        return dataPref;
    }

    public void setDataPref(String dataPref) {
        this.dataPref = dataPref;
    }

    public String getDefaultAnswer() {
        return "";
    }
}
