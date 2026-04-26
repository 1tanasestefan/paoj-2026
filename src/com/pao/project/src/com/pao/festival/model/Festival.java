package com.pao.festival.model;

import java.util.*;

/**
 * Festival — entitatea centrala a sistemului.
 *
 * Colectii folosite:
 *   1. List<Artist>               lineUp        — line-up (nesortata, pastreaza ordinea adaugarii)
 *   2. TreeSet<Bilet>             bileteVandute — bilete SORTATE dupa pret (Comparable<Bilet>)
 *   3. Map<String, List<Artist>>  artistiPerGen — indexare artisti dupa gen muzical
 *   4. List<Participant>          participanti  — participanti inregistrati
 *   5. List<Staff>                echipaStaff   — echipa de organizare
 *   6. List<Scena>                scene         — scenele festivalului
 */
public class Festival {
    private String nume;
    private Locatie locatie;

    private List<Artist>             lineUp        = new ArrayList<>();
    private TreeSet<Bilet>           bileteVandute = new TreeSet<>();
    private Map<String, List<Artist>>artistiPerGen = new HashMap<>();
    private List<Participant>        participanti  = new ArrayList<>();
    private List<Staff>              echipaStaff   = new ArrayList<>();
    private List<Scena>              scene         = new ArrayList<>();

    public Festival(String nume, Locatie locatie) {
        this.nume = nume;
        this.locatie = locatie;
    }

    public String getNume()                         { return nume; }
    public Locatie getLocatie()                     { return locatie; }
    public void setLocatie(Locatie locatie)         { this.locatie = locatie; }
    public List<Artist> getLineUp()                 { return lineUp; }
    public TreeSet<Bilet> getBileteVandute()        { return bileteVandute; }
    public Map<String, List<Artist>> getArtistiPerGen() { return artistiPerGen; }
    public List<Participant> getParticipanti()      { return participanti; }
    public List<Staff> getEchipaStaff()             { return echipaStaff; }
    public List<Scena> getScene()                   { return scene; }
}
