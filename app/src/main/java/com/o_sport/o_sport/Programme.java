package com.o_sport.o_sport;

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

    public void setExercices(Map exercices) {
        this.exercices = exercices;
    }

}
