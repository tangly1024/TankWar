package com.tlyong1992.controller;

import com.tlyong1992.constant.ClientConstant;
import com.tlyong1992.constant.Param;
import com.tlyong1992.factory.TankFactory;
import com.tlyong1992.net.NetManager;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.thread.EventThread;
import com.tlyong1992.thread.PaintThread;
import com.tlyong1992.view.impl.ClientMainWindow;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//import com.tlyong1992.view.impl.ClientMainWindow;

/**
 * 初始化控制器，项目运行的入口
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:12
 */
@Controller
public class MainController {

    private Logger logger = Logger.getLogger(MainController.class);

    //ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    @Resource
    private ThreadPoolTaskExecutor mainExecutor;//线程调度 在applicationContext.xml 中配置

    @Resource
    private NetManager netManager;

    @Resource
    private ClientMainWindow clientMainWindow;

    @Resource
    private KeyController keyController;

    @Resource
    private WindowController windowController;

    @Resource
    private ComponentController componentController;

    @Resource
    private PaintThread paintThread;

    @Resource
    private EventThread eventThread;

    @PostConstruct
    public void init() {
        logger.info("Initialing...");
        clientMainWindow.initWindow();

        clientMainWindow.showLog(logger, "对象初始化");
        //生成我的坦克对象
        ObjectManager.singleTon.setMyTank(TankFactory.getDefaulMyTank());

        //添加监听器
        clientMainWindow.showLog(logger, "添加窗口事件监听");
        clientMainWindow.addWindowListener(windowController);
        clientMainWindow.addComponentListener(componentController);

        clientMainWindow.showLog(logger, "添加按键监听");
        clientMainWindow.addKeyListener(keyController);

//        mainExecutor.submit(new PaintThread(clientMainWindow));
//        mainExecutor.submit(new EventThread(clientMainWindow));
        mainExecutor.submit(paintThread);
        mainExecutor.submit(eventThread);

        clientMainWindow.showLog(logger, "连接服务器");
        boolean connectResult = netManager.connect();

        ObjectManager.singleTon.getParamMap().put(Param.IS_CONNECT, connectResult);

        if (connectResult) {
            //发一个包给服务端
            DatagramSocket ds;
            try {
                ds = new DatagramSocket(ClientConstant.LOCAL_UDP_PORT, InetAddress.getLocalHost());
                ds.connect(InetAddress.getLocalHost(), ClientConstant.SERVER_UDP_PORT);
                String testSendText = "测试发送数据包";
                ds.send(new DatagramPacket(testSendText.getBytes(), testSendText.getBytes().length));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
