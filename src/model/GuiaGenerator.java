package model;

import java.util.concurrent.atomic.AtomicLong;

public class GuiaGenerator {
    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());

    public static String generarNumero() {
        return "GUIA" + counter.getAndIncrement();
    }
}
