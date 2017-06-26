package com.tlyong1992.model;

/**
 * USER：tangly
 * DATE：2017/6/26
 * TIME：18:11
 */
public class Client {
    private String ip;
    private int udpPort;
    private int tcpPort;

    @Override
    public String toString() {
        return "Client{" +
                "ip='" + ip + '\'' +
                ", udpPort=" + udpPort +
                ", tcpPort=" + tcpPort +
                '}';
    }

    public Client(String ip, int udpPort, int tcpPort) {
        this.ip = ip;
        this.udpPort = udpPort;
        this.tcpPort = tcpPort;
    }

    public String getIp() {

        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }
}
