package com.janad.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {


    private static int timeout= 3000;
    TextView txt;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        txt= (TextView)findViewById(R.id.txtLogo) ;
        iv= (ImageView) findViewById(R.id.ivLogo);


        Animation animation = AnimationUtils.loadAnimation(Splash.this,R.anim.myanim);
        txt.startAnimation(animation);
        iv.startAnimation(animation);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        },timeout);
    }
}
