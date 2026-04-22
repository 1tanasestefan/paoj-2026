package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String nume;
    protected OrderState stare;

    public Comanda(String nume) {
        this.nume = nume;
        // Orice comandă nouă are starea inițială PLACED, conform cerinței
        this.stare = OrderState.PLACED;
    }

    public abstract double pretFinal();
    public abstract String descriere();
}