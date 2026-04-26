package com.pao.festival.model;

import java.util.Objects;

/**
 * BiletId — clasa IMUTABILA ce reprezinta identificatorul unic al unui bilet.
 * Toate campurile sunt final, nu exista setteri, initializate complet in constructor.
 */
public final class BiletId {
    private final String serie;
    private final int numar;

    public BiletId(String serie, int numar) {
        if (serie == null || serie.isBlank())
            throw new IllegalArgumentException("Seria nu poate fi nula sau goala.");
        if (numar <= 0)
            throw new IllegalArgumentException("Numarul biletului trebuie sa fie pozitiv.");
        this.serie = serie;
        this.numar = numar;
    }

    public String getSerie() { return serie; }
    public int getNumar()    { return numar; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BiletId)) return false;
        BiletId other = (BiletId) o;
        return numar == other.numar && Objects.equals(serie, other.serie);
    }

    @Override
    public int hashCode() { return Objects.hash(serie, numar); }

    @Override
    public String toString() { return serie + "-" + String.format("%04d", numar); }
}
