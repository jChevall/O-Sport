package com.o_sport.o_sport;

import com.google.firebase.database.Exclude;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    public String email;
    public Integer point;
    public Map<String,String> imc; // Key:date value:IMC current date
    private String id;

    public User(String email, Integer point, Map imc, String id) {
        this.email = email;
        this.point = point;
        if(imc == null || imc.size() == 0){
            this.imc = new HashMap<>();
        }else{
            this.imc = imc;
        }
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public void addpoint(Integer point){
        this.point += point;
    }

    public Map getImc() {
        return imc;
    }


    public  void addImc(Float imc){
        // Calendar information
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        this.imc.put(String.valueOf(timestamp.getTime()),imc.toString());
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("point", this.point);
        result.put("imc", this.imc);
        result.put("email", this.email);
        return result;
    }

    public void setImc(Map<String, String> imc) {
        this.imc = imc;
    }


    public String getId() {
        return id;
    }


}
