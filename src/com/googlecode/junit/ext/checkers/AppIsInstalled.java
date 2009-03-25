package com.googlecode.junit.ext.checkers;

public class AppIsInstalled implements Checker {
    private final String command;

    public AppIsInstalled(String command) {
        this.command = command;
    }

    public boolean satisfy() {
        try {
            Runtime.getRuntime().exec(this.command);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

