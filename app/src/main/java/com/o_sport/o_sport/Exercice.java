package com.o_sport.o_sport;

public class Exercice {
    public String id;
    public String nom;
    public String photoUrl;
    public Number tempsSec;
    public Number point;


    public Exercice(String nom, String photoUrl, Number tempsSec, Number point) {
        this.nom = nom;
        this.photoUrl = photoUrl;
        this.tempsSec = tempsSec;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Number getTempsSec() {
        return tempsSec;
    }

    public void setTempsSec(Number tempsSec) {
        this.tempsSec = tempsSec;
    }

    public Number getPoint() {
        return point;
    }

    public void setPoint(Number point) {
        this.point = point;
    }
}
