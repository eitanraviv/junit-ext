package com.googlecode.junit.ext.checkers;

public class RunningOnTargetOS implements Checker {
    public static final String MAC = "mac";
    public static final String LINUX = "linux";
    public static final String WINDOWS = "win";

    private final String targetOS;

    public RunningOnTargetOS(String targetOS) {
        this.targetOS = targetOS;
    }

    public boolean satisfy() {
        String osName = System.getProperty("os.name");
        return osName.toLowerCase().contains(targetOS);
    }
}
