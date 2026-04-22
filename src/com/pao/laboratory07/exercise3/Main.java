package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) return;

        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        // Citirea comenzilor
        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().trim().split(" ");
            String tip = tokens[0];

            try {
                switch (tip) {
                    case "STANDARD" -> comenzi.add(new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
                    case "DISCOUNTED" -> comenzi.add(new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]));
                    case "GIFT" -> comenzi.add(new ComandaGratuita(tokens[1], tokens[2]));
                    default -> throw new InvalidInputException("Tip comandă necunoscut: " + tip);
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        // Afișăm comenzile inițiale
        comenzi.forEach(c -> System.out.println(c.descriere()));

        // Loop-ul pentru analize
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split(" ");
            String comanda = tokens[0];

            if (comanda.equals("QUIT")) {
                break;
            }
            else if (comanda.equals("STATS")) {
                System.out.println("\n--- STATS ---");
                // Grupăm datele după tipul instanței și calculăm media
                Map<String, Double> medii = comenzi.stream().collect(Collectors.groupingBy(
                        c -> {
                            if (c instanceof ComandaStandard) return "STANDARD";
                            if (c instanceof ComandaRedusa) return "DISCOUNTED";
                            return "GIFT";
                        },
                        Collectors.averagingDouble(Comanda::pretFinal)
                ));

                if (medii.containsKey("STANDARD")) System.out.printf(Locale.US, "STANDARD: medie = %.2f lei\n", medii.get("STANDARD"));
                if (medii.containsKey("DISCOUNTED")) System.out.printf(Locale.US, "DISCOUNTED: medie = %.2f lei\n", medii.get("DISCOUNTED"));
                if (medii.containsKey("GIFT")) System.out.printf(Locale.US, "GIFT: medie = %.2f lei\n", medii.get("GIFT"));
            }
            else if (comanda.equals("FILTER")) {
                double threshold = Double.parseDouble(tokens[1]);
                System.out.printf(Locale.US, "\n--- FILTER (>= %.2f) ---\n", threshold);

                // Filtrare simplă după condiție
                comenzi.stream()
                        .filter(c -> c.pretFinal() >= threshold)
                        .forEach(c -> System.out.println(c.descriere().replace(" [PLACED]", "")));
            }
            else if (comanda.equals("SORT")) {
                System.out.println("\n--- SORT (by client, then by pret) ---");

                // Sortare multiplă
                comenzi.stream()
                        .sorted(Comparator.comparing(Comanda::getClient).thenComparing(Comanda::pretFinal))
                        .forEach(c -> System.out.println(c.descriere().replace(" [PLACED]", "")));
            }
            else if (comanda.equals("SPECIAL")) {
                System.out.println("\n--- SPECIAL (discount > 15%) ---");

                // Folosim pattern matching cu 'instanceof' direct în filtru!
                comenzi.stream()
                        .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscountProcent() > 15)
                        .forEach(c -> System.out.println(c.descriere().replace(" [PLACED]", "")));
            }
        }
        sc.close();
    }
}