package com.o_sport.o_sport;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

public class User {
    public String email;
    public String nom;
    public String prenom;
    public Timestamp dateNaissance;
    public Number point;
    public Map imc; // Key:date value:IMC current date

    public User(String email, String nom, String prenom, Timestamp dateNaissance) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Timestamp getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Timestamp dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Number getPoint() {
        return point;
    }

    public void setPoint(Number point) {
        this.point = point;
    }

    public Map getImc() {
        return imc;
    }

    public void setImc(Map imc) {
        this.imc = imc;
    }
}
