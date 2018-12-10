package com.o_sport.o_sport;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import im.dacer.androidcharts.LineView;

import static android.support.constraint.Constraints.TAG;

public class suiviActivity extends AppCompatActivity {

    WrapperFireBase wrapperFireBase;
    User user;
    ArrayList<String> strList;
    ArrayList<Float> dataLists;

    TextView textView;

    Button share;

    int pointWon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);

        wrapperFireBase = new WrapperFireBase();

        textView = findViewById(R.id.nbPoint);
        share = findViewById(R.id.share);

        final String mail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        wrapperFireBase.getUserRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    if(data.child("email").getValue().equals(mail)){
                        Map imc = (Map) data.child("imc").getValue();
                        String id = data.getKey();
                        Number pointNumber = (Number) data.child("point").getValue();
                        Integer point = pointNumber.intValue();
                        user = new User(mail, point, imc, id);
                        pointNumber = point;
                        textView.setText("Vous avec "+point+" point(s)");
                        createGraph();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Je dispose d'un total de " + pointWon + " point sur O-Sport et toi ? ;) ";
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent,"Share using"));
            }
        });
    }

    private void createGraph(){

        LineView lineView = (LineView)findViewById(R.id.line_view);

        if(user.getImc().size() == 0){
            ArrayList<String> strs = new ArrayList<>();
            strs.add("Pas de données d'IMC");
            lineView.setBottomTextList(strs);
        }else {
            Map<String, String> imc = user.getImc();
            Map<String, String> treeMap = new TreeMap<>(imc);

            strList = new ArrayList<>();
            dataLists = new ArrayList<>();

            for (Map.Entry<String, String> entry : treeMap.entrySet())
            {
                strList.add(getDate(Long.parseLong(entry.getKey())));
                dataLists.add(Float.parseFloat(entry.getValue()));
            }

            ArrayList<ArrayList<Float>> data = new ArrayList<ArrayList<Float>>();
            data.add(dataLists);
            lineView.setDrawDotLine(false); //optional
            lineView.setShowPopup(LineView.SHOW_POPUPS_All); //optional
            lineView.setBottomTextList(strList);
            lineView.setColorArray(new int[]{Color.BLACK,Color.GREEN,Color.GRAY,Color.CYAN});
            lineView.setFloatDataList(data); //or lineView.setFloatDataList(floatDataLists)
        }
    }

    private String getDate(long timeStamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private ArrayList<String> sortByDate(ArrayList<String> dates){
        Collections.sort(dates);
        return dates;
    }

}
