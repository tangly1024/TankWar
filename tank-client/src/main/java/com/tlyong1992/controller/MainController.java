package com.tlyong1992.controller;

import com.tlyong1992.constant.ClientConstant;
import com.tlyong1992.factory.TankFactory;
import com.tlyong1992.model.MyTank;
import com.tlyong1992.net.NetManager;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.thread.EventThread;
import com.tlyong1992.thread.PaintThread;
import com.tlyong1992.view.ClientMainView;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.*;

/**
 * 初始化控制器，项目运行的入口
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:12
 */
@Controller
public class MainController {

    private Logger logger = Logger.getLogger(MainController.class);

    @Resource
    private ThreadPoolTaskExecutor mainExecutor;//线程调度

    @Resource
    private NetManager netManager;

    @Resource
    private ClientMainView mainView;

    @Resource
    private KeyController keyController;

    @Resource
    private WindowController windowController;

    @PostConstruct
    public void init() {
        logger.info("对象初始化");
        //添加坦克对象
        MyTank myTank = TankFactory.getDefaulMyTank();
        ObjectManager.singleTon.setMyTank(myTank);

        logger.info("窗口初始化");
        mainView.initWindow();
        //AddListener
        mainView.addWindowListener(windowController);
        mainView.addKeyListener(keyController);
        netManager.connect();

        //发一个包给服务端
        DatagramSocket  ds;
        byte[] bytes = new byte[1024];
        try {
            ds =  new DatagramSocket(ClientConstant.LOCAL_UDP_PORT,InetAddress.getLocalHost());
            ds.connect(InetAddress.getLocalHost(), ClientConstant.SERVER_UDP_PORT);

            ds.send(new DatagramPacket(bytes,bytes.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainExecutor.submit(new PaintThread(mainView));
        mainExecutor.submit(new EventThread(mainView, ObjectManager.singleTon.getMyTank(), ObjectManager.singleTon.getEnemyTankList()));
    }

}
