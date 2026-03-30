package com.pao.laboratory06.exercise2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return;

        String firstLine = scanner.nextLine().trim();
        if (firstLine.isEmpty()) return;

        int n = Integer.parseInt(firstLine);
        List<Colaborator> lista = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                i--;
                continue;
            }

            Scanner lineScanner = new Scanner(line);
            String tipStr = lineScanner.next();
            TipColaborator tip = TipColaborator.valueOf(tipStr);

            Colaborator c = null;
            if (tip == TipColaborator.CIM) c = new CIMColaborator();
            else if (tip == TipColaborator.PFA) c = new PFAColaborator();
            else if (tip == TipColaborator.SRL) c = new SRLColaborator();

            if (c != null) {
                c.citeste(lineScanner);
                lista.add(c);
            }
        }

        // 1. Afișare nesortată
        for (Colaborator c : lista) {
            c.afiseaza();
        }

        if (!lista.isEmpty()) {

            // 2. Colaborator Maxim
            Colaborator max = lista.get(0);
            for (Colaborator c : lista) {
                if (c.calculeazaVenitNetAnual() > max.calculeazaVenitNetAnual()) {
                    max = c;
                }
            }
            System.out.print("\nColaborator cu venit net maxim: ");
            max.afiseaza();

            // 3. Persoane Juridice
            System.out.println("\nColaboratori persoane juridice:");
            for (Colaborator c : lista) {
                if (c instanceof PersoanaJuridica) {
                    c.afiseaza();
                }
            }

            // 4. Sume și număr
            System.out.println("\nSume și număr colaboratori pe tip:");
            double sumaCIM = 0, sumaPFA = 0, sumaSRL = 0;
            int countCIM = 0, countPFA = 0, countSRL = 0;

            for (Colaborator c : lista) {
                double net = c.calculeazaVenitNetAnual();
                if (c instanceof CIMColaborator) { sumaCIM += net; countCIM++; }
                else if (c instanceof PFAColaborator) { sumaPFA += net; countPFA++; }
                else if (c instanceof SRLColaborator) { sumaSRL += net; countSRL++; }
            }

            if (countCIM > 0) System.out.printf(Locale.US, "CIM: suma = %.2f lei, număr = %d\n", sumaCIM, countCIM);
            if (countPFA > 0) System.out.printf(Locale.US, "PFA: suma = %.2f lei, număr = %d\n", sumaPFA, countPFA);
            if (countSRL > 0) System.out.printf(Locale.US, "SRL: suma = %.2f lei, număr = %d\n", sumaSRL, countSRL);
        }

        scanner.close();
    }
}