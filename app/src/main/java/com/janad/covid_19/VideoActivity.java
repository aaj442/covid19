package com.janad.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.janad.covid_19.Adapters.videoPasAdapter;
import com.janad.covid_19.Models.mdlVideo;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity implements videoPasAdapter.onVideoClickListener{

    videoPasAdapter mVideoPasAdapter;
    RecyclerView recyclerView;
    private ArrayList<mdlVideo> list = new ArrayList<>();

    TextView txtTitleHeading;

    String selectedItems;

    String[] txtChoosVideoPashto = {

            "انتقال",
            "كرونا شرم نيست",
            "شيردهى",
            "جلوگيری",
            "علايم",

            "تغذيه سالم و سيستم ايمنى بدن",
            "سلامت خريد وسايل مورد نياز",
            "سلامت غذايى",
            "صحت مواد غذايى",

    };

    String[] txtMaskItems = {
            "ماسك تكه اى",
            "شستن ماسك هاى صورت پارچه اى",
            " اشتباهات بوشيدن ماسك"

    };

    String[] txtMaskItemsPeshto = {
            "ماسك تكه اى",
            "شستن ماسك هاى صورت پارچه اى",
            " اشتباهات بوشيدن ماسك"

    };

    String[] txtTipsItems = {

            "حيوانات و ويروس كرونا",
            "خطاهاى رايج",
            "مدت زمان ماندن برروى سطح",
            "تداوى",
            "ارزهاى كاغذى",
            "كفش ها",
            "افراد سالخورده",
            "آب",

            "دخانيات",
            "حرارت",
            "كشنده",
            "اضطراب",
            "خطاهاى رايج",
            "غرغره",
            "شستن دستان",
            "سير",
            "سالمندان",
            "فاصله گذارى",
            "فرزندان"


    };

    String selectType;
    public static String SelectedLang;

    ImageView ivListActivity;
    Toolbar toolbar;

    ImageView toolBackArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        toolbar = findViewById(R.id.toolbar);
        toolBackArrow = toolbar.findViewById(R.id.toolBackArrow);

        toolBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VideoActivity.this, MainActivity.class);
                Bundle extras = new Bundle();
                extras.putString("Selected",SelectedLang);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rvVideo);
        txtTitleHeading = (TextView) findViewById(R.id.txtListActivity);
        ivListActivity = (ImageView) findViewById(R.id.ivListActivity);


        getIncomingIntent();
    }

    private void getIncomingIntent() {

        if(getIntent().hasExtra("Selected")){

            Intent intent = getIntent();
            Bundle b = intent.getExtras();

            selectedItems = b.getString("Selected");

            SelectedLang = b.getString("SelectedLang");

            /*if(selectedItems.equals("VideoPashto")){

                //txtTitleHeading.setText("");

                mVideoPasAdapter = new videoPasAdapter(getVideoLists(),this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                DividerItemDecoration DID = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
                DID.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.line_divider));
                recyclerView.addItemDecoration(DID);
                recyclerView.setAdapter(mVideoPasAdapter);
            }*/

            if(selectedItems.equals("Mask")){

                selectType = "Mask";

                txtTitleHeading.setText(getResources().getString(R.string.mask));
                ivListActivity.setImageResource(R.drawable.face_mask);

                if(SelectedLang.equals("fa")) {

                    mVideoPasAdapter = new videoPasAdapter(getMaskItems(), this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    DividerItemDecoration DID = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
                    DID.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.line_divider));
                    recyclerView.addItemDecoration(DID);
                    recyclerView.setAdapter(mVideoPasAdapter);

                }
                else if(SelectedLang.equals("ps")){

                    mVideoPasAdapter = new videoPasAdapter(getMaskItemsPeshto(), this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    DividerItemDecoration DID = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
                    DID.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.line_divider));
                    recyclerView.addItemDecoration(DID);
                    recyclerView.setAdapter(mVideoPasAdapter);


                }


            }else if (selectedItems.equals("Tips")){

                selectType = "Tips";

                txtTitleHeading.setText(getResources().getString(R.string.tips));
                ivListActivity.setImageResource(R.drawable.tips);

                mVideoPasAdapter = new videoPasAdapter(getTipsItems(),this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                DividerItemDecoration DID = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
                DID.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.line_divider));
                recyclerView.addItemDecoration(DID);
                recyclerView.setAdapter(mVideoPasAdapter);



            }else if (selectedItems.equals("Videos")){

                selectType = "Videos";

                txtTitleHeading.setText(getResources().getString(R.string.videos));
                ivListActivity.setImageResource(R.drawable.video_gallery);

                mVideoPasAdapter = new videoPasAdapter(getVideoLists(),this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                DividerItemDecoration DID = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
                DID.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.line_divider));
                recyclerView.addItemDecoration(DID);
                recyclerView.setAdapter(mVideoPasAdapter);



            }else if (selectedItems.equals("Posters")){

                selectType = "Posters";

                txtTitleHeading.setText(getResources().getString(R.string.posters));
                ivListActivity.setImageResource(R.drawable.image_gallery);

                mVideoPasAdapter = new videoPasAdapter(getTipsItems(),this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                DividerItemDecoration DID = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
                DID.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.line_divider));
                recyclerView.addItemDecoration(DID);
                recyclerView.setAdapter(mVideoPasAdapter);



            }

        }
    }

    private ArrayList<mdlVideo> getVideoLists() {

        list = new ArrayList<>();

        for(int i=0; i<txtChoosVideoPashto.length; i++){
            mdlVideo m = new mdlVideo();

            m.setTitle(txtChoosVideoPashto[i]);
            list.add(m);
        }
        return list;
    }

    private ArrayList<mdlVideo> getMaskItems(){

        list = new ArrayList<>();

        for(int i=0; i<txtMaskItems.length; i++){
            mdlVideo m = new mdlVideo();

            m.setTitle(txtMaskItems[i]);
            list.add(m);
        }
        return list;


    }

    private ArrayList<mdlVideo> getMaskItemsPeshto(){

        list = new ArrayList<>();

        for(int i=0; i<txtMaskItemsPeshto.length; i++){
            mdlVideo m = new mdlVideo();

            m.setTitle(txtMaskItemsPeshto[i]);
            list.add(m);
        }
        return list;


    }

    private ArrayList<mdlVideo> getTipsItems(){

        list = new ArrayList<>();

        for(int i=0; i<txtTipsItems.length; i++){
            mdlVideo m = new mdlVideo();

            m.setTitle(txtTipsItems[i]);
            list.add(m);
        }
        return list;


    }

    @Override
    public void onClick(int position) {

        //Toast.makeText(this, "Click: "+ position, Toast.LENGTH_SHORT).show();

        if (selectType.equals("Tips")) {

            Intent intent = new Intent(this, VideoSubActivity.class);
            Bundle b = new Bundle();
            b.putInt("Selected", position);
            b.putString("SelectedLang", SelectedLang);
            intent.putExtras(b);
            startActivity(intent);

        }else if (selectType.equals("Mask")){


            Intent intent = new Intent(this, ActivityDetails.class);
            Bundle b = new Bundle();
            b.putString("Selected", selectType);
            b.putInt("SelectedPos",position);
            b.putString("SelectedLang", SelectedLang);
            b.putString("SelectedItems", selectedItems);
            intent.putExtras(b);
            startActivity(intent);

        }else if (selectType.equals("Videos")){


            Intent intent = new Intent(this, VideoSubActivity.class);
            Bundle b = new Bundle();
            b.putInt("Selected", position);
            b.putString("SelectedLang", SelectedLang);
            intent.putExtras(b);
            startActivity(intent);

        }else if (selectType.equals("Posters")){


            Intent intent = new Intent(this, ActivityDetails.class);
            Bundle b = new Bundle();
            b.putString("Selected", selectType);
            b.putInt("SelectedPos",position);
            b.putString("SelectedLang", SelectedLang);
            b.putString("SelectedItems", selectedItems);
            intent.putExtras(b);
            startActivity(intent);

        }

    }
}
