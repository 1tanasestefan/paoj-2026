package com.pao.festival.model;

import java.util.Objects;

/**
 * Artist — extinde Persoana, reprezinta un artist din line-up-ul festivalului.
 */
public class Artist extends Persoana {
    private String genMuzical;
    private double tarif;

    public Artist(String nume, String email, String genMuzical, double tarif) {
        super(nume, email);
        this.genMuzical = genMuzical;
        this.tarif = tarif;
    }

    public String getGenMuzical() { return genMuzical; }
    public double getTarif()      { return tarif; }

    @Override
    public String getRol() { return "Artist"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist a = (Artist) o;
        return Objects.equals(email, a.email);
    }

    @Override
    public int hashCode() { return Objects.hash(email); }

    @Override
    public String toString() {
        return super.toString() + " | Gen: " + genMuzical + " | Tarif: " + tarif + " EUR";
    }
}
