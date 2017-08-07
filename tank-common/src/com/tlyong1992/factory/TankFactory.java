package com.tlyong1992.factory;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.constant.GameConstant;
import com.tlyong1992.model.EnemyTank;
import com.tlyong1992.model.MyTank;

import java.util.Random;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:49
 */
public class TankFactory {

    public static MyTank getDefaulMyTank() {
        return new MyTank(true, GameConstant.TANK_POSITION_DEFAULT_X, GameConstant.TANK_POSITION_DEFAULT_Y, GameConstant.TANK_MOVE_SPEED_X, GameConstant.TANK_MOVE_SPEED_Y, GameConstant.TANK_WIDTH, GameConstant.TANK_HEIGHT, Dir.STOP);
    }

    public static EnemyTank getEnmemyTank(int positionX, int positionY, Dir dir) {
        return new EnemyTank(false, positionX, positionY, GameConstant.TANK_MOVE_SPEED_X, GameConstant.TANK_MOVE_SPEED_Y, GameConstant.TANK_WIDTH, GameConstant.TANK_HEIGHT, dir);
    }

    public static EnemyTank generateRandomEnemy() {
        Random rand = new Random();
        Dir dir = Dir.values()[rand.nextInt(8)];
        EnemyTank enemyTank = TankFactory.getEnmemyTank(rand.nextInt(500) + 40, rand.nextInt(500) + 50, dir);
        return enemyTank;
    }
}
