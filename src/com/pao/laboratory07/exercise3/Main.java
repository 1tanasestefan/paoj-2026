package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise2.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split(" ");
            if (tokens[0].equals("STANDARD")) {
                comenzi.add(new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
            } else if (tokens[0].equals("DISCOUNTED")) {
                comenzi.add(new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]));
            } else if (tokens[0].equals("GIFT")) {
                comenzi.add(new ComandaGratuita(tokens[1], tokens[2]));
            }
        }

        for (Comanda c : comenzi) {
            System.out.println(c.descriere());
        }

        while (sc.hasNextLine()) {
            String cmdLine = sc.nextLine().trim();
            if (cmdLine.isEmpty()) continue;
            String[] tokens = cmdLine.split(" ");
            String cmd = tokens[0].toUpperCase();

            if (cmd.equals("STATS")) {
                System.out.println("\n--- STATS ---");
                Map<Class<? extends Comanda>, Double> avgs = comenzi.stream()
                        .collect(Collectors.groupingBy(
                                Comanda::getClass,
                                Collectors.averagingDouble(Comanda::pretFinal)
                        ));

                System.out.printf(Locale.US, "STANDARD: medie = %.2f lei\n", avgs.getOrDefault(ComandaStandard.class, 0.0));
                System.out.printf(Locale.US, "DISCOUNTED: medie = %.2f lei\n", avgs.getOrDefault(ComandaRedusa.class, 0.0));
                System.out.printf(Locale.US, "GIFT: medie = %.2f lei\n", avgs.getOrDefault(ComandaGratuita.class, 0.0));

            } else if (cmd.equals("FILTER")) {
                double threshold = Double.parseDouble(tokens[1]);
                System.out.printf(Locale.US, "\n--- FILTER (>= %.2f) ---\n", threshold);
                comenzi.stream()
                        .filter(c -> c.pretFinal() >= threshold)
                        .forEach(c -> System.out.println(c.descriere().replace(" [PLACED]", "")));

            } else if (cmd.equals("SORT")) {
                System.out.println("\n--- SORT (by client, then by pret) ---");
                comenzi.stream()
                        .sorted(Comparator.comparing(Comanda::getClient).thenComparing(Comanda::pretFinal))
                        .forEach(c -> System.out.println(c.descriere().replace(" [PLACED]", "")));

            } else if (cmd.equals("SPECIAL")) {
                System.out.println("\n--- SPECIAL (discount > 15%) ---");
                comenzi.stream()
                        .filter(c -> c instanceof ComandaRedusa)
                        .map(c -> (ComandaRedusa) c)
                        .filter(c -> c.getDiscountProcent() > 15)
                        .forEach(c -> System.out.println(c.descriere().replace(" [PLACED]", "")));

            } else if (cmd.equals("QUIT")) {
                break;
            }
        }
    }
}
