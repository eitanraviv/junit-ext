package com.googlecode.junit.ext;

public enum PrerequisiteChecker {
    WINDOWS(new OSChecker("win")), MAC(new OSChecker("mac")), LINUX(new OSChecker("linux")),
    Nant(new CommandChecker("nant help")), Ant(new CommandChecker("ant help")), Hg(new CommandChecker("hg version")),

    NOT_SATISFIED(null);

    private final Checker checker;

    private PrerequisiteChecker(Checker checker) {
        this.checker = checker;
    }

    public boolean isSatisfied() {
        if (this.checker == null) {
            return false;
        }
        return this.checker.isExist();

    }

    public static interface Checker {
        public boolean isExist();
    }

    public static class CommandChecker implements Checker {
        private final String command;

        public CommandChecker(String command) {
            this.command = command;
        }

        public boolean isExist() {
            try {
                Runtime.getRuntime().exec(this.command);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    private static class OSChecker implements Checker {
        private final String targetOS;

        public OSChecker(String targetOS) {
            this.targetOS = targetOS;
        }

        public boolean isExist() {
            String osName = System.getProperty("os.name");
            return osName.toLowerCase().contains(targetOS);
        }
    }
}