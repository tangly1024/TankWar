package com.tlyong1992.thread;

import com.tlyong1992.model.Client;
import com.tlyong1992.view.ServerMainView;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tlyong1992.constant.ServerConstant.SERVER_PORT;

/**
 * 接收处理Socket连接线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:16
 */
public class AcceptThread implements Runnable {

    Logger logger = Logger.getLogger(this.getClass());

    AtomicInteger ids = new AtomicInteger(0);

    ServerMainView mainView;

    public AcceptThread(ServerMainView mainWindow) {
        this.mainView = mainWindow;
    }

    @Override
    public void run() {
        logger.info("启动服务器接收线程 监听端口:" + SERVER_PORT);
        ServerSocket ss;

        try {
            ss = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket s = ss.accept();
                String ipAddress = s.getLocalAddress().getHostAddress();
                int tcpPort =  s.getLocalPort();
                InputStream is = s.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                int udpPort = dis.readInt();
                s.close();
                int id = ids.addAndGet(1);
                Client client = new Client(id,ipAddress,udpPort,tcpPort);
                mainView.getTextArea().setText(mainView.getTextArea().getText() + "有新的连接: " + s + "\n" );
                mainView.getTextArea().setText(mainView.getTextArea().getText() + client + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
