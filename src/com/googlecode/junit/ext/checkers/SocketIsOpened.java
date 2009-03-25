package com.googlecode.junit.ext.checkers;

import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketIsOpened implements Checker {
    private String host;
    private Integer port;

    public SocketIsOpened(String[] args) {
        this.host = args[0];
        this.port = Integer.valueOf(args[1]);
    }

    public boolean satisfy() {
        Socket socket = null;
        try {
            Socket sock = new Socket();
            sock.bind(null);
            sock.connect(new InetSocketAddress(host, port), 10000);
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