package com.tlyong1992.net;

import com.tlyong1992.constant.ClientConstant;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.thread.UDPThread;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

/**
 * USER：tangly
 * DATE：2017/6/15
 * TIME：13:54
 */
@Component
public class NetManager {

    private Logger logger = Logger.getLogger(this.getClass());

    private Socket s = null;

    public void connect() {
        try {
            s = new Socket(ClientConstant.SERVER_ADDRESS, ClientConstant.SERVER_TCP_PORT);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(1111);
//            dos.flush();
//            dos.close();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            int id = dis.readInt();
            ObjectManager.singleTon.getMyTank().setId(id);

            int udpPort = dis.readInt();

            new Thread(new UDPThread(ClientConstant.SERVER_ADDRESS,udpPort)).start();

            logger.info("Socket 已连接: " + s);
            logger.info("该客户端分配到的id为: " + id);
            logger.info("服务器的UDP端口为: " + udpPort);
        } catch (ConnectException e) {
            logger.error("连接不上服务器: IP " + ClientConstant.SERVER_ADDRESS + "; PORT " + ClientConstant.SERVER_TCP_PORT);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void disconnect() {
        if (s != null) {
            try {
                s.close();
                logger.info("关闭连接");
            } catch (IOException e) {
                logger.error("关闭连接异常", e);
                s = null;
            }
        }
    }

}
