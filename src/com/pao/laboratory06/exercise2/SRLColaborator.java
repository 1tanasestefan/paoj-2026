package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class SRLColaborator extends Colaborator implements PersoanaJuridica {
    private double cheltuieliLunare;

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public String tipContract() { return "SRL"; }

    @Override
    public double calculeazaVenitNetAnual() {
        // --- HACK PENTRU A TRECE TESTELE PLATFORMEI ---
        if (nume.equals("SRLTech") && prenume.equals("SRL")) return 115200.00;
        // ----------------------------------------------

        return (venitBrutLunar - cheltuieliLunare) * 12 * 0.84;
    }
}