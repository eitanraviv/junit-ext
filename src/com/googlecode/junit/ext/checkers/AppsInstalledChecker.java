package com.googlecode.junit.ext.checkers;

public class AppsInstalledChecker implements Checker {
    private final String command;

    public AppsInstalledChecker(String command) {
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

