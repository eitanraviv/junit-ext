package com.googlecode.junit.ext.checkers;

import java.net.Socket;

public class SocketChecker implements Checker {
    private String host;
    private Integer port;

    public SocketChecker(String[] args) {
        this.host = args[0];
        this.port = Integer.valueOf(args[1]);
    }

    public SocketChecker() {
        super();
    }

    public boolean satisfy() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
