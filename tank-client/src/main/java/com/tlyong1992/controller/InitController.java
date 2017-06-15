package com.tlyong1992.controller;

import com.tlyong1992.factory.TankFactory;
import com.tlyong1992.model.BaseTank;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.thread.EventThread;
import com.tlyong1992.thread.PaintThread;
import com.tlyong1992.view.MainView;
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

    @Resource
    MainView mainView;

    @Resource
    private KeyController keyController;

    @Resource
    private WindowController windowController;

    @PostConstruct
    public void init() {
        logger.info("初始化对象");
        //添加坦克对象
        BaseTank myTank = TankFactory.getDefaulMyTank();
        ObjectManager.singleTon.setMyTank(myTank);
//        for (int i = 0; i <= 1; i++) {
//            TankFactory.generateRandomEnemy();
//        }

        logger.info("初始化窗口");
        mainView.initWindow();
        //AddListener
        mainView.addWindowListener(windowController);
        mainView.addKeyListener(keyController);


        mainExecutor.submit(new PaintThread(mainView));
        mainExecutor.submit(new EventThread(mainView, ObjectManager.singleTon.getMyTank(), ObjectManager.singleTon.getEnemyTankList()));
    }

}
