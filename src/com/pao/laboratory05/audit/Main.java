package com.pao.laboratory05.audit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();

        while (true) {
            System.out.println("\n===== Gestionare Angajați (cu Audit) =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("4. Afișează audit log"); // Opțiunea nouă
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
                    System.out.print("Nume angajat: ");
                    String nume = scanner.nextLine();
                    System.out.print("Nume departament: ");
                    String numeDepartament = scanner.nextLine();
                    System.out.print("Locație departament: ");
                    String locatieDepartament = scanner.nextLine();
                    System.out.print("Salariu: ");
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
                    System.out.print("Introduceți numele departamentului: ");
                    String cautareDept = scanner.nextLine();
                    System.out.println("\n--- Rezultate pentru '" + cautareDept + "' ---");
                    service.findByDepartament(cautareDept);
                    break;

                case 4:
                    System.out.println("\n--- Audit Log ---");
                    service.printAuditLog();
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