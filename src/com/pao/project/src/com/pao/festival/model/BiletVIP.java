package com.pao.festival.model;

/**
 * BiletVIP — extinde Bilet, ofera facilitati suplimentare participantului VIP.
 */
public class BiletVIP extends Bilet {
    private String facilitati;

    public BiletVIP(BiletId id, double pret, String facilitati) {
        super(id, pret);
        this.facilitati = facilitati;
    }

    public String getFacilitati() { return facilitati; }

    @Override
    public String toString() {
        return super.toString() + " [VIP: " + facilitati + "]";
    }
}
