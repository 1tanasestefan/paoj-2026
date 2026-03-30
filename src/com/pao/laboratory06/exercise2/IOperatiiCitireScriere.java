package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public interface IOperatiiCitireScriere {
    void citeste(Scanner in);
    void afiseaza();
    String tipContract();

    // Metodă default, suprascrisă doar acolo unde e nevoie (la CIM)
    default boolean areBonus() {
        return false;
    }
}