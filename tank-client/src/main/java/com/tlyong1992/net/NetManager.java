package com.tlyong1992.net;

import com.tlyong1992.constant.ClientConstant;
import com.tlyong1992.repository.ObjectManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * USER：tangly
 * DATE：2017/6/15
 * TIME：13:54
 */
@Component
public class NetManager {

    private Logger logger = Logger.getLogger(this.getClass());

    private Socket s = null;

    private DatagramSocket ds = null;

    public DatagramSocket getUDPSocket() {
        return ds;
    }

    /**
     * TCP 连接成功后会返回服务端的UDP端口
     * @return
     */
    public Integer tcpConnect() {
        try {
            s = new Socket(ClientConstant.SERVER_ADDRESS, ClientConstant.SERVER_TCP_PORT);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(ClientConstant.CLIENT_UDP_PORT);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            int id = dis.readInt();
            ObjectManager.singleTon.getMyTank().setId(id);
            int udpPort = dis.readInt();//服务端告知UDP端口
            logger.info("Socket TCP已连接: " + s + "; 分配到的客户端id为: " + id);
            return udpPort;
        } catch (ConnectException e) {
            logger.error("连接不上TCP服务器: IP " + ClientConstant.SERVER_ADDRESS + "; PORT " + ClientConstant.SERVER_TCP_PORT);
            return null;
        } catch (Exception e) {
            logger.error("TCP 连接异常 ",e);
            return null;
        }
    }

    public void tcpDisconnect() {
        if (s != null) {
            try {
                s.close();
                logger.info("关闭TCP连接");
            } catch (IOException e) {
                logger.error("关闭TCP连接异常", e);
                s = null;
            }
        }
    }

    public void udpConnect(int serverUDPPort){
        logger.info("开始建立UDP连接: 本地端口号 " + ClientConstant.CLIENT_UDP_PORT);
        try {
            ds = new DatagramSocket(ClientConstant.CLIENT_UDP_PORT);
//            ds = new DatagramSocket();
//            ds.bind(new InetSocketAddress(InetAddress.getLocalHost() .getHostAddress(),ClientConstant.CLIENT_UDP_PORT));
//            ds.connect(InetAddress.getLocalHost(), serverUDPPort);
        } catch (Exception e) {
            logger.error("建立UDP连接异常", e);
        }
    }

    public void udpDisconnect(){
        logger.info("关闭UDP连接");
        if(ds!=null){
            ds.close();
        }
    }

}
