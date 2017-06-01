package com.tlyong1992.client.controller;

import com.tlyong1992.client.factory.TankFactory;
import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.repository.ObjectManager;
import com.tlyong1992.client.thread.EventThread;
import com.tlyong1992.client.thread.PaintThread;
import com.tlyong1992.client.view.MainView;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
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

        mainExecutor.submit(new PaintThread(mainView));
        mainExecutor.submit(new EventThread(mainView, ObjectManager.singleTon.getMyTank(), ObjectManager.singleTon.getEnemyTankList()));
    }

}
