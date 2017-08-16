package com.tlyong1992.thread;

import com.tlyong1992.model.Client;
import com.tlyong1992.view.ServerWindow;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tlyong1992.constant.ServerConstant.SERVER_TCP_PORT;
import static com.tlyong1992.constant.ServerConstant.SERVER_UDP_PORT;

/**
 * 接收处理Socket连接线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:16
 */
public class AcceptThread implements Runnable {

    private Logger logger = Logger.getLogger(this.getClass());

    private AtomicInteger ids = new AtomicInteger(0);

    private ServerWindow mainView;

    public AcceptThread(ServerWindow mainWindow) {
        this.mainView = mainWindow;
    }

    @Override
    public void run() {
        mainView.showLog("TCP服务启动 监听端口:" + SERVER_TCP_PORT);
        ServerSocket ss;
        try {
            ss = new ServerSocket(SERVER_TCP_PORT);
            while (true) {
                Socket s = ss.accept();
                String ipAddress = s.getLocalAddress().getHostAddress();
                int tcpPort =  s.getLocalPort();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                int udpPort = dis.readInt();//读取UDP端口
                int id = ids.addAndGet(1);
                Client client = new Client(id,ipAddress,udpPort,tcpPort);
                mainView.showLog("有新的连接: " + s + "\n");
                mainView.showLog("client : " + client + "\n");

                //告诉客户端id
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeInt(id);

                dos.writeInt(SERVER_UDP_PORT);//告诉客户端UDP_PORT端口

                s.close();
            }
        } catch (IOException e) {
            mainView.showLog(e);
        }

    }

}
