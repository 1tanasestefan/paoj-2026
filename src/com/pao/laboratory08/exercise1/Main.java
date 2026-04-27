package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    // Calea către fișierul cu date — relativă la rădăcina proiectului
    private static final String FILE_PATH = "laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Student> studenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String nume = parts[0].trim();
                    int varsta = Integer.parseInt(parts[1].trim());
                    String oras = parts[2].trim();
                    String strada = parts[3].trim();
                    studenti.add(new Student(nume, varsta, new Adresa(oras, strada)));
                }
            }
        }

        if (!scanner.hasNextLine()) return;
        String commandLine = scanner.nextLine().trim();
        String[] cmdParts = commandLine.split(" ", 2);
        String cmd = cmdParts[0].toUpperCase();

        if (cmd.equals("PRINT")) {
            for (Student s : studenti) {
                System.out.println(s);
            }
        } else if (cmd.equals("SHALLOW") || cmd.equals("DEEP")) {
            if (cmdParts.length < 2) return;
            String numeTarget = cmdParts[1].trim();
            Student original = null;
            for (Student s : studenti) {
                if (s.getNume().equals(numeTarget)) {
                    original = s;
                    break;
                }
            }

            if (original != null) {
                Student clona = null;
                if (cmd.equals("SHALLOW")) {
                    clona = original.shallowClone();
                } else {
                    clona = original.deepClone();
                }

                clona.getAdresa().setOras("MODIFICAT");

                System.out.println("Original: " + original);
                System.out.println("Clona: " + clona);
            }
        }
    }
}
