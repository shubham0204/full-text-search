package io.shubham0204.fulltextsearch;

public class ConsoleLogger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void error(String message) {
        System.out.println(ANSI_RED + "fatal: " + message + ANSI_RESET);
    }
    public static void warn(String message) {
        System.out.println( "warn: " + message);
    }

    public static void info(String message) {
        System.out.println(message);
    }

    public static void success(String message) {
        System.out.println(ANSI_YELLOW + message + ANSI_RESET);
    }

}
