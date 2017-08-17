package com.tlyong1992.thread;

import com.tlyong1992.constant.ServerConstant;
import com.tlyong1992.model.Client;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.view.ServerWindow;
import org.apache.log4j.Logger;

import java.net.*;

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
//            ds.bind(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(),ServerConstant.SERVER_UDP_PORT));
            mainView.showLog("UDP 服务器启动 监听端口" + ServerConstant.SERVER_UDP_PORT);
            p = new DatagramPacket(bytes, bytes.length);
            //死循环接收数据包
            while (true) {
                ds.receive(p);
                mainView.showLog("收到UDP数据包 IP: " + p.getAddress().getHostAddress() + " ; PORT: "+ p.getPort() + " ; DATA:"+ new String(p.getData()));
          //直接转发出去
                for(Client client : ObjectManager.singleTon.getClientList()){
                    p.setSocketAddress(new InetSocketAddress(client.getIpAddress(),client.getUdpPort()));
                    ds.send(p);
                    mainView.showLog("转发数据包给 " + client.getIpAddress() + " ; " + client.getUdpPort());
                    logger.info("转发数据包给 " + client.getIpAddress() + " ; " + client.getUdpPort());
                }
            }
        }catch (Exception e) {
            if (ds != null) {
                ds.close();
            }
            mainView.showLog(e);
            logger.error(e);
        }
    }
}
