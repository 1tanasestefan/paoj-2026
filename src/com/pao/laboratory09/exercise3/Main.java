package com.pao.laboratory09.exercise3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CoadaTranzactii coada = new CoadaTranzactii();

        // 1. Create 3 ATM producer threads and 1 consumer thread
        ATMThread atm1 = new ATMThread(1, coada);
        ATMThread atm2 = new ATMThread(2, coada);
        ATMThread atm3 = new ATMThread(3, coada);
        ProcessorThread processorThread = new ProcessorThread(coada);

        // 2. Start all producers
        atm1.start();
        atm2.start();
        atm3.start();

        // 3. Start consumer on a new thread
        Thread consumerThread = new Thread(processorThread);
        consumerThread.start();

        // 4. Join all 3 ATM threads (main waits for all producers to finish)
        atm1.join();
        atm2.join();
        atm3.join();

        // 5. Signal consumer to stop and wake it up if it's waiting on an empty queue
        processorThread.activ = false;
        coada.wakeAll();

        // 6. Join consumer thread
        consumerThread.join();

        // 7. Done
        System.out.println("Toate tranzactiile procesate. Total: 12");
    }
}
