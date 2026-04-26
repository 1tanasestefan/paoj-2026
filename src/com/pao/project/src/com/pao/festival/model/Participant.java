package com.pao.festival.model;

/**
 * Participant — extinde Persoana, reprezinta un participant la festival cu biletul sau.
 */
public class Participant extends Persoana {
    private Bilet bilet;

    public Participant(String nume, String email, Bilet bilet) {
        super(nume, email);
        this.bilet = bilet;
    }

    public Bilet getBilet() { return bilet; }

    @Override
    public String getRol() { return "Participant"; }

    @Override
    public String toString() {
        return super.toString() + " | " + bilet;
    }
}
