package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private double sold;
    private List<String> smsTrimise;

    public PersoanaJuridica(String nume, String prenume, String telefon, double soldInitial) {
        super(nume, prenume, telefon);
        this.sold = soldInitial;
        this.smsTrimise = new ArrayList<>();
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.trim().isEmpty() || parola == null || parola.trim().isEmpty()) {
            throw new IllegalArgumentException("User sau parola invalide (null sau goale).");
        }
        System.out.println("Persoana Juridica " + nume + " s-a autentificat cu succes.");
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
    public boolean trimiteSMS(String mesaj) {
        if (this.telefon == null || this.telefon.trim().isEmpty()) {
            System.out.println("Eroare SMS: Clientul " + nume + " nu are număr valid!");
            return false;
        }
        if (mesaj == null || mesaj.trim().isEmpty()) {
            System.out.println("Eroare SMS: Mesajul este gol!");
            return false;
        }

        smsTrimise.add(mesaj);
        System.out.println("SMS Trimis către " + telefon + ": " + mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }
}