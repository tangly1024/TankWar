package com.tlyong1992.controller;

import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.thread.AcceptThread;
import com.tlyong1992.thread.EventThread;
import com.tlyong1992.thread.PaintThread;
import com.tlyong1992.thread.UDPThread;
import com.tlyong1992.view.impl.ServerMainWindow;
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
public class InitController {

    Logger logger = Logger.getLogger(InitController.class);

    @Resource
    ThreadPoolTaskExecutor mainExecutor;//线程调度

//    @Resource
//    ServerMainView mainView;

    @Resource
    ServerMainWindow serverMainWindow;

    @PostConstruct
    public void init() {
        serverMainWindow.showLog("------------>START<--------------");
        mainExecutor.submit(new UDPThread(serverMainWindow));
        mainExecutor.submit(new PaintThread(serverMainWindow));
        mainExecutor.submit(new AcceptThread(serverMainWindow));
        mainExecutor.submit(new EventThread(serverMainWindow, ObjectManager.singleTon.getMyTank(), ObjectManager.singleTon.getEnemyTankList()));
    }

}
