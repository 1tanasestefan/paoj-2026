package com.pao.laboratory05.audit;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AngajatService {

    private Angajat[] angajati = new Angajat[0];
    private AuditEntry[] auditLog = new AuditEntry[0]; // Array-ul nou pentru audit

    private AngajatService() {}

    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    private void logAction(String action, String target) {
        AuditEntry entry = new AuditEntry(action, target, LocalDateTime.now().toString());

        AuditEntry[] newLog = new AuditEntry[auditLog.length + 1];
        System.arraycopy(auditLog, 0, newLog, 0, auditLog.length);
        newLog[auditLog.length] = entry;
        auditLog = newLog;
    }

    public void addAngajat(Angajat a) {
        Angajat[] newAngajati = new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, newAngajati, 0, angajati.length);
        newAngajati[angajati.length] = a;
        angajati = newAngajati;

        System.out.println("Angajatul " + a.getNume() + " a fost adăugat cu succes!");


        logAction("ADD", a.getNume());
    }

    public void listBySalary() {
        if (angajati.length == 0) {
            System.out.println("Nu există angajați de sortat.");
            return;
        }

        Angajat[] copy = angajati.clone();
        Arrays.sort(copy);

        for (Angajat a : copy) {
            System.out.println(a);
        }
    }

    public void findByDepartament(String numeDept) {
        logAction("FIND_BY_DEPT", numeDept);

        boolean gasit = false;
        for (Angajat a : angajati) {
            if (a.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(a);
                gasit = true;
            }
        }

        if (!gasit) {
            System.out.println("Niciun angajat în departamentul: " + numeDept);
        }
    }

    public void printAuditLog() {
        if (auditLog.length == 0) {
            System.out.println("Nu există intrări în audit log.");
            return;
        }
        for (AuditEntry entry : auditLog) {
            System.out.println(entry);
        }
    }
}