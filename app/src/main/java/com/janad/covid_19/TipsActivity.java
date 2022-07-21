package com.janad.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.janad.covid_19.Adapters.videoPasAdapter;
import com.janad.covid_19.Models.mdlVideo;

import java.util.ArrayList;

public class TipsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView toolBackArrow;

    CardView cdVideo, cdPoster;

    String SelectedLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        toolbar = findViewById(R.id.toolbar);
        toolBackArrow = toolbar.findViewById(R.id.toolBackArrow);

        toolBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipsActivity.this, MainActivity.class);
                startActivity(intent);
                Bundle extras = new Bundle();
                extras.putString("Selected",SelectedLang);
                intent.putExtras(extras);
                finish();
            }
        });

        getIncomingIntent();



        cdVideo = findViewById(R.id.cdVideo);
        cdPoster = findViewById(R.id.cdPosters);


        cdVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipsActivity.this, VideoActivity.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Videos");
                b.putString("SelectedLang", SelectedLang);
                intent.putExtras(b);

                startActivity(intent);


            }
        });


        cdPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipsActivity.this, VideoActivity.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Posters");
                b.putString("SelectedLang", SelectedLang);
                intent.putExtras(b);

                startActivity(intent);

            }
        });



    }

    private void getIncomingIntent() {

        //if(getIntent().hasExtra("Selected")) {

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            SelectedLang = extras.getString("SelectedLang");
       // }
    }


}
