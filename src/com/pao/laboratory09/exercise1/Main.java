package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read N transactions
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Tranzactie> tranzactii = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            int id = Integer.parseInt(parts[0]);
            double suma = Double.parseDouble(parts[1]);
            String data = parts[2];
            String contSursa = parts[3];
            String contDestinatie = parts[4];
            TipTranzactie tip = TipTranzactie.valueOf(parts[5]);
            Tranzactie t = new Tranzactie(id, suma, data, contSursa, contDestinatie, tip);
            // Step 2: Set note = "procesat" before serialization
            t.setNote("procesat");
            tranzactii.add(t);
        }

        // Step 3: Serialize
        new File(OUTPUT_FILE).getParentFile().mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            oos.writeObject(tranzactii);
        }

        // Step 4: Deserialize (note will be null due to transient)
        List<Tranzactie> deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            @SuppressWarnings("unchecked")
            List<Tranzactie> tmp = (List<Tranzactie>) ois.readObject();
            deserialized = tmp;
        }

        // Step 5: Process commands until EOF
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null) break;
            line = line.trim();
            if (line.isEmpty()) continue;

            if (line.equals("LIST")) {
                for (Tranzactie t : deserialized) {
                    System.out.println(t);
                }
            } else if (line.startsWith("FILTER ")) {
                String prefix = line.substring(7).trim();
                boolean found = false;
                for (Tranzactie t : deserialized) {
                    if (t.getData().startsWith(prefix)) {
                        System.out.println(t);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Niciun rezultat.");
                }
            } else if (line.startsWith("NOTE ")) {
                int id = Integer.parseInt(line.substring(5).trim());
                Tranzactie found = null;
                for (Tranzactie t : deserialized) {
                    if (t.getId() == id) {
                        found = t;
                        break;
                    }
                }
                if (found != null) {
                    System.out.println("NOTE[" + id + "]: " + found.getNote());
                } else {
                    System.out.println("NOTE[" + id + "]: not found");
                }
            }
        }
    }
}
