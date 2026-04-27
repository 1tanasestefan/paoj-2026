package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    // Status byte values
    private static final byte STATUS_PENDING   = 0;
    private static final byte STATUS_PROCESSED = 1;
    private static final byte STATUS_REJECTED  = 2;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read N transactions
        int n = Integer.parseInt(scanner.nextLine().trim());

        // Keep track of the number of records for PRINT_ALL
        int[] ids = new int[n];
        double[] sums = new double[n];
        String[] dates = new String[n];
        TipTranzactie[] tips = new TipTranzactie[n];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            ids[i] = Integer.parseInt(parts[0]);
            sums[i] = Double.parseDouble(parts[1]);
            dates[i] = parts[2];
            tips[i] = TipTranzactie.valueOf(parts[3]);
        }

        // Step 2: Write all records to binary file with DataOutputStream
        new File(OUTPUT_FILE).getParentFile().mkdirs();
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                // bytes 0-3: id (int, little-endian)
                byte[] idBytes = ByteBuffer.allocate(4)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .putInt(ids[i])
                        .array();
                dos.write(idBytes);

                // bytes 4-11: suma (double, little-endian)
                byte[] sumBytes = ByteBuffer.allocate(8)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .putDouble(sums[i])
                        .array();
                dos.write(sumBytes);

                // bytes 12-21: data (10 chars ASCII, right-padded with spaces)
                byte[] dateBytes = new byte[10];
                Arrays.fill(dateBytes, (byte) ' ');
                byte[] raw = dates[i].getBytes("ASCII");
                System.arraycopy(raw, 0, dateBytes, 0, Math.min(raw.length, 10));
                dos.write(dateBytes);

                // byte 22: tip (0=CREDIT, 1=DEBIT)
                dos.write(tips[i] == TipTranzactie.CREDIT ? 0 : 1);

                // byte 23: status (0=PENDING)
                dos.write(STATUS_PENDING);

                // bytes 24-31: padding (zeroes)
                dos.write(new byte[8]);
            }
        }

        // Step 3: Process commands with RandomAccessFile
        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("READ ")) {
                    int idx = Integer.parseInt(line.substring(5).trim());
                    System.out.println(readRecord(raf, idx));

                } else if (line.startsWith("UPDATE ")) {
                    String[] parts = line.substring(7).trim().split("\\s+");
                    int idx = Integer.parseInt(parts[0]);
                    String statusStr = parts[1];
                    byte statusByte = statusToByte(statusStr);
                    raf.seek((long) idx * RECORD_SIZE + 23);
                    raf.write(statusByte);
                    System.out.println("Updated [" + idx + "]: " + statusStr);

                } else if (line.equals("PRINT_ALL")) {
                    for (int i = 0; i < n; i++) {
                        System.out.println(readRecord(raf, i));
                    }
                }
            }
        }
    }

    private static String readRecord(RandomAccessFile raf, int idx) throws IOException {
        raf.seek((long) idx * RECORD_SIZE);
        byte[] record = new byte[RECORD_SIZE];
        raf.readFully(record);

        ByteBuffer buf = ByteBuffer.wrap(record).order(ByteOrder.LITTLE_ENDIAN);
        int id = buf.getInt(0);
        double suma = buf.getDouble(4);
        // Read date: bytes 12-21 (10 bytes), trim trailing spaces
        byte[] dateBytes = new byte[10];
        System.arraycopy(record, 12, dateBytes, 0, 10);
        String data = new String(dateBytes, "ASCII").trim();

        byte tipByte = record[22];
        String tip = (tipByte == 0) ? "CREDIT" : "DEBIT";

        byte statusByte = record[23];
        String status = statusToString(statusByte);

        return String.format("[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s",
                idx, id, data, tip, suma, status);
    }

    private static byte statusToByte(String status) {
        return switch (status) {
            case "PROCESSED" -> STATUS_PROCESSED;
            case "REJECTED"  -> STATUS_REJECTED;
            default          -> STATUS_PENDING;
        };
    }

    private static String statusToString(byte b) {
        return switch (b) {
            case STATUS_PROCESSED -> "PROCESSED";
            case STATUS_REJECTED  -> "REJECTED";
            default               -> "PENDING";
        };
    }
}
