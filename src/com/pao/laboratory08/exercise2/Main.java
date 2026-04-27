package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Student> studenti = new ArrayList<>();

        // 1. Citește studenții din FILE_PATH cu BufferedReader
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

        // 2. Citește pragul de vârstă din stdin cu Scanner
        if (!scanner.hasNextInt()) return;
        int prag = scanner.nextInt();

        // 3. Filtrează studenții cu varsta >= prag
        List<Student> filtrati = new ArrayList<>();
        for (Student s : studenti) {
            if (s.getVarsta() >= prag) {
                filtrati.add(s);
            }
        }

        // 4. Scrie filtrații în "rezultate.txt" cu BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("rezultate.txt"))) {
            for (Student s : filtrati) {
                bw.write(s.toString());
                bw.newLine();
            }
        }

        // 5. Afișează sumarul la consolă
        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + filtrati.size() + " studenti\n");
        for (Student s : filtrati) {
            System.out.println(s);
        }
        System.out.println("\nScris in: rezultate.txt");
    }
}

