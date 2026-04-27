package com.pao.laboratory09.exercise3;

/**
 * Consumer thread — implements Runnable (second way to create threads in Java).
 * Processes transactions from the shared queue until activ == false.
 */
public class ProcessorThread implements Runnable {
    /** volatile ensures visibility across threads without synchronization overhead */
    public volatile boolean activ = true;

    private final CoadaTranzactii coada;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        while (activ) {
            try {
                Tranzactie t = coada.extrage();
                System.out.printf("[Processor] Factura #%d - %.0f RON | %s%n",
                        t.getId(), t.getSuma(), t.getData());
                Thread.sleep(80);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
