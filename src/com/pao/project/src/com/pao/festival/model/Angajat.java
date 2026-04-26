package com.pao.festival.model;

/**
 * Angajat — extinde Persoana, reprezinta orice persoana angajata a festivalului.
 * Ierarhie: Persoana → Angajat → Staff  (2 niveluri de mostenire)
 */
public class Angajat extends Persoana {
    private double salariu;

    public Angajat(String nume, String email, double salariu) {
        super(nume, email);
        this.salariu = salariu;
    }

    public double getSalariu() { return salariu; }
    public void setSalariu(double salariu) { this.salariu = salariu; }

    @Override
    public String getRol() { return "Angajat"; }

    @Override
    public String toString() {
        return super.toString() + " | Salariu: " + salariu + " RON";
    }
}
