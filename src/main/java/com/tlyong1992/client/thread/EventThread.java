package com.tlyong1992.client.thread;

import com.tlyong1992.client.model.Tank;
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

    List<Tank> tankList;

    Tank myTank;

    public EventThread(Tank myTank , List<Tank> objectList) {
        this.myTank = myTank;
        this.tankList = objectList;
    }

    @Override
    public void run() {
        logger.info("启动事件处理线程");
        while (true) {
            handleMove();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理对象的移动
     */
    private void handleMove() {
        myTank.move();
        for (Tank moveObject : tankList) {
            moveObject.move();
        }
    }
}
