package com.tlyong1992.controller;

import com.tlyong1992.factory.TankFactory;
import com.tlyong1992.model.tank.MyTank;
import com.tlyong1992.net.NetManager;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.thread.EventThread;
import com.tlyong1992.thread.PaintThread;
import com.tlyong1992.thread.UDPReceiveThread;
import com.tlyong1992.thread.UDPSendThread;
import com.tlyong1992.view.Impl.ClientMainView;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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
        Integer serverUdpPort = netManager.tcpConnect();
        if(serverUdpPort!=null){
            netManager.udpConnect(serverUdpPort);
            mainExecutor.submit(new UDPSendThread(netManager.getUDPSocket()));
            mainExecutor.submit(new UDPReceiveThread(netManager.getUDPSocket()));
        }
        mainExecutor.submit(new PaintThread(mainView));
        mainExecutor.submit(new EventThread(mainView, ObjectManager.singleTon.getMyTank(), ObjectManager.singleTon.getOtherTanks()));
    }

}
