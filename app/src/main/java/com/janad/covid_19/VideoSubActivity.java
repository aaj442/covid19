package com.janad.covid_19;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.janad.covid_19.Adapters.videoPasAdapter;

public class VideoSubActivity extends AppCompatActivity {


    int selectedValue;

    String selectedLang;

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_sub);



        videoView = findViewById(R.id.video_view);

        getIncomingIntent();

    }


    void getIncomingIntent(){

        String videoPath=null;
        MediaController mediaController;

        Uri uri=null;

        if(getIntent().hasExtra("Selected")){

            Intent intent = getIntent();
            Bundle b = intent.getExtras();

            selectedValue = b.getInt("Selected");

            selectedLang = b.getString("SelectedLang");



            if(selectedValue == 0){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.dari_entiqal;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pashto_entiqal;
                }


            }else if(selectedValue == 1){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.dari_sharm;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.korona_sharm_nist_pashto;
                }



            }else if(selectedValue == 2){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.dari_shir;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.shir_dehi_pashto;
                }

            }else if(selectedValue == 3){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.dari_jelakori;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pashto_jelakori;
                }


            }else if(selectedValue == 4){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.dari_alaim;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pashto_alaim;
                }


            }else if(selectedValue == 5){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.healthy_eating;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.healthy_eating_pashto;
                }


            }else if(selectedValue == 6){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.healthy_buy;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.healthy_buy_pashto;
                }


            }else if(selectedValue == 7){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.food_health;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.food_health_pashto;
                }


            }else if(selectedValue == 8){

                if(selectedLang.equals("fa")) {
                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.food_accuracy;
                }else if(selectedLang.equals("ps")){

                    videoPath = "android.resource://" + getPackageName() + "/" + R.raw.food_accuracy_pashto;
                }


            }



            else {
                //videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pashto_alaim;

                Toast.makeText(this, "No Items", Toast.LENGTH_SHORT).show();

            }



            uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);

            mediaController = new MediaController(this);

            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);

        }



    }

}
