package com.pao.laboratory03.enums;

public enum Priority {
    LOW(1, "green") {
        @Override
        public String getEmoji() { return "🟢"; }
    },
    MEDIUM(2, "yellow") {
        @Override
        public String getEmoji() { return "🟡"; }
    },
    HIGH(3, "orange") {
        @Override
        public String getEmoji() { return "🟠"; }
    },
    CRITICAL(4, "red") {
        @Override
        public String getEmoji() { return "🔴"; }
    };

    private final int level;
    private final String color;

    // Constructorul este privat implicit pentru enum-uri
    Priority(int level, String color) {
        this.level = level;
        this.color = color;
    }

    public int getLevel() { return level; }
    public String getColor() { return color; }

    // Metoda abstractă pe care fiecare constantă trebuie să o implementeze
    public abstract String getEmoji();
}