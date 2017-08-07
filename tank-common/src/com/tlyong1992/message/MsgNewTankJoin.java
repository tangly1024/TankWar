package com.tlyong1992.message;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.constant.GameConstant;
import com.tlyong1992.model.BaseTank;
import com.tlyong1992.model.EnemyTank;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * USER：tangly
 * DATE：2017/8/7
 * TIME：14:11
 */
public class MsgNewTankJoin {

    Logger logger = Logger.getLogger(MsgNewTankJoin.class);

    private BaseTank tank;

    private DatagramSocket ds;

    public MsgNewTankJoin(BaseTank tank, DatagramSocket ds) {
        this.tank = tank;
        this.ds = ds;
    }

    /**
     * 数据包写出方法
     *
     * @return
     */
    public boolean send() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeInt(tank.getId());
            dos.writeInt(tank.getWidth());
            dos.writeInt(tank.getHeight());
            dos.writeInt(tank.getPositionX());
            dos.writeInt(tank.getPositionY());
        } catch (IOException e) {
            logger.error(e);
            return false;
        }

        DatagramPacket dp = new DatagramPacket(baos.toByteArray(), baos.toByteArray().length);
        try {
            ds.send(dp);
        } catch (IOException e) {
            logger.error(e);
            return false;
        }

        return true;
    }

    /**
     * 数据包读取方法
     *
     * @return
     */
    public boolean receive(DatagramSocket ds) {
        byte[] bytes;
        ByteArrayInputStream bais;
        while (true) {
            bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            try {
                ds.receive(dp);
                bais = new ByteArrayInputStream(bytes);
                DataInputStream dis = new DataInputStream(bais);
                int id = dis.readInt();
                int width = dis.readInt();
                int height =dis.readInt();
                int positionX = dis.readInt();
                int positionY = dis.readInt();
                BaseTank tank = new EnemyTank(true,positionX,positionY, GameConstant.TANK_MOVE_SPEED_X,GameConstant.TANK_MOVE_SPEED_Y,width,height, Dir.D);
                logger.info("=====收到=====");
                logger.info(tank);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
