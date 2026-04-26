package com.pao.festival.service;

import com.pao.festival.exception.ArtistNegasitException;
import com.pao.festival.model.*;

import java.util.*;

/**
 * FestivalService — Singleton care gestioneaza artistii, scenele, staff-ul si locatia.
 *
 * Operatii expuse:
 *  1. inregistreazaArtist   — adauga artist in line-up si in Map pe gen
 *  2. eliminaArtist         — elimina artist (arunca ArtistNegasitException)
 *  3. afiseazaLineupSortat  — line-up sortat alfabetic
 *  4. cautaDupaGen          — cauta artisti dupa gen (foloseste Map)
 *  5. getArtistiPerGen      — returneaza Map-ul complet gen→artisti
 *  6. actualizeazaLocatie   — schimba locatia festivalului
 *  7. adaugaStaff           — adauga un angajat
 *  8. listeazaStaff         — listeaza toti angajatii
 *  9. adaugaScena           — adauga o scena
 * 10. listeazaScene         — listeaza toate scenele
 * 11. cautaArtistDupaNume   — returneaza Optional<Artist>
 */
public class FestivalService {

    private static FestivalService instance;
    private final Festival festival;

    private FestivalService(Festival festival) {
        this.festival = festival;
    }

    public static FestivalService getInstance(Festival festival) {
        if (instance == null) {
            instance = new FestivalService(festival);
        }
        return instance;
    }

    // 1. Inregistreaza artist in line-up si actualizeaza Map-ul gen→artisti
    public void inregistreazaArtist(Artist a) {
        if (a == null) throw new IllegalArgumentException("Artistul nu poate fi null.");
        festival.getLineUp().add(a);
        festival.getArtistiPerGen()
                .computeIfAbsent(a.getGenMuzical(), k -> new ArrayList<>())
                .add(a);
        System.out.println("Artist inregistrat: " + a.getNume() + " (" + a.getGenMuzical() + ")");
    }

    // 2. Elimina artist dupa nume — arunca ArtistNegasitException (unchecked) daca nu exista
    public void eliminaArtist(String numeArtist) {
        if (numeArtist == null || numeArtist.isBlank())
            throw new IllegalArgumentException("Numele artistului nu poate fi gol.");

        Artist gasit = festival.getLineUp().stream()
                .filter(a -> a.getNume().equalsIgnoreCase(numeArtist))
                .findFirst()
                .orElseThrow(() -> new ArtistNegasitException(numeArtist));

        festival.getLineUp().remove(gasit);
        List<Artist> perGen = festival.getArtistiPerGen().get(gasit.getGenMuzical());
        if (perGen != null) perGen.remove(gasit);
        System.out.println("Artistul '" + numeArtist + "' a fost eliminat.");
    }

    // 3. Afiseaza line-up-ul sortat alfabetic
    public void afiseazaLineupSortat() {
        List<Artist> sortati = new ArrayList<>(festival.getLineUp());
        sortati.sort(Comparator.comparing(Persoana::getNume));
        System.out.println("\n--- Line-up Sortat: " + festival.getNume() + " ---");
        if (sortati.isEmpty()) System.out.println("  (line-up gol)");
        else sortati.forEach(a -> System.out.println("  " + a));
    }

    // 4. Cauta artisti dupa gen muzical — foloseste Map (O(1))
    public void cautaDupaGen(String gen) {
        System.out.println("\nArtisti gen '" + gen + "':");
        List<Artist> artisti = festival.getArtistiPerGen().getOrDefault(gen, Collections.emptyList());
        if (artisti.isEmpty()) System.out.println("  (niciun artist gasit)");
        else artisti.forEach(a -> System.out.println("  " + a));
    }

    // 5. Returneaza Map-ul complet gen → lista artisti
    public Map<String, List<Artist>> getArtistiPerGen() {
        return Collections.unmodifiableMap(festival.getArtistiPerGen());
    }

    // 6. Actualizeaza locatia festivalului
    public void actualizeazaLocatie(Locatie nouaLocatie) {
        if (nouaLocatie == null) throw new IllegalArgumentException("Locatia nu poate fi null.");
        festival.setLocatie(nouaLocatie);
        System.out.println("Locatia actualizata la: " + nouaLocatie);
    }

    // 7. Adauga un angajat Staff
    public void adaugaStaff(Staff s) {
        if (s == null) throw new IllegalArgumentException("Staff-ul nu poate fi null.");
        festival.getEchipaStaff().add(s);
        System.out.println("Staff adaugat: " + s.getNume() + " (" + s.getRol() + ")");
    }

    // 8. Listeaza toti angajatii
    public void listeazaStaff() {
        System.out.println("\n--- Echipa Staff ---");
        if (festival.getEchipaStaff().isEmpty()) System.out.println("  (nicio persoana angajata)");
        else festival.getEchipaStaff().forEach(s -> System.out.println("  " + s));
    }

    // 9. Adauga o scena
    public void adaugaScena(Scena scena) {
        if (scena == null) throw new IllegalArgumentException("Scena nu poate fi null.");
        festival.getScene().add(scena);
        System.out.println("Scena adaugata: " + scena.getNume());
    }

    // 10. Listeaza toate scenele
    public void listeazaScene() {
        System.out.println("\n--- Scene Festival ---");
        if (festival.getScene().isEmpty()) System.out.println("  (nicio scena adaugata)");
        else festival.getScene().forEach(s -> System.out.println("  " + s));
    }

    // 11. Cauta un artist dupa nume (returneaza Optional)
    public Optional<Artist> cautaArtistDupaNume(String nume) {
        return festival.getLineUp().stream()
                .filter(a -> a.getNume().equalsIgnoreCase(nume))
                .findFirst();
    }

    public Festival getFestival() { return festival; }
}
