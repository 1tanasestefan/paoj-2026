package com.pao.laboratory03.collections;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        System.out.println("=== PARTEA A: HashMap — frecvența cuvintelor ===");


        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};


        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {

            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }


        System.out.println("Frecvență: " + frequencyMap);


        System.out.println("Conține 'rust'? " + frequencyMap.containsKey("rust"));


        System.out.println("Chei: " + frequencyMap.keySet());
        System.out.println("Valori: " + frequencyMap.values());


        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println();

        System.out.println("=== PARTEA B: TreeMap — sortare automată ===");


        TreeMap<String, Integer> sortedMap = new TreeMap<>(frequencyMap);


        System.out.println("Sortat: " + sortedMap);

        System.out.println("Prima cheie: " + sortedMap.firstKey());
        System.out.println("Ultima cheie: " + sortedMap.lastKey());

        System.out.println();


        System.out.println("=== PARTEA C: Map cu obiecte ===");


        Map<String, List<String>> cursuri = new HashMap<>();

        cursuri.put("PAOJ", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
        cursuri.put("BD", new ArrayList<>(Arrays.asList("Ana", "Elena")));


        System.out.println("Studenți la PAOJ: " + cursuri.get("PAOJ"));


        if (cursuri.containsKey("BD")) {
            cursuri.get("BD").add("George");
        }
        System.out.println("Studenți la BD (actualizat): " + cursuri.get("BD"));
    }
}