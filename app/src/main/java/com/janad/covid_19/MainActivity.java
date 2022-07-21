package com.janad.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.janad.covid_19.Helper.LocaleHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout;

    private AlertDialog internetDialog;

    String URL_Address = "https://ahmedjanad.xyz/Materials/getBanners.php";

    int [] listLinksSlider = {R.drawable.dokhaniat_dari,
                                R.drawable.ethtrab_dari,
                                R.drawable.hararat_dari,
                                R.drawable.khata_ray_dari};

    int [] listLinksSliderPeshto = {R.drawable.dokhaniat_pashto,
                                R.drawable.ethtrab_pashto,
                                R.drawable.hararat_pashto,
                                R.drawable.khata_ray_pashto};

    String[] listNamesSlider = {"", "", "", ""};


    ArrayList<String> getBannerLinks  = new ArrayList<>();
    ArrayList<String> getBannerNames  = new ArrayList<>();

    HashMap<String, String> HashMapForURL ;

    CardView cdMask,cdPrevent, cdSym, cdCorona, cdTips, cdQuestions;

    String selectedLang;
    private Toolbar toolbar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);

        final String language = Paper.book().read("language");
//        selectedLang=language ;
        if(language ==null)
            Paper.book().write("language","fa");
//            selectedLang = "fa";

        updateView((String)Paper.book().read("language"));

        toolbar = findViewById(R.id.ToolBar_Main);
        setSupportActionBar(toolbar);

        //cdVideoPashto  = (CardView) findViewById(R.id.cdVideoMain);
        //cdPhotoPashto  = (CardView) findViewById(R.id.cdPhotoMain);
        cdMask = (CardView) findViewById(R.id.cdMask);
        cdPrevent = (CardView) findViewById(R.id.cdPrevent);
        cdSym = (CardView) findViewById(R.id.cdSym);
        cdCorona = (CardView) findViewById(R.id.cdCorona);
        cdTips = (CardView) findViewById(R.id.cdTips);
        cdQuestions = (CardView) findViewById(R.id.cdQuestions);



//        getLanguage();


        cdQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuestionsAnswers.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Questions");
                b.putString("SelectedLang", language);

                intent.putExtras(b);
                startActivity(intent);

            }
        });

        cdCorona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuestionsAnswers.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Corona");
                b.putString("SelectedLang", language);

                intent.putExtras(b);
                startActivity(intent);

            }
        });

        cdTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, TipsActivity.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Tips");
                b.putString("SelectedLang", language);

                intent.putExtras(b);
                startActivity(intent);

            }
        });

        cdSym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuestionsAnswers.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Sym");
                b.putString("SelectedLang", language);

                intent.putExtras(b);
                startActivity(intent);

            }
        });

        cdMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Mask");
                b.putString("SelectedLang", language);

                intent.putExtras(b);
                startActivity(intent);



            }
        });

        cdPrevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuestionsAnswers.class);
                Bundle b = new Bundle();
                b.putString("Selected", "Preventions");
                b.putString("SelectedLang", language);

                intent.putExtras(b);
                startActivity(intent);

            }
        });


        /*cdVideoPashto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                Bundle b = new Bundle();
                b.putString("Selected", "VideoPashto");

                intent.putExtras(b);
                startActivity(intent);



            }
        });*/

        /*cdPhotoPashto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                Bundle b = new Bundle();
                b.putString("Selected", "PhotoPashto");

                intent.putExtras(b);
                startActivity(intent);

            }
        });*/

        sliderLayout = (SliderLayout) findViewById(R.id.SliderLayout);


        if(language.equals("fa")){

            if(!isConnected(this)){

                //showCustomDialog();
                SlideNoConnection();


            }else{

                //showCustomDialog();
                SlideNoConnection();

                //getBannerImage();

            }


        }else if(language.equals("ps")){

            if(!isConnected(this)){

                //showCustomDialog();
                SlideNoConnectionPeshto();


            }else{

                //showCustomDialog();
                SlideNoConnectionPeshto();

                //getBannerImage();

            }


        }





        
    }

    private void updateView(String lang) {
        Context context = LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.txtDariLang:

                Paper.book().write("language","fa");

                updateView((String) Paper.book().read("language"));

//                LocaleHelper.setLocale(MainActivity.this, "fa");
                recreate();
//                selectedLang = "fa";
//                setLocale("fa");
//                saveLanguage("fa");
                return true;

            case R.id.txtPashtoLang:

                Paper.book().write("language","ps");

                updateView((String) Paper.book().read("language"));

//                LocaleHelper.setLocale(MainActivity.this, "ps");
                recreate();

//                selectedLang = "ps";
//                setLocale("ps");
//                saveLanguage("ps");

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());


    }

    void saveLanguage(String lang){

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.putBoolean("isIntroOpnend",true);
        editor.apply();
        editor.commit();

    }



    private void SlideNoConnectionPeshto() {

        ArrayList<Integer> getBannerLinks  = new ArrayList<>();
        ArrayList<String> getBannerNames  = new ArrayList<>();
        for (int i=0; i<listLinksSliderPeshto.length; i++){


            getBannerLinks.add(listLinksSliderPeshto[i]);
            getBannerNames.add(listNamesSlider[i]);


        }

        HashMap<String, Integer> HashMapForNoConnection;



        HashMapForNoConnection = new HashMap<String, Integer>();

        for (int i=0; i<getBannerLinks.size(); i++){



            int linkF = getBannerLinks.get(i);
            String nameF = getBannerNames.get(i);
            HashMapForNoConnection.put(nameF ,linkF );
        }

        for(String name : HashMapForNoConnection.keySet()){

            TextSliderView textSliderView = new TextSliderView(MainActivity.this);

            textSliderView
                    .description(name)
                    .image(HashMapForNoConnection.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(MainActivity.this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(MainActivity.this);



    }

    private void getLanguage() {

        //if( (getIntent().hasExtra("Selected")) ) {

//            Intent intent = getIntent();
//            Bundle extras = intent.getExtras();

//            selectedLang = loadLocale();

//            selectedLang =LocaleHelper.getPersistedDataLang(this);

//            selectedLang =extras.getString("Selected");

       //}

    }

    public String loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");


        return language;

    }

    private void showCustomDialog() {

        /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                });*/

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private boolean isConnected(MainActivity mainActivity) {

        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())){

            return true;
        }else{

            return false;
        }
    }

    private void SlideNoConnection(){

         ArrayList<Integer> getBannerLinks  = new ArrayList<>();
         ArrayList<String> getBannerNames  = new ArrayList<>();
        for (int i=0; i<listLinksSlider.length; i++){


            getBannerLinks.add(listLinksSlider[i]);
            getBannerNames.add(listNamesSlider[i]);


        }

        HashMap<String, Integer> HashMapForNoConnection;



        HashMapForNoConnection = new HashMap<String, Integer>();

        for (int i=0; i<getBannerLinks.size(); i++){



            int linkF = getBannerLinks.get(i);
            String nameF = getBannerNames.get(i);
            HashMapForNoConnection.put(nameF ,linkF );
        }

        for(String name : HashMapForNoConnection.keySet()){

            TextSliderView textSliderView = new TextSliderView(MainActivity.this);

            textSliderView
                    .description(name)
                    .image(HashMapForNoConnection.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(MainActivity.this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(MainActivity.this);



    }


    private void getBannerImage() {

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, URL_Address, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<String> getBannerLinks  = new ArrayList<>();
                ArrayList<String> getBannerNames  = new ArrayList<>();
                for(int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String link = jsonObject.getString("LINK");
                        String name = jsonObject.getString("NAME");

                        getBannerLinks.add(link);
                        getBannerNames.add(name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                HashMapForURL = new HashMap<String, String>();

                for (int i=0; i<getBannerLinks.size(); i++){

                    String linkF = getBannerLinks.get(i);
                    String nameF = getBannerNames.get(i);
                    HashMapForURL.put(nameF,linkF );
                }

                for(String name : HashMapForURL.keySet()){

                    TextSliderView textSliderView = new TextSliderView(MainActivity.this);

                    textSliderView
                            .description(name)
                            .image(HashMapForURL.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(MainActivity.this);

                    textSliderView.bundle(new Bundle());

                    textSliderView.getBundle()
                            .putString("extra",name);

                    sliderLayout.addSlider(textSliderView);
                }

                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);

                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

                sliderLayout.setCustomAnimation(new DescriptionAnimation());

                sliderLayout.setDuration(3000);

                sliderLayout.addOnPageChangeListener(MainActivity.this);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(MainActivity.this, ActivityDetails.class);
        Bundle b = new Bundle();
        String sliderID = (String) slider.getBundle().get("extra");
        b.putString("Selected", sliderID);

        intent.putExtras(b);
        startActivity(intent);*/


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
