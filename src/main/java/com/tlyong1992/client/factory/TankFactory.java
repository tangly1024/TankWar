package com.tlyong1992.client.factory;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.constant.Direction;
import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.model.EnemyTank;
import com.tlyong1992.client.model.MyTank;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:49
 */
public class TankFactory {

    public static BaseTank getDefaulMyTank() {
        return new MyTank(true, Constant.TANK_POSITION_DEFAULT_X, Constant.TANK_POSITION_DEFAULT_Y, Constant.TANK_MOVE_SPEED_X, Constant.TANK_MOVE_SPEED_Y, Constant.TANK_WIDTH, Constant.TANK_HEIGHT, Direction.STOP);
    }

    public static EnemyTank getEnmemyTank(int positionX, int positionY, Direction dir) {
        return new EnemyTank(false, positionX, positionY, Constant.TANK_MOVE_SPEED_X, Constant.TANK_MOVE_SPEED_Y, Constant.TANK_WIDTH, Constant.TANK_HEIGHT, dir);
    }
}
