package com.janad.covid_19.Models;

public class mdlDetails {

    private String textHeading1, txtHeading2, txtDetails;
    private int imageUrl;

    public mdlDetails(String text, String txtRight, String txtRight2, int imageUrl) {
        this.textHeading1 = text;
        this.txtHeading2 = txtRight;
        this.txtDetails = txtRight2;
        this.imageUrl = imageUrl;
    }


    public mdlDetails() {

    }

    public String getTextHeading1() {
        return textHeading1;
    }

    public void setTextHeading1(String textHeading1) {
        this.textHeading1 = textHeading1;
    }

    public String getTxtHeading2() {
        return txtHeading2;
    }

    public void setTxtHeading2(String txtHeading2) {
        this.txtHeading2 = txtHeading2;
    }

    public String getTxtDetails() {
        return txtDetails;
    }

    public void setTxtDetails(String txtDetails) {
        this.txtDetails = txtDetails;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
