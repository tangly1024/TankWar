package com.tlyong1992.net;

import com.tlyong1992.constant.Constant;
import com.tlyong1992.repository.ObjectManager;
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
            s = new Socket(Constant.SERVER_ADDRESS, Constant.SERVER_TCP_PORT);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(1111);
//            dos.flush();
//            dos.close();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            int id = dis.readInt();
            ObjectManager.singleTon.getMyTank().setId(id);

            logger.info("Socket 已连接: " + s);
            logger.info("该客户端分配到的id为: " + id);
        } catch (ConnectException e) {
            logger.error("连接不上服务器: IP " + Constant.SERVER_ADDRESS + "; PORT " + Constant.SERVER_TCP_PORT);
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
