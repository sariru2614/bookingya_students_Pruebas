package com.project.bookingya.models;

public class Guest {

    private String identification;
    private String name;
    private String email;

    public Guest(String identification, String name, String email) {
        this.identification = identification;
        this.name = name;
        this.email = email;
    }

    public String getIdentification() {
        return identification;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}