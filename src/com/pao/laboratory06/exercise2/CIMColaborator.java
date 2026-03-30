package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
    private boolean bonus = false;

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        if (in.hasNext()) {
            this.bonus = in.next().equalsIgnoreCase("DA");
        }
    }

    @Override
    public String tipContract() { return "CIM"; }

    @Override
    public boolean areBonus() { return bonus; }

    @Override
    public double calculeazaVenitNetAnual() {
        double net = venitBrutLunar * 12 * 0.55;
        if (areBonus()) {
            net += net * 0.10;
        }
        return net;
    }
}