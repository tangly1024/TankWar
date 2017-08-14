package com.tlyong1992.thread;

import com.tlyong1992.model.BaseTank;
import com.tlyong1992.model.EnemyTank;
import com.tlyong1992.view.ServerWindow;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 事件处理线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:16
 */
public class EventThread implements Runnable {

    Logger logger = Logger.getLogger(this.getClass());

    List<EnemyTank> tankList;

    BaseTank myTank;

    ServerWindow mainView;

    public EventThread(ServerWindow mainView, BaseTank myTank, List<EnemyTank> objectList) {
        this.mainView = mainView;
        this.myTank = myTank;
        this.tankList = objectList;
    }

    @Override
    public void run() {
        mainView.showLog("事件线程启动");
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
