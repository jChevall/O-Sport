package com.o_sport.o_sport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class ImcActivity extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private TextView result;
    Button calculIMC;
    WrapperFireBase wrapperFireBase;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        height =(EditText) findViewById(R.id.height);
        weight =(EditText) findViewById(R.id.weight);
        result=(TextView) findViewById(R.id.result);
        calculIMC=(Button) findViewById(R.id.calc);

        // Get User
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            //Go to login
            startActivity(new Intent(ImcActivity.this, LoginActivity.class));
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

        calculIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI(v);
            }
        });
    }

    public void calculateBMI(View v){
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)){
            float heightValue=Float.parseFloat(heightStr)/100;
            float weightValue=Float.parseFloat(weightStr);

            float bmi=weightValue / (heightValue * heightValue);

            displayBMI(bmi);

            user.addImc(bmi);

            // Update User
            Map map = user.toMap();
            FirebaseDatabase.getInstance().getReference("user").child(user.getId()).updateChildren(user.toMap());

        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel="";

        if (Float.compare(bmi,15f) <= 0){
            bmiLabel=getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi,15f) > 0 && Float.compare(bmi,16f) <= 0){
            bmiLabel=getString(R.string.severely_underweight);
        } else if (Float.compare(bmi,16f) > 0 && Float.compare(bmi,18.5f) <= 0){
            bmiLabel=getString(R.string.underweight);
        } else if (Float.compare(bmi,18.5f) > 0 && Float.compare(bmi,25f) <= 0){
            bmiLabel=getString(R.string.normal);
        } else if (Float.compare(bmi,25f) > 0 && Float.compare(bmi,30f) <= 0){
            bmiLabel=getString(R.string.overweight);
        } else if (Float.compare(bmi,30f) > 0 && Float.compare(bmi,35f) <= 0){
            bmiLabel=getString(R.string.obese_class_i);
        } else if (Float.compare(bmi,35f) > 0 && Float.compare(bmi,40f) <= 0){
            bmiLabel =getString(R.string.obese_class_ii);
        } else{
            bmiLabel =getString(R.string.obese_class_iii);
        }
        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }
}
