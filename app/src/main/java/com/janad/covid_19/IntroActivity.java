package com.janad.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.janad.covid_19.Adapters.IntroViewPagerAdapter;
import com.janad.covid_19.Helper.LocaleHelper;
import com.janad.covid_19.Models.ScreenItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetPeshto,btnGetDari;
    Animation btnAnim ;
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         //       WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Paper.init(this);

        final String language = Paper.book().read("language");
        if(language != null){
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
            startActivity(mainActivity);
            finish();

        }
        // when this activity is about to be launch we need to check if its openened before or not


        boolean a = restorePrefData();
        //a=false;
        /*if (restorePrefDataLanguage()) {

//            String lang = loadLocale();

            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
//            Bundle extras = new Bundle();
//            extras.putString("Selected", "lang");
//            mainActivity.putExtras(extras);
            startActivity(mainActivity);
            finish();


        }*/

        setContentView(R.layout.activity_intro);

        // hide the action bar

        // getSupportActionBar().hide();

        // ini views
        btnNext = findViewById(R.id.btn_next);
        btnGetDari= findViewById(R.id.btn_Dari);
        btnGetPeshto = findViewById(R.id.btn_Peshto);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // fill list screen

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("اضطراب","",R.drawable.ethtrab_dari));
        mList.add(new ScreenItem("سالمندان","",R.drawable.salmandan_dari));
        mList.add(new ScreenItem("دخانيات","",R.drawable.dokhaniat_dari));
        mList.add(new ScreenItem("خطاهای رایج","",R.drawable.raij));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }

                if (position == mList.size()-1) { // when we reach to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }



            }
        });

        // tablayout add change listener


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        // Get Started button click listener

        btnGetDari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().write("language","fa");
                updateView((String)Paper.book().read("language"));

                //open main activity
//                setLocale("fa");
//                saveLanguage("fa");
//                LocaleHelper.setLocale(IntroActivity.this, "fa");

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                savePrefsData();
//                Bundle b= new Bundle();
//                b.putString("Selected", "Dari");
//                intent.putExtras(b);

                startActivity(intent);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process

                finish();



            }
        });

        btnGetPeshto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity
                Paper.book().write("language","ps");
                updateView((String)Paper.book().read("language"));

//                LocaleHelper.setLocale(IntroActivity.this, "ps");

//                setLocale("ps");
//                saveLanguage("ps");

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                savePrefsData();
//                Bundle b= new Bundle();
//                b.putString("Selected", "Peshto");
//                intent.putExtras(b);

                startActivity(intent);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // i'm going to use shared preferences to that process

                finish();

            }
        });

        // skip button click listener

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });


    }

    private void updateView(String lang) {
        Context context = LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());


    }

    public String loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
        saveLanguage(language);

        return language;

    }

    private boolean restorePrefDataLanguage() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings",MODE_PRIVATE);

//        String language = pref.getString("My_Lang","");
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);

        return  isIntroActivityOpnendBefore;

    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;

    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }

    void saveLanguage(String lang){

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.putBoolean("isIntroOpnend",true);
        editor.apply();
        editor.commit();

    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetDari.setVisibility(View.VISIBLE);
        btnGetPeshto.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetDari.setAnimation(btnAnim);
        btnGetPeshto.setAnimation(btnAnim);



    }


}
