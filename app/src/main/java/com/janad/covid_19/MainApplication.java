package com.janad.covid_19;

import android.app.Application;
import android.content.Context;

import com.janad.covid_19.Helper.LocaleHelper;

public class MainApplication extends Application {


    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(LocaleHelper.onAttach(base,"fa"));
    }


}




