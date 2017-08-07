package com.tlyong1992.factory;

import com.tlyong1992.constant.GameConstant;
import com.tlyong1992.model.BaseTank;
import com.tlyong1992.model.Bullet;

/**
 * USER：tangly
 * DATE：2017/5/27
 * TIME：13:50
 */
public class BulletFactory {

    public static Bullet buildDefaultBullet(BaseTank tank){
        Bullet bullet = new Bullet(tank, GameConstant.BULLET_MOVE_SPEED_X, GameConstant.BULLET_MOVE_SPEED_Y, GameConstant.BULLET_WIDTH, GameConstant.BULLET_HEIGHT);
        return bullet;
    }
}
