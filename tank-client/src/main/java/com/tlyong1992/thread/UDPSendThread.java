package com.tlyong1992.thread;

import com.tlyong1992.message.MsgNewTankJoin;
import com.tlyong1992.repository.ObjectManager;
import org.apache.log4j.Logger;

import java.net.DatagramSocket;

/**
 * 客户端UDP发送接收线程
 * USER：tangly
 * DATE：2017/8/4
 * TIME：18:26
 */
public class UDPSendThread implements Runnable {

    Logger logger = Logger.getLogger(UDPSendThread.class);

    private DatagramSocket ds;

    public UDPSendThread(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        //发一个包给服务端
        logger.info("发一个包给服务端");
        try {
            MsgNewTankJoin.send(ds, ObjectManager.singleTon.getMyTank());
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {

        }
    }
}
