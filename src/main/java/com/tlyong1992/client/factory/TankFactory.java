package com.tlyong1992.client.factory;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.constant.Dir;
import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.model.EnemyTank;
import com.tlyong1992.client.model.MyTank;
import com.tlyong1992.client.repository.ObjectManager;

import java.util.Random;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:49
 */
public class TankFactory {

    public static BaseTank getDefaulMyTank() {
        return new MyTank(true, Constant.TANK_POSITION_DEFAULT_X, Constant.TANK_POSITION_DEFAULT_Y, Constant.TANK_MOVE_SPEED_X, Constant.TANK_MOVE_SPEED_Y, Constant.TANK_WIDTH, Constant.TANK_HEIGHT, Dir.STOP);
    }

    public static EnemyTank getEnmemyTank(int positionX, int positionY, Dir dir) {
        return new EnemyTank(false, positionX, positionY, Constant.TANK_MOVE_SPEED_X, Constant.TANK_MOVE_SPEED_Y, Constant.TANK_WIDTH, Constant.TANK_HEIGHT, dir);
    }

    public static void generateRandomEnemy() {
        Random rand = new Random();
        Dir dir = Dir.values()[rand.nextInt(8)];
        EnemyTank enemyTank = TankFactory.getEnmemyTank(rand.nextInt(Constant.WINDOW_WIDTH - 100) + 40, rand.nextInt(Constant.WINDOW_HEIGHT - 100) + 50, dir);
        ObjectManager.singleTon.getEnemyTankList().add(enemyTank);
    }
}
