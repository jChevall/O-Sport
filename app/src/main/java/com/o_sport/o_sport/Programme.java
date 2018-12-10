package com.o_sport.o_sport;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Programme implements Serializable {
    public String nom;
    public Map exercices;

    public Programme(String nom, Map exercices) {
        this.nom = nom;
        this.exercices = exercices;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Map getExercices() {
        return exercices;
    }


    public void addExercie(String nom){
        String key = "exo" + this.exercices.size();
        this.exercices.put(key, nom);
    }

    public void removeExercice(String nom){
        if(this.exercices.containsValue(nom)){
            this.exercices.values().remove(nom);
        }
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nom", this.nom);
        result.put("exercices", this.exercices);
        return result;
    }
}
