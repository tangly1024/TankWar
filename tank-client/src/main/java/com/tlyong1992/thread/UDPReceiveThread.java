package com.tlyong1992.thread;

import com.tlyong1992.message.MsgNewTankJoin;
import com.tlyong1992.model.tank.BaseTank;
import com.tlyong1992.repository.ObjectManager;
import org.apache.log4j.Logger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * USER：tangly
 * DATE：2017/8/17
 * TIME：11:45
 */
public class UDPReceiveThread implements Runnable {

    Logger logger = Logger.getLogger(UDPSendThread.class);

    private DatagramSocket ds;

    public UDPReceiveThread(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        logger.info("启动本地UDP接收线程");
        DatagramPacket p;
        byte[] bytes = new byte[1024];
        p = new DatagramPacket(bytes, bytes.length);
        try {
            while (!ds.isClosed()) {

                ds.receive(p); //阻塞接受新数据包

                BaseTank tank = MsgNewTankJoin.parse(p);

                if(tank.getId() == ObjectManager.singleTon.getMyTank().getId()){
                    continue; //收到的数据包是自己发给自己的
                }

                boolean isExit = false;
                for(BaseTank tempTank : ObjectManager.singleTon.getOtherTanks()){
                    if(tempTank.getId() == tank.getId()){
                        isExit = true;
                        break;
                    }
                }
                //如果是不存在的新坦克则加入到系统中
                if(!isExit){
                    ObjectManager.singleTon.getOtherTanks().add(tank);
                }

            }
        } catch (SocketException se){
            if(se.getMessage().equals("socket closed")){
                logger.error("Socket关闭 终端UDP接收线程");
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }
}
