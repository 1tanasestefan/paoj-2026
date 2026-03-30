package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. TESTARE CONSTANTE FINANCIARE ===");
        System.out.println("Valoarea TVA-ului curent este: " + ConstanteFinanciare.TVA.getValoare() * 100 + "%");

        System.out.println("\n=== 2. CREARE ȘI SORTARE INGINERI ===");
        Inginer[] ingineri = {
                new Inginer("Popescu", "Ion", "0722111222", 8500),
                new Inginer("Avram", "Mihai", "0733123123", 9200),
                new Inginer("Zaharia", "Elena", null, 7000)
        };

        Arrays.sort(ingineri);
        System.out.println("Ingineri sortati alfabetic:");
        for (Inginer i : ingineri) System.out.println(i);

        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        System.out.println("\nIngineri sortati descrescator dupa salariu:");
        for (Inginer i : ingineri) System.out.println(i);


        System.out.println("\n=== 3. TESTARE POLIMORFISM (Referință PlataOnline) ===");
        PlataOnline referintaInginer = ingineri[0];
        referintaInginer.autentificare("admin", "12345");
        System.out.println("Sold disponibil: " + referintaInginer.consultareSold());
        referintaInginer.efectuarePlata(100);
        System.out.println("Sold dupa plata: " + referintaInginer.consultareSold());


        System.out.println("\n=== 4. TESTARE PlataOnlineSMS ȘI EDGE CASES ===");
        PersoanaJuridica pj1 = new PersoanaJuridica("TechSRL", "SA", "0799888777", 50000);
        PlataOnlineSMS refPJ1 = pj1;

        PersoanaJuridica pj2FaraTelefon = new PersoanaJuridica("NoPhoneSRL", "SRL", null, 10000);
        PlataOnlineSMS refPJ2 = pj2FaraTelefon;

        refPJ1.trimiteSMS("Plata dumneavoastra a fost confirmata.");
        refPJ1.trimiteSMS("");
        refPJ2.trimiteSMS("Alerta de securitate!");

        System.out.println("\nIstoric SMS pentru TechSRL: " + pj1.getSmsTrimise());


        System.out.println("\n=== 5. DEMONSTRARE TRATARE ERORI (Excepții) ===");

        try {
            System.out.print("Incercare autentificare cu user null: ");
            referintaInginer.autentificare(null, "parola");
        } catch (IllegalArgumentException e) {
            System.out.println("PRINSĂ EXCEPTIE: " + e.getMessage());
        }

        try {
            System.out.print("Incercare SMS de pe Inginer: ");
            referintaInginer.trimiteSMS("Salut!");
        } catch (UnsupportedOperationException e) {
            System.out.println("PRINSĂ EXCEPTIE: " + e.getMessage());
        }
    }
}