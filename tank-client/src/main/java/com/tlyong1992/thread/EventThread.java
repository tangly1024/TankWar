package com.tlyong1992.thread;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.model.effect.Bullet;
import com.tlyong1992.model.tank.AITank;
import com.tlyong1992.model.tank.BaseTank;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.view.Impl.ClientMainView;
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

    List<BaseTank> tankList;

    BaseTank myTank;

    ClientMainView mainView;

    Random rand = new Random(); //用于AI坦克的随机动作

    public EventThread(ClientMainView mainView, BaseTank myTank, List<BaseTank> objectList) {
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
        Iterator<BaseTank> it = ObjectManager.singleTon.getOtherTanks().iterator();
        while(it.hasNext()){
            BaseTank enemy = it.next();
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
     * 处理AI对象的移动
     */
    private void handleMove() {
        myTank.move(mainView);
        for (BaseTank otherTank : tankList) {
           if(otherTank instanceof AITank){
               AITank aiTank = (AITank) otherTank;
               aiTank.countStep();
               //坦克对象的移动处理
               if(aiTank.getStepCount() > 30 && aiTank.getStepCount() < 60){
                   otherTank.move(mainView);
               }
               if( aiTank.getStepCount() == 30 ){
                   Dir dir = Dir.values()[rand.nextInt(8)];
                   aiTank.changeDir(dir);
                   otherTank.shoot();
               }
               if( aiTank.getStepCount() == 60){
                   aiTank.resetStepCount();
                   otherTank.shoot();
               }
           }

        }
    }
}
