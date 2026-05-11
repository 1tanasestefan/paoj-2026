package com.pao.laboratory10.exercise3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<TranzactieDemo> tranzactii = new ArrayList<>();

        tranzactii.add(new TranzactieDemo(1, 1500.00, "2024-01-10", TipTranzactie.CREDIT, "CONT_A"));
        tranzactii.add(new TranzactieDemo(2, 250.00, "2024-01-12", TipTranzactie.DEBIT, "CONT_B"));
        tranzactii.add(new TranzactieDemo(3, 3000.00, "2024-01-20", TipTranzactie.CREDIT, "CONT_A"));

        tranzactii.add(new TranzactieDemo(4, 450.50, "2024-02-05", TipTranzactie.DEBIT, "CONT_C"));
        tranzactii.add(new TranzactieDemo(5, 800.00, "2024-02-11", TipTranzactie.CREDIT, "CONT_B"));
        tranzactii.add(new TranzactieDemo(6, 125.75, "2024-02-18", TipTranzactie.DEBIT, "CONT_A"));

        tranzactii.add(new TranzactieDemo(7, 2100.00, "2024-03-01", TipTranzactie.CREDIT, "CONT_D"));
        tranzactii.add(new TranzactieDemo(8, 90.00, "2024-03-08", TipTranzactie.DEBIT, "CONT_C"));
        tranzactii.add(new TranzactieDemo(9, 670.25, "2024-03-15", TipTranzactie.CREDIT, "CONT_B"));
        tranzactii.add(new TranzactieDemo(10, 320.00, "2024-03-22", TipTranzactie.DEBIT, "CONT_A"));

        System.out.println("1. Tranzactii CREDIT:");
        tranzactii.stream()
                .filter(tranzactie -> tranzactie.getTip() == TipTranzactie.CREDIT)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("2. Total procesat:");
        double totalProcesat = tranzactii.stream()
                .mapToDouble(TranzactieDemo::getSuma)
                .sum();

        System.out.printf("Total procesat: %.2f RON%n", totalProcesat);

        System.out.println();

        System.out.println("3. Total per luna:");
        Map<String, Double> totalPerLuna = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        tranzactie -> tranzactie.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.summingDouble(TranzactieDemo::getSuma)
                ));

        totalPerLuna.forEach((luna, total) ->
                System.out.printf("%s: %.2f RON%n", luna, total)
        );

        System.out.println();

        System.out.println("4. Top 3 tranzactii:");
        tranzactii.stream()
                .sorted(Comparator.comparingDouble(TranzactieDemo::getSuma).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("5. Conturi sursa unice:");
        List<String> conturiSursaUnice = tranzactii.stream()
                .map(TranzactieDemo::getContSursa)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Conturi sursa unice: " + conturiSursaUnice);

        System.out.println();

        System.out.println("6. Suma medie:");
        OptionalDouble sumaMedie = tranzactii.stream()
                .mapToDouble(TranzactieDemo::getSuma)
                .average();

        System.out.printf("Suma medie: %.2f RON%n", sumaMedie.orElse(0.0));

        System.out.println();

        System.out.println("7. Extrase de cont lunare:");
        Map<String, List<TranzactieDemo>> tranzactiiPeLuna = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        tranzactie -> tranzactie.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.toList()
                ));

        tranzactiiPeLuna.forEach((luna, listaLunara) -> {
            double totalLunar = listaLunara.stream()
                    .mapToDouble(TranzactieDemo::getSuma)
                    .sum();

            System.out.printf(
                    "EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON%n",
                    luna,
                    listaLunara.size(),
                    totalLunar
            );
        });
    }

    enum TipTranzactie {
        CREDIT,
        DEBIT
    }

    static class TranzactieDemo {
        private int id;
        private double suma;
        private String data;
        private TipTranzactie tip;
        private String contSursa;

        public TranzactieDemo(int id, double suma, String data, TipTranzactie tip, String contSursa) {
            this.id = id;
            this.suma = suma;
            this.data = data;
            this.tip = tip;
            this.contSursa = contSursa;
        }

        public int getId() {
            return id;
        }

        public double getSuma() {
            return suma;
        }

        public String getData() {
            return data;
        }

        public TipTranzactie getTip() {
            return tip;
        }

        public String getContSursa() {
            return contSursa;
        }

        @Override
        public String toString() {
            return String.format(
                    "[%d] %s %s: %.2f RON, cont sursa: %s",
                    id,
                    data,
                    tip,
                    suma,
                    contSursa
            );
        }
    }
}