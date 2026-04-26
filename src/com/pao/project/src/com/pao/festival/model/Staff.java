package com.pao.festival.model;

import java.util.Objects;

/**
 * Staff — extinde Angajat, reprezinta un angajat cu rol specific.
 * Ierarhie (nivelul 3): Persoana → Angajat → Staff
 */
public class Staff extends Angajat {
    private String rol;

    public Staff(String nume, String email, String rol, double salariu) {
        super(nume, email, salariu);
        this.rol = rol;
    }

    public String getRolSpecific() { return rol; }

    @Override
    public String getRol() { return rol; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff s = (Staff) o;
        return Objects.equals(email, s.email);
    }

    @Override
    public int hashCode() { return Objects.hash(email); }

    @Override
    public String toString() {
        return super.toString() + " | Functie: " + rol;
    }
}
