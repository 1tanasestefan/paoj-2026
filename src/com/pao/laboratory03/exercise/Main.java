package com.pao.laboratory03.exercise;

import com.pao.laboratory03.exercise.model.Subject;
import com.pao.laboratory03.exercise.service.StudentService;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pas 1: Obține instanța StudentService (Singleton)
        StudentService service = StudentService.getInstance();

        System.out.println("=== Sistem Gestiune Studenți ===");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Meniu ---");
            System.out.println("1. Adaugă student");
            System.out.println("2. Adaugă notă");
            System.out.println("3. Afișează toți studenții");
            System.out.println("4. Top studenți (după medie)");
            System.out.println("5. Media pe materie");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            String option = scanner.nextLine().trim();

            try {
                switch (option) {
                    case "1":
                        System.out.print("Nume: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Vârsta: ");
                        int age = Integer.parseInt(scanner.nextLine().trim());

                        // Pas 2: Adăugare student
                        service.addStudent(name, age);
                        System.out.println("Student adăugat cu succes!");
                        break;

                    case "2":
                        System.out.print("Nume student: ");
                        String studentName = scanner.nextLine().trim();

                        // Pas 3: Afișare materii din Enum dinamic
                        System.out.print("Materie (" + Arrays.toString(Subject.values()) + "): ");
                        String subjectStr = scanner.nextLine().trim().toUpperCase();

                        System.out.print("Nota (1-10): ");
                        double grade = Double.parseDouble(scanner.nextLine().trim());

                        // Pas 4: Conversie String -> Enum și adăugare notă
                        Subject subject = Subject.valueOf(subjectStr);
                        service.addGrade(studentName, subject, grade);
                        System.out.println("Notă adăugată!");
                        break;

                    case "3":
                        // Pas 5: Listare toți studenții
                        service.printAllStudents();
                        break;

                    case "4":
                        // Pas 6: Afișare top studenți
                        System.out.println("Top studenți (descrescător după medie):");
                        service.printTopStudents();
                        break;

                    case "5":
                        // Pas 7: Calcul și afișare medii pe materie
                        System.out.println("Medii pe materie:");
                        Map<Subject, Double> averages = service.getAveragePerSubject();
                        if (averages.isEmpty()) {
                            System.out.println("Nu există note introduse încă.");
                        } else {
                            averages.forEach((sub, avg) ->
                                    System.out.printf("%s: %.2f\n", sub, avg));
                        }
                        break;

                    case "0":
                        running = false;
                        System.out.println("La revedere!");
                        break;

                    default:
                        System.out.println("Opțiune invalidă.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Eroare: Introdu un număr valid (vârstă/notă).");
            } catch (IllegalArgumentException e) {
                // Aceasta prinde și erorile de la Subject.valueOf() dacă user-ul scrie o materie greșită
                System.out.println("Eroare: Materia introdusă nu există.");
            } catch (RuntimeException e) {
                // Prinde InvalidStudentException, InvalidGradeException, StudentNotFoundException etc.
                System.out.println("Eroare: " + e.getMessage());
            }
        }

        scanner.close();
    }
}