package com.o_sport.o_sport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        Button bt1=(Button)findViewById(R.id.button);
        Button bt2=(Button)findViewById(R.id.button2);
        Button bt3=(Button)findViewById(R.id.button3);

        //Surpoids
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt=(TextView)findViewById(R.id.textViewXML);
                String contenu = "-Fixez-vous un but et un échéancier. Ceux-ci doivent être réalistes et ne pas nuire à votre santé\n" +
                        "-Bougez plus\n" +
                        "-Mangez équilibré\n" +
                        "-Réduisez vos portions\n" +
                        "-Éviter les sucres rapides (bonbons, pâtisseries, sodas...) \n" +
                        "-Buvez beaucoup d'eau\n" +
                        "-Déjeunez\n" +
                        "-Réduisez votre consommation d'alcool\n" +
                        "-Eviter de manger de la JunkFood\n" +
                        "-Faire de l'activité physique régulierement avec O'Sport pour prendre du muscle";
                txt.setText(contenu);
            }
        });
        //Normal
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt=(TextView)findViewById(R.id.textViewXML);
                String contenu = "-Faire une activité physique régulière avec O'Sport pour ne pas prendre de poids\n" +
                        "-Mangez équilibré\n" +
                        "-privilégier les sucres lents (légumes secs, féculents), les légumes, les fruits, le poisson, les crudités\n";
                txt.setText(contenu);
            }
        });
        //Maigre
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt=(TextView)findViewById(R.id.textViewXML);
                String contenu = "-Stimuler l'appétit\n" +
                        "-Consommer des aliments à densité énergétique élevée\n" +
                        "-Limiter les aliments à très faible densité énergétique\n" +
                        "-Augmenter l'apport en calories sans augmenter le volume des repas\n" +
                        "-Intégrer des collations\n" +
                        "-Faire de l'activité physique avec O'Sport ";
                txt.setText(contenu);
            }
        });
    }
}
