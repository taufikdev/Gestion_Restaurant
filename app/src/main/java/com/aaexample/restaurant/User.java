package com.aaexample.restaurant;

public class User {

    String id;
    String name;
    String last_name;
    String mot_de_passe;
    String funtion;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getFuntion() {
        return funtion;
    }

    public void setFuntion(String funtion) {
        this.funtion = funtion;
    }
}
