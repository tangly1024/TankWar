package com.tlyong1992.client.thread;

import com.tlyong1992.client.constant.Direction;
import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.model.Bullet;
import com.tlyong1992.client.model.EnemyTank;
import com.tlyong1992.client.repository.ObjectManager;
import com.tlyong1992.client.view.MainView;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

    MainView mainView;

    public EventThread(MainView mainView, BaseTank myTank, List<EnemyTank> objectList) {
        this.mainView = mainView;
        this.myTank = myTank;
        this.tankList = objectList;
    }

    @Override
    public void run() {
        logger.info("启动事件处理线程");
        while (true) {
            handleMove();
            handleAttack();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理武器的碰撞
     */
    private void handleAttack() {
        //TODO 处理武器的碰撞
        Iterator<EnemyTank> it = ObjectManager.singleTon.getEnemyTankList().iterator();
        while(it.hasNext()){
            EnemyTank enemy = it.next();
            for (Bullet bullet : myTank.getBulletList()) {
                if (enemy.getRect().intersects(bullet.getRect())) {
//                    enemy.setLive(false);
                    it.remove();
                    bullet.setLive(false);
                }
            }
        }
    }

    /**
     * 处理对象的移动
     */
    private void handleMove() {
        myTank.move(mainView);
        Random rand = new Random();
        for (EnemyTank enemyTank : tankList) {
            //坦克对象的移动处理
            enemyTank.move(mainView);
            enemyTank.countStep();
            if(enemyTank.getStepCount()>30){
                Direction dir = Direction.values()[rand.nextInt(8)];
                enemyTank.changeDir(dir);
            }

        }
    }
}
