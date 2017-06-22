package com.tlyong1992.thread;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.tlyong1992.constant.ServerConstant.SERVER_PORT;

/**
 * 接收处理Socket连接线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:16
 */
@Component
public class AcceptThread implements Runnable {

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void run() {
        logger.info("启动服务器接收线程 监听端口:" + SERVER_PORT);
        ServerSocket ss;

        try {
            ss = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket s = ss.accept();
                logger.info("有新的连接: " + s + "\n");
//                mainView.getActiontarget().setText("有新的连接: " + s + "\n");

                try {
//                   MainEvent.addText("有新的连接: "+s);
//                    windowController.addText("有新的连接: "+s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
