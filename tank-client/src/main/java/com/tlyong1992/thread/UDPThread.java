package com.tlyong1992.thread;

import java.net.DatagramSocket;

/**
 * 客户端UDP发送接收线程
 * USER：tangly
 * DATE：2017/8/4
 * TIME：18:26
 */
public class UDPThread implements Runnable {

    private String serverAddress;
    private int serverUDPPort;

    public UDPThread(String serverAddress, int serverUDPPort) {
        this.serverAddress = serverAddress;
        this.serverUDPPort = serverUDPPort;
    }

    @Override
    public void run() {
            DatagramSocket ds;
    }
}
