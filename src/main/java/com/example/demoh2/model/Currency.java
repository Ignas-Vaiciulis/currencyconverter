package com.example.demoh2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    @GeneratedValue
    private Long id;
    private String abbreviation;
    private double rate;
    private String date;

    public Currency() {
    }

    public Currency(Long id, String abbreviation, double rate, String date) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.rate = rate;
        this.date = date;
    }

    public Currency(String abbreviation, double rate, String date) {
        this.abbreviation = abbreviation;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
