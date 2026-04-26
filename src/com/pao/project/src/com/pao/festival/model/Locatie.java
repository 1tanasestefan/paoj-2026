package com.pao.festival.model;

/**
 * Locatie reprezinta locul de desfasurare a festivalului.
 */
public class Locatie {
    private String nume;
    private String oras;
    private int capacitateMaxima;

    public Locatie(String nume, String oras, int capacitateMaxima) {
        this.nume = nume;
        this.oras = oras;
        this.capacitateMaxima = capacitateMaxima;
    }

    public String getNume()          { return nume; }
    public String getOras()          { return oras; }
    public int getCapacitateMaxima() { return capacitateMaxima; }

    @Override
    public String toString() {
        return oras + ", " + nume + " (Capacitate: " + capacitateMaxima + ")";
    }
}
