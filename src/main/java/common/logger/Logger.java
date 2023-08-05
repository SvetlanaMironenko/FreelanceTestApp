package common.logger;

public class Logger {

    private static void printMessage(Color currentColor, String messageHeader, String message) {
        System.out.println(currentColor.getColor() + "[" + messageHeader + "]: " + message + Color.RESET.getColor());
    }

    public static void debug(String message) {
        printMessage(Color.BLUE, "DEBUG", message);
    }

    public static void error(String message) {
        printMessage(Color.RED, "ERROR", message);
    }

    public static void info(String message) {
        printMessage(Color.RESET, "INFO", message);
    }

    public static void warning(String message) {
        printMessage(Color.YELLOW, "WARNING", message);
    }

    public static void fail(String message) {
        printMessage(Color.RED, "FAILED", message);
        throw new RuntimeException(message);
    }

    public static void step(String message) {
        printMessage(Color.GREEN, "STEP", message);
    }

    public static void wait(int sec, String message) {
        printMessage(Color.GREEN, "WAIT", message);
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
