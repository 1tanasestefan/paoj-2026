package com.pao.festival.exception;

/**
 * Exceptie aruncata cand un artist cautat nu este gasit in line-up.
 * Unchecked (extends RuntimeException).
 */
public class ArtistNegasitException extends RuntimeException {
    private final String numeArtist;

    public ArtistNegasitException(String numeArtist) {
        super("Artistul '" + numeArtist + "' nu a fost gasit in line-up.");
        this.numeArtist = numeArtist;
    }

    public String getNumeArtist() { return numeArtist; }
}
