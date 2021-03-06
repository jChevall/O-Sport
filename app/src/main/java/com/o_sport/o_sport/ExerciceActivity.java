package com.o_sport.o_sport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class ExerciceActivity extends AppCompatActivity {

    Programme programme;
    ArrayList<Exercice> exercices;
    Integer compteur;

    TextView textView;
    ImageView imageView;
    Button button;
    Button share;
    StorageReference storageRef;
    WrapperFireBase wrapperFireBase;
    User user;

    int pointWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            //Go to login
            startActivity(new Intent(ExerciceActivity.this, LoginActivity.class));
        }

        final String mail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    if(data.child("email").getValue().equals(mail)){
                        Map imc = (Map) data.child("imc").getValue();
                        String id = data.getKey();
                        Number pointNumber = (Number) data.child("point").getValue();
                        Integer point = pointNumber.intValue();
                        user = new User(mail, point, imc, id);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        textView = findViewById(R.id.titre);
        imageView = findViewById(R.id.tuto);
        button = findViewById(R.id.next);
        share = findViewById(R.id.share);

        programme = (Programme) getIntent().getSerializableExtra("PROGRAMME");
        exercices = new ArrayList<Exercice>();
        compteur = 0;

        storageRef = FirebaseStorage.getInstance().getReference();


        // Read from the database
        FirebaseDatabase.getInstance().getReference("exercice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    String id = (String) data.getKey();

                    if(programme.getExercices().values().contains(id)){
                        String nom = (String) data.child("nom").getValue();
                        String photoUrl = (String) data.child("photoUrl").getValue();
                        Number tempsSec = (Number) data.child("tempsSec").getValue();
                        Number point = (Number) data.child("point").getValue();
                        exercices.add(new Exercice(nom, photoUrl, tempsSec, point));
                    }
                }
                textView.setText(exercices.get(compteur).getNom());
                Context context = imageView.getContext();
                int id = context.getResources().getIdentifier(exercices.get(compteur).getPhotoUrl(), "drawable", context.getPackageName());
                imageView.setImageResource(id);
                compteur++;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exercices.size() == compteur+1 || exercices.size()==1){
                    pointWon = 0;
                    for(Exercice exo : exercices){
                        pointWon += exo.getPoint().intValue();
                    }
                    String view = "Bravo, vous avez terminé, vous avez gagné "+pointWon+" point(s)";
                    user.addpoint(pointWon);
                    textView.setText(view);

                    // Update User
                    FirebaseDatabase.getInstance().getReference("user").child(user.getId()).updateChildren(user.toMap());

                    Context context = imageView.getContext();
                    int id = context.getResources().getIdentifier("bravo", "drawable", context.getPackageName());
                    imageView.setImageResource(id);
                    button.setVisibility(View.INVISIBLE);

                    share.setVisibility(View.VISIBLE);
                    share.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent myIntent = new Intent(Intent.ACTION_SEND);
                            myIntent.setType("text/plain");
                            String shareBody = "J'ai gagné " + pointWon +" point sur O-Sport aujourd'hui !";
                            myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(myIntent,"Share using"));
                        }
                    });


                }else{


                    textView.setText(exercices.get(compteur).getNom());
                    Context context = imageView.getContext();
                    int id = context.getResources().getIdentifier(exercices.get(compteur).getPhotoUrl(), "drawable", context.getPackageName());
                    imageView.setImageResource(id);
                    compteur++;
                }
            }
        });


    }
}
