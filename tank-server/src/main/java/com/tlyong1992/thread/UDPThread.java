package com.tlyong1992.thread;

import com.tlyong1992.constant.ServerConstant;
import com.tlyong1992.view.ServerMainView;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * USER：tangly
 * DATE：2017/6/29
 * TIME：9:21
 */
public class UDPThread implements Runnable {

    Logger logger = Logger.getLogger(this.getClass());

    private ServerMainView frame;

    public UDPThread(ServerMainView frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(ServerConstant.SERVER_UDP_PORT);
            logger.info("UDP 服务器启动 监听端口" + ServerConstant.SERVER_UDP_PORT);

//            frame.getTextArea().setText(frame.getTextArea().getText() + dp.getAddress() + "; " + dp.getPort() + "; " + dp.getData() + "\n");


        } catch (SocketException e) {
            if(ds!=null){
                ds.close();
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
