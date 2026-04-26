package com.pao.festival.exception;

/**
 * Exceptie aruncata cand se incearca vanzarea unui bilet
 * iar capacitatea maxima a locatiei a fost atinsa.
 * Checked (extends Exception) — apelantul e obligat sa o trateze.
 */
public class CapacitateDepasitaException extends Exception {
    private final int capacitateMaxima;
    private final int bileteVandute;

    public CapacitateDepasitaException(int bileteVandute, int capacitateMaxima) {
        super("Capacitate depasita: " + bileteVandute + "/" + capacitateMaxima
                + " locuri ocupate. Nu se mai pot vinde bilete.");
        this.bileteVandute = bileteVandute;
        this.capacitateMaxima = capacitateMaxima;
    }

    public int getCapacitateMaxima() { return capacitateMaxima; }
    public int getBileteVandute()    { return bileteVandute; }
}
