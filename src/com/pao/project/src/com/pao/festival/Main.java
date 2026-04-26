package com.pao.festival;

import com.pao.festival.exception.ArtistNegasitException;
import com.pao.festival.exception.CapacitateDepasitaException;
import com.pao.festival.model.*;
import com.pao.festival.service.FestivalService;
import com.pao.festival.service.ParticipantService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Locatie locatie = new Locatie("Poiana Brasov", "Brasov", 5000);
        Festival festival = new Festival("Massif 2026", locatie);

        FestivalService festivalService = FestivalService.getInstance(festival);
        ParticipantService participantService = ParticipantService.getInstance(festival);

        System.out.println("=== " + festival.getNume() + " — " + locatie + " ===\n");

        // Actiunea 1: Inregistreaza artisti in line-up
        System.out.println(">> ACTIUNEA 1: Inregistrare artisti");
        festivalService.inregistreazaArtist(new Artist("Chase & Status", "contact@cs.com", "Drum and Bass", 30000));
        festivalService.inregistreazaArtist(new Artist("Boris Brejcha",  "boris@brejcha.com", "Techno", 40000));
        festivalService.inregistreazaArtist(new Artist("Amelie Lens",    "amelie@lens.com",   "Techno", 35000));
        festivalService.inregistreazaArtist(new Artist("Alok",           "alok@brazil.com",   "EDM",    25000));
        festivalService.inregistreazaArtist(new Artist("Fisher",         "fisher@music.com",  "Techno", 28000));

        // Actiunea 2: Adauga scene si staff
        System.out.println("\n>> ACTIUNEA 2: Adaugare scene si staff");
        festivalService.adaugaScena(new Scena("Main Stage",      "LED 4K, Funktion-One", 4000));
        festivalService.adaugaScena(new Scena("Secondary Stage", "LED HD, QSC",          1000));
        festivalService.adaugaStaff(new Staff("Mihai Ionescu", "mihai@festival.ro", "Sound Engineer", 8000));
        festivalService.adaugaStaff(new Staff("Ioana Pop",     "ioana@festival.ro", "Security",       5000));

        // Actiunea 3: Vinde bilete (CapacitateDepasitaException tratata cu try/catch)
        System.out.println("\n>> ACTIUNEA 3: Vanzare bilete");
        try {
            participantService.adaugaParticipant("Ion Popescu",  "ion@email.com",
                    new Bilet(new BiletId("REG", 1), 200));
            participantService.adaugaParticipant("Ana Maria",    "ana@email.com",
                    new BiletVIP(new BiletId("VIP", 1), 600, "Canapea Rezervata"));
            participantService.adaugaParticipant("George Dima",  "geo@email.com",
                    new Bilet(new BiletId("REG", 2), 150));
            participantService.adaugaParticipant("Elena Rusu",   "elena@email.com",
                    new BiletVIP(new BiletId("VIP", 2), 800, "Zona Backstage"));
        } catch (CapacitateDepasitaException e) {
            System.err.println("[EROARE] " + e.getMessage());
        }

        // Actiunea 4: Afiseaza biletele sortate dupa pret (TreeSet)
        System.out.println("\n>> ACTIUNEA 4: Bilete sortate dupa pret");
        participantService.afiseazaBileteSortate();

        // Actiunea 5: Afiseaza line-up-ul sortat alfabetic
        System.out.println("\n>> ACTIUNEA 5: Line-up sortat alfabetic");
        festivalService.afiseazaLineupSortat();

        // Actiunea 6: Cauta artisti dupa gen muzical (foloseste Map)
        System.out.println("\n>> ACTIUNEA 6: Cauta artisti dupa gen");
        festivalService.cautaDupaGen("Techno");
        festivalService.cautaDupaGen("EDM");

        // Actiunea 7: Listeaza participantii VIP
        System.out.println("\n>> ACTIUNEA 7: Participanti VIP");
        participantService.listeazaVIP();

        // Actiunea 8: Calculeaza venitul total
        System.out.println("\n>> ACTIUNEA 8: Venit total");
        System.out.println("Venit total din bilete: " + participantService.calculeazaVenitTotal() + " RON");

        // Actiunea 9: Verifica capacitatea disponibila
        System.out.println("\n>> ACTIUNEA 9: Verificare capacitate");
        participantService.verificaCapacitate();

        // Actiunea 10: Cauta un participant dupa email
        System.out.println("\n>> ACTIUNEA 10: Cauta participant dupa email");
        Optional<Participant> p = participantService.cautaDupaEmail("ana@email.com");
        p.ifPresentOrElse(
            participant -> System.out.println("Gasit: " + participant),
            () -> System.out.println("Participantul nu a fost gasit.")
        );

        // Extra: Elimina artist — demonstreaza ArtistNegasitException
        System.out.println("\n>> EXTRA: Elimina artist (valid + inexistent)");
        festivalService.eliminaArtist("Alok");
        try {
            festivalService.eliminaArtist("DJ Inexistent");
        } catch (ArtistNegasitException e) {
            System.err.println("[EROARE] " + e.getMessage());
        }
        festivalService.afiseazaLineupSortat();

        // Extra: Actualizeaza locatia festivalului
        System.out.println("\n>> EXTRA: Actualizare locatie");
        festivalService.actualizeazaLocatie(new Locatie("Cluj Arena", "Cluj-Napoca", 40000));

        // Extra: Afiseaza Map gen → artisti
        System.out.println("\n>> EXTRA: Artisti grupati pe gen (Map)");
        festivalService.getArtistiPerGen().forEach((gen, artisti) -> {
            System.out.println("  [" + gen + "]");
            artisti.forEach(artist -> System.out.println("    - " + artist.getNume()));
        });

        // Extra: Demonstrare clasa imutabila BiletId
        System.out.println("\n>> EXTRA: BiletId (clasa imutabila)");
        BiletId id1 = new BiletId("REG", 1);
        BiletId id2 = new BiletId("REG", 1);
        System.out.println("id1: " + id1 + " | id2: " + id2 + " | egale: " + id1.equals(id2));

        // Extra: Demonstrare polimorfism si ierarhia de mostenire
        System.out.println("\n>> EXTRA: Polimorfism getRol()");
        Persoana[] persoane = {
            new Artist("Test Artist", "t@a.com", "Jazz", 1000),
            new Angajat("Test Angajat", "t@ang.com", 3000),
            new Staff("Test Staff", "t@s.com", "Manager", 7000)
        };
        for (Persoana pers : persoane) {
            System.out.println("  " + pers.getNume() + " -> getRol(): " + pers.getRol());
        }
    }
}
