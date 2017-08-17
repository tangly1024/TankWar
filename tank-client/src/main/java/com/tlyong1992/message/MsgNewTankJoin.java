package com.tlyong1992.message;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.constant.GameConstant;
import com.tlyong1992.model.tank.BaseTank;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;

/**
 * 新坦克加入的消息
 * USER：tangly
 * DATE：2017/8/7
 * TIME：14:11
 */
public class MsgNewTankJoin {

    static Logger logger = Logger.getLogger(MsgNewTankJoin.class);

    /**
     * 数据包写出方法
     *
     * @return
     */
    public static boolean send(DatagramSocket ds , BaseTank tank) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeInt(tank.getId());
            dos.writeInt(tank.getWidth());
            dos.writeInt(tank.getHeight());
            dos.writeInt(tank.getPositionX());
            dos.writeInt(tank.getPositionY());
            dos.flush();
            dos.close();
        } catch (IOException e) {
            logger.error(e);
            return false;
        }

        DatagramPacket dp = new DatagramPacket(baos.toByteArray(), baos.toByteArray().length);
        try {
            dp.setSocketAddress(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(),1024));
            ds.send(dp);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }

        return true;
    }

     public static BaseTank parse(DatagramPacket p){
        byte[] bytes;
        ByteArrayInputStream bais;
        try {
            bytes = p.getData();
            bais = new ByteArrayInputStream(bytes);
            DataInputStream dis = new DataInputStream(bais);
            int id = dis.readInt();
            int width = dis.readInt();
            int height =dis.readInt();
            int positionX = dis.readInt();
            int positionY = dis.readInt();
            BaseTank tank = new BaseTank(id, positionX, positionY, GameConstant.TANK_MOVE_SPEED_X, GameConstant.TANK_MOVE_SPEED_Y, width, height, Dir.D);
            return tank;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
