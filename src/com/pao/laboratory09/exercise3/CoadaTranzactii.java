package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Shared bounded queue (capacity 5) — Producer-Consumer band.
 * All methods are synchronized on this object.
 */
public class CoadaTranzactii {
    private static final int CAPACITY = 5;
    private final Queue<Tranzactie> queue = new LinkedList<>();

    /**
     * Adds a transaction; waits (blocks) while the queue is full.
     */
    public synchronized void adauga(Tranzactie t) throws InterruptedException {
        while (queue.size() >= CAPACITY) {
            // Signal to caller that we are waiting (optional per README)
            // The ATM thread prints this message before calling wait()
            wait();
        }
        queue.add(t);
        notifyAll();
    }

    /**
     * Extracts a transaction; waits (blocks) while the queue is empty.
     */
    public synchronized Tranzactie extrage() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Tranzactie t = queue.poll();
        notifyAll();
        return t;
    }

    /**
     * Returns current queue size (used by ATM threads to print wait message).
     */
    public synchronized int size() {
        return queue.size();
    }

    /**
     * Wakes up all waiting threads (used for graceful shutdown).
     */
    public synchronized void wakeAll() {
        notifyAll();
    }
}
