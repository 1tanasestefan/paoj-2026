package com.pao.laboratory08.exercise1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> studenti = new ArrayList<>();
        String fisier = "src/com/pao/laboratory08/tests/studenti.txt";

        // Partea A - Citirea din fișier
        try (BufferedReader br = new BufferedReader(new FileReader(fisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                // Împărțim linia CSV prin virgulă
                String[] date = linie.split(",");
                if (date.length == 4) {
                    String nume = date[0].trim();
                    int varsta = Integer.parseInt(date[1].trim());
                    String oras = date[2].trim();
                    String strada = date[3].trim();

                    Adresa adresa = new Adresa(oras, strada);
                    Student student = new Student(nume, varsta, adresa);
                    studenti.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("A apărut o eroare la citirea fișierului: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return;

        // Citim comanda și o împărțim în maxim 2 bucăți (Comanda și Numele, dacă există)
        String input = scanner.nextLine().trim();
        String[] comandaSplit = input.split(" ", 2);
        String comanda = comandaSplit[0];

        // Răspundem la comenzile specificate
        if (comanda.equals("PRINT")) {
            for (Student s : studenti) {
                System.out.println(s);
            }
        } else if (comanda.equals("SHALLOW") || comanda.equals("DEEP")) {
            if (comandaSplit.length < 2) return;
            String numeCautat = comandaSplit[1];

            // Căutăm studentul în lista citită
            Student studentOriginal = null;
            for (Student s : studenti) {
                if (s.getNume().equals(numeCautat)) {
                    studentOriginal = s;
                    break;
                }
            }

            if (studentOriginal != null) {
                try {
                    Student clona;

                    // Apelăm metoda corespunzătoare
                    if (comanda.equals("SHALLOW")) {
                        clona = studentOriginal.shallowClone();
                    } else {
                        clona = studentOriginal.deepClone();
                    }

                    // Părțile B și C - Modificăm clona
                    clona.getAdresa().setOras("MODIFICAT");

                    System.out.println("Original: " + studentOriginal);
                    System.out.println("Clona: " + clona);

                } catch (CloneNotSupportedException e) {
                    System.out.println("Eroare la clonare: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }
}