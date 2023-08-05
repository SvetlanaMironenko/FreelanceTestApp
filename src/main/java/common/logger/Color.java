package common.logger;

public enum Color {
    RESET("\u001B[0m"),
    BLUE("\u001B[34m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
