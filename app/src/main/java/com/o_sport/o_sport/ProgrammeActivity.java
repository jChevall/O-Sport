package com.o_sport.o_sport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.api.ExperimentalOrBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class ProgrammeActivity extends AppCompatActivity {

    private LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme);

        linearLayout = findViewById(R.id.linearLayout);

        FirebaseDatabase.getInstance().getReference("programme").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    String nom = (String) data.child("nom").getValue();
                    Map exercices = (Map) data.child("exercices").getValue();
                    final Programme programme = new Programme(nom, exercices);

                    Button button = new Button(ProgrammeActivity.this);

                    button.setText(programme.getNom());
                    linearLayout.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ExerciceActivity.class);
                            intent.putExtra("PROGRAMME", programme);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
}
