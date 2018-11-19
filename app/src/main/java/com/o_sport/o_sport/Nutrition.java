package com.o_sport.o_sport;

public class Nutrition {
    public Number id;
    public String petitDej;
    public String collationMatin;
    public String dejeuner;
    public String collationAprem;
    public String diner;
    public String collationSoir;

    public Nutrition(Number id, String petitDej, String collationMatin, String dejeuner, String collationAprem, String diner, String collationSoir) {
        this.id = id;
        this.petitDej = petitDej;
        this.collationMatin = collationMatin;
        this.dejeuner = dejeuner;
        this.collationAprem = collationAprem;
        this.diner = diner;
        this.collationSoir = collationSoir;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getPetitDej() {
        return petitDej;
    }

    public void setPetitDej(String petitDej) {
        this.petitDej = petitDej;
    }

    public String getCollationMatin() {
        return collationMatin;
    }

    public void setCollationMatin(String collationMatin) {
        this.collationMatin = collationMatin;
    }

    public String getDejeuner() {
        return dejeuner;
    }

    public void setDejeuner(String dejeuner) {
        this.dejeuner = dejeuner;
    }

    public String getCollationAprem() {
        return collationAprem;
    }

    public void setCollationAprem(String collationAprem) {
        this.collationAprem = collationAprem;
    }

    public String getDiner() {
        return diner;
    }

    public void setDiner(String diner) {
        this.diner = diner;
    }

    public String getCollationSoir() {
        return collationSoir;
    }

    public void setCollationSoir(String collationSoir) {
        this.collationSoir = collationSoir;
    }
}
