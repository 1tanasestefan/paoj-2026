package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Adresa;
import com.pao.laboratory08.exercise1.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> studenti = new ArrayList<>();
        String fisierIntrare = "src/com/pao/laboratory08/tests/studenti.txt";
        String fisierIesire = "rezultate.txt";

        // Pasul 1: Citirea din fișier (ca la Exercițiul 1)
        try (BufferedReader br = new BufferedReader(new FileReader(fisierIntrare))) {
            String linie;
            while ((linie = br.readLine()) != null) {
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
            System.out.println("Eroare la citirea fișierului de intrare: " + e.getMessage());
            return;
        }

        // Pasul 2: Citirea pragului de vârstă de la tastatură
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int prag = scanner.nextInt();
        scanner.close();

        // Pasul 3: Filtrarea studenților (putem folosi un loop clasic sau Stream API)
        List<Student> studentiFiltrati = new ArrayList<>();
        for (Student s : studenti) {
            if (s.getVarsta() >= prag) {
                studentiFiltrati.add(s);
            }
        }

        // Afișăm sumarul la consolă (cum se cere în output)
        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + studentiFiltrati.size() + " studenti\n");

        for (Student s : studentiFiltrati) {
            System.out.println(s);
        }

        System.out.println("\nScris in: " + fisierIesire);

        // Pasul 4: Scrierea în fișier folosind try-with-resources
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fisierIesire))) {
            for (Student s : studentiFiltrati) {
                bw.write(s.toString()); // Scriem reprezentarea sub formă de String
                bw.newLine();           // Trecem la linia următoare
            }
        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }
}