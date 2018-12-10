package com.o_sport.o_sport;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static android.support.constraint.Constraints.TAG;

public class WrapperFireBase {
    private FirebaseDatabase db;
    private DatabaseReference userRef;
    private DatabaseReference progRef;
    private DatabaseReference exerRef;
    private DatabaseReference nutrRef;
    private User user;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;

    public WrapperFireBase(){
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        db = FirebaseDatabase.getInstance();
        userRef = db.getReference("user");
        progRef = db.getReference("programme");
        exerRef = db.getReference("exercice");
        nutrRef = db.getReference("nutrition");

    }

    public DatabaseReference getUserRef() {
        return userRef;
    }

    public DatabaseReference getProgRef() {
        return progRef;
    }

    public DatabaseReference getExerRef() {
        return exerRef;
    }

    public DatabaseReference getNutrRef() {
        return nutrRef;
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

}
