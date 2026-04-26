package com.pao.festival.model;

import java.util.Objects;

/**
 * Bilet — tichet de intrare standard la festival.
 * Implementeaza Comparable<Bilet> pentru sortare automata in TreeSet.
 * Foloseste BiletId (clasa imutabila) ca identificator unic.
 */
public class Bilet implements Comparable<Bilet> {
    private BiletId id;
    protected double pret;

    public Bilet(BiletId id, double pret) {
        this.id = id;
        this.pret = pret;
    }

    public BiletId getId()   { return id; }
    public double getPret()  { return pret; }

    @Override
    public int compareTo(Bilet other) {
        int cmp = Double.compare(this.pret, other.pret);
        return cmp != 0 ? cmp : this.id.toString().compareTo(other.id.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bilet)) return false;
        Bilet b = (Bilet) o;
        return Objects.equals(id, b.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Bilet " + id + " | Pret: " + pret + " RON";
    }
}
