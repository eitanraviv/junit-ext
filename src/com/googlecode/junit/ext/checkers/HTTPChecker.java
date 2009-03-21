package com.googlecode.junit.ext.checkers;

import com.googlecode.junit.ext.checkers.Checker;
import com.googlecode.junit.ext.checkers.SocketChecker;

public class HTTPChecker implements Checker {
    private SocketChecker socketChecker;


    public HTTPChecker(String url) {
        this(new String[]{url, "80"});
    }

    public HTTPChecker(String[] args) {
        String host = args[0].toLowerCase();
        String port = null;
        if (args.length == 1) {
            port = "80";
        } else {
            port = args[1];
        }
        host = host.replaceFirst("http://", "");
        host = host.replaceFirst("https://", "");
        socketChecker = new SocketChecker(new String[]{host, port});
    }

    public boolean satisfy() {
        return socketChecker.satisfy();
    }
}
