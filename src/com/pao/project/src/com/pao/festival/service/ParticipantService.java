package com.pao.festival.service;

import com.pao.festival.exception.CapacitateDepasitaException;
import com.pao.festival.model.*;

import java.util.*;

/**
 * ParticipantService — Singleton care gestioneaza participantii si biletele vandute.
 *
 * Operatii expuse:
 *  1. adaugaParticipant      — vinde un bilet si inregistreaza participantul
 *                              (arunca CapacitateDepasitaException — checked)
 *  2. stergeParticipant      — elimina participantul dupa email
 *  3. cautaDupaEmail         — cauta participant dupa email
 *  4. listeazaToti           — listeaza toti participantii
 *  5. listeazaVIP            — listeaza participantii cu bilet VIP
 *  6. afiseazaBileteSortate  — afiseaza biletele din TreeSet (sortate dupa pret)
 *  7. calculeazaVenitTotal   — suma totala din bilete vandute
 *  8. verificaCapacitate     — verifica locuri disponibile
 */
public class ParticipantService {

    private static ParticipantService instance;
    private final Festival festival;

    private ParticipantService(Festival festival) {
        this.festival = festival;
    }

    public static ParticipantService getInstance(Festival festival) {
        if (instance == null) {
            instance = new ParticipantService(festival);
        }
        return instance;
    }

    // 1. Vinde bilet si inregistreaza participantul; verifica capacitatea inainte
    public void adaugaParticipant(String nume, String email, Bilet bilet)
            throws CapacitateDepasitaException {
        if (nume == null || email == null || bilet == null)
            throw new IllegalArgumentException("Parametrii nu pot fi null.");

        int vandute = festival.getBileteVandute().size();
        int maxCapacitate = festival.getLocatie().getCapacitateMaxima();

        if (vandute >= maxCapacitate) {
            throw new CapacitateDepasitaException(vandute, maxCapacitate);
        }

        festival.getParticipanti().add(new Participant(nume, email, bilet));
        festival.getBileteVandute().add(bilet);
        System.out.println("Bilet vandut catre: " + nume + " (" + bilet + ")");
    }

    // 2. Sterge participant dupa email
    public boolean stergeParticipant(String email) {
        if (email == null) return false;
        boolean eliminat = festival.getParticipanti()
                .removeIf(p -> p.getEmail().equalsIgnoreCase(email));
        if (eliminat) System.out.println("Participantul cu email '" + email + "' a fost sters.");
        else System.out.println("Participantul cu email '" + email + "' nu a fost gasit.");
        return eliminat;
    }

    // 3. Cauta participant dupa email
    public Optional<Participant> cautaDupaEmail(String email) {
        return festival.getParticipanti().stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // 4. Listeaza toti participantii
    public void listeazaToti() {
        System.out.println("\n--- Toti Participantii (" + festival.getParticipanti().size() + ") ---");
        if (festival.getParticipanti().isEmpty()) System.out.println("  (niciun participant)");
        else festival.getParticipanti().forEach(p -> System.out.println("  " + p));
    }

    // 5. Listeaza participantii VIP
    public void listeazaVIP() {
        System.out.println("\n--- Participanti VIP ---");
        boolean gasit = false;
        for (Participant p : festival.getParticipanti()) {
            if (p.getBilet() instanceof BiletVIP) {
                System.out.println("  " + p);
                gasit = true;
            }
        }
        if (!gasit) System.out.println("  (niciun participant VIP)");
    }

    // 6. Afiseaza biletele vandute sortate dupa pret (TreeSet-ul pastreaza ordinea)
    public void afiseazaBileteSortate() {
        System.out.println("\n--- Bilete Vandute (sortate dupa pret) ---");
        if (festival.getBileteVandute().isEmpty()) System.out.println("  (niciun bilet vandut)");
        else festival.getBileteVandute().forEach(b -> System.out.println("  " + b));
    }

    // 7. Calculeaza venitul total din bilete vandute
    public double calculeazaVenitTotal() {
        return festival.getBileteVandute().stream()
                .mapToDouble(Bilet::getPret)
                .sum();
    }

    // 8. Verifica capacitatea disponibila
    public void verificaCapacitate() {
        int vandute = festival.getBileteVandute().size();
        int max     = festival.getLocatie().getCapacitateMaxima();
        System.out.println("\nCapacitate: " + vandute + " / " + max);
        if (vandute < max)
            System.out.println("Locuri disponibile: " + (max - vandute));
        else
            System.out.println("Sold Out!");
    }
}
