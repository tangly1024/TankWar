package com.tlyong1992.thread;

import com.tlyong1992.constant.ServerConstant;
import com.tlyong1992.view.ServerWindow;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * USER：tangly
 * DATE：2017/6/29
 * TIME：9:21
 */
public class UDPThread implements Runnable {

    Logger logger = Logger.getLogger(this.getClass());

    private ServerWindow mainView;

    public UDPThread(ServerWindow mainView) {
        this.mainView = mainView;
    }

    @Override
    public void run() {
        DatagramSocket ds = null;
        DatagramPacket p ;
        byte[] bytes = new byte[1024];
        try {
            ds = new DatagramSocket(ServerConstant.SERVER_UDP_PORT);
            mainView.showLog("UDP 服务器启动 监听端口" + ServerConstant.SERVER_UDP_PORT);
            p = new DatagramPacket(bytes, bytes.length);
            //死循环接收数据包
            while (true) {
                ds.receive(p);
                mainView.showLog("收到UDP数据包 IP: " + p.getAddress().getHostAddress() + " ; PORT: "+ p.getPort() + " ; DATA:"+ new String(p.getData()));
            }

        } catch (SocketException e) {
            if (ds != null) {
                ds.close();
            }
        } catch (IOException e) {
            mainView.showLog(e);
        }
    }
}
