package com.pao.laboratory07.exercise2;

public final class ComandaGratuita extends Comanda {
    public ComandaGratuita(String nume) {
        super(nume);
    }

    public ComandaGratuita(String nume, String client) {
        super(nume, client);
    }

    @Override
    public double pretFinal() {
        return 0.0;
    }

    @Override
    public String descriere() {
        if (client != null) {
            return String.format("GIFT: %s, gratuit [%s] - client: %s", nume, stareInit, client);
        }
        return String.format("GIFT: %s, gratuit [%s]", nume, stareInit);
    }
}
