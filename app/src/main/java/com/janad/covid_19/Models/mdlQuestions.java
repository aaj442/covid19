package com.janad.covid_19.Models;

public class mdlQuestions {

    private String txtQuestions;
    private String txtAnswers;

    public mdlQuestions(){



    }

    public mdlQuestions(String txtQuestions, String txtAnswers) {
        this.txtQuestions = txtQuestions;
        this.txtAnswers = txtAnswers;
    }

    public String getTxtQuestions() {
        return txtQuestions;
    }

    public void setTxtQuestions(String txtQuestions) {
        this.txtQuestions = txtQuestions;
    }

    public String getTxtAnswers() {
        return txtAnswers;
    }

    public void setTxtAnswers(String txtAnswers) {
        this.txtAnswers = txtAnswers;
    }
}
