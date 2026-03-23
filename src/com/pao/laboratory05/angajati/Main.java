package com.pao.laboratory05.angajati;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();

        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("4. Afișează toți angajații (nesortat)");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            int optiune;

            if (scanner.hasNextInt()) {
                optiune = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Opțiune invalidă. Introduceți un număr.");
                scanner.nextLine();
                continue;
            }

            switch (optiune) {
                case 1:
                    System.out.print("Introduceți numele angajatului: ");
                    String nume = scanner.nextLine();

                    System.out.print("Introduceți numele departamentului: ");
                    String numeDepartament = scanner.nextLine();

                    System.out.print("Introduceți locația departamentului: ");
                    String locatieDepartament = scanner.nextLine();

                    System.out.print("Introduceți salariul: ");
                    double salariu = scanner.nextDouble();
                    scanner.nextLine();


                    Departament dept = new Departament(numeDepartament, locatieDepartament);
                    Angajat angajat = new Angajat(nume, dept, salariu);
                    service.addAngajat(angajat);
                    break;

                case 2:
                    System.out.println("\n--- Listare angajați după salariu (descrescător) ---");
                    service.listBySalary();
                    break;

                case 3:
                    System.out.print("Introduceți numele departamentului pentru căutare: ");
                    String cautareDept = scanner.nextLine();
                    System.out.println("\n--- Rezultatele căutării pentru '" + cautareDept + "' ---");
                    service.findByDepartament(cautareDept);
                    break;

                case 4:
                    System.out.println("\n--- Toți angajații ---");
                    service.printAll();
                    break;

                case 0:
                    System.out.println("Se închide programul...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opțiune inexistentă. Încercați din nou.");
            }
        }
    }
}