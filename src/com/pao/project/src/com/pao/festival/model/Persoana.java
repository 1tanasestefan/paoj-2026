package com.pao.festival.model;

/**
 * Persoana — clasa abstracta de baza pentru toate persoanele din sistem.
 * Ierarhie:
 *   Persoana (abstract)
 *   ├── Artist
 *   ├── Participant
 *   └── Angajat (abstract)
 *       └── Staff
 */
public abstract class Persoana {
    protected String nume;
    protected String email;

    public Persoana(String nume, String email) {
        this.nume = nume;
        this.email = email;
    }

    public String getNume()  { return nume; }
    public String getEmail() { return email; }

    /**
     * Metoda abstracta — fiecare subclasa defineste rolul sau in sistem.
     */
    public abstract String getRol();

    @Override
    public String toString() {
        return "[" + getRol() + "] Nume: " + nume + " | Email: " + email;
    }
}
