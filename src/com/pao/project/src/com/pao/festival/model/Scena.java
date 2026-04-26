package com.pao.festival.model;

/**
 * Scena reprezinta una dintre scenele disponibile la festival.
 */
public class Scena {
    private String nume;
    private String dotariTehnice;
    private int capacitatePublic;

    public Scena(String nume, String dotariTehnice, int capacitatePublic) {
        this.nume = nume;
        this.dotariTehnice = dotariTehnice;
        this.capacitatePublic = capacitatePublic;
    }

    public String getNume()          { return nume; }
    public String getDotariTehnice() { return dotariTehnice; }
    public int getCapacitatePublic() { return capacitatePublic; }

    @Override
    public String toString() {
        return "Scena: " + nume + " | Dotari: " + dotariTehnice
                + " | Capacitate: " + capacitatePublic;
    }
}
