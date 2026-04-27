package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String nume;
    protected String client;
    protected OrderState stareInit;

    public Comanda(String nume) {
        this.nume = nume;
        this.client = null;
        this.stareInit = OrderState.PLACED;
    }

    public Comanda(String nume, String client) {
        this.nume = nume;
        this.client = client;
        this.stareInit = OrderState.PLACED;
    }

    public String getNume() { return nume; }
    public String getClient() { return client; }
    public OrderState getStareInit() { return stareInit; }

    public abstract double pretFinal();
    public abstract String descriere();
}
