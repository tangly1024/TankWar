package com.tlyong1992.client.controller;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.constant.Direction;
import com.tlyong1992.client.factory.TankFactory;
import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.model.EnemyTank;
import com.tlyong1992.client.repository.ObjectManager;
import com.tlyong1992.client.thread.EventThread;
import com.tlyong1992.client.thread.PaintThread;
import com.tlyong1992.client.view.MainView;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:12
 */
@Controller
public class MainController {

    @Resource
    ThreadPoolTaskExecutor mainExecutor;//线程调度

    @Resource
    TankFactory tankFactory;//坦克工厂

    ObjectManager objectManager;//对象管理 单例

    @Resource
    MainView mainView;

    @PostConstruct
    public void init() {
        //添加坦克对象
        BaseTank myTank = tankFactory.getDefaulMyTank();
        objectManager.singleTon.setMyTank(myTank);
        Random rand = new Random();
        for (int i = 0; i <= 10; i++) {
            Direction dir = Direction.values()[rand.nextInt(8)];
            EnemyTank enemyTank = tankFactory.getEnmemyTank(rand.nextInt(Constant.WINDOW_WIDTH - 100), rand.nextInt(Constant.WINDOW_HEIGHT - 100), dir);
            objectManager.singleTon.getEnemyTankList().add(enemyTank);
        }

        mainView.initWindow();
//        mainView.getGraphics();
        mainExecutor.submit(new PaintThread(mainView));
        mainExecutor.submit(new EventThread(mainView, objectManager.singleTon.getMyTank(), objectManager.singleTon.getEnemyTankList()));
    }

}
