package com.pao.laboratory09.exercise3;

/**
 * Producer thread — simulates an ATM sending 4 transactions to the shared queue.
 * Extends Thread (one of the two ways to create threads in Java).
 */
public class ATMThread extends Thread {
    private final int atmId;
    private final CoadaTranzactii coada;
    // Shared atomic counter for unique transaction IDs across all ATMs
    private static int nextId = 1;

    public ATMThread(int atmId, CoadaTranzactii coada) {
        this.atmId = atmId;
        this.coada = coada;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            int id;
            synchronized (ATMThread.class) {
                id = nextId++;
            }
            double suma = 100.0 * id + 50.0 * atmId;
            Tranzactie t = new Tranzactie(id, suma, "2024-01-15");

            // Print wait message if queue is full (before blocking in adauga)
            if (coada.size() >= 5) {
                System.out.println("[ATM-" + atmId + "] astept loc...");
            }

            try {
                System.out.println("[ATM-" + atmId + "] trimite: Tranzactie #" + id + " " + (long) suma + " RON");
                coada.adauga(t);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
