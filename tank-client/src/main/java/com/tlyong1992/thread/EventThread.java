package com.tlyong1992.thread;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.model.BaseTank;
import com.tlyong1992.model.Bullet;
import com.tlyong1992.model.EnemyTank;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.view.ClientMainView;
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

    ClientMainView mainView;

    public EventThread(ClientMainView mainView, BaseTank myTank, List<EnemyTank> objectList) {
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
     * 处理子弹的碰撞
     */
    private void handleAttack() {
        //TODO 处理武器的碰撞 有一定概率 子弹打不死
        Iterator<EnemyTank> it = ObjectManager.singleTon.getEnemyTankList().iterator();
        while(it.hasNext()){
            EnemyTank enemy = it.next();
            for (Bullet bullet : enemy.getBulletList()) {
                if(bullet.attackTank(myTank)){
                    enemy.setLive(false);
                    bullet.setLive(false);
                }
            }
            for (Bullet bullet : myTank.getBulletList()) {
                if(bullet.attackTank(enemy)){
                    enemy.setLive(false);
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
            enemyTank.countStep();

            //坦克对象的移动处理
            if(enemyTank.getStepCount() > 30 && enemyTank.getStepCount() < 60){
                enemyTank.move(mainView);
            }

            if( enemyTank.getStepCount() == 30 ){
                Dir dir = Dir.values()[rand.nextInt(8)];
                enemyTank.changeDir(dir);
                enemyTank.shoot();
            }

            if( enemyTank.getStepCount() == 60){
                enemyTank.resetStepCount();
                enemyTank.shoot();
            }

        }
    }
}
