package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {
    private double sold;

    public Inginer(String nume, String prenume, String telefon, double salariu) {
        super(nume, prenume, telefon, salariu);
        this.sold = salariu;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.trim().isEmpty() || parola == null || parola.trim().isEmpty()) {
            throw new IllegalArgumentException("User sau parola invalide (null sau goale).");
        }
        System.out.println("Inginerul " + nume + " s-a autentificat cu succes.");
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0 || suma > sold) return false;
        sold -= suma;
        return true;
    }

    @Override
    public int compareTo(Inginer altul) {
        return this.nume.compareTo(altul.nume);
    }

    @Override
    public String toString() {
        return "Inginer{" + "nume='" + nume + '\'' + ", salariu=" + salariu + '}';
    }
}