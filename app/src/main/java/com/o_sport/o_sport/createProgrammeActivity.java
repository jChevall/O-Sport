package com.o_sport.o_sport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.type.Color;

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class createProgrammeActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Map exercices;
    Programme programme;
    Button buttonOk;
    EditText editText;
    WrapperFireBase wrapperFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_programme);

        wrapperFireBase = new WrapperFireBase();

        linearLayout = findViewById(R.id.linearLayout);
        buttonOk = findViewById(R.id.buttonOk);
        editText = findViewById(R.id.progName);

        exercices = new HashMap();
        programme = new Programme("name", new HashMap());

        wrapperFireBase.getExerRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    final String nom = (String) data.getKey();

                    final Button button = new Button(createProgrammeActivity.this);

                    button.setText(nom);
                    linearLayout.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(programme.getExercices().containsValue(nom)){
                                button.setBackgroundColor(android.graphics.Color.parseColor("#D5D8DC"));
                                programme.removeExercice(nom);
                            }else{
                                button.setBackgroundColor(android.graphics.Color.parseColor("#58D68D"));
                                programme.addExercie(nom);
                            }
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

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programme.setNom(editText.getText().toString());
                Map<String, Object> prog = programme.toMap();
                wrapperFireBase.getProgRef().child(programme.getNom()).updateChildren(prog);
                startActivity(new Intent(createProgrammeActivity.this, ProgrammeActivity.class));
                //wrapperFireBase.createProgramme(programme);
            }
        });
    }
}
