package com.tlyong1992.model;

/**
 * USER：tangly
 * DATE：2017/8/7
 * TIME：18:08
 */
public class Client {

    private int id;
    private String ipAddress;
    private int udpPort;
    private int tcpPort;

    public Client(int id, String ipAddress, int udpPort, int tcpPort) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.udpPort = udpPort;
        this.tcpPort = tcpPort;
    }
}
