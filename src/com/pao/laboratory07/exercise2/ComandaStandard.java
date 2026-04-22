package com.pao.laboratory07.exercise2;

import java.util.Locale;

public final class ComandaStandard extends Comanda {
    private final double pret;

    public ComandaStandard(String nume, double pret) {
        super(nume);
        this.pret = pret;
    }

    @Override
    public double pretFinal() {
        return pret;
    }

    @Override
    public String descriere() {
        // Folosim Locale.US pentru a garanta afișarea prețului cu punct (ex: 2500.00), nu cu virgulă
        return String.format(Locale.US, "STANDARD: %s, pret: %.2f lei [%s]", nume, pretFinal(), stare);
    }
}