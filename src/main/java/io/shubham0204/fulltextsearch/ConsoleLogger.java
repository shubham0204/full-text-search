package io.shubham0204.fulltextsearch;

public class ConsoleLogger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void error(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static void info(String message) {
        System.out.println(message);
    }

}
