package com.janad.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class ActivityDetails extends AppCompatActivity {

    TextView txtDetail1, txtDetail2, txtDetail3;

    String selectedItems;
    public static String SelectedLang;

    int selectPos= -1;
    private SubsamplingScaleImageView img_detail;
    private Toolbar toolbar;
    private ImageView toolBackArrow;
    private String selectedPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txtDetail1 = (TextView) findViewById(R.id.txtdetail1);
        txtDetail2 = (TextView) findViewById(R.id.txtdetail2);
        txtDetail3 = (TextView) findViewById(R.id.txtdetail3);

//        img_detail = (ImageView) findViewById(R.id.img_detail);
        img_detail =   findViewById(R.id.img_detail);

        getIncomingIntent();

        toolbar = findViewById(R.id.toolbar);
        toolBackArrow = toolbar.findViewById(R.id.toolBackArrow);

        toolBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityDetails.this, VideoActivity.class);
                Bundle extras = new Bundle();
                if(selectedItems.equals("Posters")) {
                    extras.putString("Selected", "Posters");
                    extras.putString("SelectedLang", SelectedLang);
                }else if(selectedItems.equals("Mask")){
                    extras.putString("Selected", "Mask");
                    extras.putString("SelectedLang", SelectedLang);

                }
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });
    }


    private void getIncomingIntent() {

        if(getIntent().hasExtra("Selected") && !(getIntent().hasExtra("SelectedPos"))){

            Intent intent = getIntent();
            Bundle b = intent.getExtras();

            selectedItems = b.getString("Selected");

            SelectedLang = b.getString("SelectedLang");

//            selectedPage = b.getString("SelectedItems");


            if(selectedItems.equals("Hararat")){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.hararat_dari));
                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.hararat_pashto));

                }

            }else if (selectedItems.equals("Ethtrab")){

                if(SelectedLang.equals("fa")) {
                    img_detail.setImage(ImageSource.resource(R.drawable.ethtrab_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.ethtrab_pashto));

                }

            }else if (selectedItems.equals("Dokhaniat")){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.dokhaniat));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.dokhaniat_pashto));

                }


            }else if (selectedItems.equals("KhataRay")) {

                if(SelectedLang.equals("fa")) {
                    img_detail.setImage(ImageSource.resource(R.drawable.khata_ray));

                }
                else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.khata_ray_pashto));

                }


            }

        }else if (getIntent().hasExtra("SelectedPos") && getIntent().hasExtra("Selected")){

            Intent intent = getIntent();
            Bundle b = intent.getExtras();

            selectedItems = b.getString("Selected");
            selectPos = b.getInt("SelectedPos");
            SelectedLang = b.getString("SelectedLang");


            if (selectedItems.equals("Mask") && selectPos == 0){

                if(SelectedLang.equals("fa")) {
                    img_detail.setImage(ImageSource.resource(R.drawable.face_mask_poster));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.face_mask_pashto));
                }

            }else if (selectedItems.equals("Mask") && selectPos == 1){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.wash_face_mask));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.wash_face_mask_pashto));


                }

            }else if (selectedItems.equals("Mask") && selectPos == 2){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.mistakes_wearing_mask));


                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.mistakes_wearing_mask_pashto));


                }

            }else if (selectedItems.equals("Posters") && selectPos == 0){

                if(SelectedLang.equals("fa")) {
                    img_detail.setImage(ImageSource.resource(R.drawable.animal));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.animal_pashto));


                }

            }else if (selectedItems.equals("Posters") && selectPos == 1){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.common_mistakes));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.common_mistakes));


                }

            }else if (selectedItems.equals("Posters") && selectPos == 2){

                if(SelectedLang.equals("fa")) {
                    img_detail.setImage(ImageSource.resource(R.drawable.remain_in_surfaces));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.remain_in_surfaces_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 3){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.medication));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.medication_pashto));

                }


            }else if (selectedItems.equals("Posters") && selectPos == 4){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.currency));


                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.currency_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 5){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.shoes));


                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.shoes_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 6){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.elderly_people));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.elderly_people));


                }

            }else if (selectedItems.equals("Posters") && selectPos == 7){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.water));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.water_pashto));


                }

            }else if (selectedItems.equals("Posters") && selectPos == 8){

                if(SelectedLang.equals("fa")) {
                    img_detail.setImage(ImageSource.resource(R.drawable.dokhaniat_dari));


                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.dokhaniat_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 9){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.hararat_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.hararat_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 10){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.kashnda_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.kashnda_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 11){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.ethtrab_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.ethtrab_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 12){

                if(SelectedLang.equals("fa")) {
                    img_detail.setImage(ImageSource.resource(R.drawable.khata_ray_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.khata_ray_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 13){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.gharghara_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.gharghara_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 14){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.shistn_dastan_dari));


                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.shistn_dastan_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 15){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.sair_dari));


                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.sair_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 16){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.salmandan_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.salamandan_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 17){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.fasla_kathari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.fasla_kathari_pashto));

                }

            }else if (selectedItems.equals("Posters") && selectPos == 18){

                if(SelectedLang.equals("fa")) {

                    img_detail.setImage(ImageSource.resource(R.drawable.frzndan_dari));

                }else if(SelectedLang.equals("ps")){

                    img_detail.setImage(ImageSource.resource(R.drawable.frzndan_pashto));

                }

            }


        }
    }
}
