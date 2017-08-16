package com.tlyong1992.thread;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.model.Bullet;
import com.tlyong1992.model.EnemyTank;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.view.MainView;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Random;

/**
 * 事件处理线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:16
 */
@Component
public class EventThread implements Runnable {

    @Resource
    private MainView clientMainWindow;

    private Logger logger = Logger.getLogger(EventThread.class);

    @Override
    public void run() {
        clientMainWindow.showLog(logger,"启动事件处理线程");
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
                if(bullet.attackTank(ObjectManager.singleTon.getMyTank())){
                    enemy.setLive(false);
                    bullet.setLive(false);
                }
            }
            for (Bullet bullet : ObjectManager.singleTon.getMyTank().getBulletList()) {
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
        ObjectManager.singleTon.getMyTank().move(clientMainWindow);

        Random rand = new Random();
        for (EnemyTank enemyTank : ObjectManager.singleTon.getEnemyTankList()) {
            enemyTank.countStep();

            //坦克对象的移动处理
            if(enemyTank.getStepCount() > 30 && enemyTank.getStepCount() < 60){
                enemyTank.move(clientMainWindow);
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
