package com.o_sport.o_sport;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firestore.v1beta1.Document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static android.support.constraint.Constraints.TAG;


// Cette classe est non utilisée pour le moment.
// Elle contiendra les fonctions CRUD des différents objets de cette application
// Une autre classe "Tools" devrait aussi est développer pour concentrer les fonctions répétitives dans diverse activity
public class WrapperFireBase {
    FirebaseFirestore db;
    CollectionReference userRef;
    CollectionReference progRef;
    CollectionReference exerRef;
    CollectionReference nutrRef;

    public WrapperFireBase(){
        db = FirebaseFirestore.getInstance();
        userRef = db.collection("user");
        progRef = db.collection("programme");
        exerRef = db.collection("exercice");
        nutrRef = db.collection("nutrition");

    }

    // User
    ArrayList<User> users;
    public void createUser(User user){
    }
    public void findByNom(String nom){
    }
    public ArrayList<User> readUser(){
        return null;
    }
    public void updateUser(){}
    public void deleteUser(){}

    // Exercies CRUD
    ArrayList<Exercice> exercices;
    public void createExercice(){}
    public Exercice findExercice(String nom){
        for (Exercice exo : exercices) {
            if(exo.getNom().equals(nom))
                return exo;
        }
        return null;
    }
    public ArrayList<Exercice> readExercice(){
        return null;
    }
    public void updateExercice(){}
    public void deleteExercice(){}

    // Programme CRUD
    ArrayList<Programme> programmes;
    public void createProgramme(Programme programme){
        DocumentReference docRef = progRef.document();
        docRef.set(programme).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
    public ArrayList<Programme> readProgramme(){
        return null;
    }
    public Programme findProgramme(String nom){
        return null;
    }
    public void updateProgramme(){}
    public void deleteProgramme(){}

    // Nutrition CRUD
    public void createNutrition(){}
    public void readNutrition(){}
    public void updateNutrition(){}
    public void deleteNutrition(){}



}
