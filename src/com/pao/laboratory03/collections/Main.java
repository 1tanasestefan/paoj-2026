package com.pao.laboratory03.collections;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // === PARTEA A: HashMap (frecvența cuvintelor) ===
        System.out.println("=== PARTEA A: HashMap — frecvența cuvintelor ===");

        // 1. Declară array-ul de cuvinte
        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};

        // 2. Creează HashMap și contorizează frecvența
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            // getOrDefault returnează valoarea actuală sau 0 dacă cheia nu există, apoi incrementăm
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // 3. Afișează map-ul
        System.out.println("Frecvență: " + frequencyMap);

        // 4. Verifică existența cheii "rust"
        System.out.println("Conține 'rust'? " + frequencyMap.containsKey("rust"));

        // 5. Afișează doar cheile și apoi doar valorile
        System.out.println("Chei: " + frequencyMap.keySet());
        System.out.println("Valori: " + frequencyMap.values());

        // 6. Parcurge cu entrySet()
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println();

        // === PARTEA B: TreeMap (sortare automată) ===
        System.out.println("=== PARTEA B: TreeMap — sortare automată ===");

        // 7. Creează TreeMap din HashMap (se va sorta alfabetic după chei)
        TreeMap<String, Integer> sortedMap = new TreeMap<>(frequencyMap);

        // 8. Afișează TreeMap
        System.out.println("Sortat: " + sortedMap);

        // 9. Prima și ultima cheie
        System.out.println("Prima cheie: " + sortedMap.firstKey());
        System.out.println("Ultima cheie: " + sortedMap.lastKey());

        System.out.println();

        // === PARTEA C: Map cu obiecte ===
        System.out.println("=== PARTEA C: Map cu obiecte ===");

        // 10. Creează HashMap<String, List<String>>
        Map<String, List<String>> cursuri = new HashMap<>();

        // Adăugare date inițiale
        cursuri.put("PAOJ", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
        cursuri.put("BD", new ArrayList<>(Arrays.asList("Ana", "Elena")));

        // 11. Afișează studenții de la PAOJ
        System.out.println("Studenți la PAOJ: " + cursuri.get("PAOJ"));

        // 12. Adaugă un student nou la "BD"
        // Folosim get() pentru a lua lista existentă și add() pentru noul student
        if (cursuri.containsKey("BD")) {
            cursuri.get("BD").add("George");
        }
        System.out.println("Studenți la BD (actualizat): " + cursuri.get("BD"));
    }
}