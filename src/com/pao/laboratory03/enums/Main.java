package com.pao.laboratory03.enums;

public class Main {
    public static void main(String[] args) {

        // a) Parcurge toate valorile și afișează detaliile
        System.out.println("=== Toate prioritățile ===");
        for (Priority p : Priority.values()) {
            System.out.println(p.getEmoji() + " " + p.name() +
                    " (level=" + p.getLevel() + ", color=" + p.getColor() + ")");
        }

        System.out.println("\n=== Switch pe prioritate ===");
        Priority current = Priority.HIGH;
        // b) Switch pe un Priority
        switch (current) {
            case LOW, MEDIUM -> System.out.println("Totul este sub control.");
            case HIGH -> System.out.println("⚠️ Atenție! Prioritate ridicată!");
            case CRITICAL -> System.out.println("🚨 ALERTĂ! Intervenție imediată!");
        }

        System.out.println("\n=== valueOf ===");
        // c) Convertește String în Priority
        Priority highFromStr = Priority.valueOf("HIGH");
        System.out.println("Priority.valueOf(\"HIGH\") = " + highFromStr);

        System.out.println("\n=== Comparare enum ===");
        // d) Demonstrează compararea cu ==
        System.out.println("HIGH == HIGH? " + (current == highFromStr));
        System.out.println("HIGH == LOW? " + (current == Priority.LOW));

        System.out.println("\n=== name() și ordinal() ===");
        // e) Afișează name() și ordinal()
        for (Priority p : Priority.values()) {
            System.out.println(p.name() + ": name=" + p.name() + ", ordinal=" + p.ordinal());
        }
    }
}